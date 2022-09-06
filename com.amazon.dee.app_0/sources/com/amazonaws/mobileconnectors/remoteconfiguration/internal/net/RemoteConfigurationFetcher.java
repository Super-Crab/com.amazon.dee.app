package com.amazonaws.mobileconnectors.remoteconfiguration.internal.net;

import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amazonaws.mobileconnectors.remoteconfiguration.internal.model.RemoteConfiguration;
/* loaded from: classes13.dex */
public interface RemoteConfigurationFetcher {
    RemoteConfiguration fetch(String str, Attributes attributes, String str2, String str3);
}
