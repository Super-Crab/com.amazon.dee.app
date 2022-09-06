package com.amazon.deecomms.oobe.fragments;

import android.os.Bundle;
import android.text.Spannable;
import android.text.style.URLSpan;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.amazon.deecomms.core.CapabilitiesManager;
import com.amazon.deecomms.core.CommsDaggerWrapper;
import com.amazon.deecomms.oobe.OOBEActivity;
import com.amazon.deecomms.oobe.util.ThemingUpdateUtil;
import com.amazon.deecomms.util.URLSpanNoUnderline;
import com.amazon.deecomms.util.URLSpanNoUnderlineAndSetColor;
import javax.inject.Inject;
/* loaded from: classes12.dex */
public abstract class MainOOBEFragment extends Fragment {
    @Inject
    CapabilitiesManager capabilitiesManager;
    private boolean isThemedUIEnabled;
    @Inject
    ThemingUpdateUtil themingUpdateUtil;

    public static void removeUnderlines(Spannable spannable) {
        URLSpan[] uRLSpanArr;
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            int spanStart = spannable.getSpanStart(uRLSpan);
            int spanEnd = spannable.getSpanEnd(uRLSpan);
            spannable.removeSpan(uRLSpan);
            spannable.setSpan(new URLSpanNoUnderline(uRLSpan.getURL()), spanStart, spanEnd, 0);
        }
    }

    public void goBack() {
        ((OOBEActivity) getActivity()).goBack();
    }

    public void goToNextFragment() {
        ((OOBEActivity) getActivity()).nextFragment();
    }

    public boolean isActivityAvailable() {
        OOBEActivity oOBEActivity = (OOBEActivity) getActivity();
        return oOBEActivity != null && !oOBEActivity.isFinishing();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean isThemedUIEnabled() {
        return this.isThemedUIEnabled;
    }

    public void loadState(@NonNull Bundle bundle) {
    }

    public View.OnClickListener nextFragmentClickHandler() {
        return new View.OnClickListener() { // from class: com.amazon.deecomms.oobe.fragments.MainOOBEFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MainOOBEFragment.this.goToNextFragment();
            }
        };
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        CommsDaggerWrapper.getComponent().inject(this);
        this.isThemedUIEnabled = this.capabilitiesManager.isThemedUIEnabled();
    }

    public abstract void recordOobePageStartMetric();

    public void removeUnderlinesAndSetColor(Spannable spannable) {
        URLSpan[] uRLSpanArr;
        for (URLSpan uRLSpan : (URLSpan[]) spannable.getSpans(0, spannable.length(), URLSpan.class)) {
            int spanStart = spannable.getSpanStart(uRLSpan);
            int spanEnd = spannable.getSpanEnd(uRLSpan);
            spannable.removeSpan(uRLSpan);
            spannable.setSpan(new URLSpanNoUnderlineAndSetColor(getActivity(), uRLSpan.getURL(), this.isThemedUIEnabled), spanStart, spanEnd, 0);
        }
    }

    public void saveState(@NonNull Bundle bundle) {
    }

    public void setHeaderTitle(String str) {
        ((OOBEActivity) getActivity()).setHeaderTitle(str);
    }

    public void setVisibility(int i, int i2) {
        getActivity().findViewById(i).setVisibility(i2);
    }
}
