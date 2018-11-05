package drawing;

public interface SelectionObservable {
    void addObserver(SelectionObserver obs);
    void removeObservers();
    void notifyObservers(int shapeCount);
}
