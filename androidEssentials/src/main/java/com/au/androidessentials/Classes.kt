package com.au.androidessentials

class PairAnim{
     var enter:Int = 0
     var exit:Int = 0

     var popEnter:Int = 0
     var popExit:Int = 0

    constructor(enter:Int,exit:Int):this(enter,exit,0,0)
    constructor(enter: Int,exit: Int,popEnter:Int,popExit:Int){
        this.enter = enter
        this.exit = exit
        this.popEnter = popEnter
        this.popExit = popExit
    }
}
