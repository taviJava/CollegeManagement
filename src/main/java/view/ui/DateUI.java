package view.ui;

import controller.*;
import model.dto.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class DateUI {
    private DateServices dateServices=new DateServices();
    private  ScheduleServices scheduleServices=new ScheduleServices();
    private    ProfessorServices professorServices=new ProfessorServices();
    private  CourseServices courseServices=new CourseServices();
    private ClassroomServices classroomServices=new ClassroomServices();
    private  SubgroupServices subgroupServices=new SubgroupServices();
    private  EvidenceUI evidenceUI=new EvidenceUI();
    private PrezentUI prezentUI=new PrezentUI();
    private   Scanner scanner=new Scanner(System.in);

    public void addOldSchedule(CourseModel courseModel){
        DateModel dateModel=new DateModel();
        ScheduleModel scheduleModel=addSchedule(courseModel);
        SubgroupModel subgroupModel=addSubGroup(scheduleModel);
        addDates(dateModel);
        ProfessorModel professorModel=addProf(courseModel);
        ClassroomModel classroomModel=addClass();
        dateModel.setProfessorModel(professorModel).setScheduleModel(scheduleModel).setClassroomModel(classroomModel).setSubgroupModel(subgroupModel);
        verifyDates(dateModel,professorModel,classroomModel,subgroupModel,dateModel.getDate(),dateModel.getStartTime(),dateModel.getEndTime());
    }

    public void addNewSchedule2(CourseModel courseModel){
        System.out.println("Now you must to add a Schedule dates to the Course");
        DateModel dateModel=new DateModel();
        addDates(dateModel);
        ScheduleModel scheduleModel=addSchedule(courseModel);
        ProfessorModel professorModel=addProf(courseModel);
        ClassroomModel classroomModel=addClass();
        SubgroupModel subgroupModel=addSubGroup(scheduleModel);
        dateModel.setProfessorModel(professorModel).setScheduleModel(scheduleModel).setClassroomModel(classroomModel).setSubgroupModel(subgroupModel);
        verifyDates(dateModel,professorModel,classroomModel,subgroupModel,dateModel.getDate(),dateModel.getStartTime(),dateModel.getEndTime());
    }

    private void verifyDates(DateModel dateModel,ProfessorModel professorModel,ClassroomModel classroomModel,SubgroupModel subgroupModel,Date date,Time startTime,Time endTime){
        if (professorDatesOccupated(professorModel,date,startTime,endTime)){
            if (classroomDatesOccupated(classroomModel,date,startTime,endTime)){
                if (subgroupDatesOccupated(subgroupModel,date,startTime,endTime)){
                    dateServices.add(dateModel);
                    evidenceUI.add(dateModel);
                }else{
                    System.out.println("The date could not be added because the subgroup has another course that overlaps on that date");
                }
            }else {
                System.out.println("The date could not be added because the classroom has another course that overlaps on that date");
            }
        }else {
            System.out.println("The date could not be added because the professor has another course that overlaps on that date");
        }
    }
    private ClassroomModel addClass(){
        classroomServices.viewClassRoom();
        System.out.println("Insert the id of the classroom that you want to add");
        int id=scanner.nextInt();
        scanner.nextLine();
        ClassroomModel classroomModel=classroomServices.getClassroom(id);
        return classroomModel;
    }

    private ProfessorModel addProf(CourseModel courseModel){
        List<ProfessorModel> professorModelList=courseModel.getProfessorModelList();
        professorModelList.stream().forEach(professorModel -> System.out.println("Id: "+professorModel.getId()+" Name "+professorModel.getLastName()+" "+professorModel.getFirstName()));
        System.out.println("Insert the id of the professor that you want to add");
        int id=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(id);
        return professorModel;
    }

    private ScheduleModel addSchedule(CourseModel courseModel){
        ScheduleModel scheduleModel=scheduleServices.getScheduleModelList().stream().filter(scheduleModel1 -> scheduleModel1.getCourseModel().getId()==courseModel.getId()).findFirst().get();

        return scheduleModel;
    }

    private void addDates(DateModel dateModel){
        System.out.println("Insert the date");
        String dateStr=scanner.nextLine();
        Date date=Date.valueOf(dateStr);
        System.out.println("Insert the start time");
        String startTimeStr=scanner.nextLine();
        Time startTime=Time.valueOf(startTimeStr);
        System.out.println("Insert the end time");
        String endTimeStr=scanner.nextLine();
        Time endTime=Time.valueOf(endTimeStr);
        dateModel.setDate(date).setEndTime(endTime).setStartTime(startTime);
    }

    private SubgroupModel addSubGroup(ScheduleModel scheduleModel){
        CourseModel courseModel=scheduleModel.getCourseModel();
        List<GroupModel> groupModelList=courseModel.getGroupModelList();
        groupModelList.stream().forEach(groupModel -> groupModel.getSubgroupModelList().stream().forEach(subgroupModel -> System.out.println(subgroupModel.getId()+ " "+ subgroupModel.getName())));
        System.out.println("Insert the id of the subgroup that you want to add");
        int id=scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(id);
        return subgroupModel;

    }

    public void viewDates(){
        int option=-1;
        while (option!=0){
            viewMenu();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                viewByCourse();
            }if (option==2){
                viewByProfessor();
            }if (option==3){
                viewByClass();
            }if (option==4){
                viewBySubGroupMenu();
            }
        }
    }
    private void viewBySubGroupMenu(){
        subgroupServices.viewSubgroup();
        System.out.println("Insert the id of the subgroup");
        int id=scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(id);
        viewBySubGroup(subgroupModel);
    }
    private void viewByCourse(){
        courseServices.viewCourse();
        System.out.println("Insert the id of the course");
        int id=scanner.nextInt();
        scanner.nextLine();
        List<ScheduleModel> scheduleModelList=scheduleServices.getScheduleModelList();
        ScheduleModel scheduleModel1= scheduleModelList.stream().findFirst().filter(scheduleModel -> scheduleModel.getCourseModel().getId()==id).get();
        List<DateModel> dateModelList=scheduleModel1.getDateModelList();
        dateModelList.stream().forEach(dateModel -> System.out.println(dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));
    }

    private void viewByClass(){
        classroomServices.viewClassRoom();
        System.out.println("Insert the id of the Classroom");
        int id =scanner.nextInt();
        scanner.nextLine();
        ClassroomModel classroomModel=classroomServices.getClassroom(id);
        List<DateModel> dateModelList=classroomModel.getDateModelList();
        dateModelList.stream().forEach(dateModel -> System.out.println(dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));

    }

    private void viewByProfessor(){
        professorServices.viewProfessors();
        System.out.println("Insert the id of the professor");
        int id=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(id);
        List<DateModel> dateModelList=professorModel.getDateModels();
        dateModelList.stream().forEach(dateModel -> System.out.println(dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));
    }
    public void viewBySubGroup(SubgroupModel subgroupModel){
        List<DateModel> dateModelList=subgroupModel.getDateModelList();
        dateModelList.stream().forEach(dateModel -> System.out.println(dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));

    }
    private void viewMenu(){
        System.out.println("1. View Dates by course");
        System.out.println("2. View Dates by professor");
        System.out.println("3. View Dates by clasroom");
        System.out.println("4. View Dates by subgroup");
        System.out.println("0. Exit");
    }
    public void remove(){
        viewDates();
        System.out.println("Insert the id of the date that you want to remove");
        int id=scanner.nextInt();
        scanner.nextLine();
        DateModel dateModel=dateServices.getDate(id);
        dateServices.delete(dateModel);
        evidenceUI.remove(dateModel);
    }

    public void removeAll(int id){
        DateModel dateModel=dateServices.getDate(id);
        dateServices.delete(dateModel);
        evidenceUI.remove(dateModel);
    }

    public void update(){
        viewDates();
        System.out.println("Insert the id of the date that you want to update");
        int id=scanner.nextInt();
        scanner.nextLine();
        DateModel dateModel=dateServices.getDate(id);
        int option=-1;
        while (option!=0){
            updateMenu();
            option=scanner.nextInt();
            scanner.nextLine();
            if (option==1){
                updateDate(dateModel);
                evidenceUI.updateDate(dateModel);
            }if (option==2){
                prezentUI.remove(dateModel.getEvidenceModel());
                updateCourseDate(dateModel);
                updateSubgroupDate(dateModel);
                evidenceUI.updateDateGroup(dateModel);
            }if (option==3){
                updateProfessorDate(dateModel);
                evidenceUI.updateDate(dateModel);
            }if (option==4){
                updateClassroomDate(dateModel);
                evidenceUI.updateDate(dateModel);
            }if (option==5){
                prezentUI.remove(dateModel.getEvidenceModel());
                updateSubgroupDate(dateModel);
                evidenceUI.updateDateGroup(dateModel);
            }
        }
        dateServices.update(dateModel);
    }
    private void updateDate(DateModel dateModel){
        System.out.println("Insert the date");
        String dateStr=scanner.nextLine();
        Date date=Date.valueOf(dateStr);
        System.out.println("Insert the start time");
        String startTimeStr=scanner.nextLine();
        Time startTime=Time.valueOf(startTimeStr);
        System.out.println("Insert the end time");
        String endTimeStr=scanner.nextLine();
        Time endTime=Time.valueOf(endTimeStr);
        dateModel.setDate(date).setStartTime(startTime).setEndTime(endTime);
    }

    private void updateCourseDate(DateModel dateModel){
        System.out.println("Insert the id of the new Course");
        int idCourse=scanner.nextInt();
        scanner.nextLine();
        CourseModel courseModel2=courseServices.getCourse(idCourse);
        ScheduleModel scheduleModel1=courseModel2.getScheduleModel();
        dateModel.setScheduleModel(scheduleModel1);
    }
    private void updateProfessorDate(DateModel dateModel){
        professorServices.viewProfessors();
        System.out.println("Insert the id of the new professor");
        int idProf=scanner.nextInt();
        scanner.nextLine();
        ProfessorModel professorModel=professorServices.getProfessor(idProf);
        dateModel.setProfessorModel(professorModel);
    }

    private void updateClassroomDate(DateModel dateModel){
        classroomServices.viewClassRoom();
        System.out.println("Insert the id of the Classroom");
        int id=scanner.nextInt();
        scanner.nextLine();
        ClassroomModel classroomModel=classroomServices.getClassroom(id);
        dateModel.setClassroomModel(classroomModel);
    }

    private void updateSubgroupDate(DateModel dateModel){
        ScheduleModel scheduleModel=dateModel.getScheduleModel();
        CourseModel courseModel=scheduleModel.getCourseModel();
        List<GroupModel> groupModelList=courseModel.getGroupModelList();
        groupModelList.stream().forEach(groupModel -> groupModel.getSubgroupModelList().stream().forEach(subgroupModel -> System.out.println(subgroupModel.getId()+ " "+ subgroupModel.getName())));
        System.out.println("Insert the id of the subgroup that you want to add");
        int id=scanner.nextInt();
        scanner.nextLine();
        SubgroupModel subgroupModel=subgroupServices.getSubgroup(id);
        dateModel.setSubgroupModel(subgroupModel);
    }
    private void updateMenu(){
        System.out.println("1. Update Date & Time");
        System.out.println("2. Update Schedule/Course");
        System.out.println("3. Update Professor");
        System.out.println("4. Update Classroom");
        System.out.println("5. Update Subgroup");
        System.out.println("0. Exit & Save");
    }

    private boolean professorDatesOccupated(ProfessorModel professorModel,Date date,Time startTime,Time endTime){
        System.out.println( professorModel.getDateModels().size());
        List<DateModel> dateModelList=professorModel.getDateModels();
        for (DateModel dateModel: dateModelList){
            if (dateServices.ifIsOccupated(date,startTime,endTime,dateModel)){
                return true;
            }
        }if (dateModelList.size()==0){
            return true;
        }
        return false;
    }
    private boolean classroomDatesOccupated(ClassroomModel classroomModel,Date date,Time startTime,Time endTime){
        List<DateModel> dateModelList=classroomModel.getDateModelList();
        for (DateModel dateModel: dateModelList){
            if (dateServices.ifIsOccupated(date,startTime,endTime,dateModel)){
                return true;
            }
        }if (dateModelList.size()==0){
            return true;
        }
        return false;
    }

    private boolean subgroupDatesOccupated(SubgroupModel subgroupModel,Date date,Time startTime,Time endTime){
        List<DateModel> dateModelList=subgroupModel.getDateModelList();
        for (DateModel dateModel: dateModelList){
            if (dateServices.ifIsOccupated(date,startTime,endTime,dateModel)){
                return true;
            }
        }if (dateModelList.size()==0){
            return true;
        }
        return false;
    }
}
