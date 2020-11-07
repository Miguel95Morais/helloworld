package pt.iade.helloworldEIT1.models;

public class CurricularUnit {
    private String name;
    private double grade;
    private int semester;
    private int ects;

    public CurricularUnit(String name, double grade, int semester, int ects) {
        this.name = name;
        this.grade = grade;
        this.semester = semester;
        this.ects = ects;
    }

    public String getName() {
        return name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getSemester() {
        return semester;
    }

    public int getEcts() {
        return ects;
    }

    public boolean isApproved() {
        if (this.grade > 9.5) {
            return true;
        } else {
            return false;
        }
    }

}