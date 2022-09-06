package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidViewPagerManagerInterface;
/* loaded from: classes2.dex */
public class AndroidViewPagerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidViewPagerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidViewPagerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode != -445763635) {
            if (hashCode == 1984860689 && str.equals("setPage")) {
                c = 0;
            }
            c = 65535;
        } else {
            if (str.equals("setPageWithoutAnimation")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            ((AndroidViewPagerManagerInterface) this.mViewManager).setPage(t, readableArray.getInt(0));
        } else if (c != 1) {
        } else {
            ((AndroidViewPagerManagerInterface) this.mViewManager).setPageWithoutAnimation(t, readableArray.getInt(0));
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = true;
        int i = 0;
        boolean z2 = false;
        int i2 = 0;
        switch (str.hashCode()) {
            case -1151046732:
                if (str.equals(ReactProperties.SCROLL_ENABLED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -764307226:
                if (str.equals("keyboardDismissMode")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1097821469:
                if (str.equals("pageMargin")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1233251315:
                if (str.equals("initialPage")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1919780198:
                if (str.equals("peekEnabled")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            AndroidViewPagerManagerInterface androidViewPagerManagerInterface = (AndroidViewPagerManagerInterface) this.mViewManager;
            if (obj != null) {
                i = ((Double) obj).intValue();
            }
            androidViewPagerManagerInterface.setInitialPage(t, i);
        } else if (c == 1) {
            AndroidViewPagerManagerInterface androidViewPagerManagerInterface2 = (AndroidViewPagerManagerInterface) this.mViewManager;
            if (obj != null) {
                i2 = ((Double) obj).intValue();
            }
            androidViewPagerManagerInterface2.setPageMargin(t, i2);
        } else if (c == 2) {
            AndroidViewPagerManagerInterface androidViewPagerManagerInterface3 = (AndroidViewPagerManagerInterface) this.mViewManager;
            if (obj != null) {
                z2 = ((Boolean) obj).booleanValue();
            }
            androidViewPagerManagerInterface3.setPeekEnabled(t, z2);
        } else if (c == 3) {
            ((AndroidViewPagerManagerInterface) this.mViewManager).setKeyboardDismissMode(t, (String) obj);
        } else if (c != 4) {
            super.setProperty(t, str, obj);
        } else {
            AndroidViewPagerManagerInterface androidViewPagerManagerInterface4 = (AndroidViewPagerManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            androidViewPagerManagerInterface4.setScrollEnabled(t, z);
        }
    }
}
