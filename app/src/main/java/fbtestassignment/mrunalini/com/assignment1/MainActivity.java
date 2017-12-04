package fbtestassignment.mrunalini.com.assignment1;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import fbtestassignment.mrunalini.com.assignment1.adapter.IssAdapter;
import fbtestassignment.mrunalini.com.assignment1.apinterface.APIRequestHandler;
import fbtestassignment.mrunalini.com.assignment1.models.ResponseModel;
import fbtestassignment.mrunalini.com.assignment1.models.ServiceResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.location.LocationManager.GPS_PROVIDER;

public class MainActivity extends AppCompatActivity implements Callback<ServiceResponse>
{
    private static final String TAG = "MainActivity";
    private static final int MY_PERMISSIONS_ACCESS_FINE_LOCATION=10001;
    List<ResponseModel> issList = new ArrayList<>();
    LocationManager locationManager;
    IssAdapter issAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView testRv = (RecyclerView) findViewById(R.id.test_rv);
        issAdapter = new IssAdapter(issList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        testRv.setLayoutManager(linearLayoutManager);
        testRv.setAdapter(issAdapter);
        if(checkLocationPermission()){
            getCurrentLocation();
        }
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

                 Call<ServiceResponse> call = APIRequestHandler.getInstance().getPasses(location.getLatitude(),location.getLongitude());


                call.enqueue(MainActivity.this);

            }
        };

        locationManager.requestLocationUpdates(GPS_PROVIDER, 1000, 50, listener);
    }

    private boolean checkLocationPermission() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_ACCESS_FINE_LOCATION);
                    return false;
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_ACCESS_FINE_LOCATION);
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
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getCurrentLocation();
                } else {
                    Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
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
            issAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<ServiceResponse> call, Throwable t) {
            t.printStackTrace();
    }
}


