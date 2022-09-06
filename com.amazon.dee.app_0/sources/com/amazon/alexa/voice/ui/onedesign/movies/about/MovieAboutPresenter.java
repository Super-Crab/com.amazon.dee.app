package com.amazon.alexa.voice.ui.onedesign.movies.about;

import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesRatingText;
import com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.vox.ui.onedesign.R;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class MovieAboutPresenter implements MovieAboutContract.Presenter {
    private final CardInteractionTracker interactionTracker = new CardInteractionTracker();
    private final MovieAboutContract.Interactor interactor;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private final Resources resources;
    private final MovieAboutContract.View view;

    public MovieAboutPresenter(MovieAboutContract.View view, MovieAboutContract.Interactor interactor, Resources resources, Locale locale, CardMetricsInteractor cardMetricsInteractor) {
        this.view = view;
        this.interactor = interactor;
        this.resources = resources;
        this.locale = locale;
        this.metricsInteractor = cardMetricsInteractor;
    }

    private void appendIfNotEmpty(SpannableStringBuilder spannableStringBuilder, CharSequence charSequence) {
        if (!TextUtils.isEmpty(charSequence)) {
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.append(" | ");
            }
            spannableStringBuilder.append(charSequence);
        }
    }

    private String getCardName() {
        return this.interactor.getMovie().getClass().getSimpleName();
    }

    private CharSequence getCastContentDescription(CharSequence charSequence) {
        return this.resources.getString(R.string.voice_ui_od_movie_cast_content_description, charSequence);
    }

    private CharSequence getMovieContentDescription(MoviesCardModel.MovieModel movieModel) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        appendIfNotEmpty(spannableStringBuilder, this.resources.getString(R.string.voice_ui_od_movie_rating_content_description, movieModel.getRating()));
        appendIfNotEmpty(spannableStringBuilder, this.resources.getString(R.string.voice_ui_od_movie_duration_content_description, makeDurationText(movieModel.getDuration())));
        appendIfNotEmpty(spannableStringBuilder, this.resources.getString(R.string.voice_ui_od_movie_released_content_description, Integer.valueOf(movieModel.getReleaseYear())));
        appendIfNotEmpty(spannableStringBuilder, this.resources.getString(R.string.voice_ui_od_movie_review_score_content_description, Double.valueOf(movieModel.getImdbRating().getAverage()), Double.valueOf(movieModel.getImdbRating().getMaximum())));
        return spannableStringBuilder;
    }

    private CharSequence getMovieDescription(MoviesCardModel.MovieModel movieModel) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        appendIfNotEmpty(spannableStringBuilder, movieModel.getRating());
        appendIfNotEmpty(spannableStringBuilder, makeDurationText(movieModel.getDuration()));
        appendIfNotEmpty(spannableStringBuilder, makeReleaseYearText(movieModel.getReleaseYear()));
        appendIfNotEmpty(spannableStringBuilder, makeImdbRatingText(movieModel.getImdbRating()));
        return spannableStringBuilder;
    }

    private CharSequence makeCastText(List<CharSequence> list) {
        return TextUtils.join(", ", list);
    }

    private CharSequence makeImdbRatingText(MoviesCardModel.RatingModel ratingModel) {
        return MoviesRatingText.fromRating(ratingModel);
    }

    private CharSequence makeReleaseYearText(int i) {
        return i == 0 ? "" : String.format(this.locale, "%d", Integer.valueOf(i));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void backClicked() {
        this.interactor.backClicked();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void close() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void closeClicked() {
        this.interactor.closeClicked();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void linkClicked() {
        this.interactor.openLink();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @VisibleForTesting
    CharSequence makeDurationText(int i) {
        if (i <= 0) {
            return null;
        }
        int i2 = 0;
        while (i >= 60) {
            i -= 60;
            i2++;
        }
        return this.resources.getString(R.string.voice_ui_od_movies_duration_format, Integer.valueOf(i2), Integer.valueOf(i));
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void showTimesClicked() {
        this.interactor.openShowtimes();
        this.metricsInteractor.reportNavigationToInternalCard(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void start() {
        MoviesCard.Movie movie = this.interactor.getMovie();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(movie.getName());
        this.view.setSubTitle(getMovieDescription(movie), getMovieContentDescription(movie));
        this.view.setPosterImageUrl(movie.getImageUrl());
        this.view.setLinkText(movie.getLinkText());
        CharSequence makeCastText = makeCastText(movie.getCast());
        this.view.setCast(makeCastText, getCastContentDescription(makeCastText));
        this.view.setDescription(movie.getAbout());
        if (!movie.getTheaterList().isEmpty()) {
            this.view.setShowtimesText(this.resources.getString(R.string.voice_ui_od_movie_showtimes));
        } else {
            this.view.setShowtimesText(this.resources.getString(R.string.voice_ui_od_empty));
        }
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }
}
