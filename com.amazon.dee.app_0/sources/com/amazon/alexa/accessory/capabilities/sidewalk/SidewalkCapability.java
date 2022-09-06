package com.amazon.alexa.accessory.capabilities.sidewalk;

import com.amazon.alexa.accessory.AccessoryCapability;
import com.amazon.alexa.accessory.AccessoryDescriptor;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProducer;
import com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProvider;
import com.amazon.alexa.accessory.streams.sidewalk.SidewalkStream;
/* loaded from: classes.dex */
public class SidewalkCapability extends AccessoryCapability {
    private static final String TAG = "SidewalkCapability:";
    private final SidewalkActionHandler sidewalkActionHandler;
    private final SidewalkProducer sidewalkProducer;
    private final SidewalkProvider sidewalkProvider;
    private SidewalkStream sidewalkStream;

    /* loaded from: classes.dex */
    public final class SidewalkActionHandler implements SidewalkProducer.ActionHandler {
        public SidewalkActionHandler() {
        }

        @Override // com.amazon.alexa.accessory.repositories.sidewalk.SidewalkProducer.ActionHandler
        public void handleWriteData(SizedSource sizedSource) {
            if (SidewalkCapability.this.sidewalkStream != null) {
                SidewalkCapability.this.sidewalkStream.dispatchData(sizedSource);
            } else {
                Logger.e("%s Stream is not set. Capability might not have been initialized", SidewalkCapability.TAG);
            }
        }
    }

    public SidewalkCapability(SidewalkProducer sidewalkProducer, SidewalkProvider sidewalkProvider) {
        Preconditions.notNull(sidewalkProducer, "sidewalkProducer");
        Preconditions.notNull(sidewalkProvider, "sidewalkProvider");
        this.sidewalkProducer = sidewalkProducer;
        this.sidewalkProvider = sidewalkProvider;
        this.sidewalkActionHandler = new SidewalkActionHandler();
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onDispose(AccessoryDescriptor accessoryDescriptor) {
        Logger.d("%s Disposing capability...", TAG);
        this.sidewalkProducer.detachActionHandler(this.sidewalkActionHandler);
        accessoryDescriptor.remove(this.sidewalkStream);
        this.sidewalkStream.dispose();
        this.sidewalkProvider.dispose();
        this.sidewalkStream = null;
    }

    @Override // com.amazon.alexa.accessory.AccessoryCapability
    protected void onInitialize(AccessoryDescriptor accessoryDescriptor) {
        Logger.d("%s Initializing capability...", TAG);
        this.sidewalkProducer.attachActionHandler(this.sidewalkActionHandler);
        this.sidewalkStream = new SidewalkStream(this.sidewalkProvider, accessoryDescriptor.getAuthenticationAwareDispatcher());
        accessoryDescriptor.add(this.sidewalkStream);
        this.sidewalkProvider.initialize();
    }
}
