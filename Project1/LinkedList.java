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
            while (curr.getNext() != null) {
                curr = curr.getNext();
            }
            Node n = new Node(s, null);
            curr.setNext(n);
            return this;
        }
    }

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