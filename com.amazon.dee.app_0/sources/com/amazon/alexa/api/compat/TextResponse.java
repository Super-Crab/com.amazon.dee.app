package com.amazon.alexa.api.compat;

import com.amazon.alexa.api.TextResponseMetadata;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: TextResponse.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003J7\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/amazon/alexa/api/compat/TextResponse;", "", "title", "", "description", "url", "metadata", "Lcom/amazon/alexa/api/TextResponseMetadata;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/alexa/api/TextResponseMetadata;)V", "getDescription", "()Ljava/lang/String;", "getMetadata", "()Lcom/amazon/alexa/api/TextResponseMetadata;", "getTitle", "getUrl", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AlexaMobileAndroidVoiceSDKApiCompat_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class TextResponse {
    @Nullable
    private final String description;
    @Nullable
    private final TextResponseMetadata metadata;
    @NotNull
    private final String title;
    @Nullable
    private final String url;

    public TextResponse(@NotNull String title, @Nullable String str, @Nullable String str2, @Nullable TextResponseMetadata textResponseMetadata) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        this.title = title;
        this.description = str;
        this.url = str2;
        this.metadata = textResponseMetadata;
    }

    public static /* synthetic */ TextResponse copy$default(TextResponse textResponse, String str, String str2, String str3, TextResponseMetadata textResponseMetadata, int i, Object obj) {
        if ((i & 1) != 0) {
            str = textResponse.title;
        }
        if ((i & 2) != 0) {
            str2 = textResponse.description;
        }
        if ((i & 4) != 0) {
            str3 = textResponse.url;
        }
        if ((i & 8) != 0) {
            textResponseMetadata = textResponse.metadata;
        }
        return textResponse.copy(str, str2, str3, textResponseMetadata);
    }

    @NotNull
    public final String component1() {
        return this.title;
    }

    @Nullable
    public final String component2() {
        return this.description;
    }

    @Nullable
    public final String component3() {
        return this.url;
    }

    @Nullable
    public final TextResponseMetadata component4() {
        return this.metadata;
    }

    @NotNull
    public final TextResponse copy(@NotNull String title, @Nullable String str, @Nullable String str2, @Nullable TextResponseMetadata textResponseMetadata) {
        Intrinsics.checkParameterIsNotNull(title, "title");
        return new TextResponse(title, str, str2, textResponseMetadata);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof TextResponse)) {
                return false;
            }
            TextResponse textResponse = (TextResponse) obj;
            return Intrinsics.areEqual(this.title, textResponse.title) && Intrinsics.areEqual(this.description, textResponse.description) && Intrinsics.areEqual(this.url, textResponse.url) && Intrinsics.areEqual(this.metadata, textResponse.metadata);
        }
        return true;
    }

    @Nullable
    public final String getDescription() {
        return this.description;
    }

    @Nullable
    public final TextResponseMetadata getMetadata() {
        return this.metadata;
    }

    @NotNull
    public final String getTitle() {
        return this.title;
    }

    @Nullable
    public final String getUrl() {
        return this.url;
    }

    public int hashCode() {
        String str = this.title;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.description;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.url;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        TextResponseMetadata textResponseMetadata = this.metadata;
        if (textResponseMetadata != null) {
            i = textResponseMetadata.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TextResponse(title=");
        outline107.append(this.title);
        outline107.append(", description=");
        outline107.append(this.description);
        outline107.append(", url=");
        outline107.append(this.url);
        outline107.append(", metadata=");
        outline107.append(this.metadata);
        outline107.append(")");
        return outline107.toString();
    }
}
