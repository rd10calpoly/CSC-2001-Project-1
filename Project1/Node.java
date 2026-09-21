public class Node {
    private Node next;
    private Session data;

    // creates the Node object with a specific Session and a Node pointer.
    public Node (Session s, Node n) {
        this.data = s;
        this.next = n;
    }

    // getters
    // returns the next pointer.
    public Node getNext() {
        return next;
    }

    // returns the Session data.
    public Session getData() {
        return data;
    }

    // setters
    // sets the Session data to some other Session.
    public void setData(Session s) {
        this.data = s;
    }

    // setters
    // sets the next pointer to some other pointer.
    public void setNext(Node n) {
        this.next = n;
    }
}
