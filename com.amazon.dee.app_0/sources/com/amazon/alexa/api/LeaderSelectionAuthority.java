package com.amazon.alexa.api;

import android.content.ComponentName;
import android.content.Context;
import android.util.Log;
import com.amazon.alexa.utils.Provider;
import com.amazon.alexa.utils.concurrent.ManagedExecutorFactory;
import com.amazon.alexa.utils.validation.Preconditions;
import com.amazon.leaderselection.Leader;
import com.amazon.leaderselection.LeaderSelector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
/* loaded from: classes6.dex */
public class LeaderSelectionAuthority {
    private static final String LEADER_SELECTION_EXECUTOR_NAME = "leader-selection";
    private static final String L_S_TIMEOUT_EXECUTOR_NAME = "l-s-timeout";
    private static final String TAG = "LeaderSelectionAuthority";
    public static final long WAIT_FOR_LEADING_ALEXA_SERVICE_TIMEOUT = 10000;
    private final Context context;
    private final ExecutorService executorService;
    private final Provider<LeaderSelector> leaderSelectorProvider;
    private final ScheduledExecutorService timeoutScheduler;

    /* loaded from: classes6.dex */
    public enum LeaderSelectionFailureReason {
        UNKNOWN_REASON,
        UNKNOWN_LEADER,
        TIMEOUT,
        NOT_VERIFIED,
        MISSING_PACKAGE,
        DISABLED
    }

    /* loaded from: classes6.dex */
    public interface LeaderSelectionListener {
        void onLeaderSelected(ComponentName componentName);

        void onLeaderSelectionFailed(LeaderSelectionFailureReason leaderSelectionFailureReason, Throwable th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes6.dex */
    public static class LeaderSelectionListenerLifecycle implements LeaderSelectionListener {
        private final AtomicReference<LeaderSelectionListener> listenerReference;

        private LeaderSelectionListenerLifecycle(LeaderSelectionListener leaderSelectionListener) {
            this.listenerReference = new AtomicReference<>(leaderSelectionListener);
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelected(ComponentName componentName) {
            LeaderSelectionListener andSet = this.listenerReference.getAndSet(null);
            if (andSet != null) {
                andSet.onLeaderSelected(componentName);
            }
        }

        @Override // com.amazon.alexa.api.LeaderSelectionAuthority.LeaderSelectionListener
        public void onLeaderSelectionFailed(LeaderSelectionFailureReason leaderSelectionFailureReason, Throwable th) {
            LeaderSelectionListener andSet = this.listenerReference.getAndSet(null);
            if (andSet != null) {
                andSet.onLeaderSelectionFailed(leaderSelectionFailureReason, th);
            }
        }
    }

    public LeaderSelectionAuthority(final Context context) {
        this(context, ManagedExecutorFactory.newSingleThreadExecutor(LEADER_SELECTION_EXECUTOR_NAME), ManagedExecutorFactory.newSingleThreadScheduledExecutor(L_S_TIMEOUT_EXECUTOR_NAME), new Provider<LeaderSelector>() { // from class: com.amazon.alexa.api.LeaderSelectionAuthority.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.amazon.alexa.utils.Provider
            /* renamed from: get */
            public LeaderSelector mo2864get() {
                return new LeaderSelector(context);
            }
        });
    }

    public LeaderSelectionAuthority(Context context, ExecutorService executorService, ScheduledExecutorService scheduledExecutorService, Provider<LeaderSelector> provider) {
        this.context = context;
        this.executorService = executorService;
        this.timeoutScheduler = scheduledExecutorService;
        this.leaderSelectorProvider = provider;
    }

    private ScheduledFuture<?> scheduleTimeout(final LeaderSelectionListenerLifecycle leaderSelectionListenerLifecycle) {
        return this.timeoutScheduler.schedule(new Runnable() { // from class: com.amazon.alexa.api.LeaderSelectionAuthority.3
            @Override // java.lang.Runnable
            public void run() {
                Log.e(LeaderSelectionAuthority.TAG, "Leader Selection timed out after 10000ms");
                leaderSelectionListenerLifecycle.onLeaderSelectionFailed(LeaderSelectionFailureReason.TIMEOUT, new TimeoutException("leader selection timeout"));
                LeaderSelectionAuthority.this.timeoutScheduler.shutdown();
            }
        }, 10000L, TimeUnit.MILLISECONDS);
    }

    public void pickAlexaService(final String str, LeaderSelectionListener leaderSelectionListener) {
        Preconditions.notNull(leaderSelectionListener, "Leader selection listener was null");
        final LeaderSelectionListenerLifecycle leaderSelectionListenerLifecycle = new LeaderSelectionListenerLifecycle(leaderSelectionListener);
        final ScheduledFuture<?> scheduleTimeout = scheduleTimeout(leaderSelectionListenerLifecycle);
        String str2 = TAG;
        Log.i(str2, "Looking for leading Alexa service " + leaderSelectionListener);
        this.executorService.submit(new Runnable() { // from class: com.amazon.alexa.api.LeaderSelectionAuthority.2
            /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
                if (r1 == false) goto L17;
             */
            /* JADX WARN: Code restructure failed: missing block: B:12:0x0037, code lost:
                r4.onLeaderSelected(r0.getComponentName());
             */
            /* JADX WARN: Code restructure failed: missing block: B:29:0x008b, code lost:
                if (r1 == false) goto L17;
             */
            /* JADX WARN: Code restructure failed: missing block: B:33:0x00b1, code lost:
                if (r1 == false) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:42:0x00cf, code lost:
                if (r1 == 0) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:51:0x00ed, code lost:
                if (r1 == 0) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:60:0x0110, code lost:
                if (r1 == 0) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:61:0x0112, code lost:
                r1 = r4;
                r0 = r0.getComponentName();
                r1.onLeaderSelected(r0);
                r0 = r0;
                r1 = r1;
                r3 = r3;
             */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.leaderselection.Leader, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v1, types: [com.amazon.leaderselection.Leader, java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v11, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v16 */
            /* JADX WARN: Type inference failed for: r0v19 */
            /* JADX WARN: Type inference failed for: r0v20 */
            /* JADX WARN: Type inference failed for: r0v21 */
            /* JADX WARN: Type inference failed for: r0v28, types: [java.util.concurrent.ScheduledExecutorService] */
            /* JADX WARN: Type inference failed for: r0v36 */
            /* JADX WARN: Type inference failed for: r0v37 */
            /* JADX WARN: Type inference failed for: r0v38 */
            /* JADX WARN: Type inference failed for: r0v39 */
            /* JADX WARN: Type inference failed for: r0v40 */
            /* JADX WARN: Type inference failed for: r0v8, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object] */
            /* JADX WARN: Type inference failed for: r1v10, types: [boolean] */
            /* JADX WARN: Type inference failed for: r1v13, types: [com.amazon.leaderselection.Leader] */
            /* JADX WARN: Type inference failed for: r1v14, types: [boolean] */
            /* JADX WARN: Type inference failed for: r1v16, types: [com.amazon.alexa.api.LeaderSelectionAuthority$LeaderSelectionListenerLifecycle] */
            /* JADX WARN: Type inference failed for: r1v2, types: [com.amazon.leaderselection.Leader] */
            /* JADX WARN: Type inference failed for: r1v25, types: [com.amazon.leaderselection.Leader] */
            /* JADX WARN: Type inference failed for: r1v6, types: [com.amazon.leaderselection.Leader] */
            /* JADX WARN: Type inference failed for: r1v7, types: [boolean] */
            /* JADX WARN: Type inference failed for: r1v9, types: [com.amazon.leaderselection.Leader] */
            /* JADX WARN: Type inference failed for: r3v16, types: [java.lang.String] */
            /* JADX WARN: Type inference failed for: r3v4, types: [java.util.concurrent.ScheduledFuture] */
            /* JADX WARN: Type inference failed for: r3v5, types: [java.util.concurrent.ScheduledFuture] */
            /* JADX WARN: Type inference failed for: r3v7, types: [java.util.concurrent.ScheduledFuture] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    Method dump skipped, instructions count: 352
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.amazon.alexa.api.LeaderSelectionAuthority.AnonymousClass2.run():void");
            }
        });
    }

    Leader selectLeader(String str) {
        Leader select = this.leaderSelectorProvider.mo2864get().select(str);
        String str2 = "Found leader: " + select;
        return select;
    }
}
