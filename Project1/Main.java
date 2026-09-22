public class Main {
    public static void main(String[] args) {

        // test cases:
        Session s1 = new Session(110959, "Choleric", "Randy Duong", "Cedars",
                "6 Sept. 2024", "8:20", "3 Linfang St.", 14 );

        Session s2 = new Session(2684, "Antinomy", "Bruno", "Mockingbirds",
                "18 Sept. 2081", "16:10", "90 Archfiend St.", 5 );

        Session s3 = new Session(838, "Fossilized", "Requis", "Requiems",
                "29 Sept. 1938", "21:21", "911 Rebellion Ct.", 14 );

        Session s4 = new Session(1001010, "Binaries", "The Playmaker", "Code",
                "11 Dec. 2000", "10:10", "7710 Nummel Dr.", 7 );

        Session s5 = new Session(6, "Savagery", "The Devil", "Pentagrams",
                "6 Jun. 666", "6:06", "222 Angelic Ave.", 14 );

        // testing display(), insertAfter(Session s),
        // addFirst(Session s), addLast(Session s).
        MyLinkedList list = new MyLinkedList();
        list = list.addLast(s1);
        list.display();
        System.out.println();
        list = list.insertAfter(s2);
        list = list.addFirst(s3);
        list.display();
        list = list.addLast(s4);
        System.out.println();
        list.display();
        list = list.insertAfter(s5);
        System.out.println();
        list.display();
        System.out.println();

        //  testing getHead()
        // expected Session ID #6
        System.out.println(list.getHead().toString() + "\n");

        // testing search methods: searchByID(int i) and searchbyMentor(String s)
        System.out.println(list.searchByID(6));
        System.out.println(list.searchByID(88));

        System.out.println();
        System.out.println(list.searchByMentor("Randy Duong"));
        System.out.println(list.searchByMentor("Exiled Prisoners"));
        System.out.println();

        // testing remove(Session s)
        // should remove Session ID 110959
        list = list.remove(s1);
        list.display();
        System.out.println();
        // should remove Session ID 838
        list = list.remove(s3);
        list.display();
        System.out.println();

        s1.setCurrentParticipants(13);
        // should return and print true;
        System.out.println(list.registerParticipant(s1));
        // should print out "Current participants: 14"
        System.out.println("Current participants: " + s1.getCurrentParticipants());
        System.out.println("Max participants: " + s1.getMaxParticipants() + "\n");

        // should return and print false
        // should not update currentParticipants attribute in Session s1.
        // should print out "Current participants: 14"
        System.out.println(list.registerParticipant(s1));
        System.out.println("Current participants: " + s1.getCurrentParticipants());
        System.out.println("Max participants: " + s1.getMaxParticipants() + "\n");

        // should return and print true
        // should print "Current participants: 1"
        // should print "Max participants: 5"
        System.out.println(list.registerParticipant(s2));
        System.out.println("Current participants: " + s2.getCurrentParticipants());
        System.out.println("Max participants: " + s2.getMaxParticipants() + " \n");

        // TESTING EXTRA CREDIT METHODS:
        // (1) cancelRegisterParticipant(Session s)

        // should return true and have "Current Participants: 0".
        System.out.println(list.cancelRegisterParticipant(s2));
        System.out.println("Current Participants: " + s2.getCurrentParticipants());

        // should return false and have "Current Participants: 0" unchanged.
        System.out.println(list.cancelRegisterParticipant(s2));
        System.out.println("Current Participants: " + s2.getCurrentParticipants());
        System.out.println();

        // (2) updateCurrSession(int i, Session s)
        Session s6 = new Session(18, "Inside", "Yours Truly", "Henopix", "7 Jul. 1943", "23:23", "1 Brass Ct.", 10);
        Session s7 = new Session(200, "Outside", "Him", "Lestorix", "8 Aug. 1977", "20:20", "5 Tin Ave.", 1);
        list.updateCurrSession(6, s6);
        // should replace SessionID 6 w/ Session s6 (Session ID 18)
        list.display();
        System.out.println();
        list.updateCurrSession(18, s7);
        // should replace SessionID 18 w/ Session s7 (Session ID 200)
        list.display();
        System.out.println();
        // should print "There is no ID -1 that exists to update the Session".
        // should not touch and affect LinkedList.
        list.updateCurrSession(-1, s6);
        list.display();

    }
}

