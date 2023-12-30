package OrderingSystem.Notification.Entities;

public class EmailNotification implements INotification{
    private final String senderMail;
    private final String receiverMail;
    private final String message;
    public EmailNotification(String senderMail,String receiverMail,String message){
        this.senderMail = senderMail;
        this.receiverMail = receiverMail;
        this.message = message;
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS message from ("+senderMail+") to ("+receiverMail +"): "+message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
