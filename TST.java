import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TST<T extends Comparable<T>> implements Iterable<T> {
    // root node of the tree
    TSTNode<T> root;

    public TST() {
        this.root = null;
    }

    //inserts an element
    public void insert(T element) {
        if (root == null) {
            root = new TSTNode<T>(element);
        } else if (element.compareTo(root.element) < 0) {
            root.left = insert(root.left, element);

        } else if (element.compareTo(root.element) == 0) {
            root.mid = insert(root.mid, element);

        } else if (element.compareTo(root.element) > 0) {
            root.right = insert(root.right, element);

        }


    }

    //helper method
    public TSTNode<T> insert(TSTNode<T> root, T element) {
        if (root == null) {
            root = new TSTNode<T>(element);
        } else if (element.compareTo(root.element) < 0) {
            root.left = insert(root.left, element);

        } else if (element.compareTo(root.element) == 0) {
            root.mid = insert(root.mid, element);

        } else if (element.compareTo(root.element) > 0) {
            root.right = insert(root.right, element);

        }
        return root;
    }

    //removes an element
    public void remove(T element) {

        if (!(this.contains(element))){
            return;
        }
        this.root = remove(this.root, element);
    }


    //helper method
    public TSTNode<T> remove (TSTNode<T> root,T key) {
        if (root == null) {
            return null;
        } else if (key.compareTo(root.element) < 0) {
            root.left = remove(root.left, key);
        } else if (key.compareTo(root.element) > 0) {
            root.right = remove(root.right, key);
        } else {

            if (root.left == null && root.mid == null) {
                root = root.right;
            } else if (root.right == null && root.mid == null) {
                root = root.left;
            } else if (root.right == null && root.left == null) {
                root = root.mid;
            } else if (!(root.right == null) && !(root.left == null) && root.mid == null) {
                TSTNode<T> leftMax = root.left.findMax();
                int i = 0;

                while (this.contains(leftMax.element)){
                    remove(leftMax.element);
                    i ++;
                }
                root.element = leftMax.element;
                root.left = remove(root.left,root.element);

                for (int j = 0; j < i - 1; j++){
                    this.insert(leftMax.element);
                }
            }
            else {

                root.mid = remove(root.mid, key);
            }
        }
        return root;
    }

    //checks if the tree contains the element
    public boolean contains(T element) {
        if (contains(this.root, element) == null) {
            return false;
        } else {
            return true;
        }
    }

    //helper method
    public TSTNode<T> contains(TSTNode<T> root, T element) {
        if (root == null) {
            return null;
        } else if (element.compareTo(root.element) == 0) {
            return root;
        } else if (element.compareTo(root.element) < 0) {
            return contains(root.left, element);
        } else {
            return contains(root.right, element);

        }
    }

    //rebalances the tree in order to minimize its height
    public void rebalance () {
        ArrayList<T> list = inOrderTraversal(this.root, new ArrayList<>());
        System.out.println(list);
        ArrayList<T> uniqueElementList = inOrderTraversal(this.root, new ArrayList<>());
        for (int i = 0; i < uniqueElementList.size() - 1; i++){
            if (uniqueElementList.get(i) == uniqueElementList.get(i+1)){
                uniqueElementList.remove(i+1);
                }
            }

        this.root = null;

        ArrayList<T> tempList = new ArrayList<>();

        for (int i = 0; i < list.size() - 1 ; i++){
            if (list.get(i) == list.get(i+1)){
                tempList.add(list.get(i+1));
            }
        }
    // tempList contains all the repeated elements
    // uniqueElementList contains all the unique elements
        System.out.println(tempList);
        System.out.println(uniqueElementList);
        T midElement = list.get((list.size()) / 2);
        listToTST(uniqueElementList, 0 , uniqueElementList.size() - 1,  midElement ) ;

        for (int i = 0; i <= tempList.size() - 1; i ++){
            this.insert(tempList.get(i));
        }
    }

    //traversal of the tree in order of elements
    public ArrayList<T> inOrderTraversal(TSTNode<T> root , ArrayList<T> list){
        if (root == null){
            return null;
        }

        inOrderTraversal(root.left , list);
        list.add(root.element);

        inOrderTraversal(root.mid , list);
        inOrderTraversal(root.right , list);
        return list;
        }

    //creates a TST from a list
    public void listToTST(ArrayList<T> list, int start, int end, T midEl){

        if (start > end){
            return;
        }
        int mid = 0;
        
        if (start == 0 && end == list.size() -1){
             for (int i=0 ; i < list.size(); i++){
                 if (list.get(i) == midEl){
                     mid = i;
                 }
             }
        }
        else{
             mid = (end - start)/2 + start;
        }

        this.insert(list.get(mid));

        listToTST(list,start,mid - 1,midEl);
        listToTST(list,mid + 1,end,midEl);
    }

        //calculates height of the tree
        public int height () {
            if (this.root == null)
                return -1;
            return this.root.height();
        }

        //creates an iterator
        @Override
        public Iterator iterator () {

            return new TSTIterator(this);
        }
    }
