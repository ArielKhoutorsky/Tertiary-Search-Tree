import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class TSTIterator<T extends Comparable<T>> implements Iterator<T> {
    ArrayList<T> list;
    int currentIndex = 0;

    TSTIterator(TST<T> tree){

        this.list = new ArrayList<>();
        TSTNode<T> node = tree.root;
        build(node);
    }

    private void build(TSTNode<T> node) {

        if (node == null){
            return;
        }
        build(node.left);

        this.list.add(node.element);

        build(node.mid);

        build(node.right);
    }

    /**
     * Returns {@code true} if the iteration has more elements. (In other words, returns {@code true} if {@link #next}
     * would return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        if (currentIndex < list.size()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     *
     * @throws
     *         if the iteration has no more elements
     */
    @Override
    public T next() {
        return list.get(currentIndex++);
    }

}

