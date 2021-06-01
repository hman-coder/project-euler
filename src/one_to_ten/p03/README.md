# Problem 3

## Definition
The prime factors of 13195 are 5, 7, 13 and 29.
What is the largest prime factor of the number 600851475143?

## Solution
Let s be the solution array.
1. By looping through primes in ascending order, try to find the smallest prime factorial for the given number.
2. Add that to the s.
3. Divide the input on that prime factorial.
4. Recurse with the new input (original input / prime factorial).
5. Break the recursion when the multiplier of all array numbers is equal to the original input.