package view.ui;

import controller.AdminServices;
import model.dto.AdminModel;
import java.util.List;
import java.util.Scanner;

public class AdminManagementUI {
    private AdminServices adminServices=new AdminServices();
    private ClassroomUI classroomUI=new ClassroomUI();
    private CourseUI courseUI=new CourseUI();
    private GroupUI groupUI=new GroupUI();
    private ProfessorUI professorUI=new ProfessorUI();
    private StudentUI studentUI=new StudentUI();
    private ScheduleUI scheduleUI=new ScheduleUI();
    private Scanner scanner=new Scanner(System.in);

private void add(){
    List<AdminModel> adminModels=adminServices.getAdmModesl();
    if (adminModels.size()<1){
        adminServices.add();
    }
}

    public void loginStartApp(){
        add();
        System.out.println("Insert username");
        String username=scanner.nextLine();
        System.out.println("Insert password");
        String password=scanner.nextLine();
        if (adminServices.ifExist(username,password)){
            adminManagement();
        }else{
            System.out.println("The username or the password are wrong");
        }
    }
    private void menuAdminManagement(){
        System.out.println("1. Groups Management");
        System.out.println("2. Classroom Management");
        System.out.println("3. Professors Management ");
        System.out.println("4. Students Management ");
        System.out.println("5. Course Management");
        System.out.println("6. Schedule Management");
        System.out.println("0. Exit & Save");
    }
    public void adminManagement(){
        int option=-1;
        while (option!=0){
            menuAdminManagement();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                groupManagement();
            }if (option==2){
                classroomManagement();
            }if (option==3){
                profManagement();
            }if (option==4){
                studentManagement();
            }if (option==5){
                courseManagement();
            }

        }
    }
    private void menuProf(){
        System.out.println("1. Add Professor");
        System.out.println("2. Remove Professor");
        System.out.println("3. View Professors information ");
        System.out.println("4. Update Professors information ");
        System.out.println("0. Exit & Save");
    }
    private void profManagement(){
        int option=-1;
        while (option!=0){
            menuProf();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                professorUI.addProfessor();
            }if (option==2){
                professorUI.remove();
            }if (option==3){
                professorUI.viewProfessors();
            }if (option==4){
                professorUI.updateProfessorPersonalInformation();
            }

        }
    }

    private void menuStudent(){
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. View Students information ");
        System.out.println("4. Update Students information ");
        System.out.println("0. Exit & Save");
    }

    private void studentManagement(){
        int option=-1;
        while (option!=0){
            menuStudent();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                studentUI.addStudent();
            }if (option==2){
                studentUI.remove();
            }if (option==3){
                studentUI.viewStudent();
            }if (option==4){
                studentUI.UpdateStudentPersonalInformation();
            }
        }
    }
    private void menuGroup(){
        System.out.println("1. Add Group");
        System.out.println("2. Remove Group");
        System.out.println("3. View Groups information ");
        System.out.println("4. Update Groups information ");
        System.out.println("0. Exit & Save");
    }
    private void groupManagement(){
        int option=-1;
        while (option!=0){
            menuGroup();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                groupUI.add();
            }if (option==2){
                groupUI.remove();
            }if (option==3){
                groupUI.viewGroups();
            }if (option==4){
                groupUI.update();
            }

        }
    }


    private void menuCourse(){
        System.out.println("1. Create Course");
        System.out.println("2. Add Dates to course");
        System.out.println("3. Remove Course or Schedule dates from course");
        System.out.println("4. View Courses information ");
        System.out.println("5. View Schedule Course information");
        System.out.println("6. Update Courses information ");
        System.out.println("0. Exit & Save");
    }

    private void courseManagement(){
        int option=-1;
        while (option!=0){
            menuCourse();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                courseUI.addCourse();   //V
            }if (option==2){
                scheduleUI.addSchedule();

            }if (option==3){
                courseUI.removeCourse();

            }if (option==4){
                courseUI.viewCourses();

            }if (option==5){
                scheduleUI.viewSchedule();
            }if (option==6){
                courseUI.updateCourse();
            }

        }
    }


    private void menuClassRoom(){
        System.out.println("1. Add classroom");
        System.out.println("2. Remove classroom");
        System.out.println("3. View classrooms");
        System.out.println("0. Exit & Save");
    }

    private void classroomManagement(){
        int option=-1;
        while (option!=0){
            menuClassRoom();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                classroomUI.addClass();
            }if (option==2){
                classroomUI.removeClass();
            }if (option==3){
                classroomUI.viewClass();
            }

        }
    }
}
