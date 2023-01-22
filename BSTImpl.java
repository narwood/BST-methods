
package a3;
import java.util.LinkedList;
public class BSTImpl implements BST {

    private Node root;
    private int size;

    public BSTImpl() { root=null; size=0; }

    public BSTImpl(int val) { this.root=new NodeImpl(val); size=1; }

    // The implementations given to you are intended to help you 
    // see how the linked cells work, and how to program with them.
    //
    // These methods are patterns to illustrate for you how to set up
    // the method implementations as recursion.
    //
    // You should follow this pattern for implementing the other recursive methods
    // by adding your own private recursive helper methods.

    @Override
    // interface method ==================================================
    public int height() {       
      // It is not recursive itself, but it calls a recursive
      // private "helper" method and passes it access to the tree cells.
      return height_recursive(this.root);
    }
    // private recursive helper
    private int height_recursive(Node c) {
      // private inner "helper" method with different signature
      // this helper method uses recursion to traverse
      // and process the recursive structure of the tree of cells
      if (c==null) return -1;
      int lht = height_recursive(c.getLeft());
      int rht = height_recursive(c.getRight());
      return Math.max(lht,rht) + 1;
    }
    
    @Override
    // interface method ==================================================
    public boolean contains(int val) { 
      if(this.root==null) return false; 
      return contains_r(val,root); 
    }
    // private recursive helper
    private boolean contains_r(int val, Node c) {
      if(c == null) return false;
      if(c.getValue()==val) return true;
      return contains_r(val, c.getLeft()) || contains_r(val, c.getRight());
    }

    @Override
    // interface method, used by autograder, do not change
    public Node getRoot() { return this.root; }
    
    @Override
    // interface method ==================================================
    public int size() { return this.size; }

    
    @Override
    // interface method ===================================================
    public void remove(int value) { remove_r(value,this.root); }
    // private recursive helper
    private Node remove_r(int k, Node c) {
      if (c==null) return null; // item not found, nothing to do
      // now we know we have a real node to examine
      //int cflag = k.compareTo(c.getValue());
      int vc=c.getValue();
      if (k<vc) { // k is smaller than node
        c.setLeft(remove_r(k,c.getLeft()));
        return c;
      }
      else if (k>vc) { // k is larger than node
        c.setRight(remove_r(k,c.getRight()));
        return c;
      }
      else { // k==vc   // found it... now figure out how to rearrange
        // cases
        if (c.getLeft()==null && c.getRight()==null) { c = null; this.size--; } // leaf
        else if (c.getLeft()==null && c.getRight()!=null) { c = c.getRight(); this.size--; } // RC only
        else if (c.getLeft()!=null && c.getRight()==null) { c = c.getLeft(); this.size--; } // LC only
        else { // 2 children
          Node mc = findMaxCell(c.getLeft());
          c.setValue(mc.getValue());
          c.setLeft(remove_r(c.getValue(), c.getLeft()));   // recurse
        }
        return c;
      }
    }
    // private recursive helper
    private Node findMaxCell(Node c) { 
      if (c.getRight()==null) return c;
      return findMaxCell(c.getRight());
    } 

  
   //====================================================================================
   //
   // The methods below here are part of the public BST interface we defined, 
   // but you will write the implementation code.
   // 
   // At the moment, they have return statements that return dummy values; this
   // is so they will compile, but those return values are just dummy behavior
   // you will replace the dummy returns with your own code and proper return values.
   //
   //====================================================================================

  
    @Override
    // interface method ==================================================
    public int insert(int val) {
        /*See BST.java for method specification */
        /*Your code here */
        /* Hint: Don't forget to update size */
        /* Hint: You can find examples of how to create a new Node object elsewhere in the code */
        if (this.root==null) {
            this.root = new NodeImpl(val);
            this.size++;
            return val;
        }
        return insert_r(val, this.getRoot());
    }

    private int insert_r(int k, Node c) {
        Node nuevo = new NodeImpl(k);
        if (c.getValue()==k) return k; //check if current node is duplicate

        if (k < c.getValue()) {
            if (c.getLeft() == null) {
                c.setLeft(nuevo);
                this.size++;
                return k;
            }
            return insert_r(k, c.getLeft()); //left recursive step
        }
        if (k > c.getValue()) {
            if (c.getRight() == null) {
                c.setRight(nuevo);
                this.size++;
                return k;
            }
            return insert_r(k, c.getRight()); //right recursive step
        } //base case null right
        return 0; //broke check
        }

    @Override
    // interface method ==================================================
    public int findMin() {
        if (this.size==0) {return 0;}
        if (this.size==1) {return this.root.getValue();}
        return findMin_r(this.root);
    }

    private int findMin_r(Node c) {
        if (c.getLeft()==null) {return c.getValue();}
        return findMin_r(c.getLeft());
    }
    @Override
    // interface method ==================================================
    public int findMax() {
        if (this.root==null) return 0;
        return findMax_r(this.root);
    }
    private int findMax_r(Node c) {
        if (c.getRight()==null) {return c.getValue();}
        return findMax_r(c.getRight());
    }
    
    @Override
    // interface method ==================================================
    public Node get(int val) {
      return get_r(val, this.root);
    }
    private Node get_r(int k, Node c) {
       if (c.getValue()==k) return c;
       if (c.getLeft()==null && c.getRight()==null) {return null;}
       if (k<c.getValue() && !(c.getLeft()==null)) {
           return get_r(k, c.getLeft());}
       if (k>c.getValue() && !(c.getRight()==null)) {
           return get_r(k, c.getRight());}
       return null;
    }
    @Override
    // interface method ==================================================
    public boolean isFullBT() {
        if (this.root==null) {return true;}
        return isFullTree_r(this.root);
    }
    private boolean isFullTree_r(Node c) {
        if ((c.getLeft()==null) == !(c.getRight()==null)) {
            return false;
        }
        if (c.getLeft()==null) {return true;}
        return (isFullTree_r(c.getLeft()) && isFullTree_r(c.getRight()));
    }
    @Override
    // interface method ==================================================
    public int merge(BST nbt) {
        merge_r(this, nbt.getRoot());
        return merge_r(this, nbt.getRoot());
    }

    private int merge_r(BST obt, Node c) {
        int counter = 0;
        if (c == null) {return counter;}
        else {
            obt.insert(c.getValue());
            counter++;
        }
        merge_r(obt, c.getLeft());
        merge_r(obt, c.getRight());
        return (counter + merge_r(obt, c.getLeft()) + merge_r(obt, c.getRight()));
    }

    public int getMaxLeafHeightDiff () {
        if (this.getRoot() == null) {return 0;}
        int h = height_recursive(this.getRoot());
        int l = minHeight_r(this.getRoot());
        System.out.println(minHeight_r(this.getRoot()));
        return h-l;

    }

    private int minHeight_r(Node c) {
        if (c==null) {return 0;}
        if (c.getRight()==null && c.getLeft()==null) {return 0;}
        int rht = 0;
        int lht = 0;
        boolean rightNull = true;
        boolean leftNull = true;
        if (c.getRight()!= null) {
            rht = minHeight_r(c.getRight());
            rightNull = false;
        }
        if (c.getLeft()!= null) {
            lht = minHeight_r(c.getLeft());
            leftNull = false;
        }
        if (!rightNull && !leftNull) {return (Math.min(rht,lht) + 1);}
        if (rightNull && !leftNull) {return lht + 1;}
        return rht + 1;
    }
}
