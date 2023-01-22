
package a3;

public class Main {

   public static void main(String[] args){
   /*
    * You will test your own bst implementation in here.
    * Do what you wish to in Main, as we will not be running it.
    * The grader uses its own Main and calls the methods of your other classes.
    *
    * In order to test you should create BST objects, put data into them, 
    * take data out, look for values stored in it, checking size, and looking 
    * at the Nodes to see if they are all linked up correctly as a BST.
    *
    * The Tester class we give you as a suggested way to organize your tests 
    * and allow you to systematically build your ADT method by method, 
    * testing as you go, adding new tests as you add new code.
    * 
    * You will be able to comment in and out various tests easily as you work
    * as I have done below.
    *
    */
      BST bst = new BSTImpl();
      Tester tst = new Tester();
      BST nbt = new BSTImpl();

      int[] vals = new int[]{17, 8, 2, 6, 37, 21, 39};
      for (int val: vals) {bst.insert(val);}

      int[] vals2 = new int[]{2, 1, 23, 24};
      for (int val: vals2) {nbt.insert(val);}

      Print.tree(bst);
      //Print.tree(nbt);
      //bst.merge(nbt);
      //Print.tree(bst);
      int diff = bst.getMaxLeafHeightDiff();
      System.out.println(diff);
      //tst.emptyTree(bst);
      //tst.insert(bst);
      //tst.remove(bst);
      //tst.merge(bst);

      //tst.inserty(bst, 5);
 
      // etc...
      
    }
}
