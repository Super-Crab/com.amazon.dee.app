package com.amazon.alexa.handsfree.metrics.amok;

import android.content.Context;
import android.database.Cursor;
import androidx.annotation.NonNull;
import com.amazon.alexa.handsfree.devices.AMPDInformationProvider;
import com.amazon.alexa.handsfree.metrics.events.MobilyticsUserInteractionEventWrapper;
import com.amazon.alexa.handsfree.protocols.dependencies.AhfComponentsProvider;
import com.amazon.alexa.handsfree.protocols.dependencies.FalcoProtocolComponent;
import com.amazon.alexa.handsfree.protocols.metrics.Metric;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsConfiguration;
import com.amazon.alexa.handsfree.protocols.metrics.MetricsEmitter;
import com.amazon.alexa.handsfree.protocols.metrics.PermissionResult;
import com.amazon.alexa.handsfree.protocols.metrics.factories.MetricFactoryProvider;
import com.amazon.alexa.handsfree.protocols.metricsprovider.AlexaMetricsContract;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.enrollmenttype.EnrollmentTypeResolver;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.ClickMetricMetadata;
import com.amazon.alexa.handsfree.protocols.uservoicerecognition.metrics.VoiceTrainingMetricMetadata;
import com.amazon.alexa.handsfree.protocols.utils.Log;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class VoiceAppMetricsEmitter {
    private static final String HANDSFREE_SETUP = "HANDSFREE_SETUP";
    private static final String INTENTION = "INTENTION";
    private static final String NONE = "NONE";
    private static final int SECONDS_TO_MILLIS_CONVERSION = 1000;
    private static final String SETUP_COMPLETE = "SETUP_COMPLETE";
    private static final String SHOW_ON_LOCK_SCREEN_OFF = "SHOW_ON_LOCK_SCREEN_OFF";
    private static final String SHOW_ON_LOCK_SCREEN_ON = "SHOW_ON_LOCK_SCREEN_ON";
    private static final String TAG = "VoiceAppMetricsEmitter";
    private static final long UNIX_MILLIS_JAN_01_2000 = 946684800000L;
    private static final String VENDOR_EVENT_ALEXA_ALLOW_RECORD_AUDIO = "ALEXA_ALLOW_RECORD_AUDIO";
    private static final String VENDOR_EVENT_ALEXA_APP_SETUP_HANDS_FREE_SETUP_COMPLETE_SEE_PAGE = "ALEXA_APP_SETUP_HANDS_FREE_SETUP_COMPLETE_SEE_PAGE";
    private static final String VOICE_APP_AUDIO_PERMISSIONS = "VOICE_APP_AUDIO_PERMISSIONS";
    private final AMPDInformationProvider mAMPDInformationProvider;
    private final Context mContext;
    private final Lazy<EnrollmentTypeResolver> mEnrollmentTypeResolverLazy;
    private final MetricFactoryProvider mMetricFactoryProvider;
    private final List<Metric> mMetrics;
    private final MetricsEmitter mMetricsEmitter;

    public VoiceAppMetricsEmitter(@NonNull Context context) {
        this.mContext = context;
        this.mAMPDInformationProvider = AMPDInformationProvider.getInstance(context);
        this.mEnrollmentTypeResolverLazy = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).enrollmentTypeResolverLazy();
        MetricsConfiguration metricsConfiguration = ((FalcoProtocolComponent) AhfComponentsProvider.getComponent(context, FalcoProtocolComponent.class)).metricsConfiguration();
        this.mMetricFactoryProvider = metricsConfiguration.getMetricFactoryProvider();
        this.mMetricsEmitter = metricsConfiguration.getMetricsEmitter();
        this.mMetrics = new ArrayList();
    }

    private void addClickMetric(@NonNull Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_CLICK_COMPONENT));
        String string2 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_CLICK_PAGE_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_CLICK_ACTION));
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("addClickMetric: ");
        sb.append(j);
        sb.append(", ");
        sb.append(string);
        GeneratedOutlineSupport1.outline181(sb, ", ", string2, ", ", string3);
        sb.append(", ");
        Log.d(str, sb.toString());
        addMetric(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(TAG, string, string2, string3), j);
    }

    private void addFirstStartupMetric(@NonNull Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String str = TAG;
        Log.d(str, "addFirstStartupMetric: , " + j);
        addMetric(this.mMetricFactoryProvider.getOperationalMetricFactory().buildFirstStartupMetric(TAG), j);
    }

    private void addNotificationClickMetric(@NonNull Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_COMPONENT));
        String string2 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_PAGE_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_SUB_PAGE_TYPE));
        String string4 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_NOTIFICATION_TITLE));
        String string5 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_NOTIFICATION_TEXT));
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("addNotificationEventMetric: ");
        sb.append(j);
        sb.append(", ");
        sb.append(string);
        GeneratedOutlineSupport1.outline181(sb, ", ", string3, ", ", string2);
        Log.d(str, GeneratedOutlineSupport1.outline93(sb, ", ", string4, ", ", string5));
        addMetric(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(TAG, string, string2, string3), j);
        addMetric(this.mMetricFactoryProvider.getNotificationMetricFactory().buildNotificationMetadataMetric(string4, string5), j);
    }

    private void addNotificationEventMetric(@NonNull Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_COMPONENT));
        String string2 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_PAGE_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_NOTIFICATION_TITLE));
        String string4 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_NOTIFICATION_TEXT));
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("addNotificationEventMetric: ");
        sb.append(j);
        sb.append(", ");
        sb.append(string);
        GeneratedOutlineSupport1.outline181(sb, ", ", string2, ", ", string3);
        sb.append(", ");
        sb.append(string4);
        Log.d(str, sb.toString());
        addMetric(this.mMetricFactoryProvider.getNotificationMetricFactory().buildNotificationMetric(TAG, string, string2), j);
        addMetric(this.mMetricFactoryProvider.getNotificationMetricFactory().buildNotificationMetadataMetric(string3, string4), j);
    }

    private void addSetupMetric(@NonNull Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_SETUP_EVENT_TYPE));
        String string2 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_SETUP_ACTION_TYPE));
        String string3 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_SETUP_COMPONENT));
        String string4 = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_SETUP_PAGE_TYPE));
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("addSetupMetric: ");
        sb.append(j);
        sb.append(", ");
        sb.append(string);
        GeneratedOutlineSupport1.outline181(sb, ", ", string3, ", ", string2);
        sb.append(", ");
        sb.append(string4);
        Log.d(str, sb.toString());
        addMetric(new MobilyticsUserInteractionEventWrapper(string, "click", string3, string4).withInputType2(string2), j);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void addVendorMetric(@NonNull Cursor cursor) {
        char c;
        long j = cursor.getLong(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_DATE));
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_VENDOR_EVENT));
        Log.d(TAG, "addVendorMetric: " + j + ", " + string);
        switch (string.hashCode()) {
            case -796050566:
                if (string.equals(VENDOR_EVENT_ALEXA_ALLOW_RECORD_AUDIO)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 288961052:
                if (string.equals(SHOW_ON_LOCK_SCREEN_ON)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 367857842:
                if (string.equals(SHOW_ON_LOCK_SCREEN_OFF)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 765654083:
                if (string.equals(VENDOR_EVENT_ALEXA_APP_SETUP_HANDS_FREE_SETUP_COMPLETE_SEE_PAGE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            String voiceAppPackageName = this.mAMPDInformationProvider.getVoiceAppPackageName();
            if (voiceAppPackageName == null) {
                Log.e(TAG, "addVendorMetric: Voice app not found");
            } else {
                addMetric(this.mMetricFactoryProvider.getUserActionMetricFactory().buildPermissionResultMetric(TAG, voiceAppPackageName, VOICE_APP_AUDIO_PERMISSIONS, "android.permission.RECORD_AUDIO", PermissionResult.ALLOW.name(), this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType().name()), j);
            }
        } else if (c == 1) {
            addMetric(this.mMetricFactoryProvider.getVoiceTrainingMetricsFactory().buildVoiceTrainingMetric(TAG, VoiceTrainingMetricMetadata.PageType.TRAINING_FINISH.name(), VoiceTrainingMetricMetadata.SubPageType.DONE_BUTTON.name(), VoiceTrainingMetricMetadata.EventType.CLICK.name(), this.mEnrollmentTypeResolverLazy.mo358get().getSpeakerVerificationEnrollmentType().name()), j);
            addMetric(new MobilyticsUserInteractionEventWrapper(SETUP_COMPLETE, "click", HANDSFREE_SETUP, "NONE").withInputType2(INTENTION), j);
        } else if (c == 2) {
            addMetric(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(TAG, ClickMetricMetadata.Component.UVR.name(), ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN.name(), ClickMetricMetadata.Action.ENABLE.name()), j);
        } else if (c != 3) {
            addMetric(this.mMetricFactoryProvider.getVendorEventMetricFactory().newVendorEventMetric(TAG, string), j);
        } else {
            addMetric(this.mMetricFactoryProvider.getUserActionMetricFactory().buildClickMetric(TAG, ClickMetricMetadata.Component.UVR.name(), ClickMetricMetadata.PageType.RESPOND_ON_LOCK_SCREEN.name(), ClickMetricMetadata.Action.DISABLE.name()), j);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void addMetric(@NonNull Cursor cursor) {
        char c;
        String string = cursor.getString(cursor.getColumnIndex(AlexaMetricsContract.AlexaMetricsTable.COLUMN_METRIC_TYPE));
        switch (string.hashCode()) {
            case -2126103444:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_FRO)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1573539374:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_SETUP)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -1541550969:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_VENDOR)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 443728699:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_NOTIFICATION_CLICK)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 796564969:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_NOTIFICATION_EVENT)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1174320231:
                if (string.equals(AlexaMetricsContract.AlexaMetricsTable.METRIC_TYPE_CLICK)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            addFirstStartupMetric(cursor);
        } else if (c == 1) {
            addClickMetric(cursor);
        } else if (c == 2) {
            addSetupMetric(cursor);
        } else if (c == 3) {
            addVendorMetric(cursor);
        } else if (c == 4) {
            addNotificationEventMetric(cursor);
        } else if (c != 5) {
            Log.w(TAG, "addMetric: unknown metric type");
        } else {
            addNotificationClickMetric(cursor);
        }
    }

    public void emit() {
        this.mMetricsEmitter.recordMetrics(this.mContext, this, (Metric[]) this.mMetrics.toArray(new Metric[0]));
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("emit: Emitting ");
        outline107.append(this.mMetrics.size());
        outline107.append(" metrics");
        Log.d(str, outline107.toString());
        this.mMetrics.clear();
    }

    private void addMetric(@NonNull Metric metric, long j) {
        if (metric instanceof MobilyticsEvent) {
            MobilyticsEvent mobilyticsEvent = (MobilyticsEvent) metric;
            if (j < UNIX_MILLIS_JAN_01_2000) {
                j *= 1000;
            }
            mobilyticsEvent.setEventTimestamp(j);
            this.mMetrics.add(metric);
            String str = TAG;
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("addMetric: ");
            outline107.append(mobilyticsEvent.getEventTimestamp());
            Log.d(str, outline107.toString());
        }
    }
}
