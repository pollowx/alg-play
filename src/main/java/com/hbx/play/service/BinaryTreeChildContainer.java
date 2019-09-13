package com.hbx.play.service;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/23 5:13 PM
 */
public class BinaryTreeChildContainer {

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    public boolean hasSubtree(TreeNode root1, TreeNode root2) {
        if (null == root1 || null == root2) {
            return false;
        }
        return treeAContainTreeB(root1, root2) || hasSubtree(root1.left, root2) || hasSubtree(root1.right, root2);
    }

    public boolean treeAContainTreeB(TreeNode root1, TreeNode root2) {
        if (null == root1) {
            return false;
        }
        if (null == root2) {
            return true;
        }

        if (root1.val != root2.val) {
            return false;
        }
        return treeAContainTreeB(root1.left, root2.left) && treeAContainTreeB(root1.right, root2.right);
    }

    public boolean doesTree1HasTree2(TreeNode tree1, TreeNode tree2){
        if ( tree2 == null ){
            return true;
        }
        if ( tree1 == null ){
            return false;
        }

        if ( tree1.val != tree2.val ){
            return false;
        }
        return doesTree1HasTree2(tree1.left, tree2.left) && doesTree1HasTree2(tree1.right, tree2.right);
    }

}
