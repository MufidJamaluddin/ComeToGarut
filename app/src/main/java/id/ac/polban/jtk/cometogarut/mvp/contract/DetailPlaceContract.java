package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;

/**
 * Menampilkan Detail Informasi Tempat Wisata
 * Berdasarkan place_id
 * @author Mufid Jamaluddin
 */
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
         * Menampilkan pesan error
         * @param message : pesan error
         */
        void showError(String message);

        /**
         * Menamilkan Hasil Pencarian
         * @param detailPlace : detail tempat
         */
        void showResult(DetailPlace detailPlace);

        /**
         *
         * @param title judul
         */
        void showTitle(String title);

        /**
         * Menyembunyikan Loading
         */
        void hideLoading();

        /**
         * Menampilkan Loading
         */
        void showLoading();
    }

    interface Presenter
    {
        /**
         * Memulai Load Data
         */
        void startLoad(String place_id);


    }
}
