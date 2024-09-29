package core;

public interface Notificator extends Observer {

    // FIXME: notify(TASkOcupadoDTO taskOcupadoDTO)
     public void notify(Task task, Member member, Object msg);

}
