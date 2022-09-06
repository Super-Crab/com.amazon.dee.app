package com.amazon.alexa.photos.events;

import com.google.gson.Gson;
/* loaded from: classes9.dex */
public abstract class PhotosUploaderEvent {
    public String getPayloadData() {
        return new Gson().toJson(this);
    }
}
