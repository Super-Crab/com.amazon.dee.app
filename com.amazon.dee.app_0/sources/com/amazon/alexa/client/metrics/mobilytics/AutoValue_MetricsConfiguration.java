package com.amazon.alexa.client.metrics.mobilytics;

import com.amazon.alexa.drive.entertainment.EntertainmentConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
/* loaded from: classes6.dex */
final class AutoValue_MetricsConfiguration extends MetricsConfiguration {
    private final String applicationId;
    private final String serviceName;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MetricsConfiguration(String str, String str2) {
        if (str != null) {
            this.applicationId = str;
            if (str2 != null) {
                this.serviceName = str2;
                return;
            }
            throw new NullPointerException("Null serviceName");
        }
        throw new NullPointerException("Null applicationId");
    }

    @Override // com.amazon.alexa.client.metrics.mobilytics.MetricsConfiguration
    public String applicationId() {
        return this.applicationId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricsConfiguration)) {
            return false;
        }
        MetricsConfiguration metricsConfiguration = (MetricsConfiguration) obj;
        return this.applicationId.equals(metricsConfiguration.applicationId()) && this.serviceName.equals(metricsConfiguration.serviceName());
    }

    public int hashCode() {
        return ((this.applicationId.hashCode() ^ 1000003) * 1000003) ^ this.serviceName.hashCode();
    }

    @Override // com.amazon.alexa.client.metrics.mobilytics.MetricsConfiguration
    public String serviceName() {
        return this.serviceName;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MetricsConfiguration{applicationId=");
        outline107.append(this.applicationId);
        outline107.append(", serviceName=");
        return GeneratedOutlineSupport1.outline91(outline107, this.serviceName, EntertainmentConstants.TCOMM_PAYLOAD_DESERIALIZED_CLOSE);
    }
}
