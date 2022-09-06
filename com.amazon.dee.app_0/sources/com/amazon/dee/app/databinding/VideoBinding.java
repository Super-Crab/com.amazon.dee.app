package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
/* loaded from: classes12.dex */
public abstract class VideoBinding extends ViewDataBinding {
    @NonNull
    public final VideoView VideoView;

    /* JADX INFO: Access modifiers changed from: protected */
    public VideoBinding(Object obj, View view, int i, VideoView videoView) {
        super(obj, view, i);
        this.VideoView = videoView;
    }

    public static VideoBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static VideoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static VideoBinding bind(@NonNull View view, @Nullable Object obj) {
        return (VideoBinding) ViewDataBinding.bind(obj, view, R.layout.video);
    }

    @NonNull
    @Deprecated
    public static VideoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (VideoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video, viewGroup, z, obj);
    }

    @NonNull
    public static VideoBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static VideoBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (VideoBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.video, null, false, obj);
    }
}
