package com.company.adaptor;

public class HairDryer implements Electronic110V{
    @Override
    public void powerOn() {
        System.out.println("충전기 110v on");
    }
}
