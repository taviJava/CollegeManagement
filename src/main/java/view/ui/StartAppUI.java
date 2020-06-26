package view.ui;

import java.util.Scanner;

public class StartAppUI {
    private AdminManagementUI adminManagementUI=new AdminManagementUI();
    private ProfessorStudentManagement professorStudentManagement=new ProfessorStudentManagement();
    private Scanner scanner=new Scanner(System.in);
    private void menuStartApp(){
        System.out.println("1. Admin Management");
        System.out.println("2. Professor/Student App");
        System.out.println("0. Exit & Save");
    }
    public void startApp(){
        int option=-1;
        while (option!=0){
            menuStartApp();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                adminManagementUI.loginStartApp();
            }if (option==2){
                professorStudentManagement.startApp();
            }
        }
    }
}
