package model.dto;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="date123")
public class DateModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private Date date;
    private Time startTime;
    private Time endTime;
    @ManyToOne
    @JoinColumn(name="schedule_id")
    private ScheduleModel scheduleModel;
    @ManyToOne
    @JoinColumn(name="professor_id")
    private ProfessorModel professorModel;
    @OneToOne
    @JoinColumn(name="evidence_id")
    private EvidenceModel evidenceModel;
    @ManyToOne
    @JoinColumn(name="classroom_id")
    private ClassroomModel classroomModel;

    @ManyToOne
    @JoinColumn(name="subgroup_id")
    private SubgroupModel subgroupModel;

    public SubgroupModel getSubgroupModel() {
        return subgroupModel;
    }

    public DateModel setSubgroupModel(SubgroupModel subgroupModel) {
        this.subgroupModel = subgroupModel;
        return this;
    }

    public EvidenceModel getEvidenceModel() {
        return evidenceModel;
    }

    public DateModel setEvidenceModel(EvidenceModel evidenceModel) {
        this.evidenceModel = evidenceModel;
        return this;
    }

    public ClassroomModel getClassroomModel() {
        return classroomModel;
    }

    public DateModel setClassroomModel(ClassroomModel classroomModel) {
        this.classroomModel = classroomModel;
        return this;
    }

    public int getId() {
        return id;
    }

    public DateModel setId(int id) {
        this.id = id;
        return this;
    }

    public Date getDate() {
        return date;
    }

    public DateModel setDate(Date date) {
        this.date = date;
        return this;
    }

    public Time getStartTime() {
        return startTime;
    }

    public DateModel setStartTime(Time startTime) {
        this.startTime = startTime;
        return this;
    }

    public Time getEndTime() {
        return endTime;
    }

    public DateModel setEndTime(Time endTime) {
        this.endTime = endTime;
        return this;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public DateModel setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
        return this;
    }

    public ProfessorModel getProfessorModel() {
        return professorModel;
    }

    public DateModel setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;
        return this;
    }
}
