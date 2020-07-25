package demo.com.fitness.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {

    private static final String BaseURL = "https://sample.fitnesskit-admin.ru/schedule/get_group_lessons_v2/1/";



    public static class JSONLoader extends AsyncTaskLoader<JSONArray>{

        private Bundle bundle;

        public JSONLoader(@NonNull Context context, Bundle bundle) {
            super(context);
            this.bundle = bundle;
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();

            forceLoad();
        }

        @Nullable
        @Override
        public JSONArray loadInBackground() {

            if (bundle == null){
                return null;
            }

            URL url = null;

            try {
                url = new URL(BaseURL);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            JSONArray result = null;


            if (url == null){
                return null;
            }

            HttpsURLConnection connection = null;

            try {
               connection = (HttpsURLConnection) url.openConnection();
               InputStream inputStream = connection.getInputStream();
               InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
               BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

               String reader = bufferedReader.readLine();

               StringBuilder builder = new StringBuilder();

               while (reader != null){
                   builder.append(reader);
                   reader = bufferedReader.readLine();
               }


               result = new JSONArray(builder.toString());


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null){
                    connection.disconnect();
                }
            }


            return result;
        }
    }

}
