package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetOverwriteFileProgressResponse;
/* loaded from: classes11.dex */
public class GetOverwriteFileProgressResponseDeserializer extends GetFileProgressResponseDeserializer<GetOverwriteFileProgressResponse> {
    public static final GetOverwriteFileProgressResponseDeserializer INSTANCE = new GetOverwriteFileProgressResponseDeserializer();

    private GetOverwriteFileProgressResponseDeserializer() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.GetFileProgressResponseDeserializer
    public GetOverwriteFileProgressResponse newResponseInstance() {
        return new GetOverwriteFileProgressResponse();
    }
}
