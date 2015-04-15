package com.worker;

import javax.naming.Name;

public class Channel {
    private static final int MAX_REQUEST = 1000;
    private String Name="";
    private final IRequest[] requestQueue;
    private int tail;  
    private int head; 
    private int count; 

    private final WorkerThread[] threadPool;

    public Channel(String Name,int threads) {
        this.requestQueue = new IRequest[MAX_REQUEST];
        this.head = 0;
        this.tail = 0;
        this.count = 0;
        this.Name=Name;
        threadPool = new WorkerThread[threads];
        for (int i = 0; i < threadPool.length; i++) {
            threadPool[i] = new WorkerThread(Name +".Worker-" + i, this);
        }
         startWorkers();
    }
    
    public synchronized void startWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
        	threadPool[i].setRun(true);
            threadPool[i].start();
        }
    }

    public synchronized void stopWorkers() {
        for (int i = 0; i < threadPool.length; i++) {
        	threadPool[i].setRun(false);
        	notifyAll();
        }
    }

    
    public synchronized void putRequest(IRequest request) {
        while (getCount() >= requestQueue.length) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        requestQueue[tail] = request;
        tail = (tail + 1) % requestQueue.length;
        count++;
        notifyAll();
    }
    
    public synchronized IRequest takeRequest() {
        while (getCount() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        IRequest request = requestQueue[head];
        head = (head + 1) % requestQueue.length;
        count--;
        notifyAll();
        return request;
    }
    
    public synchronized void viewRequests(IViewRequest ViewReqest){
        for(int i=head;i<tail;i++){
            ViewReqest.viewRequest(requestQueue[i]);
        }
    }

    public int getCount() {
        return count;
    }
}
