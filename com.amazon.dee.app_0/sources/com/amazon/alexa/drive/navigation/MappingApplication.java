package com.amazon.alexa.drive.navigation;

import android.content.Intent;
import android.net.Uri;
import androidx.annotation.Nullable;
import java.util.Locale;
/* loaded from: classes7.dex */
public abstract class MappingApplication {
    public static final String BIKING = "BIKING";
    public static final String DRIVING = "DRIVING";
    public static final String GOOGLE_MAPS_PACKAGENAME = "com.google.android.apps.maps";
    private static final String TAG = "MappingApplication";
    public static final String TRANSIT = "TRANSIT";
    public static final String WALKING = "WALKING";
    public static final String WAZE_PACKAGENAME = "com.waze";
    private static MappingApplication googleMappingApplication;
    private static MappingApplication wazeMappingApplication;
    private final String name;
    private final String packageName;

    /* loaded from: classes7.dex */
    public static final class GoogleMappingApplication extends MappingApplication {
        private static final String NAME = "GOOGLE_MAPS";
        private static final String URI_FORMAT_ADDRESS = "google.navigation:q=%s&mode=%s";
        private static final String URI_FORMAT_GEO = "google.navigation:q=%f,%f&mode=%s";

        /* loaded from: classes7.dex */
        enum GoogleMapTransportationMode {
            BICYCLE("b"),
            DRIVING("d"),
            WALKING("w");
            
            private final String transportationMode;

            GoogleMapTransportationMode(String str) {
                this.transportationMode = str;
            }

            static GoogleMapTransportationMode from(String str) {
                if (str == null) {
                    return DRIVING;
                }
                char c = 65535;
                switch (str.hashCode()) {
                    case -1656617049:
                        if (str.equals(MappingApplication.DRIVING)) {
                            c = 2;
                            break;
                        }
                        break;
                    case -349077069:
                        if (str.equals(MappingApplication.TRANSIT)) {
                            c = 3;
                            break;
                        }
                        break;
                    case 1836798297:
                        if (str.equals(MappingApplication.WALKING)) {
                            c = 1;
                            break;
                        }
                        break;
                    case 1959247966:
                        if (str.equals(MappingApplication.BIKING)) {
                            c = 0;
                            break;
                        }
                        break;
                }
                if (c == 0) {
                    return BICYCLE;
                }
                if (c != 1) {
                    return DRIVING;
                }
                return WALKING;
            }

            String get() {
                return this.transportationMode;
            }
        }

        @Override // com.amazon.alexa.drive.navigation.MappingApplication
        Uri getUri(double d, double d2, String str) {
            return Uri.parse(String.format(Locale.US, URI_FORMAT_GEO, Double.valueOf(d), Double.valueOf(d2), GoogleMapTransportationMode.from(str).get()));
        }

        private GoogleMappingApplication() {
            super(NAME, MappingApplication.GOOGLE_MAPS_PACKAGENAME);
        }

        @Override // com.amazon.alexa.drive.navigation.MappingApplication
        Uri getUri(String str, String str2) {
            return Uri.parse(String.format(Locale.US, URI_FORMAT_ADDRESS, str, GoogleMapTransportationMode.from(str2).get()));
        }
    }

    /* loaded from: classes7.dex */
    public static final class WazeMappingApplication extends MappingApplication {
        private static final String NAME = "WAZE";
        private static final String URI_FORMAT_ADDRESS = "https://waze.com/ul?q=%s&navigate=yes";
        private static final String URI_FORMAT_GEO = "https://waze.com/ul?ll=%f,%f&navigate=yes";

        @Override // com.amazon.alexa.drive.navigation.MappingApplication
        Uri getUri(double d, double d2, String str) {
            return Uri.parse(String.format(Locale.US, URI_FORMAT_GEO, Double.valueOf(d), Double.valueOf(d2)));
        }

        private WazeMappingApplication() {
            super(NAME, MappingApplication.WAZE_PACKAGENAME);
        }

        @Override // com.amazon.alexa.drive.navigation.MappingApplication
        Uri getUri(String str, String str2) {
            return Uri.parse(String.format(Locale.US, URI_FORMAT_ADDRESS, str));
        }
    }

    public static MappingApplication from(@Nullable String str) {
        if (str == null) {
            return getGoogleMappingApplication();
        }
        char c = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -660073534) {
            if (hashCode == 40719148 && str.equals(GOOGLE_MAPS_PACKAGENAME)) {
                c = 1;
            }
        } else if (str.equals(WAZE_PACKAGENAME)) {
            c = 0;
        }
        if (c != 0) {
            return getGoogleMappingApplication();
        }
        return getWazeMappingApplication();
    }

    private static MappingApplication getGoogleMappingApplication() {
        if (googleMappingApplication == null) {
            googleMappingApplication = new GoogleMappingApplication();
        }
        return googleMappingApplication;
    }

    private static MappingApplication getWazeMappingApplication() {
        if (wazeMappingApplication == null) {
            wazeMappingApplication = new WazeMappingApplication();
        }
        return wazeMappingApplication;
    }

    public Intent getIntent(double d, double d2, String str) {
        Intent intent = new Intent("android.intent.action.VIEW", getUri(d, d2, str));
        intent.setFlags(268435456);
        intent.setPackage(getPackageName());
        return intent;
    }

    public String getPackageName() {
        return this.packageName;
    }

    abstract Uri getUri(double d, double d2, String str);

    abstract Uri getUri(String str, String str2);

    public String name() {
        return this.name;
    }

    private MappingApplication(String str, String str2) {
        this.name = str;
        this.packageName = str2;
    }

    public Intent getIntent(String str, String str2) {
        Intent intent = new Intent("android.intent.action.VIEW", getUri(str, str2));
        intent.setFlags(268435456);
        intent.setPackage(getPackageName());
        return intent;
    }
}
