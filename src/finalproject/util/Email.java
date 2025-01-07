package finalproject.util;

public class Email implements Notification{


    @Override
    public void sendMessage(String message) {
        System.out.println("Enviando email ...");
    }
}
