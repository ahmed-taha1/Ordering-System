package OrderingSystem.Notification.Entities;

public class EmailNotification implements INotification{
    private final String senderMail;
    private final String receiverMail;
    private final TemplateMessage templateMessage;
    public EmailNotification(String senderMail,String receiverMail,TemplateMessage templateMessage){
        this.senderMail = senderMail;
        this.receiverMail = receiverMail;
        this.templateMessage = templateMessage;
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending SMS message from ("+senderMail+") to ("+receiverMail +"): "+templateMessage.getMessage());
    }

    @Override
    public TemplateMessage getMessage() {
        return templateMessage;
    }
    @Override
    public String getReceiver(){
        return receiverMail;
    }
}
