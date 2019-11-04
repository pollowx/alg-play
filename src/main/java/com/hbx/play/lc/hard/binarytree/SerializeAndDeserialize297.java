package com.hbx.play.lc.hard.binarytree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-11-04 17:28
 * @Description:
 */
public class SerializeAndDeserialize297 {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode n9 = new TreeNode(2);
        TreeNode n20 = new TreeNode(3);
        TreeNode n15 = new TreeNode(4);
        TreeNode n7 = new TreeNode(5);

        root.left = n9;
        root.right = n20;

        n20.left = n15;
        n20.right = n7;

//        System.out.println(serialize(root));
//
//        TreeNode c = deserialize(serialize(root));

        System.out.println(serialize2(root));

        TreeNode c = deserialize2(serialize2(root));
    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (null == root) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node != root) {
                sb.append(",");
            }
            sb.append(node.val);

            if (node.right != null) {
                stack.push(node.right);
            } else {
                sb.append(",null");
            }

            if (node.left != null) {
                stack.push(node.left);
            } else {
                sb.append(",null");
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (null == data || data.length() == 0) {
            return null;
        }
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<String>();
        for (int i = 0; i < strs.length; i++) {
            queue.offer(strs[i]);
        }
        return preDesc(queue);
    }

    public static TreeNode preDesc(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String value = queue.poll();
        if (value.equals("null")) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.valueOf(value));
        node.left = preDesc(queue);
        node.right = preDesc(queue);
        return node;
    }

    //////////////////////////////////////////////////////////////////////////

    public static String serialize2(TreeNode root) {
        if (null == root) {
            return "#";
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);

        StringBuilder sb = new StringBuilder(String.valueOf(root.val)).append("!");
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                sb.append(node.left.val).append("!");
                queue.offer(node.left);
            } else {
                sb.append("#").append("!");
            }

            if (node.right != null) {
                sb.append(node.right.val).append("!");
                queue.offer(node.right);
            } else {
                sb.append("#").append("!");
            }
        }
        return sb.toString();
    }

    public static TreeNode deserialize2(String data) {
        if (null == data || data.length() == 0) {
            return null;
        }
        String[] strs = data.split("!");
        int index = 0;

        TreeNode root = generateNode2(strs[index++]);
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            node.left = generateNode2(strs[index++]);
            node.right = generateNode2(strs[index++]);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return root;
    }

    public static TreeNode generateNode2(String value) {
        if (value.equals("#")) {
            return null;
        }
        return new TreeNode(Integer.valueOf(value));
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
