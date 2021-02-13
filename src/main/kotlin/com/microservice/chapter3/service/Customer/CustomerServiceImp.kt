package com.microservice.chapter3.service.Customer

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImp : CustomerService {

    companion object {
        val iniCustomers = arrayOf(
            Customer("admin", "1234", "kimjung")
        )
    }

    val customers = ConcurrentHashMap<String, Customer>(iniCustomers.associateBy(Customer::id))

    override fun getCustomer(id: String): Customer? {
        return customers[id]
    }

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun deleteCustomer(id: String) {
        customers.remove(id)
    }

    override fun updateCustomer(id: String, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun searchCustomer(nameFilter: String): List<Customer> =
        customers.filter {
            it.value.id.contains(nameFilter, true)
        }.map(Map.Entry<String, Customer>::value).toList()
}