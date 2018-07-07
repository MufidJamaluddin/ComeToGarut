package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;

public interface DetailPlaceContract
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
         * Menampilkan pesan error
         * @param message : pesan error
         */
        void showError(String message);

        /**
         * Menamilkan Hasil Pencarian
         * @param list : list tempat
         */
        void showResult(SimplePlace simplePlace);

        /**
         * Mengubah judul Action Bar
         * @param title
         */
        void showTitle(String title);
    }

    interface Presenter
    {
        /**
         * Memulai Load Data
         */
        void startLoad();
    }
}
