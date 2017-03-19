package src.main;

public interface Subject 
{
    public void registerObserver( Observer obs);
    public void removeObserver( Observer obs);
    public void notifyObservers() throws InterruptedException;
}