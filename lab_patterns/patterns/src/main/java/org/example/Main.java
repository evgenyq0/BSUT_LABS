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

// Класс системы оповещения о дорожных происшествиях
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

// Класс поста ГАИ, который получает оповещения
class TrafficPost implements Observer {
    private String postID;

    public TrafficPost(String postID) {
        this.postID = postID;
    }

    @Override
    public void update(String message) {
        System.out.println("Пост ГАИ " + postID + " получил сообщение: " + message);
        // Здесь может быть логика обработки сообщения
    }
}

// Класс для работы с внешними системами уведомлений
class ExternalNotificationService {
    public void sendToExternalSources(String message) {
        System.out.println("Отправка сообщения во внешние системы: " + message);
        // Реализация отправки в внешние системы (СМС, email и т.д.)
    }
}

// Класс для представления дорожного происшествия
class Arrival {
    private String type;
    private String content;

    public Arrival(String type, String content) {
        this.type = type;
        this.content = content;
    }

    public void generateAlert() {
        String alertMessage = "Происшествие: " + type + ". Детали: " + content;
        // Здесь можно добавить логику создания оповещения
        System.out.println(alertMessage);

        // Пример отправки оповещения
        TrafficAlertSystem alertSystem = new TrafficAlertSystem();
        alertSystem.attach(new TrafficPost("POST-001"));
        alertSystem.sendAlert(alertMessage);

        // Отправка во внешние системы
        ExternalNotificationService externalService = new ExternalNotificationService();
        externalService.sendToExternalSources(alertMessage);
    }
}

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