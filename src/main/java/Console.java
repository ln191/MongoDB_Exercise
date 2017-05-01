
import java.util.Scanner;

public class Console {

    public static void main(String[] args) {
        Kaloyan kal = new Kaloyan();
        boolean exit = false;
        int number = 0;
        kal.getUsers();
        while (exit == false) {
            System.out.println("what are you interested in?\n "
                    + "For top 5 happy and top 5 sad users press 1 , \n " 
                    + " for top 10 active users press 2 , \n "
                    + "for top 10 linking other users users press 3 ,\n  "
                    + "for number of users press 4 ,\n   "
                    + "for top 10 mentioned users press 5 .\n  "
                    + "If you want to exit this program press 0");
            Scanner in = new Scanner(System.in);
            number = in.nextInt();
            if (number == 1) {
                kal.top5HappySadUsers();
            } else if (number == 2) {
                kal.top10ActiveUsers();
            } else if (number == 3) {
                kal.top10LinkingUsers();
            } else if (number == 4) {
                kal.getUsers();
            } else if (number == 5) {
                kal.top5MostMentionedUsers();
            } else {
                exit = true;
            }
        }
    }

}
