package view.ui;

import controller.CourseServices;
import controller.ScheduleServices;
import model.dto.CourseModel;
import model.dto.ScheduleModel;
import java.util.Scanner;

public class ScheduleUI {

    private  ScheduleServices scheduleServices=new ScheduleServices();
    private  Scanner scanner=new Scanner(System.in);
    private  CourseServices courseServices=new CourseServices();
    private  DateUI dateUI=new DateUI();

    public void addSchedule() {
        courseServices.viewCourse();
        System.out.println("Insert the id of the course that you want to add the Dates");
        int id=scanner.nextInt();
        CourseModel courseModel=courseServices.getCourse(id);
        scanner.nextLine();
        dateUI.addOldSchedule(courseModel);
    }



    public void addNewSchedule(CourseModel courseModel){
        ScheduleModel scheduleModel=new ScheduleModel();
        if (courseModel.getScheduleModel()==null) {
            scheduleModel.setCourseModel(courseModel);
            scheduleServices.addSchedule(scheduleModel);
            dateUI.addNewSchedule2(courseModel);
        }else{
            System.out.println(" Sorry, the selected course has already a schedule assigned");

        }
    }

    public void viewSchedule(){
        dateUI.viewDates();
    }

    public void removeScheduleDates(){
        dateUI.remove();
    }


    public void deleteAll(CourseModel courseModel){
        ScheduleModel scheduleModel=scheduleServices.getScheduleModelList().stream().findFirst().filter(scheduleModel1 -> scheduleModel1.getCourseModel()==courseModel).get();
        for (int i=0;i<scheduleModel.getDateModelList().size();i++){
            int idDate=scheduleModel.getDateModelList().get(i).getId();
            dateUI.removeAll(idDate);
        }
        scheduleServices.removeSchedule(scheduleModel);
    }

    public void updateSchedule() {
        dateUI.update();
    }
}
