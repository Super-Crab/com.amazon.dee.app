package com.amazon.alexa.accessory.speechapi.csm.speech;

import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CsmDataSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/csm/speech/CsmDataSink;", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "outputStream", "Ljava/io/ByteArrayOutputStream;", "(Ljava/io/ByteArrayOutputStream;)V", "getOutputStream", "()Ljava/io/ByteArrayOutputStream;", "getAccessoryOutputStream", "Ljava/io/OutputStream;", "AlexaAccessoryAndroid-speech-api-csm_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CsmDataSink implements AccessorySink {
    @NotNull
    private final ByteArrayOutputStream outputStream;

    public CsmDataSink() {
        this(null, 1, null);
    }

    public CsmDataSink(@NotNull ByteArrayOutputStream outputStream) {
        Intrinsics.checkParameterIsNotNull(outputStream, "outputStream");
        this.outputStream = outputStream;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.AccessorySink
    @NotNull
    public OutputStream getAccessoryOutputStream() {
        return this.outputStream;
    }

    @NotNull
    public final ByteArrayOutputStream getOutputStream() {
        return this.outputStream;
    }

    public /* synthetic */ CsmDataSink(ByteArrayOutputStream byteArrayOutputStream, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ByteArrayOutputStream() : byteArrayOutputStream);
    }
}
