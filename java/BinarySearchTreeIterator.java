package java;

import java.util.Stack;

import tarlan.printer.com.helper.TreeNode;

/**
 * Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
 *
 * BSTIterator(TreeNode root) Initializes an object of the BSTIterator class. The root of the BST is given as part of the constructor. The pointer should be initialized to a non-existent number smaller than any element in the BST.
 * boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer, otherwise returns false.
 * int next() Moves the pointer to the right, then returns the number at the pointer.
 * Notice that by initializing the pointer to a non-existent smallest number, the first call to next() will return the smallest element in the BST.
 * 
 * You may assume that next() calls will always be valid. That is, there will be at least a next number in the in-order traversal when next() is called.
 * 
 * @see https://leetcode.com/problems/binary-search-tree-iterator/description/
 */
public class BinarySearchTreeIterator {
    private final Stack<TreeNode> nodes;
    
    public BinarySearchTreeIterator(TreeNode root) {
        this.nodes = new Stack<>();
        fillLeftNodes(root);
    }

    private void fillLeftNodes(final TreeNode cur) {
        if (cur != null) {
            nodes.add(cur);
            fillLeftNodes(cur.left);
        }
    }
    
    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    public int next() {
        final TreeNode cur = nodes.pop();
        fillLeftNodes(cur.right);

        return cur.val;
    }
}
