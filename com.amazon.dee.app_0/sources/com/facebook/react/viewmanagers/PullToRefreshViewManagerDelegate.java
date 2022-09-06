package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.PullToRefreshViewManagerInterface;
/* loaded from: classes2.dex */
public class PullToRefreshViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & PullToRefreshViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public PullToRefreshViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    public void receiveCommand(PullToRefreshViewManagerInterface<T> pullToRefreshViewManagerInterface, T t, String str, ReadableArray readableArray) {
        if (((str.hashCode() == 513968928 && str.equals("setNativeRefreshing")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        pullToRefreshViewManagerInterface.setNativeRefreshing(t, readableArray.getBoolean(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        switch (str.hashCode()) {
            case -1799367701:
                if (str.equals("titleColor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -321826009:
                if (str.equals("refreshing")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 110371416:
                if (str.equals("title")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            ((PullToRefreshViewManagerInterface) this.mViewManager).setTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        } else if (c == 1) {
            ((PullToRefreshViewManagerInterface) this.mViewManager).setTitleColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        } else if (c == 2) {
            ((PullToRefreshViewManagerInterface) this.mViewManager).setTitle(t, obj == null ? null : (String) obj);
        } else if (c != 3) {
            super.setProperty(t, str, obj);
        } else {
            PullToRefreshViewManagerInterface pullToRefreshViewManagerInterface = (PullToRefreshViewManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            pullToRefreshViewManagerInterface.setRefreshing(t, z);
        }
    }
}
