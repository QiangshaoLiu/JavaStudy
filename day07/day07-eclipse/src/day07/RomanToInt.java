package day07;
import java.util.*;

public class RomanToInt {
	public static void main (String args[]) {
		System.out.println("Please input the Roman number!");
		Scanner romanNum = new Scanner(System.in);
		String s = romanNum.nextLine();
		System.out.println("Your input Roman number is " + s);
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1; i < s.length(); i++){
            int num = getValue(s.charAt(i));
            if(num > preNum){
                sum -= preNum; 
            }else{
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        System.out.println("The number is: " + sum);
        romanNum.close();
    }

    private static int getValue(char x){
        switch(x){
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}
