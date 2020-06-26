package view.ui;

import controller.GroupServices;
import model.dto.GroupModel;
import java.util.Scanner;

public class GroupUI {

    private  GroupServices groupServices=new GroupServices();
    private Scanner scanner=new Scanner(System.in);
    private  SubgroupUI subgroupUI=new SubgroupUI();


    public void add(){
        int option=-1;
        while (option!=0){
            System.out.println("1. Add new Group and Subgroups");
            System.out.println("2. Add Subgroups to an existing Group");
            System.out.println("0. Exit & Save");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                addNewGroup();
            }if (option==2){
                addSubGroupToExistingGroup();
            }
        }


    }

    private void addNewGroup(){
        System.out.println("Insert the name of the group");
        String name=scanner.nextLine();
        GroupModel groupModel=new GroupModel();
        groupModel.setName(name);
        groupServices.addGroup(groupModel);
        subgroupUI.add(groupModel);
        int option=-1;
        while (option!=0){
            System.out.println("1. Add another Subgroup");
            System.out.println("0. Exit & Save");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                subgroupUI.add(groupModel);
            }
        }
    }

    private void addSubGroupToExistingGroup(){
        viewGroups();
        System.out.println("Insert the id of the Group");
        int id=scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel=groupServices.getGroup(id);
        subgroupUI.add(groupModel);
    }

    public void remove(){
        subgroupUI.remove();
    }

    public void viewGroups(){
        subgroupUI.viewSubGroups();
    }

    public void update(){
        viewGroups();
        System.out.println("Insert the id of the group that you want to update");
        int id =scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel=groupServices.getGroup(id);
        int option=-1;
        while (option!=0){
            menuUpdate();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                System.out.println("Insert the new name");
                String name= scanner.nextLine();
                groupModel.setName(name);
            }if (option==2){
                subgroupUI.update();
            }
        }

        groupServices.updateGroup(groupModel);
    }

    private void menuUpdate(){
        System.out.println("1. Update name of the group");
        System.out.println("2. Update subGroups");
    }
}
