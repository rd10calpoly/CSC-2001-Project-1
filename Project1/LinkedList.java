public class LinkedList {
    private Node head;

    // instantiates and creates the LinkedList with its head being null.
    public LinkedList() {
        this.head = null;
    }

    // adds a Node to the first item of the LinkedList and returns the updated LinkedList.
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

    // adds to the last of the LinkedList and returns the updated LinkedList
    public LinkedList addLast(Session s) {
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
    public LinkedList insertAfter(Session s) {
        if (head == null || s.getSessionID() < head.getData().getSessionID()) {
            return addFirst(s);
        }

        Node curr = head;
        while (curr.getNext() != null &&
                curr.getNext().getData().getSessionID() < s.getSessionID()) {
            curr = curr.getNext();
        }

        Node addingSession = new Node(s, curr.getNext());
        curr.setNext(addingSession);
        return this;
    }

    // searches by SessionID in the LinkedList and
    public String searchByID(int id) {
        Node curr = head;
        while (curr.getNext() != null) {
            if (curr.getData().getSessionID() == id) {
                return curr.getData().toString();
            }
            curr = curr.getNext();
        }
        return "Session not found.";
    }

    public String searchByMentor(String ment) {
        Node curr = head;
        while (curr.getNext() != null) {
            if (curr.getData().getMentor().equals(ment)) {
                return curr.getData().toString();
            }
            curr = curr.getNext();
        }
        return "Session not found.";
    }

    public String remove(Session s) {
        return null;
    }

    public boolean registerParticipant(Session s) {
        if (s.getCurrentParticipants() < s.getMaxParticipants()) {
            s.setCurrentParticipants(s.getCurrentParticipants() + 1);
            return true;
        }
           return false;
    }

    public void display() {
        Node curr = head;
        while (curr.getNext() != null) {
            System.out.println(curr.getData().toString());
            curr = curr.getNext();
        }
    }
}