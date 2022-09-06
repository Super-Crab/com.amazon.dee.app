package com.amazon.alexa.fitness.service.hds;

import com.amazon.alexa.fitness.service.hds.model.Error;
import com.amazon.identity.auth.device.api.MAPWebViewEventHelper;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
/* compiled from: HdsClientImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/alexa/fitness/service/hds/HdsClientException;", "Lcom/amazon/alexa/fitness/service/hds/HdsException;", MAPWebViewEventHelper.KEY_ERRORS, "", "Lcom/amazon/alexa/fitness/service/hds/model/Error;", "(Ljava/util/List;)V", "getErrors", "()Ljava/util/List;", "AlexaMobileAndroidFitnessExtension_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes8.dex */
public final class HdsClientException extends HdsException {
    @Nullable
    private final List<Error> errors;

    public HdsClientException(@Nullable List<Error> list) {
        this.errors = list;
    }

    @Nullable
    public final List<Error> getErrors() {
        return this.errors;
    }
}
