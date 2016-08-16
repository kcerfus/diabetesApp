package layout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.uwm.wundergrads.diabetesselfmanagement_wundergrads.R;

import Database.DiabetesSqlHelper;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegimenInput.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegimenInput#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegimenInput extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText regimenInput;
    private EditText time;
    private Spinner type;
    private Button submit;
    private DiabetesSqlHelper db;

    private OnFragmentInteractionListener mListener;

    public RegimenInput() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegimenInput.
     */
    // TODO: Rename and change types and number of parameters
    public static RegimenInput newInstance(String param1, String param2) {
        RegimenInput fragment = new RegimenInput();
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
        View view = inflater.inflate(R.layout.fragment_regimen_input, container, false);
        db = new DiabetesSqlHelper(getActivity());
        regimenInput = (EditText) view.findViewById(R.id.EditTextRegimen);
        type = (Spinner) view.findViewById(R.id.spinnerType);
        time = (EditText) view.findViewById(R.id.EditTextTime);
        submit = (Button) view.findViewById(R.id.ButtonSubmitRegimen);
        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if (regimenInput.getText().toString().equals("") || time.getText().toString().equals("") || type.getSelectedItem().toString().equals("")){
                    Toast toaster = Toast.makeText(getActivity(), "Enter values for all fields", Toast.LENGTH_LONG);
                    toaster.show();
                }
                else{
                    db.insertRegimen(regimenInput.getText().toString(), type.getSelectedItem().toString(), time.getText().toString());
                    regimenInput.getText().clear();
                    time.getText().clear();
                    Toast toast = Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT);
                    toast.show();
                    regimenInput.requestFocus();
                }
            }
        });
        return view;

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
