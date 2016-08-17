package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;

import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.R;

import Database.DiabetesSqlHelper;

public class StatsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "exact";
    private static final String ARG_PARAM8 = "value2";
    // TODO: Rename and change types of parameters
    private String mode, value, value2, startDate, endDate, startTime, endTime;
    private boolean exact;
    private DiabetesSqlHelper db;

    private OnFragmentInteractionListener mListener;

    public StatsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2, String param3, String param4, String param5, String param6, boolean exact, String value2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        args.putString(ARG_PARAM5, param5);
        args.putString(ARG_PARAM6, param6);
        args.putBoolean(ARG_PARAM7, exact);
        args.putString(ARG_PARAM8, value2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mode = getArguments().getString(ARG_PARAM1);
            value= getArguments().getString(ARG_PARAM2);
            value2 = getArguments().getString(ARG_PARAM8);
            startDate = getArguments().getString(ARG_PARAM3);
            endDate = getArguments().getString(ARG_PARAM4);
            startTime = getArguments().getString(ARG_PARAM5);
            endTime = getArguments().getString(ARG_PARAM6);
            exact = getArguments().getBoolean(ARG_PARAM7);
        }
        loadStats();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_stats, container, false);
       /* switch(mode){
            case "BGL" : loadBGLStats();
            case "Diet" : loadDietStats();
            case "Exercise" : loadExerciseStats();
            case "Medication" : loadMedicationStats();
        }*/
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void loadStats(){
        // TODO: Query SQLite database using mode, value, startDate, endDate, startTime, and endTime. Display results
    }
}
