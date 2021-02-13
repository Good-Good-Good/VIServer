package com.microservice.chapter3

import com.microservice.chapter3.service.Customer.Customer
import com.microservice.chapter3.service.Customer.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomController {
    @Autowired
    lateinit var customerService: CustomerService

    @PostMapping(value = ["/login"])
    fun authenticateLogin(@RequestBody customer: Customer): ResponseEntity<Customer?> {
        var status: HttpStatus
        if(customer.id == "" || customer.password == ""){ // check id, password existence
            status = HttpStatus.NOT_FOUND
            return ResponseEntity(null, status)
        } else { // check if id, password matches
            val customerDB = customerService.getCustomer(customer.id)
            if(customerDB == null) {
                status = HttpStatus.NOT_FOUND
                return ResponseEntity(null, status)
            }
            if(customerDB.password != customer.password) {
                status = HttpStatus.FORBIDDEN
                return ResponseEntity(null, status)
            } else {
                status = HttpStatus.OK
                return ResponseEntity(customerDB, status)
            }
        }
    }

    @PostMapping(value = ["/register"])
    fun register(@RequestBody customer: Customer): ResponseEntity<Customer?>{
        var status : HttpStatus
        if(customer.id == "" || customer.password == "" || customer.name == ""){
            status = HttpStatus.CONFLICT
            return ResponseEntity(null, status)
        }else{
            val regex = """^[\w]+$""".toRegex()
            if(customerService.getCustomer(customer.id) != null){
                status = HttpStatus.CONFLICT
                return ResponseEntity(null, status)
            }
//            if(regex.containsMatchIn(customer.id) && regex.containsMatchIn(customer.password) && regex.containsMatchIn(customer.name)){
                status = HttpStatus.OK
                customerService.createCustomer(customer)
                return ResponseEntity(customerService.getCustomer(customer.id), status)
//            }else{
//                status = HttpStatus.FORBIDDEN
//                return ResponseEntity(null, status)
//            }
        }
    }

    @DeleteMapping(value = ["/register"])
    fun deleteAccount(@RequestBody customer: Customer): ResponseEntity<Customer?> {
        var status: HttpStatus
        if(customer.id == "" || customer.password == ""){ // check id, password existence
            status = HttpStatus.NOT_FOUND
            return ResponseEntity(null, status)
        } else { // check if id, password matches
            val customerDB = customerService.getCustomer(customer.id)
            if(customerDB == null) {
                status = HttpStatus.NOT_FOUND
                return ResponseEntity(null, status)
            }
            if(customerDB.password != customer.password) {
                status = HttpStatus.FORBIDDEN
                return ResponseEntity(null, status)
            } else {
                status = HttpStatus.OK
                customerService.deleteCustomer(customer.id)
                return ResponseEntity(null, status)
            }
        }
    }
}



//@RestController
//class gameLogic {
//
//    @PostMapping("/RockSissorPaper")
//    fun rspLogic(@RequestBody customer:Customer): ResponseEntity<String> {
//        val status: HttpStatus = HttpStatus.OK
//    }
//}