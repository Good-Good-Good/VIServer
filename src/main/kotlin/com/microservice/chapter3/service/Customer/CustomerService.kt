package com.microservice.chapter3.service.Customer

interface CustomerService {
    fun getCustomer(id: String): Customer?
    fun createCustomer(customer: Customer)
    fun deleteCustomer(id:String)
    fun updateCustomer(id:String, customer: Customer)
    fun searchCustomer(nameFilter:String):List<Customer>
}