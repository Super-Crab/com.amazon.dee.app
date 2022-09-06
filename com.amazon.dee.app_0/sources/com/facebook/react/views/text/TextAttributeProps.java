package com.facebook.react.views.text;

import android.os.Build;
import androidx.annotation.Nullable;
import androidx.room.FtsOptions;
import com.amazon.alexa.accessory.notificationpublisher.providers.BaseTemplateProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.uimanager.ReactAccessibilityDelegate;
import com.facebook.react.uimanager.ReactStylesDiffMap;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.yoga.YogaDirection;
import com.google.android.exoplayer2.text.ttml.TtmlNode;
import org.apache.logging.log4j.util.ProcessIdUtil;
/* loaded from: classes2.dex */
public class TextAttributeProps {
    private static final int DEFAULT_BREAK_STRATEGY;
    private static final int DEFAULT_JUSTIFICATION_MODE;
    private static final int DEFAULT_TEXT_SHADOW_COLOR = 1426063360;
    private static final String INLINE_IMAGE_PLACEHOLDER = "I";
    private static final String PROP_SHADOW_COLOR = "textShadowColor";
    private static final String PROP_SHADOW_OFFSET = "textShadowOffset";
    private static final String PROP_SHADOW_OFFSET_HEIGHT = "height";
    private static final String PROP_SHADOW_OFFSET_WIDTH = "width";
    private static final String PROP_SHADOW_RADIUS = "textShadowRadius";
    private static final String PROP_TEXT_TRANSFORM = "textTransform";
    public static final int UNSET = -1;
    protected int mBackgroundColor;
    protected int mColor;
    private final ReactStylesDiffMap mProps;
    protected float mLineHeight = Float.NaN;
    protected boolean mIsColorSet = false;
    protected boolean mAllowFontScaling = true;
    protected boolean mIsBackgroundColorSet = false;
    protected int mNumberOfLines = -1;
    protected int mFontSize = -1;
    protected float mFontSizeInput = -1.0f;
    protected float mLineHeightInput = -1.0f;
    protected float mLetterSpacingInput = Float.NaN;
    protected int mTextAlign = 0;
    protected int mLayoutDirection = -1;
    protected TextTransform mTextTransform = TextTransform.UNSET;
    protected float mTextShadowOffsetDx = 0.0f;
    protected float mTextShadowOffsetDy = 0.0f;
    protected float mTextShadowRadius = 1.0f;
    protected int mTextShadowColor = 1426063360;
    protected boolean mIsUnderlineTextDecorationSet = false;
    protected boolean mIsLineThroughTextDecorationSet = false;
    protected boolean mIncludeFontPadding = true;
    @Nullable
    protected ReactAccessibilityDelegate.AccessibilityRole mAccessibilityRole = null;
    protected boolean mIsAccessibilityRoleSet = false;
    protected int mFontStyle = -1;
    protected int mFontWeight = -1;
    @Nullable
    protected String mFontFamily = null;
    @Nullable
    protected String mFontFeatureSettings = null;
    protected boolean mContainsImages = false;
    protected float mHeightOfTallestInlineImage = Float.NaN;

    static {
        int i = Build.VERSION.SDK_INT;
        DEFAULT_JUSTIFICATION_MODE = 0;
        DEFAULT_BREAK_STRATEGY = 1;
    }

    public TextAttributeProps(ReactStylesDiffMap reactStylesDiffMap) {
        ReadableMap readableMap = null;
        this.mProps = reactStylesDiffMap;
        setNumberOfLines(getIntProp(ViewProps.NUMBER_OF_LINES, -1));
        setLineHeight(getFloatProp(ViewProps.LINE_HEIGHT, -1.0f));
        setLetterSpacing(getFloatProp(ViewProps.LETTER_SPACING, Float.NaN));
        setAllowFontScaling(getBooleanProp(ViewProps.ALLOW_FONT_SCALING, true));
        setFontSize(getFloatProp("fontSize", -1.0f));
        setColor(reactStylesDiffMap.hasKey("color") ? Integer.valueOf(reactStylesDiffMap.getInt("color", 0)) : null);
        setColor(reactStylesDiffMap.hasKey("foregroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("foregroundColor", 0)) : null);
        setBackgroundColor(reactStylesDiffMap.hasKey("backgroundColor") ? Integer.valueOf(reactStylesDiffMap.getInt("backgroundColor", 0)) : null);
        setFontFamily(getStringProp("fontFamily"));
        setFontWeight(getStringProp("fontWeight"));
        setFontStyle(getStringProp("fontStyle"));
        setFontVariant(getArrayProp(ViewProps.FONT_VARIANT));
        setIncludeFontPadding(getBooleanProp(ViewProps.INCLUDE_FONT_PADDING, true));
        setTextDecorationLine(getStringProp(ViewProps.TEXT_DECORATION_LINE));
        setTextShadowOffset(reactStylesDiffMap.hasKey("textShadowOffset") ? reactStylesDiffMap.getMap("textShadowOffset") : readableMap);
        setTextShadowRadius(getIntProp("textShadowRadius", 1));
        setTextShadowColor(getIntProp("textShadowColor", 1426063360));
        setTextTransform(getStringProp("textTransform"));
        setLayoutDirection(getStringProp(ViewProps.LAYOUT_DIRECTION));
        setAccessibilityRole(getStringProp(ViewProps.ACCESSIBILITY_ROLE));
    }

    @Nullable
    private ReadableArray getArrayProp(String str) {
        if (this.mProps.hasKey(str)) {
            return this.mProps.getArray(str);
        }
        return null;
    }

    private boolean getBooleanProp(String str, boolean z) {
        return this.mProps.hasKey(str) ? this.mProps.getBoolean(str, z) : z;
    }

    private float getFloatProp(String str, float f) {
        return this.mProps.hasKey(str) ? this.mProps.getFloat(str, f) : f;
    }

    private int getIntProp(String str, int i) {
        return this.mProps.hasKey(str) ? this.mProps.getInt(str, i) : i;
    }

    public static int getJustificationMode(ReactStylesDiffMap reactStylesDiffMap) {
        if ("justify".equals(reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null)) {
            int i = Build.VERSION.SDK_INT;
            return 1;
        }
        return DEFAULT_JUSTIFICATION_MODE;
    }

    private YogaDirection getLayoutDirection() {
        return YogaDirection.LTR;
    }

    private float getPaddingProp(String str) {
        if (this.mProps.hasKey(ViewProps.PADDING)) {
            return PixelUtil.toPixelFromDIP(getFloatProp(ViewProps.PADDING, 0.0f));
        }
        return PixelUtil.toPixelFromDIP(getFloatProp(str, 0.0f));
    }

    private String getStringProp(String str) {
        if (this.mProps.hasKey(str)) {
            return this.mProps.getString(str);
        }
        return null;
    }

    public static int getTextAlignment(ReactStylesDiffMap reactStylesDiffMap, boolean z) {
        String string = reactStylesDiffMap.hasKey("textAlign") ? reactStylesDiffMap.getString("textAlign") : null;
        if ("justify".equals(string)) {
            return 3;
        }
        if (string == null || "auto".equals(string)) {
            return 0;
        }
        if ("left".equals(string)) {
            if (!z) {
                return 3;
            }
        } else if (!"right".equals(string)) {
            if (!"center".equals(string)) {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textAlign: ", string));
            }
            return 1;
        } else if (z) {
            return 3;
        }
        return 5;
    }

    public static int getTextBreakStrategy(@Nullable String str) {
        int i = DEFAULT_BREAK_STRATEGY;
        if (str != null) {
            char c = 65535;
            int hashCode = str.hashCode();
            if (hashCode != -1924829944) {
                if (hashCode == -902286926 && str.equals(FtsOptions.TOKENIZER_SIMPLE)) {
                    c = 0;
                }
            } else if (str.equals("balanced")) {
                c = 1;
            }
            if (c == 0) {
                return 0;
            }
            return c != 1 ? 1 : 2;
        }
        return i;
    }

    private static int parseNumericFontWeight(String str) {
        if (str.length() != 3 || !str.endsWith("00") || str.charAt(0) > '9' || str.charAt(0) < '1') {
            return -1;
        }
        return (str.charAt(0) - '0') * 100;
    }

    public float getBottomPadding() {
        return getPaddingProp(ViewProps.PADDING_BOTTOM);
    }

    public float getEffectiveLineHeight() {
        return !Float.isNaN(this.mLineHeight) && !Float.isNaN(this.mHeightOfTallestInlineImage) && (this.mHeightOfTallestInlineImage > this.mLineHeight ? 1 : (this.mHeightOfTallestInlineImage == this.mLineHeight ? 0 : -1)) > 0 ? this.mHeightOfTallestInlineImage : this.mLineHeight;
    }

    public float getEndPadding() {
        return getPaddingProp(ViewProps.PADDING_END);
    }

    public float getLeftPadding() {
        return getPaddingProp(ViewProps.PADDING_LEFT);
    }

    public float getLetterSpacing() {
        float pixelFromDIP;
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(this.mLetterSpacingInput);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(this.mLetterSpacingInput);
        }
        int i = this.mFontSize;
        if (i > 0) {
            return pixelFromDIP / i;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("FontSize should be a positive value. Current value: ");
        outline107.append(this.mFontSize);
        throw new IllegalArgumentException(outline107.toString());
    }

    public float getRightPadding() {
        return getPaddingProp(ViewProps.PADDING_RIGHT);
    }

    public float getStartPadding() {
        return getPaddingProp(ViewProps.PADDING_START);
    }

    public float getTopPadding() {
        return getPaddingProp(ViewProps.PADDING_TOP);
    }

    public void setAccessibilityRole(@Nullable String str) {
        if (str != null) {
            this.mIsAccessibilityRoleSet = true;
            this.mAccessibilityRole = ReactAccessibilityDelegate.AccessibilityRole.fromValue(str);
        }
    }

    public void setAllowFontScaling(boolean z) {
        if (z != this.mAllowFontScaling) {
            this.mAllowFontScaling = z;
            setFontSize(this.mFontSizeInput);
            setLineHeight(this.mLineHeightInput);
            setLetterSpacing(this.mLetterSpacingInput);
        }
    }

    public void setBackgroundColor(Integer num) {
        this.mIsBackgroundColorSet = num != null;
        if (this.mIsBackgroundColorSet) {
            this.mBackgroundColor = num.intValue();
        }
    }

    public void setColor(@Nullable Integer num) {
        this.mIsColorSet = num != null;
        if (this.mIsColorSet) {
            this.mColor = num.intValue();
        }
    }

    public void setFontFamily(@Nullable String str) {
        this.mFontFamily = str;
    }

    public void setFontSize(float f) {
        double ceil;
        this.mFontSizeInput = f;
        if (f != -1.0f) {
            if (this.mAllowFontScaling) {
                ceil = Math.ceil(PixelUtil.toPixelFromSP(f));
            } else {
                ceil = Math.ceil(PixelUtil.toPixelFromDIP(f));
            }
            f = (float) ceil;
        }
        this.mFontSize = (int) f;
    }

    public void setFontStyle(@Nullable String str) {
        int i;
        if (TtmlNode.ITALIC.equals(str)) {
            i = 2;
        } else {
            i = BaseTemplateProvider.NON_INVITATION_KEY.equals(str) ? 0 : -1;
        }
        if (i != this.mFontStyle) {
            this.mFontStyle = i;
        }
    }

    public void setFontVariant(@Nullable ReadableArray readableArray) {
        this.mFontFeatureSettings = ReactTypefaceUtils.parseFontVariant(readableArray);
    }

    public void setFontWeight(@Nullable String str) {
        int i = -1;
        int parseNumericFontWeight = str != null ? parseNumericFontWeight(str) : -1;
        if (parseNumericFontWeight >= 500 || TtmlNode.BOLD.equals(str)) {
            i = 1;
        } else if (BaseTemplateProvider.NON_INVITATION_KEY.equals(str) || (parseNumericFontWeight != -1 && parseNumericFontWeight < 500)) {
            i = 0;
        }
        if (i != this.mFontWeight) {
            this.mFontWeight = i;
        }
    }

    public void setIncludeFontPadding(boolean z) {
        this.mIncludeFontPadding = z;
    }

    public void setLayoutDirection(@Nullable String str) {
        if (str != null && !"undefined".equals(str)) {
            if ("rtl".equals(str)) {
                this.mLayoutDirection = 1;
                return;
            } else if ("ltr".equals(str)) {
                this.mLayoutDirection = 0;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid layoutDirection: ", str));
            }
        }
        this.mLayoutDirection = -1;
    }

    public void setLetterSpacing(float f) {
        this.mLetterSpacingInput = f;
    }

    public void setLineHeight(float f) {
        float pixelFromDIP;
        this.mLineHeightInput = f;
        if (f == -1.0f) {
            this.mLineHeight = Float.NaN;
            return;
        }
        if (this.mAllowFontScaling) {
            pixelFromDIP = PixelUtil.toPixelFromSP(f);
        } else {
            pixelFromDIP = PixelUtil.toPixelFromDIP(f);
        }
        this.mLineHeight = pixelFromDIP;
    }

    public void setNumberOfLines(int i) {
        if (i == 0) {
            i = -1;
        }
        this.mNumberOfLines = i;
    }

    public void setTextDecorationLine(@Nullable String str) {
        String[] split;
        this.mIsUnderlineTextDecorationSet = false;
        this.mIsLineThroughTextDecorationSet = false;
        if (str != null) {
            for (String str2 : str.split(ProcessIdUtil.DEFAULT_PROCESSID)) {
                if (TtmlNode.UNDERLINE.equals(str2)) {
                    this.mIsUnderlineTextDecorationSet = true;
                } else if ("strikethrough".equals(str2)) {
                    this.mIsLineThroughTextDecorationSet = true;
                }
            }
        }
    }

    public void setTextShadowColor(int i) {
        if (i != this.mTextShadowColor) {
            this.mTextShadowColor = i;
        }
    }

    public void setTextShadowOffset(ReadableMap readableMap) {
        this.mTextShadowOffsetDx = 0.0f;
        this.mTextShadowOffsetDy = 0.0f;
        if (readableMap != null) {
            if (readableMap.hasKey("width") && !readableMap.isNull("width")) {
                this.mTextShadowOffsetDx = PixelUtil.toPixelFromDIP(readableMap.getDouble("width"));
            }
            if (!readableMap.hasKey("height") || readableMap.isNull("height")) {
                return;
            }
            this.mTextShadowOffsetDy = PixelUtil.toPixelFromDIP(readableMap.getDouble("height"));
        }
    }

    public void setTextShadowRadius(float f) {
        if (f != this.mTextShadowRadius) {
            this.mTextShadowRadius = f;
        }
    }

    public void setTextTransform(@Nullable String str) {
        if (str != null && !"none".equals(str)) {
            if ("uppercase".equals(str)) {
                this.mTextTransform = TextTransform.UPPERCASE;
                return;
            } else if ("lowercase".equals(str)) {
                this.mTextTransform = TextTransform.LOWERCASE;
                return;
            } else if ("capitalize".equals(str)) {
                this.mTextTransform = TextTransform.CAPITALIZE;
                return;
            } else {
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("Invalid textTransform: ", str));
            }
        }
        this.mTextTransform = TextTransform.NONE;
    }
}
