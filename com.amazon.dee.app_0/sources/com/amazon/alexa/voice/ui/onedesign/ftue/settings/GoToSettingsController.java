package com.amazon.alexa.voice.ui.onedesign.ftue.settings;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
/* loaded from: classes11.dex */
public final class GoToSettingsController extends ViewController implements GoToSettingsContract.View {
    private TextView contentView;
    private TextView linkView;
    private GoToSettingsContract.Presenter presenter;
    private Context themeContext;
    private TextView titleView;

    public static GoToSettingsController create() {
        return new GoToSettingsController();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.View
    public void floodBackgroundToStatusBar() {
    }

    public /* synthetic */ void lambda$onCreate$0$GoToSettingsController(Intent intent, int i) {
        startActivityForResult(intent, i);
    }

    public /* synthetic */ void lambda$onCreateView$1$GoToSettingsController(View view) {
        this.presenter.settingsClicked();
    }

    public /* synthetic */ void lambda$onCreateView$2$GoToSettingsController(View view) {
        this.presenter.closeClicked();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onAttach(@NonNull View view) {
        super.onAttach(view);
        this.presenter.start();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onCreate() {
        overrideTheme(R.style.Alexa_Voice_OneDesign_Moasic);
        this.presenter = new GoToSettingsPresenter(this, new GoToSettingsInteractor(new GoToSettingsMediator(this, new ActivityStarter() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.settings.-$$Lambda$GoToSettingsController$GtK-viXYOfxpHnzcdUKEpQdNEsE
            @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.ActivityStarter
            public final void startActivityForResult(Intent intent, int i) {
                GoToSettingsController.this.lambda$onCreate$0$GoToSettingsController(intent, i);
            }
        })), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())));
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.themeContext = layoutInflater.getContext();
        Preconditions.nonNull(layoutInflater, "inflater argument must be non-null.");
        Preconditions.nonNull(viewGroup, "container argument must be non-null.");
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_permissions_settings, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.contentView = (TextView) inflate.findViewById(R.id.content);
        this.linkView = (TextView) inflate.findViewById(R.id.link);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.contentView);
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.linkView);
        this.linkView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.settings.-$$Lambda$GoToSettingsController$f9JV_KJkYHs6BD-1F3TSAblY3dQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoToSettingsController.this.lambda$onCreateView$1$GoToSettingsController(view);
            }
        });
        inflate.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.settings.-$$Lambda$GoToSettingsController$DkHyNo99W-CiDZ0-HRDli6maHHA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GoToSettingsController.this.lambda$onCreateView$2$GoToSettingsController(view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.amazon.regulator.ViewController
    public void onDestroyView(@NonNull View view) {
        this.titleView = null;
        this.contentView = null;
        this.linkView = null;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.View
    public void setContent(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "content argument must be non-null.");
        this.contentView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.View
    public void setLinkText(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "linkText argument must be non-null.");
        this.linkView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.settings.GoToSettingsContract.View
    public void setTitle(@NonNull CharSequence charSequence) {
        Preconditions.nonNull(charSequence, "title argument must be non-null.");
        this.titleView.setText(charSequence);
    }
}
