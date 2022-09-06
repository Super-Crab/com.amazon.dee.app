package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigLong extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final long value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigLong(ConfigOrigin configOrigin, long j, String str) {
        super(configOrigin, str);
        this.value = j;
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
        return this.value;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.ConfigNumber, com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        String transformToString = super.transformToString();
        return transformToString == null ? Long.toString(this.value) : transformToString;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigLong mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigLong(configOrigin, this.value, this.originalText);
    }

    @Override // com.typesafe.config.impl.ConfigNumber, com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Long mo10253unwrapped() {
        return Long.valueOf(this.value);
    }
}
