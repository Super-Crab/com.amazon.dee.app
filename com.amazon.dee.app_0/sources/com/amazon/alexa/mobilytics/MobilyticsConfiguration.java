package com.amazon.alexa.mobilytics;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amazon.alexa.mobilytics.connector.MobilyticsConnector;
import com.amazon.alexa.mobilytics.identity.MobilyticsDeviceProvider;
import com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker;
import com.amazon.alexa.mobilytics.identity.MobilyticsUserProvider;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;
/* loaded from: classes9.dex */
public class MobilyticsConfiguration {
    private final String applicationId;
    private final List<MobilyticsConnector> connectors;
    private final Context context;
    private final String dcmDeviceType;
    private final boolean debug;
    private final MobilyticsDeviceProvider deviceProvider;
    private final int domain;
    private final MobilyticsEndpointPicker endpointPicker;
    private final String serviceName;
    private final MobilyticsUserProvider userProvider;

    /* loaded from: classes9.dex */
    public static final class Builder {
        private String applicationId;
        private List<MobilyticsConnector> connectors;
        private Context context;
        private String dcmDeviceType;
        private MobilyticsDeviceProvider deviceProvider;
        private MobilyticsEndpointPicker endpointPicker;
        private String serviceName;
        private MobilyticsUserProvider userProvider;
        private int domain = 2;
        private boolean debug = false;

        @NonNull
        public MobilyticsConfiguration build() {
            try {
                Preconditions.checkNotNull(this.context, "context cannot be null");
                boolean z = true;
                Preconditions.checkArgument(!TextUtils.isEmpty(this.applicationId), "applicationId cannot be null");
                if (TextUtils.isEmpty(this.serviceName)) {
                    z = false;
                }
                Preconditions.checkArgument(z, "serviceName cannot be null");
                Preconditions.checkNotNull(this.userProvider, "userProvider cannot be null");
                return new MobilyticsConfiguration(this);
            } catch (Exception e) {
                throw new MobilyticsException("configuration error", e);
            }
        }

        @NonNull
        public Builder withApplicationId(@NonNull String str) {
            this.applicationId = str;
            return this;
        }

        @NonNull
        public Builder withConnectors(@NonNull List<MobilyticsConnector> list) {
            this.connectors = list;
            return this;
        }

        @NonNull
        public Builder withContext(@NonNull Context context) {
            this.context = context;
            return this;
        }

        @NonNull
        public Builder withDcmDeviceType(String str) {
            this.dcmDeviceType = str;
            return this;
        }

        @NonNull
        public Builder withDebug(boolean z) {
            this.debug = z;
            return this;
        }

        @NonNull
        public Builder withDeviceProvider(@NonNull MobilyticsDeviceProvider mobilyticsDeviceProvider) {
            this.deviceProvider = mobilyticsDeviceProvider;
            return this;
        }

        @NonNull
        public Builder withDomain(@NonNull int i) {
            this.domain = i;
            return this;
        }

        @NonNull
        public Builder withEndpointPicker(@NonNull MobilyticsEndpointPicker mobilyticsEndpointPicker) {
            this.endpointPicker = mobilyticsEndpointPicker;
            return this;
        }

        @NonNull
        public Builder withServiceName(@NonNull String str) {
            this.serviceName = str;
            return this;
        }

        @NonNull
        public Builder withUserProvider(@NonNull MobilyticsUserProvider mobilyticsUserProvider) {
            this.userProvider = mobilyticsUserProvider;
            return this;
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes9.dex */
    public @interface Domain {
        public static final int BETA = 0;
        public static final int GAMMA = 1;
        public static final int PROD = 2;
    }

    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public String applicationId() {
        return this.applicationId;
    }

    @Nullable
    public List<MobilyticsConnector> connectors() {
        return this.connectors;
    }

    @NonNull
    public Context context() {
        return this.context;
    }

    @Nullable
    public String dcmDeviceType() {
        return this.dcmDeviceType;
    }

    @NonNull
    public MobilyticsDeviceProvider deviceProvider() {
        return this.deviceProvider;
    }

    public int domain() {
        return this.domain;
    }

    @NonNull
    public MobilyticsEndpointPicker endpointPicker() {
        return this.endpointPicker;
    }

    public boolean isDebug() {
        return this.debug;
    }

    @NonNull
    public String serviceName() {
        return this.serviceName;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("MobilyticsConfiguration[");
        StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("context: ");
        outline1072.append(this.context);
        outline1072.append("; ");
        outline107.append(outline1072.toString());
        outline107.append("applicationId: " + this.applicationId + "; ");
        outline107.append("serviceName: " + this.serviceName + "; ");
        outline107.append("userIdentityProvider: " + this.userProvider + "; ");
        outline107.append("domain: " + this.domain + "; ");
        outline107.append("connectors: " + this.connectors + "; ");
        outline107.append("dcmDeviceType: " + this.dcmDeviceType + ";");
        outline107.append("]");
        return outline107.toString();
    }

    @NonNull
    public MobilyticsUserProvider userProvider() {
        return this.userProvider;
    }

    private MobilyticsConfiguration(@NonNull Builder builder) {
        this.context = builder.context;
        this.applicationId = builder.applicationId;
        this.userProvider = builder.userProvider;
        this.endpointPicker = builder.endpointPicker;
        this.deviceProvider = builder.deviceProvider;
        this.domain = builder.domain;
        this.connectors = builder.connectors;
        this.debug = builder.debug;
        this.serviceName = builder.serviceName;
        this.dcmDeviceType = builder.dcmDeviceType;
    }
}
