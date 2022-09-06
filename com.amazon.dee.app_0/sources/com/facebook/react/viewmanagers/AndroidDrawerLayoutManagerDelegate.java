package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidDrawerLayoutManagerInterface;
/* loaded from: classes2.dex */
public class AndroidDrawerLayoutManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidDrawerLayoutManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidDrawerLayoutManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -258774775) {
            if (hashCode == -83186725 && str.equals("openDrawer")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("closeDrawer")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).openDrawer(t);
        } else if (c != 1) {
        } else {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).closeDrawer(t);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        switch (str.hashCode()) {
            case -2082382380:
                if (str.equals("statusBarBackgroundColor")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1233873500:
                if (str.equals("drawerBackgroundColor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -764307226:
                if (str.equals("keyboardDismissMode")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 268251989:
                if (str.equals("drawerWidth")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 695891258:
                if (str.equals("drawerPosition")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1857208703:
                if (str.equals("drawerLockMode")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
        } else if (c == 1) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        } else if (c == 2) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerPosition(t, (String) obj);
        } else if (c == 3) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerWidth(t, obj == null ? null : Float.valueOf(((Double) obj).floatValue()));
        } else if (c == 4) {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setDrawerLockMode(t, (String) obj);
        } else if (c != 5) {
            super.setProperty(t, str, obj);
        } else {
            ((AndroidDrawerLayoutManagerInterface) this.mViewManager).setStatusBarBackgroundColor(t, ColorPropConverter.getColor(obj, t.getContext()));
        }
    }
}
