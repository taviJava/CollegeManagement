package view.ui;

import controller.CourseServices;
import controller.DateServices;
import controller.ScheduleServices;
import model.dto.CourseModel;
import model.dto.ScheduleModel;

public class TestUi {
    ScheduleServices scheduleServices=new ScheduleServices();
    DateServices dateServices=new DateServices();
    CourseServices courseServices=new CourseServices();

    public void test(){
        CourseModel courseModel=courseServices.getCourse(17);
        System.out.println(courseModel.toString());
      //  ScheduleModel scheduleModel=courseModel.getScheduleModel();
        //scheduleServices.getSchedule(181).getDateModelList().stream().forEach(dateModel -> System.out.println(dateModel.getDate()));
    }
}
