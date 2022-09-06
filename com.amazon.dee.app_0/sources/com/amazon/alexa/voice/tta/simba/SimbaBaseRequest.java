package com.amazon.alexa.voice.tta.simba;

import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.client.metrics.thirdparty.configuration.MetricsConfiguration;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010%\n\u0000\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0016H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0014\u0010\n\u001a\u0004\u0018\u00010\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u0012\u0010\r\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0005R\u0012\u0010\u000f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0005R\u0012\u0010\u0011\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005R\u0012\u0010\u0013\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0005¨\u0006\u0017"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaBaseRequest;", "", "appVersion", "", "getAppVersion", "()Ljava/lang/String;", "clientRequestId", "getClientRequestId", "dsn", "getDsn", "isTestTraffic", "", "()Ljava/lang/Boolean;", "locale", "getLocale", WebConstants.WebviewConstants.MARKETPLACE_ID, "getMarketplaceId", MetricsConfiguration.PLATFORM, "getPlatform", "platformVersion", "getPlatformVersion", "toMap", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface SimbaBaseRequest {
    @NotNull
    String getAppVersion();

    @NotNull
    String getClientRequestId();

    @NotNull
    String getDsn();

    @NotNull
    String getLocale();

    @NotNull
    String getMarketplaceId();

    @NotNull
    String getPlatform();

    @NotNull
    String getPlatformVersion();

    @Nullable
    Boolean isTestTraffic();

    @NotNull
    Map<String, String> toMap();
}
