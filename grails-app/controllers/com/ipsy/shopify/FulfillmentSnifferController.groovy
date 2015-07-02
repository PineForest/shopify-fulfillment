package com.ipsy.shopify

import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = false)
class FulfillmentSnifferController extends RestfulController {
    static responseFormats = ['json']

    FulfillmentSnifferController() {
        super(FulfillmentSniffer)
    }

    def save = {
        final FulfillmentSniffer f = new FulfillmentSniffer(new Date(), request.requestURI, request.JSON.toString())
        f.save(flush: true)
        render params
    }
}
