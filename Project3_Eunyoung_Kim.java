import java.util.*;

/**
   Project 3: INT
   Name: Eunyoung Kim
   Class: MATH1165-001
   Input: user chooses integer n, which is the number of coins
          ex) Please enter integer: type n
   Output: integer p(n), the number of ways that the coins can be separated into piles
*/
public class Project3_Eunyoung_Kim{
   //Declare variables
   static int[] intPartition;
   static int userInput = 0;

   public static void main(String[] args){
      
      //Create a Scanner object to read input data
      Scanner input = new Scanner(System.in);
      
      //Get user's input
      System.out.print("Please enter integer: ");
      userInput = input.nextInt();
      
      intPartition = new int[userInput];
      
      for(int i = 0; i < userInput; i++){
         intPartition[i] = 0;
      }
      
      //Assign user input value in the first room of array
      intPartition[0] = userInput;
      
      System.out.println("* User input: " + userInput);
      //showArray();
      int arrayNumber[] = new int[2];
      int notZero, notOne, quot, rmdr, temp, temp2;
      int count = 1;
      
      if(userInput > 1){
         //Keeps doing it until the first room is 1
         while(intPartition[0] != 1){
            //Getting the room number of the last room that is not zero(which is 1)
            notZero = getLastRoom();
            //Getting the room number of the last room that is over one
            notOne = getRoomOverOne(notZero);
            
            //If the last room that has number isn't 1
            if(notZero == notOne){
               intPartition[notZero] = intPartition[notZero] - 1;
               intPartition[notZero+1] = intPartition[notZero+1] + 1;
               count = count + 1;
               //showArray();
            }else if(notZero != notOne){
               temp = intPartition[notOne] - 1;
               temp2 = 0;
               
               for(int i = 0; i < notOne; i++){
                  temp2 = temp2 + intPartition[i];
               }
               temp2 = userInput - temp2;
               
               if(temp2 - temp > temp){
                  quot = temp2 / temp;
                  rmdr = temp2 % temp;
                  for(int j = 0; j < quot; j++){
                     intPartition[j + notOne] = temp;
                  }
                  for(int j = quot; j < temp2; j++){
                     intPartition[j + notOne] = 0;
                  }
                  if(rmdr != 0){
                     intPartition[quot + notOne] = rmdr;
                  }
               }else{
                  intPartition[notOne] = temp;
                  intPartition[notOne+1] = temp2 - intPartition[notOne];
                  for(int j = notOne+2; j < userInput; j++){
                     intPartition[j] = 0;
                  }
               }
               //showArray();
               count = count + 1;
            }
         }
      }
      
      System.out.println("* The number of integer partitions: " + count);
   }
   
   /**
      The getRoomOverOne method gets room number of the last room that has 1.
   */
   public static int getLastRoom(){
      int rn = 0;
      for(int i = 0; i < userInput; i++){
         if(intPartition[userInput-1-i] != 0){
            rn = userInput-1-i;
            break;
         }
      }
      return rn;
   }
   
   /**
      The getRoomOverOne method gets room number of the last room that has over one.
   */
   public static int getRoomOverOne(int a){
      int rn = 0;
      for(int i = 0; i < a+1; i++){
         if(intPartition[a-i] != 1){
            rn = a-i;
            break;
         }
      }
      return rn;
   }
   
   /**
      The showArray method displays array values.
   */
   public static void showArray(){
      for(int i = 0; i < intPartition.length; i++){
         if(i != 0){
            System.out.print(" + ");
         }
         System.out.print(intPartition[i]);
      }
      System.out.println();
   }
   
   /**
      The moveValue method moves values to next room.
   */
   public static void moveValue(int a){
      for(int i = 0; i < userInput-a-2; i++){
         intPartition[userInput-1-i] = intPartition[userInput-2-i];
         intPartition[userInput-2-i] = 0;
      }
   }
}
