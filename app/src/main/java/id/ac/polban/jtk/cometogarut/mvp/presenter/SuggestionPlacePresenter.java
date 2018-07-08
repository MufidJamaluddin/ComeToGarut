package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.application.CgApplication;
import id.ac.polban.jtk.cometogarut.mvp.contract.SuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.RespList;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SuggestionPlacePresenter extends BasePresenter<SuggestionPlaceContract.View> implements SuggestionPlaceContract.Presenter
{
    private CompositeDisposable compositeDisposable;

    public SuggestionPlacePresenter()
    {
        this.compositeDisposable = new CompositeDisposable();
    }

    /**
     * Memulai Meload List Ratings dari Network ke Activity
     */
    @Override
    public void startLoadSuggestions(String place_id)
    {
        this.view.showLoading();

        Observable<RespList<Suggestion>> suggestions = ((CgApplication) this.view.getApplication())
                .getNetworkService().getAPI().getSuggestions(place_id);

        suggestions.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<RespList<Suggestion>>() {
            @Override
            public void onSubscribe(Disposable d)
            {
                compositeDisposable.add(d);
            }

            @Override
            public void onNext(@NonNull RespList<Suggestion> reviewRespList)
            {
                view.showResults(reviewRespList.getData());
            }

            @Override
            public void onError(@NonNull Throwable e)
            {
                view.showMessage(e.getMessage());
            }

            @Override
            public void onComplete()
            {

            }
        });

        this.view.hideLoading();
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

        messageObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Message>() {
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
