package fbtestassignment.mrunalini.com.assignment1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fbtestassignment.mrunalini.com.assignment1.adapter.UserRvAdapter;
import fbtestassignment.mrunalini.com.assignment1.apinterface.APIRequestHandler;
import fbtestassignment.mrunalini.com.assignment1.apinterface.CommonInterface;
import fbtestassignment.mrunalini.com.assignment1.models.ResponseModel;
import fbtestassignment.mrunalini.com.assignment1.models.ServiceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.location.LocationManager.*;

public class MainActivity extends AppCompatActivity implements Callback<ServiceResponse>
{
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION=10001;

    List<ResponseModel> issList = new ArrayList<>();
//    private static final String[] INITIAL_PERMS = {
//            Manifest.permission.ACCESS_FINE_LOCATION,
//    };
//    private static final String[] LOCATION_PERMS = {
//            Manifest.permission.ACCESS_FINE_LOCATION
//    };
//    private static final int INITIAL_REQUEST = 1337;
//
//    private static final int LOCATION_REQUEST = INITIAL_REQUEST + 1;
    LocationManager locationManager;
    UserRvAdapter userRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RecyclerView testRv = (RecyclerView) findViewById(R.id.test_rv);
        userRvAdapter = new UserRvAdapter(issList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        testRv.setLayoutManager(linearLayoutManager);
        testRv.setAdapter(userRvAdapter);


        if(checkLocationPermission()){
            getCurrentLocation();
        }





//        if (!canAccessLocation()) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
//            }
//        }
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//





//        }
//    }

//    private boolean canAccessLocation() {
//        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
//    }
//
//    private boolean hasPermission(String accessFineLocation) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(accessFineLocation));
//        }
//        return false;
//    }
//    @Override
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
//        switch (requestCode) {
//            case LOCATION_REQUEST:
//                if (canAccessLocation()) {
//                    Toast.makeText(this, "Location", Toast.LENGTH_SHORT).show();
//                } else {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                        requestPermissions(LOCATION_PERMS, LOCATION_REQUEST);
//                    }
//                }
//                break;
    }

    private void getCurrentLocation() {
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        LocationListener listener = new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }

            @Override
            public void onLocationChanged(Location location) {
//                double lati = location.getLatitude();
//                double longi = location.getLongitude();
//                textView.append(" " + lati + "," + longi + "\n");
//           Log.i(TAG, "Longitude & Longitude" + lati + longi);
                 Call<ServiceResponse> call = APIRequestHandler.getInstance().getPasses(location.getLatitude(),location.getLongitude());


                call.enqueue(MainActivity.this);

            }
        };

        locationManager.requestLocationUpdates(GPS_PROVIDER, 1000, 50, listener);
    }

    private boolean checkLocationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {

                    // Show an explanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_ACCESS_FINE_LOCATION);

                    return false;
                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_ACCESS_FINE_LOCATION);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                    return false;
                }
            }

        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[],int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    getCurrentLocation();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    @Override
    public void onResponse(Call<ServiceResponse> call, Response<ServiceResponse> response) {
        if(response!=null){
            ServiceResponse response2 = response.body();
            Gson gson=new Gson();
            String jsonString=gson.toJson(response);
            issList.clear();
            if(response2.getResponse()!=null)
                issList.addAll(response2.getResponse());
            userRvAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
t.printStackTrace();
    }

}


