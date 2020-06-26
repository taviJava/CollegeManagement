package view.ui;

import controller.DateServices;
import controller.EvidenceServices;
import model.dto.*;
import java.util.List;
import java.util.Scanner;

public class EvidenceUI {
    private EvidenceServices evidenceServices=new EvidenceServices();
    private  PrezentUI prezentUI=new PrezentUI();
    private DateServices dateServices=new DateServices();
    private  Scanner scanner=new Scanner(System.in);

    public void add(DateModel dateModel){
        EvidenceModel evidenceModel=new EvidenceModel();
        evidenceModel.setDateModel(dateModel);
        ScheduleModel scheduleModel=dateModel.getScheduleModel();
        CourseModel courseModel=scheduleModel.getCourseModel();
        evidenceModel.setCourseModel(courseModel);
        ProfessorModel professorModel=dateModel.getProfessorModel();
        evidenceModel.setProfessorModel(professorModel);
        SubgroupModel subgroupModel=dateModel.getSubgroupModel();
        evidenceModel.setSubgroupModel(subgroupModel);
        evidenceServices.addEvidence(evidenceModel);
        prezentUI.add(evidenceModel);

    }
    public void remove(DateModel dateModel){
        EvidenceModel evidenceModel=dateModel.getEvidenceModel();
        evidenceServices.removeEvidence(evidenceModel);
        prezentUI.remove(evidenceModel);
    }

    public void viewProfSudDate(){
        System.out.println("Insert the Date id");
        int id=scanner.nextInt();
        scanner.nextLine();
        DateModel dateModel=dateServices.getDate(id);
        List<EvidenceModel> evidenceModels=evidenceServices.getEvidenceList();
        EvidenceModel evidenceModel=evidenceModels.stream().filter(evidenceModel1 -> evidenceModel1.getDateModel().getId()==dateModel.getId()).findFirst().get();
        prezentUI.view(evidenceModel);

    }

    public void updateDate(DateModel dateModel){
        EvidenceModel evidenceModel=dateModel.getEvidenceModel();
        evidenceServices.updateEvidence(evidenceModel);
    }

    public void updateDateGroup(DateModel dateModel){
        EvidenceModel evidenceModel=dateModel.getEvidenceModel();
        evidenceServices.updateEvidence(evidenceModel);
        prezentUI.add(evidenceModel);
    }
}
