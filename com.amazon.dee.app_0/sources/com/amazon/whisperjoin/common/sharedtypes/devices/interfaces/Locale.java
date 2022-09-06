package com.amazon.whisperjoin.common.sharedtypes.devices.interfaces;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.locale.LocaleConfiguration;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface Locale {
    Future<Void> clearLocaleConfiguration();

    Future<Void> setLocaleConfiguration(LocaleConfiguration localeConfiguration);
}
