package com.typesafe.config.impl;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigIncluder;
import com.typesafe.config.ConfigMemorySize;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigOrigin;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import com.typesafe.config.ConfigValue;
import com.typesafe.config.impl.ConfigString;
import com.typesafe.config.impl.SimpleIncluder;
import java.io.File;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Callable;
/* loaded from: classes3.dex */
public class ConfigImpl {
    private static final ConfigOrigin defaultValueOrigin = SimpleConfigOrigin.newSimple("hardcoded value");
    private static final ConfigBoolean defaultTrueValue = new ConfigBoolean(defaultValueOrigin, true);
    private static final ConfigBoolean defaultFalseValue = new ConfigBoolean(defaultValueOrigin, false);
    private static final ConfigNull defaultNullValue = new ConfigNull(defaultValueOrigin);
    private static final SimpleConfigList defaultEmptyList = new SimpleConfigList(defaultValueOrigin, Collections.emptyList());
    private static final SimpleConfigObject defaultEmptyObject = SimpleConfigObject.empty(defaultValueOrigin);

    /* loaded from: classes3.dex */
    static class ClasspathNameSource implements SimpleIncluder.NameSource {
        ClasspathNameSource() {
        }

        @Override // com.typesafe.config.impl.SimpleIncluder.NameSource
        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newResources(str, configParseOptions);
        }
    }

    /* loaded from: classes3.dex */
    static class ClasspathNameSourceWithClass implements SimpleIncluder.NameSource {
        private final Class<?> klass;

        public ClasspathNameSourceWithClass(Class<?> cls) {
            this.klass = cls;
        }

        @Override // com.typesafe.config.impl.SimpleIncluder.NameSource
        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newResources(this.klass, str, configParseOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DebugHolder {
        private static final Map<String, Boolean> diagnostics = loadDiagnostics();
        private static String LOADS = "loads";
        private static final boolean traceLoadsEnabled = diagnostics.get(LOADS).booleanValue();
        private static String SUBSTITUTIONS = "substitutions";
        private static final boolean traceSubstitutionsEnabled = diagnostics.get(SUBSTITUTIONS).booleanValue();

        private DebugHolder() {
        }

        private static Map<String, Boolean> loadDiagnostics() {
            String[] split;
            HashMap hashMap = new HashMap();
            hashMap.put(LOADS, false);
            hashMap.put(SUBSTITUTIONS, false);
            String property = System.getProperty("config.trace");
            if (property == null) {
                return hashMap;
            }
            for (String str : property.split(",")) {
                if (str.equals(LOADS)) {
                    hashMap.put(LOADS, true);
                } else if (str.equals(SUBSTITUTIONS)) {
                    hashMap.put(SUBSTITUTIONS, true);
                } else {
                    System.err.println("config.trace property contains unknown trace topic '" + str + "'");
                }
            }
            return hashMap;
        }

        static boolean traceLoadsEnabled() {
            return traceLoadsEnabled;
        }

        static boolean traceSubstitutionsEnabled() {
            return traceSubstitutionsEnabled;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class DefaultIncluderHolder {
        static final ConfigIncluder defaultIncluder = new SimpleIncluder(null);

        private DefaultIncluderHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class EnvVariablesHolder {
        static volatile AbstractConfigObject envVariables = ConfigImpl.loadEnvVariables();

        private EnvVariablesHolder() {
        }
    }

    /* loaded from: classes3.dex */
    static class FileNameSource implements SimpleIncluder.NameSource {
        FileNameSource() {
        }

        @Override // com.typesafe.config.impl.SimpleIncluder.NameSource
        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            return Parseable.newFile(new File(str), configParseOptions);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class LoaderCache {
        private Config currentSystemProperties = null;
        private WeakReference<ClassLoader> currentLoader = new WeakReference<>(null);
        private Map<String, Config> cache = new HashMap();

        LoaderCache() {
        }

        synchronized Config getOrElseUpdate(ClassLoader classLoader, String str, Callable<Config> callable) {
            Config config;
            if (classLoader != this.currentLoader.get()) {
                this.cache.clear();
                this.currentLoader = new WeakReference<>(classLoader);
            }
            Config systemPropertiesAsConfig = ConfigImpl.systemPropertiesAsConfig();
            if (systemPropertiesAsConfig != this.currentSystemProperties) {
                this.cache.clear();
                this.currentSystemProperties = systemPropertiesAsConfig;
            }
            config = this.cache.get(str);
            if (config == null) {
                try {
                    try {
                        config = callable.call();
                        if (config != null) {
                            this.cache.put(str, config);
                        } else {
                            throw new ConfigException.BugOrBroken("null config from cache updater");
                        }
                    } catch (RuntimeException e) {
                        throw e;
                    }
                } catch (Exception e2) {
                    throw new ConfigException.Generic(e2.getMessage(), e2);
                }
            }
            return config;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class LoaderCacheHolder {
        static final LoaderCache cache = new LoaderCache();

        private LoaderCacheHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class SystemPropertiesHolder {
        static volatile AbstractConfigObject systemProperties = ConfigImpl.loadSystemProperties();

        private SystemPropertiesHolder() {
        }
    }

    public static Config computeCachedConfig(ClassLoader classLoader, String str, Callable<Config> callable) {
        try {
            return LoaderCacheHolder.cache.getOrElseUpdate(classLoader, str, callable);
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigIncluder defaultIncluder() {
        try {
            return DefaultIncluderHolder.defaultIncluder;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static Config defaultReference(final ClassLoader classLoader) {
        return computeCachedConfig(classLoader, "defaultReference", new Callable<Config>() { // from class: com.typesafe.config.impl.ConfigImpl.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            /* renamed from: call */
            public Config mo10205call() {
                return ConfigImpl.systemPropertiesAsConfig().mo10234withFallback((ConfigMergeable) Parseable.newResources("reference.conf", ConfigParseOptions.defaults().setClassLoader(classLoader)).parse().mo10165toConfig()).mo10228resolve();
            }
        });
    }

    static AbstractConfigObject empty(ConfigOrigin configOrigin) {
        return emptyObject(configOrigin);
    }

    public static Config emptyConfig(String str) {
        return emptyObject(str).mo10165toConfig();
    }

    private static SimpleConfigList emptyList(ConfigOrigin configOrigin) {
        if (configOrigin != null && configOrigin != defaultValueOrigin) {
            return new SimpleConfigList(configOrigin, Collections.emptyList());
        }
        return defaultEmptyList;
    }

    static AbstractConfigObject emptyObject(String str) {
        return emptyObject(str != null ? SimpleConfigOrigin.newSimple(str) : null);
    }

    public static Config envVariablesAsConfig() {
        return envVariablesAsConfigObject().mo10165toConfig();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigObject envVariablesAsConfigObject() {
        try {
            return EnvVariablesHolder.envVariables;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static ConfigValue fromAnyRef(Object obj, String str) {
        return fromAnyRef(obj, valueOrigin(str), FromMapMode.KEYS_ARE_KEYS);
    }

    public static ConfigObject fromPathMap(Map<String, ? extends Object> map, String str) {
        return (ConfigObject) fromAnyRef(map, valueOrigin(str), FromMapMode.KEYS_ARE_PATHS);
    }

    private static Properties getSystemProperties() {
        Properties properties = System.getProperties();
        Properties properties2 = new Properties();
        synchronized (properties) {
            properties2.putAll(properties);
        }
        return properties2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigException.NotResolved improveNotResolved(Path path, ConfigException.NotResolved notResolved) {
        String outline91 = GeneratedOutlineSupport1.outline91(new StringBuilder(), path.render(), " has not been resolved, you need to call Config#resolve(), see API docs for Config#resolve()");
        return outline91.equals(notResolved.getMessage()) ? notResolved : new ConfigException.NotResolved(outline91, notResolved);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AbstractConfigObject loadEnvVariables() {
        return PropertiesParser.fromStringMap(newSimpleOrigin("env variables"), System.getenv());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static AbstractConfigObject loadSystemProperties() {
        return (AbstractConfigObject) Parseable.newProperties(getSystemProperties(), ConfigParseOptions.defaults().setOriginDescription("system properties")).parse();
    }

    public static ConfigOrigin newFileOrigin(String str) {
        return SimpleConfigOrigin.newFile(str);
    }

    public static ConfigOrigin newSimpleOrigin(String str) {
        if (str == null) {
            return defaultValueOrigin;
        }
        return SimpleConfigOrigin.newSimple(str);
    }

    public static ConfigOrigin newURLOrigin(URL url) {
        return SimpleConfigOrigin.newURL(url);
    }

    public static ConfigObject parseFileAnySyntax(File file, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new FileNameSource(), file.getPath(), configParseOptions);
    }

    public static ConfigObject parseResourcesAnySyntax(Class<?> cls, String str, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new ClasspathNameSourceWithClass(cls), str, configParseOptions);
    }

    public static void reloadEnvVariablesConfig() {
        EnvVariablesHolder.envVariables = loadEnvVariables();
    }

    public static void reloadSystemPropertiesConfig() {
        SystemPropertiesHolder.systemProperties = loadSystemProperties();
    }

    public static Config systemPropertiesAsConfig() {
        return systemPropertiesAsConfigObject().mo10165toConfig();
    }

    static AbstractConfigObject systemPropertiesAsConfigObject() {
        try {
            return SystemPropertiesHolder.systemProperties;
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static void trace(String str) {
        System.err.println(str);
    }

    public static boolean traceLoadsEnabled() {
        try {
            return DebugHolder.traceLoadsEnabled();
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    public static boolean traceSubstitutionsEnabled() {
        try {
            return DebugHolder.traceSubstitutionsEnabled();
        } catch (ExceptionInInitializerError e) {
            throw ConfigImplUtil.extractInitializerError(e);
        }
    }

    private static ConfigOrigin valueOrigin(String str) {
        if (str == null) {
            return defaultValueOrigin;
        }
        return SimpleConfigOrigin.newSimple(str);
    }

    public static void trace(int i, String str) {
        while (i > 0) {
            System.err.print("  ");
            i--;
        }
        System.err.println(str);
    }

    private static AbstractConfigObject emptyObject(ConfigOrigin configOrigin) {
        if (configOrigin == defaultValueOrigin) {
            return defaultEmptyObject;
        }
        return SimpleConfigObject.empty(configOrigin);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AbstractConfigValue fromAnyRef(Object obj, ConfigOrigin configOrigin, FromMapMode fromMapMode) {
        if (configOrigin != null) {
            if (obj == null) {
                if (configOrigin != defaultValueOrigin) {
                    return new ConfigNull(configOrigin);
                }
                return defaultNullValue;
            } else if (obj instanceof AbstractConfigValue) {
                return (AbstractConfigValue) obj;
            } else {
                if (obj instanceof Boolean) {
                    if (configOrigin != defaultValueOrigin) {
                        return new ConfigBoolean(configOrigin, ((Boolean) obj).booleanValue());
                    }
                    if (((Boolean) obj).booleanValue()) {
                        return defaultTrueValue;
                    }
                    return defaultFalseValue;
                } else if (obj instanceof String) {
                    return new ConfigString.Quoted(configOrigin, (String) obj);
                } else {
                    if (obj instanceof Number) {
                        if (obj instanceof Double) {
                            return new ConfigDouble(configOrigin, ((Double) obj).doubleValue(), null);
                        }
                        if (obj instanceof Integer) {
                            return new ConfigInt(configOrigin, ((Integer) obj).intValue(), null);
                        }
                        if (obj instanceof Long) {
                            return new ConfigLong(configOrigin, ((Long) obj).longValue(), null);
                        }
                        return ConfigNumber.newNumber(configOrigin, ((Number) obj).doubleValue(), (String) null);
                    } else if (obj instanceof Duration) {
                        return new ConfigLong(configOrigin, ((Duration) obj).toMillis(), null);
                    } else {
                        if (obj instanceof Map) {
                            Map map = (Map) obj;
                            if (map.isEmpty()) {
                                return emptyObject(configOrigin);
                            }
                            if (fromMapMode == FromMapMode.KEYS_ARE_KEYS) {
                                HashMap hashMap = new HashMap();
                                for (Map.Entry entry : map.entrySet()) {
                                    Object key = entry.getKey();
                                    if (key instanceof String) {
                                        hashMap.put((String) key, fromAnyRef(entry.getValue(), configOrigin, fromMapMode));
                                    } else {
                                        throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline70("bug in method caller: not valid to create ConfigObject from map with non-String key: ", key));
                                    }
                                }
                                return new SimpleConfigObject(configOrigin, hashMap);
                            }
                            return PropertiesParser.fromPathMap(configOrigin, map);
                        } else if (obj instanceof Iterable) {
                            Iterator it2 = ((Iterable) obj).iterator();
                            if (!it2.hasNext()) {
                                return emptyList(configOrigin);
                            }
                            ArrayList arrayList = new ArrayList();
                            while (it2.hasNext()) {
                                arrayList.add(fromAnyRef(it2.next(), configOrigin, fromMapMode));
                            }
                            return new SimpleConfigList(configOrigin, arrayList);
                        } else if (obj instanceof ConfigMemorySize) {
                            return new ConfigLong(configOrigin, ((ConfigMemorySize) obj).toBytes(), null);
                        } else {
                            throw new ConfigException.BugOrBroken(GeneratedOutlineSupport1.outline70("bug in method caller: not valid to create ConfigValue from: ", obj));
                        }
                    }
                }
            }
        }
        throw new ConfigException.BugOrBroken("origin not supposed to be null");
    }

    public static ConfigObject parseResourcesAnySyntax(String str, ConfigParseOptions configParseOptions) {
        return SimpleIncluder.fromBasename(new ClasspathNameSource(), str, configParseOptions);
    }
}
