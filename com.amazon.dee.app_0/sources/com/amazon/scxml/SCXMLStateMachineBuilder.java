package com.amazon.scxml;

import com.amazon.scxml.internal.ConcreteSCXMLStateMachine;
import com.amazon.scxml.internal.SCXMLStateMachineComponentAssembler;
import com.amazon.scxml.internal.SimpleXMLElement;
import com.amazon.scxml.internal.SimpleXMLReader;
import com.amazon.scxml.logging.Loggable;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SCXMLStateMachineBuilder.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0019\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J \u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t0\bJ \u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b0\bJ\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000e\u001a\u00020\u000fR\u0012\u0010\u0005\u001a\u0004\u0018\u00018\u0000X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u001c\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000b\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/scxml/SCXMLStateMachineBuilder;", "TUserContext", "", "Lcom/amazon/scxml/logging/Loggable;", "()V", "context", "Ljava/lang/Object;", "nativeEvaluators", "Lcom/amazon/scxml/NativeFunctions;", "", "nativeInvocations", "", "xmlStream", "Ljava/io/InputStream;", "xmlString", "", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/amazon/scxml/SCXMLStateMachine;", "withContext", "(Ljava/lang/Object;)Lcom/amazon/scxml/SCXMLStateMachineBuilder;", "withNativeEvaluators", "withNativeInvocations", "withXmlStream", "withXmlString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SCXMLStateMachineBuilder<TUserContext> extends Loggable {
    private TUserContext context;
    private NativeFunctions<TUserContext, Boolean> nativeEvaluators;
    private NativeFunctions<TUserContext, Unit> nativeInvocations;
    private InputStream xmlStream;
    private String xmlString;

    @Nullable
    public final SCXMLStateMachine build() {
        TUserContext tusercontext = this.context;
        if (tusercontext != null) {
            InputStream inputStream = this.xmlStream;
            if (inputStream == null) {
                String str = this.xmlString;
                if (str != null) {
                    byte[] bytes = str.getBytes(Charsets.UTF_8);
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                    inputStream = new ByteArrayInputStream(bytes);
                } else {
                    inputStream = null;
                }
            }
            if (inputStream != null) {
                try {
                    SimpleXMLElement process = new SimpleXMLReader().process(inputStream);
                    if (process == null) {
                        return null;
                    }
                    ConcreteSCXMLStateMachine<TUserContext> assembleMachine$AlexaMobileAndroidVoice_TTA_release = new SCXMLStateMachineComponentAssembler(process, tusercontext).assembleMachine$AlexaMobileAndroidVoice_TTA_release();
                    if (assembleMachine$AlexaMobileAndroidVoice_TTA_release == null) {
                        return null;
                    }
                    assembleMachine$AlexaMobileAndroidVoice_TTA_release.setNativeAdaptors$AlexaMobileAndroidVoice_TTA_release(this.nativeEvaluators, this.nativeInvocations);
                    Loggable.Companion.getLog().i(getTAG(), "created statemachine");
                    return assembleMachine$AlexaMobileAndroidVoice_TTA_release;
                } finally {
                    inputStream.close();
                }
            }
        }
        return null;
    }

    @NotNull
    public final SCXMLStateMachineBuilder<TUserContext> withContext(@NotNull TUserContext context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        this.context = context;
        return this;
    }

    @NotNull
    public final SCXMLStateMachineBuilder<TUserContext> withNativeEvaluators(@NotNull NativeFunctions<TUserContext, Boolean> nativeEvaluators) {
        Intrinsics.checkParameterIsNotNull(nativeEvaluators, "nativeEvaluators");
        this.nativeEvaluators = nativeEvaluators;
        return this;
    }

    @NotNull
    public final SCXMLStateMachineBuilder<TUserContext> withNativeInvocations(@NotNull NativeFunctions<TUserContext, Unit> nativeInvocations) {
        Intrinsics.checkParameterIsNotNull(nativeInvocations, "nativeInvocations");
        this.nativeInvocations = nativeInvocations;
        return this;
    }

    @NotNull
    public final SCXMLStateMachineBuilder<TUserContext> withXmlStream(@NotNull InputStream xmlStream) {
        Intrinsics.checkParameterIsNotNull(xmlStream, "xmlStream");
        this.xmlStream = xmlStream;
        return this;
    }

    @NotNull
    public final SCXMLStateMachineBuilder<TUserContext> withXmlString(@NotNull String xmlString) {
        Intrinsics.checkParameterIsNotNull(xmlString, "xmlString");
        this.xmlString = xmlString;
        return this;
    }
}
