package com.microservice.chapter3.service.Customer

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Customer(var id:String = "",var password:String = "", var name:String = "", var money:Int = 0)