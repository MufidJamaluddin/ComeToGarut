package id.ac.polban.jtk.cometogarut.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SearchPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPlacePresenter extends BasePresenter<SearchPlaceContract.View> implements SearchPlaceContract.Presenter
{
    // Koleksi untuk Unsubscribe
    private CompositeDisposable compositeDisposable;

    /**
     * Konstruktor
     */
    public SearchPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
        Log.d("SearchPlacePresenter", "Load Presenter...");
    }

    /**
     *
     * @param searchKey : kata kunci
     */
    @Override
    public void startSearch(String searchKey)
    {
        Log.d("SearchPlacePresenter", "StartSearch...");
        // loading..
        this.view.showLoading();

        if(TextUtils.isEmpty(searchKey))
            this.getAll();
        else {
            NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
            Observable<List<SimplePlace>> fplaces = restservice.getAPI().getPlaces(searchKey);
            this.mergeData(fplaces);
        }

        this.view.hideLoading();

        Log.d("SearchPlacePresenter", "EndSearch...");
    }

    /**
     * Mendapatkan Semua Data
     */
    @Override
    public void getAll()
    {
        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        Observable<List<SimplePlace>> fplaces = restservice.getAPI().getPlaces();
        this.mergeData(fplaces);
    }

    /**
     *
     * @param fplaces : Flowable
     */
    private void mergeData(Observable<List<SimplePlace>> fplaces)
    {
        Observable<List<SimplePlace>> observable = fplaces.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<SimplePlace>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(List<SimplePlace> list)
            {
                view.showResults(list);
                Log.d("SearchPlacePresenter", "Add All Data : onNext...");
            }

            @Override
            public void onError(Throwable e)
            {
                view.showError(e.getMessage());
                Log.d("SearchPlacePresenter", e.getMessage());
            }

            @Override
            public void onComplete()
            {
                Log.d("SearchPlacePresenter", "Search Completed...");
            }
        });
    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        this.compositeDisposable.clear();
        Log.d("SearchPlacePresenter", "Detach...");
    }

}