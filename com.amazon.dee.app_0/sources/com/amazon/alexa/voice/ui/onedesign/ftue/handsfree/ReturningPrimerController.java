package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import androidx.annotation.Nullable;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink;
/* loaded from: classes11.dex */
public final class ReturningPrimerController extends PrimerController implements HandsfreePrimerContract.View {
    private ReturningPrimerPresenter presenter;

    public static ReturningPrimerController create() {
        return new ReturningPrimerController();
    }

    static /* synthetic */ void lambda$getRationaleClickListener$1() {
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    protected HandsfreePrimerContract.Presenter getPresenter() {
        this.presenter = new ReturningPrimerPresenter(this, new HandsfreePrimerInteractor(new ReturningPrimerMediator(this)), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), (MetricsBridge) getComponent().get(MetricsBridge.class));
        return this.presenter;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    @Nullable
    protected TextViewWithLink.OnEmbeddedLinkClickListener getRationaleClickListener() {
        return $$Lambda$ReturningPrimerController$q4eGqOGF9N7wB4dxRk7Gkz3PO28.INSTANCE;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    @Nullable
    protected TextViewWithLink.OnEmbeddedLinkClickListener getUsageClickListener() {
        return new TextViewWithLink.OnEmbeddedLinkClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$ReturningPrimerController$SbitOZeSQqA5wrwClSTtipKS68c
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink.OnEmbeddedLinkClickListener
            public final void onEmbeddedLinkClick() {
                ReturningPrimerController.this.lambda$getUsageClickListener$0$ReturningPrimerController();
            }
        };
    }

    public /* synthetic */ void lambda$getUsageClickListener$0$ReturningPrimerController() {
        this.presenter.learnMoreClicked();
    }
}
