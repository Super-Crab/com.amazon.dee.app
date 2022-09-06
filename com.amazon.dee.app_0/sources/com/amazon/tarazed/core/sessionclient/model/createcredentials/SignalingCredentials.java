package com.amazon.tarazed.core.sessionclient.model.createcredentials;

import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
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
/* compiled from: SignalingCredentials.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0087\b\u0018\u0000 )2\u00020\u0001:\u0002()Bi\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\u0002\u0010\u000fBE\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u0005\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\t\u0010\u001a\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0005HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003JY\u0010\"\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u00052\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u0005HÆ\u0001J\u0013\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010&\u001a\u00020\u0003HÖ\u0001J\t\u0010'\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0012R\u0011\u0010\u000b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0012R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0012¨\u0006*"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", "", "seen1", "", "endpoint", "", "region", AuthorizationResponseParser.CLIENT_ID_STATE, "accessKey", "secretKey", "sessionToken", "publishTopic", "subscribeTopic", "serializationConstructorMarker", "Lkotlinx/serialization/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/SerializationConstructorMarker;)V", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAccessKey", "()Ljava/lang/String;", "getClientId", "getEndpoint", "getPublishTopic", "getRegion", "getSecretKey", "getSessionToken", "getSubscribeTopic", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "$serializer", "Companion", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
@Serializable
/* loaded from: classes13.dex */
public final class SignalingCredentials {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String accessKey;
    @NotNull
    private final String clientId;
    @NotNull
    private final String endpoint;
    @NotNull
    private final String publishTopic;
    @NotNull
    private final String region;
    @NotNull
    private final String secretKey;
    @NotNull
    private final String sessionToken;
    @NotNull
    private final String subscribeTopic;

    /* compiled from: SignalingCredentials.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004HÆ\u0001¨\u0006\u0006"}, d2 = {"Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials$Companion;", "", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/amazon/tarazed/core/sessionclient/model/createcredentials/SignalingCredentials;", "TarazedMobileCore_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion {
        private Companion() {
        }

        @NotNull
        public final KSerializer<SignalingCredentials> serializer() {
            return SignalingCredentials$$serializer.INSTANCE;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public /* synthetic */ SignalingCredentials(int i, @Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6, @Nullable String str7, @Nullable String str8, @Nullable SerializationConstructorMarker serializationConstructorMarker) {
        if ((i & 1) != 0) {
            this.endpoint = str;
            if ((i & 2) == 0) {
                throw new MissingFieldException("region");
            }
            this.region = str2;
            if ((i & 4) == 0) {
                throw new MissingFieldException(AuthorizationResponseParser.CLIENT_ID_STATE);
            }
            this.clientId = str3;
            if ((i & 8) == 0) {
                throw new MissingFieldException("accessKey");
            }
            this.accessKey = str4;
            if ((i & 16) == 0) {
                throw new MissingFieldException("secretKey");
            }
            this.secretKey = str5;
            if ((i & 32) == 0) {
                throw new MissingFieldException("sessionToken");
            }
            this.sessionToken = str6;
            if ((i & 64) == 0) {
                throw new MissingFieldException("publishTopic");
            }
            this.publishTopic = str7;
            if ((i & 128) == 0) {
                throw new MissingFieldException("subscribeTopic");
            }
            this.subscribeTopic = str8;
            return;
        }
        throw new MissingFieldException("endpoint");
    }

    public SignalingCredentials(@NotNull String endpoint, @NotNull String region, @NotNull String clientId, @NotNull String accessKey, @NotNull String secretKey, @NotNull String sessionToken, @NotNull String publishTopic, @NotNull String subscribeTopic) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(accessKey, "accessKey");
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(sessionToken, "sessionToken");
        Intrinsics.checkParameterIsNotNull(publishTopic, "publishTopic");
        Intrinsics.checkParameterIsNotNull(subscribeTopic, "subscribeTopic");
        this.endpoint = endpoint;
        this.region = region;
        this.clientId = clientId;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
        this.publishTopic = publishTopic;
        this.subscribeTopic = subscribeTopic;
    }

    @JvmStatic
    public static final void write$Self(@NotNull SignalingCredentials self, @NotNull CompositeEncoder output, @NotNull SerialDescriptor serialDesc) {
        Intrinsics.checkParameterIsNotNull(self, "self");
        Intrinsics.checkParameterIsNotNull(output, "output");
        Intrinsics.checkParameterIsNotNull(serialDesc, "serialDesc");
        output.encodeStringElement(serialDesc, 0, self.endpoint);
        output.encodeStringElement(serialDesc, 1, self.region);
        output.encodeStringElement(serialDesc, 2, self.clientId);
        output.encodeStringElement(serialDesc, 3, self.accessKey);
        output.encodeStringElement(serialDesc, 4, self.secretKey);
        output.encodeStringElement(serialDesc, 5, self.sessionToken);
        output.encodeStringElement(serialDesc, 6, self.publishTopic);
        output.encodeStringElement(serialDesc, 7, self.subscribeTopic);
    }

    @NotNull
    public final String component1() {
        return this.endpoint;
    }

    @NotNull
    public final String component2() {
        return this.region;
    }

    @NotNull
    public final String component3() {
        return this.clientId;
    }

    @NotNull
    public final String component4() {
        return this.accessKey;
    }

    @NotNull
    public final String component5() {
        return this.secretKey;
    }

    @NotNull
    public final String component6() {
        return this.sessionToken;
    }

    @NotNull
    public final String component7() {
        return this.publishTopic;
    }

    @NotNull
    public final String component8() {
        return this.subscribeTopic;
    }

    @NotNull
    public final SignalingCredentials copy(@NotNull String endpoint, @NotNull String region, @NotNull String clientId, @NotNull String accessKey, @NotNull String secretKey, @NotNull String sessionToken, @NotNull String publishTopic, @NotNull String subscribeTopic) {
        Intrinsics.checkParameterIsNotNull(endpoint, "endpoint");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(clientId, "clientId");
        Intrinsics.checkParameterIsNotNull(accessKey, "accessKey");
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(sessionToken, "sessionToken");
        Intrinsics.checkParameterIsNotNull(publishTopic, "publishTopic");
        Intrinsics.checkParameterIsNotNull(subscribeTopic, "subscribeTopic");
        return new SignalingCredentials(endpoint, region, clientId, accessKey, secretKey, sessionToken, publishTopic, subscribeTopic);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SignalingCredentials)) {
                return false;
            }
            SignalingCredentials signalingCredentials = (SignalingCredentials) obj;
            return Intrinsics.areEqual(this.endpoint, signalingCredentials.endpoint) && Intrinsics.areEqual(this.region, signalingCredentials.region) && Intrinsics.areEqual(this.clientId, signalingCredentials.clientId) && Intrinsics.areEqual(this.accessKey, signalingCredentials.accessKey) && Intrinsics.areEqual(this.secretKey, signalingCredentials.secretKey) && Intrinsics.areEqual(this.sessionToken, signalingCredentials.sessionToken) && Intrinsics.areEqual(this.publishTopic, signalingCredentials.publishTopic) && Intrinsics.areEqual(this.subscribeTopic, signalingCredentials.subscribeTopic);
        }
        return true;
    }

    @NotNull
    public final String getAccessKey() {
        return this.accessKey;
    }

    @NotNull
    public final String getClientId() {
        return this.clientId;
    }

    @NotNull
    public final String getEndpoint() {
        return this.endpoint;
    }

    @NotNull
    public final String getPublishTopic() {
        return this.publishTopic;
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

    @NotNull
    public final String getSubscribeTopic() {
        return this.subscribeTopic;
    }

    public int hashCode() {
        String str = this.endpoint;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.region;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.clientId;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.accessKey;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.secretKey;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.sessionToken;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.publishTopic;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.subscribeTopic;
        if (str8 != null) {
            i = str8.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SignalingCredentials(endpoint=");
        outline107.append(this.endpoint);
        outline107.append(", region=");
        outline107.append(this.region);
        outline107.append(", clientId=");
        outline107.append(this.clientId);
        outline107.append(", accessKey=");
        outline107.append(this.accessKey);
        outline107.append(", secretKey=");
        outline107.append(this.secretKey);
        outline107.append(", sessionToken=");
        outline107.append(this.sessionToken);
        outline107.append(", publishTopic=");
        outline107.append(this.publishTopic);
        outline107.append(", subscribeTopic=");
        return GeneratedOutlineSupport1.outline91(outline107, this.subscribeTopic, ")");
    }
}
