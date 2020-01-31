package com.forstudy.efjava.ch02.item03;

// 바람직한 Singleton 방법
public enum  ElvisEnum {

    INSTANCE;

    public void leaveTheBuilding() {
        System.out.println("I am out");
    }

    public static void main(String[] args) {
        ElvisEnum elvisEnum = ElvisEnum.INSTANCE;
        elvisEnum.leaveTheBuilding();
    }


}
