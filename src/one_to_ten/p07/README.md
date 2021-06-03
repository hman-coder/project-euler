# Problem 7

## Definition
By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

What is the 10 001st prime number?

## Solution
A simple nested loop that can solve the problem in less than 4 seconds. However, there are ways to improve it:
- Since we know the first few primes, we can cross these, and their multipliers, off.
- The loop can start with `i = 3`, and keep adding 2 to `i` each loop because otherwise we will consider all numbers divisible by 2.