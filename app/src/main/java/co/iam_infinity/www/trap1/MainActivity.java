package co.iam_infinity.www.trap1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_LOCATION=1;
    LocationManager locationmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationmanager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        getlocation();
    }

    public void getlocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }
        else{
            Location location= locationmanager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location!=null){
                double latitude=location.getLatitude();
                double longitude=location.getLongitude();
                ((EditText)findViewById(R.id.edit_longitude)).setText(""+longitude);
                ((EditText)findViewById(R.id.edit_latitude)).setText(""+latitude);

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("DATA").child("liveNode");
                Loc loc = new Loc();
                loc.setLat((float) latitude);
                loc.setLon((float) longitude);
                databaseReference.setValue(loc);
            }
            else {
                ((EditText)findViewById(R.id.edit_longitude)).setText("Unable to fecth Longitude");
                ((EditText)findViewById(R.id.edit_longitude)).setText("Unable to fetch Longitude");
            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permission, @NonNull int[]grantResults)
    {
        super.onRequestPermissionsResult(requestCode,permission,grantResults);
        switch (requestCode)
        {
            case REQUEST_LOCATION:
                getlocation();
                break;
        }
    }
}
