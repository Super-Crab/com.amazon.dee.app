package com.amazon.alexa.voice.ui.onedesign.movies.list;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.movies.MoviesCard;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListAdapter;
import com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class MoviesListController extends ViewController implements MoviesListContract.View {
    private static final String EXTRA_CARD = "card";
    private static final String POSTER_TAG = "poster";
    private MoviesListAdapter contentAdapter;
    private MoviesListContract.Presenter presenter;
    private TextView subTitleView;
    private ImageView titleAttributionView;
    private TextView titleView;

    public static MoviesListController create(MoviesCard moviesCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", moviesCard);
        MoviesListController moviesListController = new MoviesListController();
        moviesListController.setArguments(bundle);
        return moviesListController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$MoviesListController(View view) {
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
        super.onCreate();
        Bundle arguments = getArguments();
        MoviesCard moviesCard = arguments != null ? (MoviesCard) arguments.getParcelable("card") : null;
        if (moviesCard != null) {
            this.presenter = new MoviesListPresenter(this, new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), new MoviesListInteractor(moviesCard, new MoviesListMediator(this)), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use MoviesListController.create(MoviesCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_movies_list, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        View findViewById = inflate.findViewById(R.id.close);
        this.titleAttributionView = (ImageView) inflate.findViewById(R.id.titleAttribution);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subTitleView = (TextView) inflate.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subTitleView);
        this.contentAdapter = new MoviesListAdapter();
        MoviesListAdapter moviesListAdapter = this.contentAdapter;
        final MoviesListContract.Presenter presenter = this.presenter;
        presenter.getClass();
        moviesListAdapter.setOnMoviePosterClickListener(new MoviesListAdapter.OnMoviePosterClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$wPf7Z3eV7Edd0t1cW8Q0ET3j_WM
            @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListAdapter.OnMoviePosterClickListener
            public final void onMoviePosterClicked(MoviesListPosterModel moviesListPosterModel) {
                MoviesListContract.Presenter.this.posterClicked(moviesListPosterModel);
            }
        });
        MoviesListAdapter moviesListAdapter2 = this.contentAdapter;
        final MoviesListContract.Presenter presenter2 = this.presenter;
        presenter2.getClass();
        moviesListAdapter2.setOnMovieLinkClickListener(new MoviesListAdapter.OnMovieLinkClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$Dlv5ZpuJryFk2zKA6YE60ns_qJw
            @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListAdapter.OnMovieLinkClickListener
            public final void onMovieLinkClicked(MoviesListLinkModel moviesListLinkModel) {
                MoviesListContract.Presenter.this.linkClicked(moviesListLinkModel);
            }
        });
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.-$$Lambda$MoviesListController$JUkoTYywSBC1fEm3Pb4ybJLFaA0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoviesListController.this.lambda$onCreateView$0$MoviesListController(view);
            }
        });
        Resources resources = inflate.getResources();
        final int integer = resources.getInteger(R.integer.voice_ui_od_movie_list_card_number);
        final int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.voice_ui_od_movie_list_card_vertical_padding);
        final int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.voice_ui_od_content_default_double_padding);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(inflate.getContext(), integer, 1, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListController.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                if (MoviesListController.this.contentAdapter.get(i) instanceof MoviesListPosterModel) {
                    return 1;
                }
                return integer;
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.movieListContent);
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                MoviesListController.this.presenter.interacted();
            }
        });
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListController.3
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                int childAdapterPosition = recyclerView2.getChildAdapterPosition(view) / integer;
                rect.set(0, 0, 0, 0);
                rect.right += dimensionPixelSize2;
                if (!MoviesListController.POSTER_TAG.equals(view.getTag()) || childAdapterPosition <= 0) {
                    return;
                }
                rect.top += dimensionPixelSize;
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.titleView = null;
        this.subTitleView = null;
        this.contentAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.View
    public void setSubTitle(CharSequence charSequence) {
        this.subTitleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.movies.list.MoviesListContract.View
    public void setTitleAttributionImage(@DrawableRes int i) {
        this.titleAttributionView.setImageResource(i);
        this.titleAttributionView.setVisibility(0);
    }
}
