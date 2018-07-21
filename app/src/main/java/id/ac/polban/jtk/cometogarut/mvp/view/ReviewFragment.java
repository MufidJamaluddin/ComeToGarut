package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.ReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import id.ac.polban.jtk.cometogarut.mvp.presenter.ReviewPlacePresenter;

/**
 * Created by rohmatdasuki on 7/14/2018.
 * Edit by Mufid Jamaluddin on 7/16/2018
 */
public class ReviewFragment extends BaseFragment implements ReviewPlaceContract.View
{
    // preenter
    private ReviewPlacePresenter presenter;
    // adapter
    private ReviewPlaceAdaper adaper;

    /**
     * Membuat View dlm Fragment
     *
     * @param inflater : inflanter layout
     * @param container : viewgroup
     * @param savedInstanceState : state
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        //this.showLoading();

        View view;
        view = inflater.inflate(R.layout.fragment_list_review, container, false);

        this.presenter = new ReviewPlacePresenter();
        this.adaper = new ReviewPlaceAdaper();

        RecyclerView recycleView = view.findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(super.getContext()));
        recycleView.setAdapter(this.adaper);

        this.presenter.attach(this);

        this.presenter.startLoadReviews(super.getPlaceId().toString());

        return view;
    }

    /**
     * Menghancurkan View
     */
    @Override
    public void onDestroyView()
    {
        this.presenter.detach();
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
        if(this.getView() != null)
            Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Menampilkan Semua Review
     *
     * @param list list review
     */
    @Override
    public void showResults(List<Review> list)
    {
        this.adaper.setList(list);
    }

    /**
     * Class ViewHolder untuk Satuan CardView
     */
    private static class ReviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView userName;
        TextView userEmail;
//        TextView userMessage;

        RatingBar ratingCleanliness;
        RatingBar ratingSecurity;
        RatingBar ratingOrderly;
        RatingBar ratingFacility;

        View itemView;

        ReviewViewHolder(View itemView)
        {
            super(itemView);

            this.itemView = itemView;

            this.userName = itemView.findViewById(R.id.user_name);
            this.userEmail = itemView.findViewById(R.id.user_email);
//            this.userMessage = itemView.findViewById(R.id.user_description);

            this.ratingCleanliness = itemView.findViewById(R.id.rating_cleanliness);
            this.ratingSecurity = itemView.findViewById(R.id.rating_security);
            this.ratingOrderly = itemView.findViewById(R.id.rating_orderly);
            this.ratingFacility = itemView.findViewById(R.id.rating_facility);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(view.getContext(), this.userName.getText(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Adapter untuk CardView
     */
    private static class ReviewPlaceAdaper extends RecyclerView.Adapter<ReviewViewHolder>
    {
        List<Review> list;

        /**
         * Konstruktor
         */
        ReviewPlaceAdaper()
        {
            this.list = new ArrayList<>();
        }

        /**
         * Menset List ke View
         * @param list : list data yg akan ditampilkan
         */
        void setList(List<Review> list)
        {
            this.list.clear();
            this.list.addAll(list);
            super.notifyDataSetChanged();
        }

        /**
         *
         * @param parent : parent
         * @param viewType : viewType
         * @return view holder
         */
        @NonNull
        @Override
        public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_review_item, parent, false);
            return new ReviewViewHolder(view);
        }

        /**
         *
         * @param holder : view holder
         * @param position : posisi list ke-
         */
        @Override
        public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position)
        {
            Review review =  this.list.get(position);

            holder.userName.setText(review.getName());
            holder.userEmail.setText(review.getEmail());
    //        holder.userMessage.setText(review.getMessage());

            try
            {
                holder.ratingFacility.setRating(Float.parseFloat(String.valueOf(review.getFacility_rate())));
                holder.ratingOrderly.setRating(Float.parseFloat(String.valueOf(review.getPolicy_rate())));
                holder.ratingSecurity.setRating(Float.parseFloat(String.valueOf(review.getSecurity_rate())));
                holder.ratingCleanliness.setRating(Float.parseFloat(String.valueOf(review.getPurity_rate())));
            }
            catch (Exception e)
            {
                Snackbar.make(holder.itemView, "Error Parse : " + e.getMessage(), Snackbar.LENGTH_LONG).show();
            }
        }

        /**
         * @return jml isi list
         */
        @Override
        public int getItemCount()
        {
            return this.list.size();
        }
    }
}

