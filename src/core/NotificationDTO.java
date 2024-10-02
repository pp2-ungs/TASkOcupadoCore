package core;

// FIXME
public class NotificationDTO {
    
    private Task task;
    private Member member;
    private String message;

    public NotificationDTO(Task task, Member member, String message) {
        this.task = task;
        this.member = member;
        this.message = message;
    }

    public Task getTask() {
        return task;
    }

    public Member getMember() {
        return member;
    }

    public String getMessage() {
        return message;
    }
    
}
