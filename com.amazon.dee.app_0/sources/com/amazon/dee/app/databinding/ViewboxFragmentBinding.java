package com.amazon.dee.app.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.amazon.dee.app.R;
import com.amazon.dee.app.ui.clouddrive.ViewBoxImageView;
import com.amazon.dee.app.ui.clouddrive.ViewBoxOverlayView;
import com.amazon.dee.app.ui.clouddrive.ViewBoxViewModel;
/* loaded from: classes12.dex */
public abstract class ViewboxFragmentBinding extends ViewDataBinding {
    @NonNull
    public final Button btnCancel;
    @NonNull
    public final Button btnDone;
    @NonNull
    public final TextView description;
    @NonNull
    public final RelativeLayout fullscreenContent;
    @NonNull
    public final ViewBoxImageView image;
    @Bindable
    protected ViewBoxViewModel mViewModel;
    @NonNull
    public final ViewBoxOverlayView overlay;
    @NonNull
    public final TextView primePhotos;
    @NonNull
    public final TextView tac;
    @NonNull
    public final FrameLayout viewbox;

    /* JADX INFO: Access modifiers changed from: protected */
    public ViewboxFragmentBinding(Object obj, View view, int i, Button button, Button button2, TextView textView, RelativeLayout relativeLayout, ViewBoxImageView viewBoxImageView, ViewBoxOverlayView viewBoxOverlayView, TextView textView2, TextView textView3, FrameLayout frameLayout) {
        super(obj, view, i);
        this.btnCancel = button;
        this.btnDone = button2;
        this.description = textView;
        this.fullscreenContent = relativeLayout;
        this.image = viewBoxImageView;
        this.overlay = viewBoxOverlayView;
        this.primePhotos = textView2;
        this.tac = textView3;
        this.viewbox = frameLayout;
    }

    public static ViewboxFragmentBinding bind(@NonNull View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    public static ViewboxFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Nullable
    public ViewBoxViewModel getViewModel() {
        return this.mViewModel;
    }

    public abstract void setViewModel(@Nullable ViewBoxViewModel viewBoxViewModel);

    @Deprecated
    public static ViewboxFragmentBinding bind(@NonNull View view, @Nullable Object obj) {
        return (ViewboxFragmentBinding) ViewDataBinding.bind(obj, view, R.layout.viewbox_fragment);
    }

    @NonNull
    @Deprecated
    public static ViewboxFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, boolean z, @Nullable Object obj) {
        return (ViewboxFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.viewbox_fragment, viewGroup, z, obj);
    }

    @NonNull
    public static ViewboxFragmentBinding inflate(@NonNull LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @NonNull
    @Deprecated
    public static ViewboxFragmentBinding inflate(@NonNull LayoutInflater layoutInflater, @Nullable Object obj) {
        return (ViewboxFragmentBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.viewbox_fragment, null, false, obj);
    }
}
