package controller;

import model.dto.EvidenceModel;
import model.service.EvidenceService;
import java.util.List;

public class EvidenceServices {
    private EvidenceService evidenceService=new EvidenceService();
    private EvidenceModel evidenceModelDef=new EvidenceModel();
    public void addEvidence(EvidenceModel evidenceModel){
        evidenceService.add(evidenceModel);
    }
    public void removeEvidence(EvidenceModel evidenceModel){
        evidenceService.delete(evidenceModel);
    }
    public void updateEvidence(EvidenceModel evidenceModel){
        evidenceService.update(evidenceModel);
    }
    public EvidenceModel getEvidence(int id){
        EvidenceModel evidenceModel=new EvidenceModel();
        return evidenceService.findById(evidenceModel,id);
    }
    public void viewEvidence(){
        EvidenceModel evidenceModel=new EvidenceModel();
        List<EvidenceModel> evidenceModelList=evidenceService.getAll(evidenceModel);
        evidenceModelList.stream().forEach(evidenceModel1 -> System.out.println(evidenceModel1.getId()+" Course name"+evidenceModel1.getCourseModel().getName()+ " Date: "+evidenceModel1.getDateModel().getDate()+" from: "+
                evidenceModel1.getDateModel().getStartTime()+" to: "+evidenceModel1.getDateModel().getEndTime()+" Professor: "+
                evidenceModel1.getProfessorModel().getFirstName()+" "+evidenceModel1.getProfessorModel().getLastName()+" "+evidenceModel1.toString()));
    }

    public List<EvidenceModel> getEvidenceList(){
        return evidenceService.getAll(evidenceModelDef);
    }

}
