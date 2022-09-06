package com.amazon.tarazed.core.sessionclient.model.createcredentials;

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
/* compiled from: LoggingCredentials.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 &2\u00020\u0001:\u0002%&B_\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eB=\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0002\u0010\u000fJ\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003JO\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u0005HÆ\u0001J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020\u0003HÖ\u0001J\t\u0010$\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011¨\u0006'"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "", "seen1", "", "accessKey", "", "endpoint", "logGroup", "logStream", "region", "secretKey", "sessionToken", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessKey", "()Ljava/lang/String;", "getEndpoint", "getLogGroup", "getLogStream", "getRegion", "getSecretKey", "getSessionToken", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class LoggingCredentials {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String accessKey;
    @NotNull
    private final String endpoint;
    @NotNull
    private final String logGroup;
    @NotNull
    private final String logStream;
    @NotNull
    private final String region;
    @NotNull
    private final String secretKey;
    @NotNull
    private final String sessionToken;

    /* compiled from: LoggingCredentials.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/LoggingCredentials;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<LoggingCredentials> serializer() {
            return LoggingCredentials$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ LoggingCredentials(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.accessKey = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("endpoint");
            }
            this.endpoint = str2;
            if ((i & 4) == 0) {
                throw new MissingFieldException("logGroup");
            }
            this.logGroup = str3;
            if ((i & 8) == 0) {
                throw new MissingFieldException("logStream");
            }
            this.logStream = str4;
            if ((i & 16) == 0) {
                throw new MissingFieldException("region");
            }
            this.region = str5;
            if ((i & 32) == 0) {
                throw new MissingFieldException("secretKey");
            }
            this.secretKey = str6;
            if ((i & 64) == 0) {
                throw new MissingFieldException("sessionToken");
            }
            this.sessionToken = str7;
            return;
        }
        throw new MissingFieldException("accessKey");
    }

    public LoggingCredentials(@NotNull String accessKey, @NotNull String endpoint, @NotNull String logGroup, @NotNull String logStream, @NotNull String region, @NotNull String secretKey, @NotNull String sessionToken) {
        Intrinsics.checkParameterIsNotNull(accessKey, "accessKey");
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(logGroup, "logGroup");
        Intrinsics.checkParameterIsNotNull(logStream, "logStream");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(sessionToken, "sessionToken");
        this.accessKey = accessKey;
        this.endpoint = endpoint;
        this.logGroup = logGroup;
        this.logStream = logStream;
        this.region = region;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
    }

    public static /* synthetic */ LoggingCredentials copy$default(LoggingCredentials loggingCredentials, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = loggingCredentials.accessKey;
        }
        if ((i & 2) != 0) {
            str2 = loggingCredentials.endpoint;
        }
        String str8 = str2;
        if ((i & 4) != 0) {
            str3 = loggingCredentials.logGroup;
        }
        String str9 = str3;
        if ((i & 8) != 0) {
            str4 = loggingCredentials.logStream;
        }
        String str10 = str4;
        if ((i & 16) != 0) {
            str5 = loggingCredentials.region;
        }
        String str11 = str5;
        if ((i & 32) != 0) {
            str6 = loggingCredentials.secretKey;
        }
        String str12 = str6;
        if ((i & 64) != 0) {
            str7 = loggingCredentials.sessionToken;
        }
        return loggingCredentials.copy(str, str8, str9, str10, str11, str12, str7);
    }

    @JvmStatic
    public static final void write$Self(@NotNull LoggingCredentials self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.accessKey);
        output.encodeStringElement(serialDesc, 1, self.endpoint);
        output.encodeStringElement(serialDesc, 2, self.logGroup);
        output.encodeStringElement(serialDesc, 3, self.logStream);
        output.encodeStringElement(serialDesc, 4, self.region);
        output.encodeStringElement(serialDesc, 5, self.secretKey);
        output.encodeStringElement(serialDesc, 6, self.sessionToken);
    }

    @NotNull
    public final String component1() {
        return this.accessKey;
    }

    @NotNull
    public final String component2() {
        return this.endpoint;
    }

    @NotNull
    public final String component3() {
        return this.logGroup;
    }

    @NotNull
    public final String component4() {
        return this.logStream;
    }

    @NotNull
    public final String component5() {
        return this.region;
    }

    @NotNull
    public final String component6() {
        return this.secretKey;
    }

    @NotNull
    public final String component7() {
        return this.sessionToken;
    }

    @NotNull
    public final LoggingCredentials copy(@NotNull String accessKey, @NotNull String endpoint, @NotNull String logGroup, @NotNull String logStream, @NotNull String region, @NotNull String secretKey, @NotNull String sessionToken) {
        Intrinsics.checkParameterIsNotNull(accessKey, "accessKey");
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(logGroup, "logGroup");
        Intrinsics.checkParameterIsNotNull(logStream, "logStream");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(sessionToken, "sessionToken");
        return new LoggingCredentials(accessKey, endpoint, logGroup, logStream, region, secretKey, sessionToken);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof LoggingCredentials)) {
                return false;
            }
            LoggingCredentials loggingCredentials = (LoggingCredentials) obj;
            return Intrinsics.areEqual(this.accessKey, loggingCredentials.accessKey) && Intrinsics.areEqual(this.endpoint, loggingCredentials.endpoint) && Intrinsics.areEqual(this.logGroup, loggingCredentials.logGroup) && Intrinsics.areEqual(this.logStream, loggingCredentials.logStream) && Intrinsics.areEqual(this.region, loggingCredentials.region) && Intrinsics.areEqual(this.secretKey, loggingCredentials.secretKey) && Intrinsics.areEqual(this.sessionToken, loggingCredentials.sessionToken);
        }
        return true;
    }

    @NotNull
    public final String getAccessKey() {
        return this.accessKey;
    }

    @NotNull
    public final String getEndpoint() {
        return this.endpoint;
    }

    @NotNull
    public final String getLogGroup() {
        return this.logGroup;
    }

    @NotNull
    public final String getLogStream() {
        return this.logStream;
    }

    @NotNull
    public final String getRegion() {
        return this.region;
    }

    @NotNull
    public final String getSecretKey() {
        return this.secretKey;
    }

    @NotNull
    public final String getSessionToken() {
        return this.sessionToken;
    }

    public int hashCode() {
        String str = this.accessKey;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.endpoint;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.logGroup;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.logStream;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.region;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.secretKey;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.sessionToken;
        if (str7 != null) {
            i = str7.hashCode();
        }
        return hashCode6 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("LoggingCredentials(accessKey=");
        outline107.append(this.accessKey);
        outline107.append(", endpoint=");
        outline107.append(this.endpoint);
        outline107.append(", logGroup=");
        outline107.append(this.logGroup);
        outline107.append(", logStream=");
        outline107.append(this.logStream);
        outline107.append(", region=");
        outline107.append(this.region);
        outline107.append(", secretKey=");
        outline107.append(this.secretKey);
        outline107.append(", sessionToken=");
        return GeneratedOutlineSupport1.outline91(outline107, this.sessionToken, ")");
    }
}
