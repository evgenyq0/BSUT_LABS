package org.example;

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