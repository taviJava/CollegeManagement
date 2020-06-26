package model.dto;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="student")
public class StudentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String cnp;
    private String lastName;
    private String firstName;
    private String username;
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    private PrezentModel prezentModel;
    @ManyToOne
    @JoinColumn(name="subgroup_id")
    private SubgroupModel subgroupModel;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "studentModelList")
    private List<CourseModel> courseModelList;


    public PrezentModel getPrezentModel() {
        return prezentModel;
    }

    public StudentModel setPrezentModel(PrezentModel prezentModel) {
        this.prezentModel = prezentModel;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public StudentModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StudentModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public int getId() {
        return id;
    }

    public StudentModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getCnp() {
        return cnp;
    }

    public StudentModel setCnp(String cnp) {
        this.cnp = cnp;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public SubgroupModel getSubgroupModel() {
        return subgroupModel;
    }

    public StudentModel setSubgroupModel(SubgroupModel subgroupModel) {
        this.subgroupModel = subgroupModel;
        return this;
    }

    public List<CourseModel> getCourseModelList() {
        return courseModelList;
    }

    public StudentModel setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
        return this;
    }

    @Override
    public String toString() {
        return "Student : " +
                "id: " + id +
                ", cnp: " + cnp + '\'' +
                ", lastName: " + lastName + '\'' +
                ", firstName: " + firstName + '\''+
                "Subgroup: "+ subgroupModel.getName();
    }
}
