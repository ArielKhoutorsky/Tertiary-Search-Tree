// add your imports here

import static java.lang.Math.max;

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child

    // TODO: implement the node class here
    
    TSTNode(T element){
        this.element = element;

    }

    TSTNode<T> findMax(){
        if (this.right == null & this.mid == null){
            return (this);
        }
        else if ((this.right != null)) {
            return findMax(this.right);
        }
        else {
            return findMax(this.mid);
        }
    }
    TSTNode<T> findMax(TSTNode<T> child){
        if (child.right == null & child.mid == null){
            return (child);
        }
        else if ((child.right != null)) {
            return findMax(child.right);
        }
        else {
            return findMax(child.mid);
        }
    }


    TSTNode<T> findMin(){
        if (this.left == null){
            return (this);
        }
        else {
            return findMin(this.left);
        }

    }

    TSTNode<T> findMin(TSTNode<T> child){
        if (child.left == null){
            return (child);
        }
        else {
            return findMin(child.left);
        }

    }

    int height(){
        if (left == null & mid == null & right == null ){
            return 0;
        }
        else{
            int h = 0;

            if (!(this.left == null)){
                h = max(h,left.height());
            }
            if (!(this.mid == null)){
                h = max(h,mid.height());
            }
            if (!(this.right == null)){
                h = max(h,right.height());
            }
            return 1 + h;
        }
    }

    // add your own helper methods if necessary
}