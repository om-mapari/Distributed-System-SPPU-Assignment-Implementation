import java.util.Scanner;

public class Bully {
    static boolean[] state = new boolean[5];
    int coordinator;

    public static void up(int up) {
        if (state[up - 1]) {
            System.out.println("process" + up + "is already up");
        } else {
            int i;
            Bully.state[up - 1] = true;
            System.out.println("process " + up + "held election");
            for (i = up; i < 5; ++i) {
                System.out.println("election message sent from process" + up + "to process" + (i + 1));
            }
            for (i = up + 1; i <= 5; ++i) {
                if (state[i - 1] == true) {
                    System.out.println("alive message send from process" + i + "to process" + up);
                    break;
                }
            }
        }
    }

    public static void down(int down) {
        if (!state[down - 1]) {
            System.out.println("process " + down + "is already down.");
        } else {
            Bully.state[down - 1] = false;
        }
    }

    public static void mess(int mess) {
        if (state[mess - 1]) {
            if (state[4] == true) {
                System.out.println("0K");
            } else {
                System.out.println("process" + mess + "election");
                for (int i = mess; i < 5; ++i) {
                    System.out.println("election send from process" + mess + "to process " + (i + 1));
                }
                for (int i = 5; i >= mess; --i) {
                    if (state[i - 1] == true) {
                        System.out.println("Coordinator message send from process" + i + "to all");
                        break;
                    }
                }
            }
        } else {
            System.out.println("Prccess" + mess + "is down");
        }
    }

    public static void main(String[] args) {
        int choice;
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 5; ++i) {
            Bully.state[i] = true;
        }
        System.out.println("5 active process are:");
        System.out.println("Process up  = p1 p2 p3 p4 p5");
        System.out.println("Process 5 is coordinator");
        do {
            System.out.println(".........");
            System.out.println("1 up a process.");
            System.out.println("2.down a process");
            System.out.println("3 send a message");
            System.out.println("4.Exit");
            choice = sc.nextInt();
            switch (choice) {
                case 1: {
                    System.out.println("bring proces up");
                    int up = sc.nextInt();
                    if (up == 5) {
                        System.out.println("process 5 is co-ordinator");  // don't forget 
                        Bully.state[4] = true;
                        break;
                    }
                    Bully.up(up);
                    break;
                }
                case 2: {
                    System.out.println("bring down any process.");
                    int down = sc.nextInt();
                    Bully.down(down);
                    break;
                }
                case 3: {
                    System.out.println("which process will send message");
                    int mess = sc.nextInt();
                    Bully.mess(mess);
                }
            }
        } while (choice != 4);
        sc.close();
    }
}

/*
* alive message
* election message
* co-ordinator message
* Ok message
*/



/*
 * This code implements the Bully algorithm, which is a leader election
 * algorithm used in distributed systems. The algorithm is designed to elect a
 * coordinator among a set of processes.
 * 
 * Let's go through the code step by step:
 * 
 * The code defines a class named "Bully" and declares two variables: a boolean
 * array named "state" to represent the state (up or down) of each process, and
 * an integer variable named "coordinator" to store the process ID of the
 * coordinator.
 * 
 * The up method is used to bring a process up. It takes an integer parameter up
 * representing the process ID to bring up. If the process is already up
 * (state[up - 1] is true), it prints a message indicating that the process is
 * already up. Otherwise, it updates the state of the process to true,
 * indicating it is up. It then initiates an election by sending election
 * messages to all the processes with higher process IDs. Additionally, it
 * checks if there is any higher process that is already up. If so, it sends an
 * "alive" message back to the initiating process.
 * 
 * The down method is used to bring a process down. It takes an integer
 * parameter down representing the process ID to bring down. If the process is
 * already down (state[down - 1] is false), it prints a message indicating that
 * the process is already down. Otherwise, it updates the state of the process
 * to false, indicating it is down.
 * 
 * The mess method is used to send a message from one process to another. It
 * takes an integer parameter mess representing the process ID that sends the
 * message. If the sending process is up (state[mess - 1] is true), it checks if
 * the current coordinator (process 5) is up. If the coordinator is up, it
 * prints "OK" indicating that the message was successfully sent. If the
 * coordinator is down, it initiates an election by sending election messages to
 * all the processes with higher process IDs. It then checks if there is any
 * higher process that is already up and sends a "Coordinator" message to all
 * processes.
 * 
 * The main method is the entry point of the program. It initializes the state
 * array with all processes initially up. It then presents a menu to the user,
 * allowing them to bring up a process, bring down a process, send a message, or
 * exit the program. Based on the user's choice, it calls the corresponding
 * methods (up, down, or mess) to perform the desired action.
 */
