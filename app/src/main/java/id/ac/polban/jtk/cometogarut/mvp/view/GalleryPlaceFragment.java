package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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

import java.util.ArrayList;
import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.GalleryPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;
import id.ac.polban.jtk.cometogarut.mvp.presenter.GalleryPlacePresenter;

/**
 * Activity u. list gallery tempat wisata tertentu
 * @author Mufid Jamaluddin
 */
public class GalleryPlaceFragment extends BaseFragment implements GalleryPlaceContract.View
{
    // Presenter yang berhubungan dengan View ini
    private GalleryPlacePresenter presenter;
    // Adapter RecycleView dg List CardView
    private GalleryPlaceAdaper galleryPlaceAdaper;

    /**
     * Dipanggil ketika activity dijalankan
     * @param savedInstanceState : instance state
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //this.showLoading();

        this.presenter = new GalleryPlacePresenter();

        this.presenter.attach(this);

        View view = inflater.inflate(R.layout.activity_gallery_place, container, false);

        this.galleryPlaceAdaper = new GalleryPlaceAdaper();

        RecyclerView recycleView = view.findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(super.getContext()));
        recycleView.setAdapter(this.galleryPlaceAdaper);

        this.presenter.startLoadGalleries(super.getPlaceId().toString());

        return view;
    }

    /**
     * Dipanggil ketika activity di-kill
     */
    @Override
    public void onDestroyView()
    {
        this.presenter.detach();
        super.onDestroyView();
    }

    /**
     * Menampilkan pesan error
     *
     * @param message : pesan error
     */
    @Override
    public void showError(String message)
    {
        if(this.getView() != null)
            Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG).show();
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
