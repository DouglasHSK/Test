/*
 * ViewThread.java
 *
 * Created on 2007�~08��12�� �P����, �U��8:52
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worker;

/**
 *
 * @author Douglas
 */

public class ViewThread extends Thread{
      private final Channel channel;
      
    /** Creates a new instance of ViewThread */
    public ViewThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    public void run() {
        for (int i = 0; true; i++) {
            
            channel.viewRequests(new ViewRequest());
           
        }
    }
    
}
