package id.ac.polban.jtk.cometogarut.mvp.network;

import java.util.List;

import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;
import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Mengambil Data dari Web Server
 */
public interface IRestService
{
    /**
     * Mendapatkan List Place
     * @return call dari list place
     */
    @GET("/place")
    Call<List<SimplePlace>> getPlaces();

    /**
     * Mendapatkan Tempat Wisata berdasarkan Pencarian
     * @param title : kunci pencarian
     * @return call
     */
    @GET("/place/search/{title}")
    Call<List<SimplePlace>> getPlaces(@Path(value = "title", encoded = true) String title);

    /**
     * Mendapatkan Detail Place berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("/place/{place_id}")
    Call<DetailPlace> getPlace(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Mendapatkan Gallery dari Tempat Wisata
     * @param place_id : id tempat
     * @return call
     */
    @GET("/galery/place/{place_id}")
    Call<List<Gallery>> getGalleries(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Mendapatkan Review dari Tempat Wisata berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("/review/place/{place_id}")
    Call<List<Review>> getReviews(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Menginputkan Review ke Database
     * @return : call
     */
    @POST("/review")
    Call<Message> saveReview(@Body Review review);

    /**
     * Mendapatkan Suggestion dari Tempat Wisata berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("/suggestion/place/{place_id}")
    Call<List<Review>> getSuggestions(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Menginputkan Suggestion ke Web Server
     * @return : call
     */
    @POST("/suggestion")
    Call<Message> saveSuggestion(@Body Suggestion suggestion);

}