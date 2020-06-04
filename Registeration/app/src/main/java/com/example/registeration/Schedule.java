package com.example.registeration;

import android.content.Context;
import android.widget.TextView;

public class Schedule {
    private String monday[] = new String[14];
    private String tuesday[] = new String[14];
    private String wednesday[] = new String[14];
    private String thursday[] = new String[14];
    private String friday[] = new String[14];

    public Schedule(){
        for(int i = 0 ; i < 14; i ++){
            monday[i] = "";
            tuesday[i] = "";
            wednesday[i] = "";
            thursday[i] = "";
            friday[i] = "";

        }
    }

    public  void addSchedule(String scheduleText){
        System.out.println("kkkkkkkkkkk1" + scheduleText);
        int temp;
        if((temp = scheduleText.indexOf("monday"))> -1) {

            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){

                if(scheduleText.charAt(i) == '['){
                    startPoint = i;
                    System.out.println(startPoint);
                    System.out.println("kkkkkkkkkkk1");
                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    System.out.println("kkkkkkkkkkk2");
                    monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }
        if((temp = scheduleText.indexOf("tuesday"))> -1) {

            temp += 8;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }
        if((temp = scheduleText.indexOf("wednesday"))> -1) {

            temp += 10;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                }
                if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }
        if((temp = scheduleText.indexOf("thursday"))> -1) {

            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }
        if((temp = scheduleText.indexOf("friday"))> -1) {

            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = "class";
                }
            }
        }
    }


    public boolean validate(String scheduleText){
        if (scheduleText.equals("")){
            return true;
        }
        int temp;

        if((temp = scheduleText.indexOf("monday"))> -1) {

            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    if(!monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("tuesday"))> -1) {

            temp += 8;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    if(!tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("wednesday"))> -1) {

            temp += 10;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    if(!wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("thursday"))> -1) {

            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    if(!friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        if((temp = scheduleText.indexOf("friday"))> -1) {

            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    if(!friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))].equals("")){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public  void addSchedule(String scheduleText, String courseTitle, String courseProfessor){
//        System.out.println("mmmmmmmmmmmmmmmm"+ scheduleText);
//        System.out.println("mmmmmmmmmmmmmmmm"+ courseTitle);
//        System.out.println("mmmmmmmmmmmmmmmm"+ courseProfessor);
        String professor;
        if(courseProfessor.equals("")){
            professor="";
        }else {
            professor = "(" + courseProfessor + ")";
        }

        int temp;
        if((temp = scheduleText.indexOf("monday"))> -1) {


            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length() && scheduleText.charAt(i) != ':'; i++){

                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    monday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                }
            }
        }
        if((temp = scheduleText.indexOf("tuesday"))> -1) {

            temp += 8;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    tuesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                }
            }
        }
        if((temp = scheduleText.indexOf("wednesday"))> -1) {
            System.out.println(temp);
            temp += 10;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;


                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    wednesday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                }
            }
        }
        if((temp = scheduleText.indexOf("thursday"))> -1) {

            temp += 9;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    thursday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                }
            }
        }
        if((temp = scheduleText.indexOf("friday"))> -1) {

            temp += 7;
            int startPoint = temp;
            int endPoint = temp;
            for(int i = temp; i < scheduleText.length()&& scheduleText.charAt(i) != ':'; i++){
                if(scheduleText.charAt(i) == '['){
                    startPoint = i;

                } if( scheduleText.charAt(i) == ']'){
                    endPoint = i;
                    friday[Integer.parseInt(scheduleText.substring(startPoint + 1, endPoint))] = courseTitle + professor;
                }
            }
        }
    }
    // print schedule list on time table
    public void setting(TextView[] monday, TextView[] tuesday, TextView[] wednesday, TextView[] thursday, TextView[] friday, Context context){



        for(int i = 0; i < 14; i++){
            System.out.println("!!!!!@#!@#!#====" + this.monday[i]);
            System.out.println("!!!!!@#!@#!#====" + this.tuesday[i]);
            System.out.println("!!!!!@#!@#!#====" + this.wednesday[i]);
            System.out.println("!!!!!@#!@#!#====" + this.thursday[i]);
            System.out.println("!!!!!@#!@#!#====" + this.friday[i]);
            if (!this.monday[i].equals("")){

                monday[i].setText(this.monday[i]);
                monday[i].setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));

            }
            if (!this.tuesday[i].equals("")){

                System.out.println("!!!!!@#!@#!#====" + this.tuesday[i]);
                System.out.println("!!!!!?????====" + tuesday[i].toString());
                tuesday[i].setText(this.tuesday[i]);
                tuesday[i].setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }if (!this.wednesday[i].equals("")){
                System.out.println("!!!!!@#!@#!#====" + this.wednesday[i]);
                System.out.println("!!!!!?????====" + wednesday[i].toString());
                wednesday[i].setText(this.wednesday[i]);
                wednesday[i].setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
            if (!this.thursday[i].equals("")){
                System.out.println("!!!!!@#!@#!#====" + this.thursday[i]);
                System.out.println("!!!!!?????====" + thursday[i].toString());
                thursday[i].setText(this.thursday[i]);
                thursday[i].setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }
            if (!this.friday[i].equals("")){
                System.out.println("!!!!!@#!@#!#====" + this.friday[i]);
                System.out.println("!!!!!?????====" + friday[i].toString());
                friday[i].setText(this.friday[i]);
                friday[i].setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
            }

        }


    }
}
