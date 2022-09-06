package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import android.text.SpannableStringBuilder;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.voice.ui.metrics.CardInteractionTracker;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCardModel;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract;
import com.amazon.alexa.voice.ui.onedesign.util.DateUtils;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class MovieShowtimesPresenter implements MovieShowtimesContract.Presenter {
    private Disposable disposable;
    private final CardInteractionTracker interactionTracker;
    private final MovieShowtimesContract.Interactor interactor;
    private boolean is24HrFormat;
    private final Locale locale;
    private final CardMetricsInteractor metricsInteractor;
    private final MovieShowtimesContract.View view;

    public MovieShowtimesPresenter(MovieShowtimesContract.View view, MovieShowtimesContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, Locale locale, boolean z) {
        this(view, interactor, cardMetricsInteractor, new CardInteractionTracker(), locale, z);
    }

    private CharSequence formatAvailableTimes(List<? extends MoviesCardModel.TimeModel> list) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        int min = Math.min(list.size(), 4);
        for (int i = 0; i < min; i++) {
            if (i > 0) {
                spannableStringBuilder.append((CharSequence) "    ");
            }
            CharSequence time = list.get(i).getTime();
            try {
                spannableStringBuilder.append((CharSequence) DateUtils.convert24HrToTargetFormat(time.toString(), this.locale, this.is24HrFormat));
            } catch (ParseException unused) {
                spannableStringBuilder.append(time);
            }
        }
        if (list.size() > 4) {
            spannableStringBuilder.append((CharSequence) "...");
        }
        return spannableStringBuilder;
    }

    private String getCardName() {
        return this.interactor.getMovie().getClass().getSimpleName();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void backClicked() {
        this.interactor.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void end() {
        Disposable disposable = this.disposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.disposable.dispose();
        this.disposable = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void interacted() {
        this.interactionTracker.notifyInteracted();
    }

    public /* synthetic */ MovieShowtimesTheater lambda$start$0$MovieShowtimesPresenter(MoviesCardModel.TheaterModel theaterModel) throws Throwable {
        return new MovieShowtimesTheater(theaterModel.getName(), formatAvailableTimes(theaterModel.getTimeList()), theaterModel);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void linkClicked() {
        this.interactor.openLink();
        this.metricsInteractor.reportNavigationToExternalLink(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void start() {
        MoviesCard.Movie movie = this.interactor.getMovie();
        this.view.floodBackgroundToStatusBar();
        this.view.setTitle(movie.getName());
        this.view.setSubTitle(this.interactor.getDate());
        Single list = Observable.fromIterable(movie.getTheaterList()).map(new Function() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.-$$Lambda$MovieShowtimesPresenter$94cq9s51kkwusPuYa3-9gzM-gCA
            @Override // io.reactivex.rxjava3.functions.Function
            /* renamed from: apply */
            public final Object mo10358apply(Object obj) {
                return MovieShowtimesPresenter.this.lambda$start$0$MovieShowtimesPresenter((MoviesCardModel.TheaterModel) obj);
            }
        }).toList();
        final MovieShowtimesContract.View view = this.view;
        view.getClass();
        this.disposable = list.subscribe(new Consumer() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.-$$Lambda$kJ3G3nvO1Uk5jWhORgW6XnFm1Xk
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                MovieShowtimesContract.View.this.setContent((List) obj);
            }
        });
        this.metricsInteractor.reportCardShown(getCardName());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void theaterClicked(MovieShowtimesTheaterModel movieShowtimesTheaterModel) {
        this.interactor.openTheater((MoviesCard.Theater) movieShowtimesTheaterModel.getTag());
        this.metricsInteractor.reportNavigationToInternalCard(getCardName());
        this.interactionTracker.notifyInteracted();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.Presenter
    public void viewDestroyed() {
        this.metricsInteractor.reportCardInteracted(getCardName(), this.interactionTracker.wasInteracted());
    }

    @VisibleForTesting
    MovieShowtimesPresenter(MovieShowtimesContract.View view, MovieShowtimesContract.Interactor interactor, CardMetricsInteractor cardMetricsInteractor, CardInteractionTracker cardInteractionTracker, Locale locale, boolean z) {
        this.view = view;
        this.interactor = interactor;
        this.metricsInteractor = cardMetricsInteractor;
        this.interactionTracker = cardInteractionTracker;
        this.is24HrFormat = z;
        this.disposable = null;
        this.locale = locale;
    }
}
