package franciscoamado.timer24;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
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


public class MainActivity extends ActionBarActivity implements OnClickListener{


    public FragmentManager fragmentManager;
    public FragmentTransaction fragmentTransaction;
    public OptionsFragment opFragment;
    public NumpadFragment numpadFragment;
    private TextView timerText;

    public MyCountDownTimer countDownTimer;
    public long millisRemaining;
    private long timeElapsed;
    public boolean timerHasStarted = false;
    private TextView text;
    private final long startTime = 24 * 1000;
    private final long interval = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main);

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        timerText = (TextView)findViewById(R.id.timer);
        timerText.setOnClickListener(this);

        countDownTimer = new MyCountDownTimer(24000, 1);
        text = (TextView) findViewById(R.id.timer);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, new OptionsFragment());
        fragmentTransaction.commit();
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
    public void onBackPressed() {
        if(fragmentManager.getBackStackEntryCount() == 1){
            fragmentManager.popBackStack();

            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new OptionsFragment());
            fragmentTransaction.commit();
        } else{
            super.onBackPressed();
        }
    }

    public void start_Stop()
    {
        if (!timerHasStarted)
        {
            if(millisRemaining != 0){
                countDownTimer = new MyCountDownTimer(millisRemaining, interval);
            }
            countDownTimer.start();
            timerHasStarted = true;
        }
        else
        {
            countDownTimer.cancel();
            timerHasStarted = false;
        }
    }

    public void reset_24(){
        if(!timerHasStarted){
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.onTick(24000);
        } else {
            countDownTimer.cancel();
            countDownTimer = new MyCountDownTimer(startTime, interval);
            timerHasStarted = true;
            countDownTimer.start();
        }
    }

    public void reset_14(){
        if(!timerHasStarted){
            countDownTimer = new MyCountDownTimer(startTime, interval);
            countDownTimer.onTick(14000);
        } else {
            countDownTimer.cancel();
            countDownTimer = new MyCountDownTimer(14000, interval);
            timerHasStarted = true;
            countDownTimer.start();
        }
    }

    public void editTime(long newTime){
        countDownTimer = new MyCountDownTimer(newTime, interval);
        countDownTimer.onTick(newTime);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.timer){
            start_Stop();
        }
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
