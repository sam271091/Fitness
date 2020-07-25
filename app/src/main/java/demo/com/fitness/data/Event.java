package demo.com.fitness.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "events")
public class Event {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;
    private String place;
    private String teacher;
    private String startTime;
    private String endTime;
    private int weekDay;
    @Ignore
    private Teacher teacherObj;



    public Event(int id, String name, String description, String place, String teacher, String startTime, String endTime, int weekDay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.teacher = teacher;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekDay = weekDay;
    }

    @Ignore
    public Event( String name, String description, String place, Teacher teacherObj, String startTime, String endTime, int weekDay) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.teacher = teacherObj.getName();
        this.teacherObj = teacherObj;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekDay = weekDay;
    }

    public Teacher getTeacherObj() {
        return teacherObj;
    }

    public void setTeacherObj(Teacher teacherObj) {
        this.teacherObj = teacherObj;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }
}
