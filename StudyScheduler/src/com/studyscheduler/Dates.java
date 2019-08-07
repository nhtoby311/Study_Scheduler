package com.studyscheduler;

public enum Dates {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
    public String Task (UserInput input)
    {
        String result ="";
        for(Subject a : input.getList())
        {
            if (a.getTotal() != 0)
            {
                result = result + "\n" + "\t" + " " + a.getName() + " need to do: " + a.getTotal() ;
            }
            else
                {
                    result = result + "\n" + "\t" +"something went wrong :(";
                }
        }
        return result;
    }

}
