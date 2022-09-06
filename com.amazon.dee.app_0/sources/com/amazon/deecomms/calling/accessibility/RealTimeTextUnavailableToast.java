package com.amazon.deecomms.calling.accessibility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.deecomms.R;
/* loaded from: classes12.dex */
public class RealTimeTextUnavailableToast {
    private final Toast toast;

    public RealTimeTextUnavailableToast(@NonNull Context context) {
        this.toast = new Toast(context);
    }

    public void showRTTIncapableToast(@NonNull LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.real_time_text_toast, (ViewGroup) null);
        this.toast.setGravity(55, 0, 0);
        this.toast.setDuration(1);
        this.toast.setView(inflate);
        this.toast.show();
    }

    @VisibleForTesting
    public RealTimeTextUnavailableToast(@NonNull Toast toast) {
        this.toast = toast;
    }
}
