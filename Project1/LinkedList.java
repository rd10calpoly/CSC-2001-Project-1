public class LinkedList {
    private Node head;

    public LinkedList() {
        this.head = null;
    }

    public LinkedList addFirst(Session s) {
        if (head == null) {
            Node n = new Node(s, null);
            head = n;
        } else {
            Node n = new Node(s, this.head);
            head = n;
        }
        return this;
    }

    public LinkedList addLast(Session s) {
        if (head == null) {
            Node n = new Node(s, null);
            head = n;
            return this;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            Node n = new Node(s, null);
            curr.next = n;
            return this;
        }
    }

    public LinkedList insertAfter(Session s1) {
        if (s1.getSessionID() < )
    }

    public
    
}