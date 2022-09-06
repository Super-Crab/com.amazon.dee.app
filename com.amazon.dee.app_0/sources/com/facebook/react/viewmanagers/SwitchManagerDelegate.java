package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.SwitchManagerInterface;
/* loaded from: classes2.dex */
public class SwitchManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SwitchManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SwitchManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        if (((str.hashCode() == 1406685743 && str.equals("setValue")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        ((SwitchManagerInterface) this.mViewManager).setValue(t, readableArray.getBoolean(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        switch (str.hashCode()) {
            case -1742453971:
                if (str.equals("thumbColor")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 111972721:
                if (str.equals("value")) {
                    c = 1;
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
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1296813577:
                if (str.equals("onTintColor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1327599912:
                if (str.equals("tintColor")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 2113632767:
                if (str.equals("trackColorForTrue")) {
                    c = 7;
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
                SwitchManagerInterface switchManagerInterface = (SwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                switchManagerInterface.setDisabled(t, z);
                return;
            case 1:
                SwitchManagerInterface switchManagerInterface2 = (SwitchManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                switchManagerInterface2.setValue(t, z);
                return;
            case 2:
                ((SwitchManagerInterface) this.mViewManager).setTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 3:
                ((SwitchManagerInterface) this.mViewManager).setOnTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                ((SwitchManagerInterface) this.mViewManager).setThumbTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 5:
                ((SwitchManagerInterface) this.mViewManager).setThumbColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 6:
                ((SwitchManagerInterface) this.mViewManager).setTrackColorForFalse(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 7:
                ((SwitchManagerInterface) this.mViewManager).setTrackColorForTrue(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
