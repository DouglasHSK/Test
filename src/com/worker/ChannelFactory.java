/*
 * ChannelFactory.java
 *
 * Created on 2007年08月12日 星期日, 下午6:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worker;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 *
 * @author Douglas
 */
public class ChannelFactory {
    private final static ChannelFactory Instance =  new ChannelFactory();
    private static Hashtable Channels = new Hashtable();
    /** Creates a new instance of ChannelFactory */
    private  ChannelFactory() {
    }
    
    public static ChannelFactory getInstance(){
        return Instance;
    }
    
    public static synchronized Channel newChannel(String Name,int workerCount){
        Channel c;
        getChannels().put(Name,c=new Channel(Name,workerCount));
        return c;
    }
    
    public static synchronized Channel getChannel(String Name){
        if( getChannels().get(Name) == null)  newChannel(Name,1);
        return (Channel) getChannels().get(Name);
    }
    
    public static synchronized Channel addRequest(String Name,IRequest Request){
         Channel c=getChannel(Name);
         c.putRequest(Request);
         return c;
    }

    public static Hashtable getChannels() {
        return Channels;
    }
    
    public static synchronized void shutdown(){
    	Enumeration keys = getChannels().keys();
    	while (keys.hasMoreElements()){
    		String key = (String) keys.nextElement();
    		Channel c = (Channel) getChannels().get(key);
    		c.stopWorkers();
    	}
    }
    
    
}
