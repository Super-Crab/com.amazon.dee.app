package com.amazon.tarazed.core.sessionclient.model.createcredentials;

import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.rtcsc.android.typedapi.types.IceServer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.CompositeEncoder;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.SerialDescriptor;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.SerializationConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: MediaCredential.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0087\b\u0018\u0000 \u001d2\u00020\u0001:\u0002\u001c\u001dB7\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\u000bJ\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0010\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0015J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\r¨\u0006\u001e"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/MediaCredential;", "", "seen1", "", "endpoint", "", "username", MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD, "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEndpoint", "()Ljava/lang/String;", "getPassword", "getUsername", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toIceServer", "Lcom/amazon/rtcsc/android/typedapi/types/IceServer;", "isBeta", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class MediaCredential {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String endpoint;
    @NotNull
    private final String password;
    @NotNull
    private final String username;

    /* compiled from: MediaCredential.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/MediaCredential$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/MediaCredential;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<MediaCredential> serializer() {
            return MediaCredential$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ MediaCredential(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.endpoint = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("username");
            }
            this.username = str2;
            if ((i & 4) == 0) {
                throw new MissingFieldException(MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
            }
            this.password = str3;
            return;
        }
        throw new MissingFieldException("endpoint");
    }

    public MediaCredential(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "endpoint", str2, "username", str3, MAPAccountManager.KEY_AMAZON_ACCOUNT_PASSWORD);
        this.endpoint = str;
        this.username = str2;
        this.password = str3;
    }

    public static /* synthetic */ MediaCredential copy$default(MediaCredential mediaCredential, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mediaCredential.endpoint;
        }
        if ((i & 2) != 0) {
            str2 = mediaCredential.username;
        }
        if ((i & 4) != 0) {
            str3 = mediaCredential.password;
        }
        return mediaCredential.copy(str, str2, str3);
    }

    public static /* synthetic */ IceServer toIceServer$default(MediaCredential mediaCredential, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return mediaCredential.toIceServer(z);
    }

    @JvmStatic
    public static final void write$Self(@NotNull MediaCredential self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.endpoint);
        output.encodeStringElement(serialDesc, 1, self.username);
        output.encodeStringElement(serialDesc, 2, self.password);
    }

    @NotNull
    public final String component1() {
        return this.endpoint;
    }

    @NotNull
    public final String component2() {
        return this.username;
    }

    @NotNull
    public final String component3() {
        return this.password;
    }

    @NotNull
    public final MediaCredential copy(@NotNull String endpoint, @NotNull String username, @NotNull String password) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(username, "username");
        Intrinsics.checkParameterIsNotNull(password, "password");
        return new MediaCredential(endpoint, username, password);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof MediaCredential)) {
                return false;
            }
            MediaCredential mediaCredential = (MediaCredential) obj;
            return Intrinsics.areEqual(this.endpoint, mediaCredential.endpoint) && Intrinsics.areEqual(this.username, mediaCredential.username) && Intrinsics.areEqual(this.password, mediaCredential.password);
        }
        return true;
    }

    @NotNull
    public final String getEndpoint() {
        return this.endpoint;
    }

    @NotNull
    public final String getPassword() {
        return this.password;
    }

    @NotNull
    public final String getUsername() {
        return this.username;
    }

    public int hashCode() {
        String str = this.endpoint;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.username;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.password;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public final IceServer toIceServer(boolean z) {
        String outline91;
        if (z) {
            outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("turn:"), this.endpoint, ":8193?transport=tcp");
        } else {
            outline91 = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107("turn:"), this.endpoint, "?transport=udp");
        }
        return new IceServer(outline91, this.username, this.password);
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MediaCredential(endpoint=");
        outline107.append(this.endpoint);
        outline107.append(", username=");
        outline107.append(this.username);
        outline107.append(", password=");
        return GeneratedOutlineSupport1.outline91(outline107, this.password, ")");
    }
}
