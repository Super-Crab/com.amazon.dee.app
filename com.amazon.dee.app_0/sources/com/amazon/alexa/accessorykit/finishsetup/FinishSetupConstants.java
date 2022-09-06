package com.amazon.alexa.accessorykit.finishsetup;

import com.amazon.alexa.accessorykit.AlexaDeviceManufacturerSupplier;
import java.util.concurrent.TimeUnit;
/* loaded from: classes6.dex */
public interface FinishSetupConstants {
    public static final int DEFAULT_MAX_PRESENTATION_ATTEMPTS_ALLOWED = 3;
    public static final int FIRST_TIME_VIEW_PRESENTED = 1;
    public static final String[] DEFAULT_BLOCKLISTED_DEVICE_TYPES = {"A303PJF6ISQ7IC", AlexaDeviceManufacturerSupplier.M, "A13W6HQIHKEN3Z"};
    public static final String[] DEFAULT_EXCLUDED_DEVICE_TYPES = {"A303PJF6ISQ7IC", AlexaDeviceManufacturerSupplier.M, AlexaDeviceManufacturerSupplier.HK, "A3IYPH06PH1HRA", "A2QDHDQIWC2LTG", "A31PMVIWCRNTX2", "A3HVREY4JWAZ6K", "A13W6HQIHKEN3Z", "A168KS6Z8QG6RV", "A3KF60B9RDMWLY", "A21YKVRGQV9TET", AlexaDeviceManufacturerSupplier.Z};
    public static final long DEFAULT_PRESENTATION_COOLDOWN_DURATION_IN_MILLISECONDS = TimeUnit.DAYS.toMillis(7);
}
