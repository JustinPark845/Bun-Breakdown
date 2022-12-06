package Components;

public interface Iterator<T> {
    boolean hasNext();
	T next();
    void goPrevious(int i);
}
