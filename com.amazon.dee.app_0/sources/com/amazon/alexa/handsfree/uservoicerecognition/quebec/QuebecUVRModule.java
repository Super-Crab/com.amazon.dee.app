package com.amazon.alexa.handsfree.uservoicerecognition.quebec;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.api.UVRContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.provider.EnrollmentStateProvider;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.connection.QuebecUVRConnector;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.enrollment.QuebecUVREnroller;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.tuningsetting.QuebecUVRTuningSettings;
import com.amazon.alexa.handsfree.uservoicerecognition.quebec.vendorsetting.QuebecUVRVendorSettings;
/* loaded from: classes8.dex */
public class QuebecUVRModule {
    private static QuebecUVRModule sInstance;
    private UVRContract mUVRContract;

    @VisibleForTesting
    QuebecUVRModule() {
    }

    public static synchronized QuebecUVRModule getInstance() {
        QuebecUVRModule quebecUVRModule;
        synchronized (QuebecUVRModule.class) {
            if (sInstance == null) {
                sInstance = new QuebecUVRModule();
            }
            quebecUVRModule = sInstance;
        }
        return quebecUVRModule;
    }

    @NonNull
    public UVRContract getUVRContract(@NonNull Context context) {
        if (this.mUVRContract == null) {
            QuebecUVRConnector quebecUVRConnector = new QuebecUVRConnector(new EnrollmentStateProvider(context));
            this.mUVRContract = new UVRContract(new QuebecUVREnroller(quebecUVRConnector), new QuebecUVRTuningSettings(), new QuebecUVRVendorSettings(quebecUVRConnector), quebecUVRConnector);
        }
        return this.mUVRContract;
    }
}
