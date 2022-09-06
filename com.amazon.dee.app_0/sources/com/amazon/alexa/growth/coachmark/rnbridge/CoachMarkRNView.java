package com.amazon.alexa.growth.coachmark.rnbridge;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessorykit.finishsetup.bridge.FinishSetupModule;
import com.amazon.alexa.growth.CoachMarkFactory;
import com.amazon.alexa.growth.coachmark.CoachMark;
import com.amazon.alexa.growth.coachmark.Utils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.uimanager.ThemedReactContext;
/* loaded from: classes8.dex */
public class CoachMarkRNView extends ViewGroup {
    private static final String TAG = CoachMarkRNView.class.getSimpleName();
    private CoachMarkFactory coachMarkFactory;
    public String coachMarkId;
    private Boolean deferredShow;
    private Boolean isShown;
    public Boolean showOnLoad;
    public String testId;
    public String text;

    public CoachMarkRNView(ThemedReactContext themedReactContext) {
        super(themedReactContext);
        this.isShown = false;
        this.deferredShow = false;
        this.text = "";
        this.showOnLoad = true;
        this.coachMarkFactory = (CoachMarkFactory) GeneratedOutlineSupport1.outline20(CoachMarkFactory.class);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.alexa.growth.coachmark.rnbridge.CoachMarkRNView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (CoachMarkRNView.this.showOnLoad.booleanValue() || CoachMarkRNView.this.deferredShow.booleanValue()) {
                    CoachMarkRNView.this.show();
                }
            }
        });
    }

    public View getAnchorView() {
        return getChildAt(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onDismiss(View view) {
        Utils.dispatchEvent(this, FinishSetupModule.EVENT_ON_DISMISS, null);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        getViewTreeObserver().dispatchOnGlobalLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @VisibleForTesting
    public void onShow(View view) {
        Utils.dispatchEvent(this, "onShow", null);
    }

    public void show() {
        if (!CoachMarkRNRegistry.contains(this.coachMarkId) && !Utils.isDebuggable(getContext())) {
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CoachMark with id");
            outline107.append(this.coachMarkId);
            outline107.append(" won't be shown since it is not registered yet.See https://tiny.amazon.com/73n9uwqq/codeamazpackAlexblob212fsrc");
            Log.i(str, outline107.toString());
        } else if (this.isShown.booleanValue()) {
            String str2 = TAG;
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("CoachMark with id");
            outline1072.append(this.coachMarkId);
            outline1072.append(" won't be shown since it's already shown before.");
            Log.i(str2, outline1072.toString());
        } else if (getChildCount() != 1) {
            String str3 = TAG;
            StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("CoachMark with id");
            outline1073.append(this.coachMarkId);
            outline1073.append(" won't be shown since it's wrapping ");
            outline1073.append(getChildCount());
            outline1073.append(" children (expected 1).");
            Log.i(str3, outline1073.toString());
            this.deferredShow = true;
        } else {
            View anchorView = getAnchorView();
            if (anchorView != null && anchorView.isLaidOut()) {
                CoachMark coachMark = this.coachMarkFactory.getCoachMark(getAnchorView(), this.coachMarkId);
                coachMark.setTestId(this.testId);
                coachMark.setText(this.text);
                coachMark.setOnShowListener(new CoachMark.OnShowListener() { // from class: com.amazon.alexa.growth.coachmark.rnbridge.-$$Lambda$KcWMBNc0Dm5NWJCG48nYCvwa3XY
                    @Override // com.amazon.alexa.growth.coachmark.CoachMark.OnShowListener
                    public final void onShow(View view) {
                        CoachMarkRNView.this.onShow(view);
                    }
                });
                coachMark.setOnDismissListener(new CoachMark.OnDismissListener() { // from class: com.amazon.alexa.growth.coachmark.rnbridge.-$$Lambda$IYEEh35VIppBZWLQ29YQRMTR8Cc
                    @Override // com.amazon.alexa.growth.coachmark.CoachMark.OnDismissListener
                    public final void onDismiss(View view) {
                        CoachMarkRNView.this.onDismiss(view);
                    }
                });
                coachMark.show();
                this.isShown = true;
                return;
            }
            this.deferredShow = true;
        }
    }
}
