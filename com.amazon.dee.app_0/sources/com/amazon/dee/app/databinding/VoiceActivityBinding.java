package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public abstract class VoiceActivityBinding extends ViewDataBinding {
    @NonNull
    public final FrameLayout ftueContainer;

    /* JADX INFO: Access modifiers changed from: protected */
    public VoiceActivityBinding(Object obj, View view, int i, FrameLayout frameLayout) {
        super(obj, view, i);
        this.ftueContainer = frameLayout;
    }

    public static VoiceActivityBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static VoiceActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VoiceActivityBinding bind(@NonNull View view, @Nullable Object obj) {
        return (VoiceActivityBinding) ViewDataBinding.bind(obj, view, R.layout.voice_activity);
    }

    @NonNull
    @Deprecated
    public static VoiceActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (VoiceActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.voice_activity, viewGroup, z, obj);
    }

    @NonNull
    public static VoiceActivityBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static VoiceActivityBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (VoiceActivityBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.voice_activity, null, false, obj);
    }
}
