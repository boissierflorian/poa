package drawing;

public interface SelectionObserver {
    void update(SelectionObservable obs, int shapeCount);
}
