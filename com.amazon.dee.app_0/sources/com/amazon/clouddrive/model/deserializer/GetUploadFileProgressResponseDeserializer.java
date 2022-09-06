package com.amazon.clouddrive.model.deserializer;

import com.amazon.clouddrive.model.GetUploadFileProgressResponse;
/* loaded from: classes11.dex */
public class GetUploadFileProgressResponseDeserializer extends GetFileProgressResponseDeserializer<GetUploadFileProgressResponse> {
    public static final GetUploadFileProgressResponseDeserializer INSTANCE = new GetUploadFileProgressResponseDeserializer();

    private GetUploadFileProgressResponseDeserializer() {
    }

    @Override // com.amazon.clouddrive.model.deserializer.GetFileProgressResponseDeserializer
    public GetUploadFileProgressResponse newResponseInstance() {
        return new GetUploadFileProgressResponse();
    }
}
