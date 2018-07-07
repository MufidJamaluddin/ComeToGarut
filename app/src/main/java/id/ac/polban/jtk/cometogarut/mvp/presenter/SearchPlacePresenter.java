package id.ac.polban.jtk.cometogarut.mvp.presenter;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SearchPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPlacePresenter extends BasePresenter<SearchPlaceContract.View> implements SearchPlaceContract.Presenter
{
    // Hasil Pencarian
    private List<SimplePlace> places;

    /**
     * Konstruktor
     */
    public SearchPlacePresenter()
    {
        this.places = new ArrayList<>();
    }

    /**
     *
     * @param searchKey : kata kunci
     */
    @Override
    public void startSearch(String searchKey)
    {
        // loading..
        this.view.showLoading();

        if(TextUtils.isEmpty(searchKey))
            this.getAll();
        else {
            NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
            restservice.getAPI().getPlaces().enqueue(new Callback<List<SimplePlace>>() {

                @Override
                public void onResponse(@NonNull Call<List<SimplePlace>> call, @NonNull Response<List<SimplePlace>> response)
                {
                    if(response.isSuccessful())
                    {
                        getPlaces().clear();
                        List<SimplePlace> list = response.body();
                        if(list != null) getPlaces().addAll(list);
                    }
                    else
                    {
                        view.showError("Not Found!");
                    }
                }

                @Override
                public void onFailure(@NonNull Call<List<SimplePlace>> call, @NonNull Throwable t)
                {
                    view.showError(t.getMessage());
                }
            });
        }

        // Tampilkan Hasil
        this.view.showResults();

        // Tutup Loading
        this.view.hideLoading();
    }

    /**
     * Mendapatkan Semua Data
     */
    @Override
    public void getAll()
    {
        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        restservice.getAPI().getPlaces().enqueue(new Callback<List<SimplePlace>>() {
            @Override
            public void onResponse(@NonNull Call<List<SimplePlace>> call, @NonNull Response<List<SimplePlace>> response)
            {
                if(response.isSuccessful())
                {
                    List<SimplePlace> list = response.body();
                    getPlaces().clear();
                    if(list != null)  getPlaces().addAll(list);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<SimplePlace>> call, @NonNull Throwable t)
            {
                view.showError(t.getMessage());
            }
        });

    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        this.places = null;
    }

    /**
     * Mendapatkan List Hasil Pencarian
     * @return hasil pencarian
     */
    public List<SimplePlace> getPlaces()
    {
        return places;
    }
}
