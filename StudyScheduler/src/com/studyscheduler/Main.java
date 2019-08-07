package com.studyscheduler;


public class Main {

    public static void main(String[] args)
    {
        Dates[] pickedDates = {Dates.MONDAY,Dates.TUESDAY,Dates.WEDNESDAY,Dates.THURSDAY,Dates.FRIDAY}; //how many days per weeks u want
        UserInput input = new UserInput(pickedDates);


        for (Dates a : pickedDates)
        {
            System.out.println(a + "\t" + a.Task(input));
        }


    }
}
