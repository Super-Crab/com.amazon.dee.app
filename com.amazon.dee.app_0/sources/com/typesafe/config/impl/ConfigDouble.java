package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigDouble extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final double value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigDouble(ConfigOrigin configOrigin, double d, String str) {
        super(configOrigin, str);
        this.value = d;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    @Override // com.typesafe.config.impl.ConfigNumber
    protected double doubleValue() {
        return this.value;
    }

    @Override // com.typesafe.config.impl.ConfigNumber
    protected long longValue() {
        return (long) this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.ConfigNumber, com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        String transformToString = super.transformToString();
        return transformToString == null ? Double.toString(this.value) : transformToString;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigDouble mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigDouble(configOrigin, this.value, this.originalText);
    }

    @Override // com.typesafe.config.impl.ConfigNumber, com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Double mo10253unwrapped() {
        return Double.valueOf(this.value);
    }
}
