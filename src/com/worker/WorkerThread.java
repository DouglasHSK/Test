package com.worker;

import com.worker.Request;
public class WorkerThread extends Thread {
    private final Channel channel;
    private boolean run=true;
    
    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }
    
    public void run() {
        while (run) {
            IRequest request = channel.takeRequest();
              request.init();
            request.execute();
            request.finish();
        }
        System.out.println("Worker "+ this.getName()+ " is shutdown");
    }

	public boolean isRun() {
		return run;
	}

	public void setRun(boolean run) {
		this.run = run;
	}
	
	
}
