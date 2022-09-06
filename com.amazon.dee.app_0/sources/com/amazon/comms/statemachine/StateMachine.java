package com.amazon.comms.statemachine;

import android.text.TextUtils;
import android.util.Pair;
import com.amazon.alexa.biloba.utils.WebConstants;
import com.amazon.comms.debug.DebugAssert;
import com.amazon.comms.debug.ThreadAssert;
import com.amazon.comms.log.CommsLogger;
import com.amazon.comms.metrics.MetricsManager;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.lang.Enum;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeSet;
/* loaded from: classes12.dex */
public abstract class StateMachine<S extends Enum<S>, E extends Enum<E>> {
    private HashMap<S, Action> mActions;
    private S mInitialState;
    private boolean mProcessingStateChanges;
    private S mState;
    private ThreadAssert mThreadAssert;
    private TransitionHelper mTransitionHelper;
    private HashMap<S, HashMap<E, S>> mTransitions;

    /* loaded from: classes12.dex */
    public static class Action<E> {
        /* renamed from: onEnter */
        public E mo3618onEnter() {
            return null;
        }

        public void onExit() {
        }

        public void onReEnter() {
        }
    }

    /* loaded from: classes12.dex */
    public static final class Configuration {
        ThreadAssert mThreadAssert = new ThreadAssert();
        TransitionHelper mTransitionHelper = new TransitionHelper();

        public Configuration withMetrics(MetricsManager metricsManager, String str) {
            if (metricsManager != null) {
                if (!TextUtils.isEmpty(str)) {
                    this.mTransitionHelper = new MetricsTransitionHelper(metricsManager, str);
                    return this;
                }
                throw new IllegalArgumentException("Must specify metricsSource");
            }
            throw new IllegalArgumentException("Must specify metricsManager");
        }

        public Configuration withThreadAssert(ThreadAssert threadAssert) {
            if (threadAssert != null) {
                this.mThreadAssert = threadAssert;
                return this;
            }
            throw new IllegalArgumentException("Must specify threadAssert");
        }
    }

    /* loaded from: classes12.dex */
    private class MetricNamesDumper {
        private MetricNamesDumper() {
        }

        public void dump() {
            CommsLogger logger = StateMachine.this.getLogger();
            logger.d("-- BEGIN STATE MACHINE METRIC NAMES --");
            if (!(StateMachine.this.mTransitionHelper instanceof MetricsTransitionHelper)) {
                logger.d("WARNING: StateMachine was not configured with a MetricsManager so no metrics will be recorded.");
            }
            TreeSet treeSet = new TreeSet(new Comparator<S>() { // from class: com.amazon.comms.statemachine.StateMachine.MetricNamesDumper.1
                @Override // java.util.Comparator
                public int compare(S s, S s2) {
                    return s.name().compareTo(s2.name());
                }
            });
            treeSet.addAll(StateMachine.this.mActions.keySet());
            logger.d("ENTER/EXIT ACTION METRIC NAMES:");
            Iterator it2 = treeSet.iterator();
            while (it2.hasNext()) {
                Enum r2 = (Enum) it2.next();
                logger.d(StateMachine.formulateOnEnterMetricName(r2));
                logger.d(StateMachine.formulateOnExitMetricName(r2));
            }
            TreeSet treeSet2 = new TreeSet(new Comparator<Pair<S, S>>() { // from class: com.amazon.comms.statemachine.StateMachine.MetricNamesDumper.2
                @Override // java.util.Comparator
                public /* bridge */ /* synthetic */ int compare(Object obj, Object obj2) {
                    return compare((Pair) ((Pair) obj), (Pair) ((Pair) obj2));
                }

                public int compare(Pair<S, S> pair, Pair<S, S> pair2) {
                    int compareTo = ((Enum) pair.first).name().compareTo(((Enum) pair2.first).name());
                    return compareTo != 0 ? compareTo : ((Enum) pair.second).name().compareTo(((Enum) pair2.second).name());
                }
            });
            for (Enum r3 : StateMachine.this.mTransitions.keySet()) {
                for (Enum r5 : ((HashMap) StateMachine.this.mTransitions.get(r3)).values()) {
                    treeSet2.add(new Pair(r3, r5));
                }
            }
            logger.d("STATE TRANSITION METRIC NAMES:");
            Iterator it3 = treeSet2.iterator();
            while (it3.hasNext()) {
                Pair pair = (Pair) it3.next();
                logger.d(StateMachine.formulateTransitionMetricName(pair.first, pair.second));
            }
            logger.d("illegal_state_transition (with metadata IllegalStateTransition containing offending transition)");
            logger.d("-- END STATE MACHINE METRIC NAMES --");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes12.dex */
    public static class MetricsTransitionHelper<S, E> extends TransitionHelper<S, E> {
        static final String ILLEGAL_TRANSITION_COUNTER_NAME = "illegal_state_transition";
        static final String ILLEGAL_TRANSITION_METADATA_KEY = "IllegalStateTransition";
        private MetricsManager mMetricsManager;
        private String mMetricsSource;

        public MetricsTransitionHelper(MetricsManager metricsManager, String str) {
            super();
            this.mMetricsManager = metricsManager;
            this.mMetricsSource = str;
        }

        @Override // com.amazon.comms.statemachine.StateMachine.TransitionHelper
        public void countIllegalTransition(String str) {
            this.mMetricsManager.addMetadata(this.mMetricsSource, ILLEGAL_TRANSITION_METADATA_KEY, str);
            this.mMetricsManager.incrementCounter(this.mMetricsSource, ILLEGAL_TRANSITION_COUNTER_NAME, 1.0d);
            this.mMetricsManager.record(this.mMetricsSource);
        }

        @Override // com.amazon.comms.statemachine.StateMachine.TransitionHelper
        public void countTransition(String str) {
            this.mMetricsManager.incrementCounter(this.mMetricsSource, str, 1.0d);
            this.mMetricsManager.record(this.mMetricsSource);
        }

        @Override // com.amazon.comms.statemachine.StateMachine.TransitionHelper
        public E invokeOnEnter(S s, Action action) {
            String formulateOnEnterMetricName = StateMachine.formulateOnEnterMetricName(s);
            this.mMetricsManager.startTimer(this.mMetricsSource, formulateOnEnterMetricName);
            try {
                return (E) super.invokeOnEnter(s, action);
            } finally {
                this.mMetricsManager.stopTimer(this.mMetricsSource, formulateOnEnterMetricName);
                this.mMetricsManager.record(this.mMetricsSource);
            }
        }

        @Override // com.amazon.comms.statemachine.StateMachine.TransitionHelper
        public void invokeOnExit(S s, Action action) {
            String formulateOnExitMetricName = StateMachine.formulateOnExitMetricName(s);
            this.mMetricsManager.startTimer(this.mMetricsSource, formulateOnExitMetricName);
            try {
                super.invokeOnExit(s, action);
            } finally {
                this.mMetricsManager.stopTimer(this.mMetricsSource, formulateOnExitMetricName);
                this.mMetricsManager.record(this.mMetricsSource);
            }
        }
    }

    /* loaded from: classes12.dex */
    protected class TransitionBuilder {
        private S mAt;
        private final StateMachine<S, E> mMachine;
        private E mWhen;

        public TransitionBuilder(StateMachine<S, E> stateMachine) {
            if (stateMachine != null) {
                ((StateMachine) stateMachine).mThreadAssert.expectSameThread();
                this.mMachine = stateMachine;
                return;
            }
            throw new IllegalArgumentException("Must specify the machine");
        }

        private void throwUsage(String str) {
            throw new IllegalArgumentException(str);
        }

        public StateMachine<S, E>.TransitionBuilder at(S s) {
            if (this.mAt != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".at(");
                outline107.append(this.mAt);
                outline107.append(") already specified");
                throwUsage(outline107.toString());
            }
            if (s == null) {
                throwUsage(".at(state) must be specified with a non-null value");
            }
            this.mAt = s;
            return this;
        }

        public void build() {
            this.mAt = null;
            this.mWhen = null;
        }

        public StateMachine<S, E>.TransitionBuilder go(S s) {
            if (this.mWhen == null) {
                throwUsage(".when(event) must be specified before .go(destinationState)");
            }
            if (s == null) {
                throwUsage(".go(destinationState) must be specified with a non-null value");
            }
            if (s == this.mAt) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Use .ignore() here rather than .go(");
                outline107.append(this.mAt);
                outline107.append(")");
                throwUsage(outline107.toString());
            }
            this.mMachine.setTransition(this.mAt, this.mWhen, s);
            this.mWhen = null;
            return this;
        }

        public StateMachine<S, E>.TransitionBuilder ignore() {
            if (this.mWhen == null) {
                throwUsage(".when(event) must be specified before .ignore()");
            }
            StateMachine<S, E> stateMachine = this.mMachine;
            S s = this.mAt;
            stateMachine.setTransition(s, this.mWhen, s);
            this.mWhen = null;
            return this;
        }

        public StateMachine<S, E>.TransitionBuilder when(E e) {
            if (this.mAt == null) {
                throwUsage(".at(state) must be specified before .when(event)");
            }
            if (this.mWhen != null) {
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107(".when(");
                outline107.append(this.mWhen);
                outline107.append(") already specified");
                throwUsage(outline107.toString());
            }
            if (e == null) {
                throwUsage(".when(event) must be specifies with a non-null value");
            }
            this.mWhen = e;
            return this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes12.dex */
    public static class TransitionHelper<S, E> {
        private TransitionHelper() {
        }

        public void countIllegalTransition(String str) {
        }

        public void countTransition(String str) {
        }

        public E invokeOnEnter(S s, Action action) {
            return (E) action.mo3618onEnter();
        }

        public void invokeOnExit(S s, Action action) {
            action.onExit();
        }

        public void invokeOnReEnter(S s, Action action) {
            action.onReEnter();
        }
    }

    protected StateMachine(S s) {
        this(s, new Configuration());
    }

    private E changeState(S s, String str) {
        boolean z = this.mState != s;
        if (this.mState != null && z) {
            CommsLogger logger = getLogger();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("exiting ");
            outline107.append(this.mState);
            logger.i(outline107.toString());
            Action action = this.mActions.get(this.mState);
            if (action != null) {
                this.mTransitionHelper.invokeOnExit(this.mState, action);
            }
        }
        GeneratedOutlineSupport1.outline177(GeneratedOutlineSupport1.outline107(str), z ? "" : " Will re-enter same state", getLogger());
        this.mTransitionHelper.countTransition(formulateTransitionMetricName(this.mState, s));
        this.mState = s;
        if (z) {
            CommsLogger logger2 = getLogger();
            StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("entering ");
            outline1072.append(this.mState);
            logger2.i(outline1072.toString());
            Action action2 = this.mActions.get(this.mState);
            if (action2 == null) {
                return null;
            }
            return (E) this.mTransitionHelper.invokeOnEnter(this.mState, action2);
        }
        CommsLogger logger3 = getLogger();
        StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("Re-entering ");
        outline1073.append(this.mState);
        logger3.i(outline1073.toString());
        Action action3 = this.mActions.get(this.mState);
        if (action3 == null) {
            return null;
        }
        this.mTransitionHelper.invokeOnReEnter(this.mState, action3);
        return null;
    }

    private S determineTarget(E e) {
        HashMap<E, S> hashMap = this.mTransitions.get(this.mState);
        S s = hashMap != null ? hashMap.get(e) : null;
        if (s == null) {
            String formulateTransitionDescription = formulateTransitionDescription(this.mState, null, e);
            this.mTransitionHelper.countIllegalTransition(formulateTransitionDescription);
            DebugAssert.fail("Illegal state transition: " + formulateTransitionDescription);
        }
        return s;
    }

    static <S, E> String formulateOnEnterMetricName(S s) {
        return s + "_onEnter";
    }

    static <S, E> String formulateOnExitMetricName(S s) {
        return s + "_onExit";
    }

    private static <S, E> String formulateTransitionDescription(S s, S s2, E e) {
        String str;
        StringBuilder sb = new StringBuilder();
        String str2 = "";
        if (s != null) {
            str = s + " ";
        } else {
            str = str2;
        }
        sb.append(str);
        sb.append("-> ");
        if (s2 == null) {
            s2 = (S) WebConstants.UriConstants.QUESTIONMARK_KEY;
        }
        sb.append(s2);
        if (e != null) {
            str2 = GeneratedOutlineSupport1.outline70(" due to ", e);
        }
        sb.append(str2);
        return sb.toString();
    }

    static <S, E> String formulateTransitionMetricName(S s, S s2) {
        StringBuilder sb = new StringBuilder();
        if (s == null) {
            s = "none";
        }
        sb.append(s);
        sb.append("_to_");
        if (s2 == null) {
            s2 = "none";
        }
        sb.append(s2);
        return sb.toString();
    }

    private void processStateChanges(S s, E e) {
        this.mProcessingStateChanges = true;
        do {
            try {
                e = changeState(s, formulateTransitionDescription(this.mState, s, e));
                if (e != null) {
                    s = determineTarget(e);
                    continue;
                } else {
                    s = null;
                    continue;
                }
            } finally {
                this.mProcessingStateChanges = false;
            }
        } while (s != null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransition(S s, E e, S s2) {
        this.mThreadAssert.expectSameThread();
        DebugAssert.expect(this.mState == null, "Transitions must be specified before starting state machine.");
        if (s != null) {
            if (e == null) {
                throw new IllegalArgumentException("Must specify a value for event");
            }
            if (s2 != null) {
                HashMap<E, S> hashMap = this.mTransitions.get(s);
                if (hashMap == null) {
                    hashMap = new HashMap<>();
                    this.mTransitions.put(s, hashMap);
                }
                if (hashMap.get(e) != s2) {
                    CommsLogger logger = getLogger();
                    logger.i("setTransition(" + s + ", " + e + ", " + s2 + ")");
                    hashMap.put(e, s2);
                    return;
                }
                StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Transition already exists: ");
                outline107.append(formulateTransitionDescription(s, s2, e));
                throw new IllegalArgumentException(outline107.toString());
            }
            throw new IllegalArgumentException("Must specify a value for newState");
        }
        throw new IllegalArgumentException("Must specify a value for oldState");
    }

    public void dumpMetricNames() {
        new MetricNamesDumper().dump();
    }

    protected abstract CommsLogger getLogger();

    public void onEvent(E e) {
        S determineTarget;
        this.mThreadAssert.expectSameThread();
        if (e != null) {
            boolean z = true;
            DebugAssert.expect(!this.mProcessingStateChanges, "Firing an event from within an Action method is invalid.  Return the event to fire from Action.onEnter() instead.");
            if (this.mState == null) {
                z = false;
            }
            DebugAssert.expect(z, "State machine has not started.  Was expecting an initial state.");
            if (this.mState == null || (determineTarget = determineTarget(e)) == null) {
                return;
            }
            processStateChanges(determineTarget, e);
            return;
        }
        throw new IllegalArgumentException("Must specify an event");
    }

    public void setAction(S s, Action action) {
        this.mThreadAssert.expectSameThread();
        DebugAssert.expect(this.mState == null, "Actions must be specified before starting state machine.");
        if (s != null) {
            if (action != null) {
                if (this.mActions.get(s) == null) {
                    this.mActions.put(s, action);
                    return;
                }
                throw new IllegalArgumentException("Action already exists for " + s);
            }
            throw new IllegalArgumentException("Must specify a value for action");
        }
        throw new IllegalArgumentException("Must specify a value for state");
    }

    public void start() {
        this.mThreadAssert.expectSameThread();
        DebugAssert.expect(this.mState == null, "Can't enter initial state because state machine has already started.");
        if (this.mState == null) {
            processStateChanges(this.mInitialState, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public StateMachine(S s, Configuration configuration) {
        this(s, configuration.mThreadAssert, configuration.mTransitionHelper);
    }

    private StateMachine(S s, ThreadAssert threadAssert, TransitionHelper transitionHelper) {
        this.mTransitions = new HashMap<>();
        this.mActions = new HashMap<>();
        threadAssert.expectSameThread();
        if (s != null) {
            this.mInitialState = s;
            this.mThreadAssert = threadAssert;
            this.mTransitionHelper = transitionHelper;
            return;
        }
        throw new IllegalArgumentException("Must specify an initialState");
    }
}
