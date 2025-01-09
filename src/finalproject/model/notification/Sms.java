package finalproject.model.notification;

public class Sms implements Notification {
    @Override
    public void sendMessage(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}
