package Entity;

import java.util.Scanner;

public enum WeeklySchedule {
        EVEN,
        ODD,
        BOTH;
        public static WeeklySchedule chooseWeek(){
                int choice = -1;
                System.out.println("1. ODD\n2. EVEN\n3. BOTH");
                Scanner sc = new Scanner(System.in);
                do{
                        choice = sc.nextInt();
                        if(choice != 1 && choice != 2 && choice != 3){
                                System.out.println("Please enter a correct option.");
                                choice = -1;
                        }
                }while(choice == -1);
                return WeeklySchedule.values()[choice - 1];
        }
}

