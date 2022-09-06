package com.amazon.alexa.accessory.speechapi.listeners;

import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Caption.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/Caption;", "", "content", "", "type", "Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionFormat;", "(Ljava/lang/String;Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionFormat;)V", "getContent", "()Ljava/lang/String;", "getType", "()Lcom/amazon/alexa/accessory/speechapi/listeners/CaptionFormat;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Caption {
    @NotNull
    private final String content;
    @NotNull
    private final CaptionFormat type;

    public Caption(@NotNull String content, @NotNull CaptionFormat type) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(type, "type");
        this.content = content;
        this.type = type;
    }

    public static /* synthetic */ Caption copy$default(Caption caption, String str, CaptionFormat captionFormat, int i, Object obj) {
        if ((i & 1) != 0) {
            str = caption.content;
        }
        if ((i & 2) != 0) {
            captionFormat = caption.type;
        }
        return caption.copy(str, captionFormat);
    }

    @NotNull
    public final String component1() {
        return this.content;
    }

    @NotNull
    public final CaptionFormat component2() {
        return this.type;
    }

    @NotNull
    public final Caption copy(@NotNull String content, @NotNull CaptionFormat type) {
        Intrinsics.checkParameterIsNotNull(content, "content");
        Intrinsics.checkParameterIsNotNull(type, "type");
        return new Caption(content, type);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof Caption)) {
                return false;
            }
            Caption caption = (Caption) obj;
            return Intrinsics.areEqual(this.content, caption.content) && Intrinsics.areEqual(this.type, caption.type);
        }
        return true;
    }

    @NotNull
    public final String getContent() {
        return this.content;
    }

    @NotNull
    public final CaptionFormat getType() {
        return this.type;
    }

    public int hashCode() {
        String str = this.content;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        CaptionFormat captionFormat = this.type;
        if (captionFormat != null) {
            i = captionFormat.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Caption(content=");
        outline107.append(this.content);
        outline107.append(", type=");
        outline107.append(this.type);
        outline107.append(")");
        return outline107.toString();
    }
}
