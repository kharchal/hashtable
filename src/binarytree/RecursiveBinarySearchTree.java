package binarytree;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * {@link RecursiveBinarySearchTree} is an implementation of a {@link BinarySearchTree} that is based on a linked nodes
 * and recursion. A tree node is represented as a nested class {@link Node}. It holds an element (a value) and
 * two references to the left and right child nodes.
 *
 * @param <T> a type of elements that are stored in the tree
 * @author Taras Boychuk
 * @author Maksym Stasiuk
 */
public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {
    private Node<T> root;
    private int size;

    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        public Node(T value) {
            this.value = value;
        }
    }

    public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
        Objects.requireNonNull(elements);
        RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree<>();
        Arrays.stream(elements).forEach(tree::insert);
        return tree;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        if (root == null) {
            root = new Node(element);
            size++;
            return true;
        }
        return insert(root, element);

    }

    private boolean insert(Node<T> node, T element) {
        if (element.compareTo(node.value) < 0) {
            if (node.left == null) {
                node.left = new Node(element);
                size++;
                return true;
            } else {
                return insert(node.left, element);
            }
        } else if (element.compareTo(node.value) > 0) {
            if (node.right == null) {
                node.right = new Node(element);
                size++;
                return true;
            } else {
                return insert(node.right, element);
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        Objects.requireNonNull(element);
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        if (node == null) {
            return false;
        } else if (element.compareTo(node.value) < 0) {
            return contains(node.left, element);
        } else if (element.compareTo(node.value) > 0) {
            return contains(node.right, element);
        } else {
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int depth() {
        return root == null ? 0 : depth(root) - 1;
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(depth(node.left), depth(node.right));
    }

    @Override
    public void inOrderTraversal(Consumer<T> consumer) {
        inOrderTraversal(root, consumer);
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
        if (node != null) {
            inOrderTraversal(node.left, consumer);
            consumer.accept(node.value);
            inOrderTraversal(node.right, consumer);
        }
    }
}
