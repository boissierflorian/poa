package drawing.observable;

public interface SelectionObserver {
    void update(SelectionObservable obs, int shapeCount);
}
