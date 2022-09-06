package com.amazon.alexa.handsfree.protocols.dependencies;

import android.content.Context;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.lang.reflect.Constructor;
/* loaded from: classes8.dex */
public abstract class AhfComponentsProvider {
    public static final String COMPONENTS_PROVIDER_CLASS_NAME = "com.amazon.alexa.handsfree.adapter.AhfComponentsProviderImpl";
    private static final String TAG = "AhfComponentsProvider";
    private static ClassLoader sClassLoader = AhfComponentsProvider.class.getClassLoader();
    private static volatile AhfComponentsProvider sComponentsProvider = null;

    public static <T extends AhfComponentProtocol> T getComponent(Context context, Class<T> cls) {
        return (T) getInstance(context).getComponent(cls);
    }

    public static AhfComponentsProvider getInstance(Context context) {
        AhfComponentsProvider ahfComponentsProvider = sComponentsProvider;
        if (ahfComponentsProvider == null) {
            synchronized (AhfComponentsProvider.class) {
                ahfComponentsProvider = sComponentsProvider;
                if (ahfComponentsProvider == null) {
                    try {
                        Log.d(TAG, "getInstance: Load com.amazon.alexa.handsfree.adapter.AhfComponentsProviderImpl");
                        if (sClassLoader != null) {
                            Constructor<?> declaredConstructor = sClassLoader.loadClass(COMPONENTS_PROVIDER_CLASS_NAME).getDeclaredConstructor(Context.class);
                            declaredConstructor.setAccessible(true);
                            AhfComponentsProvider ahfComponentsProvider2 = (AhfComponentsProvider) declaredConstructor.newInstance(context.getApplicationContext());
                            sComponentsProvider = ahfComponentsProvider2;
                            ahfComponentsProvider = ahfComponentsProvider2;
                        } else {
                            throw new IllegalStateException("No classloader!");
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(TAG + ": Failed to create components provider", e);
                    }
                }
            }
        }
        return ahfComponentsProvider;
    }

    @VisibleForTesting
    public static void resetForTest() {
        sClassLoader = AhfComponentsProvider.class.getClassLoader();
        sComponentsProvider = null;
    }

    @VisibleForTesting
    public static void setClassLoader(ClassLoader classLoader) {
        sClassLoader = classLoader;
    }

    @VisibleForTesting
    public static void setComponentsProviderForTest(AhfComponentsProvider ahfComponentsProvider) {
        sComponentsProvider = ahfComponentsProvider;
    }

    public abstract <T extends AhfComponentProtocol> T getComponent(Class<T> cls);
}
