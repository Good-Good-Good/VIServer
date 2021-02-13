package com.microservice.chapter3.service.Game

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class GamePlayer(var id:String = "",var selection:Int = 0, var betAmount:Int = 0)