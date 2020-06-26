package view.ui;

import controller.CourseServices;
import controller.ProfessorServices;
import model.dto.CourseModel;
import model.dto.ProfessorModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProfessorUI {
    private Scanner scanner=new Scanner(System.in);
    private ProfessorServices professorServices=new ProfessorServices();
    private CourseServices courseServices=new CourseServices();

    public void addProfessor(){
        ProfessorModel professorModel=new ProfessorModel();
        System.out.println("Insert the CNP");
        String cnp=scanner.nextLine();
        System.out.println("Insert the first name");
        String firstName=scanner.nextLine();
        System.out.println("Insert the last name");
        String lastName=scanner.nextLine();
        String psw=firstName.toLowerCase()+"123";
        List<CourseModel> courseModelList=new ArrayList<>();
        professorModel.setCnp(cnp).setLastName(lastName).setCourseModelList(courseModelList).setFirstName(firstName).setPassword(psw).setUsername(psw);
        professorServices.addProfessor(professorModel);
    }

    public void remove(){
        professorServices.viewProfessors();
        System.out.println("Insert the id of the professor that you want to remove");
        int id=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(id);
        professorServices.removeProfessor(professorModel);
    }
    public void viewProfessors(){
        professorServices.viewProfessors();
    }

    public void updateProfessorCourse(){
        System.out.println("Insert the id of the professor that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(id);
        int option=-1;
        while (option!=0) {
            meniuUpdate();
            option=scanner.nextInt();
            if (option==1){
                updateAddCourse(professorModel);
            }if (option==2){
                updateRemoveCourse(professorModel);
            }
            professorServices.updateProfessor(professorModel);
        }
    }



    private void updateAddCourse(ProfessorModel professorModel){
        courseServices.viewCourse();
        System.out.println("Insert the id of the course that you want to add");
        int idCourse=scanner.nextInt();
        scanner.nextLine();
        CourseModel courseModel=courseServices.getCourse(idCourse);
        List<CourseModel> courseModelList=professorModel.getCourseModelList();
        courseModelList.add(courseModel);
        professorModel.setCourseModelList(courseModelList);
    }

    private void updateRemoveCourse(ProfessorModel professorModel){
        List <CourseModel> courseModelList=professorModel.getCourseModelList();
        courseModelList.stream().forEach(courseModel -> System.out.println(courseModel.toString()));
        System.out.println("Insert the id of the course that you want to delete");
        int idCourse=scanner.nextInt();
        scanner.nextLine();
        courseModelList.removeIf(courseModel -> courseModel.getId()==idCourse);
        professorModel.setCourseModelList(courseModelList);
    }
    private void meniuUpdate(){
        System.out.println("1. Add course");
        System.out.println("2. Remove course");
        System.out.println("0. Exit");
    }

    public void updateProfessorPersonalInformation(){
        professorServices.viewProfessors();
        System.out.println("Insert the id of the professor that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(id);
        int option=-1;
        while (option!=0){
            meniuUpdatePersonalInformation();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                System.out.println("Insert the new first name ");
                String firstName=scanner.nextLine();
                professorModel.setFirstName(firstName);
            }if (option==2){
                System.out.println("Insert the new last name ");
                String lastName=scanner.nextLine();
                professorModel.setLastName(lastName);
            }if (option==3){
                System.out.println("Insert the new cnp");
                String cnp=scanner.nextLine();
                professorModel.setCnp(cnp);
            }if(option==4){
                System.out.println("Insert the new password");
                String password=scanner.nextLine();
                professorModel.setPassword(password);
            }if (option==5){
                System.out.println("Insert the new usernme");
                String username=scanner.nextLine();
                professorModel.setUsername(username);
            }
        }
        professorServices.updateProfessor(professorModel);
    }

    private void meniuUpdatePersonalInformation(){
        System.out.println("1. Update first name");
        System.out.println("2. Update last name");
        System.out.println("3. Update cnp");
        System.out.println("4. Update password");
        System.out.println("5. Update username");
        System.out.println("0. Exit & Save");
    }
}
