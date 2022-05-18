package linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Stack;

public class DemoApp {
    public static void main(String[] args) {
        var head = createLinkedList(4, 3, 9, 1);
        printReversedRecursively(head);
        printReversedUsingStack(head);
    }

    @ToString
    @AllArgsConstructor
    private static class Node<T> {
        T value;
        Node<T> next;

    }

    /**
     * Creates a list of linked {@link Node} objects based on the given array of elements and returns a head of the list.
     *
     * @param elements an array of elements that should be added to the list
     * @param <T>      elements type
     * @return head of the list
     */
    public static <T> Node<T> createLinkedList(T... elements) {
        Node<T> next = null;
        for (int i = elements.length - 1; i >= 0; i--) {
            Node node = new Node(elements[i], next);
            next = node;
        }
        return next;
    }

    /**
     * Prints a list in a reserved order using a recursion technique. Please note that it should not change the list,
     * just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedRecursively(Node<T> head) {
        printReversedRecursivelyHelper(head);
        System.out.println();
    }

    private static <T> void printReversedRecursivelyHelper(Node<T> head) {
        if (head == null) {
            return;
        }
        printReversedRecursivelyHelper(head.next);
        System.out.print((head.next == null ? "" : " -> ") + head.value);
    }

    /**
     * Prints a list in a reserved order using a {@link java.util.Stack} instance. Please note that it should not change
     * the list, just print its elements.
     * <p>
     * Imagine you have a list of elements 4,3,9,1 and the current head is 4. Then the outcome should be the following:
     * 1 -> 9 -> 3 -> 4
     *
     * @param head the first node of the list
     * @param <T>  elements type
     */
    public static <T> void printReversedUsingStack(Node<T> head) {
        Stack<T> stack = new Stack<>();
        Node<T> node = head;
        while (node != null) {
            stack.push(node.value);
            node = node.next;
        }
        System.out.print(stack.pop());
        while (!stack.isEmpty()) {
            System.out.print(" -> " + stack.pop());
        }
        System.out.println();
    }
}