package com.studyscheduler;

class Subject {

    private String Name;
    private int Topic;
    private int SubTopic;
    private int Total;

    int getTotal() {
        return Total;
    }

    void setTotal(int total) {
        Total = total;
    }

    String getName() {
        return Name;
    }

    void setName(String name) {
        Name = name;
    }

    public int getTopic() {
        return Topic;
    }

    void setTopic(int topic) {
        Topic = topic;
    }

    public int getSubTopic() {
        return SubTopic;
    }

    void setSubTopic(int subTopic) {
        SubTopic = subTopic;
    }


    void Calculated(int days)
    {
        Total = (Topic * SubTopic) / days;           //Subject needs to be done each days
    }
}
