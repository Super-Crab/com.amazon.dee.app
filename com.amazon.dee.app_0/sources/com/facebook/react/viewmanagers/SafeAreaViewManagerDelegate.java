package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SafeAreaViewManagerInterface;
/* loaded from: classes2.dex */
public class SafeAreaViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SafeAreaViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SafeAreaViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        boolean z = false;
        if (((str.hashCode() == -1190582359 && str.equals("emulateUnlessSupported")) ? (char) 0 : (char) 65535) != 0) {
            super.setProperty(t, str, obj);
            return;
        }
        SafeAreaViewManagerInterface safeAreaViewManagerInterface = (SafeAreaViewManagerInterface) this.mViewManager;
        if (obj != null) {
            z = ((Boolean) obj).booleanValue();
        }
        safeAreaViewManagerInterface.setEmulateUnlessSupported(t, z);
    }
}
