package com.typesafe.config;
/* loaded from: classes3.dex */
public interface ConfigParseable {
    ConfigParseOptions options();

    ConfigOrigin origin();

    ConfigObject parse(ConfigParseOptions configParseOptions);
}
