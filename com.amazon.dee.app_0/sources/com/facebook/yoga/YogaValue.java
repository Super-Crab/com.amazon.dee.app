package com.facebook.yoga;
/* loaded from: classes2.dex */
public class YogaValue {
    public final YogaUnit unit;
    public final float value;
    static final YogaValue UNDEFINED = new YogaValue(Float.NaN, YogaUnit.UNDEFINED);
    static final YogaValue ZERO = new YogaValue(0.0f, YogaUnit.POINT);
    static final YogaValue AUTO = new YogaValue(Float.NaN, YogaUnit.AUTO);

    /* renamed from: com.facebook.yoga.YogaValue$1  reason: invalid class name */
    /* loaded from: classes2.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$yoga$YogaUnit = new int[YogaUnit.values().length];

        static {
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.UNDEFINED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.POINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.PERCENT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$yoga$YogaUnit[YogaUnit.AUTO.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public YogaValue(float f, YogaUnit yogaUnit) {
        this.value = f;
        this.unit = yogaUnit;
    }

    public static YogaValue parse(String str) {
        if (str == null) {
            return null;
        }
        if ("undefined".equals(str)) {
            return UNDEFINED;
        }
        if ("auto".equals(str)) {
            return AUTO;
        }
        if (str.endsWith("%")) {
            return new YogaValue(Float.parseFloat(str.substring(0, str.length() - 1)), YogaUnit.PERCENT);
        }
        return new YogaValue(Float.parseFloat(str), YogaUnit.POINT);
    }

    public boolean equals(Object obj) {
        if (obj instanceof YogaValue) {
            YogaValue yogaValue = (YogaValue) obj;
            YogaUnit yogaUnit = this.unit;
            if (yogaUnit != yogaValue.unit) {
                return false;
            }
            return yogaUnit == YogaUnit.UNDEFINED || yogaUnit == YogaUnit.AUTO || Float.compare(this.value, yogaValue.value) == 0;
        }
        return false;
    }

    public int hashCode() {
        return this.unit.intValue() + Float.floatToIntBits(this.value);
    }

    public String toString() {
        int ordinal = this.unit.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return Float.toString(this.value);
            }
            if (ordinal != 2) {
                if (ordinal != 3) {
                    throw new IllegalStateException();
                }
                return "auto";
            }
            return this.value + "%";
        }
        return "undefined";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public YogaValue(float f, int i) {
        this(f, YogaUnit.fromInt(i));
    }
}
