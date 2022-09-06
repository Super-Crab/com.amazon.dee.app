package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.DatePickerManagerInterface;
import com.reactcommunity.rndatetimepicker.RNConstants;
import com.sun.mail.imap.IMAPStore;
/* loaded from: classes2.dex */
public class DatePickerManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & DatePickerManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public DatePickerManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    public void receiveCommand(DatePickerManagerInterface<T> datePickerManagerInterface, T t, String str, ReadableArray readableArray) {
        if (((str.hashCode() == -714877337 && str.equals("setNativeDate")) ? (char) 0 : (char) 65535) != 0) {
            return;
        }
        datePickerManagerInterface.setNativeDate(t, (float) readableArray.getDouble(0));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        int i = 1;
        switch (str.hashCode()) {
            case -1339516167:
                if (str.equals(RNConstants.ARG_INTERVAL)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1097462182:
                if (str.equals("locale")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -292758706:
                if (str.equals("timeZoneOffsetInMinutes")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 3076014:
                if (str.equals(IMAPStore.ID_DATE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 3357091:
                if (str.equals("mode")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1007762652:
                if (str.equals(RNConstants.ARG_MINDATE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1232894226:
                if (str.equals("initialDate")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1685195246:
                if (str.equals(RNConstants.ARG_MAXDATE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        float f = 0.0f;
        switch (c) {
            case 0:
                DatePickerManagerInterface datePickerManagerInterface = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                datePickerManagerInterface.setDate(t, f);
                return;
            case 1:
                DatePickerManagerInterface datePickerManagerInterface2 = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                datePickerManagerInterface2.setInitialDate(t, f);
                return;
            case 2:
                ((DatePickerManagerInterface) this.mViewManager).setLocale(t, obj == null ? null : (String) obj);
                return;
            case 3:
                DatePickerManagerInterface datePickerManagerInterface3 = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                datePickerManagerInterface3.setMaximumDate(t, f);
                return;
            case 4:
                DatePickerManagerInterface datePickerManagerInterface4 = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                datePickerManagerInterface4.setMinimumDate(t, f);
                return;
            case 5:
                DatePickerManagerInterface datePickerManagerInterface5 = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    i = ((Double) obj).intValue();
                }
                datePickerManagerInterface5.setMinuteInterval(t, Integer.valueOf(i));
                return;
            case 6:
                ((DatePickerManagerInterface) this.mViewManager).setMode(t, (String) obj);
                return;
            case 7:
                DatePickerManagerInterface datePickerManagerInterface6 = (DatePickerManagerInterface) this.mViewManager;
                if (obj != null) {
                    f = ((Double) obj).floatValue();
                }
                datePickerManagerInterface6.setTimeZoneOffsetInMinutes(t, f);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
