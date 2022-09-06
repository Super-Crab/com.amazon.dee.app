package com.amazon.alexa.voice.ui.onedesign.ftue.primer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.font.Font;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.R;
import com.amazon.alexa.voice.ui.onedesign.ftue.VoicePermissionDelegate;
import com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.util.FontUtils;
import com.amazon.alexa.voice.ui.onedesign.util.ViewUtils;
import com.amazon.regulator.ViewController;
import com.amazon.regulator.internal.Preconditions;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
/* loaded from: classes11.dex */
public final class PrimerController extends ViewController implements PrimerContract.View {
    private static final String EXTRA_ALL_PERMISSIONS = "all_permissions";
    private static final String EXTRA_MINIMUM_PERMISSIONS = "minimum_permissions";
    private TextView allowButtonView;
    private Target<GifDrawable> animationTarget;
    private ImageView animationView;
    private TextView laterButtonView;
    private ViewGroup permissionsListContainer;
    private PrimerContract.Presenter presenter;
    private TextView rationaleView;
    private Context themeContext;
    private TextView titleView;

    public static PrimerController create(@NonNull String[] strArr, @NonNull String[] strArr2) {
        Preconditions.nonNull(strArr, "allPermissions must be non-null.");
        Preconditions.nonNull(strArr2, "minimumPersmissions must be non-null.");
        Bundle bundle = new Bundle();
        bundle.putStringArray(EXTRA_ALL_PERMISSIONS, strArr);
        bundle.putStringArray(EXTRA_MINIMUM_PERMISSIONS, strArr2);
        PrimerController primerController = new PrimerController();
        primerController.setArguments(bundle);
        return primerController;
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void floodBackgroundToStatusBar() {
    }

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
        overrideTheme(R.style.Alexa_Voice_OneDesign_Moasic);
        Bundle arguments = getArguments();
        MetricsBridge metricsBridge = null;
        String[] stringArray = arguments != null ? arguments.getStringArray(EXTRA_ALL_PERMISSIONS) : null;
        if (stringArray != null) {
            String[] stringArray2 = arguments != null ? arguments.getStringArray(EXTRA_MINIMUM_PERMISSIONS) : null;
            if (stringArray2 != null) {
                PrimerInteractor primerInteractor = new PrimerInteractor(new PrimerMediator(this, stringArray, stringArray2), new VoicePermissionDelegate(getContext()) { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerController.1
                    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.PermissionsDelegate
                    public void requestPermissions(@NonNull String[] strArr) {
                        PrimerController.this.requestPermissions(strArr, 0);
                    }
                });
                if (getComponent().isRegistered(MetricsBridge.class)) {
                    metricsBridge = (MetricsBridge) getComponent().get(MetricsBridge.class);
                }
                this.presenter = new PrimerPresenter(this, primerInteractor, new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), metricsBridge);
                return;
            }
            throw new IllegalStateException("minimumPermissions must be non-null.");
        }
        throw new IllegalStateException("allPermissions must be non-null.");
    }

    @Override // com.amazon.regulator.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.themeContext = layoutInflater.getContext();
        View inflate = layoutInflater.inflate(R.layout.voice_ui_od_permissions_primer, viewGroup, false);
        ViewUtils.addStatusBarHeightAsTopPadding(inflate);
        this.animationView = (ImageView) inflate.findViewById(R.id.animation);
        this.titleView = (TextView) inflate.findViewById(R.id.title);
        this.rationaleView = (TextView) inflate.findViewById(R.id.rationale);
        this.permissionsListContainer = (ViewGroup) inflate.findViewById(R.id.permissions_container);
        this.laterButtonView = (TextView) inflate.findViewById(R.id.later);
        this.allowButtonView = (TextView) inflate.findViewById(R.id.allow);
        this.animationTarget = new SimpleTarget<GifDrawable>() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerController.2
            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(@NonNull Object obj, @Nullable Transition transition) {
                onResourceReady((GifDrawable) obj, (Transition<? super GifDrawable>) transition);
            }

            public void onResourceReady(@NonNull GifDrawable gifDrawable, @Nullable Transition<? super GifDrawable> transition) {
                gifDrawable.start();
                PrimerController.this.animationView.setImageDrawable(gifDrawable);
            }
        };
        Glide.with(this.animationView.getContext().getApplicationContext()).mo1638asGif().mo6760load(Integer.valueOf(R.raw.voice_ui_od_permissions_primer_animation)).into((RequestBuilder<GifDrawable>) this.animationTarget);
        FontUtils.apply(Font.AMAZON_EMBER_DISPLAY_BOLD, this.titleView);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, this.rationaleView);
        FontUtils.apply(Font.AMAZON_EMBER_MEDIUM, this.allowButtonView, this.laterButtonView);
        this.laterButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.primer.-$$Lambda$PrimerController$V5At5J1-4h0BA_RBYzeeKMPEW2w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                PrimerController.this.lambda$onCreateView$0$PrimerController(view);
            }
        });
        this.allowButtonView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.primer.-$$Lambda$PrimerController$Au5pPmSxbVJ_L8PptXOoBMkQ8cc
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
        this.animationView = null;
        this.titleView = null;
        this.rationaleView = null;
        this.permissionsListContainer = null;
        this.allowButtonView = null;
        this.laterButtonView = null;
    }

    @Override // com.amazon.regulator.ViewController
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.presenter.permissionsResultReceived();
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void setAllowButtonText(@NonNull CharSequence charSequence) {
        this.allowButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void setLaterButtonText(@NonNull CharSequence charSequence) {
        this.laterButtonView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void setPermissionsListText(@NonNull CharSequence charSequence) {
        View inflate = LayoutInflater.from(getView().getContext()).inflate(R.layout.voice_ui_od_permission_bullet_text, this.permissionsListContainer, false);
        TextView textView = (TextView) inflate.findViewById(R.id.permissions);
        textView.setText(charSequence);
        FontUtils.apply(Font.AMAZON_EMBER_REGULAR, textView, (TextView) inflate.findViewById(R.id.bullet));
        this.permissionsListContainer.addView(inflate);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void setPermissionsRationale(@NonNull CharSequence charSequence) {
        this.rationaleView.setText(charSequence);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.primer.PrimerContract.View
    public void setPermissionsTitle(@NonNull CharSequence charSequence) {
        this.titleView.setText(charSequence);
    }
}
