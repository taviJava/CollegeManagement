package model.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="subgroup")
public class SubgroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @ManyToOne
    @JoinColumn(name="group_id")
    private GroupModel groupModel;
    @OneToMany
    @JoinColumn(name="subgroup_id")
    private List<StudentModel> studentModelList;

    @OneToOne
    @JoinColumn(name = "evidence_id")
    private EvidenceModel evidenceModel;
    @OneToMany
    @JoinColumn(name = "subgroup_id")
    private List<DateModel> dateModelList;


    public List<DateModel> getDateModelList() {
        return dateModelList;
    }

    public SubgroupModel setDateModelList(List<DateModel> dateModelList) {
        this.dateModelList = dateModelList;
        return this;
    }

    public List<StudentModel> getStudentModelList() {
        return studentModelList;
    }

    public SubgroupModel setStudentModelList(List<StudentModel> studentModelList) {
        this.studentModelList = studentModelList;
        return this;
    }

    public EvidenceModel getEvidenceModel() {
        return evidenceModel;
    }

    public SubgroupModel setEvidenceModel(EvidenceModel evidenceModel) {
        this.evidenceModel = evidenceModel;
        return this;
    }

    public int getId() {
        return id;
    }

    public SubgroupModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SubgroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    public SubgroupModel setGroupModel(GroupModel groupModel) {
        this.groupModel = groupModel;
        return this;
    }

}
