package OrderingSystem.Notification.Entities;

public class TemplateMessage {
    private final String message;
    private final TemplateType templateType;
    public TemplateMessage(String message,TemplateType templateType) {
        this.message = message;
        this.templateType = templateType;
    }
    public TemplateType getTemplateType(){
        return templateType;
    }
    public String getMessage() {
        return message;
    }
}
