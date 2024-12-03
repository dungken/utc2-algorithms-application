import java.util.LinkedList;

class Node {
    int key;
    Node next;

    public Node(int key) {
        this.key = key;
        this.next = null;
    }
}

public class StablePriorityQueue {
    private LinkedList<Node> list;

    public StablePriorityQueue() {
        list = new LinkedList<>();
    }

    public void insert(int key) {
        Node newNode = new Node(key);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).key > key) {
                list.add(i, newNode);
                return;
            }
        }
        list.add(newNode);
    }

    public int remove() {
        if (list.isEmpty()) {
            throw new RuntimeException("Hàng đợi ưu tiên trống!");
        }
        return list.remove(0).key;
    }

    // Phương thức in ra các phần tử trong hàng đợi
    public void printQueue() {
        for (Node node : list) {
            System.out.print(node.key + " ");
        }
        System.out.println();
    }

    // Test
    public static void main(String[] args) {
        StablePriorityQueue queue = new StablePriorityQueue();

        // Chèn các khóa vào hàng đợi
        queue.insert(5);
        queue.insert(3);
        queue.insert(4);
        queue.insert(3);
        queue.insert(2);

        // In ra hàng đợi
        System.out.print("Hàng đợi ưu tiên: ");
        queue.printQueue();

        // Loại bỏ các phần tử
        System.out.println("Phần tử bị loại bỏ: " + queue.remove());
        System.out.print("Hàng đợi ưu tiên sau khi loại bỏ: ");
        queue.printQueue();
    }
}
