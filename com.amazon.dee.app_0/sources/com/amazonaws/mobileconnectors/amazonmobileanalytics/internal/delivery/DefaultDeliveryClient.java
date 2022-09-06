package com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery;

import android.util.Log;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.MobileAnalyticsManager;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.AnalyticsContext;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.core.util.StringUtil;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.EventStore;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DefaultDeliveryPolicyFactory;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.InternalEvent;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter.EventAdapter;
import com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.adapter.JSONEventAdapter;
import com.amazonaws.util.VersionInfoUtils;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Deprecated
/* loaded from: classes13.dex */
public class DefaultDeliveryClient implements DeliveryClient {
    private static final int CLIPPED_EVENT_LENGTH = 5;
    static final int DEFAULT_MAX_SUBMISSIONS_ALLOWED = 3;
    static final long DEFAULT_MAX_SUBMISSION_SIZE = 102400;
    public static final String EVENTS_DIRECTORY = "events";
    static final String KEY_MAX_SUBMISSIONS_ALLOWED = "maxSubmissionAllowed";
    static final String KEY_MAX_SUBMISSION_SIZE = "maxSubmissionSize";
    private static final int MAX_EVENT_OPERATIONS = 1000;
    private static final int MAX_SUBMIT_OPERATIONS = 100;
    private static final String TAG = "DefaultDeliveryClient";
    private final AnalyticsContext context;
    private final EventAdapter<JSONObject> eventAdapter;
    private final EventStore eventStore;
    private final ExecutorService eventsRunnableQueue;
    private final DefaultDeliveryPolicyFactory policyFactory;
    private final ERSRequestBuilder requestBuilder;
    private final ExecutorService submissionRunnableQueue;
    private static final String USER_AGENT = MobileAnalyticsManager.class.getName() + "/" + VersionInfoUtils.getVersion();
    static final Set<Integer> RETRY_REQUEST_CODES = new HashSet();
    private final AtomicLong avgWriteEventTimeMillis = new AtomicLong(25);
    private final AtomicLong eventsProcessed = new AtomicLong(0);
    private long lastAttemptTime = 0;

    static {
        RETRY_REQUEST_CODES.add(Integer.valueOf((int) HttpServletResponse.SC_UNAUTHORIZED));
        RETRY_REQUEST_CODES.add(404);
        RETRY_REQUEST_CODES.add(Integer.valueOf((int) HttpServletResponse.SC_PROXY_AUTHENTICATION_REQUIRED));
        RETRY_REQUEST_CODES.add(408);
    }

    DefaultDeliveryClient(AnalyticsContext analyticsContext, DefaultDeliveryPolicyFactory defaultDeliveryPolicyFactory, ExecutorService executorService, ExecutorService executorService2, ERSRequestBuilder eRSRequestBuilder, EventStore eventStore, EventAdapter<JSONObject> eventAdapter) {
        this.policyFactory = defaultDeliveryPolicyFactory;
        this.eventsRunnableQueue = executorService;
        this.submissionRunnableQueue = executorService2;
        this.context = analyticsContext;
        this.requestBuilder = eRSRequestBuilder;
        this.eventStore = eventStore;
        this.eventAdapter = eventAdapter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calculateAndSetAverageWriteEventTime(long j, long j2) {
        long addAndGet = this.eventsProcessed.addAndGet(1L) - j;
        this.avgWriteEventTimeMillis.set((long) Math.ceil((System.currentTimeMillis() - j2) / addAndGet));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getSubmissionLatchWaitTime() {
        return (long) (this.avgWriteEventTimeMillis.get() * 1000 * 1.5d);
    }

    public static DefaultDeliveryClient newInstance(AnalyticsContext analyticsContext, boolean z) {
        return new DefaultDeliveryClient(analyticsContext, new DefaultDeliveryPolicyFactory(analyticsContext, z), new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(1000), new ThreadPoolExecutor.DiscardPolicy()), new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(100), new ThreadPoolExecutor.DiscardPolicy()), new ERSRequestBuilder(), FileEventStore.newInstance(analyticsContext), new JSONEventAdapter());
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DeliveryClient
    public void attemptDelivery() {
        ArrayList arrayList = new ArrayList();
        DeliveryPolicy newForceSubmissionTimePolicy = this.policyFactory.newForceSubmissionTimePolicy();
        DeliveryPolicy newConnectivityPolicy = this.policyFactory.newConnectivityPolicy();
        if (newConnectivityPolicy != null) {
            arrayList.add(newConnectivityPolicy);
        }
        if (newForceSubmissionTimePolicy != null) {
            arrayList.add(newForceSubmissionTimePolicy);
        }
        attemptDelivery(arrayList);
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DeliveryClient
    public void enqueueEventForDelivery(final InternalEvent internalEvent) {
        final long currentTimeMillis = System.currentTimeMillis();
        final long j = this.eventsProcessed.get();
        this.eventsRunnableQueue.execute(new Runnable() { // from class: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient.1
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis2 = System.currentTimeMillis();
                try {
                    try {
                        if (DefaultDeliveryClient.this.eventStore.put(((JSONObject) DefaultDeliveryClient.this.eventAdapter.translateFromEvent(internalEvent)).toString())) {
                            Log.i(DefaultDeliveryClient.TAG, String.format("Event: '%s' recorded to local filestore", StringUtil.clipString(internalEvent.getEventType(), 5, true)));
                            String.format("Time of enqueueEventForDelivery: %d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis2));
                        } else {
                            Log.w(DefaultDeliveryClient.TAG, String.format("Event: '%s' failed to record to local filestore", StringUtil.clipString(internalEvent.getEventType(), 5, true)));
                        }
                    } catch (EventStoreException unused) {
                        Log.w(DefaultDeliveryClient.TAG, String.format("Event: '%s' failed to record to local filestore", StringUtil.clipString(internalEvent.getEventType(), 5, true)));
                    }
                } finally {
                    DefaultDeliveryClient.this.calculateAndSetAverageWriteEventTime(j, currentTimeMillis);
                }
            }
        });
    }

    JSONArray getNextBatchToSubmit(EventStore.EventIterator eventIterator, long j) throws JSONException {
        if (eventIterator != null) {
            long length = eventIterator.peek() != null ? eventIterator.peek().length() : 0L;
            JSONArray jSONArray = new JSONArray();
            long j2 = length;
            long j3 = 0;
            while (true) {
                j3 += j2;
                if (j3 > j || !eventIterator.hasNext()) {
                    break;
                }
                j2 = eventIterator.peek() != null ? eventIterator.peek().length() : 0L;
                jSONArray.put(new JSONObject(eventIterator.next()));
            }
            return jSONArray;
        }
        throw new IllegalArgumentException("Iterator cannot be null");
    }

    @Override // com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.event.EventObserver
    public void notify(InternalEvent internalEvent) {
        enqueueEventForDelivery(internalEvent);
    }

    boolean shouldAttemptDelivery(long j, long j2) {
        return System.currentTimeMillis() - j > j2 || System.currentTimeMillis() - j < 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00db A[LOOP:1: B:27:0x00d5->B:29:0x00db, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    boolean submitEvents(org.json.JSONArray r8, java.util.List<com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.policy.DeliveryPolicy> r9) {
        /*
            Method dump skipped, instructions count: 305
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient.submitEvents(org.json.JSONArray, java.util.List):boolean");
    }

    public void attemptDelivery(final List<DeliveryPolicy> list) {
        if (shouldAttemptDelivery(this.lastAttemptTime, this.policyFactory.forceSubmissionInterval)) {
            this.lastAttemptTime = System.currentTimeMillis();
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            this.eventsRunnableQueue.execute(new Runnable() { // from class: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient.2
                @Override // java.lang.Runnable
                public void run() {
                    countDownLatch.countDown();
                }
            });
            this.submissionRunnableQueue.execute(new Runnable() { // from class: com.amazonaws.mobileconnectors.amazonmobileanalytics.internal.delivery.DefaultDeliveryClient.3
                @Override // java.lang.Runnable
                public void run() {
                    long currentTimeMillis = System.currentTimeMillis();
                    for (DeliveryPolicy deliveryPolicy : list) {
                        if (!deliveryPolicy.isAllowed()) {
                            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Policy ");
                            outline107.append(deliveryPolicy.getClass());
                            outline107.append(" is not allowed");
                            outline107.toString();
                            return;
                        }
                    }
                    try {
                        countDownLatch.await(DefaultDeliveryClient.this.getSubmissionLatchWaitTime(), TimeUnit.MILLISECONDS);
                    } catch (InterruptedException unused) {
                    }
                    long longValue = DefaultDeliveryClient.this.context.getConfiguration().optLong(DefaultDeliveryClient.KEY_MAX_SUBMISSION_SIZE, Long.valueOf((long) DefaultDeliveryClient.DEFAULT_MAX_SUBMISSION_SIZE)).longValue();
                    new JSONArray();
                    EventStore.EventIterator it2 = DefaultDeliveryClient.this.eventStore.iterator();
                    int intValue = DefaultDeliveryClient.this.context.getConfiguration().optInt(DefaultDeliveryClient.KEY_MAX_SUBMISSIONS_ALLOWED, 3).intValue();
                    int i = 0;
                    while (it2.hasNext() && i < intValue) {
                        try {
                        } catch (JSONException e) {
                            Log.e(DefaultDeliveryClient.TAG, "Could not convert stored event into json", e);
                        } catch (Exception e2) {
                            Log.e(DefaultDeliveryClient.TAG, "An internal error occured, events could not be submitted", e2);
                        }
                        if (!DefaultDeliveryClient.this.submitEvents(DefaultDeliveryClient.this.getNextBatchToSubmit(it2, longValue), list)) {
                            break;
                        }
                        i++;
                        it2.removeReadEvents();
                    }
                    String.format("Time of attemptDelivery: %d", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                }
            });
        }
    }
}
