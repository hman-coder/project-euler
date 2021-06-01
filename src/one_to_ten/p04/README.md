# Problem 4

## Definition
A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
Find the largest palindrome made from the product of two 3-digit numbers.

## Solution
Let s be our solution. The two 3-digit numbers s1, s2 whose multiplier produces s must be closer to the square root of s. 
Since we do not know which palindrome we should be going for, let's start with the product of the multiplier of the highest 3-digit number with itself. That is, 999 * 999. If this is a palindrome, then return it. If not, then find the next palindrome.
Because the answer is as close as it can be to the square root, let the (floored) square root of that palindrome be sqrt, and set two variables x = y = sqrt.
Now we do a loop:
1. The loop binds y < 999 (largest 3-digits value) and x > 100 (smallest 3-digit value).
2. Let p = x * y. If p is higher than our palindrome, then decrease x by 1. Increase y by 1 otherwise.
3. If no solution was found, then find the next palindrome, and repeat the process.