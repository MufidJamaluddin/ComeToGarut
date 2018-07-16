package id.ac.polban.jtk.cometogarut.mvp.presenter;
import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.RatingsPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
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
 * Created by rohmatdasuki on 7/15/2018.
 */

public class RatingsPlacePresenter extends BasePresenter<RatingsPlaceContract.View> implements RatingsPlaceContract.Presenter
{
    // komposit
    private CompositeDisposable compositeDisposable;

    // Konstruktor
    public RatingsPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Memulai Meload Ratings dari Network ke Activity
     */
@Override
    public void startLoadGalleries(String place_id)
    {
       //  this.view.showLoading();

        NetworkService restservice = ((CgApplication) this.view.getApplication()).getNetworkService();
        Observable<RespList<Review>> Ratings = restservice.getAPI().getReviews(place_id);

        Observable<RespList<Review>> respListObservable = Ratings.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        respListObservable.subscribe(new Observer<RespList<Review>>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespList<Review> RatingsRespList)
            {
                view.showResults(RatingsRespList.getData());
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