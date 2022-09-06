package amazon.speech.nexusclient.event;

import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes.dex */
public interface INexusEvent {
    JSONObject toJSON() throws JSONException;
}
