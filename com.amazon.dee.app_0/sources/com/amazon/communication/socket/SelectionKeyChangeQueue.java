package com.amazon.communication.socket;
/* loaded from: classes12.dex */
public interface SelectionKeyChangeQueue {

    /* loaded from: classes12.dex */
    public static final class SelectionKeyChangeOperation {
        public final SelectionKeyOperation operation;
        public final int operationSet;
        public final DirectBiDiSocket socket;

        public SelectionKeyChangeOperation(DirectBiDiSocket directBiDiSocket, SelectionKeyOperation selectionKeyOperation, int i) {
            this.socket = directBiDiSocket;
            this.operation = selectionKeyOperation;
            this.operationSet = i;
        }
    }

    /* loaded from: classes12.dex */
    public enum SelectionKeyOperation {
        ADD,
        REMOVE
    }

    void queueChange(DirectBiDiSocket directBiDiSocket, SelectionKeyOperation selectionKeyOperation, int i);
}
