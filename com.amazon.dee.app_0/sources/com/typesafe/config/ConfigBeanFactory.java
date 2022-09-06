package com.typesafe.config;

import com.typesafe.config.impl.ConfigBeanImpl;
/* loaded from: classes3.dex */
public class ConfigBeanFactory {
    public static <T> T create(Config config, Class<T> cls) {
        return (T) ConfigBeanImpl.createInternal(config, cls);
    }
}
