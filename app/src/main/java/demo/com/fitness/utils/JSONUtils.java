package demo.com.fitness.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import demo.com.fitness.data.Event;
import demo.com.fitness.data.Teacher;

public class JSONUtils {

    //Events
    private static final String KEY_NAME = "name";
    private static final String KEY_DESCRIPTION = "description";
    private static final String KEY_PLACE = "place";
    private static final String KEY_TEACHER = "teacher_v2";
    private static final String KEY_STARTTIME = "startTime";
    private static final String KEY_ENDTIME = "endTime";
    private static final String KEY_WEEKDAY = "weekDay";

    //Teacher
    private static final String KEY_SHORT_NAME = "short_name";
    private static final String KEY_TEACHER_NAME = "name";
    private static final String KEY_POSITION = "position";
    private static final String KEY_IMAGE_URL = "imageUrl";


    public static ArrayList<Event> getEventsFromJSON(JSONArray jsonArray){

        ArrayList<Event> result = new ArrayList<>();

        try {
            if (jsonArray == null){
                return result;
            }


            for (int i = 0;i< jsonArray.length();i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String name = jsonObject.getString(KEY_NAME);
                String description = jsonObject.getString(KEY_DESCRIPTION);
                String place = jsonObject.getString(KEY_PLACE);
                JSONObject teacherJson = jsonObject.getJSONObject(KEY_TEACHER);

                String teacherShortName = teacherJson.getString(KEY_SHORT_NAME);
                String teacherName  = teacherJson.getString(KEY_TEACHER_NAME);
                String teacherPosition = teacherJson.getString(KEY_POSITION);
                String teacherImgURL  = teacherJson.getString(KEY_IMAGE_URL);

                Teacher teacher = new Teacher(teacherShortName,teacherName,teacherPosition,teacherImgURL);

                String startTime = jsonObject.getString(KEY_STARTTIME);
                String endTime = jsonObject.getString(KEY_ENDTIME);
                int weekDay = jsonObject.getInt(KEY_WEEKDAY);

                Event event = new Event(name,description,place,teacher,startTime,endTime,weekDay);

                result.add(event);


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return result;
    }


}
