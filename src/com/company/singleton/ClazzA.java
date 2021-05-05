package com.company.singleton;

public class ClazzA {

    private SocketClient socketClient;

    public ClazzA(){
        this.socketClient = SocketClient.getInstance();
    };

    public SocketClient getSocketClient(){
        return this.socketClient;
    };

}
