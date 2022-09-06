package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidSwipeRefreshLayoutManagerInterface;
/* loaded from: classes2.dex */
public class AndroidSwipeRefreshLayoutManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidSwipeRefreshLayoutManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidSwipeRefreshLayoutManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        if (((str.hashCode() == 513968928 && str.equals("setNativeRefreshing")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setNativeRefreshing(t, readableArray.getBoolean(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        boolean z2 = true;
        int i = 1;
        switch (str.hashCode()) {
            case -1609594047:
                if (str.equals("enabled")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1354842768:
                if (str.equals("colors")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -885150488:
                if (str.equals("progressBackgroundColor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -416037467:
                if (str.equals("progressViewOffset")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -321826009:
                if (str.equals("refreshing")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 3530753:
                if (str.equals("size")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            AndroidSwipeRefreshLayoutManagerInterface androidSwipeRefreshLayoutManagerInterface = (AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager;
            if (obj != null) {
                z2 = ((Boolean) obj).booleanValue();
            }
            androidSwipeRefreshLayoutManagerInterface.setEnabled(t, z2);
        } else if (c == 1) {
            ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setColors(t, (ReadableArray) obj);
        } else if (c == 2) {
            ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setProgressBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        } else if (c == 3) {
            AndroidSwipeRefreshLayoutManagerInterface androidSwipeRefreshLayoutManagerInterface2 = (AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager;
            if (obj != null) {
                i = ((Double) obj).intValue();
            }
            androidSwipeRefreshLayoutManagerInterface2.setSize(t, i);
        } else if (c == 4) {
            ((AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager).setProgressViewOffset(t, obj == null ? 0.0f : ((Double) obj).floatValue());
        } else if (c != 5) {
            super.setProperty(t, str, obj);
        } else {
            AndroidSwipeRefreshLayoutManagerInterface androidSwipeRefreshLayoutManagerInterface3 = (AndroidSwipeRefreshLayoutManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            androidSwipeRefreshLayoutManagerInterface3.setRefreshing(t, z);
        }
    }
}
