package com.amazon.alexa.voice.ui.onedesign.empty;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public final class EmptyController extends ViewController implements EmptyContract.View {
    private static final String EXTRA_CARD = "card";
    private TextView contentView;
    private ImageView iconView;
    private Button manageButton;
    private EmptyContract.Presenter presenter;
    private TextView titleView;
    private TextView userHintView;

    public static EmptyController create(EmptyCard emptyCard) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("card", emptyCard);
        EmptyController emptyController = new EmptyController();
        emptyController.setArguments(bundle);
        return emptyController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$EmptyController(View view) {
        this.presenter.closeClicked();
    }

    public /* synthetic */ void lambda$showManageButton$1$EmptyController(View view) {
        this.presenter.manageButtonClicked();
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
        overrideTheme(R.style.Theme_Alexa_Voice_OneDesign_Content_Dark);
        Bundle arguments = getArguments();
        EmptyCard emptyCard = arguments != null ? (EmptyCard) arguments.getParcelable("card") : null;
        if (emptyCard != null) {
            this.presenter = new EmptyPresenter(this, new EmptyInteractor(emptyCard, new EmptyMediator(this)), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), new EmptyResourceBundleFactory(), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
            return;
        }
        throw new IllegalStateException("Use EmptyController.create(EmptyCard) to create a controller");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_empty, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        SpeechControlView speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, speechControlView);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.contentView = (TextView) inflate.findViewById(R.id.content);
        this.userHintView = (TextView) inflate.findViewById(R.id.userHint);
        this.iconView = (ImageView) inflate.findViewById(R.id.icon);
        this.manageButton = (Button) inflate.findViewById(R.id.manage);
        HeaderUtils.applyFontStyles(this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.contentView);
        FontUtils.apply(Font.AMAZON_BOOKERLY_REGULAR_ITALIC, this.userHintView);
        speechControlView.setComponent(getComponent());
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.-$$Lambda$EmptyController$9EjM0d-noM_sNfFBp-VGHr6so3M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmptyController.this.lambda$onCreateView$0$EmptyController(view);
            }
        });
        ((ContentLayout) inflate.findViewById(R.id.layout)).setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.EmptyController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                EmptyController.this.presenter.dismiss();
            }
        });
        ((NestedScrollView) inflate.findViewById(R.id.scrollView)).setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.EmptyController.2
            @Override // androidx.core.widget.NestedScrollView.OnScrollChangeListener
            public void onScrollChange(NestedScrollView nestedScrollView, int i, int i2, int i3, int i4) {
                EmptyController.this.presenter.interacted();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.titleView = null;
        this.contentView = null;
        this.userHintView = null;
        this.iconView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void setContent(CharSequence charSequence) {
        this.contentView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void setIconResourceId(int i) {
        this.iconView.setImageResource(i);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void setTitle(CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void setUserHint(CharSequence charSequence) {
        this.userHintView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.empty.EmptyContract.View
    public void showManageButton() {
        this.manageButton.setVisibility(0);
        this.manageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.empty.-$$Lambda$EmptyController$p7GEscqdtdok-MR2IAebQMkTHwE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EmptyController.this.lambda$showManageButton$1$EmptyController(view);
            }
        });
    }
}
