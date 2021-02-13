package com.microservice.chapter3.service.Game

import com.microservice.chapter3.service.Customer.Customer

interface GameService {
    fun playRockScissorPaperWithComputer(gamePlayer:GamePlayer):GameResult?
//    fun playRockScissorPaperWithPlayer(customer1:Customer, customer2:Customer):GameResult?
}