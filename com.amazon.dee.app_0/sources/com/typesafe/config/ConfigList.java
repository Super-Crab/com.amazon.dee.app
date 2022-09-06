package com.typesafe.config;

import java.util.List;
/* loaded from: classes3.dex */
public interface ConfigList extends List<ConfigValue>, ConfigValue {
    @Override // com.typesafe.config.ConfigValue
    /* renamed from: unwrapped  reason: collision with other method in class */
    List<Object> mo10253unwrapped();

    @Override // com.typesafe.config.ConfigValue
    /* renamed from: withOrigin */
    ConfigList mo10244withOrigin(ConfigOrigin configOrigin);
}
