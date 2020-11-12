package Entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TimeTable implements Serializable {
    private HashMap<Day, HashMap<String[], LocalTime[]>> timeTable = new HashMap<>();
    public TimeTable(){
        Day[] dayValues = Day.values();
        LocalTime[] defaultTimings = new LocalTime[2];
        defaultTimings[0] = LocalTime.of(0, 0, 0);
        defaultTimings[1] = LocalTime.of(0, 0, 0);
        for(int i=0; i < 7; ++i){
            this.timeTable.put(dayValues[i], new HashMap<>());
        }
    }
    public boolean addLesson(String courseCode, int indexNum, Lesson[] lessons){
        for(Lesson l: lessons)
            System.out.println(l);
        for(Lesson l: lessons){
            System.out.println(l);
            Day day = l.getDay();
            LocalTime startTime = l.getStartTime();
            LocalTime endTime = l.getEndTime();
            String[] key = new String[] {courseCode, Integer.toString(indexNum)};
            timeTable.get(day).put(key, new LocalTime[]{startTime, endTime});
        }
        return true;
    }
    public boolean checkForClash(Lesson[] lessons){
        Day[] dayValues = Day.values();
        for(int j = 0; j < 7; ++j){
            Iterator<Map.Entry<String[], LocalTime[]>> it = this.timeTable.get(dayValues[j]).
                    entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String[], LocalTime[]> l = it.next();
                LocalTime lessonStartTime = l.getValue()[0];
                LocalTime lessonEndTime = l.getValue()[1];
                for(int k = 0; k < lessons.length; ++k){
                    LocalTime newCourseLessonStartTime = lessons[k].getStartTime();
                    LocalTime newCourseLessonEndTime = lessons[k].getEndTime();
                    if((newCourseLessonStartTime.compareTo(lessonEndTime) < 0) &&
                            (newCourseLessonEndTime.compareTo(lessonStartTime) > 0)) {
                        System.out.println("Time table clash with course code " + l.getKey());
                        return false;
                    }
                }
            }
        }
        System.out.println("No time table clash!");
        return true;
    }
    public void printTimeTable(){
        Day[] dayValues = Day.values();
        for(int i=0; i<7; ++i){
            Iterator<Map.Entry<String[], LocalTime[]>> it = this.timeTable.get(dayValues[i]).
                    entrySet().iterator();
            System.out.println(dayValues[i].toString() + ":");
            while(it.hasNext()) {
                Map.Entry<String[], LocalTime[]> l = it.next();
                LocalTime lessonStartTime = l.getValue()[0];
                LocalTime lessonEndTime = l.getValue()[1];
                System.out.println("Course Code: " + l.getKey()[0] + ", Index Number: " + l.getKey()[1] +
                        ", Timings: " + lessonStartTime + " - " + lessonEndTime);
            }
        }
    }
}
