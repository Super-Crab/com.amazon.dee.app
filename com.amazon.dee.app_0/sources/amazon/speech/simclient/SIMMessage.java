package amazon.speech.simclient;

import amazon.speech.model.DirectiveEnvelope;
import java.util.List;
/* loaded from: classes.dex */
public class SIMMessage {
    List<DirectiveEnvelope> mDirectives;

    public SIMMessage(List<DirectiveEnvelope> list) {
        this.mDirectives = list;
    }
}
