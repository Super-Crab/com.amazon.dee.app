package com.amazon.alexa.drive.navigation.traffic;

import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Handler;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.view.ContextThemeWrapper;
import com.amazon.alexa.drive.R;
import com.amazon.alexa.drive.navigation.CurrentLocationManager;
import com.amazon.alexa.drive.navigation.SavedLocations;
import com.amazon.alexa.drivemode.api.DriveModeCard;
import com.amazon.alexa.drivemode.api.DriveModeThemeManager;
import com.amazon.alexa.drivemode.api.SingleIconCardV2;
import com.amazon.alexa.featureservice.constants.FeatureConstants;
import java.util.concurrent.TimeUnit;
/* loaded from: classes7.dex */
public class TrafficCardV2 extends SingleIconCardV2 implements CurrentLocationManager.LocationChangeListener {
    private static final int NAVIGATION_CARD_ID = 256;
    private static final double STALE_DATA_DISTANCE_METERS = 1609.34d;
    private static final int STALE_DATA_TIME_MILLISECONDS = 600000;
    private static final String TAG = "TrafficCardV2";
    private boolean isDataStale;
    private final ContextThemeWrapper mContext;
    private final DriveModeThemeManager mDriveModeThemeManager;
    private final Handler mHandler;
    private SavedLocations.Item mItem;
    private final Location mStartLocation;
    private final TrafficCardCallback mTrafficCardCallback;
    private final String mTrafficCardType;

    /* renamed from: com.amazon.alexa.drive.navigation.traffic.TrafficCardV2$1  reason: invalid class name */
    /* loaded from: classes7.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition = new int[TrafficCondition.values().length];

        static {
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.GOOD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.SLUGGISH.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.SLOW.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.EXTREMELY_SLOW.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.VERY_SLOW.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$alexa$drive$navigation$traffic$TrafficCardV2$TrafficCondition[TrafficCondition.NOT_AVAILABLE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* loaded from: classes7.dex */
    public interface TrafficCardCallback {
        void onNavigationClicked(SavedLocations.Item item);

        void onTrafficCardUpdated();

        void onTrafficUpdateRequested(String str, String str2);
    }

    /* loaded from: classes7.dex */
    public enum TrafficCondition {
        SLUGGISH,
        GOOD,
        SLOW,
        EXTREMELY_SLOW,
        VERY_SLOW,
        NOT_AVAILABLE
    }

    public TrafficCardV2(ContextThemeWrapper contextThemeWrapper, String str, String str2, Drawable drawable, SavedLocations.Item item, Location location, String str3, @NonNull TrafficCardCallback trafficCardCallback, DriveModeThemeManager driveModeThemeManager) {
        super(str, str2, drawable);
        this.mContext = contextThemeWrapper;
        this.mItem = item;
        this.mStartLocation = location;
        this.mTrafficCardCallback = trafficCardCallback;
        this.mTrafficCardType = str3;
        this.mHandler = new Handler();
        this.mDriveModeThemeManager = driveModeThemeManager;
    }

    private String getTrafficCardString(TrafficCondition trafficCondition) {
        int ordinal = trafficCondition.ordinal();
        if (ordinal != 0) {
            if (ordinal == 1) {
                return this.mContext.getString(R.string.traffic_string_good);
            }
            if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
                return this.mContext.getString(R.string.traffic_string_heavy);
            }
            if (ordinal != 5) {
            }
            return "";
        }
        return this.mContext.getString(R.string.traffic_string_sluggish);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onStaleData() {
        this.isDataStale = true;
        setSubtitle(this.mContext.getString(R.string.traffic_default_status));
        setSubtitleAddendum(null);
        this.mTrafficCardCallback.onTrafficCardUpdated();
    }

    private void updateTrafficCardColorBasedOnTrafficCondition(TrafficCondition trafficCondition) {
        int ordinal = trafficCondition.ordinal();
        if (ordinal == 0) {
            setSubtitleAddendumColor(Integer.valueOf(this.mDriveModeThemeManager.getColorFromAttribute(this.mContext, R.attr.mosaicStatus30)));
        } else if (ordinal == 1) {
            setSubtitleAddendumColor(Integer.valueOf(this.mDriveModeThemeManager.getColorFromAttribute(this.mContext, R.attr.mosaicStatus40)));
        } else if (ordinal == 2 || ordinal == 3 || ordinal == 4) {
            setSubtitleAddendumColor(Integer.valueOf(this.mDriveModeThemeManager.getColorFromAttribute(this.mContext, R.attr.mosaicStatus10)));
        } else if (ordinal != 5) {
        } else {
            setSubtitleAddendumColor(null);
        }
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public DriveModeCard.CardDomain getCardDomain() {
        return DriveModeCard.CardDomain.NAVIGATION;
    }

    @Override // com.amazon.alexa.drivemode.api.DriveModeCard
    public int getCardId() {
        return 256;
    }

    public SavedLocations.Item getItem() {
        return this.mItem;
    }

    public String getTrafficCardType() {
        return this.mTrafficCardType;
    }

    @Override // com.amazon.alexa.drivemode.api.SingleIconCardV2
    public void onCardClicked() {
        SavedLocations.Item item = this.mItem;
        if (item == null) {
            return;
        }
        this.mTrafficCardCallback.onNavigationClicked(item);
    }

    @Override // com.amazon.alexa.drive.navigation.CurrentLocationManager.LocationChangeListener
    public void onLocationChanges(Location location) {
        Location location2;
        if (location != null && (location2 = this.mStartLocation) != null) {
            if (location2.distanceTo(location) < STALE_DATA_DISTANCE_METERS) {
                return;
            }
            onStaleData();
            return;
        }
        Log.e(TAG, "onLocationChanges | location update is null or start location is null ");
    }

    public void onTrafficDataUpdated(@NonNull Integer num, @NonNull String str, @Nullable Integer num2) {
        TrafficCondition trafficCondition;
        this.isDataStale = false;
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.alexa.drive.navigation.traffic.-$$Lambda$TrafficCardV2$-j5U7Z_QGQIYCZ0D8LIYnBc8usw
            @Override // java.lang.Runnable
            public final void run() {
                TrafficCardV2.this.onStaleData();
            }
        }, FeatureConstants.SESSION_CHANGE_THRESHOLD_IN_MILLISECONDS);
        try {
            trafficCondition = TrafficCondition.valueOf(str);
        } catch (IllegalArgumentException unused) {
            Log.e(TAG, "unable to get enum value");
            trafficCondition = TrafficCondition.NOT_AVAILABLE;
        }
        long intValue = num.intValue();
        long hours = TimeUnit.SECONDS.toHours(intValue);
        long seconds = (int) (intValue - TimeUnit.HOURS.toSeconds(hours));
        long minutes = TimeUnit.SECONDS.toMinutes(seconds);
        if (((int) (seconds - TimeUnit.MINUTES.toSeconds(minutes))) > 0) {
            minutes++;
        }
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours);
            sb.append(this.mContext.getString(R.string.dm_traffic_hour));
            sb.append(" ");
        }
        sb.append(minutes);
        sb.append(this.mContext.getString(R.string.dm_traffic_minute));
        if (!trafficCondition.equals(TrafficCondition.NOT_AVAILABLE)) {
            sb.append(" · ");
            setSubtitleAddendum(getTrafficCardString(trafficCondition));
        }
        setSubtitle(sb.toString());
        updateTrafficCardColorBasedOnTrafficCondition(trafficCondition);
        this.mTrafficCardCallback.onTrafficCardUpdated();
    }

    public void setItem(SavedLocations.Item item) {
        this.mItem = item;
    }
}
