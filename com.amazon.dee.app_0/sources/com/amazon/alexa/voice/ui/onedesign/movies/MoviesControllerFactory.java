package com.amazon.alexa.voice.ui.onedesign.movies;

import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.util.JSONUtils;
import com.amazon.alexa.voice.ui.superbowl.ControllerFactory;
import com.sun.mail.imap.IMAPStore;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class MoviesControllerFactory implements ControllerFactory<MoviesController> {
    private List<CharSequence> makeCast(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.getString(i));
        }
        return arrayList;
    }

    private MoviesCardModel.MovieModel makeMovie(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("link");
        MoviesCard.Movie.Builder imageUrl = new MoviesCard.Movie.Builder().name(jSONObject.getString("name")).duration(jSONObject.getInt("duration")).releaseYear(jSONObject.getInt("releaseYear")).rating(JSONUtils.optionalString(jSONObject, "audienceGuidance")).imdbRating(makeRating(jSONObject)).about(JSONUtils.optionalString(jSONObject, "description")).cast(makeCast(jSONObject.getJSONArray("casting"))).linkText(jSONObject2.getString("text")).linkUrl(jSONObject2.getString("target")).imageUrl(jSONObject.getJSONObject("image").getString("url"));
        JSONObject optJSONObject = jSONObject.optJSONObject("runnings");
        if (optJSONObject != null) {
            imageUrl.theaterList(makeTheaterList(optJSONObject.getJSONArray("list")));
        }
        return imageUrl.build();
    }

    private List<MoviesCardModel.MovieModel> makeMoviesList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(makeMovie(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private MoviesCardModel.RatingModel makeRating(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("reviewRating");
        return new MoviesCard.Rating.Builder().average(jSONObject2.getDouble("average")).maximum(jSONObject2.getDouble("maximum")).source(jSONObject.getString("attribution")).build();
    }

    private MoviesCardModel.TheaterModel makeTheater(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("theatre");
        return new MoviesCard.Theater.Builder().name(jSONObject2.getString("name")).location(jSONObject2.getString("location")).timeList(makeTimes(jSONObject.getJSONArray("screenings"))).build();
    }

    private List<MoviesCardModel.TheaterModel> makeTheaterList(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(makeTheater(jSONArray.getJSONObject(i)));
        }
        return arrayList;
    }

    private List<MoviesCardModel.TimeModel> makeTimes(JSONArray jSONArray) throws JSONException {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(new MoviesCard.Time(jSONArray.getJSONObject(i).getString("time")));
        }
        return arrayList;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.voice.ui.superbowl.ControllerFactory
    /* renamed from: create */
    public MoviesController mo2780create(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject("link");
        return MoviesController.create(new MoviesCard.Builder().title(jSONObject.getString("title")).subTitle(jSONObject.getString(IMAPStore.ID_DATE)).linkText(jSONObject2.getString("text")).linkUrl(jSONObject2.getString("target")).movieList(makeMoviesList(jSONObject.getJSONArray(MoviesContract.ROUTER))).attribution(null).build());
    }
}
