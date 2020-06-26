package controller;

import model.dto.GroupModel;
import model.service.GroupService;
import java.util.List;

public class GroupServices {
    private GroupService groupService=new GroupService();
    public void addGroup(GroupModel groupModel){
        groupService.add(groupModel);
    }
    public void removeGroup(GroupModel groupModel){
        groupService.delete(groupModel);

    }
    public void updateGroup(GroupModel groupModel){
        groupService.update(groupModel);

    }
    public void viewGroup(){
        GroupModel groupModel=new GroupModel();
        List<GroupModel> groupModelList=groupService.getAll(groupModel);
        groupModelList.stream().forEach(groupModel1 -> System.out.println("Group Id: "+groupModel1.getId()+"Group name "+groupModel1.getName()));

    }
    public GroupModel getGroup(int id){
        GroupModel groupModel=new GroupModel();
        GroupModel groupModel1=groupService.findById(groupModel,id);
        return groupModel1;
    }
}
