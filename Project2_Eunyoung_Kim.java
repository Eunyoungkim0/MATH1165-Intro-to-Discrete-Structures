import java.util.*;
import java.math.*;

/**
   Project 2: Factorial Digits [FAC]
   Name: Eunyoung Kim
   Class: MATH1165-001
   Input: user chooses integer n, where 0<=n<=100
          ex) Please enter number between 0 and 100: type n
   Output: integer x, where x is the number of the zeros in n!
           integer y, where y is the sum of the digits in n!
*/
public class Project2_Eunyoung_Kim{

   public static void main(String[] args){
      int userInput = 0;
      int numOfZero = 0;
      char temp;
      boolean flag = true;
      boolean isZero = false;
      int addition = 0;
      int position = 0;
  
      //Create a Scanner object to read input data
      Scanner input = new Scanner(System.in);
      
      //Get user's input
      System.out.print("Please enter number between 0 and 100: ");
      userInput = input.nextInt();
      
      if(userInput < 0 || userInput > 100){
         System.exit(0);
      }
      
      System.out.println(userInput + "! = " + getFactory(userInput));
      String sFactorial = getFactory(userInput).toString();

      for(int i = 0; i < sFactorial.length(); i++){
         temp = sFactorial.charAt(sFactorial.length()-i-1);
                  
         //Adds each digit
         addition = addition + Character.getNumericValue(temp);
         
         if(temp == '0' && flag == true){
            position = sFactorial.length()-i-1;
            isZero = true;
         }else{
            flag = false;
         }
      }
            
      //Calculates the number of zero at the end
      if(isZero == false){
         numOfZero = 0;
      }else{
         numOfZero = sFactorial.length() - position;
      }
      
      System.out.println("* Digits: \t\t" + sFactorial.length());
      System.out.print("* Trailing zeros of " + userInput);
      System.out.printf("!: %d\n", numOfZero);
      System.out.println("* Sum of digits: \t" + addition);
   }
   
   /**
      The getFactory method calculates factorial
      @param n The number that user wants to get factorial
      @return factorial The factorial of n
   */
   public static BigInteger getFactory(int n){
      BigInteger factorial = BigInteger.ONE;
      for (int i = 0; i < n; i++){
         factorial = factorial.multiply(BigInteger.valueOf(i+1));
      }
      return factorial;
   }
}