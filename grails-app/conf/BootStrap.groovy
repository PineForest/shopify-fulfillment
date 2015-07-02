class BootStrap {

    def init = { servletContext ->
        /*
           Kept for reference

        new Book(title:"The Stand").save()
        new Book(title:"The Shining").save()

        grails.converters.JSON.registerObjectMarshaller(Book) {
            return it.properties.findAll {k,v -> k != 'class'}
        }
        */
    }

    def destroy = {
    }
}
