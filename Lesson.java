package Entity;

import java.time.LocalTime;

public class Lesson {
    private LocalTime startTime;
    private LocalTime endTime;
    enum Day{
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY,
        SUNDAY
    }
    private Day day;
    private int duration;

    public Lesson(LocalTime startTime, LocalTime endTime, String day, int duration){
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = Day.valueOf(day);
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
