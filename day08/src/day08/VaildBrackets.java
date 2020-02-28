package day08;

import java.util.HashMap;
import java.util.Stack;

public class VaildBrackets {
	public static void main(String args[]) {
		String s1 = "(())";
		String s2 = "()()";
		String s3 = "([)]";
		String s4 = "((()";
		boolean t1 = isVaild(s1);
		boolean t2 = isVaild(s2);
		boolean t3 = isVaild(s3);
		boolean t4 = isVaild(s4);
		
		System.out.println("Your test results are: "+t1+t2+t3+t4);
	}
	
	private static boolean isVaild(String s) {
		HashMap<Character, Character> mappings = new HashMap<Character, Character>();
		mappings.put(')', '(');
		mappings.put(']', '[');
		mappings.put('}', '{');
		
		Stack<Character> stack = new Stack<Character>();
		
		char myChar;
		
		for(int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if(mappings.containsKey(c)) {
				myChar = stack.isEmpty() ? '*' : stack.pop();
				if(mappings.get(c) != myChar) {
					return false;
				}
			}else {
			stack.push(c);
			}
		}
		return stack.isEmpty();
	}
}
