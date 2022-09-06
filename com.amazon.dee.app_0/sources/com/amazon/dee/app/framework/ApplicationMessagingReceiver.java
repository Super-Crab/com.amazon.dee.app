package com.amazon.dee.app.framework;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import com.amazon.alexa.featureservice.api.FeatureServiceV2;
import com.amazon.alexa.featureservice.data.registry.NativeFeatureRegistry;
import com.amazon.alexa.imageloader.GlideApp;
import com.amazon.alexa.mobilytics.Mobilytics;
import com.amazon.alexa.mobilytics.event.userinteraction.MobilyticsUserInteractionEvent;
import com.amazon.alexa.protocols.environment.EnvironmentService;
import com.amazon.alexa.protocols.messaging.Message;
import com.amazon.alexa.protocols.messaging.MessagingReceiver;
import com.amazon.dee.app.R;
import com.amazon.dee.app.services.logging.Log;
import com.amazon.dee.app.services.messaging.MessagingHandler;
import com.amazon.dee.app.services.metrics.AlexaMetricsConstants;
import com.amazon.dee.app.ui.external.ExternalUIActivity;
import com.amazon.dee.app.ui.main.MainActivity;
import com.amazon.dee.app.ui.web.WebConstants;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.bumptech.glide.load.resource.bitmap.DownsampleStrategy;
import com.bumptech.glide.request.FutureTarget;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.UnmodifiableIterator;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.joda.time.DateTimeConstants;
/* loaded from: classes12.dex */
public class ApplicationMessagingReceiver implements MessagingReceiver {
    private static final String DEPRECATED_CHANNEL_ID = "ALEXA_CHANNEL";
    static final String LAUNCH_THIRD_PARTY_URL_WITH_IN_APP_BROWSER_TAB = "launchThirdPartyURLWithInAppBrowserTab";
    private static final String NOTIFICATION_CHANNEL_ID = "ALEXA_CHANNEL_HIGH";
    static final String TAG = Log.tag(ApplicationMessagingReceiver.class);
    private final Context context;
    private final Lazy<EnvironmentService> environmentService;
    private final Lazy<FeatureServiceV2> featureServiceV2;
    private final Lazy<Mobilytics> mobilytics;
    private final AtomicInteger notificationId = new AtomicInteger();

    public ApplicationMessagingReceiver(Context context, Lazy<Mobilytics> lazy, Lazy<EnvironmentService> lazy2, Lazy<FeatureServiceV2> lazy3) {
        this.context = context;
        this.mobilytics = lazy;
        this.environmentService = lazy2;
        this.featureServiceV2 = lazy3;
        int i = Build.VERSION.SDK_INT;
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        int i = Build.VERSION.SDK_INT;
        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, this.context.getString(R.string.alexa_notification_channel_name), 4);
        notificationChannel.setDescription(this.context.getString(R.string.alexa_notification_channel_description));
        NotificationManager notificationManager = (NotificationManager) this.context.getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            if (notificationManager.getNotificationChannel(DEPRECATED_CHANNEL_ID) != null) {
                notificationManager.deleteNotificationChannel(DEPRECATED_CHANNEL_ID);
            }
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private Bitmap getImageFromUrl(String str) {
        recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_CONTAINS_MEDIA_PERCENT, TextUtils.isEmpty(str));
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        FutureTarget<Bitmap> submit = GlideApp.with(this.context).mo1635asBitmap().override(DateTimeConstants.MINUTES_PER_DAY).downsample(DownsampleStrategy.CENTER_INSIDE).mo6762load(str).submit();
        try {
            Bitmap bitmap = submit.get();
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_MEDIA_FETCH_SUCCESS_PERCENT, true);
            return bitmap;
        } catch (Exception e) {
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_MEDIA_FETCH_SUCCESS_PERCENT, false);
            Log.e(TAG, "Error retrieving image", e);
            return null;
        } finally {
            GlideApp.with(this.context).clear(submit);
        }
    }

    private void recordEvent(@NonNull String str, Bundle bundle) {
        MobilyticsUserInteractionEvent createUserInteractionEvent = this.mobilytics.mo358get().createUserInteractionEvent(str, "view", "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_UI_BUILDER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
        createUserInteractionEvent.setChannelName("coreapp");
        MessagingHandler.extractAndSetMetricsPayload(createUserInteractionEvent, bundle);
        this.mobilytics.mo358get().recordUserInteractionEvent(createUserInteractionEvent);
    }

    private void recordPercentOccurrence(@NonNull String str, boolean z) {
        this.mobilytics.mo358get().recordPercentOccurrence(str, z, "PushNotifications", AlexaMetricsConstants.MetricsComponents.NOTIFICATIONS_UI_BUILDER_SUBCOMPONENT, "1235005e-4e8f-4ef2-82bc-34157415015b");
    }

    Intent buildIntent(Bundle bundle) {
        String string = bundle.getString(EventBusMessagingReceiver.NOTIFICATION_URL_TYPE_ALEXA);
        if (!TextUtils.isEmpty(string)) {
            Uri parse = Uri.parse(string);
            if (this.environmentService.mo358get().isWebTivUrl(parse)) {
                ImmutableList of = ImmutableList.of("/a/c/r", "/gp/aw/help/ref=", "/gp/help/customer/display.html/ref=");
                ArrayList<String> arrayList = new ArrayList<>();
                UnmodifiableIterator mo8029iterator = of.mo8029iterator();
                while (mo8029iterator.hasNext()) {
                    arrayList.add(parse.getAuthority() + ((String) mo8029iterator.next()));
                }
                return new Intent(this.context, ExternalUIActivity.class).setAction("android.intent.action.VIEW").putExtra("android.intent.extra.TEXT", parse.toString()).putExtra("BRIDGE_ACTION_KEY", LAUNCH_THIRD_PARTY_URL_WITH_IN_APP_BROWSER_TAB).putStringArrayListExtra(WebConstants.ExternalUIConstants.HOST_ALLOWLIST_KEY, arrayList);
            }
            return new Intent(this.context, MainActivity.class).setAction("android.intent.action.VIEW").setData(parse);
        }
        String string2 = bundle.getString(EventBusMessagingReceiver.NOTIFICATION_URL_TYPE_DEEP_LINK);
        if (!TextUtils.isEmpty(string2)) {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(string2));
            if (!this.context.getPackageManager().queryIntentActivities(intent, 65536).isEmpty()) {
                return intent;
            }
        }
        String string3 = bundle.getString(EventBusMessagingReceiver.NOTIFICATION_URL_TYPE_STORE);
        if (!TextUtils.isEmpty(string3)) {
            return new Intent("android.intent.action.VIEW", Uri.parse(string3));
        }
        String string4 = bundle.getString(EventBusMessagingReceiver.NOTIFICATION_URL_TYPE_WEB);
        if (TextUtils.isEmpty(string4)) {
            return null;
        }
        return new Intent("android.intent.action.VIEW", Uri.parse(string4));
    }

    @Override // com.amazon.alexa.protocols.messaging.MessagingReceiver
    public void onReceive(@NonNull Message message) {
        Bundle bundle = message.get();
        String str = "Received message: " + bundle;
        String string = bundle.getString("title");
        String string2 = bundle.getString("text");
        if (TextUtils.isEmpty(string2)) {
            String str2 = "Ignoring empty push message: " + bundle;
            return;
        }
        if (TextUtils.isEmpty(string)) {
            string = string2;
            string2 = null;
        }
        recordEvent(AlexaMetricsConstants.MetricEvents.NOTIFICATION_BANNER_DISPLAY, bundle);
        NotificationCompat.Builder smallIcon = new NotificationCompat.Builder(this.context).setContentTitle(string).setContentText(string2).setSmallIcon(R.drawable.ic_alexa_white);
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("android.resource://");
        outline107.append(this.context.getPackageName());
        outline107.append("/");
        outline107.append(R.raw.notification_received);
        NotificationCompat.Builder priority = smallIcon.setSound(Uri.parse(outline107.toString())).setAutoCancel(true).setPriority(4);
        boolean hasAccess = this.featureServiceV2.mo358get().hasAccess(NativeFeatureRegistry.RICH_NOTIFICATIONS_IMAGE_ATTACHMENTS, false);
        if (hasAccess) {
            String string3 = bundle.getString("imageAltText");
            Optional ofNullable = Optional.ofNullable(getImageFromUrl(bundle.getString("imageUrl")));
            if (ofNullable.isPresent()) {
                recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_USED_ALT_TEXT_PERCENT, false);
                priority.setLargeIcon((Bitmap) ofNullable.get()).setStyle(new NotificationCompat.BigPictureStyle().bigPicture((Bitmap) ofNullable.get()).bigLargeIcon(null));
            } else if (hasAccess && !TextUtils.isEmpty(string3)) {
                recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_USED_ALT_TEXT_PERCENT, true);
                priority.setContentText(string3).setStyle(new NotificationCompat.BigTextStyle().bigText(string3));
            } else {
                priority.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
            }
        } else {
            priority.setStyle(new NotificationCompat.BigTextStyle().bigText(string2));
        }
        int i = Build.VERSION.SDK_INT;
        priority.setChannelId(NOTIFICATION_CHANNEL_ID);
        Intent buildIntent = buildIntent(bundle);
        if (buildIntent != null) {
            buildIntent.setFlags(335544320);
            buildIntent.putExtra(MessagingReceiver.NOTIFICATION, true);
            buildIntent.putExtras(bundle);
            priority.setContentIntent(PendingIntent.getActivity(this.context, 0, buildIntent, 268435456));
        }
        try {
            NotificationManagerCompat.from(this.context).notify(this.notificationId.incrementAndGet(), priority.build());
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_SUBMIT_SUCCESS_PERCENT, true);
        } catch (RuntimeException e) {
            recordPercentOccurrence(AlexaMetricsConstants.MetricEvents.NOTIFICATION_SUBMIT_SUCCESS_PERCENT, false);
            Log.e(TAG, "Exception from NotificationManager when submitting notification", e);
        }
    }
}
