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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;
import id.ac.polban.jtk.cometogarut.mvp.presenter.SuggestionPlacePresenter;

/**
 * Melihat list Suggestion
 * @author Mufid Jamaluddin
 */
public class SuggestionFragment extends BaseFragment implements SuggestionPlaceContract.View
{
    // preenter
    private SuggestionPlacePresenter presenter;
    // adapter
    private SuggestionPlaceAdaper adaper;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        //this.showLoading();

        View view;
        view = inflater.inflate(R.layout.fragment_list_suggestion, container, false);

        this.presenter = new SuggestionPlacePresenter();
        this.adaper = new SuggestionPlaceAdaper();

        RecyclerView recycleView = view.findViewById(R.id.recyclerView);
        recycleView.setLayoutManager(new LinearLayoutManager(super.getContext()));
        recycleView.setAdapter(this.adaper);

        this.presenter.attach(this);

        this.presenter.startLoadSuggestions(super.getPlaceId().toString());

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
     * Menampilkan Semua Saran dari User
     *
     * @param list : list users
     */
    @Override
    public void showResults(List<Suggestion> list)
    {
        this.adaper.setList(list);
    }

    /**
     * Class ViewHolder untuk Satuan CardView
     */
    private static class SuggestionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView userName;
        TextView userEmail;
        TextView userDescription;

        SuggestionViewHolder(View itemView)
        {
            super(itemView);
            this.userName = itemView.findViewById(R.id.user_name);
            this.userEmail = itemView.findViewById(R.id.user_email);
            this.userDescription = itemView.findViewById(R.id.user_description);
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
    private static class SuggestionPlaceAdaper extends RecyclerView.Adapter<SuggestionViewHolder>
    {
        List<Suggestion> list;

        /**
         * Konstruktor
         */
        SuggestionPlaceAdaper()
        {
            this.list = new ArrayList<>();
        }

        /**
         * Menset List ke View
         * @param list : list data yg akan ditampilkan
         */
        void setList(List<Suggestion> list)
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
        public SuggestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
        {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_suggestion_item, parent, false);
            return new SuggestionViewHolder(view);
        }

        /**
         *
         * @param holder : view holder
         * @param position : posisi list ke-
         */
        @Override
        public void onBindViewHolder(@NonNull SuggestionViewHolder holder, int position)
        {
            Suggestion suggestion =  this.list.get(position);

            holder.userName.setText(suggestion.getName());
            holder.userEmail.setText(suggestion.getEmail());
            holder.userDescription.setText(suggestion.getDescription());
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