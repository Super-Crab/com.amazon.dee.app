package com.amazon.alexa.handsfree.metrics.mobilytics;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.devices.DeviceTypeInformationProvider;
import com.amazon.alexa.handsfree.metrics.events.AMPDMetadataProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfScope;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.utils.ApplicationInformationProvider;
import com.amazon.alexa.mobilytics.event.DefaultMobilyticsEvent;
import com.amazon.alexa.mobilytics.event.metadata.AMPDMetadata;
import com.amazon.alexa.mobilytics.event.metadata.EventMetadata;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
@AhfScope
/* loaded from: classes8.dex */
public class MobilyticsMetadataProvider {
    private final ApplicationInformationProvider mApplicationInformationProvider;
    private final Context mContext;
    private final DeviceTypeInformationProvider mDeviceTypeInformationProvider;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;

    @Inject
    public MobilyticsMetadataProvider(@NonNull Context context, @NonNull ApplicationInformationProvider applicationInformationProvider, @NonNull DeviceTypeInformationProvider deviceTypeInformationProvider, @NonNull Lazy<EnrollmentTypeResolver> lazy) {
        this.mContext = context;
        this.mApplicationInformationProvider = applicationInformationProvider;
        this.mDeviceTypeInformationProvider = deviceTypeInformationProvider;
        this.mEnrollmentTypeResolverLazy = lazy;
    }

    @NonNull
    public Collection<EventMetadata> getMobilyticsMetadata(@NonNull DefaultMobilyticsEvent defaultMobilyticsEvent) {
        AMPDMetadata aMPDMetadata;
        ArrayList arrayList = new ArrayList();
        Integer dspAppBuildCode = this.mApplicationInformationProvider.getDspAppBuildCode();
        if (defaultMobilyticsEvent instanceof AMPDMetadataProvider) {
            aMPDMetadata = ((AMPDMetadataProvider) defaultMobilyticsEvent).getAMPDMetadata();
        } else {
            aMPDMetadata = new AMPDMetadata();
        }
        if (dspAppBuildCode != null) {
            aMPDMetadata.withDspApkVersion(dspAppBuildCode.toString());
        }
        aMPDMetadata.withDeviceType(this.mDeviceTypeInformationProvider.getDeviceInformationForCurrentDevice(this.mContext).getType());
        aMPDMetadata.withSpeakerVerificationType(this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType().toString().replace("_", ""));
        arrayList.add(aMPDMetadata);
        return arrayList;
    }
}
