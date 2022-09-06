package com.amazon.alexa.mobilytics.connector;

import android.text.TextUtils;
import android.util.Pair;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.mobilytics.MobilyticsConfiguration;
import com.amazon.alexa.mobilytics.OwnerIdentifier;
import com.amazon.alexa.mobilytics.auth.CognitoCredentialsProvider;
import com.amazon.alexa.mobilytics.auth.CredentialsProvider;
import com.amazon.alexa.mobilytics.configuration.Endpoint;
import com.amazon.alexa.mobilytics.configuration.KinesisEndpoint;
import com.amazon.alexa.mobilytics.configuration.RecordChecker;
import com.amazon.alexa.mobilytics.configuration.Region;
import com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector;
import com.amazon.alexa.mobilytics.event.MobilyticsEvent;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.operational.DefaultMobilyticsOperationalEvent;
import com.amazon.alexa.mobilytics.event.operational.MobilyticsMetricsCounter;
import com.amazon.alexa.mobilytics.event.serializer.EventSerializer;
import com.amazon.alexa.mobilytics.event.serializer.ProtobufSerializer;
import com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler;
import com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler;
import com.amazon.alexa.mobilytics.identity.MobilyticsEndpointPicker;
import com.amazon.alexa.mobilytics.identity.MobilyticsUser;
import com.amazon.alexa.mobilytics.marketplace.Marketplace;
import com.amazon.alexa.mobilytics.protobuf.ApplicationProto;
import com.amazon.alexa.mobilytics.protobuf.ClientProto;
import com.amazon.alexa.mobilytics.protobuf.MobilyticsMessageProto;
import com.amazon.alexa.mobilytics.recorder.EventRecorder;
import com.amazon.alexa.mobilytics.recorder.KinesisEventRecorder;
import com.amazon.alexa.mobilytics.session.MobilyticsSession;
import com.amazon.alexa.mobilytics.util.Log;
import com.amazon.alexa.mobilytics.util.Utils;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.amazonaws.regions.Regions;
import com.android.tools.r8.GeneratedOutlineSupport1;
import com.google.common.base.Preconditions;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import rx.functions.Action1;
/* loaded from: classes9.dex */
public class DefaultKinesisConnector implements MobilyticsConnector {
    private static final long BACKGROUND_FLUSH_SIZE = 1000000;
    public static final String COMPONENT = "mobilytics";
    private static final String DEFAULT_REGION = "us-east-1";
    private static final String SUB_COMPONENT = "platform";
    private static final String TAG = Log.tag(DefaultKinesisConnector.class);
    private KinesisEndpoint activeEndpoint;
    private MobilyticsConfiguration configuration;
    private final CognitoCredentialsProvider.Factory credentialsProviderFactory;
    @VisibleForTesting
    public File debugLogFile;
    @VisibleForTesting
    public OutputStream debugLogStream;
    private final RecordChecker defaultRecordChecker;
    private MobilyticsEndpointPicker endpointPicker;
    private final Map<Regions, Endpoint> endpoints;
    private EventRecorder eventRecorder;
    private final EventRecorder.Factory eventRecorderFactory;
    private MobilyticsMetricsCounter failedRecordsCounter;
    private final String installationId;
    private JsonRecorder jsonRecorder;
    private String name;
    private ProtobufRecorder protobufRecorder;
    private final ProtobufSerializer protobufSerializer;
    private Recorder recorder;
    private final EventSerializer serializer;
    private MobilyticsSession session;
    private MobilyticsMetricsCounter totalRecordsCounter;
    private final Map<String, Endpoint> simplifiedEndpointMap = getEndpointMap();
    private String cognitoId = "Unknown";
    @VisibleForTesting
    public long lastFlushTime = 0;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Debug {
        private static final String DIR = GeneratedOutlineSupport1.outline91(GeneratedOutlineSupport1.outline107(DefaultKinesisConnector.COMPONENT), File.separator, "debug");
        private static final String FILE_EXT = "-debug.log";

        private Debug() {
        }
    }

    @Singleton
    /* loaded from: classes9.dex */
    public static class Factory {
        private final CognitoCredentialsProvider.Factory credentialsProviderFactory;
        private final RecordChecker defaultRecordChecker;
        private final KinesisEventRecorder.Factory eventRecorderFactory;
        private final String installationId;
        private final ProtobufSerializer protobufSerializer;
        private final EventSerializer serializer;

        @Inject
        public Factory(@NonNull RecordChecker recordChecker, @NonNull EventSerializer eventSerializer, @NonNull ProtobufSerializer protobufSerializer, @NonNull @Named("InstallationId") String str, @NonNull KinesisEventRecorder.Factory factory, @NonNull CognitoCredentialsProvider.Factory factory2) {
            this.defaultRecordChecker = (RecordChecker) Preconditions.checkNotNull(recordChecker);
            this.serializer = (EventSerializer) Preconditions.checkNotNull(eventSerializer);
            this.protobufSerializer = (ProtobufSerializer) Preconditions.checkNotNull(protobufSerializer);
            this.installationId = (String) Preconditions.checkNotNull(str);
            this.eventRecorderFactory = (KinesisEventRecorder.Factory) Preconditions.checkNotNull(factory);
            this.credentialsProviderFactory = (CognitoCredentialsProvider.Factory) Preconditions.checkNotNull(factory2);
        }

        public MobilyticsConnector create(@NonNull Map<Regions, Endpoint> map, @NonNull String str) {
            DefaultKinesisConnector defaultKinesisConnector = new DefaultKinesisConnector(map, this.installationId, this.serializer, this.protobufSerializer, this.eventRecorderFactory, this.credentialsProviderFactory, this.defaultRecordChecker);
            defaultKinesisConnector.name = str;
            return defaultKinesisConnector;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class JsonRecorder extends Recorder {
        private JsonRecorder() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Pair<String, JSONObject> addClientDetails(@NonNull MobilyticsEvent mobilyticsEvent) throws JSONException {
            return Pair.create("client", new JSONObject().put(AuthorizationResponseParser.CLIENT_ID_STATE, DefaultKinesisConnector.this.installationId).put("cognitoId", DefaultKinesisConnector.this.cognitoId));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addCognitoPoolId(@NonNull String str, @NonNull JSONObject jSONObject) {
            if ("application".equalsIgnoreCase(str)) {
                try {
                    jSONObject.put("cognitoIdentityPoolId", DefaultKinesisConnector.this.activeEndpoint.cognitoIdentityPoolId());
                } catch (JSONException e) {
                    Log.e(DefaultKinesisConnector.TAG, e, "Error adding Cognito ID to application JSON", new Object[0]);
                }
            }
        }

        @Override // com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector.Recorder
        public void record(MobilyticsEvent mobilyticsEvent) {
            try {
                String serialize = DefaultKinesisConnector.this.serializer.serialize(mobilyticsEvent, new EventSerializer.Visitor() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DefaultKinesisConnector$JsonRecorder$4Qnh7E7rsWFRV50XO7g0t5ZEW7s
                    @Override // com.amazon.alexa.mobilytics.event.serializer.EventSerializer.Visitor
                    public final void visit(String str, JSONObject jSONObject) {
                        DefaultKinesisConnector.JsonRecorder.this.addCognitoPoolId(str, jSONObject);
                    }
                }, new DataHandler() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DefaultKinesisConnector$JsonRecorder$Ty-gNXpnhk3b8PkOE7sNMN9tNek
                    @Override // com.amazon.alexa.mobilytics.event.serializer.handlers.DataHandler
                    public final Pair process(MobilyticsEvent mobilyticsEvent2) {
                        Pair addClientDetails;
                        addClientDetails = DefaultKinesisConnector.JsonRecorder.this.addClientDetails(mobilyticsEvent2);
                        return addClientDetails;
                    }
                });
                if (TextUtils.isEmpty(serialize)) {
                    return;
                }
                DefaultKinesisConnector.this.eventRecorder.saveRecord(serialize, DefaultKinesisConnector.this.activeEndpoint.streamName());
                DefaultKinesisConnector.this.totalRecordsCounter.incrementCounter();
                DefaultKinesisConnector.this.logDebugData(serialize);
            } catch (JSONException e) {
                Log.w(DefaultKinesisConnector.TAG, e, "Failed to serialize event to JSON", new Object[0]);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public class ProtobufRecorder extends Recorder {
        private ProtobufRecorder() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public MobilyticsMessageProto additionalDetails(@NonNull MobilyticsEvent mobilyticsEvent) {
            MobilyticsMessageProto.Builder newBuilder = MobilyticsMessageProto.newBuilder();
            ClientProto.Builder newBuilder2 = ClientProto.newBuilder();
            ApplicationProto.Builder newBuilder3 = ApplicationProto.newBuilder();
            newBuilder3.setCognitoIdentityPoolId(DefaultKinesisConnector.this.activeEndpoint.cognitoIdentityPoolId());
            newBuilder2.setClientId(DefaultKinesisConnector.this.installationId);
            newBuilder2.setCognitoId(DefaultKinesisConnector.this.cognitoId);
            newBuilder.setApplication(newBuilder3);
            newBuilder.setClient(newBuilder2);
            return newBuilder.mo10084build();
        }

        @Override // com.amazon.alexa.mobilytics.connector.DefaultKinesisConnector.Recorder
        public void record(MobilyticsEvent mobilyticsEvent) {
            MobilyticsMessageProto serialize = DefaultKinesisConnector.this.protobufSerializer.serialize(mobilyticsEvent, new ProtobufHandler() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DefaultKinesisConnector$ProtobufRecorder$ePdcOwU6Qqxm0YoZGlM9unq-DTQ
                @Override // com.amazon.alexa.mobilytics.event.serializer.protobufhandlers.ProtobufHandler
                public final MobilyticsMessageProto serialize(MobilyticsEvent mobilyticsEvent2) {
                    MobilyticsMessageProto additionalDetails;
                    additionalDetails = DefaultKinesisConnector.ProtobufRecorder.this.additionalDetails(mobilyticsEvent2);
                    return additionalDetails;
                }
            });
            if (serialize != null) {
                DefaultKinesisConnector.this.eventRecorder.saveRecord(serialize.toByteArray(), DefaultKinesisConnector.this.activeEndpoint.protobufStreamName());
                DefaultKinesisConnector.this.totalRecordsCounter.incrementCounter();
                if (!DefaultKinesisConnector.this.configuration.isDebug()) {
                    return;
                }
                DefaultKinesisConnector.this.logDebugData(serialize.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public abstract class Recorder {
        private Recorder() {
        }

        public abstract void record(MobilyticsEvent mobilyticsEvent);
    }

    /* loaded from: classes9.dex */
    public static final class SessionEvent {
        private static final String PAUSE = "_session.pause";
        private static final String RESUME = "_session.resume";
        public static final String START = "_session.start";
        private static final String STOP = "_session.stop";
    }

    public DefaultKinesisConnector(@NonNull Map<Regions, Endpoint> map, @NonNull String str, @NonNull EventSerializer eventSerializer, @NonNull ProtobufSerializer protobufSerializer, @NonNull KinesisEventRecorder.Factory factory, @NonNull CognitoCredentialsProvider.Factory factory2, @NonNull RecordChecker recordChecker) {
        this.endpoints = (Map) Preconditions.checkNotNull(map);
        this.serializer = (EventSerializer) Preconditions.checkNotNull(eventSerializer);
        this.protobufSerializer = (ProtobufSerializer) Preconditions.checkNotNull(protobufSerializer);
        this.installationId = (String) Preconditions.checkNotNull(str);
        this.eventRecorderFactory = (EventRecorder.Factory) Preconditions.checkNotNull(factory);
        this.credentialsProviderFactory = (CognitoCredentialsProvider.Factory) Preconditions.checkNotNull(factory2);
        this.defaultRecordChecker = (RecordChecker) Preconditions.checkNotNull(recordChecker);
    }

    private boolean appStatusForFlush() {
        char c;
        String appState = this.activeEndpoint.appState();
        int hashCode = appState.hashCode();
        if (hashCode == -317045405) {
            if (appState.equals(KinesisEndpoint.AppState.FOREGROUND)) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode != 65996) {
            if (hashCode == 661270862 && appState.equals(KinesisEndpoint.AppState.BACKGROUND)) {
                c = 2;
            }
            c = 65535;
        } else {
            if (appState.equals(KinesisEndpoint.AppState.ANY)) {
                c = 0;
            }
            c = 65535;
        }
        if (c != 0) {
            if (c == 1) {
                return Utils.isAppOnForeground(this.configuration.context());
            }
            if (c == 2) {
                return !Utils.isAppOnForeground(this.configuration.context());
            }
            return false;
        }
        return true;
    }

    private void createDebugLog() {
        if (this.configuration.isDebug()) {
            try {
                if (this.debugLogStream != null) {
                    this.debugLogStream.flush();
                    IOUtils.closeQuietly(this.debugLogStream);
                }
                File createDirectory = Utils.createDirectory(this.configuration.context(), Debug.DIR);
                if (createDirectory == null) {
                    return;
                }
                this.debugLogFile = new File(createDirectory.getAbsolutePath() + File.separator + this.activeEndpoint.streamName() + "-debug.log");
                if (!this.debugLogFile.exists() && !this.debugLogFile.createNewFile()) {
                    return;
                }
                if (!this.debugLogFile.canWrite() && !this.debugLogFile.setWritable(true, true)) {
                    return;
                }
                this.debugLogStream = new FileOutputStream(this.debugLogFile);
            } catch (Exception e) {
                Log.e(TAG, e, "Error creating debug log file.", new Object[0]);
            }
        }
    }

    private void emitMetaMetrics() {
        try {
            recordEvent(this.totalRecordsCounter);
            recordEvent(this.failedRecordsCounter);
            Log.i(TAG, "[%s] Recording stats: dropped %d records; total records %d", name(), Long.valueOf(this.failedRecordsCounter.getCount()), Long.valueOf(this.totalRecordsCounter.getCount()));
            if (this.eventRecorder != null) {
                DefaultMobilyticsMetricsCounter defaultMobilyticsMetricsCounter = new DefaultMobilyticsMetricsCounter("SizeOnDisk", COMPONENT, this.activeEndpoint.streamName(), OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND);
                defaultMobilyticsMetricsCounter.incrementCounterByValue(this.eventRecorder.sizeOnDisk());
                recordEvent(defaultMobilyticsMetricsCounter);
            }
            this.totalRecordsCounter.resetCounter();
            this.failedRecordsCounter.resetCounter();
        } catch (Exception e) {
            Log.e(TAG, e, "Failed to emit meta metrics", new Object[0]);
        }
    }

    private void flushIfPossible() {
        flushIfPossible(false);
    }

    private Map<String, Endpoint> getEndpointMap() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Regions, Endpoint> entry : this.endpoints.entrySet()) {
            hashMap.put(entry.getKey().getName(), entry.getValue());
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logDebugData(String str) {
        if (this.configuration.isDebug()) {
            try {
                if (this.debugLogFile == null || this.debugLogStream == null || this.debugLogFile.length() >= 20000000) {
                    return;
                }
                this.debugLogStream.write((str + "\n").getBytes(Charset.forName("UTF8")));
                this.debugLogStream.flush();
            } catch (Exception e) {
                Log.v(TAG, "Error writing debug data file: %s", e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRecordsDropped(@Nullable List<byte[]> list) {
        Log.enter();
        if (list != null) {
            this.failedRecordsCounter.incrementCounterByValue(list.size());
        }
        Log.leave();
    }

    private void recordEvent(@NonNull MobilyticsEvent mobilyticsEvent) {
        try {
            if (!this.defaultRecordChecker.shouldBeSent(this.activeEndpoint, mobilyticsEvent)) {
                return;
            }
            mobilyticsEvent.setSession(this.session);
            if (Utils.isCommsMobilyticsMetric(mobilyticsEvent.getEventName())) {
                Log.w(TAG, "[%s] Recording event [%s]", name(), mobilyticsEvent.getEventName());
            } else {
                Log.v(TAG, "[%s] Recording event [%s]", name(), mobilyticsEvent.getEventName());
            }
            this.recorder.record(mobilyticsEvent);
        } catch (Exception e) {
            Log.w(TAG, e, "Failed to save record", new Object[0]);
        }
    }

    private boolean updateActiveEndpoint(@Nullable MobilyticsUser mobilyticsUser) {
        if (mobilyticsUser != null && mobilyticsUser.hasFeature("ALEXA_ANDROID_MOBILYTICS_PROTOBUF")) {
            this.recorder = this.protobufRecorder;
        } else {
            this.recorder = this.jsonRecorder;
        }
        if (mobilyticsUser != null && mobilyticsUser.hasFeature("ALEXA_MOBILYTICS_DATA_REGIONS_ANDROID")) {
            return updateActiveEndpointWithDataRegion();
        }
        return updateActiveEndpointWithPFM(mobilyticsUser);
    }

    private boolean updateActiveEndpointWithDataRegion() {
        KinesisEndpoint kinesisEndpoint;
        MobilyticsEndpointPicker mobilyticsEndpointPicker = this.endpointPicker;
        if (mobilyticsEndpointPicker != null) {
            kinesisEndpoint = (KinesisEndpoint) mobilyticsEndpointPicker.getEndpoint(this.simplifiedEndpointMap);
            if (kinesisEndpoint == null) {
                kinesisEndpoint = (KinesisEndpoint) this.endpoints.get(Region.DEFAULT.awsRegion());
            }
        } else {
            kinesisEndpoint = (KinesisEndpoint) this.endpoints.get(Region.DEFAULT.awsRegion());
        }
        String str = TAG;
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Using endpoint ");
        outline107.append(kinesisEndpoint.streamName());
        outline107.append(", ");
        outline107.append(kinesisEndpoint.awsRegion());
        Log.i(str, outline107.toString());
        if (this.activeEndpoint != kinesisEndpoint) {
            this.activeEndpoint = kinesisEndpoint;
            return true;
        }
        return false;
    }

    private boolean updateActiveEndpointWithPFM(@Nullable MobilyticsUser mobilyticsUser) {
        Marketplace findMarketplaceById;
        Region region = Region.DEFAULT;
        if (mobilyticsUser != null && (findMarketplaceById = Marketplace.findMarketplaceById(mobilyticsUser.marketplaceId(), Marketplace.US)) != null) {
            region = Region.fromCountryCode(findMarketplaceById.name());
        }
        KinesisEndpoint kinesisEndpoint = (KinesisEndpoint) this.endpoints.get(region.awsRegion());
        if (kinesisEndpoint == null) {
            kinesisEndpoint = (KinesisEndpoint) this.endpoints.get(Region.DEFAULT.awsRegion());
        }
        if (this.activeEndpoint != kinesisEndpoint) {
            this.activeEndpoint = kinesisEndpoint;
            return true;
        }
        return false;
    }

    private void updateEventRecorder() {
        try {
            flushIfPossible(true);
            CredentialsProvider create = this.credentialsProviderFactory.create(this.activeEndpoint);
            this.cognitoId = create.id(Utils.isAppOnForeground(this.configuration.context()));
            this.eventRecorder = this.eventRecorderFactory.create(this.activeEndpoint, create);
            this.eventRecorder.onSaveFailed().subscribe(new Action1() { // from class: com.amazon.alexa.mobilytics.connector.-$$Lambda$DefaultKinesisConnector$Eu24rj3QHbqLY5qSRAE1GmtF8H0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    DefaultKinesisConnector.this.onRecordsDropped((List) obj);
                }
            });
            Log.i(TAG, "[%s] Successfully created event recorder for stream %s", name(), this.activeEndpoint.streamName());
            createDebugLog();
        } catch (Exception e) {
            Log.e(TAG, e, "Failed to create KinesisEventRecorder", new Object[0]);
        }
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public String name() {
        return this.name;
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onFinalize() {
        Log.enter();
        Log.d(TAG, "[%s] Finalize callback received.", name());
        flushIfPossible(true);
        if (this.configuration.isDebug()) {
            IOUtils.closeQuietly(this.debugLogStream);
            this.debugLogStream = null;
            this.debugLogFile = null;
        }
        this.eventRecorder = null;
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onInitialize(@NonNull MobilyticsConfiguration mobilyticsConfiguration) {
        Log.enter();
        Log.d(TAG, "[%s] Initialize callback received.", name());
        this.protobufRecorder = new ProtobufRecorder();
        this.jsonRecorder = new JsonRecorder();
        this.configuration = (MobilyticsConfiguration) Preconditions.checkNotNull(mobilyticsConfiguration);
        MobilyticsUser user = this.configuration.userProvider().user();
        this.endpointPicker = this.configuration.endpointPicker();
        updateActiveEndpoint(user);
        this.totalRecordsCounter = new DefaultMobilyticsMetricsCounter("TotalRecords", COMPONENT, this.activeEndpoint.streamName(), OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND);
        this.failedRecordsCounter = new DefaultMobilyticsMetricsCounter("FailedRecords", COMPONENT, this.activeEndpoint.streamName(), OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND);
        updateEventRecorder();
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onRecordEvent(@NonNull MobilyticsEvent mobilyticsEvent) {
        Log.enter();
        if (this.eventRecorder == null) {
            Log.v(TAG, "KinesisRecorder is not initialized. Dropping metric.");
            return;
        }
        recordEvent(mobilyticsEvent);
        flushIfPossible();
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionPause(@NonNull MobilyticsSession mobilyticsSession) {
        Log.enter();
        this.session = mobilyticsSession;
        Log.d(TAG, "[%s] Session pause callback received.", name());
        recordEvent(new DefaultMobilyticsOperationalEvent("_session.pause", "session", COMPONENT, "platform", OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND));
        flushIfPossible(true);
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionResume(@NonNull MobilyticsSession mobilyticsSession) {
        Log.enter();
        this.session = mobilyticsSession;
        Log.d(TAG, "[%s] Session resume callback received.", name());
        recordEvent(new DefaultMobilyticsOperationalEvent("_session.resume", "session", COMPONENT, "platform", OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND));
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionStart(@NonNull MobilyticsSession mobilyticsSession) {
        Log.enter();
        this.session = mobilyticsSession;
        Log.d(TAG, "[%s] Session start callback received.", name());
        recordEvent(new DefaultMobilyticsOperationalEvent("_session.start", "session", COMPONENT, "platform", OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND));
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onSessionStop(@NonNull MobilyticsSession mobilyticsSession) {
        Log.enter();
        this.session = mobilyticsSession;
        Log.d(TAG, "[%s] Session stop callback received.", name());
        recordEvent(new DefaultMobilyticsOperationalEvent("_session.stop", "session", COMPONENT, "platform", OwnerIdentifier.MOBILE_ORG_ANALYTICS_BACKEND));
        Log.leave();
    }

    @Override // com.amazon.alexa.mobilytics.connector.MobilyticsConnector
    public void onUserChanged(@Nullable MobilyticsUser mobilyticsUser) {
        Log.enter();
        if (updateActiveEndpoint(mobilyticsUser)) {
            updateEventRecorder();
        }
        Log.leave();
    }

    private void flushIfPossible(boolean z) {
        boolean z2;
        Log.enter();
        EventRecorder eventRecorder = this.eventRecorder;
        if (eventRecorder != null) {
            try {
                long sizeOnDisk = eventRecorder.sizeOnDisk();
                long currentTimeMillis = System.currentTimeMillis() - this.lastFlushTime;
                Log.d(TAG, "[%s] Flush signal received. Disk bytes used %d and elapsed time %d.", name(), Long.valueOf(sizeOnDisk), Long.valueOf(currentTimeMillis));
                boolean z3 = appStatusForFlush() && sizeOnDisk >= this.activeEndpoint.flushSize();
                boolean z4 = !Utils.isAppOnForeground(this.configuration.context()) && sizeOnDisk >= 1000000;
                if (!z3 && !z4) {
                    z2 = false;
                    if (!z || (z2 && currentTimeMillis > this.activeEndpoint.flushTime())) {
                        Log.i(TAG, "[%s] Going to flush records", name());
                        emitMetaMetrics();
                        this.eventRecorder.submitAllRecords();
                        this.lastFlushTime = System.currentTimeMillis();
                        Log.i(TAG, "[%s] %d records failed to flush.", name(), Long.valueOf(this.failedRecordsCounter.getCount()));
                    }
                }
                z2 = true;
                if (!z) {
                }
                Log.i(TAG, "[%s] Going to flush records", name());
                emitMetaMetrics();
                this.eventRecorder.submitAllRecords();
                this.lastFlushTime = System.currentTimeMillis();
                Log.i(TAG, "[%s] %d records failed to flush.", name(), Long.valueOf(this.failedRecordsCounter.getCount()));
            } catch (Exception e) {
                Log.w(TAG, e, "Failed to flush metrics to Mobilytics", new Object[0]);
            }
        }
        Log.leave();
    }
}
