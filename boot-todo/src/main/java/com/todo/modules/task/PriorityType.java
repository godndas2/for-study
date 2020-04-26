package com.todo.modules.task;

public enum PriorityType {

    High, Medium, Low;

    @Override
    public String toString(){
        switch (this){
            case High :
                return "High";
            case Medium :
                return "Medium";
            case Low :
                return "Low";
            default:
                return "none";
        }
    }

}
