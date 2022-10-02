package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 * Minimum Difficulty of a Job Schedule
 * Hard
 *
 * 1445
 *
 * 166
 *
 * Add to List
 *
 * Share
 * You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).
 *
 * You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.
 *
 * You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].
 *
 * Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: jobDifficulty = [6,5,4,3,2,1], d = 2
 * Output: 7
 * Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
 * Second day you can finish the last job, total difficulty = 1.
 * The difficulty of the schedule = 6 + 1 = 7
 * Example 2:
 *
 * Input: jobDifficulty = [9,9,9], d = 4
 * Output: -1
 * Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
 * Example 3:
 *
 * Input: jobDifficulty = [1,1,1], d = 3
 * Output: 3
 * Explanation: The schedule is one job per day. total difficulty will be 3.
 *
 *
 * Constraints:
 *
 * 1 <= jobDifficulty.length <= 300
 * 0 <= jobDifficulty[i] <= 1000
 * 1 <= d <= 10
 *
 * In this question, we want to distribute n jobs across d days in a way that minimizes the difficulty of the job schedule. The difficulty of a job schedule is the sum of the maximum difficulty job for each day and every day must have at least one job. Whenever you need to search for the most efficient way to distribute n items, brute force is always an option. Typically, brute force solutions are very slow, but they serve as a good starting point that can be optimized later. The brute force solution to this problem is to generate all valid ways to distribute the n jobs across d days, and then calculate the minimum job difficulty of each way.
 *      *
 *      * How many possible valid ways are there to distribute the jobs? Without thinking about the implementation details, we can abstract the question into inserting d - 1 boards to divide n balls into d piles, which is a classic problem in permutations and combinations.
 *      *
 *      * Let's illustrate the scenario using Example 4 (jobDifficulty = [7,1,7,1,7,1], d = 3). separate n job into d days
 *      *
 *      * How do we code the brute force solution? Depth-first search (DFS) is a reasonable option because it is guaranteed to explore every possible sequence of states that results in scheduling all jobs in d days. Each state is a different combination of the starting index in the jobDifficulty array, and the number of days remaining. Thus, a job schedule is a valid sequence of d states that schedules all jobs and at least one job per day. The output will be the minimum difficulty among all valid job schedules.
 *      *
 *      * In this example, we want to get the result for min_diff(0, 3), which represents the minimum job difficulty when starting from the 0^{th}0
 *      * th
 *      *   index with 3 days remaining. Refer to the top panel of the image below to see the backtracking process. Each edge signifies how the state from a top row can be derived from the state in a bottom row. For instance, the edge connecting dp(0,3) and dp(1,2) means the state min_diff(0, 3) (starting from the 0^{th}0
 *      * th
 *      *   index with 3 days remaining) can be derived from min_diff(1, 2) (starting from the 1^{st}1
 *      * st
 *      *   index with 2 days remaining, if only the 0^{th}0
 *      * th
 *      *   job is performed on that day). Notice that some states, min_diff(5, 1) for instance, were calculated many times. Therefore, to improve the time complexity, we can use memoization to avoid such repeated calculations. Adding memoization to DFS is an example of dynamic programming and is illustrated in the bottom panel of the image below.
 *      *
 *      * dfs to dp
 *      *
 *      * We will go over progressively more efficient approaches to solve the problem using dynamic programming. We will start with the most intuitive approach, top-down DP (Approach 1), followed by bottom-up DP (Approach 2), and then bottom-up DP with optimized space complexity (Approach 3). Lastly, we will explore an advanced stack solution that is the optimal approach (Approach 4).
 *      *

 */
public class MinimumDifficultyOfAJobScheduleTopDown {
    /**
     *  TOP Down
     *Intuition
     *
     * Thinking back, how did we figure out this question can be solved using dynamic programming? We were given a few clues. First, the problem asks us to minimize something, in this case, the difficulty of the job schedule. Second, our current decisions (what jobs to schedule today) depend on our previous decisions (what jobs have already been scheduled). Lastly, as shown in the diagram above, the original problem can be broken into smaller subproblems, and we can reuse the results of those subproblems to solve the original problem. These three traits are characteristic of problems that can be solved with dynamic programming. In most cases, this problem included, it is more intuitive to solve DP problems using a top-down approach as opposed to a bottom-up approach. So, for our first approach, we will use top-down dynamic programming. Now, let's take a closer look at how we can break the original problem into smaller subproblems and reuse the results of those subproblems to solve the original problem.
     *
     * In this case, we need to find all possible ways to schedule the job today and define the subproblem as starting from a larger index j with one day less. That is, to solve for min_diff(i, d), we must first calculate min_diff(j, d - 1) for all j > i, and then take the minimum job difficulty of all these possibilities as the output.
     *
     * Finally, when there is only 1 day left, we must complete all of the remaining jobs on that final day; this is the base case.
     *
     * Algorithm
     *
     * The dynamic programming solution to this problem consists of three components:
     *
     * State definition:
     *
     * Index i (the index of the first task for today in the jobDifficulty array) and day d (number of remaining days) will define the DP state. min_diff(i, d) refers to the minimum difficulty when starting at the i^{th}i
     * th
     *   job with d days left.
     *
     * min_diff(0, d) will be the final answer since it represents starting at the beginning of the job array and finishing all jobs in exactly d days.
     *
     * State transition function:
     *
     * The job at index j will be the first task for the upcoming day, therefore, the jobs to be finished today are all jobs with indices between i and j, which is jobDifficulty[i:j]. Since the difficulty of a day is the maximum difficulty of a job done in that day, the maximum of jobDifficulty[i:j] will be added to the sum of job difficulties.
     *
     * With this in mind, let's formulate the state transition function as follows:
     *
     * min_diff(i, d) = min_diff(j, d - 1) + max(jobDifficulty[i:j]) for all j > i
     *
     * Base case:
     *
     * When there is only 1 day remaining, we need to finish all unfinished jobs on that day and increase the difficulty of the job schedule by the maximum difficulty of these jobs.
     *
     * Before we dive into the code, let's take a moment to consider edge cases and optimizations.
     *
     * One edge case that we must consider is if the number of days is more than the number of tasks, then we won't be able to arrange at least one job per day; in this case, we should return -1.
     *
     * Finally, one optimization that we can implement is to use a variable, daily_max_job_diff, to keep track of the most difficult job scheduled on the current day. As we scan through the jobs for the current day, we do not need to revisit the full subarray of jobDifficulty[i:j] every time we want to calculate the maximum difficulty. Instead, each day, we can update the maximum job difficulty seen so far if the current job difficulty is greater than daily_max_job_diff.
     *
     *
     *  *      Complexity Analysis
     *  *
     *  * Let nn be the length of the jobDifficulty array, and dd be the total number of days.
     *  *
     *  * Time complexity: O(n^2 \cdot d)O(n
     *  * 2
     *  *  ⋅d) since there are n \cdot dn⋅d possible states, and we need O(n)O(n) time to calculate the result for each state.
     *  *
     *  * Space complexity: O(n \cdot d)O(n⋅d) space is required to memoize all n \cdot dn⋅d states.
     */
    public int minDifficulty(int[] jobDifficulty, int d) {

        int n = jobDifficulty.length;
        // Edge case: make sure there is at least one job per day
        if (n < d) {
            return -1;
        }

        int[][] mem = new int[n][d + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= d; j++) {
                mem[i][j] = -1;
            }
        }

        return minDiff(0, d, jobDifficulty, mem);
    }

    private int minDiff(int i, int daysRemaining, int[] jobDifficulty, int[][] mem) {
        // Use memoization to avoid repeated computation of states
        if (mem[i][daysRemaining] != -1) {
            return mem[i][daysRemaining];
        }

        // Base case: finish all remaining jobs in the last day
        if (daysRemaining == 1) {
            int res = 0;
            for (int j = i; j < jobDifficulty.length; j++) {
                res = Math.max(res, jobDifficulty[j]);
            }
            return res;
        }

        int res = Integer.MAX_VALUE;
        int dailyMaxJobDiff = 0;
        // Iterate through possible starting index for the next day
        // and ensure we have at least one job for each remaining day.
        for (int j = i; j < jobDifficulty.length - daysRemaining + 1; j++) {
            //jobs i through j are schedules on the day of ${daysRemaining}, therefore dailyMaxJobDiff=max(jobDifficulty[k]), i<=k<=j
            dailyMaxJobDiff = Math.max(dailyMaxJobDiff, jobDifficulty[j]);
            res = Math.min(res, dailyMaxJobDiff + minDiff(j + 1, daysRemaining - 1, jobDifficulty, mem));
        }
        mem[i][daysRemaining] = res;
        return res;
    }
}
