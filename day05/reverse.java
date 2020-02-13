/*Input a 32bits integer，reverse the number by every bit.*/

import java.util.Scanner;

public class reverse{
	public static void main(String args[]){
		System.out.println("Please input the test number!");
		Scanner num = new Scanner(System.in);
		int  x = num.nextInt();
		System.out.println("Your input number is " + x);
		int value = 0;
       		while(x != 0){
            		int temp = x % 10;
            		x /= 10;
            		if(value > Integer.MAX_VALUE/10 || (value == Integer.MAX_VALUE/10 && temp > 7)){
                		value = 0;
				break;
            		}
            		if(value < Integer.MIN_VALUE/10 || (value == Integer.MIN_VALUE/10 && temp < -8)){
                		value = 0;
				break;
            		}
            		value = value * 10 + temp;
        	}
        		System.out.println("The reverse number is "+ value);
	}
}


