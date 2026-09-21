public class MyLinkedList {
    private Node head;

    // creates the object LinkedList with its head being null.
    public MyLinkedList() {
        this.head = null;
    }

    // getters / gets the head of the LinkedList
    public Node getHead() {
        return head;
    }

    // adds a Node to the first item of the LinkedList and returns the updated LinkedList.
    // (1) {8 -> 18 -> 1994 -> 2000 -> 7134 -> null} addFirst(Session(1, ...)) -> {1 -> 8 -> 18 -> 1994 -> 2000 -> 7134 -> null}
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
    // (1) {5 -> 7 -> 9 -> null} addLast(Session(13, ...)) -> {5 -> 7 -> 9 -> 13 -> null}
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
    // (1) {3 -> 5 -> null} insertAfter(new Session(4, ...)) -> {3 -> 4 -> 5 -> null}
    public MyLinkedList insertAfter(Session s) {
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

    // searches by SessionID in the LinkedList and returns the
    // Session's information. Otherwise, it will return "Session ID not found."
    // (1) {5 -> 7 -> 9 -> 10 -> null} searchByID(4) -> "Session ID not found."
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
    // If there is no mentor that matches with the searched mentor name in the LinkedList
    // return "Mentor not found."
    // (1) {Randy -> Harold -> Sam -> Beth -> null} searchByMentor("Harold") -> (Harold's Session).toString()
    // (2) {Randy -> Harold -> Sam -> Beth -> null} searchByMentor("Brady") -> "Mentor not found."
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
    // (1) {6 -> 8 -> 10 -> 14 -> 19 -> null} remove(Session(14, ...)) -> {6 -> 8 -> 10 -> 19 -> null}
    // (2) {821 -> 1400 -> 2500 -> 3000 -> 4001 -> null} remove(Session(1100, ...))
    //  -> {821 -> 1400 -> 2500 -> 3000 -> 4001 -> null}
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
    // maxParticipants in s1 = 5; currentParticipants in s1 = 4;
    // (1) registerParticipant(s1) -> true & currentParticipants in s1 = 5;
    // (2) registerParticipant(s1) again -> false & currentParticipants in s1 = 5;
    public boolean registerParticipant(Session s) {
        if (s.getCurrentParticipants() < s.getMaxParticipants()) {
            s.setCurrentParticipants(s.getCurrentParticipants() + 1);
            return true;
        }
           return false;
    }

    // prints out the Sessions in the LinkedList in order from head to tail.
    // (1) {Session1 -> Session2 -> null} display()
    // -> print Session1.toString() + Session2.toString()
    public void display() {
        Node curr = head;
        while (curr != null) {
            IO.println(curr.getData().toString());
            curr = curr.getNext();
        }
    }

    // [EXTRA CREDIT METHODS]


    // method for (7) Extra Credit 3: Cancel Registration.
    // opposite of the registerParticipant. Instead of incrementing,
    // currentParticipants will decrement by 1 and return true if successful (currentParticipants > 0).
    // otherwise, return false.
    // Session1 currentParticipants = 6, Session1 maxParticipants = 6;
    // Session2 currentParticipants = 0, Session2 maxParticipants = 8;
    // (1) cancelRegisterParticipant(Session1) -> true & Session1 currentParticipants becomes 5
    // (2) cancelRegisterParticipant(Session2) -> false & Session2 currentParticipants is still 0
    public boolean cancelRegisterParticipant(Session s) {
        if (s.getCurrentParticipants() > 0) {
            s.setCurrentParticipants(s.getCurrentParticipants() - 1);
            return true;
        } else {
            return false;
        }
    }

    //  method for (3) Extra Credit 2: Update Session.
    //  gets a certain Session ID and replaces that value with a given Session object.
    //  if no such ID exists, it will print an error, saying "There is no ID (currID) that exists to update Session."
    // does not return anything and only updates the current LinkedList.
    // (1) {3 -> 8 -> 18 -> 39 -> 83 -> null} updateCurrSession(8, Session(17, ...)) -> {3 -> 17 -> 18 -> 39 -> 83 -> null}
    public void updateCurrSession(int idToChange, Session s) {
        Node curr = this.head;
        while (curr != null) {
            if (idToChange == curr.getData().getSessionID()) {
                curr.setData(s);
                return;
            }
            curr = curr.getNext();
        }
        System.out.println("There is no ID " + idToChange + " that exists to update the Session.");
    }
}