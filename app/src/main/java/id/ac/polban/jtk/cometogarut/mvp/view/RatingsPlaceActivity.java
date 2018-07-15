package id.ac.polban.jtk.cometogarut.mvp.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;
import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.RatingsPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import id.ac.polban.jtk.cometogarut.mvp.presenter.RatingsPlacePresenter;
/**
 * Created by rohmatdasuki on 7/14/2018.
 */


public class RatingsPlaceActivity extends AppCompatActivity implements RatingsPlaceContract.View
{
    // Presenter yang berhubungan dengan View ini
    private RatingsPlacePresenter presenter;
    // Progress....
    private ProgressWheel progressWheel;
    // Adapter RecycleView dg List CardView
    private RatingsPlaceAdaper RatingsPlaceAdaper;

    /**
     * Dipanggil ketika activity dijalankan
     * @param savedInstanceState : instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.rating_info_wisata);

        this.progressWheel = findViewById(R.id.progress_wheel);

        //this.showLoading();

        this.presenter = new RatingsPlacePresenter();

        this.presenter.attach(this);

        Integer place_id = getIntent().getIntExtra("place_id", 1);
        String title = getIntent().getStringExtra("title");

        ActionBar actionBar = super.getSupportActionBar();

        if(title != null && actionBar != null)
            actionBar.setTitle(title);

        this.RatingsPlaceAdaper = new RatingsPlaceAdaper();
/*
        RecyclerView recycleView = findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(this.RatingsPlaceAdaper);

        this.presenter.startLoadGalleries(place_id.toString());*/
    }

    /**
     * Dipanggil ketika activity di-kill
     */
    @Override
    protected void onDestroy()
    {
        this.presenter.detach();
        super.onDestroy();
    }

    /**
     * Menampilkan Loading
     */
    @Override
    public void showLoading()
    {
        this.progressWheel.setVisibility(View.VISIBLE);
        this.progressWheel.spin();

        ValueAnimator progressFadeInAnim = ObjectAnimator.ofFloat(progressWheel, "alpha", 0, 1, 1);
        progressFadeInAnim.start();
    }

    /**
     * Menyembunyikan Loading
     */
    @Override
    public void hideLoading()
    {
        this.progressWheel.stopSpinning();
        this.progressWheel.setVisibility(View.GONE);

        ValueAnimator progressFadeInAnim = ObjectAnimator.ofFloat(progressWheel, "alpha", 1, 0, 0);
        progressFadeInAnim.start();
    }

    /**
     * Menampilkan pesan error
     *
     * @param message : pesan error
     */
    @Override
    public void showError(String message)
    {
        /* Snackbar.make(this, message, Snackbar.LENGTH_LONG).show(); */
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
    /**
     *
     * @param list : list hasil dari internet
     */
    @Override
    public void showResults(List<Review> list)
    {
        this.RatingsPlaceAdaper.setList(list);
    }

    /**
     * Class ViewHolder untuk Satuan CardView
     */
    private static class RatingsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView titleView;
        public ImageView imageView;

        RatingsViewHolder(View itemView)
        {
            super(itemView);
            this.titleView = itemView.findViewById(R.id.titleView);
            this.imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            Toast.makeText(view.getContext(), this.titleView.getText(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Adapter untuk CardView
     */
    private static class RatingsPlaceAdaper extends RecyclerView.Adapter<RatingsViewHolder>
    {
        List<Review> list;

        /**
         * Konstruktor
         */
        RatingsPlaceAdaper()
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
        public RatingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rating_info_wisata, parent, false);
            return new RatingsViewHolder(view);
        }

        /**
         *
         * @param holder : view holder
         * @param position : posisi list ke-
         */
        @Override
        public void onBindViewHolder(@NonNull RatingsViewHolder holder, int position)
        {
            Review Ratings = list.get(position);
            holder.titleView.setText(Ratings.getMessage());

            if (TextUtils.isEmpty(Ratings.getName()))
            {
                holder.imageView.setVisibility(View.GONE);
            }
            else {
                holder.imageView.setVisibility(View.VISIBLE);
                Glide.with(holder.imageView.getContext())
                        .load(Ratings.getName())
                        .into(holder.imageView);

                holder.imageView.setContentDescription(Ratings.getMessage());
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

