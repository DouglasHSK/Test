package com.worker;
public class Request implements IRequest {
    private final String name;
    private final int number;
    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }
    public void init(){
        System.out.println(Thread.currentThread().getName() + " Start " + this);
    }
    public void execute() {
        
        
        System.out.println(Thread.currentThread().getName() + " executes " + this);
        try{
            Thread.sleep(1000);
        }catch(Exception e){}
    }
    
    public void finish(){
        System.out.println(Thread.currentThread().getName() + " finish " + this);
    }
    
    public String toString() {
        return "[ Request from " + name + " No." + number + " ]";
    }
}
