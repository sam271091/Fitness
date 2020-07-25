package demo.com.fitness.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainViewModel extends AndroidViewModel {

    private static DataDatabase database;
    private LiveData<List<Event>> events;
    private LiveData<List<Teacher>> teachers;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = DataDatabase.getInstance(application);
        events   = database.dataDao().getAllEvents();
        teachers = database.dataDao().getAllTeachers();
    }

    public LiveData<List<Event>> getEvents() {
        return events;
    }

    public LiveData<List<Teacher>> getTeachers() {
        return teachers;
    }


    public Event getEventByID(int id){

        try {
            return new GetEventTask().execute(id).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void deleteAllEvents(){
        new DeleteEventsTask().execute();
    }

    public void insertEvent(Event event){
         new InsertEventTask().execute(event);
    }




    public Teacher getTeacherByName(String name){
        try {
            return new GetTeacherByNameTask().execute(name).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void deleteAllTeachers(){
        new DeleteAllTeachersTask().execute();
    }


    public void insertTeacher(Teacher teacher){
        new InsertTeacherTask().execute(teacher);
    }

    private static class InsertTeacherTask extends AsyncTask<Teacher,Void,Void>{
        @Override
        protected Void doInBackground(Teacher... teachers) {
            database.dataDao().insertTeacher(teachers[0]);
            return null;
        }
    }

    private static class DeleteAllTeachersTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.dataDao().deleteAllTeachers();
            return null;
        }
    }

    private static class GetTeacherByNameTask extends AsyncTask<String,Void,Teacher>{
        @Override
        protected Teacher doInBackground(String... strings) {

            return database.dataDao().getTeacherByName(strings[0]);
        }
    }


   private static class InsertEventTask extends AsyncTask<Event,Void,Void>{
       @Override
       protected Void doInBackground(Event... events) {
           database.dataDao().insertEvent(events[0]);
           return null;
       }
   }


    private static class DeleteEventsTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected Void doInBackground(Void... voids) {
            database.dataDao().deleteAllEvents();
            return null;
        }
    }

    private static class GetEventTask extends AsyncTask<Integer,Void,Event>{
        @Override
        protected Event doInBackground(Integer... integers) {

            if (integers != null && integers.length > 0){
               return database.dataDao().getEventById(integers[0]);
            }


            return null;
        }
    }


}
