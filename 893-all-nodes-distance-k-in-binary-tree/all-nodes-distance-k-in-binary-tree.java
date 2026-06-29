/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.*;

class Solution {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParent(root, null, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();

        queue.offer(target);
        visited.add(target);

        int distance = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            if (distance == k) break;

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();

                if (node.left != null && !visited.contains(node.left)) {
                    visited.add(node.left);
                    queue.offer(node.left);
                }
                if (node.right != null && !visited.contains(node.right)) {
                    visited.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode parent = parentMap.get(node);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.offer(parent);
                }
            }
            distance++;
        }
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()) {
            result.add(queue.poll().val);
        }
        return result;
    }
    private void buildParent(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
        if (node == null) return;

        map.put(node, parent);
        buildParent(node.left, node, map);
        buildParent(node.right, node, map);
    }
}