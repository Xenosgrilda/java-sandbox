import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a challenge from: https://www.hackerrank.com/challenges/java-stack/problem
 */
class Solution{
    private static final String[][] ENCLOSURES =  {
        {"(",")"},
        {"[","]"},
        {"{", "}"}
    };
	public static void main(String []argh)
	{
		Scanner sc = new Scanner(System.in);

		while (sc.hasNext()) {
			String input = sc.next();
            System.out.println(isBalanced(input));
        }
        
        sc.close();
    }
    
    private static boolean isBalanced(String input) {
        // TODO: Implement Stacks to handle same level closings e.g. "{()}[]"

        if (input.equals("ERROR")){
            return false;
        }

        if (input.isEmpty()) {
            return true;
        }

        return isBalanced(extractInnerString(input));
    }

    private static String extractInnerString(String input) {

        String openingChar = input.substring(0, 1);
        String closingChar = findClosingChar(openingChar);

        if (closingChar.isEmpty()){
            return "ERROR";
        }

        String regexString = new StringBuilder()
            .append("\\").append(openingChar)
            .append("(.*)")
            .append("\\").append(closingChar)
            .toString();

        Pattern regex = Pattern.compile(regexString);
        Matcher m = regex.matcher(input);

        if(m.find()) {
            return m.group(1);
        }

        return "ERROR";
    }

    private static String findClosingChar(String openingChar) {
        for (String[] group : ENCLOSURES) {
            if(group[0].equals(openingChar)){
                return group[1];
            }
        }

        return "";
    }
}
