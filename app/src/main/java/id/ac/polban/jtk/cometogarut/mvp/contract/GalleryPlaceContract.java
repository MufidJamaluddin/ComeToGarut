package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;

/**
 * Menampilkan List Gallery Tempat Wisata
 * Tertentu, Berdasarkan place_id
 */
public interface GalleryPlaceContract
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
         * Menampilkan Hasil Pencarian
         */
        void showResults(List<Gallery> list);

    }

    interface Presenter
    {
        /**
         * Memulai Meload Gallery dari Network ke Activity
         */
        void startLoadGalleries(String place_id);
    }
}
