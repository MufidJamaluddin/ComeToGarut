package id.ac.polban.jtk.cometogarut.mvp.network;

import id.ac.polban.jtk.cometogarut.mvp.model.DetailPlace;
import id.ac.polban.jtk.cometogarut.mvp.model.Gallery;
import id.ac.polban.jtk.cometogarut.mvp.model.Message;
import id.ac.polban.jtk.cometogarut.mvp.model.RespList;
import id.ac.polban.jtk.cometogarut.mvp.model.RespObj;
import id.ac.polban.jtk.cometogarut.mvp.model.Review;
import id.ac.polban.jtk.cometogarut.mvp.model.SimplePlace;
import id.ac.polban.jtk.cometogarut.mvp.model.Suggestion;

import io.reactivex.Observable;
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
    @GET("place")
    Observable<RespList<SimplePlace>> getPlaces();

    /**
     * Mendapatkan Tempat Wisata berdasarkan Pencarian
     * @param title : kunci pencarian
     * @return call
     */
    @GET("place/search/{title}")
    Observable<RespList<SimplePlace>> getPlaces(@Path(value = "title", encoded = true) String title);

    /**
     * Mendapatkan Detail Place berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("place/{place_id}")
    Observable<RespObj<DetailPlace>> getPlace(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Mendapatkan Gallery dari Tempat Wisata
     * @param place_id : id tempat
     * @return call
     */
    @GET("gallery/place/{place_id}")
    Observable<RespList<Gallery>> getGalleries(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Mendapatkan Review dari Tempat Wisata berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("review/place/{place_id}")
    Observable<RespList<Review>> getReviews(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Menginputkan Review ke Database
     * @return : call
     */
    @POST("review")
    Observable<Message> sendReview(@Body Review review);

    /**
     * Mendapatkan Suggestion dari Tempat Wisata berdasarkan place_id
     * @param place_id : id tempat
     * @return call
     */
    @GET("suggestion/place/{place_id}")
    Observable<RespList<Suggestion>> getSuggestions(@Path(value = "place_id", encoded = true) String place_id);

    /**
     * Menginputkan Suggestion ke Web Server
     * @return : call
     */
    @POST("suggestion")
    Observable<Message> sendSuggestion(@Body Suggestion suggestion);

}