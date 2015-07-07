package com.ipsy.shopify

class LineItem {
    Integer fulfillable_quantity
    String fulfillment_service
    String fulfillment_status
    Boolean gift_card
    Integer grams
    // Long id: automatically added - see constraints, mapping
    String name
    String price // TODO: look into converting to BigDecimal
    Boolean product_exists
    Long product_id
    List<String> props // the Shopify JSON calls this 'properties' - see BootStrap#init for conversion
    Integer quantity
    Boolean requires_shipping
    String sku
    List<String> tax_lines
    Boolean taxable
    String title
    String total_discount
    Integer variant_id
    String variant_inventory_management
    String variant_title
    String vendor
    // Fulfillment fulfillment automatically added - see belongsTo, mapping

    static belongsTo = [fulfillment: Fulfillment]
    static hasMany = [props: String, tax_lines: String]
    static constraints = {
        id(bindable: true)
        fulfillment_status(nullable: true)
        props(nullable: true)
        tax_lines(nullable: true)
        variant_id(nullable: true)
        variant_inventory_management(nullable: true)
        variant_title(nullable: true)
        vendor(nullable: true)
    }
    static mapping = {
        id generator: 'assigned'
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        LineItem lineItem = (LineItem) o

        if (id != lineItem.id) return false
        if (version != lineItem.version) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + (version != null ? version.hashCode() : 0)
        return result
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "id=" + id +
                ", fulfillable_quantity=" + fulfillable_quantity +
                ", fulfillment_service='" + fulfillment_service + '\'' +
                ", fulfillment_status='" + fulfillment_status + '\'' +
                ", gift_card=" + gift_card +
                ", grams=" + grams +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", product_exists=" + product_exists +
                ", product_id=" + product_id +
                ", props=" + props +
                ", quantity=" + quantity +
                ", requires_shipping=" + requires_shipping +
                ", sku='" + sku + '\'' +
                ", tax_lines=" + tax_lines +
                ", taxable=" + taxable +
                ", title='" + title + '\'' +
                ", total_discount='" + total_discount + '\'' +
                ", variant_id=" + variant_id +
                ", variant_inventory_management='" + variant_inventory_management + '\'' +
                ", variant_title='" + variant_title + '\'' +
                ", vendor='" + vendor + '\'' +
                ", version=" + version +
                ", fulfillment ID=" + (fulfillment == null ? "null" : fulfillment.id) +
                '}';
    }
}
