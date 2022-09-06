package com.amazon.whisperjoin.common.sharedtypes.devices.interfaces;

import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.ConfigurationKeySet;
import com.amazon.whisperjoin.common.sharedtypes.provisioning.data.configuration.DataMap;
import java.util.concurrent.Future;
/* loaded from: classes13.dex */
public interface Configuration {
    Future<Void> addConfiguration(DataMap dataMap);

    Future<Void> deleteConfiguration();

    Future<Void> deleteConfiguration(ConfigurationKeySet configurationKeySet);

    Future<DataMap> getConfiguration();
}
