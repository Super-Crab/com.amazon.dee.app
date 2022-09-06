package com.amazon.alexa.voice.ui.onedesign.traffic;

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
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.DrawableUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.Component;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class TrafficController extends ViewController implements TrafficContract.View {
    private static final String EXTRA_CARD = "card";
    private TrafficAdapter adapter;
    private TrafficContract.Presenter presenter;
    private TextView titleView;

    public static TrafficController create(TrafficCardModel trafficCardModel) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", trafficCardModel);
        TrafficController trafficController = new TrafficController();
        trafficController.setArguments(bundle);
        return trafficController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.View
    public void add(Object obj) {
        this.adapter.add(obj);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$TrafficController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        Bundle arguments = getArguments();
        TrafficCardModel trafficCardModel = arguments != null ? (TrafficCardModel) arguments.getParcelable("card") : null;
        if (trafficCardModel != null) {
            Component component = getComponent();
            this.presenter = new TrafficPresenter(this, new TrafficInteractor(trafficCardModel, new TrafficMediator(this)), new TrafficTimeFormatter(getContext()), new TrafficRouteFormatter(getContext()), new AndroidResources(getContext(), ComponentUtils.getLocale(component)), (CardMetricsInteractor) component.get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use TrafficController.create(TrafficCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    protected View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_traffic, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, speechControlView);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        HeaderUtils.applyFontStyles(this.titleView);
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.-$$Lambda$TrafficController$ClH6MQLRViAmcCCMrRIT8i_djBo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TrafficController.this.lambda$onCreateView$0$TrafficController(view);
            }
        });
        speechControlView.setComponent(getComponent());
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                TrafficController.this.presenter.dismiss();
            }
        });
        this.adapter = new TrafficAdapter();
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.content);
        recyclerView.setAdapter(this.adapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int i) {
                TrafficController.this.presenter.interacted();
            }
        });
        final Drawable createSeparatorDrawable = DrawableUtils.createSeparatorDrawable(inflate.getContext());
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() { // from class: com.amazon.alexa.voice.ui.onedesign.traffic.TrafficController.3
            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView2, RecyclerView.State state) {
                rect.set(0, 0, 0, 0);
                if (recyclerView2.getChildAdapterPosition(view) < state.getItemCount() - 1) {
                    rect.bottom = createSeparatorDrawable.getIntrinsicHeight() + rect.bottom;
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void onDraw(Canvas canvas, RecyclerView recyclerView2, RecyclerView.State state) {
                int paddingStart = recyclerView2.getPaddingStart();
                int width = recyclerView2.getWidth() - recyclerView2.getPaddingEnd();
                int childCount = recyclerView2.getChildCount();
                for (int i = 0; i < childCount - 1; i++) {
                    View childAt = recyclerView2.getChildAt(i);
                    if (recyclerView2.getChildAdapterPosition(childAt) >= state.getItemCount() - 1) {
                        return;
                    }
                    int bottom = childAt.getBottom();
                    createSeparatorDrawable.setBounds(paddingStart, bottom, width, createSeparatorDrawable.getIntrinsicHeight() + bottom);
                    createSeparatorDrawable.draw(canvas);
                }
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(View view) {
        this.presenter.viewDestroyed();
        this.adapter = null;
    }

    @Override // com.amazon.regulator.ViewController
    public void onDetach(View view) {
        this.presenter.end();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.traffic.TrafficContract.View
    public void setTitle(@NonNull CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
