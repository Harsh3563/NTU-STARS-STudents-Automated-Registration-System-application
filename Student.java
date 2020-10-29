package Entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Student implements Serializable {
    private String name;
    private String matricNumber;
    private String nationality;
    private char gender;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private IndexGroup[] indicesRegsitered;
    private IndexGroup[] indicesWaiting;
    private int AUsRegistered;

    public Student(String name, String matricNumber, String nationality, char gender, int AUsRegistered) {
        this.name = name;
        this.matricNumber = matricNumber;
        this.nationality = nationality;
        this.gender = gender;
        this.AUsRegistered = AUsRegistered;
        startDate = LocalDate.of(2020, 10, 23);
        endDate = LocalDate.of(2020, 11, 7);
        startTime = LocalTime.of(10, 0, 0);
        endTime = LocalTime.of(22, 0, 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMatricNumber() {
        return matricNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public int getAUsRegistered() {
        return AUsRegistered;
    }

    public void setAUsRegistered(int AUsRegistered) {
        this.AUsRegistered = AUsRegistered;
    }
    public static Student downcast(Object object){
        return (Student)(object);
    }
    public boolean equals(Student s){
        return (this.matricNumber.equals(s.getMatricNumber()));
    }

    public void displayDetails() {
        System.out.printf("%-15s %-7s %-15s\n", this.name, this.gender, this.nationality);
    }
}
