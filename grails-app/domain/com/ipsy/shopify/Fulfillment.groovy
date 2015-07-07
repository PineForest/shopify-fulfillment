package com.ipsy.shopify

class Fulfillment {
    Date created_at
    Address destination
    String email
    // Long id: automatically added - see constraints, mapping
    // Set<LineItem> line_items: automatically added - see hasMany, mapping
    Integer order_id
    Receipt receipt
    String service
    String status
    String tracking_company
    String tracking_number
    // Set<String> tracking_numbers: automatically added - see hasMany
    String tracking_url
    // Set<String> tracking_urls: automatically added - see hasMany
    Date updated_at

    static embedded = ['destination', 'receipt']
    static hasMany = [line_items: LineItem, tracking_urls: String, tracking_numbers: String]
    static constraints = {
        created_at(nullable: true)
        email(email: true)
        id(bindable: true)
        service(nullable: true)
        tracking_url(url: true)
        updated_at(nullable: true)
    }
    static mapping = {
        id generator: 'assigned'
        line_items(cascade: 'all-delete-orphan')
    }

    @Override
    public String toString() {
        return "Fulfillment{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", destination=" + destination +
                ", email='" + email + '\'' +
                ", order_id=" + order_id +
                ", receipt=" + receipt +
                ", service='" + service + '\'' +
                ", status='" + status + '\'' +
                ", tracking_company='" + tracking_company + '\'' +
                ", tracking_number='" + tracking_number + '\'' +
                ", tracking_url='" + tracking_url + '\'' +
                ", updated_at=" + updated_at +
                ", version=" + version +
                ", line_items=" + line_items +
                ", tracking_urls=" + tracking_urls +
                ", tracking_numbers=" + tracking_numbers +
                '}';
    }
}
