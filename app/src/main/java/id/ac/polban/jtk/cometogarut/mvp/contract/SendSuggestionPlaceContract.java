package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;

/**
 * Mengirim Masukkan dari User ke Web Server
 * @author Mufid Jamaluddin
 */
public interface SendSuggestionPlaceContract
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
         * Mengirimkan masukkan yang ditulis user
         */
        void sendSuggestion(Suggestion userSuggestion);
    }
}
