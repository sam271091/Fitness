package demo.com.fitness.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DataDao {

    //Teachers
    @Query("SELECT * FROM teachers")
    LiveData<List<Teacher>> getAllTeachers();


    @Query("SELECT * FROM teachers WHERE id == :id")
    Teacher getTeacherById(int id);

    @Query("SELECT * FROM teachers WHERE name == :name")
    Teacher getTeacherByName(String name);

    @Query("DELETE FROM teachers")
    void deleteAllTeachers();

    @Insert
    void insertTeacher(Teacher teacher);

    //Events
    @Query("SELECT * FROM events")
    LiveData<List<Event>> getAllEvents();

    @Query("SELECT * FROM events WHERE id == :id")
    Event getEventById(int id);


    @Query("DELETE FROM events")
    void deleteAllEvents();

    @Insert
    void insertEvent(Event event);




}
