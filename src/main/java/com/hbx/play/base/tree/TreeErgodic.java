package com.hbx.play.base.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/9/3 3:35 PM
 */
public class TreeErgodic {

    private List<Integer> list = new ArrayList<>();


    /**
     * 前序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        list.add(root.val);
        if (null != root.left) {
            preorderTraversal(root.left);
        }
        if (null != root.right) {
            preorderTraversal(root.right);
        }
        return list;
    }

    /**
     * 中序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        if (null != root.left) {
            inorderTraversal(root.left);
        }

        list.add(root.val);

        if (null != root.right) {
            inorderTraversal(root.right);
        }
        return list;
    }

    /**
     * 后序遍历-递归
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        if (null != root.left) {
            postorderTraversal(root.left);
        }
        if (null != root.right) {
            postorderTraversal(root.right);
        }

        list.add(root.val);

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        TreeNode treeNode9 = new TreeNode(9);
        TreeNode treeNode20 = new TreeNode(20);

        TreeNode treeNode15 = new TreeNode(15);
        TreeNode treeNode7 = new TreeNode(7);

        root.left = treeNode9;
        root.right = treeNode20;

        treeNode20.left = treeNode15;
        treeNode20.right = treeNode7;

        //levelOrder(root);

        System.out.println(getTreeDepth(root));
        System.out.println(getTreeDepth(root, 1));
    }

    /**
     * 广度优先搜索
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> cL = new ArrayList<>();

        List<TreeNode> helpQueue = new ArrayList<>();
        helpQueue.add(root);
        result.add(Stream.of(root.val).collect(Collectors.toList()));

        int currentCount = 0;
        int levelSize = 1;

        while (!helpQueue.isEmpty()) {
            currentCount++;

            TreeNode temp = helpQueue.remove(0);
            if (null != temp.left) {
                helpQueue.add(temp.left);
                cL.add(temp.left.val);
            }
            if (null != temp.right) {
                helpQueue.add(temp.right);
                cL.add(temp.right.val);
            }
            if (levelSize == currentCount && !cL.isEmpty()) {
                currentCount = 0;
                levelSize = helpQueue.size();
                result.add(new ArrayList<>(cL));
                cL.clear();
            }
        }
        return result;
    }

    /**
     * 树的深度
     * @param root
     * @return
     */
    private static int getTreeDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = getTreeDepth(root.left);
        int right = getTreeDepth(root.right);

        return Math.max(left, right) + 1;
    }

    private static int HELP_TREE_DEEP = 0;

    /**
     * 树的深度
     * @param root
     * @param depth
     * @return
     */
    private static int getTreeDepth(TreeNode root, int depth) {
        if (null == root) {
            return 0;
        }
        if (null == root.left && null == root.right) { // 叶子
            HELP_TREE_DEEP = Math.max(HELP_TREE_DEEP, depth);
        }
        getTreeDepth(root.left, depth + 1);
        getTreeDepth(root.right, depth + 1);

        return HELP_TREE_DEEP;
    }

    private static boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return true;
        }
        // 叶子节点
        if (null == root.left && null == root.right) {
            return true;
        }

        if (null != root.left && !isSymmetric(root.left)) {
            return false;
        }
        if (null != root.right && !isSymmetric(root.right)) {
            return false;
        }
        return root.left.val == root.right.val;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
