package view.ui;

import controller.*;
import model.dto.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CourseUI {
    private Scanner scanner = new Scanner(System.in);
    private CourseServices courseServices = new CourseServices();
    private  ProfessorServices professorServices = new ProfessorServices();
    private  GroupServices groupServices = new GroupServices();
    private  ScheduleUI scheduleUI=new ScheduleUI();

    public void addCourse() {
        CourseModel courseModel = new CourseModel();
        addCourseBasic(courseModel);
        int option = -1;
        while (option != 0) {
            addCourseMenu();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                addProfesors(courseModel);
            }
            if (option == 2) {
                addGroupToCourse(courseModel);
            }
        }
        courseServices.addCourse(courseModel);
        scheduleUI.addNewSchedule(courseModel);
    }

    private void addProfesors(CourseModel courseModel) {
        professorServices.viewProfessors();
        System.out.println("Insert the id of the professor that you want to add");
        int id = scanner.nextInt();
        ProfessorModel professorModel = professorServices.getProfessor(id);
        List<ProfessorModel> professorModelList = courseModel.getProfessorModelList();
        professorModelList.add(professorModel);
        courseModel.setProfessorModelList(professorModelList);
    }

    private void addCourseBasic(CourseModel courseModel) {
        List<ProfessorModel> professorModelListDef = new ArrayList<>();
        List<GroupModel> groupModelList = new ArrayList<>();
        List<StudentModel> studentModelListDef = new ArrayList<>();
        System.out.println("Insert the course name");
        String name = scanner.nextLine();
        System.out.println("Insert the course description");
        String description = scanner.nextLine();
        courseModel.setName(name).setProfessorModelList(professorModelListDef).setDescription(description).setGroupModelList(groupModelList).setStudentModelList(studentModelListDef);
    }

    private void addCourseMenu() {
        System.out.println("1. Add professors to course");
        System.out.println("2. Add Groups to course");
        System.out.println("0. Exit & Save");
    }


    private void addStudentsToCourse(CourseModel courseModel,GroupModel groupModel) {
        List<SubgroupModel> subgroupModelList=groupModel.getSubgroupModelList();
        for (SubgroupModel subgroupModel:subgroupModelList){
            List<StudentModel> studentModelList=subgroupModel.getStudentModelList();
            if (studentModelList.size()>0){
                for (StudentModel studentModel:studentModelList){
                    List<StudentModel> studentModels=courseModel.getStudentModelList();
                    studentModels.add(studentModel);
                    courseModel.setStudentModelList(studentModels);
                }
            }
        }
    }

    private void addGroupToCourse(CourseModel courseModel) {
        groupServices.viewGroup();
        System.out.println("Insert the id of the group that you want to add ");
        int id = scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel = groupServices.getGroup(id);
        addStudentsToCourse(courseModel,groupModel);
        List<GroupModel> groupModelList = courseModel.getGroupModelList();
        groupModelList.add(groupModel);
        courseModel.setGroupModelList(groupModelList);
    }

    private void removeAnCourse() {
        courseServices.viewCourse();
        System.out.println("Insert the id of the course that you want to remove");
        int id = scanner.nextInt();
        scanner.nextLine();
        CourseModel courseModel = courseServices.getCourse(id);
        courseServices.removeCourse(courseModel);
        scheduleUI.deleteAll(courseModel);

    }

    public void removeCourse(){
        int option=-1;
        while (option!=0){
            System.out.println("1. Remove course");
            System.out.println("2. Remove dates from course");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                removeAnCourse();
            }if(option==2){
                scheduleUI.removeScheduleDates();
            }
        }
    }

    public void viewCourses() {
        int option=-1;
        while (option!=0){
            System.out.println("1. View Courses");
            System.out.println("2. View Course Dates informations");
            System.out.println("0. Exit");
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                courseServices.viewCourse();
            }if (option==2){
                scheduleUI.viewSchedule();
            }
        }

    }

    public void updateCourse() {
        courseServices.viewCourse();
        System.out.println("Insert the id of the course that you want to change information");
        int idCourse = scanner.nextInt();
        scanner.nextLine();
        CourseModel courseModel = courseServices.getCourse(idCourse);
        int option = -1;
        while (option != 0) {
            updateMenu();
            option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                updateProfesor(courseModel);
            }if (option==2){
                updateGroup(courseModel);
            }if (option==3){
                scheduleUI.updateSchedule();
            }if (option==4){
                System.out.println("Insert the new name");
                String name=scanner.nextLine();
                courseModel.setName(name);
                courseServices.updateCourse(courseModel);
            }
            if (option == 5) {
                System.out.println("Insert the new description");
                String description=scanner.nextLine();
                courseModel.setDescription(description);
                courseServices.updateCourse(courseModel);
            }
        }
    }

    private void updateMenu() {
        System.out.println("1. Update professors list");
        System.out.println("2. Update the groups list");
        System.out.println("3. Update Course dates");
        System.out.println("4. Update the course name ");
        System.out.println("5. Update the description of the course");
        System.out.println("0. Exit & Save");
    }

    private void removeProfesors(CourseModel courseModel) {
        List<ProfessorModel> professorModelList = courseModel.getProfessorModelList();
        professorModelList.stream().forEach(professorModel -> System.out.println(professorModel.toString()));
        System.out.println("Insert the id of the professor that you want to remove from the course");
        int id = scanner.nextInt();
        scanner.nextLine();
        professorModelList.removeIf(professorModel1 -> professorModel1.getId() == id);
        courseModel.setProfessorModelList(professorModelList);
    }

    private void removeStudents(CourseModel courseModel,GroupModel groupModel) {
        List<SubgroupModel> subgroupModelList= groupModel.getSubgroupModelList();
        for (SubgroupModel subgroupModel:subgroupModelList){
            List<StudentModel> studentModelList=subgroupModel.getStudentModelList();
            if (studentModelList.size()>0){
                List<StudentModel> studentModels=courseModel.getStudentModelList();
                for (StudentModel studentModel:studentModelList){
                    studentModels.removeIf(studentModel1 -> studentModel1.getId()==studentModel.getId());
                }
                courseModel.setStudentModelList(studentModels);
            }
        }
    }

    private void removeGroupCourse(CourseModel courseModel){
        List<GroupModel> groupModelList=courseModel.getGroupModelList();
        groupModelList.stream().forEach(groupModel1 -> System.out.println("Group Id: "+groupModel1.getId()+"Group name: "+groupModel1.getName()));
        System.out.println("Insert the id of the Group that you want to remove");
        int id = scanner.nextInt();
        scanner.nextLine();
        GroupModel groupModel1=groupServices.getGroup(id);
        removeStudents(courseModel,groupModel1);
        groupModelList.removeIf(groupModel -> groupModel.getId()==id);
        courseModel.setGroupModelList(groupModelList);
    }



    private void updateProfesor(CourseModel courseModel) {
        int opt = -1;
        while (opt != 0) {
            System.out.println("1. Add Professor to list");
            System.out.println("2. Remove professor form the list");
            System.out.println("0. Exit & Save");
            opt = scanner.nextInt();
            scanner.nextLine();
            if (opt == 1) {
                addProfesors(courseModel);
            }
            if (opt == 2) {
                removeProfesors(courseModel);
            }

        }
        courseServices.updateCourse(courseModel);
    }

    private void updateGroup(CourseModel courseModel){
        int opt=-1;
        while (opt!=0){
            System.out.println("1. Add group to list");
            System.out.println("2. Remove group from the list");
            System.out.println("Exit & Save");
            opt = scanner.nextInt();
            scanner.nextLine();
            if (opt==1){
                addGroupToCourse(courseModel);
            }if (opt==2){
                removeGroupCourse(courseModel);
            }
        }
        courseServices.updateCourse(courseModel);
    }
}