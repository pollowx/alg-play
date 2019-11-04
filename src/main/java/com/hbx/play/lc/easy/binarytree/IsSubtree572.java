package com.hbx.play.lc.easy.binarytree;

import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-11-04 16:36
 * @Description:
 */
public class IsSubtree572 {

    public static void main(String[] args) {

    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (null == s) {
            return false;
        }
        return checkTwoTreeEqual(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }

    public boolean checkTwoTreeEqual(TreeNode s, TreeNode t) {
        if (null == t && null == s) {
            return true;
        }
        if (null == s || null == t || s.val != t.val) {
            return false;
        }
        return checkTwoTreeEqual(s.left, t.left) && checkTwoTreeEqual(s.right, t.right);
    }

    public boolean isSubtree2(TreeNode s, TreeNode t) {
        // ks

        return false;
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
