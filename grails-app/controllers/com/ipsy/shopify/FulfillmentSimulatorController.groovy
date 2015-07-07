package com.ipsy.shopify

import grails.rest.RestfulController
import grails.transaction.Transactional
import org.codehaus.groovy.grails.web.json.JSONObject

@Transactional(readOnly = false)
class FulfillmentSimulatorController extends RestfulController {
    static responseFormats = ['json']

    FulfillmentSimulatorController() {
        super(Fulfillment)
    }

    def save = {
        Fulfillment fulfillment = new Fulfillment(request.JSON)
        // Should not be necessary, but the JSON converter does not seem to use addTo
        fulfillment.line_items.each { it.fulfillment = fulfillment }
        fulfillment.save(failOnError:true, flush: true)
        render('action': 'save', 'controller': 'fulfillmentSimulator')
    }

    def update = {
        int id = ((JSONObject) request.JSON).get("id")
        Fulfillment fulfillment = Fulfillment.get(id)
        deepCopy(fulfillment, ((JSONObject) request.JSON).entrySet())
        // Should not be necessary, but the JSON converter does not seem to use addTo
        // fulfillment.line_items.each { it.fulfillment = fulfillment }
        fulfillment.save(failOnError:true, flush: true)
        render('action': 'update', 'controller': 'fulfillmentSimulator')
    }

    // TODO: validate that other properties that are collection types are properly handled (e.g. tracking_urls)
    private def deepCopy = { Fulfillment fulfillment, Set bindingProperties ->
        fulfillment.properties = bindingProperties
        deepCopyEmbedded "destination", fulfillment, bindingProperties
        deepCopyEmbedded "receipt", fulfillment, bindingProperties
        List lineItems = bindingProperties.find {
            it.key.equals "line_items"
        }.value
        // clear() *may* actually effect gorm metadata regarding collections, so I have extra logic to properly use it
        if (fulfillment.line_items == null) {
            fulfillment.line_items = new HashSet<LineItem>()
        }
        if (lineItems == null || lineItems.empty) {
            fulfillment.line_items.clear()
        } else {
            if (fulfillment.line_items == null) {
                fulfillment.line_items = new HashSet<LineItem>()
            }
        }
        Set retentionSet = new HashSet<LineItem>()
        lineItems?.each {
            LineItem lineItem = new LineItem(it)
            LineItem currentLineItem = fulfillment.line_items.find { it?.id.equals(lineItem?.id) }
            if (currentLineItem == null) {
                fulfillment.addToLine_items lineItem
                retentionSet.add lineItem
            } else {
                currentLineItem.properties = lineItem.properties.findAll {k,v -> k != 'version' && k != 'fulfillment'}
                retentionSet.add currentLineItem
            }
        }
        fulfillment.line_items.retainAll retentionSet
        log.error 'test'
    }

    private def deepCopyEmbedded = { String propertyName, Fulfillment fulfillment, Set bindingProperties ->
        Map newProperties = bindingProperties.find {
            it.key.equals(propertyName)
        }.value
        if (newProperties == null || newProperties.empty) {
            fulfillment.setProperty(propertyName, propertyName.equals("receipt") ? new Receipt() : new Address())
        } else {
            if (fulfillment.getProperty(propertyName) == null) {
                fulfillment.setProperty(propertyName, propertyName.equals("receipt") ? new Receipt() : new Address())
            }
            fulfillment.getProperty(propertyName).properties = newProperties
        }
    }
}
