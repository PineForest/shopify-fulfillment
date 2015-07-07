package com.ipsy.shopify

class Address {
    String address1
    String address2
    String city
    String company
    String country
    String country_code
    String first_name
    String last_name
    String latitude
    String longitude
    String name
    String phone
    String province
    String province_code
    String zip

    static constraints = {
        address2(nullable: true)
        latitude(nullable: true)
        longitude(nullable: true)
    }
}
