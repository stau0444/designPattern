package com.company.singleton;

public class SocketClient {

    private static SocketClient socketClient = null;

    //생성은 막아준다.
    private SocketClient(){};

    //다른 클래스에서 호출 가능한 메서드
    public static SocketClient getInstance(){
        if(socketClient == null){
            socketClient = new SocketClient();
        }
        return socketClient;
    };

    public void connect(){
        System.out.println("connect");
    };

}
