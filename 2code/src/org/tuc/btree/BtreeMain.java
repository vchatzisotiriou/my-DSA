package org.tuc.btree;

public class BtreeMain {
    public static void main(String[] args) {
        BTree b = new BTree(3);
        b.insert(8);
        b.insert(9);
        b.insert(10);
        b.insert(11);
        b.insert(15);
        b.insert(20);
        b.insert(17);

        b.Show();
        System.out.println();

        System.out.println("Searching for 10: " + b.searchKey(10));
        System.out.println("Searching for 21: " + b.searchKey(21));

        b.delete(10);
        System.out.println();
        b.Show();
    }
}
