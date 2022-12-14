package com.amazon.tarazed.core.appInfo.sessioninfo;

import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
/* compiled from: SessionInfo.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0012\u0010\u000e\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0005R\u0012\u0010\u0010\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/amazon/tarazed/core/appInfo/sessioninfo/SessionInfo;", "", "applicationName", "", "getApplicationName", "()Ljava/lang/String;", MetricsConfiguration.DEVICE_LANGUAGE, "getDeviceLanguage", "handledEventTypes", "", "getHandledEventTypes", "()Ljava/util/List;", "operatingSystem", "getOperatingSystem", "operatingSystemVersion", "getOperatingSystemVersion", "tarazedVersion", "getTarazedVersion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public interface SessionInfo {
    @NotNull
    String getApplicationName();

    @NotNull
    String getDeviceLanguage();

    @NotNull
    List<String> getHandledEventTypes();

    @NotNull
    String getOperatingSystem();

    @NotNull
    String getOperatingSystemVersion();

    @NotNull
    String getTarazedVersion();
}
