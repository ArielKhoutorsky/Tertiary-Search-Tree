import static java.lang.Math.max;

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child
    
    TSTNode(T element){
        this.element = element;
    }

    //finds node with the largest value
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

    //helper method
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

    //finds node with the smallest value
    TSTNode<T> findMin(){
        if (this.left == null){
            return (this);
        }
        else {
            return findMin(this.left);
        }
    }

    //helper method
    TSTNode<T> findMin(TSTNode<T> child){
        if (child.left == null){
            return (child);
        }
        else {
            return findMin(child.left);
        }
    }

    //finds height of the node in the tree
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
}