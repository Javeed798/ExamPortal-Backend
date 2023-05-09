package com.exam.helper;

public class UserFoundException extends Exception{
    public UserFoundException(){
        super("user with this username already exists");
    }


    public UserFoundException(String msg){
        super(msg);
    }
}
