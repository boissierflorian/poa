package drawing.observable;

public interface SelectionObservable {
    void addSelectionObserver(SelectionObserver obs);
    void removeSelectionObservers();
    void notifySelectionToObservers(int shapeCount);
}
