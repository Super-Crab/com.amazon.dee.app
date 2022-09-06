package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.devsupport.StackTraceHelper;
import com.facebook.react.modules.i18nmanager.I18nUtil;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaDisplay;
import com.facebook.yoga.YogaFlexDirection;
import com.facebook.yoga.YogaJustify;
import com.facebook.yoga.YogaOverflow;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaUnit;
import com.facebook.yoga.YogaWrap;
/* loaded from: classes2.dex */
public class LayoutShadowNode extends ReactShadowNodeImpl {
    boolean mCollapsable;
    private final MutableYogaValue mTempYogaValue = new MutableYogaValue((AnonymousClass1) null);

    /* renamed from: com.facebook.react.uimanager.LayoutShadowNode$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit = new int[YogaUnit.values().length];

        static {
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.POINT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.UNDEFINED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class MutableYogaValue {
        YogaUnit unit;
        float value;

        /* synthetic */ MutableYogaValue(AnonymousClass1 anonymousClass1) {
            this();
        }

        void setFromDynamic(Dynamic dynamic) {
            if (dynamic.isNull()) {
                this.unit = YogaUnit.UNDEFINED;
                this.value = Float.NaN;
            } else if (dynamic.getType() == ReadableType.String) {
                String asString = dynamic.asString();
                if (asString.equals("auto")) {
                    this.unit = YogaUnit.AUTO;
                    this.value = Float.NaN;
                } else if (asString.endsWith("%")) {
                    this.unit = YogaUnit.PERCENT;
                    this.value = Float.parseFloat(asString.substring(0, asString.length() - 1));
                } else {
                    throw new IllegalArgumentException(GeneratedOutlineSupport1.outline72("Unknown value: ", asString));
                }
            } else {
                this.unit = YogaUnit.POINT;
                this.value = PixelUtil.toPixelFromDIP(dynamic.asDouble());
            }
        }

        private MutableYogaValue() {
        }

        private MutableYogaValue(MutableYogaValue mutableYogaValue) {
            this.value = mutableYogaValue.value;
            this.unit = mutableYogaValue.unit;
        }
    }

    private int maybeTransformLeftRightToStartEnd(int i) {
        if (!I18nUtil.getInstance().doLeftAndRightSwapInRTL(getThemedContext())) {
            return i;
        }
        if (i == 0) {
            return 4;
        }
        if (i == 2) {
            return 5;
        }
        return i;
    }

    @ReactProp(name = ViewProps.ALIGN_CONTENT)
    public void setAlignContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignContent(YogaAlign.FLEX_START);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals("stretch")) {
                    c = 4;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals("baseline")) {
                    c = 5;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals("center")) {
                    c = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c = 1;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c = 0;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c = 6;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c = 3;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                setAlignContent(YogaAlign.AUTO);
                return;
            case 1:
                setAlignContent(YogaAlign.FLEX_START);
                return;
            case 2:
                setAlignContent(YogaAlign.CENTER);
                return;
            case 3:
                setAlignContent(YogaAlign.FLEX_END);
                return;
            case 4:
                setAlignContent(YogaAlign.STRETCH);
                return;
            case 5:
                setAlignContent(YogaAlign.BASELINE);
                return;
            case 6:
                setAlignContent(YogaAlign.SPACE_BETWEEN);
                return;
            case 7:
                setAlignContent(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for alignContent: ", str));
        }
    }

    @ReactProp(name = ViewProps.ALIGN_ITEMS)
    public void setAlignItems(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignItems(YogaAlign.STRETCH);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals("stretch")) {
                    c = 4;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals("baseline")) {
                    c = 5;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals("center")) {
                    c = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c = 1;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c = 0;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c = 6;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c = 3;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                setAlignItems(YogaAlign.AUTO);
                return;
            case 1:
                setAlignItems(YogaAlign.FLEX_START);
                return;
            case 2:
                setAlignItems(YogaAlign.CENTER);
                return;
            case 3:
                setAlignItems(YogaAlign.FLEX_END);
                return;
            case 4:
                setAlignItems(YogaAlign.STRETCH);
                return;
            case 5:
                setAlignItems(YogaAlign.BASELINE);
                return;
            case 6:
                setAlignItems(YogaAlign.SPACE_BETWEEN);
                return;
            case 7:
                setAlignItems(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for alignItems: ", str));
        }
    }

    @ReactProp(name = ViewProps.ALIGN_SELF)
    public void setAlignSelf(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setAlignSelf(YogaAlign.AUTO);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1881872635:
                if (str.equals("stretch")) {
                    c = 4;
                    break;
                }
                break;
            case -1720785339:
                if (str.equals("baseline")) {
                    c = 5;
                    break;
                }
                break;
            case -1364013995:
                if (str.equals("center")) {
                    c = 2;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c = 1;
                    break;
                }
                break;
            case 3005871:
                if (str.equals("auto")) {
                    c = 0;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c = 6;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c = 3;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c = 7;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                setAlignSelf(YogaAlign.AUTO);
                return;
            case 1:
                setAlignSelf(YogaAlign.FLEX_START);
                return;
            case 2:
                setAlignSelf(YogaAlign.CENTER);
                return;
            case 3:
                setAlignSelf(YogaAlign.FLEX_END);
                return;
            case 4:
                setAlignSelf(YogaAlign.STRETCH);
                return;
            case 5:
                setAlignSelf(YogaAlign.BASELINE);
                return;
            case 6:
                setAlignSelf(YogaAlign.SPACE_BETWEEN);
                return;
            case 7:
                setAlignSelf(YogaAlign.SPACE_AROUND);
                return;
            default:
                throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for alignSelf: ", str));
        }
    }

    @ReactProp(defaultFloat = Float.NaN, name = ViewProps.ASPECT_RATIO)
    public void setAspectRatio(float f) {
        setStyleAspectRatio(f);
    }

    @ReactPropGroup(defaultFloat = Float.NaN, names = {ViewProps.BORDER_WIDTH, ViewProps.BORDER_START_WIDTH, ViewProps.BORDER_END_WIDTH, ViewProps.BORDER_TOP_WIDTH, ViewProps.BORDER_BOTTOM_WIDTH, ViewProps.BORDER_LEFT_WIDTH, ViewProps.BORDER_RIGHT_WIDTH})
    public void setBorderWidths(int i, float f) {
        if (isVirtual()) {
            return;
        }
        setBorder(maybeTransformLeftRightToStartEnd(ViewProps.BORDER_SPACING_TYPES[i]), PixelUtil.toPixelFromDIP(f));
    }

    @ReactProp(name = ViewProps.COLLAPSABLE)
    public void setCollapsable(boolean z) {
        this.mCollapsable = z;
    }

    @ReactProp(name = "display")
    public void setDisplay(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setDisplay(YogaDisplay.FLEX);
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != 3145721) {
            if (hashCode == 3387192 && str.equals("none")) {
                c = 1;
            }
        } else if (str.equals(ViewProps.FLEX)) {
            c = 0;
        }
        if (c == 0) {
            setDisplay(YogaDisplay.FLEX);
        } else if (c == 1) {
            setDisplay(YogaDisplay.NONE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for display: ", str));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX)
    public void setFlex(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlex(f);
    }

    @ReactProp(name = ViewProps.FLEX_BASIS)
    public void setFlexBasis(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setFlexBasis(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setFlexBasisPercent(this.mTempYogaValue.value);
        } else if (ordinal == 3) {
            setFlexBasisAuto();
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.FLEX_DIRECTION)
    public void setFlexDirection(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexDirection(YogaFlexDirection.COLUMN);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1448970769:
                if (str.equals("row-reverse")) {
                    c = 3;
                    break;
                }
                break;
            case -1354837162:
                if (str.equals(StackTraceHelper.COLUMN_KEY)) {
                    c = 0;
                    break;
                }
                break;
            case 113114:
                if (str.equals("row")) {
                    c = 2;
                    break;
                }
                break;
            case 1272730475:
                if (str.equals("column-reverse")) {
                    c = 1;
                    break;
                }
                break;
        }
        if (c == 0) {
            setFlexDirection(YogaFlexDirection.COLUMN);
        } else if (c == 1) {
            setFlexDirection(YogaFlexDirection.COLUMN_REVERSE);
        } else if (c == 2) {
            setFlexDirection(YogaFlexDirection.ROW);
        } else if (c == 3) {
            setFlexDirection(YogaFlexDirection.ROW_REVERSE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for flexDirection: ", str));
        }
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX_GROW)
    public void setFlexGrow(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlexGrow(f);
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(defaultFloat = 0.0f, name = ViewProps.FLEX_SHRINK)
    public void setFlexShrink(float f) {
        if (isVirtual()) {
            return;
        }
        super.setFlexShrink(f);
    }

    @ReactProp(name = ViewProps.FLEX_WRAP)
    public void setFlexWrap(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setFlexWrap(YogaWrap.NO_WRAP);
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1039592053) {
            if (hashCode != -749527969) {
                if (hashCode == 3657802 && str.equals("wrap")) {
                    c = 1;
                }
            } else if (str.equals("wrap-reverse")) {
                c = 2;
            }
        } else if (str.equals("nowrap")) {
            c = 0;
        }
        if (c == 0) {
            setFlexWrap(YogaWrap.NO_WRAP);
        } else if (c == 1) {
            setFlexWrap(YogaWrap.WRAP);
        } else if (c == 2) {
            setFlexWrap(YogaWrap.WRAP_REVERSE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for flexWrap: ", str));
        }
    }

    @ReactProp(name = "height")
    public void setHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleHeight(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleHeightPercent(this.mTempYogaValue.value);
        } else if (ordinal == 3) {
            setStyleHeightAuto();
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.JUSTIFY_CONTENT)
    public void setJustifyContent(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setJustifyContent(YogaJustify.FLEX_START);
            return;
        }
        char c = 65535;
        switch (str.hashCode()) {
            case -1364013995:
                if (str.equals("center")) {
                    c = 1;
                    break;
                }
                break;
            case -46581362:
                if (str.equals("flex-start")) {
                    c = 0;
                    break;
                }
                break;
            case 441309761:
                if (str.equals("space-between")) {
                    c = 3;
                    break;
                }
                break;
            case 1742952711:
                if (str.equals("flex-end")) {
                    c = 2;
                    break;
                }
                break;
            case 1937124468:
                if (str.equals("space-around")) {
                    c = 4;
                    break;
                }
                break;
            case 2055030478:
                if (str.equals("space-evenly")) {
                    c = 5;
                    break;
                }
                break;
        }
        if (c == 0) {
            setJustifyContent(YogaJustify.FLEX_START);
        } else if (c == 1) {
            setJustifyContent(YogaJustify.CENTER);
        } else if (c == 2) {
            setJustifyContent(YogaJustify.FLEX_END);
        } else if (c == 3) {
            setJustifyContent(YogaJustify.SPACE_BETWEEN);
        } else if (c == 4) {
            setJustifyContent(YogaJustify.SPACE_AROUND);
        } else if (c == 5) {
            setJustifyContent(YogaJustify.SPACE_EVENLY);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for justifyContent: ", str));
        }
    }

    @ReactPropGroup(names = {ViewProps.MARGIN, ViewProps.MARGIN_VERTICAL, ViewProps.MARGIN_HORIZONTAL, ViewProps.MARGIN_START, ViewProps.MARGIN_END, ViewProps.MARGIN_TOP, ViewProps.MARGIN_BOTTOM, ViewProps.MARGIN_LEFT, ViewProps.MARGIN_RIGHT})
    public void setMargins(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setMargin(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setMarginPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (ordinal == 3) {
            setMarginAuto(maybeTransformLeftRightToStartEnd);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "maxHeight")
    public void setMaxHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleMaxHeight(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleMaxHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = "maxWidth")
    public void setMaxWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleMaxWidth(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleMaxWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.MIN_HEIGHT)
    public void setMinHeight(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleMinHeight(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleMinHeightPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.MIN_WIDTH)
    public void setMinWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleMinWidth(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleMinWidthPercent(this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.OVERFLOW)
    public void setOverflow(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setOverflow(YogaOverflow.VISIBLE);
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1217487446) {
            if (hashCode != -907680051) {
                if (hashCode == 466743410 && str.equals("visible")) {
                    c = 0;
                }
            } else if (str.equals(ViewProps.SCROLL)) {
                c = 2;
            }
        } else if (str.equals("hidden")) {
            c = 1;
        }
        if (c == 0) {
            setOverflow(YogaOverflow.VISIBLE);
        } else if (c == 1) {
            setOverflow(YogaOverflow.HIDDEN);
        } else if (c == 2) {
            setOverflow(YogaOverflow.SCROLL);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for overflow: ", str));
        }
    }

    @ReactPropGroup(names = {ViewProps.PADDING, ViewProps.PADDING_VERTICAL, ViewProps.PADDING_HORIZONTAL, ViewProps.PADDING_START, ViewProps.PADDING_END, ViewProps.PADDING_TOP, ViewProps.PADDING_BOTTOM, ViewProps.PADDING_LEFT, ViewProps.PADDING_RIGHT})
    public void setPaddings(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(ViewProps.PADDING_MARGIN_SPACING_TYPES[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setPadding(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setPaddingPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @ReactProp(name = ViewProps.POSITION)
    public void setPosition(@Nullable String str) {
        if (isVirtual()) {
            return;
        }
        if (str == null) {
            setPositionType(YogaPositionType.RELATIVE);
            return;
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -892481938) {
            if (hashCode != -554435892) {
                if (hashCode == 1728122231 && str.equals("absolute")) {
                    c = 2;
                }
            } else if (str.equals("relative")) {
                c = 1;
            }
        } else if (str.equals("static")) {
            c = 0;
        }
        if (c == 0) {
            setPositionType(YogaPositionType.STATIC);
        } else if (c == 1) {
            setPositionType(YogaPositionType.RELATIVE);
        } else if (c == 2) {
            setPositionType(YogaPositionType.ABSOLUTE);
        } else {
            throw new JSApplicationIllegalArgumentException(GeneratedOutlineSupport1.outline72("invalid value for position: ", str));
        }
    }

    @ReactPropGroup(names = {"start", "end", "left", "right", ViewProps.TOP, ViewProps.BOTTOM})
    public void setPositionValues(int i, Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        int maybeTransformLeftRightToStartEnd = maybeTransformLeftRightToStartEnd(new int[]{4, 5, 0, 2, 1, 3}[i]);
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setPosition(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setPositionPercent(maybeTransformLeftRightToStartEnd, this.mTempYogaValue.value);
        }
        dynamic.recycle();
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    @ReactProp(name = ViewProps.ON_LAYOUT)
    public void setShouldNotifyOnLayout(boolean z) {
        super.setShouldNotifyOnLayout(z);
    }

    @ReactProp(name = "width")
    public void setWidth(Dynamic dynamic) {
        if (isVirtual()) {
            return;
        }
        this.mTempYogaValue.setFromDynamic(dynamic);
        int ordinal = this.mTempYogaValue.unit.ordinal();
        if (ordinal == 0 || ordinal == 1) {
            setStyleWidth(this.mTempYogaValue.value);
        } else if (ordinal == 2) {
            setStyleWidthPercent(this.mTempYogaValue.value);
        } else if (ordinal == 3) {
            setStyleWidthAuto();
        }
        dynamic.recycle();
    }
}
