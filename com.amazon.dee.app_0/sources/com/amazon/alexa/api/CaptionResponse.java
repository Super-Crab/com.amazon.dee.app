package com.amazon.alexa.api;

import android.os.Bundle;
import com.amazon.alexa.api.Bundles;
/* loaded from: classes6.dex */
public class CaptionResponse {
    private final String content;
    private final CaptionFormat type;

    /* loaded from: classes6.dex */
    static class Adapter implements u<CaptionResponse> {

        /* JADX INFO: Access modifiers changed from: package-private */
        /* loaded from: classes6.dex */
        public enum CaptionResponseKeys implements Bundles.Key {
            CAPTION_CONTENT,
            CAPTION_TYPE
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.amazon.alexa.api.u
        /* renamed from: createFromBundle */
        public CaptionResponse mo844createFromBundle(Bundle bundle) {
            return new CaptionResponse(bundle.getString(CaptionResponseKeys.CAPTION_CONTENT.name()), CaptionFormat.valueOf(bundle.getString(CaptionResponseKeys.CAPTION_TYPE.name(), CaptionFormat.RAW.name())));
        }

        @Override // com.amazon.alexa.api.u
        public Bundle toBundle(CaptionResponse captionResponse) {
            Bundle bundle = new Bundle();
            bundle.putString(CaptionResponseKeys.CAPTION_CONTENT.name(), captionResponse.content);
            if (captionResponse.type == null) {
                bundle.putString(CaptionResponseKeys.CAPTION_TYPE.name(), CaptionFormat.RAW.name());
            } else {
                bundle.putString(CaptionResponseKeys.CAPTION_TYPE.name(), captionResponse.type.name());
            }
            return bundle;
        }
    }

    /* loaded from: classes6.dex */
    public enum CaptionFormat {
        WEBVTT,
        RAW
    }

    public CaptionResponse(String str, CaptionFormat captionFormat) {
        this.content = str;
        this.type = captionFormat;
    }

    public String getContent() {
        return this.content;
    }

    public CaptionFormat getType() {
        return this.type;
    }
}
