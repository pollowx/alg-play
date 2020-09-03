package com.hbx.play.daily;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GenerateTrees95 {

    public static void main(String[] args) {
        GenerateTrees95 generateTrees95 = new GenerateTrees95();

        List<TreeNode> res = generateTrees95.generateTrees(3);

        for (TreeNode treeNode : res) {
            treeNode.show();
        }
    }

    public List<TreeNode> generateTrees(int n) {
        if (n <= 0) {
            return new LinkedList<>();
        }
        return generateTrees(1, n);
    }

    public List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> allTrees = new LinkedList<>();
        if (start > end) {
            allTrees.add(null);
            return allTrees;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftTrees = generateTrees(start, i - 1);

            List<TreeNode> rightTrees = generateTrees(i + 1, end);

            for (TreeNode left : leftTrees) {
                for (TreeNode right : rightTrees) {
                    TreeNode treeNode = new TreeNode(i);
                    treeNode.left = left;
                    treeNode.right = right;

                    allTrees.add(treeNode);
                }
            }
        }
        return allTrees;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        void show() {
            if (this == null) {
                return;
            }
            ArrayList<TreeNode> queue = new ArrayList<>();
            queue.add(this);
            while (queue.size() > 0) {
                TreeNode temp = queue.get(0);
                queue.remove(0);
                System.out.print(temp.val + "\t");
                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }
            System.out.println();
        }
    }
}
