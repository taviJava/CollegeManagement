package controller;

import model.dto.ClassroomModel;
import model.service.ClassroomService;
import java.util.List;

public class ClassroomServices {
    private ClassroomService classroomService=new ClassroomService();
    public void addClassroom(ClassroomModel classroomModel){
        classroomService.add(classroomModel);
    }
    public void removeClassroom(ClassroomModel classroomModel){
        classroomService.delete(classroomModel);
    }
    public void updateClassroom(ClassroomModel classroomModel){
        classroomService.update(classroomModel);
    }

    public void viewClassRoom(){
        ClassroomModel classroomModel=new ClassroomModel();
        List<ClassroomModel> classroomModelList=classroomService.getAll(classroomModel);
        classroomModelList.stream().forEach(classroomModel1 -> System.out.println("Classroom id: "+classroomModel1.getId()+" Classroom name: "+ classroomModel1.getName()));
    }

    public ClassroomModel getClassroom(int id){
        ClassroomModel classroomModel=new ClassroomModel();
        return classroomService.findById(classroomModel,id);
    }
}
