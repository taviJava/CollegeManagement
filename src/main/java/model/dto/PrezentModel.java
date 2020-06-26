package model.dto;

import javax.persistence.*;

@Entity
@Table(name = "prezentm")
public class PrezentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private boolean isPresent;
    @OneToOne
    @JoinColumn(name = "student_id")
    private StudentModel studentModel;
    @ManyToOne
    @JoinColumn(name="evidence_id")
    private EvidenceModel evidenceModel;

    public int getId() {
        return id;
    }

    public PrezentModel setId(int id) {
        this.id = id;
        return this;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public PrezentModel setPresent(boolean present) {
        isPresent = present;
        return this;
    }

    public StudentModel getStudentModel() {
        return studentModel;
    }

    public PrezentModel setStudentModel(StudentModel studentModel) {
        this.studentModel = studentModel;
        return this;
    }

    public EvidenceModel getEvidenceModel() {
        return evidenceModel;
    }

    public PrezentModel setEvidenceModel(EvidenceModel evidenceModel) {
        this.evidenceModel = evidenceModel;
        return this;
    }

    @Override
    public String toString() {
        return "Prezent: " +
                "id: " + id +
                ", isPresent: " + isPresent +
                ", studentModel: " + studentModel;
    }
}
