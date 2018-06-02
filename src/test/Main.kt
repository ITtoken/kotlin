package test

import subscription.action.Action
import subscription.action.ActionListener

fun main(args: Array<String>) {
    testAction()

}

fun testAction(){
    var actionListener = object : ActionListener<String>{
        override fun notifyMessage(msg: String) {
            print(msg)
        }
    }

var action  = Action<String>()

    action += actionListener /*register*/
    action.notify("test message after register")

    action -= actionListener/*unregister*/
    action.notify("test message after unregister")
}