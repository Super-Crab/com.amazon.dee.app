package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigOrigin;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class ConfigNumber extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    protected final String originalText;

    /* JADX INFO: Access modifiers changed from: protected */
    public ConfigNumber(ConfigOrigin configOrigin, String str) {
        super(configOrigin);
        this.originalText = str;
    }

    private boolean isWhole() {
        return ((double) longValue()) == doubleValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigNumber newNumber(ConfigOrigin configOrigin, long j, String str) {
        if (j <= 2147483647L && j >= -2147483648L) {
            return new ConfigInt(configOrigin, (int) j, str);
        }
        return new ConfigLong(configOrigin, j, str);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean canEqual(Object obj) {
        return obj instanceof ConfigNumber;
    }

    protected abstract double doubleValue();

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public boolean equals(Object obj) {
        if (!(obj instanceof ConfigNumber) || !canEqual(obj)) {
            return false;
        }
        ConfigNumber configNumber = (ConfigNumber) obj;
        return isWhole() ? configNumber.isWhole() && longValue() == configNumber.longValue() : !configNumber.isWhole() && doubleValue() == configNumber.doubleValue();
    }

    @Override // com.typesafe.config.impl.AbstractConfigValue
    public int hashCode() {
        long doubleToLongBits;
        if (isWhole()) {
            doubleToLongBits = longValue();
        } else {
            doubleToLongBits = Double.doubleToLongBits(doubleValue());
        }
        return (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int intValueRangeChecked(String str) {
        long longValue = longValue();
        if (longValue < -2147483648L || longValue > 2147483647L) {
            throw new ConfigException.WrongType(mo10176origin(), str, "32-bit integer", GeneratedOutlineSupport1.outline56("out-of-range value ", longValue));
        }
        return (int) longValue;
    }

    protected abstract long longValue();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        return this.originalText;
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public abstract Number mo10253unwrapped();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigNumber newNumber(ConfigOrigin configOrigin, double d, String str) {
        long j = (long) d;
        if (j == d) {
            return newNumber(configOrigin, j, str);
        }
        return new ConfigDouble(configOrigin, d, str);
    }
}
