package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidDialogPickerManagerInterface;
/* loaded from: classes2.dex */
public class AndroidDialogPickerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidDialogPickerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidDialogPickerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        int i = 0;
        boolean z = true;
        switch (str.hashCode()) {
            case -1609594047:
                if (str.equals("enabled")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -979805852:
                if (str.equals("prompt")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 94842723:
                if (str.equals("color")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 100526016:
                if (str.equals(EntertainmentConstants.ENTERTAINMENT_ITEM_JSON_ATTR_ITEMS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1191572123:
                if (str.equals("selected")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1287124693:
                if (str.equals("backgroundColor")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        Integer num = null;
        if (c == 0) {
            AndroidDialogPickerManagerInterface androidDialogPickerManagerInterface = (AndroidDialogPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                num = Integer.valueOf(((Double) obj).intValue());
            }
            androidDialogPickerManagerInterface.setColor(t, num);
        } else if (c == 1) {
            AndroidDialogPickerManagerInterface androidDialogPickerManagerInterface2 = (AndroidDialogPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                num = Integer.valueOf(((Double) obj).intValue());
            }
            androidDialogPickerManagerInterface2.setBackgroundColor(t, num);
        } else if (c == 2) {
            AndroidDialogPickerManagerInterface androidDialogPickerManagerInterface3 = (AndroidDialogPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            androidDialogPickerManagerInterface3.setEnabled(t, z);
        } else if (c == 3) {
            ((AndroidDialogPickerManagerInterface) this.mViewManager).setItems(t, (ReadableArray) obj);
        } else if (c == 4) {
            ((AndroidDialogPickerManagerInterface) this.mViewManager).setPrompt(t, obj == null ? "" : (String) obj);
        } else if (c != 5) {
            super.setProperty(t, str, obj);
        } else {
            AndroidDialogPickerManagerInterface androidDialogPickerManagerInterface4 = (AndroidDialogPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                i = ((Double) obj).intValue();
            }
            androidDialogPickerManagerInterface4.setSelected(t, i);
        }
    }
}
