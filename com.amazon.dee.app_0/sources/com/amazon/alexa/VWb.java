package com.amazon.alexa;

import android.content.Context;
import android.util.Log;
import com.amazon.alexa.client.core.configuration.ClientConfiguration;
import com.amazon.alexa.client.core.configuration.PropertiesConfigurationLoader;
import com.amazon.alexa.client.core.configuration.ResourcesConfigurationLoader;
import dagger.Module;
import dagger.Provides;
import java.io.IOException;
import java.util.Properties;
import javax.inject.Singleton;
/* compiled from: ConfigurationModule.java */
@Module
/* loaded from: classes.dex */
public class VWb {
    public static final String zZm = "VWb";

    @Provides
    @Singleton
    public ClientConfiguration zZm(mOV mov, PropertiesConfigurationLoader propertiesConfigurationLoader, ResourcesConfigurationLoader resourcesConfigurationLoader) {
        ClientConfiguration.Builder builder = ClientConfiguration.builder();
        mov.load(builder);
        propertiesConfigurationLoader.load(builder);
        resourcesConfigurationLoader.load(builder);
        return builder.build();
    }

    @Provides
    @Singleton
    public Properties zZm(Context context) {
        Properties properties = new Properties();
        try {
            properties.load(context.getAssets().open("avs.properties"));
        } catch (IOException e) {
            String str = zZm;
            Log.e(str, "Error reading avs.properties: " + e);
        }
        return properties;
    }
}
