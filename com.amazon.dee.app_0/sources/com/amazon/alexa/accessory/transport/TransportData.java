package com.amazon.alexa.accessory.transport;

import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.SizedSource;
import com.android.tools.r8.GeneratedOutlineSupport1;
import kotlinx.serialization.json.internal.JsonReaderKt;
/* loaded from: classes6.dex */
public final class TransportData {
    private final SizedSource source;
    private final int stream;

    public TransportData(SizedSource sizedSource, int i) {
        Preconditions.notNull(sizedSource, "source");
        this.source = sizedSource;
        this.stream = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || TransportData.class != obj.getClass()) {
            return false;
        }
        TransportData transportData = (TransportData) obj;
        if (this.stream == transportData.stream) {
            return this.source.equals(transportData.source);
        }
        return false;
    }

    public SizedSource getSource() {
        return this.source;
    }

    public int getStream() {
        return this.stream;
    }

    public int hashCode() {
        return (this.source.hashCode() * 31) + this.stream;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("TransportData{source=");
        outline107.append(this.source);
        outline107.append(", stream=");
        return GeneratedOutlineSupport1.outline85(outline107, this.stream, JsonReaderKt.END_OBJ);
    }
}
