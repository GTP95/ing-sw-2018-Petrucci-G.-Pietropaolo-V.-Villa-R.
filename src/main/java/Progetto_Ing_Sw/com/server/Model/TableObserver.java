package Progetto_Ing_Sw.com.server.Model;

public interface TableObserver {
    Table subject=Table.getOurInstance();

    public void NotifyTableUpdate();
}
