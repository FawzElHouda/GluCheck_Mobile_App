package com.example.glucosemeasure.controller;

import androidx.annotation.NonNull;

import com.example.glucosemeasure.model.Patient;

public class Controller {
    private static Patient p;


    //user action view --> controller
    public void createpatient (int age , float value, boolean Isfasting){
        p = new Patient(age, value, Isfasting);
    }
    //user action view --> controller
    public Controller(){
        super();
    }
        public String getResponse() { //notify controller --> view
        return p.getResponse();// notify model --> controller
    }
}
