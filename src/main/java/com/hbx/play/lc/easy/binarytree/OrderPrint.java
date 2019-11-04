package com.hbx.play.lc.easy.binarytree;

import com.sun.imageio.plugins.common.I18N;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-11-03 22:37
 * @Description:
 */
public class OrderPrint {

    public List<Integer> inorderTraversalByStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode temp = stack.pop();
                list.add(temp.val);
                root = temp.right;
            }
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        inOrder(list, root);

        return list;
    }

    public void inOrder(List<Integer> list, TreeNode node) {
        if (null == node) {
            return;
        }
        if (node.left != null) {
            inOrder(list, node.left);
        }
        list.add(node.val);
        if (node.right != null) {
            inOrder(list, node.right);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Integer> preorderTraversalByStack(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            list.add(temp.val);
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        return list;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        preOrder(list, root);
        return list;
    }

    public void preOrder(List<Integer> list, TreeNode node) {
        if (null == node) {
            return;
        }
        list.add(node.val);
        if (node.left != null) {
            inOrder(list, node.left);
        }
        if (node.right != null) {
            inOrder(list, node.right);
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        stack1.push(root);

        Stack<TreeNode> stack2 = new Stack<>();

        while (!stack1.isEmpty()) {
            TreeNode temp = stack1.pop();
            if (temp.left != null) {
                stack1.push(temp.left);
            }
            if (temp.right != null) {
                stack1.push(temp.right);
            }
            stack2.push(temp);
        }

        while (!stack2.isEmpty()) {
            list.add(stack2.pop().val);
        }
        return list;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode n9 = new TreeNode(9);
        TreeNode n20 = new TreeNode(20);
        TreeNode n15 = new TreeNode(15);
        TreeNode n7 = new TreeNode(7);

        root.left = n9;
        root.right = n20;

        n20.left = n15;
        n20.right = n7;

        for (List<Integer> l : levelOrder(root)) {
            for (Integer c : l) {
                System.out.print(c + "\t");
            }
            System.out.println();
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);

        int levelCount = 1;
        int currentCount = 0;

        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            list.add(temp.val);
            currentCount++;

            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }

            if (currentCount == levelCount) {
                levelCount = queue.size();
                currentCount = 0;
                res.add(new ArrayList<>(list));
                list.clear();
            }
        }
        Collections.reverse(res);
        return res;
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
