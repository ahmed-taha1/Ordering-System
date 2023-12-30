package OrderingSystem.Notification.Entities;

public class SMSNotification implements INotification{
    private final String senderPhoneNumber;
    private final String receiverPhoneNumber;
    private final String message;
    public SMSNotification(String senderPhoneNumber,String receiverPhoneNumber,String message){
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.message = message;
    }
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS message from ("+senderPhoneNumber+") to ("+receiverPhoneNumber +"): "+message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
