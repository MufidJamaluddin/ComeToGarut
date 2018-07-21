package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SendReviewPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import id.ac.polban.jtk.cometogarut.mvp.presenter.SendReviewPlacePresenter;

/**
 * Fragment untuk mengirimkan Review User
 */
public class SendReviewFragment extends BaseFragment implements SendReviewPlaceContract.View
{
    private RatingBar securityRating;
    private RatingBar cleanlinessRating;
    private RatingBar facilityRating;
    private RatingBar orderlyRating;
    private EditText nameField;
    private EditText emailField;

    private SendReviewPlacePresenter presenter;

    /**
     * Membuat View dlm ragment
     * @param inflater : inflanter view
     * @param container : viewgroup
     * @param savedInstanceState : state
     * @return view
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_send_review, container, false);

        this.presenter = new SendReviewPlacePresenter();
        this.presenter.attach(this);

        this.securityRating = view.findViewById(R.id.rating_security);

        this.cleanlinessRating = view.findViewById(R.id.rating_cleanliness);

        this.facilityRating = view.findViewById(R.id.rating_facility);

        this.orderlyRating = view.findViewById(R.id.rating_orderly);

        this.nameField = view.findViewById(R.id.user_name);

        this.emailField = view.findViewById(R.id.user_email);
        /*
         * Klik Tombol Kirim
         */
        view.findViewById(R.id.but).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                /*
                 * Buat Objek Review Baru
                 * dan Inisialisasi Atribut
                 */
                Review review = new Review();

                review.setName(String.valueOf(nameField.getText()));
                review.setEmail(String.valueOf(emailField.getText()));
                review.setPlace_id(getPlaceId().toString());
                review.setSecurity_rate(String.valueOf(securityRating.getRating()));
                review.setPolicy_rate(String.valueOf(orderlyRating.getRating()));
                review.setPurity_rate(String.valueOf(cleanlinessRating.getRating()));
                review.setFacility_rate(String.valueOf(facilityRating.getRating()));

                /*
                 * Kirim Review ke Server
                 */
                presenter.sendReview(review);
            }
        });

        return view;
    }

    /**
     * Menghancurkan View dlm Fragment
     */
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
        if(this.getView() != null)
            Snackbar.make(this.getView(), message, Snackbar.LENGTH_LONG).show();
    }

}