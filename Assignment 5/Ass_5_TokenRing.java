import java.util.*;

public class Ass_5_TokenRing {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number of Nodes you want in the ring: ");
        int n = sc.nextInt();

        System.out.println("Ring Formed Is As below: ");

        for (int i = 0; i < n; i++) {
            System.out.print(i + " ");
        }
        System.out.println("0");
        // 0 1 2 3 4 0

        int choice = 0;

        do {

            System.out.println("Enter Sender: ");
            int sender = sc.nextInt();

            System.out.println("Enter Receiver: ");
            int receiver = sc.nextInt();

            System.out.println("Enter data to Send: ");
            int data = sc.nextInt();

            int token = 0;

            System.out.println("Token Passing : ");

            for (int i = token; i < sender; i++) {
                System.out.print(" " + i + "->");
            }

            System.out.println(" " + sender);

            System.out.println("Sender: " + sender + " is sending data: " + data);

            for (int i = sender; i != receiver; i = (i + 1) % n) {
                System.out.println("Data: " + data + " Forward by: " + i);
            }

            System.out.println("Receiver: " + receiver + " Received The data: " + data);

            token = sender;

            System.out.println("Do you want to send Data Again? 1/0");
            choice = sc.nextInt();

        } while (choice == 1);


        sc.close();

    }

}