package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidSwitchManagerInterface;
/* loaded from: classes2.dex */
public class AndroidSwitchManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidSwitchManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidSwitchManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        if (((str.hashCode() == -669744680 && str.equals("setNativeValue")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        ((AndroidSwitchManagerInterface) this.mViewManager).setNativeValue(t, readableArray.getBoolean(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = true;
        boolean z2 = false;
        switch (str.hashCode()) {
            case -1742453971:
                if (str.equals("thumbColor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1609594047:
                if (str.equals("enabled")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -287374307:
                if (str.equals("trackTintColor")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 3551:
                if (str.equals("on")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 111972721:
                if (str.equals("value")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 270940796:
                if (str.equals(FeatureState.DISABLED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1084662482:
                if (str.equals("trackColorForFalse")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 2113632767:
                if (str.equals("trackColorForTrue")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                AndroidSwitchManagerInterface androidSwitchManagerInterface = (AndroidSwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                androidSwitchManagerInterface.setDisabled(t, z2);
                return;
            case 1:
                AndroidSwitchManagerInterface androidSwitchManagerInterface2 = (AndroidSwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                androidSwitchManagerInterface2.setEnabled(t, z);
                return;
            case 2:
                ((AndroidSwitchManagerInterface) this.mViewManager).setThumbColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 3:
                ((AndroidSwitchManagerInterface) this.mViewManager).setTrackColorForFalse(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                ((AndroidSwitchManagerInterface) this.mViewManager).setTrackColorForTrue(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 5:
                AndroidSwitchManagerInterface androidSwitchManagerInterface3 = (AndroidSwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                androidSwitchManagerInterface3.setValue(t, z2);
                return;
            case 6:
                AndroidSwitchManagerInterface androidSwitchManagerInterface4 = (AndroidSwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                androidSwitchManagerInterface4.setOn(t, z2);
                return;
            case 7:
                ((AndroidSwitchManagerInterface) this.mViewManager).setThumbTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '\b':
                ((AndroidSwitchManagerInterface) this.mViewManager).setTrackTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
