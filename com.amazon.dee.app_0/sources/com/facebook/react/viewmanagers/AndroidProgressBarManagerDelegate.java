package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.AndroidProgressBarManagerInterface;
/* loaded from: classes2.dex */
public class AndroidProgressBarManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & AndroidProgressBarManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public AndroidProgressBarManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        boolean z2 = true;
        switch (str.hashCode()) {
            case -1001078227:
                if (str.equals("progress")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -877170387:
                if (str.equals(ViewProps.TEST_ID)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -676876213:
                if (str.equals("typeAttr")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 94842723:
                if (str.equals("color")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 633138363:
                if (str.equals("indeterminate")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1118509918:
                if (str.equals("animating")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1804741442:
                if (str.equals("styleAttr")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        String str2 = null;
        switch (c) {
            case 0:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                androidProgressBarManagerInterface.setStyleAttr(t, str2);
                return;
            case 1:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface2 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    str2 = (String) obj;
                }
                androidProgressBarManagerInterface2.setTypeAttr(t, str2);
                return;
            case 2:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface3 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                androidProgressBarManagerInterface3.setIndeterminate(t, z);
                return;
            case 3:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setProgress(t, obj == null ? FrostVideoEffectController.VIDEO_STRENGTH_CLEAR : ((Double) obj).doubleValue());
                return;
            case 4:
                AndroidProgressBarManagerInterface androidProgressBarManagerInterface4 = (AndroidProgressBarManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                androidProgressBarManagerInterface4.setAnimating(t, z2);
                return;
            case 5:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 6:
                ((AndroidProgressBarManagerInterface) this.mViewManager).setTestID(t, obj == null ? "" : (String) obj);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
