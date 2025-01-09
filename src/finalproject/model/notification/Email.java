package finalproject.model.notification;

public class Email implements Notification{


    @Override
    public void sendMessage(String message) {
        System.out.println("Enviando email: " + message);
    }
}
