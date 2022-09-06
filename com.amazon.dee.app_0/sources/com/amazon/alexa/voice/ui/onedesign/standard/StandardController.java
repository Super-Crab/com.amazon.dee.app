package com.amazon.alexa.voice.ui.onedesign.standard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.metrics.CardMetricsInteractor;
import com.amazon.alexa.voice.ui.onedesign.speech.SpeechControlView;
import com.amazon.alexa.voice.ui.onedesign.standard.StandardContract;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.HeaderUtils;
import com.amazon.alexa.voice.ui.onedesign.util.LetterSpacing;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.util.image.ImageLoader;
import com.amazon.alexa.voice.ui.onedesign.util.image.RoundedCornerImageLoader;
import com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout;
import com.amazon.alexa.vox.ui.onedesign.R;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public class StandardController extends ViewController implements StandardContract.View {
    private static final String EXTRA_CARD = "card";
    private TextView calculationContentView;
    private View calculationLayoutView;
    private View closeView;
    private ContentLayout contentLayoutView;
    private TextView contentView;
    private TextView imageAttributionView;
    private View imageContainer;
    private ImageLoader imageLoader;
    private View imageTextLayoutView;
    private ImageView imageView;
    private TextView linkView;
    private StandardContract.Presenter presenter;
    private TextView questionView;
    private SpeechControlView speechControlView;
    private TextView subtitleView;

    public static StandardController create(@NonNull StandardCardModel standardCardModel) {
        return create(standardCardModel, new Bundle());
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreateView$0$StandardController(View view) {
        this.presenter.closeClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$StandardController(View view) {
        this.presenter.linkClicked();
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
        this.presenter = new StandardPresenter(this, new StandardInteractor(arguments != null ? (StandardCardModel) arguments.getParcelable("card") : null, new StandardMediator(this)), (CardMetricsInteractor) getComponent().get(CardMetricsInteractor.class));
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_standard, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.cardLayout);
        this.speechControlView = (SpeechControlView) inflate.findViewById(R.id.control);
        ViewUtils.addStatusBarHeightAsTopPadding(findViewById, this.speechControlView);
        this.contentLayoutView = (ContentLayout) inflate.findViewById(R.id.layout);
        this.subtitleView = (TextView) inflate.findViewById(R.id.title);
        this.closeView = inflate.findViewById(R.id.close);
        this.imageView = (ImageView) inflate.findViewById(R.id.image);
        this.questionView = (TextView) inflate.findViewById(R.id.question);
        this.contentView = (TextView) inflate.findViewById(R.id.content);
        this.linkView = (TextView) inflate.findViewById(R.id.link);
        this.imageAttributionView = (TextView) inflate.findViewById(R.id.imageAttribution);
        this.calculationContentView = (TextView) inflate.findViewById(R.id.calculation_content);
        this.calculationLayoutView = inflate.findViewById(R.id.standardCalculationLayout);
        this.imageTextLayoutView = inflate.findViewById(R.id.standardImageTextLayout);
        this.imageContainer = inflate.findViewById(R.id.imageContainer);
        this.imageLoader = new RoundedCornerImageLoader(this.imageView);
        HeaderUtils.applyFontStyles(this.subtitleView);
        LetterSpacing.apply(R.dimen.voice_ui_od_font_letter_spacing, this.linkView);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.questionView, this.calculationContentView);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_LIGHT, this.contentView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.linkView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.imageAttributionView);
        this.speechControlView.setComponent(getComponent());
        this.closeView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.standard.-$$Lambda$StandardController$I-mUkIcMXVzRyJuNvpcPHNejCqM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StandardController.this.lambda$onCreateView$0$StandardController(view);
            }
        });
        this.linkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.standard.-$$Lambda$StandardController$XTL89S6s4-Lb1bcuJq81GgasyV8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StandardController.this.lambda$onCreateView$1$StandardController(view);
            }
        });
        this.contentLayoutView.setOnContentListener(new ContentLayout.SimpleOnContentListener() { // from class: com.amazon.alexa.voice.ui.onedesign.standard.StandardController.1
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.SimpleOnContentListener, com.amazon.alexa.voice.ui.onedesign.widget.ContentLayout.OnContentListener
            public void onDismiss() {
                StandardController.this.presenter.dismiss();
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.presenter.viewDestroyed();
        this.imageLoader.unload();
        this.contentLayoutView = null;
        this.subtitleView = null;
        this.closeView = null;
        this.imageView = null;
        this.questionView = null;
        this.contentView = null;
        this.linkView = null;
        this.imageAttributionView = null;
        this.imageLoader = null;
        this.calculationContentView = null;
        this.calculationLayoutView = null;
        this.imageTextLayoutView = null;
        this.speechControlView = null;
        this.imageContainer = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setContent(@NonNull CharSequence charSequence) {
        this.contentView.setText(charSequence);
        this.calculationContentView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setImageAttribution(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.imageAttributionView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setImageUrl(@Nullable CharSequence charSequence) {
        this.imageLoader.unload();
        if (TextUtils.isEmpty(charSequence)) {
            this.imageView.setImageBitmap(null);
            this.imageContainer.setVisibility(8);
            return;
        }
        this.imageLoader.load(charSequence.toString());
        this.imageContainer.setVisibility(0);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setLinkText(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.linkView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setSubTitle(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.subtitleView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setTitle(@Nullable CharSequence charSequence) {
        ViewUtils.setTextOrRemove(this.questionView, charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.standard.StandardContract.View
    public void setVisibleLayout(int i) {
        if (i != 0) {
            this.imageTextLayoutView.setVisibility(0);
            this.calculationLayoutView.setVisibility(8);
            return;
        }
        this.imageTextLayoutView.setVisibility(8);
        this.calculationLayoutView.setVisibility(0);
    }

    @VisibleForTesting
    public static StandardController create(@NonNull StandardCardModel standardCardModel, @NonNull Bundle bundle) {
        bundle.putParcelable("card", standardCardModel);
        StandardController standardController = new StandardController();
        standardController.setArguments(bundle);
        return standardController;
    }
}
