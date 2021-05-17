package helpers;

public class PalindromeHelpers {
    /**
     * Since a palindrome is a mirrored string, we can only work on one half of the palindrome, and just mirror that.
     * Say we take the left half. The process - though a bit different depending on whether the input length is even -
     * is basically the same.
     *
     * - Loop from right to left:
     *      - if the current digit is bigger than zero, decrease it by one and break
     *      - if it is zero, then make it a 9 and continue
     * - if all characters are 0, return a null since the are no palindromes left.
     * - fill in the remaining leftmost characters without any change.
     * - create a copy of the string, inverse it, then added to the string itself.
     * - return the resultant string
     */
    public static String nextPalindrome(String number) {
        boolean isEven = number.length() % 2 == 0;
        int middleIndex = isEven ? number.length() / 2 : (number.length()/2) + 1;
        String leftSegment = number.substring(0, middleIndex);

        StringBuilder nextPalindromeLeftSegment = new StringBuilder();
        int i = middleIndex - 1;
        for(; i >= 0; i--) {
            if(leftSegment.charAt(i) == '0') {
                if(i == 0) return null;
                nextPalindromeLeftSegment.insert(0, "9");
            } else {
                int nextNumber = Integer.parseInt(leftSegment.substring(i, i+1)) - 1;
                nextPalindromeLeftSegment.insert(0, nextNumber);
                i--;
                break;
            }
        }

        for(; i >= 0; i--) nextPalindromeLeftSegment.insert(0, leftSegment.charAt(i));

        StringBuilder nextPalindromeRightSegment = new StringBuilder(nextPalindromeLeftSegment.toString());
        nextPalindromeRightSegment.reverse();
        if(isEven) return nextPalindromeLeftSegment.toString() + nextPalindromeRightSegment.toString();
        else return nextPalindromeLeftSegment.toString() + nextPalindromeRightSegment.substring(1);
    }

    public static boolean isPalindrome(String s) {
        boolean isEven = s.length() % 2 > 0;
        int middleIndex = isEven ? s.length() / 2 : (s.length()/2) + 1;
        String leftSegment = isEven ? s.substring(0, middleIndex + 1) : s.substring(0, middleIndex);
        String rightSegment = s.substring(middleIndex+1);

        return leftSegment.equals(rightSegment);
    }
}
