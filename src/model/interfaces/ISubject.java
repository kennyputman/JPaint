package model.interfaces;

public interface ISubject {
    void registerObserver(IObserver shape);

    void clearObservers();

    void moveObservers(int xD, int yD);

    void removeObserver(IObserver shape);
}
