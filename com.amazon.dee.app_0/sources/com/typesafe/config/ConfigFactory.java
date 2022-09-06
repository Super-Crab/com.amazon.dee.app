package com.typesafe.config;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import com.typesafe.config.impl.ConfigImpl;
import com.typesafe.config.impl.Parseable;
import java.io.File;
import java.io.Reader;
import java.net.URL;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
/* loaded from: classes3.dex */
public final class ConfigFactory {
    private static final String STRATEGY_PROPERTY_NAME = "config.strategy";

    private ConfigFactory() {
    }

    private static ClassLoader checkedContextClassLoader(String str) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (contextClassLoader != null) {
            return contextClassLoader;
        }
        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline72("Context class loader is not set for the current thread; if Thread.currentThread().getContextClassLoader() returns null, you must pass a ClassLoader explicitly to ConfigFactory.", str));
    }

    public static Config defaultApplication() {
        return defaultApplication(ConfigParseOptions.defaults());
    }

    public static Config defaultOverrides() {
        return systemProperties();
    }

    public static Config defaultReference() {
        return defaultReference(checkedContextClassLoader("defaultReference"));
    }

    public static Config empty() {
        return empty(null);
    }

    private static ConfigParseOptions ensureClassLoader(ConfigParseOptions configParseOptions, String str) {
        return configParseOptions.getClassLoader() == null ? configParseOptions.setClassLoader(checkedContextClassLoader(str)) : configParseOptions;
    }

    private static ConfigLoadingStrategy getConfigLoadingStrategy() {
        String property = System.getProperties().getProperty(STRATEGY_PROPERTY_NAME);
        if (property != null) {
            try {
                return (ConfigLoadingStrategy) ConfigLoadingStrategy.class.cast(Class.forName(property).newInstance());
            } catch (Throwable th) {
                throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline72("Failed to load strategy: ", property), th);
            }
        }
        return new DefaultConfigLoadingStrategy();
    }

    public static void invalidateCaches() {
        ConfigImpl.reloadSystemPropertiesConfig();
        ConfigImpl.reloadEnvVariablesConfig();
    }

    public static Config load(String str) {
        return load(str, ConfigParseOptions.defaults(), ConfigResolveOptions.defaults());
    }

    public static Config parseFile(File file, ConfigParseOptions configParseOptions) {
        return Parseable.newFile(file, configParseOptions).parse().mo10165toConfig();
    }

    public static Config parseFileAnySyntax(File file, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseFileAnySyntax(file, configParseOptions).mo10165toConfig();
    }

    public static Config parseMap(Map<String, ? extends Object> map, String str) {
        return ConfigImpl.fromPathMap(map, str).mo10165toConfig();
    }

    public static Config parseProperties(Properties properties, ConfigParseOptions configParseOptions) {
        return Parseable.newProperties(properties, configParseOptions).parse().mo10165toConfig();
    }

    public static Config parseReader(Reader reader, ConfigParseOptions configParseOptions) {
        return Parseable.newReader(reader, configParseOptions).parse().mo10165toConfig();
    }

    public static Config parseResources(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return Parseable.newResources(cls, str, configParseOptions).parse().mo10165toConfig();
    }

    public static Config parseResourcesAnySyntax(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(cls, str, configParseOptions).mo10165toConfig();
    }

    public static Config parseString(String str, ConfigParseOptions configParseOptions) {
        return Parseable.newString(str, configParseOptions).parse().mo10165toConfig();
    }

    public static Config parseURL(URL url, ConfigParseOptions configParseOptions) {
        return Parseable.newURL(url, configParseOptions).parse().mo10165toConfig();
    }

    public static Config systemEnvironment() {
        return ConfigImpl.envVariablesAsConfig();
    }

    public static Config systemProperties() {
        return ConfigImpl.systemPropertiesAsConfig();
    }

    public static Config defaultApplication(ClassLoader classLoader) {
        return defaultApplication(ConfigParseOptions.defaults().setClassLoader(classLoader));
    }

    public static Config defaultOverrides(ClassLoader classLoader) {
        return systemProperties();
    }

    public static Config defaultReference(ClassLoader classLoader) {
        return ConfigImpl.defaultReference(classLoader);
    }

    public static Config empty(String str) {
        return ConfigImpl.emptyConfig(str);
    }

    public static Config parseFile(File file) {
        return parseFile(file, ConfigParseOptions.defaults());
    }

    public static Config parseFileAnySyntax(File file) {
        return parseFileAnySyntax(file, ConfigParseOptions.defaults());
    }

    public static Config parseMap(Map<String, ? extends Object> map) {
        return parseMap(map, null);
    }

    public static Config parseProperties(Properties properties) {
        return parseProperties(properties, ConfigParseOptions.defaults());
    }

    public static Config parseReader(Reader reader) {
        return parseReader(reader, ConfigParseOptions.defaults());
    }

    public static Config parseString(String str) {
        return parseString(str, ConfigParseOptions.defaults());
    }

    public static Config parseURL(URL url) {
        return parseURL(url, ConfigParseOptions.defaults());
    }

    public static Config defaultApplication(ConfigParseOptions configParseOptions) {
        return getConfigLoadingStrategy().parseApplicationConfig(ensureClassLoader(configParseOptions, "defaultApplication"));
    }

    public static Config parseResources(Class<?> cls, String str) {
        return parseResources(cls, str, ConfigParseOptions.defaults());
    }

    public static Config parseResourcesAnySyntax(Class<?> cls, String str) {
        return parseResourcesAnySyntax(cls, str, ConfigParseOptions.defaults());
    }

    public static Config load(ClassLoader classLoader, String str) {
        return load(str, ConfigParseOptions.defaults().setClassLoader(classLoader), ConfigResolveOptions.defaults());
    }

    public static Config parseResources(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions) {
        return parseResources(str, configParseOptions.setClassLoader(classLoader));
    }

    public static Config parseResourcesAnySyntax(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(str, configParseOptions.setClassLoader(classLoader)).mo10165toConfig();
    }

    public static Config parseResources(ClassLoader classLoader, String str) {
        return parseResources(classLoader, str, ConfigParseOptions.defaults());
    }

    public static Config parseResources(String str, ConfigParseOptions configParseOptions) {
        return Parseable.newResources(str, ensureClassLoader(configParseOptions, "parseResources")).parse().mo10165toConfig();
    }

    public static Config parseResourcesAnySyntax(ClassLoader classLoader, String str) {
        return parseResourcesAnySyntax(classLoader, str, ConfigParseOptions.defaults());
    }

    public static Config load(String str, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        ConfigParseOptions ensureClassLoader = ensureClassLoader(configParseOptions, "load");
        return load(ensureClassLoader.getClassLoader(), parseResourcesAnySyntax(str, ensureClassLoader), configResolveOptions);
    }

    public static Config parseResourcesAnySyntax(String str, ConfigParseOptions configParseOptions) {
        return ConfigImpl.parseResourcesAnySyntax(str, configParseOptions).mo10165toConfig();
    }

    public static Config parseResources(String str) {
        return parseResources(str, ConfigParseOptions.defaults());
    }

    public static Config parseResourcesAnySyntax(String str) {
        return parseResourcesAnySyntax(str, ConfigParseOptions.defaults());
    }

    public static Config load(ClassLoader classLoader, String str, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(str, configParseOptions.setClassLoader(classLoader), configResolveOptions);
    }

    public static Config load(Config config) {
        return load(checkedContextClassLoader("load"), config);
    }

    public static Config load(ClassLoader classLoader, Config config) {
        return load(classLoader, config, ConfigResolveOptions.defaults());
    }

    public static Config load(Config config, ConfigResolveOptions configResolveOptions) {
        return load(checkedContextClassLoader("load"), config, configResolveOptions);
    }

    public static Config load(ClassLoader classLoader, Config config, ConfigResolveOptions configResolveOptions) {
        return defaultOverrides(classLoader).mo10234withFallback((ConfigMergeable) config).mo10234withFallback((ConfigMergeable) defaultReference(classLoader)).mo10229resolve(configResolveOptions);
    }

    public static Config load() {
        return load(checkedContextClassLoader("load"));
    }

    public static Config load(ConfigParseOptions configParseOptions) {
        return load(configParseOptions, ConfigResolveOptions.defaults());
    }

    public static Config load(final ClassLoader classLoader) {
        final ConfigParseOptions classLoader2 = ConfigParseOptions.defaults().setClassLoader(classLoader);
        return ConfigImpl.computeCachedConfig(classLoader, "load", new Callable<Config>() { // from class: com.typesafe.config.ConfigFactory.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Config mo10151call() {
                return ConfigFactory.load(classLoader, ConfigFactory.defaultApplication(classLoader2));
            }
        });
    }

    public static Config load(ClassLoader classLoader, ConfigParseOptions configParseOptions) {
        return load(configParseOptions.setClassLoader(classLoader));
    }

    public static Config load(ClassLoader classLoader, ConfigResolveOptions configResolveOptions) {
        return load(classLoader, ConfigParseOptions.defaults(), configResolveOptions);
    }

    public static Config load(ClassLoader classLoader, ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(classLoader, defaultApplication(ensureClassLoader(configParseOptions, "load")), configResolveOptions);
    }

    public static Config load(ConfigParseOptions configParseOptions, ConfigResolveOptions configResolveOptions) {
        return load(defaultApplication(ensureClassLoader(configParseOptions, "load")), configResolveOptions);
    }
}
