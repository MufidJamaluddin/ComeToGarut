package id.ac.polban.jtk.cometogarut.mvp.view;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment
{
    /**
     *
     * @return Base Fragment
     */
    public BaseFragmentActivity getBaseFragment()
    {
        return (BaseFragmentActivity) super.getActivity();
    }

    /**
     * @return Application
     */
    public Application getApplication()
    {
        Activity activity = super.getActivity();

        if(activity != null)
            return activity.getApplication();
        else
            return null;
    }

    /**
     * Menampilkan Judul
     * @param title : judul
     */
    public void showTitle(String title)
    {
        this.getBaseFragment().showTitle(title);
    }

    /**
     * Menampilkan Animasi Loading
     */
    public void showLoading()
    {
        this.getBaseFragment().showLoading();
    }

    /**
     * Menyembunyikan Animasi Loading
     */
    public void hideLoading()
    {
        this.getBaseFragment().hideLoading();
    }

    /**
     * Mendapatkan Place_id
     * @return Place_id : ID tempat wisata
     */
    public Integer getPlaceId()
    {
        return this.getBaseFragment().getPlace_id();
    }
}