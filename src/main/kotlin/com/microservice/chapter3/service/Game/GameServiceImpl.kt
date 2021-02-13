package com.microservice.chapter3.service.Game

import com.microservice.chapter3.service.Customer.Customer


//class GameServiceImpl : GameService{
//    override fun playRockScissorPaperWithComputer(gamePlayer:GamePlayer): GameResult? {
//        var gameResult:GameResult = GameResult()
//        var computerSelection = (Math.random()*10).toInt()%3
//        var playerSelection = gamePlayer.selection
//        var differ = playerSelection - computerSelection
//
//        if(differ == 0){ // draw
//
//        }else if(differ > 0){ // win
//
//        }else if(differ < 0){ // lose
//
//        }
//    }
//}

/*

0 : Rock
1 : Scissor
2 : Paper

       P C
주먹 가위 0 1 -1
주먹 주먹 0 0 0
주먹 보  0 2 -2

가위 가위 1 1
가위 주먹 1 0
가위 보  1 2

보 가위 2 1
보 주먹 2 0
보 보

 */