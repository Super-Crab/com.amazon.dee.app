package com.facebook.react.uimanager;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
/* loaded from: classes2.dex */
public abstract class BaseViewManagerAdapter<T extends View> implements BaseViewManagerInterface<T> {
    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setAccessibilityActions(@NonNull T t, ReadableArray readableArray) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setAccessibilityHint(@NonNull T t, String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setAccessibilityLabel(@NonNull T t, String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setAccessibilityLiveRegion(@NonNull T t, @Nullable String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setAccessibilityRole(@NonNull T t, @Nullable String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBackgroundColor(@NonNull T t, int i) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomLeftRadius(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderBottomRightRadius(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderRadius(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopLeftRadius(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setBorderTopRightRadius(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setElevation(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setImportantForAccessibility(@NonNull T t, @Nullable String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setNativeId(@NonNull T t, String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setOpacity(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setRenderToHardwareTexture(@NonNull T t, boolean z) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setRotation(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setScaleX(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setScaleY(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setShadowColor(@NonNull T t, int i) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setTestId(@NonNull T t, String str) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setTransform(@NonNull T t, @Nullable ReadableArray readableArray) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setTranslateX(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setTranslateY(@NonNull T t, float f) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setViewState(@NonNull T t, @Nullable ReadableMap readableMap) {
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerInterface
    public void setZIndex(@NonNull T t, float f) {
    }
}
