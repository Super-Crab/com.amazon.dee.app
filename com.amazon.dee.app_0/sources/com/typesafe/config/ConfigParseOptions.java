package com.typesafe.config;
/* loaded from: classes3.dex */
public final class ConfigParseOptions {
    final boolean allowMissing;
    final ClassLoader classLoader;
    final ConfigIncluder includer;
    final String originDescription;
    final ConfigSyntax syntax;

    private ConfigParseOptions(ConfigSyntax configSyntax, String str, boolean z, ConfigIncluder configIncluder, ClassLoader classLoader) {
        this.syntax = configSyntax;
        this.originDescription = str;
        this.allowMissing = z;
        this.includer = configIncluder;
        this.classLoader = classLoader;
    }

    public static ConfigParseOptions defaults() {
        return new ConfigParseOptions(null, null, true, null, null);
    }

    public ConfigParseOptions appendIncluder(ConfigIncluder configIncluder) {
        if (configIncluder != null) {
            ConfigIncluder configIncluder2 = this.includer;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return setIncluder(configIncluder2.withFallback(configIncluder));
            }
            return setIncluder(configIncluder);
        }
        throw new NullPointerException("null includer passed to appendIncluder");
    }

    public boolean getAllowMissing() {
        return this.allowMissing;
    }

    public ClassLoader getClassLoader() {
        ClassLoader classLoader = this.classLoader;
        return classLoader == null ? Thread.currentThread().getContextClassLoader() : classLoader;
    }

    public ConfigIncluder getIncluder() {
        return this.includer;
    }

    public String getOriginDescription() {
        return this.originDescription;
    }

    public ConfigSyntax getSyntax() {
        return this.syntax;
    }

    public ConfigParseOptions prependIncluder(ConfigIncluder configIncluder) {
        if (configIncluder != null) {
            ConfigIncluder configIncluder2 = this.includer;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return setIncluder(configIncluder.withFallback(configIncluder2));
            }
            return setIncluder(configIncluder);
        }
        throw new NullPointerException("null includer passed to prependIncluder");
    }

    public ConfigParseOptions setAllowMissing(boolean z) {
        return this.allowMissing == z ? this : new ConfigParseOptions(this.syntax, this.originDescription, z, this.includer, this.classLoader);
    }

    public ConfigParseOptions setClassLoader(ClassLoader classLoader) {
        return this.classLoader == classLoader ? this : new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, this.includer, classLoader);
    }

    public ConfigParseOptions setIncluder(ConfigIncluder configIncluder) {
        return this.includer == configIncluder ? this : new ConfigParseOptions(this.syntax, this.originDescription, this.allowMissing, configIncluder, this.classLoader);
    }

    public ConfigParseOptions setOriginDescription(String str) {
        String str2 = this.originDescription;
        return str2 == str ? this : (str2 == null || str == null || !str2.equals(str)) ? new ConfigParseOptions(this.syntax, str, this.allowMissing, this.includer, this.classLoader) : this;
    }

    public ConfigParseOptions setSyntax(ConfigSyntax configSyntax) {
        return this.syntax == configSyntax ? this : new ConfigParseOptions(configSyntax, this.originDescription, this.allowMissing, this.includer, this.classLoader);
    }

    ConfigParseOptions withFallbackOriginDescription(String str) {
        return this.originDescription == null ? setOriginDescription(str) : this;
    }
}
