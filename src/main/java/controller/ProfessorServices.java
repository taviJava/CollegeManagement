package controller;

import model.dto.ProfessorModel;
import model.service.ProfessorService;
import java.util.List;

public class ProfessorServices {
    private ProfessorService professorService=new ProfessorService();
    private ProfessorModel professorModelDef=new ProfessorModel();
    public void addProfessor(ProfessorModel professorModel){
        professorService.add(professorModel);
    }
    public void removeProfessor(ProfessorModel professorModel){
        professorService.delete(professorModel);
    }
    public void updateProfessor(ProfessorModel professorModel){
        professorService.update(professorModel);
    }
    public void viewProfessors(){
        ProfessorModel professorModel=new ProfessorModel();
        List<ProfessorModel> professorModelList=professorService.getAll(professorModel);
        professorModelList.stream().forEach(professorModel1 -> System.out.println(professorModel1.getId()+ " Name: "+ professorModel1.getFirstName()+" "+professorModel1.getLastName()));
    }

    public ProfessorModel getProfessor(int id){
        ProfessorModel professorModel=new ProfessorModel();
        return professorService.findById(professorModel,id);
    }

    public boolean ifExist(String username, String password){
        ProfessorModel professorModel=new ProfessorModel();
        List<ProfessorModel> professorModelList=professorService.getAll(professorModel);
        for (ProfessorModel profesor: professorModelList){
            if (profesor.getUsername().equals(username)&&profesor.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public List<ProfessorModel> getProfesors(){
        List<ProfessorModel> professorModelList=professorService.getAll(professorModelDef);
        return  professorModelList;
    }




}
