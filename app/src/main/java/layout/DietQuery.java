package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.GraphTableStats;
import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DietQuery.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DietQuery#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietQuery extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Button search;
    private EditText value, startDate, endDate, startTime, endTime;
    private CheckBox exact;

    public DietQuery() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DietQuery.
     */
    // TODO: Rename and change types and number of parameters
    public static DietQuery newInstance(String param1, String param2) {
        DietQuery fragment = new DietQuery();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_diet_query, container, false);
        value = (EditText) view.findViewById(R.id.editTextFoodType);
        startDate = (EditText) view.findViewById(R.id.editTextStartDate);
        endDate = (EditText) view.findViewById(R.id.editTextEndDate);
        startTime = (EditText) view.findViewById(R.id.editTextEarliestTime);
        endTime = (EditText) view.findViewById(R.id.editTextLatestTime);
        exact = (CheckBox) view.findViewById(R.id.checkBoxExactMatch);
        search = (Button) view.findViewById(R.id.buttonSearch);
        search.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getContext(), GraphTableStats.class );
                intent.putExtra("mode", "Diet");
                intent.putExtra("value", value.getText().toString());
                intent.putExtra("value2", "");
                intent.putExtra("startDate", startDate.getText().toString());
                intent.putExtra("endDate", endDate.getText().toString());
                intent.putExtra("startTime", startTime.getText().toString());
                intent.putExtra("endTime", endTime.getText().toString());
                intent.putExtra("exact", exact.isChecked());
                startActivity(intent);
            }
        });

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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
