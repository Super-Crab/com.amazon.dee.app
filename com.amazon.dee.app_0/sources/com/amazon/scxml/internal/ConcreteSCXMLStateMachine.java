package com.amazon.scxml.internal;

import com.amazon.alexa.handsfree.metrics.caching.JsonFields;
import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.deecomms.common.Constants;
import com.amazon.scxml.NativeFunctions;
import com.amazon.scxml.SCXMLContext;
import com.amazon.scxml.SCXMLEvent;
import com.amazon.scxml.SCXMLStateMachine;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ConcreteSCXMLStateMachine.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u00032\u00020\u0004B%\b\u0000\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00028\u0000¢\u0006\u0002\u0010\u000bJ\b\u0010(\u001a\u00020\u0016H\u0002J\u0010\u0010)\u001a\u00020\u00162\u0006\u0010*\u001a\u00020+H\u0002J*\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u001cH\u0002J\b\u00102\u001a\u00020\u001cH\u0002J\u0010\u00103\u001a\u00020\u001c2\u0006\u00104\u001a\u00020\tH\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u00106\u001a\u000207H\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u00108\u001a\u000209H\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u0010:\u001a\u00020;H\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u00105\u001a\u00020\u00162\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\tH\u0002J\u0010\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\tH\u0002J\b\u0010E\u001a\u00020\u0016H\u0002J\b\u0010F\u001a\u00020\u0016H\u0016J\b\u0010G\u001a\u00020\u0016H\u0002JF\u0010H\u001a\u00020\u00162<\u0010I\u001a8\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\t0K¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\u001c¢\u0006\f\bL\u0012\b\bM\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020\u00160JH\u0016J\u0010\u0010O\u001a\u00020\u00162\u0006\u0010P\u001a\u00020\u0007H\u0002J\b\u0010Q\u001a\u00020\u0016H\u0002J\b\u0010R\u001a\u00020\u0016H\u0002J\b\u0010S\u001a\u00020\u0016H\u0002J\u0010\u0010T\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+H\u0002J\u0010\u0010U\u001a\u00020\u00162\u0006\u0010P\u001a\u00020\u0007H\u0016J\u001a\u0010V\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u000100H\u0016J*\u0010W\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\t2\u0006\u0010.\u001a\u00020\t2\b\u0010/\u001a\u0004\u0018\u0001002\u0006\u0010X\u001a\u00020YH\u0002J9\u0010Z\u001a\u00020\u00162\u0014\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001f2\u0014\u0010 \u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001fH\u0000¢\u0006\u0002\b[J\b\u0010\\\u001a\u00020\u0016H\u0016J\u0018\u0010]\u001a\u00020\u001c2\u0006\u0010-\u001a\u00020\t2\u0006\u0010^\u001a\u00020_H\u0016J\u0010\u0010`\u001a\u00020\u001c2\u0006\u0010^\u001a\u00020_H\u0016R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00070\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00130\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0014\u001a \u0012\u0004\u0012\u00020\t\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u000f0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010 \u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010#\u0012\u0004\u0012\u00020\u0016\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lcom/amazon/scxml/internal/ConcreteSCXMLStateMachine;", "TUserContext", "Lcom/amazon/scxml/logging/Loggable;", "Lcom/amazon/scxml/SCXMLStateMachine;", "Lcom/amazon/scxml/internal/SCXMLStateMachineExecutor;", "topLevelStates", "", "Lcom/amazon/scxml/internal/State;", Constants.ACCESSORY_PRIVACY_INITIAL_STATUS, "", "userContext", "([Lcom/amazon/scxml/internal/State;Ljava/lang/String;Ljava/lang/Object;)V", "activeStates", "", "allStates", "", "currentContext", "Lcom/amazon/scxml/SCXMLContext;", "eventToAtomicStatesMap", "", "eventWaitingMap", "Lkotlin/Function0;", "", "externalEventQueue", "Lcom/amazon/scxml/internal/EventQueueWithDelay;", "internalEventQueue", "Lcom/amazon/scxml/internal/EventQueue;", "isInitialized", "", "isStarted", "nativeEvaluators", "Lcom/amazon/scxml/NativeFunctions;", "nativeInvocations", "onProcessedMacroStep", "Lkotlin/Function1;", "Lcom/amazon/scxml/SCXMLEvent;", "q", "Lcom/amazon/scxml/internal/DispatchQueue;", "root", "Lcom/amazon/scxml/internal/RootState;", "_unsafeInitialize", "_unsafeProcessEventWaitingMap", "event", "Lcom/amazon/scxml/internal/Event;", "_unsafeSendEvent", JsonFields.EVENT_NAME, "sendId", "eventData", "", "isInvokedInternal", "_unsafeValidateInternalStructure", "evaluateCondition", "cond", "execute", DeviceStateModule.CANCEL, "Lcom/amazon/scxml/internal/Cancel;", "unhandled", "Lcom/amazon/scxml/internal/Executable;", "invocation", "Lcom/amazon/scxml/internal/Invocation;", "raise", "Lcom/amazon/scxml/internal/Raise;", "send", "Lcom/amazon/scxml/internal/Send;", "executeNative", "nativeMethodName", "fatalError", "", "message", "indexAtomicStatesByEvents", "initialize", "initializeAllStates", "insertBlock", "completion", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isValid", "loadStatesRecursive", "state", "moveToFirstState", "printValidityMessage", "processMacrostep", "processMicrostep", "registerActiveStatusChange", "sendEvent", "sendEventWithDelay", "delay", "", "setNativeAdaptors", "setNativeAdaptors$AlexaMobileAndroidVoice_TTA_release", "start", "waitForEvent", "timeoutMilliseconds", "", "waitUntilProcessed", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class ConcreteSCXMLStateMachine<TUserContext> extends Loggable implements SCXMLStateMachine, SCXMLStateMachineExecutor {
    private final Set<State> activeStates;
    private final Map<String, State> allStates;
    private SCXMLContext<TUserContext> currentContext;
    private final Map<String, List<State>> eventToAtomicStatesMap;
    private final Map<String, Map<String, Function0<Unit>>> eventWaitingMap;
    private final EventQueueWithDelay externalEventQueue;
    private final EventQueue internalEventQueue;
    private boolean isInitialized;
    private boolean isStarted;
    private NativeFunctions<TUserContext, Boolean> nativeEvaluators;
    private NativeFunctions<TUserContext, Unit> nativeInvocations;
    private Function1<? super SCXMLEvent, Unit> onProcessedMacroStep;
    private final DispatchQueue q;
    private final RootState root;

    public ConcreteSCXMLStateMachine(@NotNull State[] topLevelStates, @NotNull String initial, TUserContext tusercontext) {
        Intrinsics.checkParameterIsNotNull(topLevelStates, "topLevelStates");
        Intrinsics.checkParameterIsNotNull(initial, "initial");
        this.eventWaitingMap = new LinkedHashMap();
        this.activeStates = new LinkedHashSet();
        this.internalEventQueue = new EventQueue();
        this.externalEventQueue = new EventQueueWithDelay();
        this.q = new DispatchQueue();
        this.eventToAtomicStatesMap = new LinkedHashMap();
        this.allStates = new LinkedHashMap();
        this.root = new RootState(initial, topLevelStates);
        this.currentContext = new SCXMLContext<>(tusercontext, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void _unsafeInitialize() {
        if (this.isInitialized) {
            return;
        }
        loadStatesRecursive(this.root);
        indexAtomicStatesByEvents();
        initializeAllStates();
        this.isInitialized = true;
    }

    private final void _unsafeProcessEventWaitingMap(Event event) {
        List<Function0> list;
        Map<String, Function0<Unit>> map = this.eventWaitingMap.get(event.getName());
        if (map != null) {
            list = CollectionsKt___CollectionsKt.toList(map.values());
            for (Function0 function0 : list) {
                function0.mo12560invoke();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void _unsafeSendEvent(String str, String str2, Object obj, boolean z) {
        if (!this.isStarted) {
            return;
        }
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        StringBuilder outline116 = GeneratedOutlineSupport1.outline116("SCXML: SEND [", str, ", ", str2, "] - INTERNAL_INVOCATION?(");
        outline116.append(z);
        outline116.append(')');
        log.i(tag, outline116.toString());
        this.externalEventQueue.push(new Event(str, str2, obj));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean _unsafeValidateInternalStructure() {
        return this.root.isStateValid();
    }

    private final void executeNative(String str) {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "SCXML:execute native [" + str + JsonReaderKt.END_LIST);
        NativeFunctions<TUserContext, Unit> nativeFunctions = this.nativeInvocations;
        if (nativeFunctions == null) {
            Logger log2 = Loggable.Companion.getLog();
            String tag2 = getTAG();
            log2.i(tag2, "SCXML:can't execute native [" + str + "] - nativeInvocations == nil");
        } else if (!nativeFunctions.functionExists$AlexaMobileAndroidVoice_TTA_release(str)) {
            Logger log3 = Loggable.Companion.getLog();
            String tag3 = getTAG();
            log3.i(tag3, "SCXML:can't execute native [" + str + "] - not registered with nativeInvocations");
        } else {
            nativeFunctions.execute$AlexaMobileAndroidVoice_TTA_release(str, this.currentContext);
        }
    }

    private final Void fatalError(String str) {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.e(tag, "fatalError: " + str);
        throw new Exception(String.valueOf(str));
    }

    private final void indexAtomicStatesByEvents() {
        for (State state : this.allStates.values()) {
            if (state.isAtomic()) {
                for (String str : state.getAllRelevantEventsFromSelfOrAncestors()) {
                    List<State> list = this.eventToAtomicStatesMap.get(str);
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(state);
                    this.eventToAtomicStatesMap.put(str, list);
                }
            }
        }
    }

    private final void initializeAllStates() {
        for (State state : this.allStates.values()) {
            state.initialize(this, this.allStates);
        }
    }

    private final void loadStatesRecursive(State state) {
        if (this.allStates.get(state.getId()) == null) {
            this.allStates.put(state.getId(), state);
            for (State state2 : state.getSubstates()) {
                loadStatesRecursive(state2);
            }
            return;
        }
        fatalError("duplicate state: [" + state + JsonReaderKt.END_LIST);
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveToFirstState() {
        List emptyList;
        List emptyList2;
        List<State> sorted;
        emptyList = CollectionsKt__CollectionsKt.emptyList();
        String id = this.root.getId();
        emptyList2 = CollectionsKt__CollectionsKt.emptyList();
        Transition transition = new Transition(emptyList, id, "", emptyList2, true, Integer.MIN_VALUE);
        transition.setTarget(this.root);
        transition.setMachine(this);
        sorted = CollectionsKt___CollectionsKt.sorted(transition.getEntrySet());
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "ENTRY SET = [" + sorted + JsonReaderKt.END_LIST);
        for (State state : sorted) {
            state.executeEntry();
        }
    }

    private final void printValidityMessage() {
        if (!_unsafeValidateInternalStructure()) {
            Loggable.Companion.getLog().e(getTAG(), "SCXML:valid-status = FALSE");
        }
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:56)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:30)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:18)
        */
    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0099 A[EDGE_INSN: B:22:0x0099->B:14:0x0099 ?: BREAK  , SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x0095 -> B:13:0x0097). Please submit an issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void processMacrostep() {
        /*
            r10 = this;
            com.amazon.scxml.logging.Loggable$Companion r0 = com.amazon.scxml.logging.Loggable.Companion
            com.amazon.scxml.logging.Logger r0 = r0.getLog()
            java.lang.String r1 = r10.getTAG()
            java.lang.String r2 = "SCXML:macrostep START"
            r0.i(r1, r2)
            com.amazon.scxml.internal.EventQueueWithDelay r0 = r10.externalEventQueue
            com.amazon.scxml.internal.Event r0 = r0.pop()
            r1 = 1
            r2 = 93
            r3 = 0
            if (r0 == 0) goto L55
            r10._unsafeProcessEventWaitingMap(r0)
            com.amazon.scxml.logging.Loggable$Companion r4 = com.amazon.scxml.logging.Loggable.Companion
            com.amazon.scxml.logging.Logger r4 = r4.getLog()
            java.lang.String r5 = r10.getTAG()
            java.lang.String r6 = "SCXML:processing event from External scxml.EventQueue: ["
            java.lang.StringBuilder r6 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r6)
            java.lang.String r7 = r0.getName()
            r6.append(r7)
            r6.append(r2)
            java.lang.String r6 = r6.toString()
            r4.i(r5, r6)
            com.amazon.scxml.SCXMLContext<TUserContext> r4 = r10.currentContext
            java.lang.Object r4 = r4.getUserContext()
            com.amazon.scxml.SCXMLContext r5 = new com.amazon.scxml.SCXMLContext
            r5.<init>(r4, r0)
            r10.currentContext = r5
            boolean r4 = r10.processMicrostep(r0)
            if (r4 == 0) goto L56
            r4 = r0
            r0 = r10
            goto L97
        L55:
            r0 = 0
        L56:
            r4 = r0
            r0 = r10
        L58:
            com.amazon.scxml.internal.EventQueue r5 = r0.internalEventQueue
            com.amazon.scxml.internal.Event r5 = r5.pop()
            if (r5 == 0) goto L99
            r0._unsafeProcessEventWaitingMap(r5)
            com.amazon.scxml.logging.Loggable$Companion r6 = com.amazon.scxml.logging.Loggable.Companion
            com.amazon.scxml.logging.Logger r6 = r6.getLog()
            java.lang.String r7 = r0.getTAG()
            java.lang.String r8 = "SCXML:processing event from Internal scxml.EventQueue: ["
            java.lang.StringBuilder r8 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r8)
            java.lang.String r9 = r5.getName()
            r8.append(r9)
            r8.append(r2)
            java.lang.String r8 = r8.toString()
            r6.i(r7, r8)
            com.amazon.scxml.SCXMLContext<TUserContext> r6 = r0.currentContext
            java.lang.Object r6 = r6.getUserContext()
            com.amazon.scxml.SCXMLContext r7 = new com.amazon.scxml.SCXMLContext
            r7.<init>(r6, r5)
            r0.currentContext = r7
            boolean r5 = r0.processMicrostep(r5)
            if (r5 == 0) goto L58
        L97:
            r3 = r1
            goto L58
        L99:
            if (r3 != 0) goto Lc3
            com.amazon.scxml.logging.Loggable$Companion r1 = com.amazon.scxml.logging.Loggable.Companion
            com.amazon.scxml.logging.Logger r1 = r1.getLog()
            java.lang.String r3 = r0.getTAG()
            java.lang.String r5 = "SCXML:macrostep DONE (Did nothing): ["
            java.lang.StringBuilder r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r5)
            java.util.Set<com.amazon.scxml.internal.State> r6 = r0.activeStates
            java.util.List r6 = kotlin.collections.CollectionsKt.toMutableList(r6)
            java.util.List r6 = kotlin.collections.CollectionsKt.sorted(r6)
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.i(r3, r2)
            goto Led
        Lc3:
            r0.printValidityMessage()
            com.amazon.scxml.logging.Loggable$Companion r1 = com.amazon.scxml.logging.Loggable.Companion
            com.amazon.scxml.logging.Logger r1 = r1.getLog()
            java.lang.String r3 = r0.getTAG()
            java.lang.String r5 = "SCXML:macrostep DONE: ["
            java.lang.StringBuilder r5 = com.android.tools.r8.GeneratedOutlineSupport1.outline107(r5)
            java.util.Set<com.amazon.scxml.internal.State> r6 = r0.activeStates
            java.util.List r6 = kotlin.collections.CollectionsKt.toMutableList(r6)
            java.util.List r6 = kotlin.collections.CollectionsKt.sorted(r6)
            r5.append(r6)
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r1.i(r3, r2)
        Led:
            kotlin.jvm.functions.Function1<? super com.amazon.scxml.SCXMLEvent, kotlin.Unit> r0 = r0.onProcessedMacroStep
            if (r0 == 0) goto Lf7
            java.lang.Object r0 = r0.mo12165invoke(r4)
            kotlin.Unit r0 = (kotlin.Unit) r0
        Lf7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.scxml.internal.ConcreteSCXMLStateMachine.processMacrostep():void");
    }

    private final boolean processMicrostep(Event event) {
        return Microstep.Companion.create(event, this.eventToAtomicStatesMap).execute();
    }

    private final void sendEventWithDelay(String str, String str2, Object obj, double d) {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "SCXML:sendEventWithDelay(" + str + ", " + d + ')');
        this.externalEventQueue.push(new Event(str, str2, obj), d, new ConcreteSCXMLStateMachine$sendEventWithDelay$1(this, str));
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public boolean evaluateCondition(@NotNull String cond) {
        CharSequence trim;
        boolean startsWith;
        boolean endsWith;
        Intrinsics.checkParameterIsNotNull(cond, "cond");
        trim = StringsKt__StringsKt.trim((CharSequence) cond);
        String obj = trim.toString();
        if (obj.length() == 0) {
            return true;
        }
        startsWith = StringsKt__StringsJVMKt.startsWith(obj, "NOT(", true);
        if (startsWith) {
            endsWith = StringsKt__StringsJVMKt.endsWith(obj, ")", true);
            if (endsWith) {
                String substring = obj.substring(4, obj.length() - 1);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                return !evaluateCondition(substring);
            }
        }
        NativeFunctions<TUserContext, Boolean> nativeFunctions = this.nativeEvaluators;
        if (nativeFunctions == null) {
            Logger log = Loggable.Companion.getLog();
            String tag = getTAG();
            log.e(tag, "can't execute nativeEvaluators [" + obj + "] - nativeEvaluators == nil");
            return false;
        } else if (!nativeFunctions.functionExists$AlexaMobileAndroidVoice_TTA_release(obj)) {
            Logger log2 = Loggable.Companion.getLog();
            String tag2 = getTAG();
            log2.e(tag2, "SCXML:can't evaluate native [" + obj + "] - not registered with nativeEvaluators - returning FALSE");
            return false;
        } else {
            Boolean execute$AlexaMobileAndroidVoice_TTA_release = nativeFunctions.execute$AlexaMobileAndroidVoice_TTA_release(obj, this.currentContext);
            if (execute$AlexaMobileAndroidVoice_TTA_release == null) {
                return false;
            }
            return execute$AlexaMobileAndroidVoice_TTA_release.booleanValue();
        }
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public void execute(@NotNull Cancel cancel) {
        Intrinsics.checkParameterIsNotNull(cancel, "cancel");
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:execute cancel [");
        outline107.append(cancel.getSendId());
        outline107.append(JsonReaderKt.END_LIST);
        log.i(tag, outline107.toString());
        this.externalEventQueue.cancelEvent(cancel.getSendId());
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public void initialize() {
        this.q.async(new ConcreteSCXMLStateMachine$initialize$1(this));
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public void insertBlock(@NotNull Function2<? super List<String>, ? super Boolean, Unit> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        this.q.async(new ConcreteSCXMLStateMachine$insertBlock$1(this, completion));
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public void registerActiveStatusChange(@NotNull State state) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        if (state.isActive$AlexaMobileAndroidVoice_TTA_release()) {
            this.activeStates.add(state);
        } else {
            this.activeStates.remove(state);
        }
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public void sendEvent(@NotNull String eventName, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(eventName, "eventName");
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "sendEvent - scxml [" + eventName + JsonReaderKt.END_LIST);
        this.q.async(new ConcreteSCXMLStateMachine$sendEvent$1(this, eventName, obj));
    }

    public final void setNativeAdaptors$AlexaMobileAndroidVoice_TTA_release(@Nullable NativeFunctions<TUserContext, Boolean> nativeFunctions, @Nullable NativeFunctions<TUserContext, Unit> nativeFunctions2) {
        this.nativeEvaluators = nativeFunctions;
        this.nativeInvocations = nativeFunctions2;
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public void start() {
        this.q.async(new ConcreteSCXMLStateMachine$start$1(this));
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public boolean waitForEvent(@NotNull String eventName, long j) {
        Intrinsics.checkParameterIsNotNull(eventName, "eventName");
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        Semaphore semaphore = new Semaphore(0);
        this.q.async(new ConcreteSCXMLStateMachine$waitForEvent$1(this, eventName, new ConcreteSCXMLStateMachine$waitForEvent$func$1(this, eventName, uuid, semaphore), uuid));
        boolean tryAcquire = semaphore.tryAcquire(j, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            this.q.async(new ConcreteSCXMLStateMachine$waitForEvent$2(this, eventName, uuid));
        }
        return tryAcquire;
    }

    @Override // com.amazon.scxml.SCXMLStateMachine
    public boolean waitUntilProcessed(long j) {
        Semaphore semaphore = new Semaphore(0);
        this.q.async(new ConcreteSCXMLStateMachine$waitUntilProcessed$1(semaphore));
        return semaphore.tryAcquire(j, TimeUnit.MILLISECONDS);
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public void execute(@NotNull Executable unhandled) {
        Intrinsics.checkParameterIsNotNull(unhandled, "unhandled");
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "programmer error: execute should be overridden. execute(unhandled) was called by [" + unhandled + JsonReaderKt.END_LIST);
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x005c, code lost:
        if ((r5.toString().length() == 0) != false) goto L39;
     */
    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void execute(@org.jetbrains.annotations.NotNull com.amazon.scxml.internal.Send r12) {
        /*
            Method dump skipped, instructions count: 358
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.scxml.internal.ConcreteSCXMLStateMachine.execute(com.amazon.scxml.internal.Send):void");
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public void execute(@NotNull Raise raise) {
        Intrinsics.checkParameterIsNotNull(raise, "raise");
        String event = raise.getEvent();
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "SCXML:raiseEvent [" + event + JsonReaderKt.END_LIST);
        this.internalEventQueue.push(new Event(event, "", null));
    }

    @Override // com.amazon.scxml.internal.SCXMLStateMachineExecutor
    public void execute(@NotNull Invocation invocation) {
        Intrinsics.checkParameterIsNotNull(invocation, "invocation");
        if (!Intrinsics.areEqual(invocation.getType(), "native")) {
            return;
        }
        executeNative(invocation.getSrc());
    }
}
