package id.ac.polban.jtk.cometogarut.mvp.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.SearchManager;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SearchPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.presenter.SearchPlacePresenter;

public class MainActivity extends AppCompatActivity implements SearchPlaceContract.View, SwipeRefreshLayout.OnRefreshListener
{
    private SearchPlacePresenter presenter;
    private String searchKey;
    private ProgressWheel progressWheel;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchPlaceAdapter searchPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new SearchPlacePresenter();
        this.presenter.attach(this);

        this.progressWheel = findViewById(R.id.progress_wheel);
        this.swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        this.swipeRefreshLayout.setOnRefreshListener(this);

        Intent intent = getIntent();
        if(Intent.ACTION_SEARCH.equals(intent.getAction()))
        {
            this.searchKey = intent.getStringExtra(SearchManager.QUERY);
        }

        this.searchPlaceAdapter = new SearchPlaceAdapter(this.presenter.getPlaces());
    }

    @Override
    protected void onDestroy()
    {
        this.presenter.detach();
        super.onDestroy();
    }

    @Override
    public void showResults()
    {
        this.searchPlaceAdapter.notifyDataSetChanged();
    }

    /**
     * Menampilkan Loading
     *
     */
    @Override
    public void showLoading()
    {
        this.progressWheel.setVisibility(View.VISIBLE);
        this.swipeRefreshLayout.setVisibility(View.GONE);
        // use objectAnimator let progress from InVisible to Visible
        ValueAnimator progressFadeInAnim = ObjectAnimator.ofFloat(progressWheel, "alpha", 0, 1, 1);
        progressFadeInAnim.start();
    }

    /**
     * Menyembunyikan Loading
     */
    @Override
    public void hideLoading()
    {
        progressWheel.setVisibility(View.GONE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(false); // close refresh animator

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
        // use snackbar to replace Toast to show Error message
        Snackbar.make(swipeRefreshLayout, message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    @Override
    public void onRefresh()
    {
        this.presenter.startSearch(this.searchKey);
    }

    /**
     * Class ViewHolder untuk Satuan CardView
     */
    private static class SearchViewHolder extends RecyclerView.ViewHolder
    {
        public TextView titleView;
        public ImageView imageView;

        SearchViewHolder(View itemView)
        {
            super(itemView);
            this.titleView = itemView.findViewById(R.id.titleView);
            this.imageView = itemView.findViewById(R.id.imageView);
        }
    }

    /**
     * Adapter untuk Card View
     */
    public static class SearchPlaceAdapter extends RecyclerView.Adapter<SearchViewHolder>
    {
        private List<SimplePlace> list;

        SearchPlaceAdapter(List<SimplePlace> list)
        {
            this.list = list;
        }

        @NonNull
        @Override
        public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
            return new SearchViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchViewHolder holder, int position)
        {
            SimplePlace place = list.get(position);
            holder.titleView.setText(place.getName());

            if (TextUtils.isEmpty(place.getLink_photo()))
            {
                holder.imageView.setVisibility(View.GONE);
            }
            else {
                holder.imageView.setVisibility(View.VISIBLE);
                Glide.with(holder.imageView.getContext())
                        .load(place.getLink_photo())
                        .into(holder.imageView);
            }
        }

        @Override
        public int getItemCount()
        {
            return list.size();
        }
    }
}
