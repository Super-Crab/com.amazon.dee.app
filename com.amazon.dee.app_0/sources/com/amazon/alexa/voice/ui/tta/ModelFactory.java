package com.amazon.alexa.voice.ui.tta;

import androidx.annotation.NonNull;
import com.amazon.alexa.voice.ui.tta.TtaResponseCard;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
public interface ModelFactory<T extends TtaResponseCard> {
    T build(@NonNull JSONObject jSONObject) throws JSONException;
}
