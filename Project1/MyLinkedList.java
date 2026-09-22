public class MyLinkedList {
    private Session head;
    private MyLinkedList next;

    // creates the object LinkedList with its head and the rest of the list being null.
    public MyLinkedList() {
        this.head = null;
        this.next = null;
    }

    public MyLinkedList(Session s, MyLinkedList l) {
        this.head = s;
        this.next = l;
    }

    // getters / gets the head of the LinkedList
    public Session  getHead() {
        return head;
    }

    // getters / gets the next pointer of the LinkedList
    public MyLinkedList getNext() { return next; }

    // adds a Node to the first item of the LinkedList and returns the updated LinkedList.
    // (1) {8 -> 18 -> 1994 -> 2000 -> 7134 -> null} addFirst(Session(1, ...)) -> {1 -> 8 -> 18 -> 1994 -> 2000 -> 7134 -> null}
    public MyLinkedList addFirst(Session s) {
        if (head == null) {
            head = s;
        } else {
            MyLinkedList oldList = new MyLinkedList(head, next);
            head = s;
            next = oldList;
        }
        return this;
    }

    // adds to the last of the LinkedList and returns the updated LinkedList
    // (1) {5 -> 7 -> 9 -> null} addLast(Session(13, ...)) -> {5 -> 7 -> 9 -> 13 -> null}
    public MyLinkedList addLast(Session s) {
        if (head == null) {
            head = s;
            return this;
        } else {
            MyLinkedList curr = this;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = new MyLinkedList(s, null);
            return this;
        }
    }

    // adds a Node after some other Node based on the SessionID,
    // then it will return the new LinkedList with the added Node.
    // (1) {3 -> 5 -> null} insertAfter(new Session(4, ...)) -> {3 -> 4 -> 5 -> null}
    public MyLinkedList insertAfter(Session s) {
        if (head == null || s.getSessionID() < head.getSessionID()) {
            return addFirst(s);
        }

        MyLinkedList curr = this;
        while (curr.next != null &&
                curr.next.head.getSessionID() < s.getSessionID()) {
            curr = curr.next;
        }

        curr.next = new MyLinkedList(s, curr.next);
        return this;
    }

    // searches by SessionID in the LinkedList and returns the
    // Session's information. Otherwise, it will return "Session ID not found."
    // (1) {5 -> 7 -> 9 -> 10 -> null} searchByID(4) -> "Session ID not found."
    public String searchByID(int id) {
        MyLinkedList curr = this;
        while (curr != null) {
            if (curr.head.getSessionID() == id) {
                return curr.head.toString();
            }
            curr = curr.next;
        }
        return "Session ID not found";
    }

    // searches by Mentor in the LinkedList and returns the Session's information.
    // If there is no mentor that matches with the searched mentor name in the LinkedList
    // return "Mentor not found."
    // (1) {Randy -> Harold -> Sam -> Beth -> null} searchByMentor("Harold") -> (Harold's Session).toString()
    // (2) {Randy -> Harold -> Sam -> Beth -> null} searchByMentor("Brady") -> "Mentor not found."
    public String searchByMentor(String ment) {
        MyLinkedList curr = this;
        while (curr != null) {
            if (curr.head.getMentor().equals(ment)) {
                return curr.head.toString();
            }
            curr = curr.next;
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

        if (head.getSessionID() == s.getSessionID()) {
            head = next.head;
            next = next.next;
            return this;
        }
        MyLinkedList curr = this;
        while (curr.next != null) {
            if (curr.next.head.getSessionID() == s.getSessionID()) {
                curr.next = curr.next.next;
                return this;
            }
            curr = curr.next;
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
        MyLinkedList curr = this;
        while (curr != null) {
            IO.println(curr.head.toString());
            curr = curr.next;
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
        MyLinkedList curr = this;
        while (curr != null) {
            if (idToChange == curr.head.getSessionID()) {
                curr.head = s;
                return;
            }
            curr = curr.next;
        }
        System.out.println("There is no ID " + idToChange + " that exists to update the Session.");
    }
}