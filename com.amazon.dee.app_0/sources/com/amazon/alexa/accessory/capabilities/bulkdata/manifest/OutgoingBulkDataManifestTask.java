package com.amazon.alexa.accessory.capabilities.bulkdata.manifest;

import com.amazon.alexa.accessory.TaskManager;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataCategory;
import com.amazon.alexa.accessory.capabilities.bulkdata.BulkDataOrchestrator;
import com.amazon.alexa.accessory.capabilities.bulkdata.TaskCompletionListener;
import com.amazon.alexa.accessory.capabilities.bulkdata.exceptions.BulkDataException;
import com.amazon.alexa.accessory.internal.util.Logger;
import com.amazon.alexa.accessory.internal.util.Preconditions;
import com.amazon.alexa.accessory.protocol.Accessories;
import com.amazon.alexa.accessory.protocol.Bulkdata;
import com.amazon.alexa.accessory.protocol.Common;
import com.amazon.alexa.accessory.streams.control.ControlResponseHandler;
import com.amazon.alexa.accessory.streams.control.ControlStream;
import com.amazon.alexa.accessory.streams.control.ProtobufControlMessage;
import com.google.common.collect.ImmutableList;
import java.util.List;
/* loaded from: classes.dex */
public class OutgoingBulkDataManifestTask implements TaskManager.Task {
    private final BulkDataCategory category;
    private final ControlStream controlStream;
    private final List<CategorySpecificData> manifestEntries;
    private int manifestEntriesAcknowledged = 0;
    private final int manifestEntriesCount;
    private final ControlResponseHandler responseHandler;
    private final BulkDataOrchestrator.SendManifestCallback sendBulkDataManifestCallback;
    private final TaskCompletionListener<OutgoingBulkDataManifestTask> taskCompletionListener;

    /* loaded from: classes.dex */
    private class OutgoingManifestResponseHandler implements ControlResponseHandler {
        private final BulkDataOrchestrator.SendManifestCallback sendManifestCallback;

        public OutgoingManifestResponseHandler(BulkDataOrchestrator.SendManifestCallback sendManifestCallback) {
            Preconditions.notNull(sendManifestCallback, "sendManifestCallback");
            this.sendManifestCallback = sendManifestCallback;
        }

        @Override // com.amazon.alexa.accessory.streams.control.ControlResponseHandler
        public void onResponseReceived(ControlStream controlStream, Accessories.Command command, Accessories.Response response) {
            if (response.getErrorCode() == Common.ErrorCode.SUCCESS) {
                OutgoingBulkDataManifestTask.access$008(OutgoingBulkDataManifestTask.this);
                Logger.d("Manifest entry received by accessory: %d/%d ", Integer.valueOf(OutgoingBulkDataManifestTask.this.manifestEntriesAcknowledged), Integer.valueOf(OutgoingBulkDataManifestTask.this.manifestEntriesCount));
            } else {
                this.sendManifestCallback.onError(new BulkDataException("An error occurred while sending manifest entries to the accessory", response.getErrorCode()));
                OutgoingBulkDataManifestTask.this.dispose();
            }
            if (OutgoingBulkDataManifestTask.this.manifestEntriesCount == OutgoingBulkDataManifestTask.this.manifestEntriesAcknowledged) {
                Logger.d("All manifest entries received by accessory (Total count: %d)", Integer.valueOf(OutgoingBulkDataManifestTask.this.manifestEntriesAcknowledged));
                this.sendManifestCallback.onComplete();
                OutgoingBulkDataManifestTask.this.dispose();
            }
        }
    }

    public OutgoingBulkDataManifestTask(BulkDataCategory bulkDataCategory, Iterable<CategorySpecificData> iterable, ControlStream controlStream, TaskCompletionListener<OutgoingBulkDataManifestTask> taskCompletionListener, BulkDataOrchestrator.SendManifestCallback sendManifestCallback) {
        Preconditions.notNull(bulkDataCategory, "category");
        Preconditions.notNull(iterable, "manifestEntries");
        Preconditions.notNull(controlStream, "controlStream");
        Preconditions.notNull(taskCompletionListener, "taskCallback");
        Preconditions.notNull(sendManifestCallback, "sendBulkDataManifestCallback");
        this.category = bulkDataCategory;
        this.manifestEntries = ImmutableList.copyOf(iterable);
        this.controlStream = controlStream;
        this.taskCompletionListener = taskCompletionListener;
        this.sendBulkDataManifestCallback = sendManifestCallback;
        this.manifestEntriesCount = this.manifestEntries.size();
        this.responseHandler = new OutgoingManifestResponseHandler(sendManifestCallback);
    }

    static /* synthetic */ int access$008(OutgoingBulkDataManifestTask outgoingBulkDataManifestTask) {
        int i = outgoingBulkDataManifestTask.manifestEntriesAcknowledged;
        outgoingBulkDataManifestTask.manifestEntriesAcknowledged = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispose() {
        ControlResponseHandler controlResponseHandler = this.responseHandler;
        if (controlResponseHandler != null) {
            this.controlStream.removeResponseHandler(controlResponseHandler);
        }
        this.taskCompletionListener.onTaskComplete(this);
    }

    private void sendManifests() {
        try {
            this.controlStream.addResponseHandler(Accessories.Command.BULK_DATA_MANIFEST_ENTRY, this.responseHandler);
            int i = 0;
            for (CategorySpecificData categorySpecificData : this.manifestEntries) {
                this.controlStream.dispatch(new ProtobufControlMessage(Accessories.ControlEnvelope.newBuilder().setCommand(Accessories.Command.BULK_DATA_MANIFEST_ENTRY).setBulkDataManifestEntry(Bulkdata.BulkDataManifestEntry.newBuilder().setCategory(this.category.value()).setCategorySpecificData(categorySpecificData.getByteString()).setManifestIndex(i).setManifestCount(this.manifestEntriesCount).mo10084build()).mo10084build()));
                i++;
            }
        } catch (Throwable th) {
            Logger.e("OutgoingBulkDataManifestEntryTask failed to send manifests due to an exception");
            this.sendBulkDataManifestCallback.onError(new BulkDataException("An error occurred while sending manifest entries to the accessory", th));
            dispose();
        }
    }

    @Override // com.amazon.alexa.accessory.TaskManager.Task
    public void onStateChanged(int i, int i2) {
        if (3 == i2 && (i == 0 || 1 == i)) {
            sendManifests();
        } else if (3 != i) {
        } else {
            if (2 != i2 && 1 != i2) {
                return;
            }
            dispose();
        }
    }
}
