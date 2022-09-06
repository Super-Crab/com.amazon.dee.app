package com.amazon.alexa.client.core.configuration;

import android.content.Context;
import android.text.TextUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
@Singleton
/* loaded from: classes6.dex */
public class ResourcesConfigurationLoader extends ConfigurationLoader {
    private static final String KEY_PREFIX = "a4a_sdk_";
    private final Context context;

    @Inject
    public ResourcesConfigurationLoader(Context context) {
        this.context = context;
    }

    private int getResourceIdentifier(String str, String str2) {
        return this.context.getResources().getIdentifier(getResourceKey(str), str2, this.context.getApplicationContext().getPackageName());
    }

    private String getResourceKey(String str) {
        return GeneratedOutlineSupport1.outline72(KEY_PREFIX, str);
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Boolean getBoolean(String str) {
        int resourceIdentifier = getResourceIdentifier(str, "bool");
        if (resourceIdentifier == 0) {
            return null;
        }
        return Boolean.valueOf(this.context.getResources().getBoolean(resourceIdentifier));
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Long getLong(String str) {
        int resourceIdentifier = getResourceIdentifier(str, "integer");
        if (resourceIdentifier == 0) {
            return null;
        }
        return Long.valueOf(this.context.getResources().getInteger(resourceIdentifier));
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected String getString(String str) {
        String string;
        int resourceIdentifier = getResourceIdentifier(str, "string");
        if (resourceIdentifier == 0 || (string = this.context.getResources().getString(resourceIdentifier)) == null || TextUtils.isEmpty(string)) {
            return null;
        }
        return string;
    }

    @Override // com.amazon.alexa.client.core.configuration.ConfigurationLoader
    protected Set<String> getStringSet(String str) {
        int resourceIdentifier = getResourceIdentifier(str, "array");
        if (resourceIdentifier == 0) {
            return null;
        }
        return new HashSet(Arrays.asList(this.context.getResources().getStringArray(resourceIdentifier)));
    }
}
