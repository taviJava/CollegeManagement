package view.ui;

import controller.PrezentServices;
import model.dto.*;
import java.util.List;
import java.util.Scanner;

public class PrezentUI {
    private PrezentServices prezentServices=new PrezentServices();
    private Scanner scanner=new Scanner(System.in);

    public void add(EvidenceModel evidenceModel){
        PrezentModel prezentModel=new PrezentModel();
        SubgroupModel subgroupModel=evidenceModel.getSubgroupModel();
        List<StudentModel> studentModelList=subgroupModel.getStudentModelList();
        for (StudentModel studentModel: studentModelList){
            prezentModel.setStudentModel(studentModel);
            prezentModel.setEvidenceModel(evidenceModel);
            prezentServices.add(prezentModel);
        }
    }

    public void remove(EvidenceModel evidenceModel){
        List<PrezentModel> prezentModelList=prezentServices.getPrezentModelList();
        for (PrezentModel prezentModel: prezentModelList){
            if (prezentModel.getEvidenceModel().getId()==evidenceModel.getId()){
                prezentServices.remove(prezentModel);
            }
        }
    }

    public void view(EvidenceModel evidenceModel){
        ProfessorModel professorModel=evidenceModel.getProfessorModel();
        List<PrezentModel> prezentModelList=evidenceModel.getPrezentModelList();
        prezentModelList.stream().forEach(prezentModel -> System.out.println("Id: "+prezentModel.getId()+ "Student name: "+ prezentModel.getStudentModel().getFirstName()+" "+prezentModel.getStudentModel().getLastName()+
                " Prezent: "+ prezentModel.isPresent()+" Professor name: "+professorModel.getFirstName()+"  "+professorModel.getLastName()));
    }

    public void updatePrezent(EvidenceModel evidenceModel){
        view(evidenceModel);
        int option=-1;
        while (option!=0){
            System.out.println("1. Update if the student is prezent at the course");
            System.out.println("0. Exit & Save");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                System.out.println("Insert the id ");
                int id=scanner.nextInt();
                scanner.nextLine();
                PrezentModel prezentModel=prezentServices.getPrezentModel(id);
                boolean t=true;
                boolean f=false;
                System.out.println("1. Student is prezent");
                System.out.println("2. Student is not prezent");
                int opt=scanner.nextInt();
                scanner.nextLine();
                if (opt==1){
                    prezentModel.setPresent(t);
                }if (opt==2){
                    prezentModel.setPresent(f);
                }
                prezentServices.update(prezentModel);
            }
        }
    }

    public void updateStudentsRemove(EvidenceModel evidenceModel){  //prima data sterge  apoi o sa pui metoda de add
        List<PrezentModel> prezentModelList=evidenceModel.getPrezentModelList();
        for (PrezentModel prezentModel: prezentModelList){
            if (prezentModel.getEvidenceModel().getId()==evidenceModel.getId()){
                prezentServices.remove(prezentModel);
            }
        }
    }


}

