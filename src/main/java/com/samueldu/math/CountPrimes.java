package com.samueldu.math;

/**
 * Given an integer n, return the number of prime numbers that are strictly less than n.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 10
 * Output: 4
 * Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 * Example 2:
 *
 * Input: n = 0
 * Output: 0
 * Example 3:
 *
 * Input: n = 1
 * Output: 0
 *
 *
 * Constraints:
 *
 * 0 <= n <= 5 * 106
 *    Hide Hint #1
 * Checking all the integers in the range [1, n - 1] is not efficient. Think about a better approach.
 *    Hide Hint #2
 * Since most of the numbers are not primes, we need a fast approach to exclude the non-prime integers.
 *    Hide Hint #3
 *
 *    Solution
 * Overview
 * The basic brute-force solution for this problem is to iterate from 0 to n and for each number, we do a prime-check. To check if a number is prime or not, we simply check if its divisors include anything other than 1 and the number itself. If so, then it is not a prime number. This method will not scale for the given limits on n. The iteration itself has O(n)O(n) time complexity and for each iteration, we have the prime check which takes O(\sqrt{n})O(
 * n
 * ​
 *  ). This will exceed the problem's time limit. Therefore, we need a more efficient solution.
 *
 * Instead of checking if each number is prime or not, what if we mark the multiples of a prime number as non-prime?
 *
 *
 * Approach: Sieve of Eratosthenes
 * Intuition
 *
 * Suppose we are required to count the number of primes that are less than 21. Start by creating an array that contains 21 integers (each index represents an integer).
 *
 * Array of 21 integers
 *
 * Figure 1. An array of 21 integers which we'll use to mark primes and non-primes.
 *
 * Now, let's start with the smallest prime number we know, which is 2. We mark the multiples of this number as non-primes in the array. To mark a number as non-prime, we set a sentinel value of -1 in the array at the index corresponding to that number. E.g. the number 4 is not a prime number, so we mark primes[4] = -1.
 *
 * Multiples of "2" marked as composites
 *
 * Figure 2. Multiples of 2 marked as composites in the array.
 *
 * Now let's move on to the next available element in the array that has not yet been marked as a composite number. That number is 3, which is also a prime. Now, we repeat the same process with 3 i.e. we mark all the multiples (some will be repeats like 6) as composites.
 *
 * Multiples of "3" marked as composites
 *
 * Figure 3. Multiples of 3 are marked as composites in the array.
 *
 * At this point, you may notice that all of the numbers remaining in the array (that are not marked as composites) are primes.
 *
 * Remaining numbers as primes
 *
 * Figure 4. Highlighting the remaining numbers as primes.
 *
 * We can start with the smallest prime number, 2, and mark all of its multiples up to "n" as non-primes. Then we repeat the same process for the next available number in the array that is not marked as composite and so on.
 *
 * We have a nested-loop structure. Now the question is: What are the bounds on these two loops? The outer loop will start at 2 and go up to \sqrt{n}
 * n
 * ​
 *  . This is because by that point we will have considered all of the possible multiples of all the prime numbers below n. Let's look at the example where n is 30. Now the square-root of n is greater than 5.
 *
 * It is not necessary to consider any number greater than the square root of n.
 *
 * 6 * 1 = 6 = 1 * 6
 * 6 * 2 = 12 = 2 * 6
 * 6 * 3 = 18 = 3 * 6
 * 6 * 4 = 24 = 2 * 12
 * 6 * 5 = 30 = 5 * 6
 * 6 * 6 = 36 > 30
 *
 * Notice that every multiple of 6 was already addressed by some multiple of a prime number less than 6.
 * Now that the outer loop's boundaries are defined, let's define the boundaries of the inner loop. We will invariantly pick the next available prime number (a number/index not yet marked in the array as a composite) before entering the inner loop. Say the index we picked from the outer loop is i, then the inner loop will start at i*i and increase by increments of i until it surpasses n. In short, we iterate over every multiple of i between i and n.
 *
 * The question now is why should we start at i*i. Why not start at 2*i to keep things simple? The reason is that all of the previous multiples would already have been covered by previous primes. In number theory, the fundamental theorem of arithmetic, also called the unique factorization theorem or the unique prime factorization theorem, states that every integer greater than 1 either is a prime number itself or can be represented as the product of prime numbers. So the prime numbers smaller than i would have already covered the multiples smaller than i*i. Let's look at the prime number 7 to see how all the multiples up to 7*7 are already covered by primes smaller than '7'.
 *
 *
 * Let's assume that n is 50 (a value greater than 7*7) to demonstrate this claim.
 *
 * 7 * 2 = 14 = 2 * 7
 * 7 * 3 = 21 = 3 * 7
 * 7 * 4 = 28 = 2 * 2 * 7 = 2 * 14
 * 7 * 5 = 35 = 5 * 7
 * 7 * 6 = 42 = 2 * 3 * 7 = 2 * 21
 * Algorithm
 *
 * Wikipedia provides a great approach for this algorithm. So we will follow their method step by step to find all the prime numbers less than or equal to a given integer n by Eratosthenes' method:
 *
 * Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
 * Let p be the variable we use in the outer loop that iterates from 2 to \sqrt{n}
 * n
 * ​
 *  . Initially, let p equal 2, the smallest prime number.
 * Enumerate the multiples of p by counting in increments of p from p*p to n, and mark them in the list (these will be p*p, p*p + p, p*p + 2*p, ...; p itself should be prime).
 * Find the smallest number in the list greater than p that is not marked. If there was no such number, stop. Otherwise, let p now equal this new number (which is the next prime), and repeat from step 3.
 * When the algorithm terminates, all of the remaining numbers that are not marked are prime.
 * A key observation is that p will always be prime because every composite value less than p*p has already been marked as a multiple of some smaller prime. Note that some of the numbers may be marked more than once (e.g., 15 will be marked by both 3 and 5).
 *
 * Implementation Improvement (Theoretical)
 *
 * The original algorithm advocates using an array to keep track of primes. However, if we do that, we will spend O(n)O(n) space just to initialize the array and O(n)O(n) time iterating over the array to count the primes. To avoid this, we will use a dictionary. There are two advantages to this:
 *
 * We don't need to iterate from 0..n to initialize the dictionary. If a number is not a part of the dictionary, it is prime by definition.
 * In the end, we can simply subtract the length of the dictionary (number of non-primes) from n to find the number of primes.
 * For n = 10, the dictionary would contain 4, 6, 8, 9. Excluding 1 and 10, we have 10 - 2 - 4 = 4 primes.
 *
 * This dictionary-based approach unfortunately does not execute within the time limit. Even though on paper it looks like it's worth saving the extra O(n)O(n) space, in practice, the time required to evaluate and maintain the dictionary makes this approach impractical. So we will stick with the traditional array-based approach. This will add an additional O(n)O(n) to the time complexity.
 *
 * Why does this happen you might ask? Well, it's probably because of the localization property of an array. An array is stored as a consecutive chunk of memory. The compiler can cache most of the array's elements in the RAM and other internal caches for super-fast access since these elements are right next to each other in memory. This is why it takes much less time to access elements in an array than to access elements in a dictionary. Furthermore, this is what offsets the savings we see on paper regarding the overall complexity for the dictionary-based approach.
 *
 * Implementation Improvement (Python Specific)
 *
 * In most cases, for loops are fine, and sometimes they are more readable than built-in functions. However, for loops must be interpreted every iteration which makes them slower than some built-in alternatives that are implemented in C. Python Built-in Sum versus For Loop Performance
 *
 * In this problem, n may be very large and for every prime number less than or equal to \sqrt{n}
 * n
 * ​
 *   we will iterate over all multiples of that number up to n and mark them as not prime. In the code below, this is done by using a for loop to iterate over every multiple of prime number (p)(p) between p^2p
 * 2
 *   and n. For readability, this implementation is presented below.
 *
 * However, by replacing the for loop with python's built-in method for accessing an array at every p^{th}p
 * th
 *   element, the runtime can be greatly reduced. This faster implementation is provided here: (click)
 *
 *   Complexity Analysis
 *
 * Time Complexity: The overall time complexity is O(\sqrt{n} \log \log n))O(
 * n
 * ​
 *  loglogn)). The \sqrt{n}
 * n
 * ​
 *   comes from the outer loop. Each time we hit a prime, we "cross out" the multiples of that prime because we know they aren't prime. But how many iterations do we perform for each prime number? That depends on how many multiples of that number are lower than nn. Let's look at a rough estimate of these values for all the primes.
 *
 *   For 2, we have to cross out n/2 numbers.
 *   For 3, we have to cross out n/3 numbers.
 *   For 5, we have to cross out n/5 numbers.
 *   ...etc for each prime less than n.
 *
 * This means that the time complexity of "crossing out" is O(\frac{n}{2} + \frac{n}{3} + \frac{n}{5} + ... + \frac{n}{\text{last prime < n}})O(
 * 2
 * n
 * ​
 *  +
 * 3
 * n
 * ​
 *  +
 * 5
 * n
 * ​
 *  +...+
 * last prime < n
 * n
 * ​
 *  ). This is bounded by O(\log \log n)O(loglogn) and the proof is available here. Cheers to this discussion post for explaining the complexity analysis in a detailed manner!
 *
 * Space Complexity: O(n)O(n) because we use an array of length n + 1n+1 to track primes and their multiples. If you use a dictionary instead of an array, you will still end up marking at least \frac{n}{2}
 * 2
 * n
 * ​
 *   elements as composites of the number 2. Thus, the overall complexity when using a dictionary is also O(n)O(n).
 */
public class CountPrimes {

        public int countPrimes(int n) {
            if (n <= 2) {
                return 0;
            }

            boolean[] numbers = new boolean[n];
            for (int p = 2; p <= (int)Math.sqrt(n); ++p) {
                if (numbers[p] == false) {
                    for (int j = p*p; j < n; j += p) {
                        numbers[j] = true;
                    }
                }
            }

            int numberOfPrimes = 0;
            for (int i = 2; i < n; i++) {
                if (numbers[i] == false) {
                    ++numberOfPrimes;
                }
            }

            return numberOfPrimes;
        }

}
