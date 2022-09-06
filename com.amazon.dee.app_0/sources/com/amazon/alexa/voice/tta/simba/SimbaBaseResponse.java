package com.amazon.alexa.voice.tta.simba;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: SimbaApi.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/voice/tta/simba/SimbaBaseResponse;", "", "errorCode", "", "getErrorCode", "()Ljava/lang/String;", "errorDescription", "getErrorDescription", "statusCode", "", "getStatusCode", "()I", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes11.dex */
public interface SimbaBaseResponse {
    @Nullable
    String getErrorCode();

    @Nullable
    String getErrorDescription();

    int getStatusCode();
}
