package id.ac.polban.jtk.cometogarut.mvp.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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
import id.ac.polban.jtk.cometogarut.mvp.contract.SearchPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.presenter.SearchPlacePresenter;

public class MainActivity extends AppCompatActivity implements SearchPlaceContract.View, SearchView.OnQueryTextListener
{
    private SearchPlacePresenter presenter;
    private ProgressWheel progressWheel;
    private SearchPlaceAdapter searchPlaceAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.presenter = new SearchPlacePresenter();
        this.presenter.attach(this);

        this.progressWheel = findViewById(R.id.progress_wheel);

        this.searchPlaceAdapter = new SearchPlaceAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView recycleView = findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(layoutManager);
        recycleView.setAdapter(this.searchPlaceAdapter);
    }

    @Override
    protected void onDestroy()
    {
        this.presenter.detach();
        super.onDestroy();
    }

    @Override
    public void showResults(List<SimplePlace> list)
    {
        this.searchPlaceAdapter.setList(list);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    @Override
    public boolean onQueryTextSubmit(String query)
    {
        this.presenter.startSearch(query);
        return true;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText)
    {
        if(newText.length() > 3)
        {
            this.presenter.startSearch(newText);
        }
        return true;
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

        SearchPlaceAdapter()
        {
            this.list = new ArrayList();
        }

        public void setList(List<SimplePlace> list)
        {
            this.list.clear();
            this.list.addAll(list);
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
