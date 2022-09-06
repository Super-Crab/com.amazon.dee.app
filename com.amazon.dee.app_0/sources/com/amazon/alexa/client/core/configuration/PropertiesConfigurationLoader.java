package com.amazon.alexa.client.core.configuration;

import android.text.TextUtils;
import dagger.Lazy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class PropertiesConfigurationLoader extends ConfigurationLoader {
    private final Lazy<Properties> properties;

    @Inject
    public PropertiesConfigurationLoader(Lazy<Properties> lazy) {
        this.properties = lazy;
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Boolean getBoolean(String str) {
        String string = getString(str);
        if (string == null) {
            return null;
        }
        return Boolean.valueOf(Boolean.parseBoolean(string));
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Long getLong(String str) {
        String string = getString(str);
        if (string == null) {
            return null;
        }
        return Long.valueOf(Long.parseLong(string));
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected String getString(String str) {
        String property = this.properties.mo358get().getProperty(str);
        if (property == null || TextUtils.isEmpty(property)) {
            return null;
        }
        return property;
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Set<String> getStringSet(String str) {
        String string = getString(str);
        if (string == null) {
            return null;
        }
        return new HashSet(Arrays.asList(string.trim().split("\\s*,\\s*")));
    }
}
