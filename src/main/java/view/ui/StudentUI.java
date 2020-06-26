package view.ui;

import controller.StudentServices;
import controller.SubgroupServices;
import model.dto.CourseModel;
import model.dto.StudentModel;
import model.dto.SubgroupModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentUI {

    StudentServices studentServices=new StudentServices();
    SubgroupServices subgroupServices=new SubgroupServices();
    Scanner scanner=new Scanner(System.in);

    public void addStudent(){
        StudentModel studentModel=new StudentModel();
        System.out.println("Insert the CNP");
        String cnp=scanner.nextLine();
        System.out.println("Insert the first name");
        String firstName=scanner.nextLine();
        System.out.println("Insert the last name");
        String lastName=scanner.nextLine();
        String psw=firstName.toLowerCase()+"123";
        List<CourseModel> courseModelList=new ArrayList<>();
        System.out.println("Insert the id of the subgroup");
        subgroupServices.viewSubgroup();
        int id =scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(id);
        studentModel.setCnp(cnp).setLastName(lastName).setCourseModelList(courseModelList).setFirstName(firstName).setSubgroupModel(subgroupModel).setPassword(psw).setUsername(psw);
        studentServices.addStudent(studentModel);
    }

    public void remove(){
        studentServices.viewStudent();
        System.out.println("Insert the id of the student that you want to remove");
        int id=scanner.nextInt();
        scanner.nextLine();
        StudentModel studentModel=studentServices.getStudent(id);
        studentServices.removeStudent(studentModel);
    }
    public void viewStudent(){
        studentServices.viewStudent();
    }




    public void UpdateStudentPersonalInformation(){
        System.out.println("Insert the id of the student that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        StudentModel studentModel=studentServices.getStudent(id);
        int option=-1;
        while (option!=0){
            meniuUpdatePersonalInformation();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                System.out.println("Insert the new first name ");
                String firstName=scanner.nextLine();
                studentModel.setFirstName(firstName);
            }if (option==2){
                System.out.println("Insert the new last name ");
                String lastName=scanner.nextLine();
                studentModel.setLastName(lastName);
            }if (option==3){
                System.out.println("Insert the new cnp");
                String cnp=scanner.nextLine();
                studentModel.setCnp(cnp);
            }if(option==4){
                System.out.println("Insert the new password");
                String password=scanner.nextLine();
                studentModel.setPassword(password);
            }if (option==5){
                System.out.println("Insert the new username");
                String username=scanner.nextLine();
                studentModel.setUsername(username);
            }if (option==6){
                System.out.println("Insert the id of the subgroup");
                subgroupServices.viewSubgroup();
                int idSub =scanner.nextInt();
                scanner.nextLine();
                SubgroupModel subgroupModel=subgroupServices.getSubgroup(idSub);
                studentModel.setSubgroupModel(subgroupModel);
            }
        }
        studentServices.updateStudent(studentModel);
    }

    private void meniuUpdatePersonalInformation() {
        System.out.println("1. Update first name");
        System.out.println("2. Update last name");
        System.out.println("3. Update cnp");
        System.out.println("4. Update password");
        System.out.println("5. Update username");
        System.out.println("6 Update subgroup");
        System.out.println("0. Exit & Save");
    }
}
