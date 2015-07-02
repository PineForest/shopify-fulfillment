package com.ipsy.shopify

import grails.rest.RestfulController
import grails.transaction.Transactional

@Transactional(readOnly = false)
class FulfillmentSimulatorController extends RestfulController {
    static responseFormats = ['json']

    FulfillmentSimulatorController() {
        super(FulfillmentSimulator)
    }
}
