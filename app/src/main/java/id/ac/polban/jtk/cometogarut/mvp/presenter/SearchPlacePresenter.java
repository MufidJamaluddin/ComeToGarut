package id.ac.polban.jtk.cometogarut.mvp.presenter;

import android.text.TextUtils;

import java.util.ArrayList;
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
    // Hasil Pencarian
    private List<SimplePlace> places;
    // Koleksi untuk Unsubscribe
    private CompositeDisposable compositeDisposable;

    /**
     * Konstruktor
     */
    public SearchPlacePresenter()
    {
        this.places = new ArrayList<>();
        this.compositeDisposable = new CompositeDisposable();
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
            Observable<List<SimplePlace>> fplaces = restservice.getAPI().getPlaces(searchKey);
            this.mergeData(fplaces);
        }

        this.view.hideLoading();
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
                places.clear();
                places.addAll(list);
            }

            @Override
            public void onError(Throwable e)
            {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete()
            {

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