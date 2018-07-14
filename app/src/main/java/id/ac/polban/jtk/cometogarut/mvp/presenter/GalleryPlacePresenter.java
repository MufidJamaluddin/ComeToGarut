package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.GalleryPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;
import id.ac.polban.jtk.cometogarut.mvp.model.RespList;
import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter u/ mendapatkan Gallery tempat tertentu
 * @author Mufid Jamaluddin
 */
public class GalleryPlacePresenter extends BasePresenter<GalleryPlaceContract.View> implements GalleryPlaceContract.Presenter
{
    // komposit
    private CompositeDisposable compositeDisposable;

    // Konstruktor
    public GalleryPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Memulai Meload Gallery dari Network ke Activity
     */
    @Override
    public void startLoadGalleries(String place_id)
    {
    //    this.view.showLoading();

        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        Observable<RespList<Gallery>> galleries = restservice.getAPI().getGalleries(place_id);

        Observable<RespList<Gallery>> respListObservable = galleries.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        respListObservable.subscribe(new Observer<RespList<Gallery>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespList<Gallery> galleryRespList)
            {
                view.showResults(galleryRespList.getData());
            }

            @Override
            public void onError(@NonNull Throwable e)
            {
                view.showError(e.getMessage());
            }

            @Override
            public void onComplete()
            {
                view.hideLoading();
            }
        });

      //  this.view.hideLoading();
    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        this.compositeDisposable.clear();
    }
}