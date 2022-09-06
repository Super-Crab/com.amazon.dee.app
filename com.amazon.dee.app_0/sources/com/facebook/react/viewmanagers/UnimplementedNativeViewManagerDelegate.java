package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.UnimplementedNativeViewManagerInterface;
/* loaded from: classes2.dex */
public class UnimplementedNativeViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & UnimplementedNativeViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public UnimplementedNativeViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        if (((str.hashCode() == 3373707 && str.equals("name")) ? (char) 0 : (char) 65535) != 0) {
            super.setProperty(t, str, obj);
        } else {
            ((UnimplementedNativeViewManagerInterface) this.mViewManager).setName(t, obj == null ? "" : (String) obj);
        }
    }
}
