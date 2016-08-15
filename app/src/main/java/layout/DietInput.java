package layout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.R;

import java.util.Calendar;

import Database.FeedReaderContract;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DietInput.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DietInput#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DietInput extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public DietInput() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DietInput.
     */
    // TODO: Rename and change types and number of parameters
    public static DietInput newInstance(String param1, String param2) {
        DietInput fragment = new DietInput();
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
        final Button submitButton = (Button) getActivity().findViewById(R.id.ButtonSubmitDiet);
        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Get the values from text fields to pass to database
                long id = Calendar.getInstance().getTimeInMillis();
                int calories = Integer.parseInt(getActivity().findViewById(R.id.EditTextDietInput).toString());
                String food = getActivity().findViewById(R.id.EditFoodInput).toString();
                String date = getActivity().findViewById(R.id.EditTextDate).toString();
                String time = getActivity().findViewById(R.id.EditTextTime).toString();

                // need to instantiate a FeedReaderContract.Diet object here
                FeedReaderContract.Diet diet = new FeedReaderContract.Diet(getActivity()) {
                    @Override
                    public void onCreate(SQLiteDatabase db) {
                        super.onCreate(db);
                    }

                    @Override
                    public void onUpgrade(SQLiteDatabase db, int a, int b) {
                        super.onUpgrade(db, a, b);
                    }

                    @Override
                    public boolean insert(long id, String name, String time, int calories) {
                        return super.insert(id, name, time, calories);
                    }
                };
                diet.insert(id,food,time,calories);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_diet_input, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
