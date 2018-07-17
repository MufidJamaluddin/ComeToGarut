package id.ac.polban.jtk.cometogarut.mvp.presenter;
import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SendReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by rohmatdasuki on 7/15/2018.
 * Edit by MufidJamaluddin on 16/07/2018
 */
public class SendReviewPlacePresenter extends BasePresenter<SendReviewPlaceContract.View> implements SendReviewPlaceContract.Presenter
{
    // komposit
    private CompositeDisposable compositeDisposable;

    // Konstruktor
    public SendReviewPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        this.compositeDisposable.clear();
    }

    /**
     * Mengirimkan review yang ditulis user
     *
     * @param userReview : dikirim user ke server
     */
    @Override
    public void sendReview(Review userReview)
    {
        this.view.showLoading();

        Observable<Message> messageObservable = ((CgApplication) this.view.getApplication())
                .getNetworkService().getAPI().sendReview(userReview);

        messageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Message>()
        {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(Message message)
            {
                view.showMessage(message.getMessages());
            }

            @Override
            public void onError(Throwable e)
            {
                view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete()
            {
                view.hideLoading();
            }
        });
    }
}