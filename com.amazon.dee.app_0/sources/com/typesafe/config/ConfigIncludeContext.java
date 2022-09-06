package com.typesafe.config;
/* loaded from: classes3.dex */
public interface ConfigIncludeContext {
    ConfigParseOptions parseOptions();

    ConfigParseable relativeTo(String str);

    ConfigIncludeContext setParseOptions(ConfigParseOptions configParseOptions);
}
