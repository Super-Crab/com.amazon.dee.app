package amazon.speech.model;
/* loaded from: classes.dex */
class SerializedPayload extends Payload {
    private static final String TAG = "SerializedPayload";
    private final String mPayload;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SerializedPayload(String str) {
        this.mPayload = str;
    }

    @Override // amazon.speech.model.Payload
    public String getPayload() {
        return this.mPayload;
    }
}
