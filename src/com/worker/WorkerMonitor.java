/*
 * WorkerMonitor.java
 *
 * Created on 2008年01月02日 星期三, 上午11:54
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.worker;

import java.util.Enumeration;

class ViewRequest implements  IViewRequest{
    private String List="";
        public void viewRequest(IRequest Request){
            List+="  --> "+Request.toString()+"\n";
        }

    public String getList() {
        return List;
    }
       
}
/**
 *
 * @author Dou
 */
public class WorkerMonitor {
    
    /** Creates a new instance of WorkerMonitor */
    public WorkerMonitor() {
    }
    
    public static synchronized String ListAll(){
        int i;
        StringBuffer SB = new StringBuffer();
        
        Enumeration keys = ChannelFactory.getChannels().keys();
        while (keys.hasMoreElements()){
            String key=keys.nextElement().toString();
            Channel channel = ChannelFactory.getChannel(key);
            i=channel.getCount();
            ViewRequest VR= new ViewRequest();
            channel.viewRequests(VR);
            SB.append("-- " + key + " : " +i +"\n");
            SB.append(VR.getList());
            
        }
            
            return SB.toString();
        }
        
    }
    

