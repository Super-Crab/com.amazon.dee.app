package com.typesafe.config.impl;

import com.typesafe.config.ConfigException;
import com.typesafe.config.ConfigFactory;
import com.typesafe.config.ConfigIncludeContext;
import com.typesafe.config.ConfigIncluder;
import com.typesafe.config.ConfigIncluderClasspath;
import com.typesafe.config.ConfigIncluderFile;
import com.typesafe.config.ConfigIncluderURL;
import com.typesafe.config.ConfigMergeable;
import com.typesafe.config.ConfigObject;
import com.typesafe.config.ConfigParseOptions;
import com.typesafe.config.ConfigParseable;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public class SimpleIncluder implements FullIncluder {
    private ConfigIncluder fallback;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes3.dex */
    public interface NameSource {
        ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class Proxy implements FullIncluder {
        final ConfigIncluder delegate;

        Proxy(ConfigIncluder configIncluder) {
            this.delegate = configIncluder;
        }

        @Override // com.typesafe.config.ConfigIncluder
        public ConfigObject include(ConfigIncludeContext configIncludeContext, String str) {
            return this.delegate.include(configIncludeContext, str);
        }

        @Override // com.typesafe.config.ConfigIncluderFile
        public ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderFile) {
                return ((ConfigIncluderFile) configIncluder).includeFile(configIncludeContext, file);
            }
            return SimpleIncluder.includeFileWithoutFallback(configIncludeContext, file);
        }

        @Override // com.typesafe.config.ConfigIncluderClasspath
        public ConfigObject includeResources(ConfigIncludeContext configIncludeContext, String str) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderClasspath) {
                return ((ConfigIncluderClasspath) configIncluder).includeResources(configIncludeContext, str);
            }
            return SimpleIncluder.includeResourceWithoutFallback(configIncludeContext, str);
        }

        @Override // com.typesafe.config.ConfigIncluderURL
        public ConfigObject includeURL(ConfigIncludeContext configIncludeContext, URL url) {
            ConfigIncluder configIncluder = this.delegate;
            if (configIncluder instanceof ConfigIncluderURL) {
                return ((ConfigIncluderURL) configIncluder).includeURL(configIncludeContext, url);
            }
            return SimpleIncluder.includeURLWithoutFallback(configIncludeContext, url);
        }

        @Override // com.typesafe.config.ConfigIncluder
        public ConfigIncluder withFallback(ConfigIncluder configIncluder) {
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public static class RelativeNameSource implements NameSource {
        private final ConfigIncludeContext context;

        RelativeNameSource(ConfigIncludeContext configIncludeContext) {
            this.context = configIncludeContext;
        }

        @Override // com.typesafe.config.impl.SimpleIncluder.NameSource
        public ConfigParseable nameToParseable(String str, ConfigParseOptions configParseOptions) {
            ConfigParseable relativeTo = this.context.relativeTo(str);
            if (relativeTo == null) {
                return Parseable.newNotFound(str, "include was not found: '" + str + "'", configParseOptions);
            }
            return relativeTo;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimpleIncluder(ConfigIncluder configIncluder) {
        this.fallback = configIncluder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ConfigParseOptions clearForInclude(ConfigParseOptions configParseOptions) {
        return configParseOptions.setSyntax(null).setOriginDescription(null).setAllowMissing(true);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't wrap try/catch for region: R(14:7|(7:55|56|57|(3:49|50|51)|(3:43|44|45)|18|(1:(1:41)(2:39|40))(4:21|(1:23)|24|(5:26|(2:29|27)|30|31|32)(2:34|35)))|11|(1:13)|49|50|51|(1:16)|43|44|45|18|(0)|(2:37|41)(1:42)) */
    /* JADX WARN: Code restructure failed: missing block: B:23:0x0082, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0083, code lost:
        r2.add(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a4, code lost:
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a5, code lost:
        r2.add(r9);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.typesafe.config.ConfigObject fromBasename(com.typesafe.config.impl.SimpleIncluder.NameSource r9, java.lang.String r10, com.typesafe.config.ConfigParseOptions r11) {
        /*
            Method dump skipped, instructions count: 345
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.typesafe.config.impl.SimpleIncluder.fromBasename(com.typesafe.config.impl.SimpleIncluder$NameSource, java.lang.String, com.typesafe.config.ConfigParseOptions):com.typesafe.config.ConfigObject");
    }

    static ConfigObject includeFileWithoutFallback(ConfigIncludeContext configIncludeContext, File file) {
        return ConfigFactory.parseFileAnySyntax(file, configIncludeContext.parseOptions()).mo10232root();
    }

    static ConfigObject includeResourceWithoutFallback(ConfigIncludeContext configIncludeContext, String str) {
        return ConfigFactory.parseResourcesAnySyntax(str, configIncludeContext.parseOptions()).mo10232root();
    }

    static ConfigObject includeURLWithoutFallback(ConfigIncludeContext configIncludeContext, URL url) {
        return ConfigFactory.parseURL(url, configIncludeContext.parseOptions()).mo10232root();
    }

    static ConfigObject includeWithoutFallback(ConfigIncludeContext configIncludeContext, String str) {
        URL url;
        try {
            url = new URL(str);
        } catch (MalformedURLException unused) {
            url = null;
        }
        if (url != null) {
            return includeURLWithoutFallback(configIncludeContext, url);
        }
        return fromBasename(new RelativeNameSource(configIncludeContext), str, configIncludeContext.parseOptions());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static FullIncluder makeFull(ConfigIncluder configIncluder) {
        if (configIncluder instanceof FullIncluder) {
            return (FullIncluder) configIncluder;
        }
        return new Proxy(configIncluder);
    }

    @Override // com.typesafe.config.ConfigIncluder
    public ConfigObject include(ConfigIncludeContext configIncludeContext, String str) {
        ConfigObject includeWithoutFallback = includeWithoutFallback(configIncludeContext, str);
        ConfigIncluder configIncluder = this.fallback;
        return configIncluder != null ? includeWithoutFallback.mo10234withFallback((ConfigMergeable) configIncluder.include(configIncludeContext, str)) : includeWithoutFallback;
    }

    @Override // com.typesafe.config.ConfigIncluderFile
    public ConfigObject includeFile(ConfigIncludeContext configIncludeContext, File file) {
        ConfigObject includeFileWithoutFallback = includeFileWithoutFallback(configIncludeContext, file);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderFile)) ? includeFileWithoutFallback : includeFileWithoutFallback.mo10234withFallback((ConfigMergeable) ((ConfigIncluderFile) configIncluder).includeFile(configIncludeContext, file));
    }

    @Override // com.typesafe.config.ConfigIncluderClasspath
    public ConfigObject includeResources(ConfigIncludeContext configIncludeContext, String str) {
        ConfigObject includeResourceWithoutFallback = includeResourceWithoutFallback(configIncludeContext, str);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderClasspath)) ? includeResourceWithoutFallback : includeResourceWithoutFallback.mo10234withFallback((ConfigMergeable) ((ConfigIncluderClasspath) configIncluder).includeResources(configIncludeContext, str));
    }

    @Override // com.typesafe.config.ConfigIncluderURL
    public ConfigObject includeURL(ConfigIncludeContext configIncludeContext, URL url) {
        ConfigObject includeURLWithoutFallback = includeURLWithoutFallback(configIncludeContext, url);
        ConfigIncluder configIncluder = this.fallback;
        return (configIncluder == null || !(configIncluder instanceof ConfigIncluderURL)) ? includeURLWithoutFallback : includeURLWithoutFallback.mo10234withFallback((ConfigMergeable) ((ConfigIncluderURL) configIncluder).includeURL(configIncludeContext, url));
    }

    @Override // com.typesafe.config.ConfigIncluder
    public ConfigIncluder withFallback(ConfigIncluder configIncluder) {
        if (this != configIncluder) {
            ConfigIncluder configIncluder2 = this.fallback;
            if (configIncluder2 == configIncluder) {
                return this;
            }
            if (configIncluder2 != null) {
                return new SimpleIncluder(configIncluder2.withFallback(configIncluder));
            }
            return new SimpleIncluder(configIncluder);
        }
        throw new ConfigException.BugOrBroken("trying to create includer cycle");
    }
}
