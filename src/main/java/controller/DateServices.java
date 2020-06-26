package controller;

import model.dto.DateModel;
import model.service.DateService;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class DateServices {
    DateService dateService=new DateService();
    DateModel dateModel1=new DateModel();

    public void add(DateModel dateModel){
        dateService.add(dateModel);
    }

    public void delete(DateModel dateModel){
        dateService.delete(dateModel);
    }

    public void view(){
        List<DateModel> dateModelList=dateService.getAll(dateModel1);
        dateModelList.stream().forEach(dateModel -> System.out.println(dateModel.getId()+" Date: "+ dateModel.getDate()+" from: "+ dateModel.getStartTime()+" to: "+dateModel.getEndTime()+" Professor: "+dateModel.getProfessorModel().getId()+" "+
                dateModel.getProfessorModel().getFirstName()+" "+dateModel.getProfessorModel().getLastName()+" Subgroup: "+dateModel.getSubgroupModel().getName()+" Classroom: "+dateModel.getClassroomModel().getName()+" Course: "+dateModel.getScheduleModel().getCourseModel().getName()
        ));
    }
    public void update(DateModel dateModel){
        dateService.update(dateModel);
    }

    public DateModel getDate(int id){
        return dateService.findById(dateModel1,id);
    }
    public List<DateModel> dateModelsList(){
        List<DateModel> dateModelList = dateService.getAll(dateModel1);
        return dateModelList;
    }

    public boolean ifIsOccupated(Date date, Time startTime,Time endTime,DateModel dateModel){
        if (dateModel.getDate().equals(date)){
            if (dateModel.getStartTime().equals(startTime)||dateModel.getStartTime().before(startTime)&&dateModel.getEndTime().after(startTime)||
                    dateModel.getStartTime().after(startTime)&&dateModel.getStartTime().before(endTime)||dateModel.getStartTime().before(startTime)&&dateModel.getEndTime().equals(endTime)){
                return false;
            }
        }
        return true;
    }


}
