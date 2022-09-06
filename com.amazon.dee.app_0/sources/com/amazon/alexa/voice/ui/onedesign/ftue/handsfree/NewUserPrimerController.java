package com.amazon.alexa.voice.ui.onedesign.ftue.handsfree;

import android.os.Bundle;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.voice.metrics.MetricsBridge;
import com.amazon.alexa.voice.ui.onedesign.ftue.VoicePermissionDelegate;
import com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.HandsfreePrimerContract;
import com.amazon.alexa.voice.ui.onedesign.util.AndroidResources;
import com.amazon.alexa.voice.ui.onedesign.util.ComponentUtils;
import com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public final class NewUserPrimerController extends PrimerController implements HandsfreePrimerContract.View {
    private static final String EXTRA_ALL_PERMISSIONS = "all_permissions";
    private static final String EXTRA_VIEW_PROPERTIES = "view_props";
    private static final String TAG = "NewUserPrimerController";
    private NewUserPrimerPresenter presenter;
    JSONObject viewProperties;

    public static NewUserPrimerController create(@NonNull String[] strArr) {
        return create(strArr, null);
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    protected HandsfreePrimerContract.Presenter getPresenter() {
        Bundle arguments = getArguments();
        String[] stringArray = arguments != null ? arguments.getStringArray(EXTRA_ALL_PERMISSIONS) : null;
        if (stringArray != null) {
            if (arguments.getString(EXTRA_VIEW_PROPERTIES) != null) {
                try {
                    this.viewProperties = new JSONObject(arguments.getString(EXTRA_VIEW_PROPERTIES));
                } catch (JSONException unused) {
                    Log.e(TAG, "error in getting view properties");
                }
            }
            this.presenter = new NewUserPrimerPresenter(this, new NewUserPrimerInteractor(new NewUserPrimerMediator(this, new VoicePermissionDelegate(getContext()) { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.NewUserPrimerController.1
                @Override // com.amazon.alexa.voice.ui.onedesign.ftue.PermissionsDelegate
                public void requestPermissions(@NonNull String[] strArr) {
                    NewUserPrimerController.this.requestPermissions(strArr, 0);
                }
            }, stringArray), (OnPermissionResultReceivedListener) getComponent().get(OnPermissionResultReceivedListener.class)), new AndroidResources(getContext(), ComponentUtils.getLocale(getComponent())), (MetricsBridge) getComponent().get(MetricsBridge.class), this.viewProperties, getContext());
            return this.presenter;
        }
        throw new IllegalStateException("allPermissions must be non-null.");
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    @Nullable
    protected TextViewWithLink.OnEmbeddedLinkClickListener getRationaleClickListener() {
        return new TextViewWithLink.OnEmbeddedLinkClickListener() { // from class: com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.-$$Lambda$NewUserPrimerController$IN44nwcDx-o_sm5sryRjhwywxAs
            @Override // com.amazon.alexa.voice.ui.onedesign.widget.TextViewWithLink.OnEmbeddedLinkClickListener
            public final void onEmbeddedLinkClick() {
                NewUserPrimerController.this.lambda$getRationaleClickListener$0$NewUserPrimerController();
            }
        };
    }

    @Override // com.amazon.alexa.voice.ui.onedesign.ftue.handsfree.PrimerController
    @Nullable
    protected TextViewWithLink.OnEmbeddedLinkClickListener getUsageClickListener() {
        return null;
    }

    public /* synthetic */ void lambda$getRationaleClickListener$0$NewUserPrimerController() {
        this.presenter.learnMoreClicked();
    }

    @Override // com.amazon.regulator.ViewController
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.presenter.permissionsResultReceived();
    }

    public static NewUserPrimerController create(@NonNull String[] strArr, @Nullable String str) {
        Bundle bundle = new Bundle();
        bundle.putStringArray(EXTRA_ALL_PERMISSIONS, strArr);
        if (str != null) {
            bundle.putString(EXTRA_VIEW_PROPERTIES, str);
        }
        NewUserPrimerController newUserPrimerController = new NewUserPrimerController();
        newUserPrimerController.setArguments(bundle);
        return newUserPrimerController;
    }
}
