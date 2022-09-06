package com.amazon.scxml.internal;

import com.amazon.dee.app.elements.bridges.DeviceStateModule;
import com.amazon.deecomms.common.Constants;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: SCXMLStateMachineComponentAssembler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0015\u0010\u0012\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0013H\u0000¢\u0006\u0002\b\u0014J\u0016\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\"2\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0002R\u0010\u0010\u0005\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/scxml/internal/SCXMLStateMachineComponentAssembler;", "TUserContext", "Lcom/amazon/scxml/logging/Loggable;", "topLevelXml", "Lcom/amazon/scxml/internal/SimpleXMLElement;", "context", "(Lcom/amazon/scxml/internal/SimpleXMLElement;Ljava/lang/Object;)V", "Ljava/lang/Object;", "stateDocumentOrder", "", "transitionDocumentOrderWithinSourceState", "assembleCancel", "Lcom/amazon/scxml/internal/Cancel;", "element", "assembleInvocation", "Lcom/amazon/scxml/internal/Invocation;", "assembleItem", "", "assembleMachine", "Lcom/amazon/scxml/internal/ConcreteSCXMLStateMachine;", "assembleMachine$AlexaMobileAndroidVoice_TTA_release", "assembleOnEnter", "Lcom/amazon/scxml/internal/OnEnter;", "assembleOnExit", "Lcom/amazon/scxml/internal/OnExit;", "assembleRaise", "Lcom/amazon/scxml/internal/Raise;", "assembleSend", "Lcom/amazon/scxml/internal/Send;", "assembleState", "Lcom/amazon/scxml/internal/State;", "isParallel", "", "assembleTransition", "Lcom/amazon/scxml/internal/Transition;", "fatalError", "", "message", "", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class SCXMLStateMachineComponentAssembler<TUserContext> extends Loggable {
    private final TUserContext context;
    private int stateDocumentOrder;
    private final SimpleXMLElement topLevelXml;
    private int transitionDocumentOrderWithinSourceState;

    public SCXMLStateMachineComponentAssembler(@NotNull SimpleXMLElement topLevelXml, TUserContext tusercontext) {
        Intrinsics.checkParameterIsNotNull(topLevelXml, "topLevelXml");
        this.topLevelXml = topLevelXml;
        this.context = tusercontext;
    }

    private final Cancel assembleCancel(SimpleXMLElement simpleXMLElement) {
        String str = simpleXMLElement.getAttributes().get("sendid");
        if (str == null) {
            str = "";
        }
        return new Cancel(str);
    }

    private final Invocation assembleInvocation(SimpleXMLElement simpleXMLElement) {
        String str = simpleXMLElement.getAttributes().get("type");
        if (str == null) {
            str = "";
        }
        String str2 = simpleXMLElement.getAttributes().get("src");
        if (str2 == null) {
            str2 = "";
        }
        return new Invocation(str, str2);
    }

    private final Object assembleItem(SimpleXMLElement simpleXMLElement) {
        String name = simpleXMLElement.getName();
        switch (name.hashCode()) {
            case -1724158635:
                if (name.equals("transition")) {
                    return assembleTransition(simpleXMLElement);
                }
                break;
            case -1367724422:
                if (name.equals(DeviceStateModule.CANCEL)) {
                    return assembleCancel(simpleXMLElement);
                }
                break;
            case -1320432141:
                if (name.equals("onentry")) {
                    return assembleOnEnter(simpleXMLElement);
                }
                break;
            case -1183693704:
                if (name.equals("invoke")) {
                    return assembleInvocation(simpleXMLElement);
                }
                break;
            case -1012416643:
                if (name.equals("onexit")) {
                    return assembleOnExit(simpleXMLElement);
                }
                break;
            case 3526536:
                if (name.equals("send")) {
                    return assembleSend(simpleXMLElement);
                }
                break;
            case 108275692:
                if (name.equals("raise")) {
                    return assembleRaise(simpleXMLElement);
                }
                break;
            case 109273031:
                if (name.equals("scxml")) {
                    return assembleMachine(simpleXMLElement);
                }
                break;
            case 109757585:
                if (name.equals("state")) {
                    return assembleState(simpleXMLElement, false);
                }
                break;
            case 1171402247:
                if (name.equals("parallel")) {
                    return assembleState(simpleXMLElement, true);
                }
                break;
        }
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("assembleItem: Unknown or unsupported element: [");
        outline107.append(simpleXMLElement.getName());
        outline107.append(JsonReaderKt.END_LIST);
        fatalError(outline107.toString());
        throw null;
    }

    private final ConcreteSCXMLStateMachine<TUserContext> assembleMachine(SimpleXMLElement simpleXMLElement) {
        String str = simpleXMLElement.getAttributes().get(Constants.ACCESSORY_PRIVACY_INITIAL_STATUS);
        if (str == null) {
            str = "";
        }
        ArrayList arrayList = new ArrayList();
        for (SimpleXMLElement simpleXMLElement2 : simpleXMLElement.getChildren()) {
            Object assembleItem = assembleItem(simpleXMLElement2);
            if (!(assembleItem instanceof State)) {
                assembleItem = null;
            }
            State state = (State) assembleItem;
            if (state == null) {
                fatalError("assembleMachine: error assembling [" + simpleXMLElement2 + "] as? State");
                throw null;
            }
            arrayList.add(state);
        }
        Object[] array = arrayList.toArray(new State[0]);
        if (array != null) {
            return new ConcreteSCXMLStateMachine<>((State[]) array, str, this.context);
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }

    private final OnEnter assembleOnEnter(SimpleXMLElement simpleXMLElement) {
        ArrayList arrayList = new ArrayList();
        for (SimpleXMLElement simpleXMLElement2 : simpleXMLElement.getChildren()) {
            Object assembleItem = assembleItem(simpleXMLElement2);
            if (!(assembleItem instanceof Executable)) {
                assembleItem = null;
            }
            Executable executable = (Executable) assembleItem;
            if (executable == null) {
                fatalError("assembleOnEnter: error assembling [" + simpleXMLElement2 + "] as? Executable");
                throw null;
            } else if (executable != null) {
                arrayList.add(executable);
            }
        }
        return new OnEnter(arrayList);
    }

    private final OnExit assembleOnExit(SimpleXMLElement simpleXMLElement) {
        ArrayList arrayList = new ArrayList();
        for (SimpleXMLElement simpleXMLElement2 : simpleXMLElement.getChildren()) {
            Object assembleItem = assembleItem(simpleXMLElement2);
            if (!(assembleItem instanceof Executable)) {
                assembleItem = null;
            }
            Executable executable = (Executable) assembleItem;
            if (executable == null) {
                fatalError("assembleOnExit: error assembling [" + simpleXMLElement2 + "] as? Executable");
                throw null;
            } else if (executable != null) {
                arrayList.add(executable);
            }
        }
        return new OnExit(arrayList);
    }

    private final Raise assembleRaise(SimpleXMLElement simpleXMLElement) {
        String str = simpleXMLElement.getAttributes().get("event");
        if (str == null) {
            str = "";
        }
        return new Raise(str);
    }

    private final Send assembleSend(SimpleXMLElement simpleXMLElement) {
        String str = simpleXMLElement.getAttributes().get("event");
        String str2 = str != null ? str : "";
        String str3 = simpleXMLElement.getAttributes().get("delay");
        if (str3 == null) {
            str3 = "";
        }
        String str4 = simpleXMLElement.getAttributes().get("target");
        String str5 = str4 != null ? str4 : "";
        String str6 = simpleXMLElement.getAttributes().get("type");
        String str7 = str6 != null ? str6 : "";
        String str8 = simpleXMLElement.getAttributes().get("id");
        return new Send(str2, null, str5, str7, str8 != null ? str8 : "", Double.valueOf(Double.parseDouble(str3)));
    }

    private final State assembleState(SimpleXMLElement simpleXMLElement, boolean z) {
        ArrayList<State> arrayList = new ArrayList();
        ArrayList<Transition> arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        this.transitionDocumentOrderWithinSourceState = 0;
        int i = this.stateDocumentOrder;
        this.stateDocumentOrder = i + 1;
        for (SimpleXMLElement simpleXMLElement2 : simpleXMLElement.getChildren()) {
            Object assembleItem = assembleItem(simpleXMLElement2);
            if (assembleItem == null) {
                fatalError("assembleState: error assembling [" + simpleXMLElement2 + JsonReaderKt.END_LIST);
                throw null;
            } else if (assembleItem instanceof State) {
                arrayList.add(assembleItem);
            } else if (assembleItem instanceof Transition) {
                arrayList2.add(assembleItem);
            } else if (assembleItem instanceof OnEnter) {
                arrayList3.add(assembleItem);
            } else if (!(assembleItem instanceof OnExit)) {
                fatalError("assembleState: unexpected child element [" + simpleXMLElement2 + JsonReaderKt.END_LIST);
                throw null;
            } else {
                arrayList4.add(assembleItem);
            }
        }
        String str = simpleXMLElement.getAttributes().get(Constants.ACCESSORY_PRIVACY_INITIAL_STATUS);
        String str2 = str != null ? str : "";
        String str3 = simpleXMLElement.getAttributes().get("id");
        State state = new State(null, str3 != null ? str3 : "", z, i, arrayList2, str2, arrayList3, arrayList4, arrayList);
        for (State state2 : arrayList) {
            state2.setParent(state);
        }
        for (Transition transition : arrayList2) {
            transition.setSourceState$AlexaMobileAndroidVoice_TTA_release(state);
        }
        return state;
    }

    private final Transition assembleTransition(SimpleXMLElement simpleXMLElement) {
        List<String> split$default;
        int collectionSizeOrDefault;
        CharSequence trim;
        ArrayList arrayList = new ArrayList();
        for (SimpleXMLElement simpleXMLElement2 : simpleXMLElement.getChildren()) {
            Object assembleItem = assembleItem(simpleXMLElement2);
            if (!(assembleItem instanceof Executable)) {
                assembleItem = null;
            }
            Executable executable = (Executable) assembleItem;
            if (executable == null) {
                fatalError("assembleTransition: error assembling [" + simpleXMLElement2 + "] as? Executable");
                throw null;
            } else if (executable != null) {
                arrayList.add(executable);
            }
        }
        String str = simpleXMLElement.getAttributes().get("event");
        split$default = StringsKt__StringsKt.split$default((CharSequence) (str != null ? str : ""), new String[]{" "}, false, 0, 6, (Object) null);
        collectionSizeOrDefault = CollectionsKt__IterablesKt.collectionSizeOrDefault(split$default, 10);
        ArrayList arrayList2 = new ArrayList(collectionSizeOrDefault);
        for (String str2 : split$default) {
            if (str2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            trim = StringsKt__StringsKt.trim((CharSequence) str2);
            arrayList2.add(trim.toString());
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it2 = arrayList2.iterator();
        while (true) {
            boolean z = true;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            if (((String) next).length() <= 0) {
                z = false;
            }
            if (z) {
                arrayList3.add(next);
            }
        }
        String str3 = simpleXMLElement.getAttributes().get("target");
        String str4 = str3 != null ? str3 : "";
        String str5 = simpleXMLElement.getAttributes().get("cond");
        String str6 = str5 != null ? str5 : "";
        String str7 = simpleXMLElement.getAttributes().get("type");
        if (str7 == null) {
            str7 = "";
        }
        Transition transition = new Transition(arrayList3, str4, str6, arrayList, !Intrinsics.areEqual(str7, "internal"), this.transitionDocumentOrderWithinSourceState);
        this.transitionDocumentOrderWithinSourceState++;
        return transition;
    }

    private final Void fatalError(String str) {
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.e(tag, "fatalError: " + str);
        throw new Exception(str);
    }

    @Nullable
    public final ConcreteSCXMLStateMachine<TUserContext> assembleMachine$AlexaMobileAndroidVoice_TTA_release() {
        Object assembleItem = assembleItem(this.topLevelXml);
        if (!(assembleItem instanceof ConcreteSCXMLStateMachine)) {
            assembleItem = null;
        }
        return (ConcreteSCXMLStateMachine) assembleItem;
    }
}
