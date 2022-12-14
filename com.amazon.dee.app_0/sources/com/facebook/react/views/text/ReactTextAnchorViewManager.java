package com.facebook.react.views.text;

import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.amazon.clouddrive.cdasdk.aps.account.FeatureState;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.BaseViewManager;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.views.text.ReactBaseTextShadowNode;
import com.facebook.yoga.YogaConstants;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import com.lwansbrough.RCTCamera.RCTCameraModule;
/* loaded from: classes2.dex */
public abstract class ReactTextAnchorViewManager<T extends View, C extends ReactBaseTextShadowNode> extends BaseViewManager<T, C> {
    private static final int[] SPACING_TYPES = {8, 0, 2, 1, 3};
    private static final String TAG = "ReactTextAnchorViewManager";

    @ReactProp(name = ViewProps.ADJUSTS_FONT_SIZE_TO_FIT)
    public void setAdjustFontSizeToFit(ReactTextView reactTextView, boolean z) {
        reactTextView.setAdjustFontSizeToFit(z);
    }

    @ReactProp(name = "android_hyphenationFrequency")
    public void setAndroidHyphenationFrequency(ReactTextView reactTextView, @Nullable String str) {
        int i = Build.VERSION.SDK_INT;
        if (str != null && !str.equals("none")) {
            if (str.equals("full")) {
                reactTextView.setHyphenationFrequency(2);
                return;
            } else if (str.equals("balanced")) {
                reactTextView.setHyphenationFrequency(2);
                return;
            } else if (str.equals(RCTCameraModule.RCT_CAMERA_CAPTURE_QUALITY_HIGH)) {
                reactTextView.setHyphenationFrequency(1);
                return;
            } else if (str.equals(BaseTemplateProvider.NON_INVITATION_KEY)) {
                reactTextView.setHyphenationFrequency(1);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid android_hyphenationFrequency: ", str));
            }
        }
        reactTextView.setHyphenationFrequency(0);
    }

    @ReactPropGroup(customType = "Color", names = {ViewProps.BORDER_COLOR, ViewProps.BORDER_LEFT_COLOR, ViewProps.BORDER_RIGHT_COLOR, ViewProps.BORDER_TOP_COLOR, ViewProps.BORDER_BOTTOM_COLOR})
    public void setBorderColor(ReactTextView reactTextView, int i, Integer num) {
        float f = Float.NaN;
        float intValue = num == null ? Float.NaN : num.intValue() & ViewCompat.MEASURED_SIZE_MASK;
        if (num != null) {
            f = num.intValue() >>> 24;
        }
        reactTextView.setBorderColor(SPACING_TYPES[i], intValue, f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_RADIUS, ViewProps.BORDER_TOP_LEFT_RADIUS, ViewProps.BORDER_TOP_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_RIGHT_RADIUS, ViewProps.BORDER_BOTTOM_LEFT_RADIUS})
    public void setBorderRadius(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        if (i == 0) {
            reactTextView.setBorderRadius(f);
        } else {
            reactTextView.setBorderRadius(f, i - 1);
        }
    }

    @ReactProp(name = "borderStyle")
    public void setBorderStyle(ReactTextView reactTextView, @Nullable String str) {
        reactTextView.setBorderStyle(str);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH})
    public void setBorderWidth(ReactTextView reactTextView, int i, float f) {
        if (!YogaConstants.isUndefined(f)) {
            f = PixelUtil.toPixelFromDIP(f);
        }
        reactTextView.setBorderWidth(SPACING_TYPES[i], f);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @ReactProp(name = "dataDetectorType")
    public void setDataDetectorType(ReactTextView reactTextView, @Nullable String str) {
        char c;
        switch (str.hashCode()) {
            case -1192969641:
                if (str.equals("phoneNumber")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 96673:
                if (str.equals("all")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 3321850:
                if (str.equals("link")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 3387192:
                if (str.equals("none")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 96619420:
                if (str.equals("email")) {
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
            reactTextView.setLinkifyMask(4);
        } else if (c == 1) {
            reactTextView.setLinkifyMask(1);
        } else if (c == 2) {
            reactTextView.setLinkifyMask(2);
        } else if (c != 3) {
            reactTextView.setLinkifyMask(0);
        } else {
            reactTextView.setLinkifyMask(15);
        }
    }

    @ReactProp(defaultBoolean = false, name = FeatureState.DISABLED)
    public void setDisabled(ReactTextView reactTextView, boolean z) {
        reactTextView.setEnabled(!z);
    }

    @ReactProp(name = ViewProps.ELLIPSIZE_MODE)
    public void setEllipsizeMode(ReactTextView reactTextView, @Nullable String str) {
        if (str != null && !str.equals("tail")) {
            if (str.equals(TtmlNode.TAG_HEAD)) {
                reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.START);
                return;
            } else if (str.equals("middle")) {
                reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.MIDDLE);
                return;
            } else if (str.equals("clip")) {
                reactTextView.setEllipsizeLocation(null);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid ellipsizeMode: ", str));
            }
        }
        reactTextView.setEllipsizeLocation(TextUtils.TruncateAt.END);
    }

    @ReactProp(defaultBoolean = true, name = ViewProps.INCLUDE_FONT_PADDING)
    public void setIncludeFontPadding(ReactTextView reactTextView, boolean z) {
        reactTextView.setIncludeFontPadding(z);
    }

    @ReactProp(name = "onInlineViewLayout")
    public void setNotifyOnInlineViewLayout(ReactTextView reactTextView, boolean z) {
        reactTextView.setNotifyOnInlineViewLayout(z);
    }

    @ReactProp(defaultInt = Integer.MAX_VALUE, name = ViewProps.NUMBER_OF_LINES)
    public void setNumberOfLines(ReactTextView reactTextView, int i) {
        reactTextView.setNumberOfLines(i);
    }

    @ReactProp(name = "selectable")
    public void setSelectable(ReactTextView reactTextView, boolean z) {
        reactTextView.setTextIsSelectable(z);
    }

    @ReactProp(customType = "Color", name = "selectionColor")
    public void setSelectionColor(ReactTextView reactTextView, @Nullable Integer num) {
        if (num == null) {
            reactTextView.setHighlightColor(DefaultStyleValuesUtil.getDefaultTextColorHighlight(reactTextView.getContext()));
        } else {
            reactTextView.setHighlightColor(num.intValue());
        }
    }

    @ReactProp(name = ViewProps.TEXT_ALIGN_VERTICAL)
    public void setTextAlignVertical(ReactTextView reactTextView, @Nullable String str) {
        if (str != null && !"auto".equals(str)) {
            if (ViewProps.TOP.equals(str)) {
                reactTextView.setGravityVertical(48);
                return;
            } else if (ViewProps.BOTTOM.equals(str)) {
                reactTextView.setGravityVertical(80);
                return;
            } else if ("center".equals(str)) {
                reactTextView.setGravityVertical(16);
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textAlignVertical: ", str));
            }
        }
        reactTextView.setGravityVertical(0);
    }
}
