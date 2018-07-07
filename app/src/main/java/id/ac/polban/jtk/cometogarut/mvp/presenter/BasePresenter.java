package id.ac.polban.jtk.cometogarut.mvp.presenter;

public abstract class BasePresenter<V>
{
    protected V view;

    /**
     * Melakukan Binding View ke Presenter
     *
     * @param view : View
     */
    public void attach(V view)
    {
        this.view = view;
    }

    /**
     * Melakukan unbinding View di Presenter
     */
    public abstract void detach();

}
