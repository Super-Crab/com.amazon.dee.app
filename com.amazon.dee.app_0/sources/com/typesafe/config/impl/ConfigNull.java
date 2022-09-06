package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public final class ConfigNull extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ConfigNull(ConfigOrigin configOrigin) {
        super(configOrigin);
    }

    private Object writeReplace() throws ObjectStreamException {
        return new SerializedConfigValue(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        sb.append("null");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        return "null";
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped */
    public Object mo10253unwrapped() {
        return null;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.NULL;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    /* renamed from: newCopy  reason: collision with other method in class */
    public ConfigNull mo10238newCopy(ConfigOrigin configOrigin) {
        return new ConfigNull(configOrigin);
    }
}
