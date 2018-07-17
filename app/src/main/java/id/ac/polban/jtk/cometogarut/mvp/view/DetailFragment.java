package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.DetailPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;
import id.ac.polban.jtk.cometogarut.mvp.presenter.DetailPlacePresenter;

/**
 * Activity u/ membuka detail tempat wisata
 * @author Mufid Jamaluddin dan Rohmat Dasuki
 */
public class DetailFragment extends BaseFragment implements DetailPlaceContract.View
{
    // Presenter
    private DetailPlacePresenter presenter;

    // Data2 View
    private ImageView placePhoto;
    private TextView placeName;
    private TextView placeAddress;
    private TextView placeTicketPrice;
    private TextView placeOpenDay;
    private TextView placeOpenTime;
    private TextView placeDescription;
    private TextView placeContact;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        this.showLoading();

        this.presenter = new DetailPlacePresenter();
        this.presenter.attach(this);

        View view = inflater.inflate(R.layout.activity_detail_place, container, false);

        this.placePhoto = view.findViewById(R.id.imageView);
        this.placeName = view.findViewById(R.id.nameView);
        this.placeAddress = view.findViewById(R.id.addressView);
        this.placeTicketPrice = view.findViewById(R.id.priceView);
        this.placeOpenDay = view.findViewById(R.id.opendayView);
        this.placeOpenTime = view.findViewById(R.id.opentimeView);
        this.placeDescription = view.findViewById(R.id.descriptionView);
        this.placeContact = view.findViewById(R.id.contactView);

        this.presenter.startLoad(super.getPlaceId().toString());

        return view;
    }

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
        Toast.makeText(this.getContext(), message, Toast.LENGTH_LONG).show();
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

        String openDay = detailPlace.getDay_begin() + " s.d " + detailPlace.getDay_end();
        this.placeOpenDay.setText(openDay);

        String openTime = detailPlace.getTime_begin() + " s.d " + detailPlace.getTime_end();
        this.placeOpenTime.setText(openTime);

        this.placeTicketPrice.setText(detailPlace.getPrice());
        this.placeAddress.setText(detailPlace.getAddress());
    }

    /**
     * @param title judul
     */
    @Override
    public void showTitle(String title)
    {
        DetailActivity activity = (DetailActivity) super.getActivity();

        if(activity != null)
        {
            activity.showTitle(title);
        }
    }

}
