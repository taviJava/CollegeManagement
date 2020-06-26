package controller;

import model.dto.CourseModel;
import model.service.CourseService;
import java.util.List;

public class CourseServices {
    private CourseService courseService=new CourseService();
    public void addCourse(CourseModel courseModel){
        courseService.add(courseModel);
    }
    public void removeCourse(CourseModel courseModel){
        courseService.delete(courseModel);
    }
    public void updateCourse(CourseModel courseModel){
        courseService.update(courseModel);
    }

    public void viewCourse(){
        CourseModel courseModel=new CourseModel();
        List<CourseModel> classroomModelList=courseService.getAll(courseModel);
        classroomModelList.stream().forEach(courseModel1 -> System.out.println("Course id: "+courseModel1.getId()+" name: "+ courseModel1.getName()+" description: "+courseModel1.getDescription()));
    }
    public CourseModel getCourse(int id){
        CourseModel courseModel=new CourseModel();
        return courseService.findById(courseModel,id);
    }

    public boolean courseListEmpty(){
        CourseModel courseModel=new CourseModel();
        List<CourseModel> courseModelList=courseService.getAll(courseModel);
        if (courseModelList.size()>0){
            return true;
        }else {
            System.out.println("You must first add courses ");
        }
        return false;
    }
}
