package com.amazon.alexa.voice.handsfree.dependencies;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.features.HandsFreeUserIdentityProvider;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentStatusManager;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.uservoicerecognition.edgesv.EdgeSVUVRModule;
import com.amazon.alexa.handsfree.vendor.bridge.VendorAPIWrapperProvider;
import com.amazon.alexa.voice.handsfree.features.IdentityServiceProvider;
import com.amazon.alexa.voice.handsfree.features.VoxEnrollmentTypeResolver;
import dagger.Module;
import dagger.Provides;
@Module
/* loaded from: classes11.dex */
public class EnrollmentTypeResolverModule {
    @AhfScope
    @Provides
    public EnrollmentTypeResolver provideVoxEnrollmentTypeResolver(@NonNull Context context, @NonNull VendorAPIWrapperProvider vendorAPIWrapperProvider, @NonNull HandsFreeUserIdentityProvider handsFreeUserIdentityProvider) {
        return new VoxEnrollmentTypeResolver(handsFreeUserIdentityProvider, context, EdgeSVUVRModule.getInstance(), vendorAPIWrapperProvider.getSupportedAPIWrapper(), EnrollmentStatusManager.getInstance(context), new IdentityServiceProvider(), AMPDInformationProvider.getInstance(context));
    }
}
