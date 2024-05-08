package org.example.OUTPUT

import org.example.DBSF.DataSourceFactory
import org.example.ENTITY.UserEntity
import org.example.SERVICE.UserServiceImpl
import javax.sql.DataSource

class Console() {


    fun showMessage(message:String, lineBreak:Boolean){
        if (lineBreak){
            println(message)
        }
        else{
            print(message)
        }
    }

    fun showUsers(list: List<UserEntity>){

        if (list.isEmpty()){
            showMessage("NO USERS FOUND!!", true)
        }
        else{
            var cont = 1
            showMessage("ALL USERS:", true)
            list.forEach{
                showMessage("   $cont --> ${it.id} -- ${it.name} -- ${it.email}", true)
                cont++
            }
        }
    }
}