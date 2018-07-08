package id.ac.polban.jtk.cometogarut.mvp.contract;

import android.app.Application;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;

/**
 * Menampilkan Semua / Hasil Pencarian
 * Tempat Wisata
 */
public interface SearchPlaceContract
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
        void showResults(List<SimplePlace> list);

    }

    interface Presenter
    {
        /**
         * Memulai Pencarian
         * @param searchKey : kata kunci
         */
        void startSearch(String searchKey);

        /**
         * Mendapatkan Semua Data
         */
        void getAll();

    }
}