package com.typesafe.config;

import java.util.Map;
/* loaded from: classes3.dex */
public interface ConfigObject extends ConfigValue, Map<String, ConfigValue> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.Map
    /* renamed from: get */
    ConfigValue mo10154get(Object obj);

    /* renamed from: toConfig */
    Config mo10165toConfig();

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    Map<String, Object> mo10253unwrapped();

    @Override // com.typesafe.config.ConfigValue, com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback  reason: collision with other method in class */
    ConfigObject mo10234withFallback(ConfigMergeable configMergeable);

    /* renamed from: withOnlyKey */
    ConfigObject mo10255withOnlyKey(String str);

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: withOrigin */
    ConfigObject mo10244withOrigin(ConfigOrigin configOrigin);

    /* renamed from: withValue */
    ConfigObject mo10258withValue(String str, ConfigValue configValue);

    /* renamed from: withoutKey */
    ConfigObject mo10260withoutKey(String str);
}
