package fbtestassignment.mrunalini.com.assignment1.apinterface;



import java.util.Map;

import fbtestassignment.mrunalini.com.assignment1.models.ServiceResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface APICommonInterface {


	@GET("/iss-pass.json?")
	Call<ServiceResponse> getPasses(@QueryMap Map<String, String> params);
}
