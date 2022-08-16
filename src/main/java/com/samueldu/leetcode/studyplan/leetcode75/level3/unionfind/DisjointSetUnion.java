package com.samueldu.leetcode.studyplan.leetcode75.level3.unionfind;

import java.util.*;

/**
 * 721. Accounts Merge
 * Medium
 *
 * 4627
 *
 * 822
 *
 * Add to List
 *
 * Share
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
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
 * Approach 2: Disjoint Set Union (DSU)
 * Intuition
 *
 * As in the previous approach, the first step is to find which accounts have an email in common and merge them to form a larger connected component. Any problem that involves merging connected components (accounts) is a natural fit for the Disjoint Set Union (DSU) data structure. If you would like to learn more about the DSU data structure (also known as Union-Find), a tutorial is provided in the Graph Explore Card. Since most implementations of DSU use an array to record the root (representative) of each component, we will use integers to represent each component for ease of operability. Therefore, we will give each account a unique ID, and we will map all the emails in the account to the account's ID. We will use a map, emailGroup, to store this information.
 *
 * We chose the account index to be the identifier for all the emails of an account. We will assign the account index as the group when we get the email for the first time and when we get an email that we have already traversed, we will merge the current account and the group that we have previously stored in emailGroup using union operation.
 *
 * After traversing over all the accounts, we will find the representative of all the emails which will inform us about their group. Emails with the same representative belong to the same person/group and hence will be stored together. Also, we can retrieve the account name for our final answer using accountList as we have group which is the index in the original accounts list.
 *
 *Algorithm
 *
 * Traverse over each account, and for each account, traverse over all of its emails. If we see an email for the first time, then set the group of the email as the index of the current account in emailGroup .
 * Otherwise, if the email has already been seen in another account, then we will union the current group (i) and the group the current email belongs to (emailGroup[email]).
 * After traversing over every account and merging the accounts that share a common email, we will now traverse over every email once more. Each email will be added to a map (components) where the key is the email's representative, and the value is a list of emails with that representative.
 * Traverse over components, here the keys are the group indices and the value is the list of emails belonging to this group (person). Since the emails must be "in sorted order" we will sort the list of emails for each group. Lastly, we can get the account name using the accountList[group][0]. In accordance with the instructions, we will insert this name at the beginning of the email list.
 * Store the list created in step 4 in our final result (mergedAccount).
 *
 */


public class DisjointSetUnion{

    public List<List<String>> accountMerge(List<List<String>> accountList){
        int accountListSize = accountList.size();
        DSU dsu = new DSU(accountListSize);
        Map<String, Integer> emailGroup = new HashMap<>();
        for (int i=0; i<accountListSize; i++){
            int accountSize = accountList.get(i).size();
            String accountName = accountList.get(i).get(0);
            for (int j=1; j<accountSize; j++){
                String email = accountList.get(i).get(j);
                if((!emailGroup.containsKey(email))){
                    //the email address belongs to group i
                    emailGroup.put(email, i);
                }else{
                    //merge the two sets of email addresses.
                    dsu.unionBySize(i, emailGroup.get(email));
                }
            }
        }

        //Store emails correpsonding to the component's representative.
        Map<Integer, List<String >> components = new HashMap<>();
        for (String email: emailGroup.keySet()){
            int group = emailGroup.get(email);
            int groupRep = dsu.findRepresentative(group);

            if(!components.containsKey(groupRep)){
                components.put(groupRep, new ArrayList<>());
            }
            components.get(groupRep).add(email);
        }

        //sort the components and add the account name
        List<List<String>> mergedAccounts = new ArrayList<>();

        for (int group: components.keySet()){
            List<String>component= components.get(group);
            Collections.sort(component);
            //add account name
            component.add(0, accountList.get(group).get(0));
        }

        return mergedAccounts;

    }

    /**
     * Disjoint Set Representation.
     */
    public class DSU {
        /**
         * y=representative[x], where x is the set number, and y is the index of the parent, in other words,
         * representative[y] is the value of x's parent. so the tree goes y-->x.
         * This array represents the Set and the parent/child relationship of the items in the Set.
         */
        int representative [];
        int size[];
        DSU(int sz){
            representative = new int[sz];
            size = new int[sz];
            for (int i=0; i<sz; ++i){
                //Initially each group is its own representative
                representative[i]=i;
                // Initialize the size of all groups to 1
                size[i]=1;
            }
        }

        /**
         * Path compression of union operation!!!!!!!!!!!!
         */
        public int findRepresentative(int x){
            if(x==representative[x])
                return x;//reached the root, return index value;
            /**
             * this recursively sets the value to the root of the tree.
             * where the index x of representative [] is the index of account+emaillist, while the representative[x] is the index location of the root node.
             * note this call has the side effects of changing data, which compresses the path.
             */
            return representative[x]=findRepresentative(representative[x]);
        }

        /**
         * Union the group at index a with the group at index b
         * @param a
         * @param b
         */
        public void unionBySize(int a, int b){
            int representativeA= findRepresentative(a); //find the root node of  tree containing a
            int representativeB = findRepresentative(b); //find the root node of tree containing b

            if(representativeA==representativeB) return; // already merged, nothing more need to be done.

            //Union by size: point the representative of the smaller group to the representative of the larger group
            if(size[representativeA]>=size[representativeB]){
                size[representativeA]+=size[representativeB];
                representative[representativeB]=representativeA;
            }else{
                size[representativeB]+=size[representativeA];
                representative[representativeA]=representativeB;
            }


        }


    }
}
