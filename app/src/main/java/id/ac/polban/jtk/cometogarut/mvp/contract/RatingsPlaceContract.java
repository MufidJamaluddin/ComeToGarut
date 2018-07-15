package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.Review;

/**
 * Created by rohmatdasuki on 7/14/2018.
 */

public interface RatingsPlaceContract {

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
         * Menampilkan pesan error
         * @param message : pesan error
         */
        void showError(String message);

        /**
         * Menampilkan Hasil Pencarian
         */
        void showResults(List<Review> list);

    }

    interface Presenter
    {
        /**
         * Memulai Meload Ratings dari Network ke Activity
         */
        void startLoadGalleries(String place_id);
    }
}
