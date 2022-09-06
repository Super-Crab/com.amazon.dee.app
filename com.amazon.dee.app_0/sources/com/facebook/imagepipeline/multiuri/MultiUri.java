package com.facebook.imagepipeline.multiuri;

import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class MultiUri {
    @Nullable
    private ImageRequest mHighResImageRequest;
    @Nullable
    private ImageRequest mLowResImageRequest;
    @Nullable
    private ImageRequest[] mMultiImageRequests;

    /* loaded from: classes2.dex */
    public static class Builder {
        @Nullable
        private ImageRequest mHighResImageRequest;
        @Nullable
        private ImageRequest mLowResImageRequest;
        @Nullable
        private ImageRequest[] mMultiImageRequests;

        public MultiUri build() {
            return new MultiUri(this);
        }

        public Builder setHighResImageRequest(@Nullable ImageRequest highResImageRequest) {
            this.mHighResImageRequest = highResImageRequest;
            return this;
        }

        public Builder setImageRequests(@Nullable ImageRequest... multiImageRequests) {
            this.mMultiImageRequests = multiImageRequests;
            return this;
        }

        public Builder setLowResImageRequest(@Nullable ImageRequest lowResImageRequest) {
            this.mLowResImageRequest = lowResImageRequest;
            return this;
        }

        private Builder() {
        }
    }

    public static Builder create() {
        return new Builder();
    }

    @Nullable
    public ImageRequest getHighResImageRequest() {
        return this.mHighResImageRequest;
    }

    @Nullable
    public ImageRequest getLowResImageRequest() {
        return this.mLowResImageRequest;
    }

    @Nullable
    public ImageRequest[] getMultiImageRequests() {
        return this.mMultiImageRequests;
    }

    private MultiUri(Builder builder) {
        this.mLowResImageRequest = builder.mLowResImageRequest;
        this.mHighResImageRequest = builder.mHighResImageRequest;
        this.mMultiImageRequests = builder.mMultiImageRequests;
    }
}
