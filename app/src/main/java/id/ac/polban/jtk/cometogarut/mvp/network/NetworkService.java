package id.ac.polban.jtk.cometogarut.mvp.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Akses IRestService ke WEB SERVER REST API
 * @author Mufid Jamaluddin
 */
public class NetworkService
{

    private IRestService restService;

    public NetworkService()
    {
        String SERVICE_ENDPOINT = "http://cometogarut.gaetcita.com/api/";

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SERVICE_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        restService = retrofit.create(IRestService.class);
    }

    public IRestService getAPI()
    {
        return this.restService;
    }

}
