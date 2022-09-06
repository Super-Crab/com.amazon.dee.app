package com.amazon.alexa;

import android.util.Log;
import com.amazon.alexa.client.alexaservice.messages.AvsApiConstants;
import com.amazon.alexa.client.core.messages.DialogRequestIdentifier;
import com.amazon.alexa.client.core.messages.Header;
import com.amazon.alexa.client.core.messages.Message;
import com.amazon.alexa.client.core.messages.MessageIdentifier;
import com.amazon.alexa.client.core.messages.RawStringPayload;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TextMessageCreator.java */
/* loaded from: classes.dex */
public class Qjn {
    public static final String zZm = "Qjn";

    public static Message zZm(String str, DialogRequestIdentifier dialogRequestIdentifier) {
        String str2;
        Header build = Header.builder().setMessageIdentifier(MessageIdentifier.createRandom()).setDialogRequestIdentifier(dialogRequestIdentifier).setName(AvsApiConstants.Input.Text.Directives.TextMessage.zZm).setNamespace(AvsApiConstants.Input.Text.zZm).build();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("text", str);
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            Log.i(zZm, "Unable to create sendText payload");
            str2 = "";
        }
        return Message.create(build, RawStringPayload.create(str2));
    }
}
