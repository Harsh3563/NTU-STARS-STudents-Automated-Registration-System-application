package Entity;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalTime;

public class Lesson implements Serializable {
    private LocalTime startTime;
    private LocalTime endTime;
    private Day day;
    private Duration duration;
    private String venue;
    private WeeklySchedule weeklySchedule;
    private LessonType lessonType;

    public Lesson(LocalTime startTime, LocalTime endTime, String day,
                  String venue, WeeklySchedule weeklySchedule, LessonType lessonType){
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = Day.valueOf(day);
        this.duration = Duration.between(endTime, startTime);
        this.venue = venue;
        this.weeklySchedule = weeklySchedule;
        this.lessonType = lessonType;
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

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        duration = Duration.between(endTime, startTime);
    }
    public void displayEveryDetail(){
        System.out.println("Start Time : " + startTime);
        System.out.println("End Time : " + endTime);
        System.out.println("Day : " + day.toString());
        System.out.println("Duration : " + duration);
        System.out.println("Venue : " + venue);
        System.out.println("Weekly Schedule : " + weeklySchedule);
        System.out.println("Lesson Type : " + lessonType);
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }

    public void setWeeklySchedule(WeeklySchedule weeklySchedule) {
        this.weeklySchedule = weeklySchedule;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }
}
