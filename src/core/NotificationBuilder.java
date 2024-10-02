package core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// FIXME
public class NotificationBuilder {

    public static NotificationDTO createNotificationDTO(Task task, Member member) {
        String timeStampOfNotification = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm'hs'"));
        String notification = "(" + timeStampOfNotification + ")  Task: [" + task.getDescription() + "]  →  Member: [" + member.getName() + "]\n";

        return new NotificationDTO(task, member, notification);
    }

}
