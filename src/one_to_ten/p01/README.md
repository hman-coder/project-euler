# Problem 1

## Definition
If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
Find the sum of all the multiples of 3 or 5 below 1000.

## Solution
The idea is to keep multiplying both numbers by a variable x that goes up by 1 each loop, and add that multiplier to the solution, until you reach the upper bound.
Care should be taken, however, not to add the same  multiplier twice. So, when x = 3, you add 3 * 3 and 3 * 5. However, when x = 5, you should only add 5 * 5, because 3 * 5 has already been added. 