package controller;

import model.dto.ScheduleModel;
import model.service.ClassroomService;
import model.service.ScheduleService;
import java.util.List;

public class ScheduleServices {
    private ScheduleModel scheduleModelDef=new ScheduleModel();
    private ScheduleService scheduleService=new ScheduleService();
    private ClassroomService classroomService=new ClassroomService();
    public void addSchedule(ScheduleModel scheduleModel){
        scheduleService.add(scheduleModel);
    }
    public void removeSchedule(ScheduleModel scheduleModel){
        scheduleService.delete(scheduleModel);
    }
    public void updateSchedule(ScheduleModel scheduleModel){
        scheduleService.update(scheduleModel);
    }
    public void viewSchedule(){
        ScheduleModel scheduleModel=new ScheduleModel();
        List<ScheduleModel> scheduleModelList=scheduleService.getAll(scheduleModel);
        scheduleModelList.stream().forEach(scheduleModel1 -> System.out.println("Id: "+scheduleModel1.getId()+" Schedule course name: "+ scheduleModel1.getCourseModel().getName()));
    }
    public List<ScheduleModel> getScheduleModelList(){
        List<ScheduleModel> scheduleModelList=scheduleService.getAll(scheduleModelDef);
        return scheduleModelList;
    }
    public ScheduleModel getSchedule(int id){
        ScheduleModel scheduleModel=new ScheduleModel();
        return scheduleService.findById(scheduleModel,id);

    }



}
