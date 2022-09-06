package com.amazon.scxml.internal;

import com.amazon.scxml.SCXMLEvent;
import com.amazon.scxml.logging.Loggable;
import com.amazon.scxml.logging.Logger;
import com.android.tools.r8.GeneratedOutlineSupport1;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.serialization.json.internal.JsonReaderKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: Event.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0013\u0010\u0018\u001a\u00020\u00102\b\u0010\u0019\u001a\u0004\u0018\u00010\u0007H\u0096\u0002J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\u0006\u0010\u001b\u001a\u00020\u0004H\u0002J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016R\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\nX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u000e\u0010\u0017\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/scxml/internal/Event;", "Lcom/amazon/scxml/logging/Loggable;", "Lcom/amazon/scxml/SCXMLEvent;", "name", "", "sendId", "data", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "allMatchingEventNames", "", "getAllMatchingEventNames$AlexaMobileAndroidVoice_TTA_release", "()Ljava/util/Set;", "getData", "()Ljava/lang/Object;", "isCancelled", "", "()Z", "setCancelled", "(Z)V", "getName", "()Ljava/lang/String;", "getSendId", "uuid", "equals", "other", "getAllEventNames", "source", "hashCode", "", "toString", "AlexaMobileAndroidVoice-TTA_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes13.dex */
public final class Event extends Loggable implements SCXMLEvent {
    @NotNull
    private final Set<String> allMatchingEventNames;
    @Nullable
    private final Object data;
    private boolean isCancelled;
    @NotNull
    private final String name;
    @NotNull
    private final String sendId;
    private final String uuid;

    public Event(@NotNull String name, @NotNull String sendId, @Nullable Object obj) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(sendId, "sendId");
        this.name = name;
        this.sendId = sendId;
        this.data = obj;
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkExpressionValueIsNotNull(uuid, "UUID.randomUUID().toString()");
        this.uuid = uuid;
        this.allMatchingEventNames = getAllEventNames(getName());
        Logger log = Loggable.Companion.getLog();
        String tag = getTAG();
        log.d(tag, "matching event names for [" + this + "]: [" + this.allMatchingEventNames + JsonReaderKt.END_LIST);
    }

    private final Set<String> getAllEventNames(String str) {
        List<String> split$default;
        HashSet hashSet = new HashSet();
        hashSet.add("*");
        split$default = StringsKt__StringsKt.split$default((CharSequence) str, new String[]{"."}, false, 0, 6, (Object) null);
        if (split$default.size() <= 1) {
            hashSet.add(str);
            return hashSet;
        }
        String str2 = "";
        for (String str3 : split$default) {
            str2 = str2.length() == 0 ? str3 : GeneratedOutlineSupport1.outline48(str2, '.', str3);
            hashSet.add(str2);
        }
        return hashSet;
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof Event)) {
            obj = null;
        }
        Event event = (Event) obj;
        if (event != null) {
            return Intrinsics.areEqual(event.uuid, this.uuid);
        }
        return false;
    }

    @NotNull
    public final Set<String> getAllMatchingEventNames$AlexaMobileAndroidVoice_TTA_release() {
        return this.allMatchingEventNames;
    }

    @Override // com.amazon.scxml.SCXMLEvent
    @Nullable
    public Object getData() {
        return this.data;
    }

    @Override // com.amazon.scxml.SCXMLEvent
    @NotNull
    public String getName() {
        return this.name;
    }

    @NotNull
    public final String getSendId() {
        return this.sendId;
    }

    public int hashCode() {
        return this.uuid.hashCode();
    }

    public final boolean isCancelled() {
        return this.isCancelled;
    }

    public final void setCancelled(boolean z) {
        this.isCancelled = z;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("Event(name=");
        outline107.append(getName());
        outline107.append(", sendId=");
        return GeneratedOutlineSupport1.outline89(outline107, this.sendId, ')');
    }
}
