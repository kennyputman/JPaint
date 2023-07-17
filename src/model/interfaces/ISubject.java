package model.interfaces;

public interface ISubject {
     void registerObserver(IMoveObserver shape);
     void clearObservers();
     void moveObservers(int xD, int yD);
}
