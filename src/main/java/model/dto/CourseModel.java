package model.dto;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="course")
public class CourseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="course_professors", catalog = "collegemanagement",
            joinColumns = {
                    @JoinColumn(name="course_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="professor_id", nullable = false, updatable = false)
            })
    private List<ProfessorModel> professorModelList;
    @OneToOne
    @JoinColumn(name = "schedule_id")
    private ScheduleModel scheduleModel;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="course_group", catalog = "collegemanagement",
            joinColumns = {
                    @JoinColumn(name="course_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="group_id", nullable = false, updatable = false)
            })
    private List<GroupModel> groupModelList;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name="course_student", catalog = "collegemanagement",
            joinColumns = {
                    @JoinColumn(name="course_id", nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name="student_id", nullable = false, updatable = false)
            })

    private List<StudentModel> studentModelList;

    @OneToOne
    @JoinColumn(name = "evidence_id")
    private EvidenceModel evidenceModel;

    public EvidenceModel getEvidenceModel() {
        return evidenceModel;
    }

    public CourseModel setEvidenceModel(EvidenceModel evidenceModel) {
        this.evidenceModel = evidenceModel;
        return this;
    }

    public ScheduleModel getScheduleModel() {
        return scheduleModel;
    }

    public CourseModel setScheduleModel(ScheduleModel scheduleModel) {
        this.scheduleModel = scheduleModel;
        return this;
    }

    public List<StudentModel> getStudentModelList() {
        return studentModelList;
    }

    public CourseModel setStudentModelList(List<StudentModel> studentModelList) {
        this.studentModelList = studentModelList;
        return this;
    }

    public List<GroupModel> getGroupModelList() {
        return groupModelList;
    }

    public CourseModel setGroupModelList(List<GroupModel> groupModelList) {
        this.groupModelList = groupModelList;
        return this;
    }

    public int getId() {
        return id;
    }

    public CourseModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public CourseModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<ProfessorModel> getProfessorModelList() {
        return professorModelList;
    }

    public CourseModel setProfessorModelList(List<ProfessorModel> professorModelList) {
        this.professorModelList = professorModelList;
        return this;
    }



}
