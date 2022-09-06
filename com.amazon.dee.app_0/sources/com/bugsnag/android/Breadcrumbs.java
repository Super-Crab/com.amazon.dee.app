package com.bugsnag.android;

import androidx.annotation.NonNull;
import com.bugsnag.android.JsonStream;
import com.bugsnag.android.NativeInterface;
import java.io.IOException;
import java.util.Observable;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class Breadcrumbs extends Observable implements JsonStream.Streamable {
    private static final int MAX_PAYLOAD_SIZE = 4096;
    private final Configuration configuration;
    final Queue<Breadcrumb> store = new ConcurrentLinkedQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    public Breadcrumbs(Configuration configuration) {
        this.configuration = configuration;
    }

    private void addToStore(@NonNull Breadcrumb breadcrumb) {
        try {
            if (breadcrumb.payloadSize() > 4096) {
                Logger.warn("Dropping breadcrumb because payload exceeds 4KB limit");
                return;
            }
            this.store.add(breadcrumb);
            pruneBreadcrumbs();
            setChanged();
            notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.ADD_BREADCRUMB, breadcrumb));
        } catch (IOException e) {
            Logger.warn("Dropping breadcrumb because it could not be serialized", e);
        }
    }

    private void pruneBreadcrumbs() {
        int maxBreadcrumbs = this.configuration.getMaxBreadcrumbs();
        while (this.store.size() > maxBreadcrumbs) {
            this.store.poll();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void add(@NonNull Breadcrumb breadcrumb) {
        addToStore(breadcrumb);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.store.clear();
        setChanged();
        notifyObservers(new NativeInterface.Message(NativeInterface.MessageType.CLEAR_BREADCRUMBS, null));
    }

    @Override // com.bugsnag.android.JsonStream.Streamable
    public void toStream(@NonNull JsonStream jsonStream) throws IOException {
        pruneBreadcrumbs();
        jsonStream.beginArray();
        for (Breadcrumb breadcrumb : this.store) {
            breadcrumb.toStream(jsonStream);
        }
        jsonStream.endArray();
    }
}
