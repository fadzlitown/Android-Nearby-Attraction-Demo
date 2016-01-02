package my.fadzlirazali.myfind;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

import java.util.List;

import de.greenrobot.event.EventBus;
import my.fadzlirazali.myfind.Event.NearbyEvent;
import my.fadzlirazali.myfind.jobs.GetNearbyLocationJob;
import my.fadzlirazali.myfind.models.Nearby;
import my.fadzlirazali.myfind.models.Result;


/**
 * A simple {@link Fragment} subclass.
 */
public class WalkaboutFragment extends Fragment{

    private static final LatLngBounds BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
            new LatLng(37.398160, -122.180831), new LatLng(37.430610, -121.972090));

    private TextView mTextView;

    private Button mCurrentBtn;

    public WalkaboutFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().getJobManager().addJobInBackground(new GetNearbyLocationJob());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_walkabout, container, false);
        mTextView = (TextView) view.findViewById(R.id.item);
        mCurrentBtn = (Button) view.findViewById(R.id.current_btn);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    public void onEventMainThread(NearbyEvent.onGetNearby event){
        Nearby nearbyObj = event.getNearby();
        if(nearbyObj.getResults().size()>0){
            Log.d("TAG", "onEventMainThread "+nearbyObj.getResults().size());

            List<Result> results = nearbyObj.getResults();
            String content ="";
            content+="Name: "+results.get(0).getName();
            content+="Icon: "+results.get(0).getIcon();
            content+="LatLit "+results.get(0).getGeometry().getLocation().getLat()+", "+results.get(0).getGeometry().getLocation().getLng();
            mTextView.setText(content);

        }

    }

}
