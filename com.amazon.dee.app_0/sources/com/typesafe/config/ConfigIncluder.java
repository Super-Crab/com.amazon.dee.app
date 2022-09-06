package com.typesafe.config;
/* loaded from: classes3.dex */
public interface ConfigIncluder {
    ConfigObject include(ConfigIncludeContext configIncludeContext, String str);

    ConfigIncluder withFallback(ConfigIncluder configIncluder);
}
