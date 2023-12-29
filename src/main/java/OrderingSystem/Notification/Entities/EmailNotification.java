package OrderingSystem.Notification.Entities;

public class EmailNotification implements INotification{
    private final String senderMail;
    private final String receiverMail;
    public EmailNotification(String senderMail,String receiverMail){
        this.senderMail = senderMail;
        this.receiverMail = receiverMail;
    }

    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS message from ("+senderMail+") to ("+receiverMail +"): "+message);
    }
}
