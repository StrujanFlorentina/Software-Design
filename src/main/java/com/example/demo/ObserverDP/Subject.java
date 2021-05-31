package com.example.demo.ObserverDP;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Subject {
    private List<Observer> observers = new ArrayList<Observer>();
    private State state;
 
 
    public void setStare(State state) {
       this.state = state;
       notifyAllObservers();
    }
 
    public void attach(Observer observer){
       observers.add(observer);		
    }
 
    public void notifyAllObservers(){
       for (Observer observer : observers) {
          observer.update();
       }
    }
}
