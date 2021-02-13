package com.microservice.chapter3.service.Game

import com.microservice.chapter3.service.Customer.Customer


//class GameServiceImpl : GameService{
//    override fun playRockScissorPaperWithComputer(gamePlayer:GamePlayer): GameResult? {
//        var gameResult:GameResult = GameResult()
//        var computerSelection = (Math.random()*10).toInt()%3
//        var playerSelection = gamePlayer.selection
//        var differ = playerSelection - computerSelection
//
//        if((computerSelection == 2 && playerSelection == 0 ) || (computerSelection == 0 && playerSelection == 2)){
//
//        }else if(differ > 0){
//
//        }else if(differ < 0)
//    }
//}

/*
주먹 가위
- 주먹 주먹
주먹 보

- 가위 가위
가위 주먹
가위 보

보 가위
보 주먹
- 보 보

 */