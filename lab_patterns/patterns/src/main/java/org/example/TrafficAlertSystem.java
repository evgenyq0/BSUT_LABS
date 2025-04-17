package org.example;

import java.util.ArrayList;
import java.util.List;

class TrafficAlertSystem implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Общее оповещение от ГАИ");
        }
    }

    public void sendAlert(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}