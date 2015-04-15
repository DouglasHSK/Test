package com.worker;

public class Main {
    public static void main(String[] args) {
        Channel channel =ChannelFactory.newChannel("test",10);
        //channel.startWorkers();
       ClientThread c = new ClientThread("Alice", channel);
       c.start();
  //      new ClientThread("Bobby", channel).start();
  //      new ClientThread("Chris", channel).start();
        ViewThread v = new ViewThread("Viewer",channel);
        v.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        	
        }
        c.stop();
        v.stop();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        	
        }
        ChannelFactory.shutdown();
       
        
        //System.exit(0);
    }
}
