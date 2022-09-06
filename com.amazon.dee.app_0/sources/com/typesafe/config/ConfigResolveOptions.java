package com.typesafe.config;

import com.typesafe.config.ConfigException;
/* loaded from: classes3.dex */
public final class ConfigResolveOptions {
    private static final ConfigResolver NULL_RESOLVER = new ConfigResolver() { // from class: com.typesafe.config.ConfigResolveOptions.1
        @Override // com.typesafe.config.ConfigResolver
        public ConfigValue lookup(String str) {
            return null;
        }

        @Override // com.typesafe.config.ConfigResolver
        public ConfigResolver withFallback(ConfigResolver configResolver) {
            return configResolver;
        }
    };
    private final boolean allowUnresolved;
    private final ConfigResolver resolver;
    private final boolean useSystemEnvironment;

    private ConfigResolveOptions(boolean z, boolean z2, ConfigResolver configResolver) {
        this.useSystemEnvironment = z;
        this.allowUnresolved = z2;
        this.resolver = configResolver;
    }

    public static ConfigResolveOptions defaults() {
        return new ConfigResolveOptions(true, false, NULL_RESOLVER);
    }

    public static ConfigResolveOptions noSystem() {
        return defaults().setUseSystemEnvironment(false);
    }

    public ConfigResolveOptions appendResolver(ConfigResolver configResolver) {
        if (configResolver != null) {
            ConfigResolver configResolver2 = this.resolver;
            return configResolver == configResolver2 ? this : new ConfigResolveOptions(this.useSystemEnvironment, this.allowUnresolved, configResolver2.withFallback(configResolver));
        }
        throw new ConfigException.BugOrBroken("null resolver passed to appendResolver");
    }

    public boolean getAllowUnresolved() {
        return this.allowUnresolved;
    }

    public ConfigResolver getResolver() {
        return this.resolver;
    }

    public boolean getUseSystemEnvironment() {
        return this.useSystemEnvironment;
    }

    public ConfigResolveOptions setAllowUnresolved(boolean z) {
        return new ConfigResolveOptions(this.useSystemEnvironment, z, this.resolver);
    }

    public ConfigResolveOptions setUseSystemEnvironment(boolean z) {
        return new ConfigResolveOptions(z, this.allowUnresolved, this.resolver);
    }
}
