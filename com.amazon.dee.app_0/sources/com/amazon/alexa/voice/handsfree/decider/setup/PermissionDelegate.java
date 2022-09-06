package com.amazon.alexa.voice.handsfree.decider.setup;

import android.view.View;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.ui.ManagedActivity;
import com.amazon.alexa.voice.handsfree.metrics.MetricsConstants;
/* loaded from: classes11.dex */
public interface PermissionDelegate {
    void finishStepForPermission(@NonNull ManagedActivity.StepResult stepResult);

    <T extends View> T getViewById(int i);

    boolean hasPermission(String str, String str2);

    void onClickContinueButton();

    void onClickLaterButton();

    void onClickLaterButtonWithConfirmationDialog();

    void onClickLearnMore();

    void setCurrentPage(@NonNull MetricsConstants.PageType pageType);

    void setupContentView(@LayoutRes int i);
}
