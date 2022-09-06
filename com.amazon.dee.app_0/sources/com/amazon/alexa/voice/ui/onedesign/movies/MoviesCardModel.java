package com.amazon.alexa.voice.ui.onedesign.movies;

import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.ui.annotation.UiModel;
import java.util.List;
@UiModel(builder = true)
/* loaded from: classes11.dex */
public interface MoviesCardModel extends Parcelable {

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface MovieModel extends Parcelable {
        CharSequence getAbout();

        List<CharSequence> getCast();

        int getDuration();

        String getImageUrl();

        RatingModel getImdbRating();

        @NonNull
        CharSequence getLinkText();

        @NonNull
        String getLinkUrl();

        @NonNull
        CharSequence getName();

        CharSequence getRating();

        int getReleaseYear();

        @NonNull
        List<? extends TheaterModel> getTheaterList();

        String getVideoUrl();
    }

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface RatingModel extends Parcelable {
        double getAverage();

        double getMaximum();

        CharSequence getSource();
    }

    @UiModel(builder = true)
    /* loaded from: classes11.dex */
    public interface TheaterModel extends Parcelable {
        @NonNull
        CharSequence getLocation();

        @NonNull
        CharSequence getName();

        @NonNull
        List<? extends TimeModel> getTimeList();
    }

    @UiModel
    /* loaded from: classes11.dex */
    public interface TimeModel extends Parcelable {
        @NonNull
        CharSequence getTime();
    }

    @Nullable
    CharSequence getAttribution();

    @NonNull
    CharSequence getLinkText();

    @NonNull
    String getLinkUrl();

    @NonNull
    List<? extends MovieModel> getMovieList();

    @NonNull
    CharSequence getSubTitle();

    @NonNull
    CharSequence getTitle();
}
