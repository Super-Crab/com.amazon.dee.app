package com.typesafe.config.impl;

import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigRenderOptions;
import com.typesafe.config.ConfigValueType;
import java.io.ObjectStreamException;
import java.io.Serializable;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public abstract class ConfigString extends AbstractConfigValue implements Serializable {
    private static final long serialVersionUID = 2;
    protected final String value;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public static final class Quoted extends ConfigString {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Quoted(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str);
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedConfigValue(this);
        }

        @Override // com.typesafe.config.impl.ConfigString, com.typesafe.config.ConfigValue
        /* renamed from: unwrapped */
        public /* bridge */ /* synthetic */ Object mo10253unwrapped() {
            return super.mo10253unwrapped();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.typesafe.config.impl.AbstractConfigValue
        /* renamed from: newCopy  reason: collision with other method in class */
        public Quoted mo10238newCopy(ConfigOrigin configOrigin) {
            return new Quoted(configOrigin, this.value);
        }
    }

    /* loaded from: classes3.dex */
    static final class Unquoted extends ConfigString {
        /* JADX INFO: Access modifiers changed from: package-private */
        public Unquoted(ConfigOrigin configOrigin, String str) {
            super(configOrigin, str);
        }

        private Object writeReplace() throws ObjectStreamException {
            return new SerializedConfigValue(this);
        }

        @Override // com.typesafe.config.impl.ConfigString, com.typesafe.config.ConfigValue
        /* renamed from: unwrapped */
        public /* bridge */ /* synthetic */ Object mo10253unwrapped() {
            return super.mo10253unwrapped();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.typesafe.config.impl.AbstractConfigValue
        /* renamed from: newCopy  reason: collision with other method in class */
        public Unquoted mo10238newCopy(ConfigOrigin configOrigin) {
            return new Unquoted(configOrigin, this.value);
        }
    }

    protected ConfigString(ConfigOrigin configOrigin, String str) {
        super(configOrigin);
        this.value = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public void render(StringBuilder sb, int i, boolean z, ConfigRenderOptions configRenderOptions) {
        String renderStringUnquotedIfPossible;
        if (configRenderOptions.getJson()) {
            renderStringUnquotedIfPossible = ConfigImplUtil.renderJsonString(this.value);
        } else {
            renderStringUnquotedIfPossible = ConfigImplUtil.renderStringUnquotedIfPossible(this.value);
        }
        sb.append(renderStringUnquotedIfPossible);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.typesafe.config.impl.AbstractConfigValue
    public String transformToString() {
        return this.value;
    }

    @Override // com.typesafe.config.ConfigValue
    public ConfigValueType valueType() {
        return ConfigValueType.STRING;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean wasQuoted() {
        return this instanceof Quoted;
    }

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    public String mo10253unwrapped() {
        return this.value;
    }
}
