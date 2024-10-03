package core;

// FIXME
// X: este probablemente se vaya, pero depende de si usamos un DTO. en ese caso,
// Lautaro había dicho que se puede solucionar el tema de los dos métodos
public interface Notificator extends Observer {

    // FIXME: notify(TASkOcupadoDTO taskOcupadoDTO)
    public void notify(NotificationDTO notificationDTO);

}
