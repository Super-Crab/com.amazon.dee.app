package com.amazon.alexa.voice.ui.onedesign.movies.showtimes;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesAdapter;
import com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
import java.util.List;
/* loaded from: classes11.dex */
public final class MovieShowtimesController extends ViewController implements MovieShowtimesContract.View {
    private static final String EXTRA_MOVIE = "movie";
    private static final String EXTRA_SUBTITLE = "subTitle";
    private MovieShowtimesContract.Presenter presenter;
    private MovieShowtimesAdapter showtimesAdapter;
    private TextView subTitleView;
    private TextView titleView;

    public static MovieShowtimesController create(CharSequence charSequence, MoviesCard.Movie movie) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence(EXTRA_SUBTITLE, charSequence);
        bundle.putParcelable(EXTRA_MOVIE, movie);
        MovieShowtimesController movieShowtimesController = new MovieShowtimesController();
        movieShowtimesController.setArguments(bundle);
        return movieShowtimesController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$MovieShowtimesController(View view) {
        this.presenter.backClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        Bundle arguments = getArguments();
        CharSequence charSequence = null;
        MoviesCard.Movie movie = arguments != null ? (MoviesCard.Movie) arguments.getParcelable(EXTRA_MOVIE) : null;
        if (arguments != null) {
            charSequence = arguments.getCharSequence(EXTRA_SUBTITLE);
        }
        if (movie != null) {
            this.presenter = new MovieShowtimesPresenter(this, new MovieShowtimesInteractor(charSequence, movie, new MovieShowtimesMediator(this)), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class), ComponentUtils.getLocale(getComponent()), DateFormat.is24HourFormat(getContext()));
            return;
        }
        throw new IllegalStateException("Use MovieShowtimesController.create(MoviesCard.Movie) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_movie_showtimes, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.showtimesAdapter = new MovieShowtimesAdapter();
        MovieShowtimesAdapter movieShowtimesAdapter = this.showtimesAdapter;
        final MovieShowtimesContract.Presenter presenter = this.presenter;
        presenter.getClass();
        movieShowtimesAdapter.setOnMovieTheaterClickListener(new MovieShowtimesAdapter.OnMovieTheaterClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.-$$Lambda$utii87pQpEYevH-hT0yxEQ5djwI
            @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesAdapter.OnMovieTheaterClickListener
            public final void onMovieTheaterClicked(MovieShowtimesTheaterModel movieShowtimesTheaterModel) {
                MovieShowtimesContract.Presenter.this.theaterClicked(movieShowtimesTheaterModel);
            }
        });
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.showtimesContent);
        recyclerView.setAdapter(this.showtimesAdapter);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        View findViewById = inflate.findViewById(R.id.back);
        findViewById.setVisibility(0);
        inflate.findViewById(R.id.close).setVisibility(8);
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.-$$Lambda$MovieShowtimesController$Bvn7ocNpYxx-97tX4nd9dQMcif0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieShowtimesController.this.lambda$onCreateView$0$MovieShowtimesController(view);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesController.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                MovieShowtimesController.this.presenter.interacted();
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesController.2
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                rect.set(0, 0, 0, createSeparatorDrawable.getIntrinsicHeight());
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                int paddingStart = recyclerView2.getPaddingStart();
                int width = recyclerView2.getWidth() - recyclerView2.getPaddingEnd();
                int childCount = recyclerView2.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    int bottom = recyclerView2.getChildAt(i).getBottom();
                    createSeparatorDrawable.setBounds(paddingStart, bottom, width, createSeparatorDrawable.getIntrinsicHeight() + bottom);
                    createSeparatorDrawable.draw(canvas);
                }
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.showtimesAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.View
    public void setContent(List<? extends MovieShowtimesTheaterModel> list) {
        this.showtimesAdapter.set(list);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.View
    public void setSubTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.subTitleView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.showtimes.MovieShowtimesContract.View
    public void setTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.titleView, charSequence);
    }
}
