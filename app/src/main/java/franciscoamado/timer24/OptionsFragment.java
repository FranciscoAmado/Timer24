package franciscoamado.timer24;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.CountDownTimer;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OptionsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OptionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OptionsFragment extends Fragment implements OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View myFragmentView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public long millisRemaining;
    private long timeElapsed;
    public boolean timerHasStarted = false;
    private TextView text;
    private final long startTime = 24 * 1000;
    private final long interval = 1;

    private Button btn_reset_14;
    private Button btn_start_stop;
    private Button btn_numpad;
    private Button btn_reset_24;

    private OptionsListener mListener;
    private MainActivity myActivity;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OptionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OptionsFragment newInstance(String param1, String param2) {
        OptionsFragment fragment = new OptionsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public OptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        text = (TextView) getActivity().findViewById(R.id.timer);
//      text.setText(text.getText() + String.valueOf(startTime));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myFragmentView =  inflater.inflate(R.layout.fragment_options, container, false);

        myActivity = (MainActivity) getActivity();

        btn_reset_14 = (Button) myFragmentView.findViewById(R.id.btn_reset_14);
        btn_reset_14.setOnClickListener(this);

        btn_start_stop = (Button) myFragmentView.findViewById(R.id.btn_start_stop);
        btn_start_stop.setOnClickListener(this);

        btn_numpad = (Button) myFragmentView.findViewById(R.id.btn_numpad);
        btn_numpad.setOnClickListener(this);

        btn_reset_24 = (Button) myFragmentView.findViewById(R.id.btn_reset_24);
        btn_reset_24.setOnClickListener(this);

        return myFragmentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        /*
        try {
            mListener = (OptionsListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OptionsListener");
        }
        */
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
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

    @Override
    public void onClick(View v) {
       switch(v.getId()) {
            case R.id.btn_reset_24:
                onClick_reset_24(v);
                break;
            case R.id.btn_start_stop:
                onClick_Start_Stop(v);
                break;
            case R.id.btn_reset_14:
                onClick_reset_14(v);
                break;
            case R.id.btn_numpad:
                onClick_numpad(v);
                break;
        }
    }
    public void onClick_Start_Stop(View v)
    {
        if (!myActivity.timerHasStarted)
        {
            timerHasStarted = true;
            btn_start_stop.setText("STOP");
        }
        else
        {
            timerHasStarted = false;
            btn_start_stop.setText("START");
        }
        myActivity.start_Stop();
    }

    public void onClick_reset_24(View v){
        if(myActivity.timerHasStarted){
            timerHasStarted = true;
            btn_start_stop.setText("STOP");
        }
        myActivity.reset_24();
    }

    public void onClick_reset_14(View v){
        if(myActivity.timerHasStarted){
            timerHasStarted = true;
            btn_start_stop.setText("STOP");
        }
        myActivity.reset_14();
    }

    public void onClick_numpad(View v){

        if(myActivity.timerHasStarted){
            myActivity.countDownTimer.cancel();
        }

        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, new NumpadFragment());
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
    }
}
