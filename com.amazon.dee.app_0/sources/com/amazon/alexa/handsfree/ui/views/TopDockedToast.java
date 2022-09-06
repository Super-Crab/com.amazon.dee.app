package com.amazon.alexa.handsfree.ui.views;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.ui.R;
/* loaded from: classes8.dex */
public class TopDockedToast {
    private TextView mTextView;
    private Toast mToast;

    private TopDockedToast(@NonNull Activity activity, @NonNull ViewGroup viewGroup) {
        View inflate = activity.getLayoutInflater().inflate(R.layout.top_docked_toast, viewGroup);
        this.mTextView = (TextView) inflate.findViewById(R.id.top_docked_toast_text);
        this.mToast = new Toast(activity.getApplicationContext());
        this.mToast.setGravity(55, 0, 0);
        this.mToast.setView(inflate);
    }

    public static TopDockedToast newInstance(@NonNull Activity activity) {
        return new TopDockedToast(activity, (ViewGroup) activity.findViewById(R.id.top_docked_toast_container));
    }

    public TopDockedToast setDuration(int i) {
        this.mToast.setDuration(i);
        return this;
    }

    public TopDockedToast setText(String str) {
        this.mTextView.setText(str);
        return this;
    }

    public void show() {
        this.mToast.show();
    }
}
