package com.gyzlab.gpsexample;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
    private TextView textView;
    private LocationManager locationManager;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textView);
        button = (Button)findViewById(R.id.button);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Request the Location Updates
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                //Get last time known location
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                //If system had last location
                if(location != null)
                    textView.setText("經度:" + location.getLongitude() + ", 緯度:" + location.getLatitude());
                else
                    textView.setText("取得座標中...");
            }
        });
    }


    public LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            Log.d("locationListener","status: Get LocationChanged!");
            textView.setText("經度:" + location.getLongitude() + ", 緯度:" + location.getLatitude());
        }
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("locationListener","status:"+status);
        }
        public void onProviderEnabled(String provider) {
            Log.d("locationListener","status: GPS Enabled");

        }
        public void onProviderDisabled(String provider) {
            Log.d("locationListener","status: GPS Disabled");
            textView.setText("GPS未開啟！");
        }
    };


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
}
