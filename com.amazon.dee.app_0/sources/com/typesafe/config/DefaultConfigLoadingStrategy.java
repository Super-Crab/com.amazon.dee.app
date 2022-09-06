package com.typesafe.config;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.typesafe.config.ConfigException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
/* loaded from: classes3.dex */
public class DefaultConfigLoadingStrategy implements ConfigLoadingStrategy {
    @Override // com.typesafe.config.ConfigLoadingStrategy
    public Config parseApplicationConfig(ConfigParseOptions configParseOptions) {
        ClassLoader classLoader = configParseOptions.getClassLoader();
        if (classLoader != null) {
            String property = System.getProperty("config.resource");
            int i = property != null ? 1 : 0;
            String property2 = System.getProperty("config.file");
            if (property2 != null) {
                i++;
            }
            String property3 = System.getProperty("config.url");
            if (property3 != null) {
                i++;
            }
            if (i == 0) {
                return ConfigFactory.parseResourcesAnySyntax("application", configParseOptions);
            }
            if (i <= 1) {
                ConfigParseOptions allowMissing = configParseOptions.setAllowMissing(false);
                if (property != null) {
                    if (property.startsWith("/")) {
                        property = property.substring(1);
                    }
                    return ConfigFactory.parseResources(classLoader, property, allowMissing);
                } else if (property2 != null) {
                    return ConfigFactory.parseFile(new File(property2), allowMissing);
                } else {
                    try {
                        return ConfigFactory.parseURL(new URL(property3), allowMissing);
                    } catch (MalformedURLException e) {
                        StringBuilder outline115 = GeneratedOutlineSupport1.outline115("Bad URL in config.url system property: '", property3, "': ");
                        outline115.append(e.getMessage());
                        throw new ConfigException.Generic(outline115.toString(), e);
                    }
                }
            }
            throw new ConfigException.Generic(GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline116("You set more than one of config.file='", property2, "', config.url='", property3, "', config.resource='"), property, "'; don't know which one to use!"));
        }
        throw new ConfigException.BugOrBroken("ClassLoader should have been set here; bug in ConfigFactory. (You can probably work around this bug by passing in a class loader or calling currentThread().setContextClassLoader() though.)");
    }
}
