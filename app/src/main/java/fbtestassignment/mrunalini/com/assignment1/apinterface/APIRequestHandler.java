package fbtestassignment.mrunalini.com.assignment1.apinterface;


import java.util.HashMap;

import fbtestassignment.mrunalini.com.assignment1.models.ServiceResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIRequestHandler {

	private static final APIRequestHandler instance = new APIRequestHandler();
	APICommonInterface mInterfaces = createService();

	private APIRequestHandler() {

	}

	public static APIRequestHandler getInstance() {
		return instance;
	}

	public static APICommonInterface createService() {
		Retrofit retrofit = new Retrofit.Builder().baseUrl(AppConstants.BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.build();

		return retrofit.create(APICommonInterface.class);
	}

	public Call<ServiceResponse> getPasses(double lat,double lon) {
		//DialogManager.showProgress(mActivity);
		HashMap<String, String> paramsMap= new HashMap<String,String>();
		paramsMap.put("lat",String.valueOf(lat));
		paramsMap.put("lon",String.valueOf(lon));
		return mInterfaces.getPasses(paramsMap);
	}
}
