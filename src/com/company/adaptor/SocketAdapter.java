package com.company.adaptor;

//220v를 110v로 변환해줄 adapter
public class SocketAdapter implements Electronic110V {

    private Electronic220V electronic220V;
    //220v를 받아서
    public SocketAdapter(Electronic220V electronic220V){
        this.electronic220V = electronic220V;
    }
    //110v에서 사용하게 해준다.
    @Override
    public void powerOn() {
        electronic220V.connect();
    }
}
