package model.dto;

import javax.persistence.CascadeType;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "evidence")
public class EvidenceModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    private SubgroupModel subgroupModel;
    @OneToOne(cascade = CascadeType.ALL)
    private CourseModel courseModel;
    @OneToOne(cascade = CascadeType.ALL)
    private ProfessorModel professorModel;
    @OneToMany
    @JoinColumn(name="evidence_id")
    private List<PrezentModel> prezentModelList;

    @OneToOne(cascade = CascadeType.ALL)
    private DateModel dateModel;

    public List<PrezentModel> getPrezentModelList() {
        return prezentModelList;
    }

    public EvidenceModel setPrezentModelList(List<PrezentModel> prezentModelList) {
        this.prezentModelList = prezentModelList;
        return this;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public SubgroupModel getSubgroupModel() {
        return subgroupModel;
    }

    public EvidenceModel setSubgroupModel(SubgroupModel subgroupModel) {
        this.subgroupModel = subgroupModel;
        return this;
    }

    public CourseModel getCourseModel() {
        return courseModel;
    }
    public EvidenceModel setCourseModel(CourseModel courseModel) {
        this.courseModel = courseModel;return this;
    }
    public ProfessorModel getProfessorModel() {
        return professorModel;
    }
    public EvidenceModel setProfessorModel(ProfessorModel professorModel) {
        this.professorModel = professorModel;return this;
    }
    public DateModel getDateModel() {
        return dateModel;
    }
    public EvidenceModel setDateModel(DateModel dateModel) {
        this.dateModel = dateModel;return this;
    }

    @Override
    public String toString() {
        return
                ""+prezentModelList
                ;
    }
}
