package franciscoamado.timer24;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.os.Build;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Button;


import android.app.FragmentManager;
import android.app.FragmentTransaction;


public class MainActivity extends Activity implements OnClickListener{

    private MyCountDownTimer countDownTimer;
    public long millisRemaining;
    private long timeElapsed;
    private boolean timerHasStarted = false;
    private Button btn_reset_14;
    private Button btn_start_stop;
    private Button btn_numpad;
    private Button btn_reset_24;
    private TextView text;
    private final long startTime = 24 * 1000;
    private final long interval = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
//        getActionBar().hide();

        setContentView(R.layout.activity_main);
//        FragmentManager fragmentManager = getFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        OptionsFragment opFragment = new OptionsFragment();
//        fragmentTransaction.replace(R.id.options_fragment, opFragment);

        btn_reset_14 = (Button) this.findViewById(R.id.btn_reset_14);
        btn_reset_14.setOnClickListener(this);

        btn_start_stop = (Button) this.findViewById(R.id.btn_start_stop);
        btn_start_stop.setOnClickListener(this);

        btn_numpad = (Button) this.findViewById(R.id.btn_numpad);
        btn_numpad.setOnClickListener(this);

        btn_reset_24 = (Button) this.findViewById(R.id.btn_reset_24);
        btn_reset_24.setOnClickListener(this);

        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(startTime, interval);
        text.setText(text.getText() + String.valueOf(startTime));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        if (!timerHasStarted)
        {
            if(millisRemaining != 0){
                countDownTimer = new MyCountDownTimer(millisRemaining, interval);
            }
            countDownTimer.start();
            timerHasStarted = true;
            btn_start_stop.setText("STOP");
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
            btn_start_stop.setText("START");
        }
    }

    public void onClick_reset_24(View v){
        if(!timerHasStarted){
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.onTick(24000);
        } else {
            countDownTimer.cancel();
            countDownTimer = new MyCountDownTimer(startTime, interval);
            timerHasStarted = true;
            countDownTimer.start();
            btn_start_stop.setText("STOP");
        }
    }

    public void onClick_reset_14(View v){
        if(!timerHasStarted){
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.onTick(14000);
        } else {
            countDownTimer.cancel();
            countDownTimer = new MyCountDownTimer(14000, interval);
            timerHasStarted = true;
            countDownTimer.start();
            btn_start_stop.setText("STOP");
        }
    }

    public void onClick_numpad(View v){
//        TableRow row = (TableRow)this.findViewById(R.id.first_button_row);
//        row.removeAllViews();
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long startTime, long interval) { super(startTime, interval);}

        @Override
        public void onFinish() {
            text.setText("Time's up!");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            millisRemaining = millisUntilFinished;
            long sec = millisUntilFinished / 1000;
            long msec = millisUntilFinished % 1000;
            text.setText(Long.toString(sec) + "." + Long.toString(msec));
        }
    }
}
