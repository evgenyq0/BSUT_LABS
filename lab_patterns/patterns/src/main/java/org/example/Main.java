package org.example;

import java.util.ArrayList;
import java.util.List;

// Интерфейс Subject (Наблюдаемый объект)
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Интерфейс Observer (Наблюдатель)
interface Observer {
    void update(String message);
}




// Класс для представления дорожного происшествия


// Пример использования
public class Main {
    public static void main(String[] args) {
        // Создаем систему оповещения
        TrafficAlertSystem alertSystem = new TrafficAlertSystem();

        // Регистрируем посты ГАИ
        alertSystem.attach(new TrafficPost("POST-001"));
        alertSystem.attach(new TrafficPost("POST-002"));

        // Создаем происшествие
        Arrival accident = new Arrival("Авария", "Столкновение двух автомобилей на перекрестке");
        accident.generateAlert();

        // Общее оповещение
        alertSystem.notifyObservers();
    }
}