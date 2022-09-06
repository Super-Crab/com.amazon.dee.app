package com.amazon.alexa.accessory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
/* loaded from: classes.dex */
public class DefaultAccessoryServiceConfigurationSupplier implements AccessoryServiceConfigurationSupplier {
    private final List<String> blockedRfcommNamePrefixes;
    @StringRes
    private final int companionDeviceNotificationBodyTextRes;
    @ColorRes
    private final int companionDeviceNotificationColorRes;
    @DrawableRes
    private final int companionDeviceNotificationLargeIconRes;
    @DrawableRes
    private final int companionDeviceNotificationSmallIconRes;
    @StringRes
    private final int companionDeviceNotificationTitleTextRes;
    private final Context context;
    @StringRes
    private final int highPriorityChannelDescriptionRes;
    private final String highPriorityChannelId;
    @StringRes
    private final int highPriorityChannelNameRes;
    @StringRes
    private final int lowPriorityChannelDescriptionRes;
    private final String lowPriorityChannelId;
    @StringRes
    private final int lowPriorityChannelNameRes;
    @ColorRes
    private final int notificationColorRes;
    @PluralsRes
    private final int notificationContentTextRes;
    @StringRes
    private final int notificationContentTitleRes;
    @DrawableRes
    private final int notificationLargeIconRes;
    @DrawableRes
    private final int notificationSmallIconRes;
    private final boolean shouldExcludeInquirySession;
    private final boolean shouldShowHighPriorityChannelBadge;
    private final boolean shouldShowLowPriorityChannelBadge;
    private final boolean showCompanionDeviceNotification;
    private final boolean showCompanionDeviceNotificationBeforeSetupComplete;
    private final int showCompanionDeviceNotificationForApiLevelAndAbove;

    /* loaded from: classes.dex */
    public static final class Builder {
        @StringRes
        private int companionDeviceNotificationBodyTextRes;
        @ColorRes
        private int companionDeviceNotificationColorRes;
        @DrawableRes
        int companionDeviceNotificationLargeIconRes;
        @DrawableRes
        int companionDeviceNotificationSmallIconRes;
        @StringRes
        private int companionDeviceNotificationTitleTextRes;
        private Context context;
        @StringRes
        private int highPriorityChannelDescriptionRes;
        private String highPriorityChannelId;
        @StringRes
        private int highPriorityChannelNameRes;
        @StringRes
        private int lowPriorityChannelDescriptionRes;
        private String lowPriorityChannelId;
        @StringRes
        private int lowPriorityChannelNameRes;
        @ColorRes
        private int notificationColorRes;
        @PluralsRes
        private int notificationContentTextRes;
        @StringRes
        private int notificationContentTitleRes;
        @DrawableRes
        private int notificationLargeIconRes;
        @DrawableRes
        private int notificationSmallIconRes;
        private boolean showCompanionDeviceNotification;
        private boolean showCompanionDeviceNotificationBeforeSetupComplete;
        private int showCompanionDeviceNotificationForApiLevelAndAbove;
        private boolean shouldShowHighPriorityChannelBadge = true;
        private boolean shouldShowLowPriorityChannelBadge = true;
        private List<String> blockedRfcommNamePrefixes = Collections.emptyList();
        private boolean shouldExcludeInquirySession = false;

        public Builder blockedRfcommNamePrefixes(@NonNull List<String> list) {
            this.blockedRfcommNamePrefixes = list;
            return this;
        }

        public AccessoryServiceConfigurationSupplier build() {
            Preconditions.notNull(this.context, "context");
            return new DefaultAccessoryServiceConfigurationSupplier(this);
        }

        public Builder companionDeviceNotificationBodyTextRes(@StringRes int i) {
            this.companionDeviceNotificationBodyTextRes = i;
            return this;
        }

        public Builder companionDeviceNotificationColorRes(@ColorRes int i) {
            this.companionDeviceNotificationColorRes = i;
            return this;
        }

        public Builder companionDeviceNotificationLargeIconRes(@DrawableRes int i) {
            this.companionDeviceNotificationLargeIconRes = i;
            return this;
        }

        public Builder companionDeviceNotificationSmallIconRes(@DrawableRes int i) {
            this.companionDeviceNotificationSmallIconRes = i;
            return this;
        }

        public Builder companionDeviceNotificationTitleTextRes(@StringRes int i) {
            this.companionDeviceNotificationTitleTextRes = i;
            return this;
        }

        public Builder context(Context context) {
            this.context = context;
            return this;
        }

        public Builder highPriorityChannelDescriptionRes(int i) {
            this.highPriorityChannelDescriptionRes = i;
            return this;
        }

        public Builder highPriorityChannelId(String str) {
            this.highPriorityChannelId = str;
            return this;
        }

        public Builder highPriorityChannelNameRes(int i) {
            this.highPriorityChannelNameRes = i;
            return this;
        }

        public Builder lowPriorityChannelDescriptionRes(int i) {
            this.lowPriorityChannelDescriptionRes = i;
            return this;
        }

        public Builder lowPriorityChannelId(String str) {
            this.lowPriorityChannelId = str;
            return this;
        }

        public Builder lowPriorityChannelNameRes(int i) {
            this.lowPriorityChannelNameRes = i;
            return this;
        }

        public Builder notificationColorRes(@ColorRes int i) {
            this.notificationColorRes = i;
            return this;
        }

        public Builder notificationContentTextRes(@PluralsRes int i) {
            this.notificationContentTextRes = i;
            return this;
        }

        public Builder notificationContentTitleRes(@StringRes int i) {
            this.notificationContentTitleRes = i;
            return this;
        }

        public Builder notificationLargeIconRes(@DrawableRes int i) {
            this.notificationLargeIconRes = i;
            return this;
        }

        public Builder notificationSmallIconRes(@DrawableRes int i) {
            this.notificationSmallIconRes = i;
            return this;
        }

        public Builder shouldExcludeInquirySession(boolean z) {
            this.shouldExcludeInquirySession = z;
            return this;
        }

        public Builder shouldShowHighPriorityChannelBadge(boolean z) {
            this.shouldShowHighPriorityChannelBadge = z;
            return this;
        }

        public Builder shouldShowLowPriorityChannelBadge(boolean z) {
            this.shouldShowLowPriorityChannelBadge = z;
            return this;
        }

        public Builder showCompanionDeviceNotification(boolean z) {
            this.showCompanionDeviceNotification = z;
            return this;
        }

        public Builder showCompanionDeviceNotificationBeforeSetupComplete(boolean z) {
            this.showCompanionDeviceNotificationBeforeSetupComplete = z;
            return this;
        }

        public Builder showCompanionDeviceNotificationForApiLevelAndAbove(int i) {
            this.showCompanionDeviceNotificationForApiLevelAndAbove = i;
            return this;
        }
    }

    public DefaultAccessoryServiceConfigurationSupplier(Builder builder) {
        this.context = builder.context;
        this.notificationSmallIconRes = builder.notificationSmallIconRes;
        this.notificationLargeIconRes = builder.notificationLargeIconRes;
        this.notificationColorRes = builder.notificationColorRes;
        this.notificationContentTitleRes = builder.notificationContentTitleRes;
        this.notificationContentTextRes = builder.notificationContentTextRes;
        this.companionDeviceNotificationTitleTextRes = builder.companionDeviceNotificationTitleTextRes;
        this.companionDeviceNotificationBodyTextRes = builder.companionDeviceNotificationBodyTextRes;
        this.companionDeviceNotificationColorRes = builder.companionDeviceNotificationColorRes;
        this.companionDeviceNotificationSmallIconRes = builder.companionDeviceNotificationSmallIconRes;
        this.companionDeviceNotificationLargeIconRes = builder.companionDeviceNotificationLargeIconRes;
        this.showCompanionDeviceNotificationBeforeSetupComplete = builder.showCompanionDeviceNotificationBeforeSetupComplete;
        this.showCompanionDeviceNotification = builder.showCompanionDeviceNotification;
        this.showCompanionDeviceNotificationForApiLevelAndAbove = builder.showCompanionDeviceNotificationForApiLevelAndAbove;
        this.highPriorityChannelId = builder.highPriorityChannelId;
        this.highPriorityChannelNameRes = builder.highPriorityChannelNameRes;
        this.highPriorityChannelDescriptionRes = builder.highPriorityChannelDescriptionRes;
        this.shouldShowHighPriorityChannelBadge = builder.shouldShowHighPriorityChannelBadge;
        this.lowPriorityChannelId = builder.lowPriorityChannelId;
        this.lowPriorityChannelNameRes = builder.lowPriorityChannelNameRes;
        this.lowPriorityChannelDescriptionRes = builder.lowPriorityChannelDescriptionRes;
        this.shouldShowLowPriorityChannelBadge = builder.shouldShowLowPriorityChannelBadge;
        this.blockedRfcommNamePrefixes = builder.blockedRfcommNamePrefixes;
        this.shouldExcludeInquirySession = builder.shouldExcludeInquirySession;
    }

    private static Bitmap getBitmapFromVectorDrawable(@NonNull Context context, @DrawableRes int i) {
        Drawable drawable = ContextCompat.getDrawable(context, i);
        Bitmap createBitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public List<String> blockedRfcommNamePrefixes() {
        return this.blockedRfcommNamePrefixes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence getCompanionDeviceNotificationBodyText(String str, String str2) {
        return String.format(Locale.US, this.context.getString(this.companionDeviceNotificationBodyTextRes), str, str2);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int getCompanionDeviceNotificationColor() {
        return ContextCompat.getColor(this.context, this.companionDeviceNotificationColorRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @Nullable
    public Bitmap getCompanionDeviceNotificationLargeIcon() {
        if (getNotificationLargeIconRes() != 0) {
            return getBitmapFromVectorDrawable(this.context, getCompanionDeviceNotificationLargeIconRes());
        }
        return null;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int getCompanionDeviceNotificationLargeIconRes() {
        return this.companionDeviceNotificationLargeIconRes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int getCompanionDeviceNotificationSmallIconRes() {
        return this.companionDeviceNotificationSmallIconRes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence getCompanionDeviceNotificationTitleText(String str, String str2) {
        return String.format(Locale.US, this.context.getString(this.companionDeviceNotificationTitleTextRes), str, str2);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @ColorInt
    public int getNotificationColor() {
        return ContextCompat.getColor(this.context, this.notificationColorRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence getNotificationContentText(int i) {
        return this.context.getResources().getQuantityString(this.notificationContentTextRes, i, Integer.valueOf(i));
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence getNotificationContentTitle() {
        return this.context.getString(this.notificationContentTitleRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public Bitmap getNotificationLargeIcon() {
        if (getNotificationLargeIconRes() != 0) {
            return getBitmapFromVectorDrawable(this.context, getNotificationLargeIconRes());
        }
        return null;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int getNotificationLargeIconRes() {
        return this.notificationLargeIconRes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int getNotificationSmallIcon() {
        return this.notificationSmallIconRes;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence highPriorityChannelDescription() {
        return this.context.getString(this.highPriorityChannelDescriptionRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public String highPriorityChannelId() {
        return this.highPriorityChannelId;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence highPriorityChannelName() {
        return this.context.getString(this.highPriorityChannelNameRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence lowPriorityChannelDescription() {
        return this.context.getString(this.lowPriorityChannelDescriptionRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public String lowPriorityChannelId() {
        return this.lowPriorityChannelId;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    @NonNull
    public CharSequence lowPriorityChannelName() {
        return this.context.getString(this.lowPriorityChannelNameRes);
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public boolean shouldExcludeInquirySession() {
        return this.shouldExcludeInquirySession;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public boolean shouldShowHighPriorityChannelBadge() {
        return this.shouldShowHighPriorityChannelBadge;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public boolean shouldShowLowPriorityChannelBadge() {
        return this.shouldShowLowPriorityChannelBadge;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public boolean showCompanionDeviceNotification() {
        return this.showCompanionDeviceNotification;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public boolean showCompanionDeviceNotificationBeforeSetupComplete() {
        return this.showCompanionDeviceNotificationBeforeSetupComplete;
    }

    @Override // com.amazon.alexa.accessory.AccessoryServiceConfigurationSupplier
    public int showCompanionDeviceNotificationForApiLevelAndAbove() {
        return this.showCompanionDeviceNotificationForApiLevelAndAbove;
    }

    public DefaultAccessoryServiceConfigurationSupplier(@NonNull Context context) {
        this(new Builder().context(context).notificationSmallIconRes(R.drawable.amazon_avs_alexaicon).notificationColorRes(R.color.notification_color).notificationContentTitleRes(R.string.accessory_service_notification_title).notificationContentTextRes(R.plurals.accessory_service_notification_content_active_sessions).companionDeviceNotificationTitleTextRes(R.string.companion_device_primer_title).companionDeviceNotificationBodyTextRes(R.string.companion_device_primer_text).showCompanionDeviceNotification(true).showCompanionDeviceNotificationBeforeSetupComplete(false).companionDeviceNotificationSmallIconRes(R.drawable.amazon_avs_alexaicon).companionDeviceNotificationLargeIconRes(R.drawable.amazon_avs_alexaicon).companionDeviceNotificationColorRes(R.color.notification_color).showCompanionDeviceNotificationForApiLevelAndAbove(29).lowPriorityChannelId(AccessoryService.NOTIFICATION_CHANNEL_ID).lowPriorityChannelNameRes(R.string.accessory_service_channel).lowPriorityChannelDescriptionRes(R.string.accessory_service_channel_description).highPriorityChannelId(AccessoryService.NOTIFICATION_CHANNEL_ID_HIGH).highPriorityChannelNameRes(R.string.accessory_service_channel_high).highPriorityChannelDescriptionRes(R.string.accessory_service_channel_high_description));
    }
}
