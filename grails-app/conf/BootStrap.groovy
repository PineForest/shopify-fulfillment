import com.ipsy.shopify.Address
import com.ipsy.shopify.Fulfillment
import com.ipsy.shopify.LineItem
import com.ipsy.shopify.Receipt

class BootStrap {
    def init = {
        // Removes the class property
        grails.converters.JSON.registerObjectMarshaller(Fulfillment) {
            return it.properties.findAll {k,v -> k != 'class'}
        }
        /*
         * Converts 'variant_id' value of null to 0 and key name 'properties' to 'props'. Removes the 'class' and
         * 'fulfillment' properties
         */
        grails.converters.JSON.registerObjectMarshaller(LineItem) {
            Map newMap = new HashMap()
            it.properties.findAll {k,v -> k != 'class' && k != 'fulfillment'}.collect {
                if (it.key == 'properties') {
                    newMap.put("props", it.value)
                } else {
                    newMap.put(it.key, it.value)
                }
            }
            newMap
        }
        // Removes the class and id properties
        grails.converters.JSON.registerObjectMarshaller(Address) {
            return it.properties.findAll {k,v -> k != 'class' && k != 'id'}
        }
        // Removes the class and id properties
        grails.converters.JSON.registerObjectMarshaller(Receipt) {
            return it.properties.findAll {k,v -> k != 'class' && k != 'id'}
        }
    }

    def destroy = {
    }
}
