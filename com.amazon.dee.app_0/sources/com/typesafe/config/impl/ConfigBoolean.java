package com.typesafe.config.impl;

import com.amazon.deecomms.calling.phonecallcontroller.PCCConstants;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigBoolean extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    private final boolean value;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigBoolean(ConfigOrigin configOrigin, boolean z) {
        super(configOrigin);
        this.value = z;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        return this.value ? "true" : PCCConstants.PHONE_CALL_CONTROLLER_CALLING_FEATURE_ABSENT_VALUE;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.BOOLEAN;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigBoolean mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigBoolean(configOrigin, this.value);
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Boolean mo10253unwrapped() {
        return Boolean.valueOf(this.value);
    }
}
