package com.studyscheduler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class UserInput {
    private Scanner a = new Scanner(System.in);
    private String UserIn;                                                                 //Temp variable store Scanner
    private Subject Input = new Subject();                                                  //new Subject
    private ArrayList<Subject> list = new ArrayList<>();
    private int Days;                                                                      //Days apart from current and deadline
    private Date now = new Date();
    private LocalDate localDate = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();        //Get current date
    private int Nmonth = localDate.getMonthValue();
    private int Nyears = localDate.getYear();
    private int Nday = localDate.getDayOfMonth();
    private int day;                                                                    //Store deadline date
    private int month;
    private int years;

    public int getDays() {
        return Days;
    }

    public void setDays(int days) {
        Days = days;
    }

    public ArrayList<Subject> getList() {
        return list;
    }

    public void setList(ArrayList<Subject> list) {
        this.list = list;
    }


    UserInput(Dates [] date)
    {
        DaySet();                                                                   //Input for deadline dates
        InputInterface(date);                                                       //Input for subject info
        while(true)
        {
            UserIn = a.nextLine();
            System.out.println();
            if (UserIn.equals("n") || UserIn.equals("y")) {
                if (UserIn.equals("y")) {
                    InputInterface(date);
                }
                else
                    break;
            }
            else {
                System.out.println("only yes or no");
            }
        }
    }

    private void InputInterface(Dates [] date)
    {
        Input = new Subject();                                          //Store subject, new so can add to list
        System.out.println("Name:");
        Input.setName(a.nextLine());
        System.out.println("Number of topics:" + " ([topics x subtopics] has to larger than:" + (Days/7)*date.length + "-> bc u assign "+ date.length + "days/week" + ")");
        Input.setTopic(Integer.parseInt(a.nextLine()));
        System.out.println("Number of subtopics:");
        Input.setSubTopic(Integer.parseInt(a.nextLine()));

        Input.Calculated((Days/7)*date.length);          //Subject per day
        list.add(Input);                                    //add to list
        System.out.println("More?:(y/n)");
    }
    private void DaySet()
    {
        System.out.println("What year will it be?:");
        years = Integer.parseInt(a.nextLine());
        System.out.println("What month will it be?:");
        month = Integer.parseInt(a.nextLine());
        System.out.println("What day will it be?:");
        day = Integer.parseInt(a.nextLine());

        DaySet_Confirm();                               //Yes or no confirm

        if (ValidateDate())                             //Check if date appropriate
        {
            setDays(CalculateDaysLeft());
            System.out.println("***You have: " + getDays() + " day(s) left***");
            System.out.println();
        }


        else
            {
            System.out.println("***Invalid input***");
            System.out.println();
            DaySet();                                   //Re-do if invalid deadline date input
            }

    }




    private int CalculateDaysLeft()                                 //Calculate day left
    {
        int result = 0;
        result = Calculated_Month(month,years) + day - Nday;
        return result;
    }

    private boolean ValidateDate()
    {
        if ((ValidateMonth() && ValidateDay())) {
            if (Nyears < years) {
                return true;
            } else if (Nyears == years) {
                if (Nmonth < month) {
                    return true;
                } else if (Nmonth == month) {
                    return Nday < day;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        else
            {
                return false;
            }
    }
    private void DaySet_Confirm()
    {
        System.out.println("Is this the deadline date?: " + day + "/" + month + "/" + years + " (y/n ?)");
        UserIn = a.nextLine();
        if (UserIn.equals("n") || UserIn.equals("y")) {
            if (UserIn.equals("n"))
            {
                DaySet();
            }
        }
        else
        {
            System.out.println("Yes or no only");
            DaySet_Confirm();
        }
    }


    private int Month_to_Day(int month_deadline,int Years)                  //Every month to days
        {
            int result = 0;
            switch (month_deadline) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                    case 12:
                    result = 31;
                    return result;

                case 2:
                    if ((Years - 2016)%4 == 0 )
                    {
                        result = 29;
                        return result;
                    }
                    else{
                    result = 28;
                    return result;}

                case 4:
                case 6:
                case 9:
                case 11:
                    result = 30;
                    return result;


            }
            return result;
        }

        private int Calculated_Month (int month_deadline,int Years)
        {
            int result = 0;
            if (Years == Nyears && month_deadline == Nmonth)                // stop at the current time
            {
                return result;
            }
            else
            {
                if (month_deadline  > 1 ) {

                    result += Month_to_Day(month_deadline -1 , Years) + Calculated_Month(month_deadline - 1, Years);       //add a month apart. go from deadline to current date, dont calculate the deadline month.
                }
                else        // at month_deadline == 1, January
                    {
                        result += Month_to_Day(12, Years) + Calculated_Month(12, Years-1);          //if Years of deadline is > then the current date.
                    }
            }
            return result;
        }

        private boolean ValidateMonth()
        {
            boolean result = false;
            if (month > 0 && month < 13)
            {
                result = true;
            }
            return result;
        }

        private boolean ValidateDay()
        {
            boolean result = false;
            if (day > 0 && month < 32)
            {
                result = true;
            }
            return result;
        }
}
