package id.ac.polban.jtk.cometogarut.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.ReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;

/**
 * Created by rohmatdasuki on 7/14/2018.
 * Edit by Mufid Jamaluddin on 7/16/2018
 */
public class ReviewFragment extends BaseFragment implements ReviewPlaceContract.View
{

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View view;
        view = inflater.inflate(R.layout.fragment_list_review, container, false);

        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
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
     * Menampilkan Semua Review
     *
     * @param list list review
     */
    @Override
    public void showResults(List<Review> list)
    {

    }
}

