/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bowlingproject;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import static java.lang.Character.getNumericValue;
import java.util.List;


/**
 * Asks user for input, takes input and produces bowling score
 * @author Rick Gould
 * @date March 18, 2017
 */
public class BowlingProject {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        // Application Logic
        String scoreInput = new String();
        int output = 0;

        try{
            System.out.println("Enter a score:");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            scoreInput = br.readLine();
            System.out.println("Your input: " + scoreInput);

            //Put input into character array
            ArrayList<Character> inputToList = new ArrayList<>();
            for(char ch : scoreInput.toCharArray())
            {
                inputToList.add(ch);
            }
          
            //Turn input to integer array
            List<Integer> hold = getIntArray(inputToList, inputToList.size());
            output = produceScore(hold);
            System.out.println("Your score = " + output);
           
          }
        catch (IOException e){
            System.out.println(e);
        }
              
        
        
    }
    
    /**
     * Takes in the character array input by user and returns
     * an integer array. 
     * 
     * @param input
     * @param size
     * @return output
     */
    public static final List<Integer> getIntArray(List<Character> input, int size){
       //Create integer array
       List<Integer> output = new ArrayList<>();
        //Loop through to create array
        for(int i = 0; i < size; i++){
            if(input.get(i) == 'X'){
               output.add(10);
            }
            if(input.get(i) == '-'){
                output.add(0);
            }
            if(input.get(i) == '/'){
                output.add(11);
            }
           
            if(getNumericValue(input.get(i)) >= 0 && getNumericValue(input.get(i)) < 10){
             output.add(getNumericValue(input.get(i)));
            }
        }
        
        return output;
    }
    
   /**
    * Takes in the integer array representing user input,
    * calculates and returns the score. 
    * 
    * @param score
    * @return 
    */
   public static final int produceScore(List<Integer> score){
       int total = 0;
       
       for(int i = 0; i < score.size(); i++){
           //Add the non-spare, non-strike score
           if(score.get(i) < 11 && score.get(i) > 0){
               total = total + score.get(i);
               
           }
          
           //Spares
           //Add as long as we are not on the last ball
           if(score.get(i) == 11 && i != score.size()-1){
               total = total + (10 - score.get(i-1)) + score.get(i+1);
               
           }
           //If ends on a spare, do not look forward
           if(score.get(i) == 11 && i == score.size()-1){
               total = total + (10 - score.get(i-1));
               
           }
           //remove socre that was added previously if
           //a spare is thrown in the last frame
           if(i == score.size()-2 && score.get(i) == 11){
               total = total - score.get(score.size()-1);
               
           }
           
           //Strikes
           if(score.get(i) == 10){
               if(i < score.size() - 3){
                   if(score.get(i+2) == 11){
                       total = total + score.get(i+1) + (10- score.get(i+1));
                   }
                   else{
                   total = total + score.get(i+1) + score.get(i+2);
                   
                   }
               }
           }
           
       }
       
    return total;
}
} 
   
   
  
