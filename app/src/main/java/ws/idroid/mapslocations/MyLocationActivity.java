package ws.idroid.mapslocations;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.os.*;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MyLocationActivity extends AppCompatActivity {
    private static final String[] LOCATION_PERMS = {
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
    };
    private static final int LOCATION_REQUEST_CODE = 1337;
    double longitude;
    double latitude;
    private Location location;
    LocationManager locationManager;
    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_location);

        tvLocation = findViewById(R.id.txt_location);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(LOCATION_PERMS, LOCATION_REQUEST_CODE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission
                .ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission
                        .ACCESS_COARSE_LOCATION) ==
                        PackageManager.PERMISSION_GRANTED) {
            // we will get the user location
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    if (location != null) {
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        tvLocation.setText(String.format("My Longitude = %s and latitude = %s",
                                longitude, latitude));
                    }
                }

                //method is called when a provider is unable to fetch a location
                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {
                    tvLocation.setText("onStatusChanged ");
                    if (location != null) {

                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        tvLocation.setText("onStatusChanged My Longitude = " + longitude + " " +
                                "and latitude = " + latitude);
                    }
                }

                @Override
                public void onProviderEnabled(String provider) {
                    tvLocation.setText("onProviderEnabled ");

                    if (location != null) {

                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        tvLocation.setText("onProviderEnabled My Longitude = " + longitude + " " +
                                "and latitude = " + latitude);
                    }
                }

                @Override
                public void onProviderDisabled(String provider) {
                    tvLocation.setText("onProviderDisabled ");
                    if (location != null) {
                        longitude = location.getLongitude();
                        latitude = location.getLatitude();
                        tvLocation.setText("onProviderDisabled My Longitude = " + longitude + "" +
                                " and latitude = " + latitude);
                    }

                }
            };

            //Register the listner
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 10,
                    locationListener);
        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //TODO If the user accepted the permission
    }
}
