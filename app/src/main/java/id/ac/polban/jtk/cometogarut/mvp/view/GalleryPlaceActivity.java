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
import id.ac.polban.jtk.cometogarut.mvp.contract.GalleryPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;
import id.ac.polban.jtk.cometogarut.mvp.presenter.GalleryPlacePresenter;

public class GalleryPlaceActivity extends AppCompatActivity implements GalleryPlaceContract.View
{
    // Presenter yang berhubungan dengan View ini
    private GalleryPlacePresenter presenter;
    // Progress....
    private ProgressWheel progressWheel;
    // Adapter RecycleView dg List CardView
    private GalleryPlaceAdaper galleryPlaceAdaper;

    /**
     * Dipanggil ketika activity dijalankan
     * @param savedInstanceState : instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_gallery_place);

        this.progressWheel = findViewById(R.id.progress_wheel);

        this.showLoading();

        this.presenter = new GalleryPlacePresenter();

        this.presenter.attach(this);

        Integer place_id = getIntent().getIntExtra("place_id", 1);
        String title = getIntent().getStringExtra("title");

        ActionBar actionBar = super.getSupportActionBar();

        if(title != null && actionBar != null)
            actionBar.setTitle(title);

        this.galleryPlaceAdaper = new GalleryPlaceAdaper();

        RecyclerView recycleView = findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.setAdapter(this.galleryPlaceAdaper);

        this.presenter.startLoadGalleries(place_id.toString());
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
    public void showResults(List<Gallery> list)
    {
        this.galleryPlaceAdaper.setList(list);
    }

    /**
     * Class ViewHolder untuk Satuan CardView
     */
    private static class GalleryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView titleView;
        public ImageView imageView;

        GalleryViewHolder(View itemView)
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
    private static class GalleryPlaceAdaper extends RecyclerView.Adapter<GalleryViewHolder>
    {
        List<Gallery> list;

        /**
         * Konstruktor
         */
        GalleryPlaceAdaper()
        {
            this.list = new ArrayList<>();
        }

        /**
         * Menset List ke View
         * @param list : list data yg akan ditampilkan
         */
        void setList(List<Gallery> list)
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
        public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item, parent, false);
            return new GalleryViewHolder(view);
        }

        /**
         *
         * @param holder : view holder
         * @param position : posisi list ke-
         */
        @Override
        public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position)
        {
            Gallery gallery = list.get(position);
            holder.titleView.setText(gallery.getDescription());

            if (TextUtils.isEmpty(gallery.getLink_photo()))
            {
                holder.imageView.setVisibility(View.GONE);
            }
            else {
                holder.imageView.setVisibility(View.VISIBLE);
                Glide.with(holder.imageView.getContext())
                        .load(gallery.getLink_photo())
                        .into(holder.imageView);

                holder.imageView.setContentDescription(gallery.getDescription());
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
