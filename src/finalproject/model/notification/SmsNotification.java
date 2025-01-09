package finalproject.model.notification;

public class SmsNotification implements Notification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}
