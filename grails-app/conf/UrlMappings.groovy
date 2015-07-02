class UrlMappings {

	static mappings = {
        "/fulfillment-simulator"(resources:'fulfillmentSimulator')
        "/fulfillment-sniffer"(resources:'fulfillmentSniffer')

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
