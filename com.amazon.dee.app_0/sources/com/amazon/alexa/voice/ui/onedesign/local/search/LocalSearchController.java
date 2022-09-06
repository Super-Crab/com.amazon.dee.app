package com.amazon.alexa.voice.ui.onedesign.local.search;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.local.LocalCard;
import com.amazon.alexa.voice.ui.onedesign.local.LocalDelegate;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchAdapter;
import com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.Resources;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.DividerDecoration;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class LocalSearchController extends ViewController implements LocalSearchContract.View {
    private static final String EXTRA_CARD = "card";
    private LocalSearchAdapter contentAdapter;
    private LocalSearchContract.Presenter presenter;
    private Resources resources;
    private ImageView resultAttributionView;
    private TextView subtitleView;
    private TextView titleView;

    public static LocalSearchController create(LocalCard localCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", localCard);
        LocalSearchController localSearchController = new LocalSearchController();
        localSearchController.setArguments(bundle);
        return localSearchController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.View
    public void add(Object obj) {
        this.contentAdapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$LocalSearchController(View view) {
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
        LocalCard localCard = (LocalCard) getArguments().getParcelable("card");
        if (localCard != null) {
            Component component = getComponent();
            this.resources = new AndroidResources(getContext(), ComponentUtils.getLocale(component));
            LocalSearchMediator localSearchMediator = new LocalSearchMediator(this);
            this.presenter = new LocalSearchPresenter(this, new LocalSearchInteractor(localCard, localSearchMediator, (LocalDelegate) component.get(LocalDelegate.class)), this.resources, (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use LocalSearchController.create(LocalCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_local_search, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.resultAttributionView = (ImageView) inflate.findViewById(R.id.titleAttribution);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.subtitleView = (TextView) inflate.findViewById(R.id.subTitle);
        HeaderUtils.applyFontStyles(this.titleView, this.subtitleView);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.search.-$$Lambda$LocalSearchController$AFKU-wV57NLIuOCuF2T9dgPoYLk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LocalSearchController.this.lambda$onCreateView$0$LocalSearchController(view);
            }
        });
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        Resources resources = this.resources;
        final LocalSearchContract.Presenter presenter = this.presenter;
        presenter.getClass();
        this.contentAdapter = new LocalSearchAdapter(resources, new LocalSearchAdapter.OnItemClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.search.-$$Lambda$bLQ0_Ag6n82aOqntsG8kf3L_UK0
            @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchAdapter.OnItemClickListener
            public final void onItemClicked(LocalSearchItemModel localSearchItemModel) {
                LocalSearchContract.Presenter.this.itemClicked(localSearchItemModel);
            }
        });
        recyclerView.setAdapter(this.contentAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchController.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                LocalSearchController.this.presenter.interacted();
            }
        });
        recyclerView.addItemDecoration(new DividerDecoration(getContext(), R.color.voice_ui_od_list_item_divider, this.contentAdapter));
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.contentAdapter = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDetach(@NonNull View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.View
    public void setImageAttribution(Drawable drawable) {
        ViewUtils.setDrawableOrRemove(this.resultAttributionView, drawable);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.View
    public void setSubTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.subtitleView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.local.search.LocalSearchContract.View
    public void setTitle(CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.titleView, charSequence);
    }
}
