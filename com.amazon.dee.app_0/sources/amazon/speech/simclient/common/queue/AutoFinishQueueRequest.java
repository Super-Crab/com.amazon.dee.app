package amazon.speech.simclient.common.queue;
/* loaded from: classes.dex */
public abstract class AutoFinishQueueRequest extends QueueRequest {
    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // amazon.speech.simclient.common.queue.QueueRequest
    public void invoke() {
        super.invoke();
        onFinished();
    }
}
