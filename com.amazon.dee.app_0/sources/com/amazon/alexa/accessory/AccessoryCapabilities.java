package com.amazon.alexa.accessory;

import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.io.SizedSource;
import com.amazon.alexa.accessory.transport.TransportDispatcher;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes.dex */
public final class AccessoryCapabilities {
    private final List<AccessoryCapability> capabilityList;
    private final CapabilityDescriptor descriptor;
    private final TransportDispatcher dispatcherAuthenticationAgnostic;
    private final TransportDispatcher dispatcherAuthenticationAware;
    private volatile boolean initialized;
    private final Object lock;
    private volatile boolean released;
    private final SparseArray<List<AccessoryStream>> streamList;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public final class CapabilityDescriptor implements AccessoryDescriptor {
        CapabilityDescriptor() {
        }

        @Override // com.amazon.alexa.accessory.AccessoryDescriptor
        public void add(AccessoryStream accessoryStream) {
            Preconditions.notNull(accessoryStream, "stream");
            synchronized (AccessoryCapabilities.this.lock) {
                List list = (List) AccessoryCapabilities.this.streamList.get(accessoryStream.getId());
                if (list == null) {
                    list = new ArrayList();
                    AccessoryCapabilities.this.streamList.put(accessoryStream.getId(), list);
                }
                list.add(accessoryStream);
            }
        }

        @Override // com.amazon.alexa.accessory.AccessoryDescriptor
        public TransportDispatcher getAuthenticationAgnosticDispatcher() {
            return AccessoryCapabilities.this.dispatcherAuthenticationAgnostic;
        }

        @Override // com.amazon.alexa.accessory.AccessoryDescriptor
        public TransportDispatcher getAuthenticationAwareDispatcher() {
            return AccessoryCapabilities.this.dispatcherAuthenticationAware;
        }

        @Override // com.amazon.alexa.accessory.AccessoryDescriptor
        public void remove(AccessoryStream accessoryStream) {
            Preconditions.notNull(accessoryStream, "stream");
            synchronized (AccessoryCapabilities.this.lock) {
                List list = (List) AccessoryCapabilities.this.streamList.get(accessoryStream.getId());
                if (list != null) {
                    list.remove(accessoryStream);
                }
            }
        }
    }

    public AccessoryCapabilities(TransportDispatcher transportDispatcher, TransportDispatcher transportDispatcher2) {
        Preconditions.notNull(transportDispatcher, "dispatcherAuthenticationAware");
        Preconditions.notNull(transportDispatcher2, "dispatcherAuthenticationAgnostic");
        this.dispatcherAuthenticationAware = transportDispatcher;
        this.dispatcherAuthenticationAgnostic = transportDispatcher2;
        this.capabilityList = new ArrayList();
        this.streamList = new SparseArray<>();
        this.lock = new Object();
        this.descriptor = new CapabilityDescriptor();
    }

    private void reset() {
        synchronized (this.lock) {
            if (!this.initialized) {
                return;
            }
            this.initialized = false;
            for (int size = this.capabilityList.size() - 1; size >= 0; size--) {
                this.capabilityList.get(size).dispose(this.descriptor);
            }
            this.capabilityList.clear();
            this.streamList.clear();
        }
    }

    public void add(AccessoryCapability accessoryCapability) {
        Preconditions.notNull(accessoryCapability, "capability");
        synchronized (this.lock) {
            if (!this.released) {
                this.capabilityList.add(accessoryCapability);
                if (this.initialized) {
                    accessoryCapability.initialize(this.descriptor);
                }
            } else {
                throw new IllegalStateException("Accessory capabilities were released, unable to add capability!");
            }
        }
    }

    @VisibleForTesting(otherwise = 5)
    public int getSize() {
        synchronized (this.lock) {
            if (this.capabilityList != null) {
                return this.capabilityList.size();
            }
            return 0;
        }
    }

    public boolean handleData(SizedSource sizedSource, int i) throws Exception {
        synchronized (this.lock) {
            if (this.released) {
                Logger.w("Accessory capabilities were released and should not handle a packet!");
                return false;
            }
            List<AccessoryStream> list = this.streamList.get(i);
            ArrayList arrayList = list == null ? null : new ArrayList(list);
            if (arrayList != null) {
                int size = arrayList.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (((AccessoryStream) arrayList.get(i2)).handleData(sizedSource)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public void initialize() {
        synchronized (this.lock) {
            if (!this.released && !this.initialized) {
                this.initialized = true;
                for (int size = this.capabilityList.size() - 1; size >= 0; size--) {
                    this.capabilityList.get(size).initialize(this.descriptor);
                }
            }
        }
    }

    public void release() {
        if (this.released) {
            return;
        }
        this.released = true;
        reset();
    }

    public void remove(AccessoryCapability accessoryCapability) {
        Preconditions.notNull(accessoryCapability, "capability");
        synchronized (this.lock) {
            if (this.capabilityList.remove(accessoryCapability)) {
                accessoryCapability.dispose(this.descriptor);
            }
        }
    }
}
