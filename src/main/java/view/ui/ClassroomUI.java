package view.ui;

import controller.ClassroomServices;
import model.dto.ClassroomModel;
import java.util.Scanner;

public class ClassroomUI {
    private Scanner scanner=new Scanner(System.in);
    private ClassroomServices classroomServices=new ClassroomServices();

    public void addClass(){
        ClassroomModel classroomModel=new ClassroomModel();
        System.out.println("Insert the name of the class");
        String name=scanner.nextLine();
        classroomModel.setName(name);
        classroomServices.addClassroom(classroomModel);
    }

    public void removeClass(){
        classroomServices.viewClassRoom();
        System.out.println("Insert the ID of the class that you want to remove");
        int id=scanner.nextInt();
        scanner.nextLine();
        ClassroomModel classroomModel=classroomServices.getClassroom(id);
        classroomServices.removeClassroom(classroomModel);
    }

    public void viewClass(){
        classroomServices.viewClassRoom();
    }

    public void updateScheduleClass(){  // de revazut daca o fac
        classroomServices.viewClassRoom();
        System.out.println("Insert the id of the class that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        ClassroomModel classroomModel=classroomServices.getClassroom(id);
        classroomServices.updateClassroom(classroomModel);
    }
}
