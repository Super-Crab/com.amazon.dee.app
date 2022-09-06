package com.typesafe.config;
/* loaded from: classes3.dex */
public interface ConfigValue extends ConfigMergeable {
    /* renamed from: atKey */
    Config mo10174atKey(String str);

    /* renamed from: atPath */
    Config mo10175atPath(String str);

    /* renamed from: origin */
    ConfigOrigin mo10176origin();

    String render();

    String render(ConfigRenderOptions configRenderOptions);

    /* renamed from: unwrapped */
    Object mo10253unwrapped();

    ConfigValueType valueType();

    @Override // com.typesafe.config.ConfigMergeable
    /* renamed from: withFallback  reason: collision with other method in class */
    ConfigValue mo10234withFallback(ConfigMergeable configMergeable);

    /* renamed from: withOrigin */
    ConfigValue mo10244withOrigin(ConfigOrigin configOrigin);
}
