package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.Review;

/**
 * Menampilkan list review (max 5 terbaru)
 * Mengirimkan review dari User
 */
public interface ReviewPlaceContract
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

        /**
         * Menampilkan Semua Review
         */
        void showResults(List<Review> list);

    }

    interface Presenter
    {
        /**
         * Memulai Meload List Ratings dari Network ke Activity
         */
        void startLoadRatings();

        /**
         * Mengirimkan review yang ditulis user
         */
        void sendReview(Review userReview);
    }
}
