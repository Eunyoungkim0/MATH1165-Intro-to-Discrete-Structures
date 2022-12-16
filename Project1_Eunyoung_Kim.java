import java.util.Scanner;
import java.util.ArrayList;

/**
   Project 1: The Greatest Common Divisor [GCD]
   Name: Eunyoung Kim
   Class: MATH1165-001
   Input: two integers(a, b)
          ex) Please enter number1: type a
              Please enter number2: type b
   Output: GCD (+process), u and v which satisfy au+bv=GCD(a,b)
*/
public class Project1_Eunyoung_Kim{

   static int gcd = 0;      // The Greatest Common Divisor
   static int gcdSeqNum = 0;// The line number where remainder is GCD
    
   static int num1 = 0;     // Absolute value of input1
   static int num2 = 0;     // Absolute value of input2
   static int maxnum = 0;   // Max number between num1, num2
   static int minnum = 0;   // Min number between num1, num2
    
   static ArrayList<Integer> quot = new ArrayList<Integer>();        // Quotient array
   static ArrayList<Integer> rmdr = new ArrayList<Integer>();        // Remainder array
   static ArrayList<Integer> dividend = new ArrayList<Integer>();    // Dividend array
   static ArrayList<Integer> divisor = new ArrayList<Integer>();     // Divisor array
   static ArrayList<Integer> number = new ArrayList<Integer>();      // (Backward Substitution)
   static ArrayList<Integer> multiple = new ArrayList<Integer>();    // (Backward Substitution)
   
   public static void main(String[] args){
   
      int input1 = 0;   // User's input 1
      int input2 = 0;   // User's input 2
      int[] bsResult = new int[2]; // u and v values
      
      //Create a Scanner object to read input data
      Scanner input = new Scanner(System.in);
      
      //Get user's input
      System.out.print("Please enter number1: ");
      input1 = input.nextInt();
      System.out.print("Please enter number2: ");
      input2 = input.nextInt();
            
      //If both numbers are 0 then gcd(0,0) = DNE
      if(input1 == 0 && input2 == 0){
         System.out.println("# GCD(0,0): DNE");
      }else{         
         //Make both numbers absolute values.
         if(input1 < 0){
            num1 = input1 * (-1);
         }else{
            num1 = input1;
         }
         if(input2 < 0){
            num2 = input2 * (-1);
         }else{
            num2 = input2;
         }
         
         //Show GCD
         System.out.println("-------------------------");
         gcd = getGcd(num1, num2);  // Get GCD of num1 and num2
         System.out.println("# GCD(" + input1 + "," + input2 + ") = " + gcd);
         
         //Backward Substitution only when both numbers are not 0.
         if(input1 != 0 && input2 != 0){
            //System.out.println("\n============================");         
            //System.out.println("   " + input1 + "u + " + input2 + "v = " + gcd);
            bsResult = getBackSub(input1, input2, num1, num2);
            //System.out.println("   (u, v) = (" + bsResult[0] + ", " + bsResult[1] + ")");
            System.out.println("  -->  " + gcd + " = " + input1 + "*(" + bsResult[0] + ") + " + input2 + "*(" + bsResult[1] + ")");
            //System.out.println("============================");
         }
      }
   }
   
   /**
      The getGcd method calculates to get gcd following the Euclidean Algorithm.
      @param num1 The absolute value of input1
      @param num2 The absolute value of input2
      @return gcd GCD of num1 and num2 
   */
   public static int getGcd(int num1, int num2){
      getMaxMin(num1, num2);  //Get Max number and Min number between num1, num2
      
      //If min number is 0, GCD is another number 
      //   because num1 and num2 are absolute numbers, and at least one of them are not 0 (If they are both 0, they should not have been here.)
      if(minnum == 0){
         gcd = maxnum;
      }
      //If they both are not 0, put them into each array according to its order.(Dividend, Divisor, Remainder, Quotient)
      //   to save those values for backward substitution.
      else{   
         dividend.add(maxnum);
         divisor.add(minnum);
         rmdr.add(maxnum % minnum);
         quot.add(maxnum / minnum);
         
         int i = 0;
         boolean flag = true;
         
         //While flag is true, this while loop keeps working. Flag will be false when remainder is 0. (Meaning it's done.)
         while (flag){
            //Dividend = Divisor * Quotient + Remainder
            //To show the process
            System.out.println(dividend.get(i) + " = " + divisor.get(i) + "*(" + quot.get(i) + ") + " + rmdr.get(i));
            
            if(rmdr.get(i) == 0){
               System.out.println("-------------------------");
               gcd = divisor.get(i);    // When the remainder is 0, the divisor in that line is GCD. 
               gcdSeqNum = i;       	// Save the line number of GCD for backward substitution.
               flag = false;
            }else{
               dividend.add(divisor.get(i));   // According to the Euclidean Algorithm (i)th divisor becomes (i+1)th dividend.
               divisor.add(rmdr.get(i));       // According to the Euclidean Algorithm (i)th remainder becomes (i+1)th divisor.
               quot.add(dividend.get(i+1) / divisor.get(i+1)); // Integer part of it
               rmdr.add(dividend.get(i+1) % divisor.get(i+1)); // Remainder
               i++;
            }
         }
      }

      return gcd;
   }

   /**
      The getMaxMin method sets max number and min number of num1 and num2
      @param n1 num1
      @param n2 num2
   */
   public static void getMaxMin(int n1, int n2){
      maxnum = Math.max(n1, n2);
      minnum = Math.min(n1, n2);
   }
   
   /**
      The getBackSub method performs Backward Substitution to get u and v.
      @param i1 input1
      @param i2 input2
      @param n1 num1 (Absolute value)
      @param n2 num2 (Absolute value)
      @return result The array that includes values of u and v
   */
   public static int[] getBackSub(int i1, int i2, int n1, int n2){
   
      int a = getSeqNum(gcd);
	  
      if(gcd == minnum){
         number.add(minnum);
         multiple.add(1);
         number.add(maxnum);
         multiple.add(0);
      }else{
         number.add(dividend.get(a));
         multiple.add(1);
         number.add(divisor.get(a));
         multiple.add(quot.get(a)*(-1));
		          
         int s = 0;
         boolean needsMore = true;
         while (needsMore){
            for (int t = 0; t < number.size(); t++){
               if(!(number.get(t) == num1 || number.get(t) == num2)){
                  s = getSeqNum(number.get(t));
				                    
				      number.set(t, dividend.get(s));
				      multiple.set(t, 1 * multiple.get(t));
                  number.add(divisor.get(s));
                  multiple.add(quot.get(s) * (-1) * multiple.get(t));
                  organize();
               }
            }
			
            for (int y = 0; y < number.size(); y++){
               if(!(number.get(y) == num1 || number.get(y) == num2)){
                  needsMore = true;
               }else{
                  needsMore = false;
               }
            }
         }
      }
      
      int[] result = new int[2];
      
      if(n1 == n2){
         if(i1 < 0){
            result[0] = -1;
         }else{
            result[0] = 1;
         }
         result[1] = 0;
      }else{
         for(int r = 0; r < number.size(); r++){            
            if(number.get(r) == i1){
               result[0] = multiple.get(r);
            }else if(number.get(r) == i1 * (-1)){
               result[0] = multiple.get(r) * (-1);
            }
                     
            if(number.get(r) == i2){
               result[1] = multiple.get(r);
            }else if(number.get(r) == i2 * (-1)){
               result[1] = multiple.get(r) * (-1);
            }
         }
      }
      
      return result;
   }
   
   /**
      The getSeqNum method finds the line number with remainder value.
      @param num The remainder value
      @return seqNum Its line number
   */
   public static int getSeqNum(int num){
      int seqNum = -1;
      //I set it until GCD line number not to perform it uselessly
      for (int j = 0; j < gcdSeqNum; j++){
         if(rmdr.get(j) == num){ //remainder array
            seqNum = j;
            break;
         }
      }
      return seqNum;
   }
      
   /**
      The organize method add values of multiple array if values of number array are same.
      (For example, if number array has two 7, it combines it)
   */
   public static void organize(){
      int compare = 0;
      
      //Compares the values of each room in number array, and if it has duplicated numbers, it combines them adding its multiple values(multiple array).
      for(int p = 0; p < number.size()-1; p++){
         compare = number.get(p);
         for(int q = p+1; q < number.size(); q++){
            if(compare == number.get(q)){
               number.set(p, number.get(q));
               multiple.set(p, multiple.get(p) + multiple.get(q));
               number.remove(q);
               multiple.remove(q);
            }
         }
      }
   }
}