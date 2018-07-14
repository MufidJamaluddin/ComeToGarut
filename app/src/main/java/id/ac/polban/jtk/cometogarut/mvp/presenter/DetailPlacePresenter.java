package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.DetailPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;
import id.ac.polban.jtk.cometogarut.mvp.model.RespObj;
import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter u/ mendapatkan DetailPlace berdasarkan place_id
 * @author Mufid Jamaluddin
 */
public class DetailPlacePresenter extends BasePresenter<DetailPlaceContract.View> implements DetailPlaceContract.Presenter
{
    // Koleksi untuk Unsubscribe // kill
    private CompositeDisposable compositeDisposable;

    /**
     * Konstruktor
     */
    public DetailPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        // Clear
        this.compositeDisposable.clear();
    }

    /**
     * Memulai Load Data
     */
    @Override
    public void startLoad(String place_id)
    {
        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        Observable<RespObj<DetailPlace>> detailplace = restservice.getAPI().getPlace(place_id);

        Observable<RespObj<DetailPlace>> respObjObservable = detailplace.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        respObjObservable.subscribe(new Observer<RespObj<DetailPlace>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespObj<DetailPlace> detailPlaceRespObj)
            {
                DetailPlace detailPlace = detailPlaceRespObj.getData();

                // Tampilkan Judul Tempat Wisata di ActionBar
                view.showTitle(detailPlace.getName());

                view.showResult(detailPlace);
            }

            @Override
            public void onError(@NonNull Throwable e)
            {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete()
            {

            }
        });
    }
}
