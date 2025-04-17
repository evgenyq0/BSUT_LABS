package org.example;

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