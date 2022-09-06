package com.amazon.scxml.internal;

import com.amazon.scxml.internal.CandidateTransitionData;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
/* compiled from: Microstep.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u0002J\u000e\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003H\u0002J\u0006\u0010\t\u001a\u00020\nJ\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0003H\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/scxml/internal/Microstep;", "Lcom/amazon/scxml/logging/Loggable;", "candidates", "", "Lcom/amazon/scxml/internal/CandidateTransitionData;", "(Ljava/util/List;)V", "calculateEntrySet", "Lcom/amazon/scxml/internal/State;", "calculateExitSet", "execute", "", "getTransitionsInDocumentOrder", "Lcom/amazon/scxml/internal/Transition;", "Companion", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Microstep extends Loggable {
    public static final Companion Companion = new Companion(null);
    private final List<CandidateTransitionData> candidates;

    /* compiled from: Microstep.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bJ0\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\bH\u0002J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\n2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\nH\u0002¨\u0006\u0010"}, d2 = {"Lcom/amazon/scxml/internal/Microstep$Companion;", "Lcom/amazon/scxml/logging/Loggable;", "()V", "create", "Lcom/amazon/scxml/internal/Microstep;", "event", "Lcom/amazon/scxml/internal/Event;", "atomicStatesIndexedByEvents", "", "", "", "Lcom/amazon/scxml/internal/State;", "getCandidateTransitions", "Lcom/amazon/scxml/internal/CandidateTransitionData;", "resolveConflicts", "candidates", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
    /* loaded from: classes13.dex */
    public static final class Companion extends Loggable {

        @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
        /* loaded from: classes13.dex */
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[CandidateTransitionData.ConflictResolutionResult.values().length];

            static {
                $EnumSwitchMapping$0[CandidateTransitionData.ConflictResolutionResult.THIS_ONE_WINS.ordinal()] = 1;
                $EnumSwitchMapping$0[CandidateTransitionData.ConflictResolutionResult.THIS_ONE_LOSES.ordinal()] = 2;
                $EnumSwitchMapping$0[CandidateTransitionData.ConflictResolutionResult.NO_CONFLICT.ordinal()] = 3;
            }
        }

        private Companion() {
        }

        private final List<CandidateTransitionData> getCandidateTransitions(Event event, Map<String, ? extends List<? extends State>> map) {
            List<CandidateTransitionData> list;
            State firstActiveAtomicDescendantIncludingSelf;
            Set union;
            ArrayList arrayList = new ArrayList();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Set<State> linkedHashSet3 = new LinkedHashSet();
            for (String str : event.getAllMatchingEventNames$AlexaMobileAndroidVoice_TTA_release()) {
                List<? extends State> list2 = map.get(str);
                if (list2 == null) {
                    list2 = CollectionsKt__CollectionsKt.emptyList();
                }
                HashSet hashSet = new HashSet(list2);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : hashSet) {
                    if (((State) obj).isActive$AlexaMobileAndroidVoice_TTA_release()) {
                        arrayList2.add(obj);
                    }
                }
                union = CollectionsKt___CollectionsKt.union(linkedHashSet3, arrayList2);
                linkedHashSet3 = CollectionsKt___CollectionsKt.toMutableSet(union);
            }
            for (State state : linkedHashSet3) {
                if (state.isActive$AlexaMobileAndroidVoice_TTA_release() && !linkedHashSet2.contains(state)) {
                    linkedHashSet2.add(state);
                    Transition firstTransitionThatCanHandleEventInSelfOrAncestors = state.getFirstTransitionThatCanHandleEventInSelfOrAncestors(event);
                    if (firstTransitionThatCanHandleEventInSelfOrAncestors != null && !linkedHashSet.contains(firstTransitionThatCanHandleEventInSelfOrAncestors)) {
                        linkedHashSet.add(firstTransitionThatCanHandleEventInSelfOrAncestors);
                        State sourceState = firstTransitionThatCanHandleEventInSelfOrAncestors.getSourceState();
                        if (sourceState != null && (firstActiveAtomicDescendantIncludingSelf = sourceState.getFirstActiveAtomicDescendantIncludingSelf()) != null) {
                            arrayList.add(new CandidateTransitionData(firstTransitionThatCanHandleEventInSelfOrAncestors, sourceState, firstActiveAtomicDescendantIncludingSelf));
                        }
                    }
                }
            }
            list = CollectionsKt___CollectionsKt.toList(arrayList);
            return list;
        }

        private final List<CandidateTransitionData> resolveConflicts(List<CandidateTransitionData> list) {
            Set set;
            Set subtract;
            List<CandidateTransitionData> list2;
            if (list.size() <= 1) {
                return list;
            }
            Logger log = Loggable.Companion.getLog();
            String tag = getTAG();
            StringBuilder outline107 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution started with ");
            outline107.append(list.size());
            outline107.append(" transitions");
            log.i(tag, outline107.toString());
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            int size = list.size() - 2;
            if (size >= 0) {
                while (true) {
                    CandidateTransitionData candidateTransitionData = list.get(i);
                    if (!linkedHashSet.contains(candidateTransitionData)) {
                        int size2 = list.size();
                        for (int i2 = i + 1; i2 < size2; i2++) {
                            CandidateTransitionData candidateTransitionData2 = list.get(i2);
                            if (!linkedHashSet.contains(candidateTransitionData2)) {
                                int i3 = WhenMappings.$EnumSwitchMapping$0[candidateTransitionData.applyConflictResolution(candidateTransitionData2).ordinal()];
                                if (i3 == 1) {
                                    linkedHashSet.add(candidateTransitionData2);
                                    Logger log2 = Loggable.Companion.getLog();
                                    String tag2 = getTAG();
                                    StringBuilder outline1072 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution between (A) [");
                                    outline1072.append(candidateTransitionData.getTransition());
                                    outline1072.append("] and (B) [");
                                    outline1072.append(candidateTransitionData2.getTransition());
                                    outline1072.append("]: A wins");
                                    log2.i(tag2, outline1072.toString());
                                } else if (i3 == 2) {
                                    linkedHashSet.add(candidateTransitionData);
                                    Logger log3 = Loggable.Companion.getLog();
                                    String tag3 = getTAG();
                                    StringBuilder outline1073 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution between (A) [");
                                    outline1073.append(candidateTransitionData.getTransition());
                                    outline1073.append("] and (B) [");
                                    outline1073.append(candidateTransitionData2.getTransition());
                                    outline1073.append("]: B wins");
                                    log3.i(tag3, outline1073.toString());
                                }
                            }
                        }
                    }
                    if (i == size) {
                        break;
                    }
                    i++;
                }
            }
            if (linkedHashSet.isEmpty()) {
                Loggable.Companion.getLog().i(getTAG(), "scxml:conflict-resolution complete. 0 transitions were knocked-out");
                return list;
            }
            Logger log4 = Loggable.Companion.getLog();
            String tag4 = getTAG();
            StringBuilder outline1074 = GeneratedOutlineSupport1.outline107("scxml:conflict-resolution complete. ");
            outline1074.append(linkedHashSet.size());
            outline1074.append(" transition(s) were knocked-out");
            log4.i(tag4, outline1074.toString());
            set = CollectionsKt___CollectionsKt.toSet(list);
            subtract = CollectionsKt___CollectionsKt.subtract(set, linkedHashSet);
            list2 = CollectionsKt___CollectionsKt.toList(subtract);
            return list2;
        }

        @NotNull
        public final Microstep create(@NotNull Event event, @NotNull Map<String, ? extends List<? extends State>> atomicStatesIndexedByEvents) {
            Intrinsics.checkParameterIsNotNull(event, "event");
            Intrinsics.checkParameterIsNotNull(atomicStatesIndexedByEvents, "atomicStatesIndexedByEvents");
            return new Microstep(resolveConflicts(getCandidateTransitions(event, atomicStatesIndexedByEvents)));
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Microstep(@NotNull List<CandidateTransitionData> candidates) {
        Intrinsics.checkParameterIsNotNull(candidates, "candidates");
        this.candidates = candidates;
    }

    private final List<State> calculateEntrySet() {
        List mutableList;
        List<State> list;
        Set union;
        Collection linkedHashSet = new LinkedHashSet();
        for (CandidateTransitionData candidateTransitionData : this.candidates) {
            union = CollectionsKt___CollectionsKt.union(linkedHashSet, candidateTransitionData.getEntrySet());
            linkedHashSet = CollectionsKt___CollectionsKt.toMutableSet(union);
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) linkedHashSet);
        CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
        list = CollectionsKt___CollectionsKt.toList(mutableList);
        return list;
    }

    private final List<State> calculateExitSet() {
        List<State> mutableList;
        Set union;
        Collection linkedHashSet = new LinkedHashSet();
        for (CandidateTransitionData candidateTransitionData : this.candidates) {
            union = CollectionsKt___CollectionsKt.union(linkedHashSet, candidateTransitionData.getExitSet());
            linkedHashSet = CollectionsKt___CollectionsKt.toMutableSet(union);
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) linkedHashSet);
        CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
        CollectionsKt___CollectionsJvmKt.reverse(mutableList);
        return mutableList;
    }

    private final List<Transition> getTransitionsInDocumentOrder() {
        int collectionSizeOrDefault;
        List mutableList;
        List<Transition> list;
        List<CandidateTransitionData> list2 = this.candidates;
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10);
        ArrayList arrayList = new ArrayList(collectionSizeOrDefault);
        for (CandidateTransitionData candidateTransitionData : list2) {
            arrayList.add(candidateTransitionData.getTransition());
        }
        mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
        CollectionsKt__MutableCollectionsJVMKt.sort(mutableList);
        list = CollectionsKt___CollectionsKt.toList(mutableList);
        return list;
    }

    public final boolean execute() {
        Loggable.Companion.getLog().i(getTAG(), "SCXML:microstep starting");
        if (this.candidates.isEmpty()) {
            Loggable.Companion.getLog().i(getTAG(), "SCXML:microstep done - Nothing to execute");
            return false;
        }
        List<State> calculateExitSet = calculateExitSet();
        List<Transition> transitionsInDocumentOrder = getTransitionsInDocumentOrder();
        List<State> calculateEntrySet = calculateEntrySet();
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.i(tag, "SCXML:microstep exiting [" + calculateExitSet + JsonReaderKt.END_LIST);
        for (State state : calculateExitSet) {
            state.executeExit();
        }
        Logger log2 = Loggable.Companion.getLog();
        String tag2 = getTAG();
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("SCXML:microstep executing [");
        outline107.append(transitionsInDocumentOrder.size());
        outline107.append("] transitions");
        log2.i(tag2, outline107.toString());
        for (Transition transition : transitionsInDocumentOrder) {
            Logger log3 = Loggable.Companion.getLog();
            String tag3 = getTAG();
            log3.i(tag3, "SCXML:microstep executing transition: [" + transition + JsonReaderKt.END_LIST);
            transition.execute();
        }
        Logger log4 = Loggable.Companion.getLog();
        String tag4 = getTAG();
        log4.i(tag4, "SCXML:microstep entering [" + calculateEntrySet + JsonReaderKt.END_LIST);
        for (State state2 : calculateEntrySet) {
            state2.executeEntry();
        }
        Loggable.Companion.getLog().i(getTAG(), "SCXML:microstep done");
        return true;
    }
}
