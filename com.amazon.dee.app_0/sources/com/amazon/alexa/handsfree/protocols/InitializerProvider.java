package com.amazon.alexa.handsfree.protocols;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
/* loaded from: classes8.dex */
public class InitializerProvider {
    public static final String INITIALIZER_CLASS_NAME = "com.amazon.alexa.voice.handsfree.initialization.InitializationModule";
    private static final Initializer NOOP_INITIALIZER = new Initializer() { // from class: com.amazon.alexa.handsfree.protocols.InitializerProvider.1
        @Override // com.amazon.alexa.handsfree.protocols.Initializer
        public void initialize(@NonNull Context context) {
        }
    };
    private static final String TAG = "InitializerProvider";
    private static Initializer mInitializer;

    private InitializerProvider() {
    }

    public static synchronized Initializer getInitializer() {
        Initializer initializer;
        synchronized (InitializerProvider.class) {
            if (mInitializer == null) {
                try {
                    ClassLoader classLoader = Initializer.class.getClassLoader();
                    if (classLoader != null) {
                        Constructor<?> declaredConstructor = classLoader.loadClass(INITIALIZER_CLASS_NAME).getDeclaredConstructor(new Class[0]);
                        declaredConstructor.setAccessible(true);
                        mInitializer = (Initializer) declaredConstructor.newInstance(new Object[0]);
                    } else {
                        throw new IllegalStateException("No classloader!");
                    }
                } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException unused) {
                    Log.w(TAG, "Failed to instantiate initializer, returning no-op");
                    return NOOP_INITIALIZER;
                }
            }
            initializer = mInitializer;
        }
        return initializer;
    }
}
