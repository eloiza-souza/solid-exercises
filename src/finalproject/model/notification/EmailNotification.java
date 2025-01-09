package finalproject.model.notification;

public class EmailNotification implements Notification{


    @Override
    public void sendNotification(String message) {
        System.out.println("Enviando email: " + message);
    }
}
