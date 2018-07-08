package id.ac.polban.jtk.cometogarut.mvp.presenter;

import android.text.TextUtils;
import android.util.Log;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SearchPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.RespList;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchPlacePresenter extends BasePresenter<SearchPlaceContract.View> implements SearchPlaceContract.Presenter
{
    // Koleksi untuk Unsubscribe // kill
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

        if(TextUtils.isEmpty(searchKey))
            this.getAll();
        else {
            // loading..
            this.view.showLoading();

            NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
            Observable<RespList<SimplePlace>> fplaces = restservice.getAPI().getPlaces(searchKey);
            this.mergeData(fplaces);

            this.view.hideLoading();
        }

        Log.d("SearchPlacePresenter", "EndSearch...");
    }

    /**
     * Mendapatkan Semua Data
     */
    @Override
    public void getAll()
    {
        this.view.showLoading();

        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        Observable<RespList<SimplePlace>> fplaces = restservice.getAPI().getPlaces();
        this.mergeData(fplaces);

        this.view.hideLoading();
    }

    /**
     *
     * @param fplaces : Flowable
     */
    private void mergeData(Observable<RespList<SimplePlace>> fplaces)
    {
        Observable<RespList<SimplePlace>> observable = fplaces.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        observable.subscribe(new Observer<RespList<SimplePlace>>()
        {
            @Override
            public void onSubscribe(@NonNull Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespList<SimplePlace> simplePlaceRespList)
            {
                view.showResults(simplePlaceRespList.getData());
            }

            @Override
            public void onError(Throwable e)
            {
                if(e !=  null)
                {
                    view.showError(e.getMessage());
                    Log.d("SearchPlacePresenter", e.getMessage());
                }
                Log.d("Mulai error", "heheheegheheheh . . . ");
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
        Log.d("SearchPlacePresenter", "Detach...");
    }

}