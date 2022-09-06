package com.amazon.alexa.accessoryclient.common.query.request;

import android.os.Bundle;
import com.amazon.alexa.accessory.Accessory;
import com.amazon.alexa.accessory.AccessorySessionOptions;
import com.amazon.alexa.accessoryclient.common.query.Query;
import com.amazon.alexa.accessoryclient.common.transformers.AccessorySessionOptionsTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.AccessoryTransformer;
import com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer;
import com.amazon.alexa.accessorykit.ModelTransformer;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: CreateSessionRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u001aB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00000\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/CreateSessionRequest;", "Lcom/amazon/alexa/accessoryclient/common/query/Query$Request;", ModelTransformer.KEY_ACCESSORY, "Lcom/amazon/alexa/accessory/Accessory;", "accessorySessionOptions", "Lcom/amazon/alexa/accessory/AccessorySessionOptions;", "(Lcom/amazon/alexa/accessory/Accessory;Lcom/amazon/alexa/accessory/AccessorySessionOptions;)V", "getAccessory", "()Lcom/amazon/alexa/accessory/Accessory;", "getAccessorySessionOptions", "()Lcom/amazon/alexa/accessory/AccessorySessionOptions;", "bundleTransformer", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "getBundleTransformer", "()Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "Transformer", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CreateSessionRequest implements Query.Request<CreateSessionRequest> {
    @NotNull
    private final Accessory accessory;
    @NotNull
    private final AccessorySessionOptions accessorySessionOptions;
    @NotNull
    private final BundleTransformer<CreateSessionRequest> bundleTransformer;

    /* compiled from: CreateSessionRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/query/request/CreateSessionRequest$Transformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessoryclient/common/query/request/CreateSessionRequest;", "()V", "ACCESSORY_KEY", "", "OPTIONS_KEY", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes6.dex */
    public static final class Transformer implements BundleTransformer<CreateSessionRequest> {
        private static final String ACCESSORY_KEY = "accessory";
        public static final Transformer INSTANCE = new Transformer();
        private static final String OPTIONS_KEY = "options";

        private Transformer() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        /* renamed from: fromBundle */
        public CreateSessionRequest mo568fromBundle(@NotNull Bundle bundle) {
            Intrinsics.checkParameterIsNotNull(bundle, "bundle");
            AccessoryTransformer accessoryTransformer = AccessoryTransformer.INSTANCE;
            Bundle bundle2 = bundle.getBundle("accessory");
            if (bundle2 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle2, "bundle.getBundle(ACCESSORY_KEY)!!");
            Accessory mo568fromBundle = accessoryTransformer.mo568fromBundle(bundle2);
            AccessorySessionOptionsTransformer accessorySessionOptionsTransformer = AccessorySessionOptionsTransformer.INSTANCE;
            Bundle bundle3 = bundle.getBundle("options");
            if (bundle3 == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(bundle3, "bundle.getBundle(OPTIONS_KEY)!!");
            return new CreateSessionRequest(mo568fromBundle, accessorySessionOptionsTransformer.mo568fromBundle(bundle3));
        }

        @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
        @NotNull
        public Bundle toBundle(@NotNull CreateSessionRequest t) {
            Intrinsics.checkParameterIsNotNull(t, "t");
            Bundle bundle = new Bundle();
            bundle.putBundle("accessory", AccessoryTransformer.INSTANCE.toBundle(t.getAccessory()));
            bundle.putBundle("options", AccessorySessionOptionsTransformer.INSTANCE.toBundle(t.getAccessorySessionOptions()));
            return bundle;
        }
    }

    public CreateSessionRequest(@NotNull Accessory accessory, @NotNull AccessorySessionOptions accessorySessionOptions) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessorySessionOptions, "accessorySessionOptions");
        this.accessory = accessory;
        this.accessorySessionOptions = accessorySessionOptions;
        this.bundleTransformer = Transformer.INSTANCE;
    }

    public static /* synthetic */ CreateSessionRequest copy$default(CreateSessionRequest createSessionRequest, Accessory accessory, AccessorySessionOptions accessorySessionOptions, int i, Object obj) {
        if ((i & 1) != 0) {
            accessory = createSessionRequest.accessory;
        }
        if ((i & 2) != 0) {
            accessorySessionOptions = createSessionRequest.accessorySessionOptions;
        }
        return createSessionRequest.copy(accessory, accessorySessionOptions);
    }

    @NotNull
    public final Accessory component1() {
        return this.accessory;
    }

    @NotNull
    public final AccessorySessionOptions component2() {
        return this.accessorySessionOptions;
    }

    @NotNull
    public final CreateSessionRequest copy(@NotNull Accessory accessory, @NotNull AccessorySessionOptions accessorySessionOptions) {
        Intrinsics.checkParameterIsNotNull(accessory, "accessory");
        Intrinsics.checkParameterIsNotNull(accessorySessionOptions, "accessorySessionOptions");
        return new CreateSessionRequest(accessory, accessorySessionOptions);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof CreateSessionRequest)) {
                return false;
            }
            CreateSessionRequest createSessionRequest = (CreateSessionRequest) obj;
            return Intrinsics.areEqual(this.accessory, createSessionRequest.accessory) && Intrinsics.areEqual(this.accessorySessionOptions, createSessionRequest.accessorySessionOptions);
        }
        return true;
    }

    @NotNull
    public final Accessory getAccessory() {
        return this.accessory;
    }

    @NotNull
    public final AccessorySessionOptions getAccessorySessionOptions() {
        return this.accessorySessionOptions;
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public Bundle getBundle() {
        return Query.Request.DefaultImpls.getBundle(this);
    }

    @Override // com.amazon.alexa.accessoryclient.common.query.Query.Request
    @NotNull
    public BundleTransformer<CreateSessionRequest> getBundleTransformer() {
        return this.bundleTransformer;
    }

    public int hashCode() {
        Accessory accessory = this.accessory;
        int i = 0;
        int hashCode = (accessory != null ? accessory.hashCode() : 0) * 31;
        AccessorySessionOptions accessorySessionOptions = this.accessorySessionOptions;
        if (accessorySessionOptions != null) {
            i = accessorySessionOptions.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("CreateSessionRequest(accessory=");
        outline107.append(this.accessory);
        outline107.append(", accessorySessionOptions=");
        outline107.append(this.accessorySessionOptions);
        outline107.append(")");
        return outline107.toString();
    }
}
