package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink;
import com.amazon.regulator.ViewController;
/* loaded from: classes11.dex */
public abstract class PrimerController extends ViewController implements HandsfreePrimerContract.View {
    protected TextView allowButtonView;
    protected TextView handsfreeSettingsView;
    protected TextView laterButtonView;
    private HandsfreePrimerContract.Presenter presenter;
    protected TextViewWithLink rationaleView;
    private Context themeContext;
    protected TextView titleView;
    protected TextViewWithLink usageView;

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void floodBackgroundToStatusBar() {
    }

    protected abstract HandsfreePrimerContract.Presenter getPresenter();

    @Nullable
    protected abstract TextViewWithLink.OnEmbeddedLinkClickListener getRationaleClickListener();

    @Nullable
    protected abstract TextViewWithLink.OnEmbeddedLinkClickListener getUsageClickListener();

    public /* synthetic */ void lambda$onCreateView$0$PrimerController(View view) {
        this.presenter.laterClicked();
    }

    public /* synthetic */ void lambda$onCreateView$1$PrimerController(View view) {
        this.presenter.allowClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(View view) {
        super.onAttach(view);
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        super.onCreate();
        overrideTheme(R.style.Alexa_Voice_OneDesign_Moasic);
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected final View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.presenter = getPresenter();
        this.themeContext = layoutInflater.getContext();
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_handsfree_vox_primer, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.usageView = (TextViewWithLink) inflate.findViewById(R.id.usage);
        this.handsfreeSettingsView = (TextView) inflate.findViewById(R.id.handsfree_settings_info);
        this.rationaleView = (TextViewWithLink) inflate.findViewById(R.id.rationale);
        this.laterButtonView = (TextView) inflate.findViewById(R.id.later);
        this.allowButtonView = (TextView) inflate.findViewById(R.id.allow);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_BOLD, this.usageView, this.rationaleView, this.allowButtonView, this.laterButtonView, this.handsfreeSettingsView);
        this.laterButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$PrimerController$2aAJvE8vxIBiN_jGSuSnBnID-2g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrimerController.this.lambda$onCreateView$0$PrimerController(view);
            }
        });
        this.allowButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$PrimerController$7XWFYpU-cFMzdBFEPmL8dFrN6eI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrimerController.this.lambda$onCreateView$1$PrimerController(view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.titleView = null;
        this.usageView = null;
        this.rationaleView = null;
        this.allowButtonView = null;
        this.laterButtonView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setAllowButtonText(@NonNull CharSequence charSequence) {
        this.allowButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setHandsfreeSettingsInfo(@NonNull CharSequence charSequence) {
        this.handsfreeSettingsView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setLaterButtonText(@NonNull CharSequence charSequence) {
        this.laterButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setRationale(@NonNull CharSequence charSequence, CharSequence charSequence2) {
        if (TextUtils.isEmpty(charSequence)) {
            this.rationaleView.setVisibility(8);
        } else {
            this.rationaleView.setTextWithLink(charSequence, charSequence2, getRationaleClickListener());
        }
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setTitle(@NonNull CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract.View
    public void setUsage(@NonNull CharSequence charSequence, CharSequence charSequence2) {
        this.usageView.setTextWithLink(charSequence, charSequence2, getUsageClickListener());
    }
}
