public class Node {
    private Node next;
    private Session data;

    public Node (Session s, Node n) {
        this.data = s;
        this.next = n;
    }

    public Node getNext() {
        return next;
    }

    public Session getData() {
        return data;
    }

    public void setData(Session s) {
        this.data = s;
    }

    public void setNext(Node n) {
        this.next = n;
    }
}
