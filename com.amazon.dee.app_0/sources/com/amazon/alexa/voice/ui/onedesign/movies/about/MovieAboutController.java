package com.amazon.alexa.voice.ui.onedesign.movies.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MovieCloseActionDelegate;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.LetterSpacing;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageLoader;
import com.amazon.alexa.voice.ui.onedesign.util.image.RoundedCornerImageLoader;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import java.util.Locale;
/* loaded from: classes11.dex */
public final class MovieAboutController extends ViewController implements MovieAboutContract.View, MovieCloseActionDelegate {
    private static final String EXTRA_BACK_NAVIGATION = "back_navigation";
    private static final String EXTRA_MOVIE = "movie";
    private static final String EXTRA_MOVIE_DATE = "date";
    private TextView castView;
    private TextView descriptionView;
    private TextView linkView;
    private ImageLoader posterLoader;
    private ImageView posterView;
    private MovieAboutContract.Presenter presenter;
    private TextView showTimesView;
    private TextView subTitleView;
    private boolean supportsBackNavigation;
    private TextView titleView;

    public static MovieAboutController create(MoviesCard.Movie movie, CharSequence charSequence, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_MOVIE, movie);
        bundle.putCharSequence("date", charSequence);
        bundle.putBoolean(EXTRA_BACK_NAVIGATION, z);
        MovieAboutController movieAboutController = new MovieAboutController();
        movieAboutController.setArguments(bundle);
        return movieAboutController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.MovieCloseActionDelegate
    public void closeClicked() {
        this.presenter.close();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$MovieAboutController(View view) {
        this.presenter.linkClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$MovieAboutController(View view) {
        this.presenter.showTimesClicked();
    }

    public /* synthetic */ void lambda$onCreateView$2$MovieAboutController(View view) {
        this.presenter.backClicked();
    }

    public /* synthetic */ void lambda$onCreateView$3$MovieAboutController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        MovieCloseActionDelegate movieCloseActionDelegate;
        super.onCreate();
        Bundle arguments = getArguments();
        MoviesCard.Movie movie = arguments != null ? (MoviesCard.Movie) arguments.getParcelable(EXTRA_MOVIE) : null;
        if (movie != null) {
            this.supportsBackNavigation = arguments.getBoolean(EXTRA_BACK_NAVIGATION, true);
            try {
                movieCloseActionDelegate = (MovieCloseActionDelegate) getComponent().get(MovieCloseActionDelegate.class);
            } catch (IllegalStateException unused) {
                movieCloseActionDelegate = this;
            }
            MovieAboutInteractor movieAboutInteractor = new MovieAboutInteractor(movie, arguments.getCharSequence("date"), new MovieAboutMediator(this), movieCloseActionDelegate);
            Locale locale = ComponentUtils.getLocale(getComponent());
            this.presenter = new MovieAboutPresenter(this, movieAboutInteractor, new AndroidResources(getContext(), locale), locale, (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use MovieAboutController.create(MoviesCard.Movie) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_movie_about, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.posterView = (ImageView) inflate.findViewById(R.id.movieImage);
        this.titleView = (TextView) inflate.findViewById(R.id.detailTitle);
        this.subTitleView = (TextView) inflate.findViewById(R.id.detailSubTitle);
        this.descriptionView = (TextView) inflate.findViewById(R.id.detailDescription);
        this.castView = (TextView) inflate.findViewById(R.id.movieCasting);
        this.linkView = (TextView) inflate.findViewById(R.id.movieBuyTicketsLink);
        this.showTimesView = (TextView) inflate.findViewById(R.id.movieShowtimesLink);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.subTitleView, this.descriptionView, this.castView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.linkView, this.showTimesView);
        LetterSpacing.apply(R.dimen.voice_ui_od_font_letter_spacing, this.linkView, this.showTimesView);
        this.linkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.about.-$$Lambda$MovieAboutController$YZTJH6xLKjuiCcR-AUBu-Z3AVkI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieAboutController.this.lambda$onCreateView$0$MovieAboutController(view);
            }
        });
        this.showTimesView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.about.-$$Lambda$MovieAboutController$BC0CUtLLKD-mvop4ojBufajbiIc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieAboutController.this.lambda$onCreateView$1$MovieAboutController(view);
            }
        });
        this.posterLoader = new RoundedCornerImageLoader(this.posterView);
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutController.1
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                MovieAboutController.this.presenter.interacted();
            }
        });
        View findViewById = inflate.findViewById(R.id.close);
        View findViewById2 = inflate.findViewById(R.id.back);
        if (this.supportsBackNavigation) {
            findViewById2.setVisibility(0);
            findViewById.setVisibility(8);
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.about.-$$Lambda$MovieAboutController$vbBja5kXX0WijPG_43bQQy8pBhA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MovieAboutController.this.lambda$onCreateView$2$MovieAboutController(view);
                }
            });
        } else {
            findViewById2.setVisibility(8);
            findViewById.setVisibility(0);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.about.-$$Lambda$MovieAboutController$2JY7RqjhZgA3UUPQB1kYibXoATQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    MovieAboutController.this.lambda$onCreateView$3$MovieAboutController(view);
                }
            });
        }
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.posterLoader.unload();
        this.posterView = null;
        this.titleView = null;
        this.subTitleView = null;
        this.descriptionView = null;
        this.castView = null;
        this.linkView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setCast(CharSequence charSequence, CharSequence charSequence2) {
        this.castView.setText(charSequence);
        this.castView.setContentDescription(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setDescription(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.descriptionView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setLinkText(CharSequence charSequence) {
        this.linkView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setPosterImageUrl(String str) {
        this.posterLoader.unload();
        if (str != null) {
            this.posterLoader.load(str);
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setShowtimesText(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.showTimesView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setSubTitle(CharSequence charSequence, CharSequence charSequence2) {
        this.subTitleView.setText(charSequence);
        this.subTitleView.setContentDescription(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.about.MovieAboutContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
