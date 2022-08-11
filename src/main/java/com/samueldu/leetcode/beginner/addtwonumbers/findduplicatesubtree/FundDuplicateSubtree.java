package com.samueldu.leetcode.beginner.addtwonumbers.findduplicatesubtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
public class FundDuplicateSubtree {
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        HashSet<String> subtrees= new HashSet<>();
        List<TreeNode> result = null;
        while(root.left!=null || root.right!=null){
            result =findSubtrees(subtrees, root);
        }
        return result;
    }

    private List<TreeNode> findSubtrees( HashSet<String> subtrees, TreeNode root){
        if(root!=null){
            TreeNodeVisitor v= new TreeNodeVisitor();
            v.visit(root, subtrees);
            ArrayList<TreeNode> result= new ArrayList();
            result.add(v.getDuplicatedTree());
            return result;

        }
        return null;
    }


    public class TreeNodeVisitor{
        int maxDuplicatedTreeSize=0;
        TreeNode duplicatedTree=null;

        public ArrayList<Integer> visit(TreeNode n, HashSet<String> subtrees){
            ArrayList<Integer> result= new ArrayList<>();
            if(n!=null )
                result.add(n.val);
            if(n.left!=null) result.add(n.left.val);
            if(n.right!=null) result.add(n.right.val);
            if(n.left!=null) {
                ArrayList<Integer> leftResult = visit(n.left, subtrees);
                result.addAll(leftResult);
            }
            if(n.right!=null) {
                ArrayList<Integer> rightResult = visit(n.right, subtrees);
                result.addAll(rightResult);
            }
            if(subtrees.contains(result.toString())){
                int size= result.size();
                if(maxDuplicatedTreeSize<size) {
                    maxDuplicatedTreeSize = size;
                    duplicatedTree=n;
                }
            }
            subtrees.add(result.toString());
            return result;
        }

        public TreeNode getDuplicatedTree(){
            return duplicatedTree;
        }
    }
    public class TreeNode {
     int val;
    TreeNode left;
     TreeNode right;
     TreeNode() {}
        TreeNode(int val) { this.val = val; }
     TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
      }
  }
}
