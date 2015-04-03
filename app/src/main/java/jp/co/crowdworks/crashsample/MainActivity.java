package jp.co.crowdworks.crashsample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.flurry.android.FlurryAgent;
import com.parse.Parse;
import com.parse.ParseCrashReporting;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    private static final String FLURRY_API_KEY = "V9QF3R9YPX7QSP88BHP9";
    private static final String PARSE_API_APPLICATION_ID = "gUNwGjYDlUvsHaXivf7mZlxTHBDPfemaWUfxu4cT";
    private static final String PARSE_API_CLIENT_KEY = "0SAPMi00LdJ2AQHpBDZPxNje9XJt2kxWKWvP2R1g";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);

        getButton(R.id.btn_raise_npe).setOnClickListener(this);
        getButton(R.id.btn_ANR).setOnClickListener(this);

        ParseCrashReporting.enable(this);
        Parse.initialize(this, PARSE_API_APPLICATION_ID, PARSE_API_CLIENT_KEY);
        FlurryAgent.init(this, FLURRY_API_KEY);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FlurryAgent.onStartSession(this, FLURRY_API_KEY);
    }

    @Override
    protected void onStop() {
        super.onStop();

        FlurryAgent.onStartSession(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

    private Button getButton(int id) {
        return (Button) findViewById(id);
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
        final int id = v.getId();
        if(R.id.btn_raise_npe==id){
            String a=null;
            Log.d(null, a.length()+"");
        }
        else if(R.id.btn_ANR==id){
            try {
                Thread.sleep(12 * 1000);
            } catch(Exception e){}
        }
    }
}
