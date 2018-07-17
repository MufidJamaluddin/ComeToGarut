package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import id.ac.polban.jtk.cometogarut.mvp.model.Review;

/**
 * Mengirimkan review dari User
 * @author Mufid Jamaluddin
 */
public interface SendReviewPlaceContract
{
    interface View
    {
        /**
         *
         * @return Application
         */
        Application getApplication();

        /**
         * Menampilkan Loading
         */
        void showLoading();

        /**
         * Menyembunyikan Loading
         */
        void hideLoading();

        /**
         * Menampilkan pesan
         * @param message : pesan yg akan ditampilkan, bisa error atau sukses
         */
        void showMessage(String message);
    }

    interface Presenter
    {
        /**
         * Mengirimkan review yang ditulis user
         */
        void sendReview(Review userReview);
    }
}
