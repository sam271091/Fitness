package demo.com.fitness;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.material.bottomsheet.BottomSheetDialog;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import demo.com.fitness.adapters.EventsAdapter;
import demo.com.fitness.data.Event;
import demo.com.fitness.data.MainViewModel;
import demo.com.fitness.data.Teacher;
import demo.com.fitness.utils.JSONUtils;
import demo.com.fitness.utils.NetworkUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<JSONArray> {

    private LoaderManager loaderManager;
    private RecyclerView recyclerViewEvents;
    private EventsAdapter eventsAdapter;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null){
//            actionBar.hide();
//        }


        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        recyclerViewEvents = findViewById(R.id.RecycleViewEvents);

        recyclerViewEvents.setLayoutManager(new LinearLayoutManager(this));

        loaderManager = LoaderManager.getInstance(this);

        eventsAdapter = new EventsAdapter();

        eventsAdapter.setViewModel(viewModel);


        eventsAdapter.setOnEventClickListener(new EventsAdapter.OnEventClickListener() {
            @Override
            public void OnEventClick(int position) {

                List<Event> events = eventsAdapter.getEvents();

                Event event = events.get(position);

                Teacher teacher = viewModel.getTeacherByName(event.getTeacher());

//                Toast.makeText(MainActivity.this, Integer.toString(position), Toast.LENGTH_SHORT).show();

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        MainActivity.this,R.style.BottomSheetDialogTheme);


                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(R.layout.layout_bottom_sheet,
                                (LinearLayout)findViewById(R.id.bottomSheetContainer)
                        );

                TextView Name = bottomSheetView.findViewById(R.id.textViewName);
                TextView shortName = bottomSheetView.findViewById(R.id.textViewShortName);
                TextView teacherposition = bottomSheetView.findViewById(R.id.textViewPosition);

                ImageView imageView = bottomSheetView.findViewById(R.id.imageView);

                Name.setText(teacher.getName());
                shortName.setText(teacher.getShort_name());
                teacherposition.setText(teacher.getPosition());

                Picasso.get().load(teacher.getImageUrl()).into(imageView);

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();


            }
        });



        recyclerViewEvents.setAdapter(eventsAdapter);


        downloadData();


        LiveData<List<Event>> eventsFromLiveData = viewModel.getEvents();
        eventsFromLiveData.observe(this, new Observer<List<Event>>() {
            @Override
            public void onChanged(@Nullable List<Event> events) {
                eventsAdapter.setEvents(events);
            }
        });


    }

    private void downloadData(){

        Bundle bundle = new Bundle();
        loaderManager.restartLoader(123,bundle,this);
    }


    @NonNull
    @Override
    public Loader<JSONArray> onCreateLoader(int i, @Nullable Bundle bundle) {
        NetworkUtils.JSONLoader jsonLoader = new NetworkUtils.JSONLoader(this,bundle);

        return jsonLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<JSONArray> loader, JSONArray jsonArray) {

        ArrayList<Event> events = JSONUtils.getEventsFromJSON(jsonArray);



        if (events != null & events.size() > 0){
            viewModel.deleteAllEvents();
            viewModel.deleteAllTeachers();
            eventsAdapter.clear();

            for (Event event : events){
                viewModel.insertEvent(event);
                viewModel.insertTeacher(event.getTeacherObj());
            }


//            eventsAdapter.addEvents(events);


            eventsAdapter.addEvents(events);
        }




        loaderManager.destroyLoader(123);


    }

    @Override
    public void onLoaderReset(@NonNull Loader<JSONArray> loader) {

    }
}
