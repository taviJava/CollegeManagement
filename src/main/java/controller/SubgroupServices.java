package controller;

import model.dto.SubgroupModel;
import model.service.SubgroupService;
import java.util.List;

public class SubgroupServices {
    private SubgroupService subgroupService=new SubgroupService();
    private SubgroupModel subgroupModelDef=new SubgroupModel();

    public void addSubgroup(SubgroupModel subgroupModel){
        subgroupService.add(subgroupModel);
    }
    public void removeSubgroup(SubgroupModel subgroupModel){
        subgroupService.delete(subgroupModel);
    }
    public void updateSubgroup(SubgroupModel subgroupModel){
        subgroupService.update(subgroupModel);
    }
    public void viewSubgroup(){
        SubgroupModel subgroupModel=new SubgroupModel();
        List<SubgroupModel> subgroupModelList=subgroupService.getAll(subgroupModel);
        subgroupModelList.stream().forEach(subgroupModel1 -> System.out.println("Id Group: "+ subgroupModel1.getGroupModel().getId()+", Group name: "+subgroupModel1.getGroupModel().getName()+
                ", SubGroup Id: "+ subgroupModel1.getId()+ ", Subgroup name: "+ subgroupModel1.getName()));
    }
    public SubgroupModel getSubgroup(int id){
        SubgroupModel subgroupModel=new SubgroupModel();
        return subgroupService.findById(subgroupModel,id);
    }

    public List<SubgroupModel> getSubgroupModels(){
        return subgroupService.getAll(subgroupModelDef);
    }
}
