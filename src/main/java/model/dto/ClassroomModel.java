package model.dto;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="classroom")
public class ClassroomModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany
    @JoinColumn(name="classroom_id")
    private List<DateModel>  dateModelList;


    public int getId() {
        return id;
    }

    public ClassroomModel setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ClassroomModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<DateModel> getDateModelList() {
        return dateModelList;
    }

    public ClassroomModel setDateModelList(List<DateModel> dateModelList) {
        this.dateModelList = dateModelList;
        return this;
    }


}
