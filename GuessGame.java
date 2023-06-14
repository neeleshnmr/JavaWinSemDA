import java.util.*;
public class GuessGame {

    public static void guessTheDigits(){
        Scanner sc = new Scanner(System.in);
        System.out.println("********** Welcome to the Lottery **********");
        System.out.println("**** Guess the FOUR DIGIT number correctly to Win ****");
        System.out.println("*** If you guess it completely right, you get 1 LAKH RUPEES ***");
        System.out.println("*** If you get last three digits right, you get 50 THOUSAND RUPEES ***");
        System.out.println("*** If you get last two digits right, you get 10 THOUSAND RUPEES ***");
        System.out.println("*** If you get only one digit right, you get 2 THOUSAND RUPEES ***");
        int n = (int) (9999 * (Math.random()) + 1000);
//        System.out.println(n);
        String num = Integer.toString(n);
//        System.out.println(num);
        char[] numArray = num.toCharArray();
        char[] enteredArray = new char[4];
        int len = numArray.length;
        int i = 0;
        int c = 0;


        String[] digits = {"FIRST", "SECOND", "THIRD", "FOURTH" };
        System.out.println("Guess the number");
        do{
            int tryCount = 3;

            System.out.println("Guess the "+digits[i]+ " digit of the number");
            while(tryCount>0){
                enteredArray[i] = sc.next().charAt(0);
                if(enteredArray[i] == numArray[i]){
                    System.out.println("Great Work! You have guessed the "+digits[i]+ " digit Correctly." );
                    i++;
                    tryCount = 0;
                    c++;


                }
                else{
                    tryCount--;
                    System.out.println("Wrong guess! Try again. You have "+(tryCount)+" tries left");
                    if(tryCount == 0){

                        System.out.println("Tries to guess "+digits[i]+ " digit exhausted");
                        i++;
                    }
                    else if(enteredArray[i]>numArray[i]){
                        System.out.println("Hint: Enter a number less than "+enteredArray[i]);
                    }
                    else if(enteredArray[i]<numArray[i]){
                        System.out.println("Hint: Enter a number than greater than "+enteredArray[i]);

                    }

                }
            }

        }while(i<len);
        System.out.println();
        System.out.println("Correct number: ");
        for (int j = 0; j<len; j++){
            System.out.print(numArray[j]);
        }
        System.out.println();
        if(Arrays.equals(enteredArray, numArray)){
            System.out.println("You've guessed all digits correctly!");
            System.out.println("Congratulations! You've won 1,00,000 RUPEES!");

        }
        else if(c == 1){
            System.out.println("You've guessed one digit correctly!");
            System.out.println("You've won 2,000 RUPEES!");

        }
        else if(c == 2){
            System.out.println("You've guessed two digits correctly!");
            System.out.println("You've won 10,000 RUPEES!");

        }
        else if(c == 3){
            System.out.println("You've guessed three digits correctly!");
            System.out.println("You've won 50,000 RUPEES!");
        }
        else if(c ==0){
            System.out.println("You did not guess any digits correctly.");
        }



    }
    public static void main(String args[]){
        guessTheDigits();

    }
}
