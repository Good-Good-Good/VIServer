package com.microservice.chapter3

import com.microservice.chapter3.service.Customer.Customer
import com.microservice.chapter3.service.Customer.CustomerService
import com.microservice.chapter3.service.Customer.CustomerServiceImp
import com.microservice.chapter3.service.Game.GamePlayer
import com.microservice.chapter3.service.Game.GameResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler

@RestController
class CustomController {
    @Autowired
    lateinit var customerService: CustomerService
    @Autowired
    lateinit var gameResult: GameResult

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
//            val regex = """^[\w]+$""".toRegex()
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

    @PostMapping("/RockScissorPaper")
    fun rspLogic(@RequestBody gamePlayer:GamePlayer): ResponseEntity<GameResult?> {
        var status: HttpStatus = HttpStatus.OK

        var computerSelection = (Math.random()*10).toInt()%3
        var playerSelection = gamePlayer.selection
        var differ = playerSelection - computerSelection

        var player = customerService.getCustomer(gamePlayer.id)
        if(player != null) { // if the player exist in the databse
            var currentMoney = player.money
            var result = 0
            if ((playerSelection == 0 && computerSelection == 2) || (playerSelection == 2 && computerSelection == 1)) { //lose
                currentMoney = (currentMoney.toDouble() * 0.1).toInt()
                result = 1
            } else if (differ == 0) { // draw
                currentMoney = (currentMoney.toDouble() * 1.1).toInt()
                result = 2
            } else if (differ < 0 || (playerSelection == 2 && computerSelection == 0)) { // win
                currentMoney = currentMoney * 2
                result = 0
            } else if (differ > 0) { //lose
                currentMoney = (currentMoney.toDouble() * 0.1).toInt()
                result = 1
            }
            player.money = currentMoney
            customerService.updateCustomer(player.id, player)
            gameResult.computerSelection = computerSelection
            gameResult.result = result
            gameResult.userMoneyAfterGame = currentMoney
            return ResponseEntity(gameResult, status)
        }else { //if the player doesn't exist int the databse
            status = HttpStatus.CONFLICT
            return ResponseEntity(null, status)
        }
    }
}