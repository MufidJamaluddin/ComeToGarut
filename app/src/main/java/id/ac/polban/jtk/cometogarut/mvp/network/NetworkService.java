package id.ac.polban.jtk.cometogarut.mvp.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService
{
    // url
    final String SERVICE_ENDPOINT = "http://cometogarut.gaetcita.com/api/";

    private IRestService restService;

    public NetworkService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        restService = retrofit.create(IRestService.class);
    }

    public IRestService getAPI()
    {
        return this.restService;
    }

}
