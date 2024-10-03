package core;

// FIXME
public interface Observer {

    // FIXME: update(TASkOcupadoDTO dto)
    // no puede ser un TASkOcupado dto, es abstraction leak
    public void update(NotificationDTO notificationDTO);

}
