package view.ui;

import controller.GroupServices;
import controller.SubgroupServices;
import model.dto.GroupModel;
import model.dto.SubgroupModel;
import java.util.List;
import java.util.Scanner;

public class SubgroupUI {
    private SubgroupServices subgroupServices=new SubgroupServices();
    private  GroupServices groupServices=new GroupServices();
    private Scanner scanner=new Scanner(System.in);
    public void add(GroupModel groupModel){
        System.out.println("Insert the subgroup name");
        String name=scanner.nextLine();
        SubgroupModel subgroupModel=new SubgroupModel();
        subgroupModel.setGroupModel(groupModel).setName(name);
        subgroupServices.addSubgroup(subgroupModel);
    }

    public void remove(){
        int option=-1;
        while (option!=0){
            System.out.println("1. Remove Group/SubGroups");
            System.out.println("2. Remove SubGroup");
            System.out.println("0. Save & Exit");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                removeGroupSubGroups();
            }if (option==2){
                removeSubGroup();
            }
        }
    }

    private void removeSubGroup(){
        groupServices.viewGroup();
        System.out.println("Insert the id of the group");
        int idGroup=scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel=groupServices.getGroup(idGroup);
        List<SubgroupModel> subgroupModelList=groupModel.getSubgroupModelList();
        subgroupModelList.stream().forEach(subgroupModel -> System.out.println(subgroupModel.toString()));
        System.out.println("Insert the id of the subgroup that you want to remove");
        int idSubGroup=scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(idSubGroup);
        subgroupServices.removeSubgroup(subgroupModel);

    }

    private void removeGroupSubGroups(){
        groupServices.viewGroup();
        System.out.println("Insert the id of the group");
        int idGroup=scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel=groupServices.getGroup(idGroup);
        List<SubgroupModel> subgroupModelList=groupModel.getSubgroupModelList();
        for (int i=0;i<subgroupModelList.size();i++){
            SubgroupModel subgroupModel=subgroupModelList.get(i);
            subgroupServices.removeSubgroup(subgroupModel);
        }
        groupServices.removeGroup(groupModel);
    }
    public void viewSubGroups(){
        int option=-1;
        while (option!=0){
            System.out.println("1. View all subgroups");
            System.out.println("2. View subgroups by Group");
            System.out.println("0. Exit");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                subgroupServices.viewSubgroup();
            }if (option==2){
                groupServices.viewGroup();
                System.out.println("Insert the id of the group");
                int idGroup=scanner.nextInt();
                scanner.nextLine();
                GroupModel groupModel=groupServices.getGroup(idGroup);
                List<SubgroupModel> subgroupModelList=groupModel.getSubgroupModelList();
                subgroupModelList.stream().forEach(subgroupModel1 -> System.out.println("Id Group: "+ subgroupModel1.getGroupModel().getId()+", Group name: "+subgroupModel1.getGroupModel().getName()+
                        ", SubGroup Id: "+ subgroupModel1.getId()+ ", Subgroup name: "+ subgroupModel1.getName()));
            }
        }
    }

    public void update(){
        subgroupServices.viewSubgroup();
        System.out.println("Insert the id of the subgroup that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(id);
        int option=-1;
        while (option!=0){
            System.out.println("1. Update name of the subgroup");
            System.out.println("2. Change Group of the subgroup");
            System.out.println("0. Exit & Save");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                System.out.println("Insert the new name");
                String name=scanner.nextLine();
                subgroupModel.setName(name);
            }if(option==2){
                groupServices.viewGroup();
                System.out.println("Select the id of the new Group");
                int idGroup=scanner.nextInt();
                scanner.nextLine();
                GroupModel groupModel=groupServices.getGroup(idGroup);
                subgroupModel.setGroupModel(groupModel);
            }
        }
        subgroupServices.updateSubgroup(subgroupModel);
    }

}
