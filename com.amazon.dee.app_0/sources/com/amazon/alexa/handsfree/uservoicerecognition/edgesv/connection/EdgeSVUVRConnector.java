package com.amazon.alexa.handsfree.uservoicerecognition.edgesv.connection;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector;
/* loaded from: classes8.dex */
public class EdgeSVUVRConnector extends UVRConnector {
    private Boolean mIsBlocking;

    public EdgeSVUVRConnector() {
        super(null, null, null);
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToEnrollmentService(@NonNull Context context) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    protected void bindToVendorSettingsService(@NonNull Context context) {
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    public void endConnection(@NonNull Context context) {
        this.mIsBlocking = null;
    }

    public boolean isBlocking() {
        Boolean bool = this.mIsBlocking;
        return bool != null && bool.booleanValue();
    }

    @Override // com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRConnector
    public void startConnection(@NonNull Context context, boolean z) {
        this.mIsBlocking = Boolean.valueOf(z);
    }
}
