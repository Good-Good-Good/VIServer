package com.microservice.chapter3.service.Game

import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.stereotype.Component

@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GameResult(var result:Int=0, var computerSelection:Int=0, var userMoneyAfterGame:Int=0)