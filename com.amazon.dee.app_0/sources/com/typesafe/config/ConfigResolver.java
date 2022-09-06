package com.typesafe.config;
/* loaded from: classes3.dex */
public interface ConfigResolver {
    ConfigValue lookup(String str);

    ConfigResolver withFallback(ConfigResolver configResolver);
}
