package com.amazon.alexa.accessory.speechapi.listeners;

import com.amazon.alexa.lifecycle.api.LifecycleEvent;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* compiled from: ReadyState.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00032\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/amazon/alexa/accessory/speechapi/listeners/ReadyState;", "", LifecycleEvent.IS_READY, "", "isQuickReady", "(ZZ)V", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "AlexaAccessoryAndroid-speech-api_release"}, k = 1, mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ReadyState {
    private final boolean isQuickReady;
    private final boolean isReady;

    public ReadyState(boolean z, boolean z2) {
        this.isReady = z;
        this.isQuickReady = z2;
    }

    public static /* synthetic */ ReadyState copy$default(ReadyState readyState, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = readyState.isReady;
        }
        if ((i & 2) != 0) {
            z2 = readyState.isQuickReady;
        }
        return readyState.copy(z, z2);
    }

    public final boolean component1() {
        return this.isReady;
    }

    public final boolean component2() {
        return this.isQuickReady;
    }

    @NotNull
    public final ReadyState copy(boolean z, boolean z2) {
        return new ReadyState(z, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (!(obj instanceof ReadyState)) {
                return false;
            }
            ReadyState readyState = (ReadyState) obj;
            return this.isReady == readyState.isReady && this.isQuickReady == readyState.isQuickReady;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.isReady;
        int i = 1;
        if (z) {
            z = true;
        }
        int i2 = z ? 1 : 0;
        int i3 = z ? 1 : 0;
        int i4 = i2 * 31;
        boolean z2 = this.isQuickReady;
        if (!z2) {
            i = z2 ? 1 : 0;
        }
        return i4 + i;
    }

    public final boolean isQuickReady() {
        return this.isQuickReady;
    }

    public final boolean isReady() {
        return this.isReady;
    }

    @NotNull
    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ReadyState(isReady=");
        outline107.append(this.isReady);
        outline107.append(", isQuickReady=");
        return GeneratedOutlineSupport1.outline97(outline107, this.isQuickReady, ")");
    }
}
