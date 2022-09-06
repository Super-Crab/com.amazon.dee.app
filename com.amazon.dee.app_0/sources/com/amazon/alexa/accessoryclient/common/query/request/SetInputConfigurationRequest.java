package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Input;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.InputBehaviorConfigurationTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SetInputConfigurationRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001dB\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J'\u0010\u0016\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aHÖ\u0003J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00000\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetInputConfigurationRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", "identifier", "", "deviceId", "", "inputBehaviorConfiguration", "Lcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfiguration;", "(Ljava/lang/String;ILcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfiguration;)V", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getDeviceId", "()I", "getIdentifier", "()Ljava/lang/String;", "getInputBehaviorConfiguration", "()Lcom/amazon/alexa/accessory/protocol/Input$InputBehaviorConfiguration;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SetInputConfigurationRequest implements Query.Request<SetInputConfigurationRequest> {
    @NotNull
    private final BundleTransformer<SetInputConfigurationRequest> bundleTransformer;
    private final int deviceId;
    @NotNull
    private final String identifier;
    @NotNull
    private final Input.InputBehaviorConfiguration inputBehaviorConfiguration;

    /* compiled from: SetInputConfigurationRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/SetInputConfigurationRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/SetInputConfigurationRequest;", "()V", "DEVICE_ID_KEY", "", "IDENTIFIER_KEY", "INPUT_BEHAVIOR_CONFIGURATION_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "setInputConfigurationRequest", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<SetInputConfigurationRequest> {
        private static final String DEVICE_ID_KEY = "deviceId";
        private static final String IDENTIFIER_KEY = "identifier";
        private static final String INPUT_BEHAVIOR_CONFIGURATION_KEY = "inputConfiguration";
        public static final Transformer INSTANCE = new Transformer();

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public SetInputConfigurationRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            String string = bundle.getString("identifier");
            if (string == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(string, "bundle.getString(IDENTIFIER_KEY)!!");
            int i = bundle.getInt("deviceId");
            InputBehaviorConfigurationTransformer inputBehaviorConfigurationTransformer = InputBehaviorConfigurationTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle(INPUT_BEHAVIOR_CONFIGURATION_KEY);
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(INPUT_B…VIOR_CONFIGURATION_KEY)!!");
            return new SetInputConfigurationRequest(string, i, inputBehaviorConfigurationTransformer.mo568fromBundle(bundle2));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull SetInputConfigurationRequest setInputConfigurationRequest) {
            Intrinsics.checkParameterIsNotNull(setInputConfigurationRequest, "setInputConfigurationRequest");
            Bundle bundle = new Bundle();
            bundle.putString("identifier", setInputConfigurationRequest.getIdentifier());
            bundle.putInt("deviceId", setInputConfigurationRequest.getDeviceId());
            bundle.putBundle(INPUT_BEHAVIOR_CONFIGURATION_KEY, InputBehaviorConfigurationTransformer.INSTANCE.toBundle(setInputConfigurationRequest.getInputBehaviorConfiguration()));
            return bundle;
        }
    }

    public SetInputConfigurationRequest(@NotNull String identifier, int i, @NotNull Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(inputBehaviorConfiguration, "inputBehaviorConfiguration");
        this.identifier = identifier;
        this.deviceId = i;
        this.inputBehaviorConfiguration = inputBehaviorConfiguration;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ SetInputConfigurationRequest copy$default(SetInputConfigurationRequest setInputConfigurationRequest, String str, int i, Input.InputBehaviorConfiguration inputBehaviorConfiguration, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = setInputConfigurationRequest.identifier;
        }
        if ((i2 & 2) != 0) {
            i = setInputConfigurationRequest.deviceId;
        }
        if ((i2 & 4) != 0) {
            inputBehaviorConfiguration = setInputConfigurationRequest.inputBehaviorConfiguration;
        }
        return setInputConfigurationRequest.copy(str, i, inputBehaviorConfiguration);
    }

    @NotNull
    public final String component1() {
        return this.identifier;
    }

    public final int component2() {
        return this.deviceId;
    }

    @NotNull
    public final Input.InputBehaviorConfiguration component3() {
        return this.inputBehaviorConfiguration;
    }

    @NotNull
    public final SetInputConfigurationRequest copy(@NotNull String identifier, int i, @NotNull Input.InputBehaviorConfiguration inputBehaviorConfiguration) {
        Intrinsics.checkParameterIsNotNull(identifier, "identifier");
        Intrinsics.checkParameterIsNotNull(inputBehaviorConfiguration, "inputBehaviorConfiguration");
        return new SetInputConfigurationRequest(identifier, i, inputBehaviorConfiguration);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof SetInputConfigurationRequest)) {
                return false;
            }
            SetInputConfigurationRequest setInputConfigurationRequest = (SetInputConfigurationRequest) obj;
            return Intrinsics.areEqual(this.identifier, setInputConfigurationRequest.identifier) && this.deviceId == setInputConfigurationRequest.deviceId && Intrinsics.areEqual(this.inputBehaviorConfiguration, setInputConfigurationRequest.inputBehaviorConfiguration);
        }
        return true;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<SetInputConfigurationRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    public final int getDeviceId() {
        return this.deviceId;
    }

    @NotNull
    public final String getIdentifier() {
        return this.identifier;
    }

    @NotNull
    public final Input.InputBehaviorConfiguration getInputBehaviorConfiguration() {
        return this.inputBehaviorConfiguration;
    }

    public int hashCode() {
        String str = this.identifier;
        int i = 0;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + this.deviceId) * 31;
        Input.InputBehaviorConfiguration inputBehaviorConfiguration = this.inputBehaviorConfiguration;
        if (inputBehaviorConfiguration != null) {
            i = inputBehaviorConfiguration.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SetInputConfigurationRequest(identifier=");
        outline107.append(this.identifier);
        outline107.append(", deviceId=");
        outline107.append(this.deviceId);
        outline107.append(", inputBehaviorConfiguration=");
        outline107.append(this.inputBehaviorConfiguration);
        outline107.append(")");
        return outline107.toString();
    }
}
