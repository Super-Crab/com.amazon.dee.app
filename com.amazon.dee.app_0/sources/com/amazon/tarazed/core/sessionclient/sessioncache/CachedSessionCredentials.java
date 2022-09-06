package com.amazon.tarazed.core.sessionclient.sessioncache;

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
/* compiled from: CachedSessionCredentials.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0002\u0014\u0015B?\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fB%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\rR\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0016"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "", "seen1", "", "timestamp", "", "encryptedKey", "", "iv", "encryptedCredentials", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(IJLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEncryptedCredentials", "()Ljava/lang/String;", "getEncryptedKey", "getIv", "getTimestamp", "()J", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class CachedSessionCredentials {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String encryptedCredentials;
    @NotNull
    private final String encryptedKey;
    @NotNull
    private final String iv;
    private final long timestamp;

    /* compiled from: CachedSessionCredentials.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/sessionclient/sessioncache/CachedSessionCredentials;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<CachedSessionCredentials> serializer() {
            return CachedSessionCredentials$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ CachedSessionCredentials(int i, long j, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.timestamp = j;
            if ((i & 2) == 0) {
                throw new MissingFieldException("encryptedKey");
            }
            this.encryptedKey = str;
            if ((i & 4) == 0) {
                throw new MissingFieldException("iv");
            }
            this.iv = str2;
            if ((i & 8) == 0) {
                throw new MissingFieldException("encryptedCredentials");
            }
            this.encryptedCredentials = str3;
            return;
        }
        throw new MissingFieldException("timestamp");
    }

    public CachedSessionCredentials(long j, @NotNull String str, @NotNull String str2, @NotNull String str3) {
        GeneratedOutlineSupport1.outline169(str, "encryptedKey", str2, "iv", str3, "encryptedCredentials");
        this.timestamp = j;
        this.encryptedKey = str;
        this.iv = str2;
        this.encryptedCredentials = str3;
    }

    @JvmStatic
    public static final void write$Self(@NotNull CachedSessionCredentials self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeLongElement(serialDesc, 0, self.timestamp);
        output.encodeStringElement(serialDesc, 1, self.encryptedKey);
        output.encodeStringElement(serialDesc, 2, self.iv);
        output.encodeStringElement(serialDesc, 3, self.encryptedCredentials);
    }

    @NotNull
    public final String getEncryptedCredentials() {
        return this.encryptedCredentials;
    }

    @NotNull
    public final String getEncryptedKey() {
        return this.encryptedKey;
    }

    @NotNull
    public final String getIv() {
        return this.iv;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }
}
