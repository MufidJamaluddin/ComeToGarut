package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SendSuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Presenter u/ mengirim
 * @author Mufid Jamaluddin
 */
public class SendSuggestionPlacePresenter extends BasePresenter<SendSuggestionPlaceContract.View> implements SendSuggestionPlaceContract.Presenter
{
    private CompositeDisposable compositeDisposable;

    public SendSuggestionPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Mengirimkan masukkan yang ditulis user
     *
     * @param userSuggestion;
     */
    @Override
    public void sendSuggestion(Suggestion userSuggestion)
    {
        this.view.showLoading();

        Observable<Message> messageObservable = ((CgApplication) this.view.getApplication())
                .getNetworkService().getAPI().sendSuggestion(userSuggestion);

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

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {
        this.compositeDisposable.clear();
    }
}
