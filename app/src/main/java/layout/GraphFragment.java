package layout;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.charts.*;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.R;

import java.util.ArrayList;
import java.util.List;

import Database.DiabetesSqlHelper;

public class GraphFragment extends Fragment {
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

    public GraphFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static GraphFragment newInstance(String param1, String param2, String param3, String param4, String param5, String param6, boolean exact, String value2) {
        GraphFragment fragment = new GraphFragment();
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
            value = getArguments().getString(ARG_PARAM2);
            value2 = getArguments().getString(ARG_PARAM8);
            startDate = getArguments().getString(ARG_PARAM3);
            endDate = getArguments().getString(ARG_PARAM4);
            startTime = getArguments().getString(ARG_PARAM5);
            endTime = getArguments().getString(ARG_PARAM6);
            exact = getArguments().getBoolean(ARG_PARAM7);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_graph, container, false);
        db = new DiabetesSqlHelper(getActivity());
        switch (mode){
            case "BGL": loadBGLGraph(view);
            case "Diet": loadDietGraph(view);
            case "Exercise": loadExerciseGraph(view);
            case "Medication": loadMedicationGraph(view);
        }
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

    public void loadBGLGraph(View v){
        // TODO: Query SQLite database using mode, value, startDate, endDate, startTime, and endTime. Display results
        Cursor cursor = db.queryBgl(value2, value, startDate, endDate, startTime, endTime);
        cursor.getColumnNames();
        // Below is a placeholder graph to be replaced by a graph fed with data from SQLite
        LineChart chart = new LineChart(getContext());
        LinearLayout layout = (LinearLayout)v.findViewById(R.id.graphFragment);
        chart.setLayoutParams(new LineChart.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(chart);
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0, 0));
        entries.add(new Entry(1, 3));
        entries.add(new Entry(2, 6));
        entries.add(new Entry(3, 2));
        entries.add(new Entry(4, 4));
        chart.setData(new LineData(new LineDataSet(entries, "Placeholder Graph")));
        chart.invalidate();
    }

    public void loadDietGraph(View v){
        // TODO: Query SQLite database using mode, value, startDate, endDate, startTime, and endTime. Display results
        // Below is a placeholder graph to be replaced by a graph fed with data from SQLite

        BarChart chart = new BarChart(getContext());
        LinearLayout layout = (LinearLayout)v.findViewById(R.id.graphFragment);
        chart.setLayoutParams(new LineChart.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 3));
        entries.add(new BarEntry(2, 6));
        entries.add(new BarEntry(3, 2));
        entries.add(new BarEntry(4, 4));
        chart.setData(new BarData(new BarDataSet(entries, "Placeholder Graph")));
        chart.invalidate();
    }

    public void loadExerciseGraph(View v){
        // TODO: Query SQLite database using mode, value, startDate, endDate, startTime, and endTime. Display results
        // Below is a placeholder graph to be replaced by a graph fed with data from SQLite
       Cursor cursor =  db.queryExercise(exact, value, startDate, endDate, startTime, endTime);
        cursor.getColumnNames();
        BarChart chart = new BarChart(getContext());
        LinearLayout layout = (LinearLayout)v.findViewById(R.id.graphFragment);
        chart.setLayoutParams(new LineChart.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(chart);
        List<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0, 0));
        entries.add(new BarEntry(1, 3));
        entries.add(new BarEntry(2, 6));
        entries.add(new BarEntry(3, 2));
        entries.add(new BarEntry(4, 4));
        chart.setData(new BarData(new BarDataSet(entries, "Placeholder Graph")));
        chart.invalidate();
    }

    public void loadMedicationGraph(View v){
        // TODO: Query SQLite database using mode, value, startDate, endDate, startTime, and endTime. Display results
        // Below is a placeholder graph to be replaced by a graph fed with data from SQLite
        Cursor cursor = db.queryMedication(exact, value, startDate, endDate, startTime, endTime);
        cursor.getColumnNames();

        LineChart chart = new LineChart(getContext());
        LinearLayout layout = (LinearLayout)v.findViewById(R.id.graphFragment);
        chart.setLayoutParams(new LineChart.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        layout.addView(chart);
        List<Entry> entries = new ArrayList<Entry>();
        entries.add(new Entry(0, 0));
        entries.add(new Entry(1, 3));
        entries.add(new Entry(2, 6));
        entries.add(new Entry(3, 2));
        entries.add(new Entry(4, 4));
        chart.setData(new LineData(new LineDataSet(entries, "Placeholder Graph")));
        chart.invalidate();
    }
}
