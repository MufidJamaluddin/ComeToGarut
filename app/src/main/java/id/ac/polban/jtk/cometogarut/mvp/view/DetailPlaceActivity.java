package id.ac.polban.jtk.cometogarut.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.DetailPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;
import id.ac.polban.jtk.cometogarut.mvp.presenter.DetailPlacePresenter;

public class DetailPlaceActivity extends AppCompatActivity implements DetailPlaceContract.View
{
    // Presenter
    private DetailPlacePresenter presenter;
    // Action Bar
    private ActionBar actionBar;
    // Data2 View
    private ImageView placePhoto;
    private TextView placeName;
    private TextView placeAddress;
    private TextView placeTicketPrice;
    private TextView placeTimeBegin;
    private TextView placeTimeEnd;
    private TextView placeDescription;
    private TextView placeContact;

    // place id
    private Integer place_id;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_place);

        this.presenter = new DetailPlacePresenter();
        this.presenter.attach(this);

        this.actionBar = super.getSupportActionBar();

        this.placePhoto = super.findViewById(R.id.imageView);
        this.placeName = super.findViewById(R.id.nameView);
        this.placeAddress = super.findViewById(R.id.addressView);
        this.placeTicketPrice = super.findViewById(R.id.priceView);

        this.placeTimeBegin = super.findViewById(R.id.time_beginView);
        this.placeTimeEnd = super.findViewById(R.id.time_endView);
        this.placeDescription = super.findViewById(R.id.descriptionView);
        this.placeContact = super.findViewById(R.id.contactView);

        Button showGalleries = super.findViewById(R.id.btn_galleries);

        //Button showSuggestions = super.findViewById(R.id.btn_suggestions);
        //Button showRatings = super.findViewById(R.id.btn_ratings);

        this.place_id = getIntent().getIntExtra("place_id", 1);

        showGalleries.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(DetailPlaceActivity.this, GalleryPlaceActivity.class);
                intent.putExtra("place_id", place_id);
                view.getContext().startActivity(intent);
            }
        });

        // initialize
        this.presenter.startLoad(place_id.toString());
    }

    @Override
    protected void onDestroy()
    {
        this.presenter.detach();
        super.onDestroy();
    }

    /**
     * Menampilkan pesan error
     *
     * @param message : pesan error
     */
    @Override
    public void showError(String message)
    {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * Menamilkan Hasil Pencarian
     *
     * @param detailPlace : detail place
     */
    @Override
    public void showResult(DetailPlace detailPlace)
    {
        Glide.with(this)
                .load(detailPlace.getLink_photo())
                .into(this.placePhoto);

        this.placeName.setText(detailPlace.getName());
        this.placeContact.setText(detailPlace.getContact());
        this.placeDescription.setText(detailPlace.getDescription());

        String begin = detailPlace.getDay_begin() + " at " + detailPlace.getTime_begin();
        this.placeTimeBegin.setText(begin);

        String end = detailPlace.getDay_end() + " at " + detailPlace.getTime_end();
        this.placeTimeEnd.setText(end);

        this.placeTicketPrice.setText(detailPlace.getPrice());
        this.placeAddress.setText(detailPlace.getAddress());
    }

    /**
     * Mengubah judul Action Bar
     *
     * @param title : judul
     */
    @Override
    public void showTitle(String title)
    {
        this.actionBar.setTitle(title);
    }

}
