package com.ipsy.shopify

class FulfillmentSniffer {
    Date timestamp;
    String urlValue, jsonValue

    FulfillmentSniffer(Date timestamp, String urlValue, String jsonValue) {
        this.timestamp = timestamp
        this.urlValue = urlValue
        this.jsonValue = jsonValue
    }

    static constraints = {
        timestamp (blank: true, bindable: false, nullable: true)
        urlValue (blank: true, bindable: false, nullable: true)
        jsonValue (blank: true, bindable: false, nullable: true)
    }

    static mapping = {
        jsonValue column: "jsonValue", sqlType: "varchar", length: 3000
    }
}
