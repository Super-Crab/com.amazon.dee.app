package com.amazon.alexa.accessory.davs.i18n;

import io.reactivex.rxjava3.core.Single;
import java.util.Set;
/* loaded from: classes.dex */
public interface DavsI18nConfigStore {
    Single<Set<DavsI18nConfigData>> getDavsI18nConfig(String str);

    Single<DavsI18nConfigData> putDavsI18nConfig(String str, DavsI18nConfigData davsI18nConfigData);
}
