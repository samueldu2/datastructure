package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

import java.util.*;

/**
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 *
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * Constraints:
 *
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 *
 */
public class AccountsMerge {

    /**
     *
     *Overview
     * We are given a list of accounts where each account consists of a list containing the name of the person the account belongs to and some emails that belong to the person. One person is allowed to have multiple accounts, but each email can only belong to one person. Therefore, we can say two accounts must belong to the same person if the accounts have an email in common. Note that we cannot just use the user's name to determine which email addresses belong to the same user since different users may have the same name.
     *
     * Our goal is, for each person, we want to identify all of the emails that belong to that person. Therefore, every time we find two accounts with an email in common, we will merge the two accounts into one.
     *
     * Whenever we must work with a set of elements (emails) that are connected (belong to the same user), we should always consider visualizing our input as a graph. In this problem, converting the input into a graph will facilitate the process of "merging" two accounts.
     *
     * Emails can be represented as nodes, and an edge between nodes will signify that they belong to the same person. Since all of the emails in an account belong to the same person, we can connect all of the emails with edges. Thus, each account can be represented by a connected component. What if two accounts have an email in common? Then we can add an edge between the two connected components, effectively merging them into one connected component.
     *
     *
     * Approach 1: Depth First Search (DFS)
     * Intuition
     *
     * Here, we will represent emails as nodes, and an edge will signify that two emails are connected and hence belong to the same person. This means that any two emails that are connected by a path of edges must also belong to the same person. Initially, we are given NN accounts, where each account's emails make up a connected component.
     *
     * Our first step should be to ensure that for each account, all of its nodes are connected. Suppose an account has KK emails, and we want to connect these emails. Since all emails in an account are connected, we can add an edge between every pair of emails. This will create a complete subgraph and require adding K \choose 2(
     * 2
     * K
     * ​
     *  ) edges. However, do we really need that many edges to keep track of which emails belong to the same account? No, as long as two emails are connected by a path of edges, we know they belong to the same account. So instead of creating a complete subgraph for each account, we can create an acyclic graph using only K - 1K−1 edges. Recall that K - 1K−1 is the minimum number of edges required to connect KK nodes. In this approach, we will connect emails in an account in a star manner with the first email as the internal node of the star and all other emails as the leaves (as shown below).
     *
     * fig
     *
     * The beauty of connecting the emails in each account in this manner is that after connecting an email to a second account, that email will have one edge going to an email in the first account and one edge going to an email in the second account. Thereby automatically merging the two accounts. The below slideshow depicts the merging process for four accounts that belong to two different people.
     *
     * Current
     * 6 / 7
     * After iterating over each account and connecting the emails as described above, we will have a one or more connected components. Each connected component will represent one person, and the nodes in the connected component are the person's emails. Now our task is to explore each connected component to find all the emails that belong to each person. Since a depth-first search is guaranteed to explore every node in a connected component, we will perform a DFS on each connected component (person) to find all of the connected emails.
     *
     * To do so, we will iterate over all of the nodes and consider starting a DFS. If the node has already been visited, in an earlier DFS, we will not start a DFS. Otherwise, perform a DFS traversal over the connected component and store all the visited emails together, as they all belong to one person. Each time we visit an email during a DFS, we will mark it as visited to ensure that we do not search the same connected component more than once. To read more about how DFS can be leveraged to find components you can refer to the first approach here.
     *
     * Algorithm
     *
     * Create an adjacency list: For each account add an edge between the first email (accountFirstEmail) and each of the other emails in the account.
     * Traverse over the accounts; for each account, check if the first email in the account (accountFirstEmail) was already visited. If so, then do not start a new DFS. Otherwise, perform DFS with this email as the source node.
     * During each DFS, store the traversed emails in an array mergedAccount, also mark all these emails as visited.
     * After the DFS traversal is over, sort the emails and add the account name (accountName) at the start of the vector mergedAccount.
     * Store the vector mergedAccount in the answer list mergedAccounts.
     */

    //keeps all visited email addresses
    HashSet<String> visited = new HashSet<>();
    Map<String, List<String>> adjacent = new HashMap<>();

    /**
     * Complexity Analysis
     *
     * Here NN is the number of accounts and KK is the maximum length of an account.
     *
     * Time complexity: O(NK \log{NK})O(NKlogNK)
     *
     * In the worst case, all the emails will end up belonging to a single person. The total number of emails will be N*KN∗K, and we need to sort these emails. DFS traversal will take NKNK operations as no email will be traversed more than once.
     *
     * Space complexity: O(NK)O(NK)
     *
     * Building the adjacency list will take O(NK)O(NK) space. In the end, visited will contain all of the emails hence it will use O(NK)O(NK) space. Also, the call stack for DFS will use O(NK)O(NK) space in the worst case.
     *
     * The space complexity of the sorting algorithm depends on the implementation of each programming language. For instance, in Java, Collections.sort() dumps the specified list into an array this will take O(NK)O(NK) space then Arrays.sort() for primitives is implemented as a variant of quicksort algorithm whose space complexity is O(\log NK)O(logNK). In C++ sort() function provided by STL is a hybrid of Quick Sort, Heap Sort, and Insertion Sort with the worst-case space complexity of O(\log NK)O(logNK).
     * @param accountList
     * @return
     */
    public List<List<String>> accountsMerge(List<List<String>> accountList) {

        /**
         * build the adjacency list representation of the trees.
         */
        for (List <String>account:accountList){
            String accountFirstEmail=account.get(1);
            for (int j=1; j< account.size(); j++){
                String anEmail = account.get(j);
                if(!adjacent.containsKey(accountFirstEmail)){
                    adjacent.put(accountFirstEmail,new ArrayList<String>());
                }
                adjacent.get(accountFirstEmail).add(anEmail);
                if(!adjacent.containsKey(anEmail)){
                    adjacent.put(anEmail, new ArrayList<>());
                }
                adjacent.get(anEmail).add(accountFirstEmail);
            }
        }

        List<List<String>> mergedAccounts = new ArrayList<>();

        for (List<String> account:accountList){
            String accountName = account.get(0);
            String accountFirstEmail= account.get(1);

            /**
             * this is where merge happens. if an email is visited, then it's a part of different component, then we skip
             * this account as the same account is added previously.
             */
            if(!visited.contains(accountFirstEmail)){
                List<String>mergedAccount = new ArrayList<>();
                mergedAccount.add(accountName);

                //add all related emails through DFS on adjacency list
                DFS(mergedAccount, accountFirstEmail);

                Collections.sort(mergedAccount.subList(1,  mergedAccount.size()));
                mergedAccounts.add(mergedAccount);
            }
        }
        return mergedAccounts;
    }

    private void DFS(List<String> mergedAccount, String email){
        //prevent revisit.
        visited.add(email);
        mergedAccount.add(email);

        if(!adjacent.containsKey(email))
            return;

        //DFS on neighbors.
        for (String neighbor: adjacent.get(email)){
            if(!visited.contains(neighbor))
                DFS(mergedAccount, neighbor);
        }

    }

    /**
     * Approach 2: Disjoint Set Union (DSU)
     * Intuition
     *
     * As in the previous approach, the first step is to find which accounts have an email in common and merge them to form a larger connected component. Any problem that involves merging connected components (accounts) is a natural fit for the Disjoint Set Union (DSU) data structure. If you would like to learn more about the DSU data structure (also known as Union-Find), a tutorial is provided in the Graph Explore Card. Since most implementations of DSU use an array to record the root (representative) of each component, we will use integers to represent each component for ease of operability. Therefore, we will give each account a unique ID, and we will map all the emails in the account to the account's ID. We will use a map, emailGroup, to store this information.
     *
     * We chose the account index to be the identifier for all the emails of an account. We will assign the account index as the group when we get the email for the first time and when we get an email that we have already traversed, we will merge the current account and the group that we have previously stored in emailGroup using union operation.
     *
     * After traversing over all the accounts, we will find the representative of all the emails which will inform us about their group. Emails with the same representative belong to the same person/group and hence will be stored together. Also, we can retrieve the account name for our final answer using accountList as we have group which is the index in the original accounts list.
     *
     * Algorithm
     *
     * Traverse over each account, and for each account, traverse over all of its emails. If we see an email for the first time, then set the group of the email as the index of the current account in emailGroup .
     * Otherwise, if the email has already been seen in another account, then we will union the current group (i) and the group the current email belongs to (emailGroup[email]).
     * After traversing over every account and merging the accounts that share a common email, we will now traverse over every email once more. Each email will be added to a map (components) where the key is the email's representative, and the value is a list of emails with that representative.
     * Traverse over components, here the keys are the group indices and the value is the list of emails belonging to this group (person). Since the emails must be "in sorted order" we will sort the list of emails for each group. Lastly, we can get the account name using the accountList[group][0]. In accordance with the instructions, we will insert this name at the beginning of the email list.
     * Store the list created in step 4 in our final result (mergedAccount).
     */


}
