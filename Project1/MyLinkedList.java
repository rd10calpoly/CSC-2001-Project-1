public class MyLinkedList {
    private Node head;

    // instantiates and creates the LinkedList with its head being null.
    public MyLinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return head;
    }

    // adds a Node to the first item of the LinkedList and returns the updated LinkedList.
    public MyLinkedList addFirst(Session s) {
        if (head == null) {
            Node n = new Node(s, null);
            head = n;
        } else {
            Node n = new Node(s, this.head);
            head = n;
        }
        return this;
    }

    // adds to the last of the LinkedList and returns the updated LinkedList
    public MyLinkedList addLast(Session s) {
        if (head == null) {
            Node n = new Node(s, null);
            head = n;
            return this;
        } else {
            Node curr = head;
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            Node n = new Node(s, null);
            curr.setNext(n);
            return this;
        }
    }

    // adds a Node after some other Node based on the SessionID,
    // then it will return the new LinkedList with the added Node.
    public MyLinkedList insertAfter(Session s) {
        Node curr = head;
        while (curr.getNext() != null &&
                curr.getNext().getData().getSessionID() < s.getSessionID()) {
            curr = curr.getNext();
        }

        Node addingSession = new Node(s, curr.getNext());
        curr.setNext(addingSession);
        return this;
    }

    // searches by SessionID in the LinkedList and returns the
    // Session's information. Otherwise, it will return "Session ID not found."
    public String searchByID(int id) {
        Node curr = head;
        while (curr != null) {
            if (curr.getData().getSessionID() == id) {
                return curr.getData().toString();
            }
            curr = curr.getNext();
        }
        return "Session ID not found";
    }

    // searches by Mentor in the LinkedList and returns the Session's information.
    // If there is no mentor that matches with the searched mantor name in the LinkedList
    // return "Mentor not found."
    public String searchByMentor(String ment) {
        Node curr = head;
        while (curr != null) {
            if (curr.getData().getMentor().equals(ment)) {
                return curr.getData().toString();
            }
            curr = curr.getNext();
        }
        return "Mentor not found.";
    }

    // removes a given Session in a LinkedList and returns the LinkedList that has the Session removed.
    // if the given Session is not available, it will return the original LinkedList.
    // if the head is null, it will also return the original LinkedList
    public MyLinkedList remove(Session s) {
        if (head == null) { return this; }
        if (head.getData().getSessionID() == s.getSessionID()) {
            head = head.getNext();
            return this;
        }
        Node curr = head;
        while (curr.getNext() != null) {
            if (curr.getNext().getData().getSessionID() == s.getSessionID()) {
                curr.setNext(curr.getNext().getNext());
                return this;
            }
            curr = curr.getNext();
        }
       return this;
    }

    // increments participants if the current < max participants and will return true
    // otherwise, it will not increment and return false.
    public boolean registerParticipant(Session s) {
        if (s.getCurrentParticipants() < s.getMaxParticipants()) {
            s.setCurrentParticipants(s.getCurrentParticipants() + 1);
            return true;
        }
           return false;
    }

    // prints out the Sessions in the LinkedList in order from head to tail.
    public void display() {
        Node curr = head;
        while (curr != null) {
            IO.println(curr.getData().toString());
            curr = curr.getNext();
        }
    }
}