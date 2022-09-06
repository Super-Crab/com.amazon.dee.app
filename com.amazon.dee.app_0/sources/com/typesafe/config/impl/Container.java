package com.typesafe.config.impl;

import com.typesafe.config.ConfigValue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes3.dex */
public interface Container extends ConfigValue {
    boolean hasDescendant(AbstractConfigValue abstractConfigValue);

    /* renamed from: replaceChild */
    AbstractConfigValue mo10252replaceChild(AbstractConfigValue abstractConfigValue, AbstractConfigValue abstractConfigValue2);
}
