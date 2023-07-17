package model.interfaces;

public interface IPublisher {

     void subscribe(IMoveable shape);
     void unsubscribe(IMoveable shape);
     void clearSubscribers();

     void moveSubscribers(int xD, int yD);
}
