package com.amazon.alexa.accessory;

import java.util.concurrent.atomic.AtomicBoolean;
/* loaded from: classes.dex */
public abstract class AccessoryCapability {
    private final AtomicBoolean initialized = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void dispose(AccessoryDescriptor accessoryDescriptor) {
        if (this.initialized.compareAndSet(true, false)) {
            onDispose(accessoryDescriptor);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void initialize(AccessoryDescriptor accessoryDescriptor) {
        if (this.initialized.compareAndSet(false, true)) {
            onInitialize(accessoryDescriptor);
            return;
        }
        throw new IllegalStateException("A capability is already initialized!");
    }

    public boolean isInitialized() {
        return this.initialized.get();
    }

    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
    }

    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
    }
}
