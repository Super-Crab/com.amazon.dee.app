package io.ktor.network.selector;

import io.ktor.util.InternalAPI;
import io.ktor.util.KtorExperimentalAPI;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
/* compiled from: SelectorManager.kt */
@KtorExperimentalAPI
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0087\u0001\u0018\u0000 \u000b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\f"}, d2 = {"Lio/ktor/network/selector/SelectInterest;", "", "flag", "", "(Ljava/lang/String;II)V", "getFlag", "()I", "READ", "WRITE", "ACCEPT", "CONNECT", "Companion", "ktor-network"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes3.dex */
public enum SelectInterest {
    READ(1),
    WRITE(4),
    ACCEPT(16),
    CONNECT(8);
    
    @NotNull
    private static final int[] flags;
    private static final int size;
    private final int flag;
    public static final Companion Companion = new Companion(null);
    @NotNull
    private static final SelectInterest[] AllInterests = values();

    /* compiled from: SelectorManager.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\u0010\n\u0002\u0010\t\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u00020\u00108\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0011\u0010\u0002\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lio/ktor/network/selector/SelectInterest$Companion;", "", "()V", "AllInterests", "", "Lio/ktor/network/selector/SelectInterest;", "AllInterests$annotations", "getAllInterests", "()[Lio/ktor/network/selector/SelectInterest;", "[Lio/ktor/network/selector/SelectInterest;", "flags", "", "flags$annotations", "getFlags", "()[I", "size", "", "size$annotations", "getSize", "()I", "ktor-network"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes3.dex */
    public static final class Companion {
        private Companion() {
        }

        @InternalAPI
        public static /* synthetic */ void AllInterests$annotations() {
        }

        @InternalAPI
        public static /* synthetic */ void flags$annotations() {
        }

        @InternalAPI
        public static /* synthetic */ void size$annotations() {
        }

        @NotNull
        public final SelectInterest[] getAllInterests() {
            return SelectInterest.AllInterests;
        }

        @NotNull
        public final int[] getFlags() {
            return SelectInterest.flags;
        }

        public final int getSize() {
            return SelectInterest.size;
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    static {
        int[] intArray;
        SelectInterest[] values = values();
        ArrayList arrayList = new ArrayList(values.length);
        for (SelectInterest selectInterest : values) {
            arrayList.add(Integer.valueOf(selectInterest.flag));
        }
        intArray = CollectionsKt___CollectionsKt.toIntArray(arrayList);
        flags = intArray;
        size = values().length;
    }

    SelectInterest(int i) {
        this.flag = i;
    }

    public final int getFlag() {
        return this.flag;
    }
}
