package com.amazon.alexa.accessoryclient.common.transformers;

import android.os.Bundle;
import com.amazon.alexa.accessory.protocol.Cbl;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* compiled from: CblInformationTransformer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/alexa/accessoryclient/common/transformers/CblInformationTransformer;", "Lcom/amazon/alexa/accessoryclient/common/transformers/BundleTransformer;", "Lcom/amazon/alexa/accessory/protocol/Cbl$CblInformation;", "()V", "CBL_EXPIRE_IN_SECOND", "", "CBL_USER_CODE", "CBL_VERIFICATION_URI", "fromBundle", "bundle", "Landroid/os/Bundle;", "toBundle", "t", "AlexaAccessoryAndroidClient_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class CblInformationTransformer implements BundleTransformer<Cbl.CblInformation> {
    private static final String CBL_EXPIRE_IN_SECOND = "CblExpireInSecond";
    private static final String CBL_USER_CODE = "CblUserCode";
    private static final String CBL_VERIFICATION_URI = "CblVerificationUri";
    public static final CblInformationTransformer INSTANCE = new CblInformationTransformer();

    private CblInformationTransformer() {
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    /* renamed from: fromBundle */
    public Cbl.CblInformation mo568fromBundle(@NotNull Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(bundle, "bundle");
        Cbl.CblInformation mo10084build = Cbl.CblInformation.newBuilder().setUserCode(bundle.getString(CBL_USER_CODE)).setVerificationUri(bundle.getString(CBL_VERIFICATION_URI)).setExpiresInSec(bundle.getInt(CBL_EXPIRE_IN_SECOND)).mo10084build();
        Intrinsics.checkExpressionValueIsNotNull(mo10084build, "CblInformation.newBuilde…\n                .build()");
        return mo10084build;
    }

    @Override // com.amazon.alexa.accessoryclient.common.transformers.BundleTransformer
    @NotNull
    public Bundle toBundle(@NotNull Cbl.CblInformation t) {
        Intrinsics.checkParameterIsNotNull(t, "t");
        Bundle bundle = new Bundle();
        bundle.putString(CBL_USER_CODE, t.getUserCode());
        bundle.putString(CBL_VERIFICATION_URI, t.getVerificationUri());
        bundle.putInt(CBL_EXPIRE_IN_SECOND, t.getExpiresInSec());
        return bundle;
    }
}
