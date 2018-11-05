package drawing.observable;

public interface Observable {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers(Observable obs, int shapeCount);
}