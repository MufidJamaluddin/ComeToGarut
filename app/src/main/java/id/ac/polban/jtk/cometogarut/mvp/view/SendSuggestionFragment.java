package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SendSuggestionPlaceContract;

public class SendSuggestionFragment extends BaseFragment implements SendSuggestionPlaceContract.View
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view;
        view = inflater.inflate(R.layout.fragment_send_suggestion, container, false);

        return view;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    /**
     * Menampilkan Loading
     */
    @Override
    public void showLoading()
    {

    }

    /**
     * Menyembunyikan Loading
     */
    @Override
    public void hideLoading()
    {

    }

    /**
     * Menampilkan pesan
     *
     * @param message : pesan yg akan ditampilkan, bisa error atau sukses
     */
    @Override
    public void showMessage(String message)
    {

    }

}