package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;

/**
 * Menampilkan List Masukkan
 * @author Mufid Jamaluddin
 */
public interface SuggestionPlaceContract
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
         * Menampilkan Semua Saran dari User
         */
        void showResults(List<Suggestion> list);

    }

    interface Presenter
    {
        /**
         * Memulai Meload List Suggestions dari Network ke Activity
         */
        void startLoadSuggestions(String place_id);
    }
}
