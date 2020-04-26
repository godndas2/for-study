package com.todo.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OverTimeTaskDto {

    private int count;
    private List<String> titles;

    public OverTimeTaskDto(){
        titles = new ArrayList<>();
    }
    public void addTitle(String title){
        titles.add(title);
    }
}
