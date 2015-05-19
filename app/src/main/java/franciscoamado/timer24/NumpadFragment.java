package franciscoamado.timer24;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NumpadFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumpadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumpadFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private View myFragmentView;

    private TextView second_timer;
    private Double temp_timer;

    private Button button_zero;
    private Button button_one;
    private Button button_two;
    private Button button_three;
    private Button button_four;
    private Button button_dot;

    private Button button_five;
    private Button button_six;
    private Button button_seven;
    private Button button_eight;
    private Button button_nine;
    private Button button_enter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumpadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumpadFragment newInstance(String param1, String param2) {
        NumpadFragment fragment = new NumpadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NumpadFragment() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        myFragmentView =  inflater.inflate(R.layout.fragment_numpad, container, false);

        //if secondTimer textview is pressed it will erase previous (or entire) number
        second_timer = (TextView)myFragmentView.findViewById(R.id.secondtimer);
        second_timer.setOnClickListener(this);

        button_zero = (Button) myFragmentView.findViewById(R.id.num_zero);
        button_zero.setOnClickListener(this);

        button_one = (Button) myFragmentView.findViewById(R.id.num_one);
        button_one.setOnClickListener(this);

        button_two = (Button) myFragmentView.findViewById(R.id.num_two);
        button_two.setOnClickListener(this);

        button_three = (Button) myFragmentView.findViewById(R.id.num_three);
        button_three.setOnClickListener(this);

        button_four = (Button) myFragmentView.findViewById(R.id.num_four);
        button_four.setOnClickListener(this);

        button_dot = (Button) myFragmentView.findViewById(R.id.num_dot);
        button_dot.setOnClickListener(this);

        button_five = (Button) myFragmentView.findViewById(R.id.num_five);
        button_five.setOnClickListener(this);

        button_six = (Button) myFragmentView.findViewById(R.id.num_six);
        button_six.setOnClickListener(this);

        button_seven = (Button) myFragmentView.findViewById(R.id.num_seven);
        button_seven.setOnClickListener(this);

        button_eight = (Button) myFragmentView.findViewById(R.id.num_eight);
        button_eight.setOnClickListener(this);

        button_nine = (Button) myFragmentView.findViewById(R.id.num_nine);
        button_nine.setOnClickListener(this);

        button_enter = (Button) myFragmentView.findViewById(R.id.num_enter);
        button_enter.setOnClickListener(this);

        return myFragmentView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
//        try {
//            mListener = (OnFragmentInteractionListener) activity;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(activity.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.num_enter) {
            onClick_Enter();
        }else if(v.getId() == R.id.secondtimer) {
            onClick_EraseTimer();
        }else {
            onClick_addTimer(v);
        }
    }

    /*
    * NOT DEVELOPED
    * Sends secondtimer content to the first timer in the previous OpFragment
    * */
    public void onClick_Enter(){
        MainActivity myActivity = (MainActivity)getActivity();
        myActivity.countDownTimer.onTick(2000);
    }

    /*
    * Erases the timer when the textview is clicked
    * */
    public void onClick_EraseTimer(){
        second_timer.setText("00");
    }

    /*
    * Adds a number or dot to the second timer based on the view that is clicked
    * */
    public void onClick_addTimer(View v){
        String auxText = second_timer.getText().toString();
        if(auxText.compareToIgnoreCase("EDIT")==0 || auxText.compareTo("00")==0 ){
            auxText = "";
        }
        if(auxText.length() <= "100".length()){
            switch (v.getId()) {
                case R.id.num_zero:
                    second_timer.setText(auxText + 0);
                    break;
                case R.id.num_one:
                    second_timer.setText(auxText + 1);
                    break;
                case R.id.num_two:
                    second_timer.setText(auxText + 2);
                    break;
                case R.id.num_three:
                    second_timer.setText(auxText + 3);
                    break;
                case R.id.num_four:
                    second_timer.setText(auxText + 4);
                    break;
                case R.id.num_five:
                    second_timer.setText(auxText + 5);
                    break;
                case R.id.num_six:
                    second_timer.setText(auxText + 6);
                    break;
                case R.id.num_seven:
                    second_timer.setText(auxText + 7);
                    break;
                case R.id.num_eight:
                    second_timer.setText(auxText + 8);
                    break;
                case R.id.num_nine:
                    second_timer.setText(auxText + 9);
                    break;
                case R.id.num_dot:
                    if(!auxText.contains(".")) {
                        second_timer.setText(auxText + ".");
                    }
                    break;
            }
        }
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
        public void onFragmentInteraction(Uri uri);
    }
}
