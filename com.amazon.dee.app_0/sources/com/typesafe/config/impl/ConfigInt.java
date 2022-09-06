package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigInt extends ConfigNumber implements Serializable {
    private static final long serialVersionUID = 2;
    private final int value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigInt(ConfigOrigin configOrigin, int i, String str) {
        super(configOrigin, str);
        this.value = i;
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
        return transformToString == null ? Integer.toString(this.value) : transformToString;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.NUMBER;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigInt mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigInt(configOrigin, this.value, this.originalText);
    }

    @Override // com.typesafe.config.impl.ConfigNumber, com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Integer mo10253unwrapped() {
        return Integer.valueOf(this.value);
    }
}
