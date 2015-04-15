package com.worker;

import com.worker.Request;
public class ClientThread extends Thread {
    private final Channel channel;
    public ClientThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    
    public void run() {
        for (int i = 0; true; i++) {
            Request request = new Request(getName(), i);
            System.out.println("Put " +request.toString());
            channel.putRequest(request);
        }
    }
}
