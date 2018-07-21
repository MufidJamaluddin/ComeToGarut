package id.ac.polban.jtk.cometogarut.mvp.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import id.ac.polban.jtk.cometogarut.R;
import id.ac.polban.jtk.cometogarut.mvp.contract.SendSuggestionPlaceContract;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;
import id.ac.polban.jtk.cometogarut.mvp.presenter.SendSuggestionPlacePresenter;

/**
 * Fragment untuk Mengirimkan Masukkan/Saran (SUGGESTION)
 * @author Mufid Jamaluddin
 */
public class SendSuggestionFragment extends BaseFragment implements SendSuggestionPlaceContract.View
{
    private EditText nameField;
    private EditText emailField;
    private EditText descriptionField;
    private Button sendSuggestionBtn;

    private SendSuggestionPlacePresenter presenter;

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
        View view;
        view = inflater.inflate(R.layout.fragment_send_suggestion, container, false);

        this.presenter = new SendSuggestionPlacePresenter();
        this.presenter.attach(this);

        this.nameField = view.findViewById(R.id.user_name);
        this.emailField = view.findViewById(R.id.user_email);
        this.descriptionField = view.findViewById(R.id.user_description);

        this.sendSuggestionBtn = view.findViewById(R.id.sh_suggestion);

        this.sendSuggestionBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Suggestion suggestion = new Suggestion();
                //
                suggestion.setName(String.valueOf(nameField.getText()));
                suggestion.setEmail(String.valueOf(emailField.getText()));
                suggestion.setDescription(String.valueOf(descriptionField.getText()));
                // Kirim Suggestion
                presenter.sendSuggestion(suggestion);
            }
        });

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

}