package amazon.speech.csmshark;

import java.util.Collection;
/* loaded from: classes.dex */
public interface ISharkableEnqueuer {
    Collection<Sharkable> getSharkables(int i);

    boolean isRecording();

    void startRecording(CSMSharkFlag... cSMSharkFlagArr);

    void stopRecording();
}
