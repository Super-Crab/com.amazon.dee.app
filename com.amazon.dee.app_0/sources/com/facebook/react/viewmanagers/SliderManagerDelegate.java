package com.facebook.react.viewmanagers;

import android.view.View;
import androidx.annotation.Nullable;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.amazon.comms.ringservice.webrtc.FrostVideoEffectController;
import com.facebook.react.bridge.ColorPropConverter;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.viewmanagers.SliderManagerInterface;
/* loaded from: classes2.dex */
public class SliderManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & SliderManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public SliderManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, @Nullable Object obj) {
        char c;
        boolean z = false;
        boolean z2 = true;
        switch (str.hashCode()) {
            case -1900655011:
                if (str.equals("maximumTrackTintColor")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -1736983259:
                if (str.equals("thumbImage")) {
                    c = '\n';
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
            case -1021497397:
                if (str.equals("minimumTrackTintColor")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -981448432:
                if (str.equals("maximumTrackImage")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -877170387:
                if (str.equals(ViewProps.TEST_ID)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 3540684:
                if (str.equals("step")) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 111972721:
                if (str.equals("value")) {
                    c = '\r';
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
            case 718061361:
                if (str.equals("maximumValue")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1139400400:
                if (str.equals("trackImage")) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            case 1192487427:
                if (str.equals("minimumValue")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1333596542:
                if (str.equals("minimumTrackImage")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1912319986:
                if (str.equals("thumbTintColor")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        double d = FrostVideoEffectController.VIDEO_STRENGTH_CLEAR;
        switch (c) {
            case 0:
                SliderManagerInterface sliderManagerInterface = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    z = ((Boolean) obj).booleanValue();
                }
                sliderManagerInterface.setDisabled(t, z);
                return;
            case 1:
                SliderManagerInterface sliderManagerInterface2 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    z2 = ((Boolean) obj).booleanValue();
                }
                sliderManagerInterface2.setEnabled(t, z2);
                return;
            case 2:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackImage(t, (ReadableMap) obj);
                return;
            case 3:
                ((SliderManagerInterface) this.mViewManager).setMaximumTrackTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 4:
                ((SliderManagerInterface) this.mViewManager).setMaximumValue(t, obj == null ? 1.0d : ((Double) obj).doubleValue());
                return;
            case 5:
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackImage(t, (ReadableMap) obj);
                return;
            case 6:
                ((SliderManagerInterface) this.mViewManager).setMinimumTrackTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case 7:
                SliderManagerInterface sliderManagerInterface3 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d = ((Double) obj).doubleValue();
                }
                sliderManagerInterface3.setMinimumValue(t, d);
                return;
            case '\b':
                SliderManagerInterface sliderManagerInterface4 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d = ((Double) obj).doubleValue();
                }
                sliderManagerInterface4.setStep(t, d);
                return;
            case '\t':
                ((SliderManagerInterface) this.mViewManager).setTestID(t, obj == null ? "" : (String) obj);
                return;
            case '\n':
                ((SliderManagerInterface) this.mViewManager).setThumbImage(t, (ReadableMap) obj);
                return;
            case 11:
                ((SliderManagerInterface) this.mViewManager).setThumbTintColor(t, ColorPropConverter.getColor(obj, t.getContext()));
                return;
            case '\f':
                ((SliderManagerInterface) this.mViewManager).setTrackImage(t, (ReadableMap) obj);
                return;
            case '\r':
                SliderManagerInterface sliderManagerInterface5 = (SliderManagerInterface) this.mViewManager;
                if (obj != null) {
                    d = ((Double) obj).doubleValue();
                }
                sliderManagerInterface5.setValue(t, d);
                return;
            default:
                super.setProperty(t, str, obj);
                return;
        }
    }
}
