package id.ac.polban.jtk.cometogarut.mvp.application;

import android.app.Application;

import id.ac.polban.jtk.cometogarut.mvp.network.NetworkService;

/**
 * Aplikasi inti
 * @author Mufid Jamaluddin
 */
public class CgApplication extends Application
{
    private NetworkService networkService;

    @Override
    public void onCreate()
    {
        super.onCreate();
        this.networkService = new NetworkService();
    }

    public NetworkService getNetworkService()
    {
        return networkService;
    }
}
