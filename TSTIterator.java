import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//creates an iterator
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

    @Override
    public boolean hasNext() {
        if (currentIndex < list.size()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public T next() {
        return list.get(currentIndex++);
    }
}

