import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class TST<T extends Comparable<T>> implements Iterable<T> {
    // root node of the tree
    TSTNode<T> root;

    // constructor
    public TST() {
        this.root = null;
    }

    // TODO: implement the tree class here

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

    public void remove(T element) {

        if (!(this.contains(element))){
            return;
        }

        this.root = remove(this.root, element);

    }


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
     //           root.mid = remove(root.mid, key);
            } else if (!(root.right == null) && !(root.left == null) && root.mid == null) {
      //          root.element = root.left.findMax().element;
      //          root.left = remove(root.left, root.element);
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




    public boolean contains(T element) {
        if (contains(this.root, element) == null) {
            return false;
        } else {
            return true;
        }
    }

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

        /**
         * Caculate the height of the tree.
         * You need to implement the height() method in the TSTNode class.
         *
         * @return -1 if the tree is empty otherwise the height of the root node
         */
        public int height () {
            if (this.root == null)
                return -1;
            return this.root.height();
        }

        /**
         * Returns an iterator over elements of type {@code T}.
         *
         * @return an Iterator.
         */
        @Override
        public Iterator iterator () {

            return new TSTIterator(this);
        }

        // --------------------PROVIDED METHODS--------------------
        // The code below is provided to you as a simple way to visualize the tree
        // This string representation of the tree mimics the 'tree' command in unix
        // with the first child being the left child, the second being the middle child, and the last being the right child.
        // The left child is connect by ~~, the middle child by -- and the right child by __.
        // e.g. consider the following tree
        //               5
        //            /  |  \
        //         2     5    9
        //                   /
        //                  8
        // the tree will be printed as
        // 5
        // |~~ 2
        // |   |~~ null
        // |   |-- null
        // |   |__ null
        // |-- 5
        // |   |~~ null
        // |   |-- null
        // |   |__ null
        // |__ 9
        //     |~~ 8
        //     |   |~~ null
        //     |   |-- null
        //     |   |__ null
        //     |-- null
        //     |__ null
        @Override
        public String toString () {
            if (this.root == null)
                return "empty tree";
            // creates a buffer of 100 characters for the string representation
            StringBuilder buffer = new StringBuilder(100);
            // build the string
            stringfy(buffer, this.root, "", "");
            return buffer.toString();
        }

        /**
         * Build a string representation of the tertiary tree.
         * @param buffer String buffer
         * @param node Root node
         * @param nodePrefix The string prefix to add before the node's data (connection line from the parent)
         * @param childrenPrefix The string prefix for the children nodes (connection line to the children)
         */
        private void stringfy (StringBuilder buffer, TSTNode < T > node, String nodePrefix, String childrenPrefix){
            buffer.append(nodePrefix);
            buffer.append(node.element);
            buffer.append('\n');
            if (node.left != null)
                stringfy(buffer, node.left, childrenPrefix + "|~~ ", childrenPrefix + "|   ");
            else
                buffer.append(childrenPrefix + "|~~ null\n");
            if (node.mid != null)
                stringfy(buffer, node.mid, childrenPrefix + "|-- ", childrenPrefix + "|   ");
            else
                buffer.append(childrenPrefix + "|-- null\n");
            if (node.right != null)
                stringfy(buffer, node.right, childrenPrefix + "|__ ", childrenPrefix + "    ");
            else
                buffer.append(childrenPrefix + "|__ null\n");
        }

        /**
         * Print out the tree as a list using an enhanced for loop.
         * Since the Iterator performs an inorder traversal, the printed list will also be inorder.
         */
        public void inorderPrintAsList () {
            String buffer = "[";
            for (T element : this) {
                buffer += element + ", ";
            }
            int len = buffer.length();
            if (len > 1)
                buffer = buffer.substring(0, len - 2);
            buffer += "]";
            System.out.println(buffer);
        }
    }
