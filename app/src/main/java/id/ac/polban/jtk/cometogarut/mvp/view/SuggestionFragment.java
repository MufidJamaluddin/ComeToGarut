package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;

public class SuggestionFragment extends BaseFragment implements SuggestionPlaceContract.View
{
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view;
        view = inflater.inflate(R.layout.fragment_list_suggestion, container, false);

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

    /**
     * Menampilkan Semua Saran dari User
     *
     * @param list : list users
     */
    @Override
    public void showResults(List<Suggestion> list)
    {

    }
}