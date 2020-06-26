package model.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="schedule")
public class ScheduleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private CourseModel courseModel;
    @OneToMany
    @JoinColumn(name="schedule_id")
    private List<DateModel> dateModelList;



    public List<DateModel> getDateModelList() {
        return dateModelList;
    }

    public ScheduleModel setDateModelList(List<DateModel> dateModelList) {
        this.dateModelList = dateModelList;
        return this;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }

    public ScheduleModel setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;
        return this;
    }


    public int getId() {
        return id;
    }

    public ScheduleModel setId(int id) {
        this.id = id;
        return this;
    }


}
