package com.amazon.alexa.accessory.speechapi.voicesdk.speech;

import com.amazon.alexa.accessory.speechapi.speech.AccessorySink;
import com.amazon.alexa.api.AlexaDataSink;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: VoxDataSink.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/voicesdk/speech/VoxDataSink;", "Lcom/amazon/alexa/accessory/speechapi/speech/AccessorySink;", "dataSink", "Lcom/amazon/alexa/api/AlexaDataSink;", "(Lcom/amazon/alexa/api/AlexaDataSink;)V", "getDataSink", "()Lcom/amazon/alexa/api/AlexaDataSink;", "getAccessoryOutputStream", "Ljava/io/OutputStream;", "AlexaAccessoryAndroid-speech-api-voicesdk_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class VoxDataSink implements AccessorySink {
    @NotNull
    private final AlexaDataSink dataSink;

    public VoxDataSink() {
        this(null, 1, null);
    }

    public VoxDataSink(@NotNull AlexaDataSink dataSink) {
        Intrinsics.checkParameterIsNotNull(dataSink, "dataSink");
        this.dataSink = dataSink;
    }

    @Override // com.amazon.alexa.accessory.speechapi.speech.AccessorySink
    @NotNull
    public OutputStream getAccessoryOutputStream() {
        OutputStream openForWriting = this.dataSink.openForWriting();
        Intrinsics.checkExpressionValueIsNotNull(openForWriting, "dataSink.openForWriting()");
        return openForWriting;
    }

    @NotNull
    public final AlexaDataSink getDataSink() {
        return this.dataSink;
    }

    public /* synthetic */ VoxDataSink(AlexaDataSink alexaDataSink, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new AlexaDataSink() : alexaDataSink);
    }
}
