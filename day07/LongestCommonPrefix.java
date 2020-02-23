import java.util.*;

class LongestCommonPrefix{
	public static void main(String args[]){
		System.out.println("Please input the string group: ");
		Scanner myStr = new Scanner(System.in);
		int num = 0;
		String[] strs = {"","",""};
		while(num < 3){
			strs[num++] = myStr.nextLine();
		}
		System.out.println("Your input string group are: " 
				+ strs[0] +" "+ strs[1] +" "+ strs[2]);
		if(strs.length == 0){
			System.out.println("The longest common prefix is: "
					+"");
		}
		String preFix = strs[0];
		for(int i = 1; i < strs.length; i++){
			while(strs[i].indexOf(preFix) != 0){
				preFix = preFix.substring(0, preFix.length()-1);
				if(preFix.isEmpty())
					System.out.println("The longest common prefix is: " +"");
			}
		}
		System.out.println("The longest common prefix is: " + preFix);
		myStr.close();
	}
}


