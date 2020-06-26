package view.ui;

import controller.*;
import model.dto.*;
import java.util.List;
import java.util.Scanner;

public class ProfessorStudentManagement {
    private ProfessorServices professorServices=new ProfessorServices();
    private  StudentServices studentServices=new StudentServices();
    private EvidenceServices evidenceServices=new EvidenceServices();
    private PrezentUI prezentUI=new PrezentUI();
    private  EvidenceUI evidenceUI=new EvidenceUI();
    private  DateServices dateServices=new DateServices();
    private SubgroupServices subgroupServices=new SubgroupServices();
    private  DateUI dateUI=new DateUI();
    private Scanner scanner=new Scanner(System.in);

    private void professorLogin(){
        System.out.println("Insert username");
        String username=scanner.nextLine();
        System.out.println("Insert password");
        String password=scanner.nextLine();
        if (professorServices.ifExist(username,password)){
            List<ProfessorModel> professorModels=professorServices.getProfesors();
            ProfessorModel professorModel=professorModels.stream().filter(professorModel1 -> professorModel1.getPassword().equals(password)&&professorModel1.getUsername().equals(username)).findFirst().get();
            professorApp(professorModel);
        }else{
            System.out.println("The username or the password are wrong");
        }
    }

    private void studentLogin(){
        System.out.println("Insert username");
        String username=scanner.nextLine();
        System.out.println("Insert password");
        String password=scanner.nextLine();
        if (studentServices.ifExist(username,password)){
            List<StudentModel> studentModelList=studentServices.getStudentModels();
            StudentModel studentModel=studentModelList.stream().filter(studentModel1 -> studentModel1.getPassword().equals(password)&&studentModel1.getUsername().equals(username)).findFirst().get();
            studApp(studentModel);
        }else{
            System.out.println("The username or the password are wrong");
        }
    }

    private void professorMenu(){
        System.out.println("1. View your courses dates ");
        System.out.println("2. View dates details");
        System.out.println("3. Make the students present");
        System.out.println("4. Change your password");
        System.out.println("0. Exit");
    }
    private void viewProf(ProfessorModel professorModel){
        List<DateModel> dateModelList=professorModel.getDateModels();
        dateModelList.stream().forEach(dateModel -> System.out.println("Date Id: "+dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));

    }

    private void viewProfStudDate(){
        evidenceUI.viewProfSudDate();
    }
    private void changeProfPass(ProfessorModel professorModel){
        System.out.println("Insert the new Password");
        String password=scanner.nextLine();
        professorModel.setPassword(password);
        professorServices.updateProfessor(professorModel);
    }

    private void professorApp(ProfessorModel professorModel){
        int option=-1;
        while (option!=0){
            professorMenu();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                viewProf(professorModel);
            }if (option==2){
                viewProf(professorModel);
                viewProfStudDate();
            }if (option==3){
                viewProf(professorModel);
                System.out.println("Insert the Date id");
                int id =scanner.nextInt();
                scanner.nextLine();
                DateModel dateModel=dateServices.getDate(id);
                List<EvidenceModel> evidenceModelList=evidenceServices.getEvidenceList();
                EvidenceModel evidenceModel=evidenceModelList.stream().filter(evidenceModel1 -> evidenceModel1.getDateModel().getId()==dateModel.getId()).findFirst().get();
                prezentUI.updatePrezent(evidenceModel);
            }if (option==4){
                changeProfPass(professorModel);
            }
        }
    }

    private void studMenu(){
        System.out.println("1. View your course dates");
        System.out.println("2. View Date details");
        System.out.println("3. Change your password");
        System.out.println("0. Exit" );
    }

    private void studApp(StudentModel studentModel){
        int option=-1;
        while (option!=0){
            studMenu();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                viewStudDates(studentModel);
            }if (option==2){
                viewStudDates(studentModel);
                viewProfStudDate();
            }if (option==3){
                changeStudPass(studentModel);
            }

        }
    }

    private void viewStudDates(StudentModel studentModel){
        SubgroupModel subgroupModel=subgroupStudent(studentModel);
        dateUI.viewBySubGroup(subgroupModel);
    }

    private SubgroupModel subgroupStudent(StudentModel studentModel){
        List<SubgroupModel> subgroupModelList=subgroupServices.getSubgroupModels();
        SubgroupModel subgroupModel1=new SubgroupModel();
        for (SubgroupModel subgroupModel: subgroupModelList){
            List<StudentModel> studentModelList=subgroupModel.getStudentModelList();
            for (StudentModel studentModel1: studentModelList){
                if (studentModel.getId()==studentModel1.getId()){
                    subgroupModel1 =subgroupModel;
                }
            }
        }
        return subgroupModel1;
    }

    private void changeStudPass(StudentModel studentModel){
        System.out.println("Insert the new Password");
        String password=scanner.nextLine();
        studentModel.setPassword(password);
        studentServices.updateStudent(studentModel);
    }

    public void startApp(){
        int option=-1;
        while (option!=0){
            System.out.println("1. Professor");
            System.out.println("2. Student");
            System.out.println("0. Exit");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                professorLogin();
            }if (option==2){
                studentLogin();
            }
        }
    }

}
