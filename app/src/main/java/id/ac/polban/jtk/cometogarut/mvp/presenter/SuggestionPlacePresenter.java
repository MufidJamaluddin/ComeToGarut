package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.contract.SuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;

public class SuggestionPlacePresenter extends BasePresenter<SuggestionPlaceContract.View> implements SuggestionPlaceContract.Presenter
{
    /**
     * Memulai Meload List Ratings dari Network ke Activity
     */
    @Override
    public void startLoadRatings() {

    }

    /**
     * Mengirimkan masukkan yang ditulis user
     *
     * @param userSuggestion
     */
    @Override
    public void sendReview(Suggestion userSuggestion) {

    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach() {

    }
}
