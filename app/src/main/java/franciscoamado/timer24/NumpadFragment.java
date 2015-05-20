package franciscoamado.timer24;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Objects;


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
    private MainActivity myActivity;

    private TextView second_timer;
    private long temp_timer;
    private long temp_timer_decimal;
    private boolean decimal;

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

        temp_timer = 0;
        decimal = false;
        temp_timer_decimal = 000;
        myActivity = (MainActivity) getActivity();


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

        myActivity.editTime(convertToFull(temp_timer, temp_timer_decimal));
        myActivity.onBackPressed();
    }

    /*
    * Erases the timer when the textview is clicked
    * */
    public void onClick_EraseTimer(){
        second_timer.setText("00");
        temp_timer = 0;
        temp_timer_decimal = 0;
    }

    /*
    * Adds a number or dot to the second timer based on the view that is clicked
    * */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void onClick_addTimer(View v){
        String auxText = second_timer.getText().toString();
        if(auxText.compareToIgnoreCase("EDIT")==0 || auxText.compareTo("00")==0 ){
            auxText = "";
        }
            switch (v.getId()) {
                case R.id.num_zero:
                    addNum(0);
                    break;
                case R.id.num_one:
                    addNum(1);
                    break;
                case R.id.num_two:
                    addNum(2);
                    break;
                case R.id.num_three:
                    addNum(3);
                    break;
                case R.id.num_four:
                    addNum(4);
                    break;
                case R.id.num_five:
                    addNum(5);
                    break;
                case R.id.num_six:
                    addNum(6);
                    break;
                case R.id.num_seven:
                    addNum(7);
                    break;
                case R.id.num_eight:
                    addNum(8);
                    break;
                case R.id.num_nine:
                    addNum(9);
                    break;
                case R.id.num_dot:
                    decimal = true;
                    break;
            }
            second_timer.setText(Objects.toString(temp_timer, null) + "." + Objects.toString(temp_timer_decimal, null));
    }

    private void addNum(int num){
        long aux_timer;
        if(!decimal && temp_timer < 10) {
            aux_timer = temp_timer;
            temp_timer = aux_timer * 10;
            temp_timer += num;
        } else {
            if (temp_timer_decimal < 99) {
                aux_timer = temp_timer_decimal;
                temp_timer_decimal = aux_timer * 10;
                temp_timer_decimal += num;
            }
        }
    }

    private long convertToFull(long intTemp, long decTemp){
        return (intTemp * 1000) + decTemp;
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
