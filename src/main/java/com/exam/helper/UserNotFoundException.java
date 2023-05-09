package com.exam.helper;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(){
        super("user with this username is not found");
    }


    public UserNotFoundException(String msg){
        super(msg);
    }
}
