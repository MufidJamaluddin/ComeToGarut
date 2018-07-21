package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.ReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.RespList;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter u/ mengirim dan menerima list review pengguna
 * @author Mufid Jamaluddin
 */
public class ReviewPlacePresenter extends BasePresenter<ReviewPlaceContract.View> implements ReviewPlaceContract.Presenter
{
    private CompositeDisposable compositeDisposable;

    public ReviewPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Memulai Meload List Ratings dari Network ke Activity
     */
    @Override
    public void startLoadReviews(String place_id)
    {
    //    this.view.showLoading();

        Observable<RespList<Review>> reviews = ((CgApplication) this.view.getApplication())
                .getNetworkService().getAPI().getReviews(place_id);

        reviews.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RespList<Review>>() {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespList<Review> reviewRespList)
            {
                view.showResults(reviewRespList.getData());
            }

            @Override
            public void onError(@NonNull Throwable e)
            {
                view.showMessage("Error Load : " + e.getMessage());
            }

            @Override
            public void onComplete() {
                view.hideLoading();
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
    }
}
