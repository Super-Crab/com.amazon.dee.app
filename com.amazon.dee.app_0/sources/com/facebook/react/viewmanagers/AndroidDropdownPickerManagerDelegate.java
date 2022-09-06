package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.AndroidDropdownPickerManagerInterface;
/* loaded from: classes2.dex */
public class AndroidDropdownPickerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidDropdownPickerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidDropdownPickerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
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
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -979805852:
                if (str.equals("prompt")) {
                    c = 3;
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
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1191572123:
                if (str.equals("selected")) {
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
            ((AndroidDropdownPickerManagerInterface) this.mViewManager).setColor(t, obj == null ? null : Integer.valueOf(((Double) obj).intValue()));
        } else if (c == 1) {
            AndroidDropdownPickerManagerInterface androidDropdownPickerManagerInterface = (AndroidDropdownPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                z = ((Boolean) obj).booleanValue();
            }
            androidDropdownPickerManagerInterface.setEnabled(t, z);
        } else if (c == 2) {
            ((AndroidDropdownPickerManagerInterface) this.mViewManager).setItems(t, (ReadableArray) obj);
        } else if (c == 3) {
            ((AndroidDropdownPickerManagerInterface) this.mViewManager).setPrompt(t, obj == null ? "" : (String) obj);
        } else if (c != 4) {
            super.setProperty(t, str, obj);
        } else {
            AndroidDropdownPickerManagerInterface androidDropdownPickerManagerInterface2 = (AndroidDropdownPickerManagerInterface) this.mViewManager;
            if (obj != null) {
                i = ((Double) obj).intValue();
            }
            androidDropdownPickerManagerInterface2.setSelected(t, i);
        }
    }
}
