package demo.com.fitness.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import demo.com.fitness.R;
import demo.com.fitness.data.Event;
import demo.com.fitness.data.MainViewModel;
import demo.com.fitness.data.Teacher;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder>{

    private List<Event> events;
    private MainViewModel viewModel;

    public EventsAdapter() {
        events = new ArrayList<Event>();
    }

    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.event_item,viewGroup,false);


        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder eventsViewHolder, int i) {

          Event event = events.get(i);

          eventsViewHolder.textViewname.setText(event.getName());
          eventsViewHolder.textViewdescription.setText(event.getDescription());
          eventsViewHolder.textViewplace.setText(event.getPlace());
          eventsViewHolder.textViewteacher.setText(event.getTeacher());
          eventsViewHolder.textViewstartTime.setText(event.getStartTime());
          eventsViewHolder.textViewendTime.setText(event.getEndTime());

          Teacher teacher = viewModel.getTeacherByName(event.getTeacher());

          Picasso.get().load(teacher.getImageUrl()).into(eventsViewHolder.imageViewTeacherPic);

//          eventsViewHolder.textViewweekDay.setText(event.getWeekDay());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class EventsViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewname;
        private TextView textViewdescription;
        private TextView textViewplace;
        private TextView textViewteacher;
        private TextView textViewstartTime;
        private TextView textViewendTime;
        private TextView textViewweekDay;
        private ImageView imageViewTeacherPic;


      public EventsViewHolder(@NonNull View itemView) {
          super(itemView);

          textViewname = itemView.findViewById(R.id.textViewname);
          textViewdescription = itemView.findViewById(R.id.textViewdescription);
          textViewplace = itemView.findViewById(R.id.textViewplace);
          textViewteacher = itemView.findViewById(R.id.textViewteacher);
          textViewstartTime = itemView.findViewById(R.id.textViewstartTime);
          textViewendTime = itemView.findViewById(R.id.textViewendTime);
          textViewweekDay = itemView.findViewById(R.id.textViewweekDay);
          imageViewTeacherPic = itemView.findViewById(R.id.imageViewTeacherPic);

      }
  }

    public void setViewModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public List<Event> getEvents(){
        return events;
  }

  public void clear(){
        this.events.clear();
        notifyDataSetChanged();
  }

public void addEvents(List<Event> events){
        this.events.addAll(events);
        notifyDataSetChanged();
}


public void setEvents(List<Event> events){
        this.events = events;
        notifyDataSetChanged();
}

}
