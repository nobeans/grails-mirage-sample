package com.area_b.samples

class CustomerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def customerService

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        def customers = customerService.list()
        [customers: customers]
    }

    def update() {
        customerService.update()
        redirect(action: "list")
    }
}
