package core;

public interface Notificator extends Observer {

    public void notify(Task task, Member member, Object msg);

}
