package com.example.glucosemeasure.model;

import android.view.View;
import android.widget.Toast;

import com.example.glucosemeasure.view.MainActivity;

public class Patient {
    private int age;
    private float value ;
    private boolean isfasting;
    private static String res ;

    //update controller --> view
    public Patient(int age, float vm, boolean isfasting){
        this.age=age;
        this.isfasting= isfasting;
        this.value=value;
        calcul();
    }
    public float getVm(){
        return value;
    }
    public int getAge(){
        return  age;
    }
    public boolean isIsfasting(){
        return isfasting;
    }

    //notify model --> controller
    public String getResponse(){
        return res;
    }
    private void calcul() {
        if (isfasting) {
            if (age >= 13) {
                if (value < 5.0)
                    res="Low blood sugar level";
                else if (value > 5.0 && value <= 7.2) {
                    res="Normal blood sugar level";
                } else {
                    res="Elevated blood glucose level";
                }
            } else if (age >= 6 && age <= 12) {
                if (value < 5.0)
                   res="Low blood sugar level";
                else if (value >= 5.0 && value <= 10.0)
                   res="Normal blood sugar level";
                else
                    res="Elevated blood glucose level";
            } else { // age < 6
                if (value < 5.5)
                   res="Low blood sugar level";
                else if (value > 5.5 && value <= 10)
                   res="Normal blood sugar level";
                else
                   res="Elevated blood glucose level";
            }
        } else {
            if (value < 10.5)
                res="Low blood sugar level";
            else
                res="Elevated blood glucose level";
        }
    }
}

