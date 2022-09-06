package com.amazon.alexa.voice.ui.onedesign.movies.theater;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MovieTheaterController extends ViewController implements MovieTheaterContract.View {
    private static final String EXTRA_SUBTITLE = "subTitle";
    private static final String EXTRA_THEATER = "theater";
    private static final String EXTRA_TITLE = "title";
    private MovieTheaterAdapter contentAdapter;
    private TextView locationView;
    private TextView nameView;
    private MovieTheaterContract.Presenter presenter;
    private TextView subTitleView;
    private TextView titleView;

    public static MovieTheaterController create(CharSequence charSequence, CharSequence charSequence2, MoviesCard.Theater theater) {
        Bundle bundle = new Bundle();
        bundle.putCharSequence("title", charSequence);
        bundle.putCharSequence(EXTRA_SUBTITLE, charSequence2);
        bundle.putParcelable(EXTRA_THEATER, theater);
        MovieTheaterController movieTheaterController = new MovieTheaterController();
        movieTheaterController.setArguments(bundle);
        return movieTheaterController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.View
    public void addItem(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$MovieTheaterController(View view) {
        this.presenter.backClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$MovieTheaterController(View view) {
        this.presenter.locationClicked();
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
        MoviesCard.Theater theater = arguments != null ? (MoviesCard.Theater) arguments.getParcelable(EXTRA_THEATER) : null;
        CharSequence charSequence2 = arguments != null ? arguments.getCharSequence("title") : null;
        if (arguments != null) {
            charSequence = arguments.getCharSequence(EXTRA_SUBTITLE);
        }
        if (theater != null) {
            this.presenter = new MovieTheaterPresenter(this, new MovieTheaterInteractor(charSequence2, charSequence, theater, new MovieTheaterMediator(this)), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use MovieTheaterController.create(CharSequence, CharSequence, MoviesCard.Theater) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_movie_theater, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.nameView = (TextView) inflate.findViewById(R.id.name);
        this.locationView = (TextView) inflate.findViewById(R.id.location);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.nameView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.locationView);
        this.contentAdapter = new MovieTheaterAdapter(ComponentUtils.getLocale(getComponent()));
        View findViewById = inflate.findViewById(R.id.close);
        View findViewById2 = inflate.findViewById(R.id.back);
        findViewById2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.theater.-$$Lambda$MovieTheaterController$6Pinm1Vlew-FHi-Iw9qJpdrDEow
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieTheaterController.this.lambda$onCreateView$0$MovieTheaterController(view);
            }
        });
        findViewById2.setVisibility(0);
        findViewById.setVisibility(8);
        inflate.findViewById(R.id.theater).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.theater.-$$Lambda$MovieTheaterController$DuDT9Y8xrK7qWPmrb5YyQDv8_MY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MovieTheaterController.this.lambda$onCreateView$1$MovieTheaterController(view);
            }
        });
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.theaterContent);
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterController.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                MovieTheaterController.this.presenter.interacted();
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterController.2
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                rect.set(0, 0, 0, 0);
                int itemCount = state.getItemCount();
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view);
                if (!(MovieTheaterController.this.contentAdapter.get(childAdapterPosition) instanceof MovieTheaterTimeModel) || childAdapterPosition >= itemCount - 1) {
                    return;
                }
                rect.bottom = createSeparatorDrawable.getIntrinsicHeight() + rect.bottom;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                int paddingStart = recyclerView2.getPaddingStart();
                int width = recyclerView2.getWidth() - recyclerView2.getPaddingEnd();
                int childCount = recyclerView2.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    View childAt = recyclerView2.getChildAt(i);
                    int childAdapterPosition = recyclerView2.getChildAdapterPosition(childAt);
                    if ((MovieTheaterController.this.contentAdapter.get(childAdapterPosition) instanceof MovieTheaterTimeModel) && childAdapterPosition < state.getItemCount() - 1) {
                        int bottom = childAt.getBottom();
                        createSeparatorDrawable.setBounds(paddingStart, bottom, width, createSeparatorDrawable.getIntrinsicHeight() + bottom);
                        createSeparatorDrawable.draw(canvas);
                    }
                }
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.nameView = null;
        this.locationView = null;
        this.contentAdapter = null;
        this.titleView = null;
        this.subTitleView = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.View
    public void setSubTitle(CharSequence charSequence) {
        this.subTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.View
    public void setTheater(CharSequence charSequence, CharSequence charSequence2) {
        this.nameView.setText(charSequence);
        this.locationView.setText(charSequence2);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.theater.MovieTheaterContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
