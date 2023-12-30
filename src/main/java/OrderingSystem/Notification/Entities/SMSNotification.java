package OrderingSystem.Notification.Entities;

public class SMSNotification implements INotification{
    private final String senderPhoneNumber;
    private final String receiverPhoneNumber;
    private final TemplateMessage templateMessage;
    public SMSNotification(String senderPhoneNumber,String receiverPhoneNumber,TemplateMessage templateMessage){
        this.senderPhoneNumber = senderPhoneNumber;
        this.receiverPhoneNumber = receiverPhoneNumber;
        this.templateMessage = templateMessage;
    }
    @Override
    public void sendNotification() {
        System.out.println("Sending SMS message from ("+senderPhoneNumber+") to ("+receiverPhoneNumber +"): "+templateMessage.getMessage());
    }
    @Override
    public TemplateMessage getMessage() {
        return templateMessage;
    }
    @Override
    public String getReceiver(){
        return receiverPhoneNumber;
    }
}
