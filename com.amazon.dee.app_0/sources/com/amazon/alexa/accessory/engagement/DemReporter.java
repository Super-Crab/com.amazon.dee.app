package com.amazon.alexa.accessory.engagement;

import androidx.annotation.NonNull;
import com.amazon.alexa.accessory.AccessorySession;
@FunctionalInterface
/* loaded from: classes.dex */
interface DemReporter {
    void report(@NonNull DemType demType, @NonNull AccessorySession accessorySession, String str);
}
