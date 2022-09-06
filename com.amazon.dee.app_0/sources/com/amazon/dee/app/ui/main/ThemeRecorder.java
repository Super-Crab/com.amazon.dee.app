package com.amazon.dee.app.ui.main;

import android.content.Context;
import com.amazon.alexa.identity.api.IdentityService;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.event.operational.OperationalEventType;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.ui.util.UiUtils;
import dagger.Lazy;
/* loaded from: classes12.dex */
public class ThemeRecorder {
    private static final String TAG = "ThemeRecorder";
    private Context context;
    private Lazy<IdentityService> identityService;
    private Lazy<Mobilytics> mobilyticsService;

    /* renamed from: com.amazon.dee.app.ui.main.ThemeRecorder$1  reason: invalid class name */
    /* loaded from: classes12.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$dee$app$ui$main$ThemeRecorder$ColorScheme = new int[ColorScheme.values().length];

        static {
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$ThemeRecorder$ColorScheme[ColorScheme.LIGHT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$dee$app$ui$main$ThemeRecorder$ColorScheme[ColorScheme.DARK.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* loaded from: classes12.dex */
    public enum ColorScheme {
        LIGHT,
        DARK,
        UNKNOWN
    }

    public ThemeRecorder(Context context, Lazy<Mobilytics> lazy, Lazy<IdentityService> lazy2) {
        this.context = context;
        this.mobilyticsService = lazy;
        this.identityService = lazy2;
    }

    public ColorScheme getColorScheme() {
        if (UiUtils.isLightMode(this.context)) {
            return ColorScheme.LIGHT;
        }
        return ColorScheme.DARK;
    }

    public void recordTheme() {
        if (this.identityService.mo358get().getUser(TAG) == null) {
            return;
        }
        recordTheme(getColorScheme());
    }

    public void recordThemeOnSchemeChange(ColorScheme colorScheme) {
        if (this.identityService.mo358get().getUser(TAG) == null) {
            return;
        }
        ColorScheme colorScheme2 = getColorScheme();
        if (colorScheme.equals(colorScheme2)) {
            return;
        }
        recordTheme(colorScheme2);
    }

    private void recordTheme(ColorScheme colorScheme) {
        int ordinal = colorScheme.ordinal();
        this.mobilyticsService.mo358get().recordOperationalEvent(this.mobilyticsService.mo358get().createOperationalEvent(ordinal != 0 ? ordinal != 1 ? AlexaMetricsConstants.MetricEvents.APP_THEME_JASPER_UNKNOWN : AlexaMetricsConstants.MetricEvents.APP_THEME_JASPER_DARK : AlexaMetricsConstants.MetricEvents.APP_THEME_JASPER_LIGHT, OperationalEventType.COUNTER, "Application", TAG, OwnerIdentifier.ALEXA_MOBILE_PLATFORM_MOSAIC));
    }
}
