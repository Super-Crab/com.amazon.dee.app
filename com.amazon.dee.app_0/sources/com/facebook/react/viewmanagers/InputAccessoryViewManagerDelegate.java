package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.InputAccessoryViewManagerInterface;
/* loaded from: classes2.dex */
public class InputAccessoryViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & InputAccessoryViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public InputAccessoryViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        if (((str.hashCode() == 1287124693 && str.equals("backgroundColor")) ? (char) 0 : (char) 65535) != 0) {
            super.setProperty(t, str, obj);
        } else {
            ((InputAccessoryViewManagerInterface) this.mViewManager).setBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        }
    }
}
