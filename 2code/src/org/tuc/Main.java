package org.tuc;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.tuc.avl.AVLTree;
import org.tuc.bst.BSTree;
import org.tuc.btree.BTree;
import org.tuc.linearhashing.LinearHashing;

import utils.Globals;
import utils.MultiCounter;

public class Main {



    public int[] readInts(String fileName) {
		int[] numbers = null;
		try {
            // Create FileInputStream and DataInputStream
            FileInputStream fis = new FileInputStream(fileName);
            DataInputStream dis = new DataInputStream(fis);
            
            // Create byte array with enough space for the whole file
            // and read the whole file. We assume that the file has the correct
            // length (multiple of Integer.BYTES=4)
            byte[] bytes = new byte[(int) new File(fileName).length()];
            dis.readFully(bytes);

            // Create ByteBuffer with little-endian byte order
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            
            // Read integers from the ByteBuffer
            numbers = new int[bytes.length / Integer.BYTES];
            for (int i = 0; i < numbers.length; i++) {
            	numbers[i] = buffer.getInt();
            }

            // Close streams
            dis.close();
            fis.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
		return numbers;
	}

    
    
    
    public void main(String[] args) throws Exception {
        
        double insertAverageTimes[][] = new double[6][19];
        double searchAverageTimes[][] = new double[6][19];
        

        
        double searchAverageOperations[][] = new double[6][19];
        

        String F[] = {  
                        "numbers-20.bin" ,"numbers-50.bin","numbers-100.bin","numbers-200.bin","numbers-1000.bin","numbers-2500.bin","numbers-5000.bin",
        "numbers-10000.bin","numbers-20000.bin","numbers-40000.bin","numbers-60000.bin","numbers-80000.bin","numbers-100000.bin","numbers-200000.bin","numbers-1000000.bin",
        "numbers-1500000.bin","numbers-2000000.bin","numbers-2500000.bin","numbers-3000000.bin"};
        int N[] = {20, 50, 100, 200, 1000, 2500, 5000, 10000, 20000, 40000, 60000, 80000, 100000, 200000, 1000000, 1500000, 2000000, 2500000, 3000000};
        
             

        
            int numberOfNumbers;
            int minIntNumber;
            int maxIntNumber;

            double avlTreeInsertTimeAverages[] =  new double[19];
            double bsTreeInsertTimeAverages[] =  new double[19];
            double bTree100InsertTimeAverages[] =  new double[19];
            double bTree600InsertTimeAverages[] =  new double[19];
            double linearHashing40InsertTimeAverages[] =  new double[19];
            double linearHashing1000InsertTimeAverages[] =  new double[19];

            double avlTreeSearchimeAverages[] =  new double[19];
            double bsTreeSearchTimeAverages[] =  new double[19];
            double bTree100SearchTimeAverages[] =  new double[19];
            double bTree600SearchTimeAverages[] =  new double[19];
            double linearHashing40SearchTimeAverages[] =  new double[19];
            double linearHashing1000SearchTimeAverages[] =  new double[19];

            



           

            double avlTreeSearchOperationAverages[] =  new double[19];
            double bsTreeSearchOperationAverages[] =  new double[19];
            double bTree100SearchOperationAverages[] =  new double[19];
            double btree600SearchOperationAverages[] =  new double[19];
            double linearHashing40SearchOperationAverages[] =  new double[19];
            double linearHashing1000SearchOperationAverages[] =  new double[19];

            





            for (int i = 0; i < 19; i++) {
                numberOfNumbers = N[i];
                minIntNumber = 1;
                maxIntNumber = 3 * numberOfNumbers;
                int k = 0;



                
                
                AVLTree avltree = new AVLTree();
                BSTree bstree = new BSTree();
                BTree btree100 = new BTree(100);
                BTree btree600 = new BTree(600);
                LinearHashing linearHashing40 = new LinearHashing(40,500);
                LinearHashing linearHashing1000 = new LinearHashing(1000,500);

                
                int[] intArray = null;
                intArray = readInts("C:\\Users\\kosta\\Downloads\\src\\src\\bin\\" + F[i]);


                for (int m = 0;m < numberOfNumbers; m++){
                   
                    avltree.insert(intArray[m]);
                    bstree.insert(intArray[m]);
                    btree100.insert(intArray[m]);
                    btree600.insert(intArray[m]);
                    linearHashing40.insert(intArray[m]);
                    linearHashing1000.insert(intArray[m]);
                }
                if(N[i] < 201){
                    k = 10;
                }
                if(N[i] > 201 && N[i] < 1001){
                    k = 50;
                }
                if(N[i] > 1001){
                    k = 100;
                }
                

                    double insertAVLTreeTimeTotal = 0;
                    double insertBSTreeTimeTotal = 0;
                    double insertBTree100TimeTotal = 0;
                    double insertBTree600TimeTotal = 0; 
                    double insertLinearHashing40TimeTotal = 0;
                    double insertLinearHashing1000TimeTotal = 0;

                

                    double searchAVLTreeTimeTotal = 0;
                    double searchBSTreeTimeTotal = 0;
                    double searchBTree100TimeTotal = 0;
                    double searchBTree600TimeTotal = 0; 
                    double searchLinearHashing40TimeTotal = 0;
                    double searchLinearHashing1000TimeTotal = 0;



                   

                    double searchAVLTreeOperationTotal = 0;
                    double searchBSTreeOperationTotal = 0;
                    double searchBTree100OperationTotal = 0;
                    double searchBTree600OperationTotal = 0; 
                    double searchLinearHashing40OperationTotal = 0;
                    double searchLinearHashing1000OperationTotal = 0;



                    java.util.Random randomGenerator = new java.util.Random();
                    int[] randomInts = randomGenerator.ints(minIntNumber, maxIntNumber+1).distinct().limit(numberOfNumbers).toArray();

                    

                    for (int m = 0;m < k ; m++){
                        
                        


                        double time1 = System.nanoTime();
                        avltree.insert(randomInts[m]);
                        double time2 = System.nanoTime();

                        double insertAVLTreeTime = time2 - time1;

                        insertAVLTreeTimeTotal += insertAVLTreeTime;
                        MultiCounter.resetCounter(1);
                        


                        time1 = System.nanoTime();
                        bstree.insert(randomInts[m]);
                        time2 = System.nanoTime();

                        double insertBSTreeTime = time2 - time1;

                        insertBSTreeTimeTotal += insertBSTreeTime;
                        MultiCounter.resetCounter(2);
                        




                        time1 = System.nanoTime();
                        btree100.insert(randomInts[m]);
                        time2 = System.nanoTime();

                        double insertBTree100Time = time2 - time1;

                        insertBTree100TimeTotal += insertBTree100Time;
                        MultiCounter.resetCounter(3);
                        




                        time1 = System.nanoTime();
                        btree600.insert(randomInts[m]);
                        time2 = System.nanoTime();

                        double insertBTree600Time = time2 - time1;

                        insertBTree600TimeTotal += insertBTree600Time;
                        MultiCounter.resetCounter(4);
                       



                        time1 = System.nanoTime();
                        linearHashing40.insert(randomInts[m]);
                        time2 = System.nanoTime();

                        double insertLinearHashing40Time = time2 - time1;

                        insertLinearHashing40TimeTotal += insertLinearHashing40Time;
                        MultiCounter.resetCounter(5);
                        




                        time1 = System.nanoTime();
                        linearHashing1000.insert(randomInts[m]);
                        time2 = System.nanoTime();

                        double insertLinearHashing1000Time = time2 - time1;

                        insertLinearHashing1000TimeTotal += insertLinearHashing1000Time;
                        MultiCounter.resetCounter(6);
                        
                    }











                    
                    for (int m = 0;m < k; m++){

                        double time1;
                        double time2;
                        time1 = System.nanoTime();
                        avltree.searchKey(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchAVLTreeTime = time2 - time1;

                        searchAVLTreeTimeTotal += searchAVLTreeTime;
                        searchAVLTreeOperationTotal += MultiCounter.getCount(1);
                        MultiCounter.resetCounter(1);


                        time1 = System.nanoTime();
                        bstree.searchKey(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchBSTreeTime = time2 - time1;

                        searchBSTreeTimeTotal += searchBSTreeTime;
                        searchBSTreeOperationTotal += MultiCounter.getCount(2);
                        MultiCounter.resetCounter(2);




                        time1 = System.nanoTime();
                        btree100.searchKey(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchBTree100Time = time2 - time1;

                        searchBTree100TimeTotal += searchBTree100Time;
                        searchBTree100OperationTotal += MultiCounter.getCount(3);
                        MultiCounter.resetCounter(3);





                        time1 = System.nanoTime();
                        btree600.searchKey(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchBTree600Time = time2 - time1;

                        searchBTree600TimeTotal += searchBTree600Time;
                        searchBTree600OperationTotal += MultiCounter.getCount(4);
                        MultiCounter.resetCounter(4);




                        time1 = System.nanoTime();
                        linearHashing40.search(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchLinearHashing40Time = time2 - time1;

                        searchLinearHashing40TimeTotal += searchLinearHashing40Time;
                        searchLinearHashing40OperationTotal += MultiCounter.getCount(5);
                        MultiCounter.resetCounter(5);





                        time1 = System.nanoTime();
                        linearHashing1000.search(randomInts[m]);
                        time2 = System.nanoTime();

                        double searchLinearHashing1000Time = time2 - time1;

                        searchLinearHashing1000TimeTotal += searchLinearHashing1000Time;
                        searchLinearHashing1000OperationTotal += MultiCounter.getCount(6);
                        MultiCounter.resetCounter(6);


                    }


















                    



                    
                    avlTreeInsertTimeAverages[i] = insertAVLTreeTimeTotal / k;
                    bsTreeInsertTimeAverages[i] = insertBSTreeTimeTotal / k;
                    bTree100InsertTimeAverages[i] = insertBTree100TimeTotal / k;
                    bTree600InsertTimeAverages[i] = insertBTree600TimeTotal / k;
                    linearHashing40InsertTimeAverages[i] = insertLinearHashing40TimeTotal / k;
                    linearHashing1000InsertTimeAverages[i] = insertLinearHashing1000TimeTotal / k;

                    avlTreeSearchimeAverages[i] = searchAVLTreeTimeTotal / k;
                    bsTreeSearchTimeAverages[i] = searchBSTreeTimeTotal / k;
                    bTree100SearchTimeAverages[i] = searchBTree100TimeTotal / k;
                    bTree600SearchTimeAverages[i] = searchBTree600TimeTotal / k;
                    linearHashing40SearchTimeAverages[i] = searchLinearHashing40TimeTotal / k;
                    linearHashing1000SearchTimeAverages[i] = searchLinearHashing1000TimeTotal / k;    
                
                    insertAverageTimes[0][i] = avlTreeInsertTimeAverages[i];
                    insertAverageTimes[1][i] = bsTreeInsertTimeAverages[i];
                    insertAverageTimes[2][i] = bTree100InsertTimeAverages[i];
                    insertAverageTimes[3][i] = bTree600InsertTimeAverages[i];
                    insertAverageTimes[4][i] = linearHashing40InsertTimeAverages[i];
                    insertAverageTimes[5][i] = linearHashing1000InsertTimeAverages[i];

                    searchAverageTimes[0][i] = avlTreeSearchimeAverages[i];
                    searchAverageTimes[1][i] = bsTreeSearchTimeAverages[i];
                    searchAverageTimes[2][i] = bTree100SearchTimeAverages[i];
                    searchAverageTimes[3][i] = bTree600SearchTimeAverages[i];
                    searchAverageTimes[4][i] = linearHashing40SearchTimeAverages[i];
                    searchAverageTimes[5][i] = linearHashing1000SearchTimeAverages[i];

                   


                    

                    avlTreeSearchOperationAverages[i] = searchAVLTreeOperationTotal / k;
                    bsTreeSearchOperationAverages[i] = searchBSTreeOperationTotal / k;
                    bTree100SearchOperationAverages[i] = searchBTree100OperationTotal / k;
                    btree600SearchOperationAverages[i] = searchBTree600OperationTotal / k;
                    linearHashing40SearchOperationAverages[i] = searchLinearHashing40OperationTotal / k;
                    linearHashing1000SearchOperationAverages[i] = searchLinearHashing1000OperationTotal / k; 
                
                    searchAverageOperations[0][i] = avlTreeSearchOperationAverages[i];
                    searchAverageOperations[1][i] = bsTreeSearchOperationAverages[i];
                    searchAverageOperations[2][i] = bTree100SearchOperationAverages[i];
                    searchAverageOperations[3][i] = btree600SearchOperationAverages[i];
                    searchAverageOperations[4][i] = linearHashing40SearchOperationAverages[i];
                    searchAverageOperations[5][i] = linearHashing1000SearchOperationAverages[i];

                


                    
            }
        System.out.println(Globals.insertTimeHeader);
        printOut(insertAverageTimes);
        
        System.out.println(Globals.searchTimeHeader);
        printOut(searchAverageTimes);
       
        System.out.println(Globals.searchOperationHeader);
        printOut(searchAverageOperations);
    
    }



    public static void printOut(double array[][]){
        String listNames[] = {"AVLTree","BSTree","BTree100","BTree600","Linear40","Linear1000"};

        
        System.out.println(
                    String.format(" %-11s | %-11s | %-11s | %-11s | %-11s | %-11s",
                                    String.format( listNames[0]),
                                    String.format( listNames[1]),
                                    String.format( listNames[2]),
                                    String.format( listNames[3]),
                                    String.format( listNames[4]),
                                    String.format( listNames[5])
                                    ));
        
        
        for (int i = 0; i < 19; i++) {
        
        
             
                
            
                System.out.println(
                    String.format(" %-11s | %-11s | %-11s | %-11s | %-11s | %-11s",
                                            
                                            String.format("%.2f", array[0][i]),
                                            String.format("%.2f", array[1][i]),
                                            String.format("%.2f", array[2][i]),
                                            String.format("%.2f", array[3][i]),
                                            String.format("%.2f", array[4][i]),
                                            String.format("%.2f", array[5][i])
                                            ));
            
        }
    }

    
}
