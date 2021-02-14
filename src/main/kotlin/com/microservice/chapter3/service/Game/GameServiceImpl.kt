package com.microservice.chapter3.service.Game

import com.microservice.chapter3.service.Customer.Customer
import com.microservice.chapter3.service.Customer.CustomerServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

class GameServiceImpl : GameService{
    override fun playRockScissorPaperWithComputer(gamePlayer: GamePlayer): GameResult? {
        TODO("Not yet implemented")
    }
//    override fun playRockScissorPaperWithComputer(gamePlayer:GamePlayer): GameResult? {
//
//        var customerServiceImp : CustomerServiceImp
//        var gameResult:GameResult = GameResult()
//        var computerSelection = (Math.random()*10).toInt()%3
//        var playerSelection = gamePlayer.selection
//        var differ = playerSelection - computerSelection
//
//        if((playerSelection == 0 && computerSelection == 2) || (playerSelection == 2 && computerSelection == 1)){ //lose
//
//        }else if(differ == 0){ // draw
//
//        }else if(differ < 0 || (playerSelection == 2 && computerSelection == 0)){ // win
//
//        }else if(differ > 0){ //lose
//
//        }
//    }
}

/*

0 : Rock
1 : Scissor
2 : Paper

       P C
주먹 가위 0 1 -1   3
주먹 주먹 0 0 0    2
주먹 보  0 2 -2   1

가위 가위 1 1 0    2
가위 주먹 1 0 1    4
가위 보  1 2 -1   3

보 가위 2 1 1     1
보 주먹 2 0 2     2
보 보  2 2 0     2

 */