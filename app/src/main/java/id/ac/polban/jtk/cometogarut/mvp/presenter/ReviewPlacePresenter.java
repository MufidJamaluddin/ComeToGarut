package id.ac.polban.jtk.cometogarut.mvp.presenter;

import id.ac.polban.jtk.cometogarut.mvp.contract.ReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;

public class ReviewPlacePresenter extends BasePresenter<ReviewPlaceContract.View> implements ReviewPlaceContract.Presenter
{

    /**
     * Memulai Meload List Ratings dari Network ke Activity
     */
    @Override
    public void startLoadRatings()
    {

    }

    /**
     * Mengirimkan review yang ditulis user
     *
     * @param userReview
     */
    @Override
    public void sendReview(Review userReview)
    {

    }

    /**
     * Melakukan unbinding View di Presenter
     */
    @Override
    public void detach()
    {

    }
}
