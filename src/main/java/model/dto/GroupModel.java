package model.dto;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="group123")
public class GroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name="group_id")
    private List<SubgroupModel> subgroupModelList;
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "groupModelList")
    private List<CourseModel> courseModelList;




    public List<CourseModel> getCourseModelList() {
        return courseModelList;
    }

    public GroupModel setCourseModelList(List<CourseModel> courseModelList) {
        this.courseModelList = courseModelList;
        return this;
    }

    public int getId() {
        return id;
    }

    public GroupModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public GroupModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<SubgroupModel> getSubgroupModelList() {
        return subgroupModelList;
    }

    public GroupModel setSubgroupModelList(List<SubgroupModel> subgroupModelList) {
        this.subgroupModelList = subgroupModelList;
        return this;
    }

}
