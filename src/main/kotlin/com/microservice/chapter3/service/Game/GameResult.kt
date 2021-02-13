package com.microservice.chapter3.service.Game

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GameResult(var result:Int="", var computerSelection:String="", var userMoneyAfterGane:Int=0)