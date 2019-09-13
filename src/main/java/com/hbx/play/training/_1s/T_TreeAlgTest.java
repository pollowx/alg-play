//package com.hbx.play.training._1s;
//
//import java.util.ArrayList;
//import java.util.Stack;
//
///**
// * @author danyu.bx
// * @description: TODO
// * @date 2019/8/29 2:37 PM
// */
//public class T_TreeAlgTest {
//
//    public static void main(String[] args) {
//        /*
//         *                  1
//         *                 /  \
//         *               2     3
//         *              /  \   /\
//         *             4  5   6  7
//         */
//        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7};
//
//        TreeNode root = getBinaryTree(array, 0);
//
//        myTFindDFS(root);
//
//        System.out.println(" ");
//
//        myTFindBFS(root);
//
//    }
//
//    //获取二叉树
//    private static TreeNode getBinaryTree(int[] arr, int index) {
//        TreeNode node = null;
//        if (index < arr.length) {
//            int value = arr[index];
//            node = new TreeNode(value);
//            node.left = getBinaryTree(arr, index * 2 + 1);
//            node.right = getBinaryTree(arr, index * 2 + 2);
//            return node;
//        }
//        return node;
//    }
//
//    private static void myTFindDFS(TreeNode root) {
//        if (null == root) {
//            return;
//        }
//        Stack<TreeNode> helpStack = new Stack<>();
//        helpStack.push(root);
//
//        while (!helpStack.isEmpty()) {
//            TreeNode temp = helpStack.peek();
//
//            System.out.print(temp.value + "\t");
//
//            helpStack.pop();
//
//            if (temp.right != null) {
//                helpStack.push(temp.right);
//            }
//
//            if (temp.left != null) {
//                helpStack.push(temp.left);
//            }
//        }
//    }
//
//        /**
//         * 广度优先搜索
//         *
//         * @param root
//         */
//    private static void myTFindBFS(TreeNode root) {
//        if (null == root) {
//            return;
//        }
//        ArrayList<TreeNode> helpList = new ArrayList<>();
//        helpList.add(root);
//
//        while (!helpList.isEmpty()) {
//            TreeNode temp = helpList.get(0);
//
//            System.out.print(temp.value + "\t");
//
//            if (temp.left != null) {
//                helpList.add(temp.left);
//            }
//            if (temp.right != null) {
//                helpList.add(temp.right);
//            }
//            helpList.remove(0);
//        }
//    }
//
//    //二叉树结构
//    static class TreeNode {
//        int value;
//        TreeNode left;
//        TreeNode right;
//
//        TreeNode(int value) {
//            this.value = value;
//        }
//    }
//
//}
