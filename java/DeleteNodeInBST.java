package java;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.
 *
 * Basically, the deletion can be divided into two stages:
 *
 * Search for a node to remove.
 * If the node is found, delete the node.
 * 
 * @see https://leetcode.com/problems/delete-node-in-a-bst/description/
 */
public class DeleteNodeInBST {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;
        
        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.right == null) {
                return root.left;
            } else if (root.left == null) {
                return root.right;
            } else {
                TreeNode minNode = getMin(root.right);
                root.val = minNode.val;

                root.right = deleteNode(root.right, minNode.val);
            }
        }

        return root;
    }

    private TreeNode getMin(final TreeNode root) {
        if (root.left == null)
            return root;
        
        return getMin(root.left);
    }
}
