package com.amazon.scxml.logging;

import com.amazon.alexa.accessorykit.ModelTransformer;
import com.amazon.alexa.voice.ui.driveMode.util.DriveModeVoxUiConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.drew.metadata.wav.WavDirectory;
import com.facebook.common.callercontext.ContextChain;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: PrintLnLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\"\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u001a\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\r\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lcom/amazon/scxml/logging/PrintLnLogger;", "Lcom/amazon/scxml/logging/Logger;", "()V", "d", "", "tag", "", "message", "e", ContextChain.TAG_INFRA, "printMessage", ModelTransformer.KEY_BATTERY_LEVEL, "v", "w", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class PrintLnLogger implements Logger {
    private final void printMessage(String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(JsonReaderKt.BEGIN_LIST);
        sb.append(str);
        sb.append("]:[");
        if (str2 == null) {
            str2 = "";
        }
        System.out.println((Object) GeneratedOutlineSupport1.outline92(sb, str2, "] ", str3));
    }

    @Override // com.amazon.scxml.logging.Logger
    public void d(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printMessage("DEBUG", str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void e(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printMessage("ERROR", str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void i(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printMessage(WavDirectory.LIST_INFO, str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void v(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printMessage("VERBOSE", str, message);
    }

    @Override // com.amazon.scxml.logging.Logger
    public void w(@Nullable String str, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(message, "message");
        printMessage(DriveModeVoxUiConstants.WARN, str, message);
    }
}
