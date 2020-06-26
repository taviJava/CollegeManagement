package model.dto;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="professor")
public class ProfessorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cnp;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "professorModelList")
    private List<CourseModel> courseModelList;
    @OneToMany
    @JoinColumn(name="professor_id")
    private List<DateModel> dateModels;
    @OneToOne
    @JoinColumn(name = "evidence_id")
    private EvidenceModel evidenceModel;



    public List<DateModel> getDateModels() {
        return dateModels;
    }


    public EvidenceModel getEvidenceModel() {
        return evidenceModel;
    }

    public ProfessorModel setEvidenceModel(EvidenceModel evidenceModel) {
        this.evidenceModel = evidenceModel;
        return this;
    }

    public ProfessorModel setDateModels(List<DateModel> dateModels) {
        this.dateModels = dateModels;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public ProfessorModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ProfessorModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public List<CourseModel> getCourseModelList() {
        return courseModelList;
    }

    public ProfessorModel setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
        return this;
    }

    public int getId() {
        return id;
    }

    public ProfessorModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getCnp() {
        return cnp;
    }

    public ProfessorModel setCnp(String cnp) {
        this.cnp = cnp;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public ProfessorModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public ProfessorModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

}
