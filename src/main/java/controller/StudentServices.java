package controller;

import model.dto.StudentModel;
import model.service.StudentService;
import java.util.List;

public class StudentServices {
    StudentService studentService=new StudentService();
    StudentModel studentModelDef=new StudentModel();
    public void addStudent(StudentModel studentModel){
        studentService.add(studentModel);
    }
    public void removeStudent(StudentModel studentModel){
        studentService.delete(studentModel);
    }
    public void updateStudent(StudentModel studentModel){
        studentService.update(studentModel);

    }
    public void viewStudent(){
        StudentModel studentModel=new StudentModel();
        List<StudentModel> studentModelList=studentService.getAll(studentModel);
        studentModelList.stream().forEach(studentModel1 -> System.out.println(studentModel1.toString()));
    }

    public StudentModel getStudent(int id){
        StudentModel studentModel=new StudentModel();
        return studentService.findById(studentModel,id);
    }

    public boolean ifExist(String username, String password){
        StudentModel studentModel=new StudentModel();
        List<StudentModel> studentModelList=studentService.getAll(studentModel);
        for (StudentModel student: studentModelList){
            if (student.getPassword().equals(password)&&student.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public List<StudentModel> getStudentModels(){
        List<StudentModel> studentModelList=studentService.getAll(studentModelDef);
        return  studentModelList;
    }
}
