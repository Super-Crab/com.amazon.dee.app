package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.ActivityIndicatorViewManagerInterface;
/* loaded from: classes2.dex */
public class ActivityIndicatorViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & ActivityIndicatorViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public ActivityIndicatorViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        switch (str.hashCode()) {
            case 3530753:
                if (str.equals("size")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 94842723:
                if (str.equals("color")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 865748226:
                if (str.equals("hidesWhenStopped")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1118509918:
                if (str.equals("animating")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            ActivityIndicatorViewManagerInterface activityIndicatorViewManagerInterface = (ActivityIndicatorViewManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            activityIndicatorViewManagerInterface.setHidesWhenStopped(t, z);
        } else if (c == 1) {
            ActivityIndicatorViewManagerInterface activityIndicatorViewManagerInterface2 = (ActivityIndicatorViewManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            activityIndicatorViewManagerInterface2.setAnimating(t, z);
        } else if (c == 2) {
            ((ActivityIndicatorViewManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        } else if (c != 3) {
            super.setProperty(t, str, obj);
        } else {
            ((ActivityIndicatorViewManagerInterface) this.mViewManager).setSize(t, (String) obj);
        }
    }
}
