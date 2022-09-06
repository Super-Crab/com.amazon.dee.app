package com.amazon.whisperjoin.deviceprovisioningservice.di.modules;

import com.amazon.whisperjoin.deviceprovisioningservice.error.WorkflowFailureException;
import com.amazon.whisperjoin.deviceprovisioningservice.workflow.reporting.errorcodes.WJErrorMapper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
/* loaded from: classes13.dex */
public final class ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory implements Factory<WJErrorMapper<WorkflowFailureException>> {
    private final ErrorCodeMapperModule module;

    public ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory(ErrorCodeMapperModule errorCodeMapperModule) {
        this.module = errorCodeMapperModule;
    }

    public static ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory create(ErrorCodeMapperModule errorCodeMapperModule) {
        return new ErrorCodeMapperModule_ProvidesWorkflowFailureExceptionErrorMapperFactory(errorCodeMapperModule);
    }

    public static WJErrorMapper<WorkflowFailureException> provideInstance(ErrorCodeMapperModule errorCodeMapperModule) {
        return proxyProvidesWorkflowFailureExceptionErrorMapper(errorCodeMapperModule);
    }

    public static WJErrorMapper<WorkflowFailureException> proxyProvidesWorkflowFailureExceptionErrorMapper(ErrorCodeMapperModule errorCodeMapperModule) {
        return (WJErrorMapper) Preconditions.checkNotNull(errorCodeMapperModule.providesWorkflowFailureExceptionErrorMapper(), "Cannot return null from a non-@Nullable @Provides method");
    }

    @Override // javax.inject.Provider
    /* renamed from: get */
    public WJErrorMapper<WorkflowFailureException> mo10268get() {
        return provideInstance(this.module);
    }
}
