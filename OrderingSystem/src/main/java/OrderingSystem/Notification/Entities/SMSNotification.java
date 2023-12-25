package OrderingSystem.Notification.Entities;

public class SMSNotification implements INotification{
    private final String senderPhoneNumber;
    private final String receiverPhoneNumber;
    public SMSNotification(String senderPhoneNumber,String receiverPhoneNumber){
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
    }
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS message from ("+senderPhoneNumber+") to ("+receiverPhoneNumber +"): "+message);
    }
}
