package com.facebook.imagepipeline.producers;

import androidx.annotation.VisibleForTesting;
import com.amazon.mobile.heremapsexplore.Constants.ReactProperties;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.PooledByteBufferFactory;
import com.facebook.common.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.TriState;
import com.facebook.imageformat.DefaultImageFormats;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.common.RotationOptions;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.JobScheduler;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.transcoder.ImageTranscodeResult;
import com.facebook.imagepipeline.transcoder.ImageTranscoder;
import com.facebook.imagepipeline.transcoder.ImageTranscoderFactory;
import com.facebook.imagepipeline.transcoder.JpegTranscoderUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
/* loaded from: classes2.dex */
public class ResizeAndRotateProducer implements Producer<EncodedImage> {
    private static final String INPUT_IMAGE_FORMAT = "Image format";
    @VisibleForTesting
    static final int MIN_TRANSFORM_INTERVAL_MS = 100;
    private static final String ORIGINAL_SIZE_KEY = "Original size";
    private static final String PRODUCER_NAME = "ResizeAndRotateProducer";
    private static final String REQUESTED_SIZE_KEY = "Requested size";
    private static final String TRANSCODER_ID = "Transcoder id";
    private static final String TRANSCODING_RESULT = "Transcoding result";
    private final Executor mExecutor;
    private final ImageTranscoderFactory mImageTranscoderFactory;
    private final Producer<EncodedImage> mInputProducer;
    private final boolean mIsResizingEnabled;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    /* loaded from: classes2.dex */
    private class TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final ImageTranscoderFactory mImageTranscoderFactory;
        private boolean mIsCancelled;
        private final boolean mIsResizingEnabled;
        private final JobScheduler mJobScheduler;
        private final ProducerContext mProducerContext;

        TransformingConsumer(final Consumer<EncodedImage> consumer, final ProducerContext producerContext, final boolean isResizingEnabled, final ImageTranscoderFactory imageTranscoderFactory) {
            super(consumer);
            this.mIsCancelled = false;
            this.mProducerContext = producerContext;
            Boolean resizingAllowedOverride = this.mProducerContext.getImageRequest().getResizingAllowedOverride();
            this.mIsResizingEnabled = resizingAllowedOverride != null ? resizingAllowedOverride.booleanValue() : isResizingEnabled;
            this.mImageTranscoderFactory = imageTranscoderFactory;
            this.mJobScheduler = new JobScheduler(ResizeAndRotateProducer.this.mExecutor, new JobScheduler.JobRunnable() { // from class: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.1
                @Override // com.facebook.imagepipeline.producers.JobScheduler.JobRunnable
                public void run(EncodedImage encodedImage, int status) {
                    TransformingConsumer transformingConsumer = TransformingConsumer.this;
                    transformingConsumer.doTransform(encodedImage, status, (ImageTranscoder) Preconditions.checkNotNull(transformingConsumer.mImageTranscoderFactory.createImageTranscoder(encodedImage.getImageFormat(), TransformingConsumer.this.mIsResizingEnabled)));
                }
            }, 100);
            this.mProducerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.ResizeAndRotateProducer.TransformingConsumer.2
                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onCancellationRequested() {
                    TransformingConsumer.this.mJobScheduler.clearJob();
                    TransformingConsumer.this.mIsCancelled = true;
                    consumer.onCancellation();
                }

                @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
                public void onIsIntermediateResultExpectedChanged() {
                    if (TransformingConsumer.this.mProducerContext.isIntermediateResultExpected()) {
                        TransformingConsumer.this.mJobScheduler.scheduleJob();
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doTransform(EncodedImage encodedImage, int status, ImageTranscoder imageTranscoder) {
            this.mProducerContext.getProducerListener().onProducerStart(this.mProducerContext, ResizeAndRotateProducer.PRODUCER_NAME);
            ImageRequest imageRequest = this.mProducerContext.getImageRequest();
            PooledByteBufferOutputStream mo6911newOutputStream = ResizeAndRotateProducer.this.mPooledByteBufferFactory.mo6911newOutputStream();
            try {
                ImageTranscodeResult transcode = imageTranscoder.transcode(encodedImage, mo6911newOutputStream, imageRequest.getRotationOptions(), imageRequest.getResizeOptions(), null, 85);
                if (transcode.getTranscodeStatus() != 2) {
                    Map<String, String> extraMap = getExtraMap(encodedImage, imageRequest.getResizeOptions(), transcode, imageTranscoder.getIdentifier());
                    CloseableReference of = CloseableReference.of(mo6911newOutputStream.mo6913toByteBuffer());
                    try {
                        EncodedImage encodedImage2 = new EncodedImage(of);
                        encodedImage2.setImageFormat(DefaultImageFormats.JPEG);
                        encodedImage2.parseMetaData();
                        this.mProducerContext.getProducerListener().onProducerFinishWithSuccess(this.mProducerContext, ResizeAndRotateProducer.PRODUCER_NAME, extraMap);
                        if (transcode.getTranscodeStatus() != 1) {
                            status |= 16;
                        }
                        getConsumer().onNewResult(encodedImage2, status);
                        EncodedImage.closeSafely(encodedImage2);
                        return;
                    } finally {
                        CloseableReference.closeSafely(of);
                    }
                }
                throw new RuntimeException("Error while transcoding the image");
            } catch (Exception e) {
                this.mProducerContext.getProducerListener().onProducerFinishWithFailure(this.mProducerContext, ResizeAndRotateProducer.PRODUCER_NAME, e, null);
                if (BaseConsumer.isLast(status)) {
                    getConsumer().onFailure(e);
                }
            } finally {
                mo6911newOutputStream.close();
            }
        }

        private void forwardNewResult(EncodedImage newResult, int status, ImageFormat imageFormat) {
            EncodedImage newResultsForJpegOrHeif;
            if (imageFormat != DefaultImageFormats.JPEG && imageFormat != DefaultImageFormats.HEIF) {
                newResultsForJpegOrHeif = getNewResultForImagesWithoutExifData(newResult);
            } else {
                newResultsForJpegOrHeif = getNewResultsForJpegOrHeif(newResult);
            }
            getConsumer().onNewResult(newResultsForJpegOrHeif, status);
        }

        @Nullable
        private EncodedImage getCloneWithRotationApplied(EncodedImage encodedImage, int angle) {
            EncodedImage cloneOrNull = EncodedImage.cloneOrNull(encodedImage);
            if (cloneOrNull != null) {
                cloneOrNull.setRotationAngle(angle);
            }
            return cloneOrNull;
        }

        @Nullable
        private Map<String, String> getExtraMap(EncodedImage encodedImage, @Nullable ResizeOptions resizeOptions, @Nullable ImageTranscodeResult transcodeResult, @Nullable String transcoderId) {
            String str;
            if (!this.mProducerContext.getProducerListener().requiresExtraMap(this.mProducerContext, ResizeAndRotateProducer.PRODUCER_NAME)) {
                return null;
            }
            String str2 = encodedImage.getWidth() + ReactProperties.HereMapMarker.X + encodedImage.getHeight();
            if (resizeOptions != null) {
                str = resizeOptions.width + ReactProperties.HereMapMarker.X + resizeOptions.height;
            } else {
                str = "Unspecified";
            }
            HashMap hashMap = new HashMap();
            hashMap.put(ResizeAndRotateProducer.INPUT_IMAGE_FORMAT, String.valueOf(encodedImage.getImageFormat()));
            hashMap.put(ResizeAndRotateProducer.ORIGINAL_SIZE_KEY, str2);
            hashMap.put(ResizeAndRotateProducer.REQUESTED_SIZE_KEY, str);
            hashMap.put("queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
            hashMap.put(ResizeAndRotateProducer.TRANSCODER_ID, transcoderId);
            hashMap.put(ResizeAndRotateProducer.TRANSCODING_RESULT, String.valueOf(transcodeResult));
            return ImmutableMap.copyOf((Map) hashMap);
        }

        @Nullable
        private EncodedImage getNewResultForImagesWithoutExifData(EncodedImage encodedImage) {
            RotationOptions rotationOptions = this.mProducerContext.getImageRequest().getRotationOptions();
            return (rotationOptions.useImageMetadata() || !rotationOptions.rotationEnabled()) ? encodedImage : getCloneWithRotationApplied(encodedImage, rotationOptions.getForcedAngle());
        }

        @Nullable
        private EncodedImage getNewResultsForJpegOrHeif(EncodedImage encodedImage) {
            return (this.mProducerContext.getImageRequest().getRotationOptions().canDeferUntilRendered() || encodedImage.getRotationAngle() == 0 || encodedImage.getRotationAngle() == -1) ? encodedImage : getCloneWithRotationApplied(encodedImage, 0);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.facebook.imagepipeline.producers.BaseConsumer
        public void onNewResultImpl(@Nullable EncodedImage newResult, int status) {
            if (this.mIsCancelled) {
                return;
            }
            boolean isLast = BaseConsumer.isLast(status);
            if (newResult == null) {
                if (!isLast) {
                    return;
                }
                getConsumer().onNewResult(null, 1);
                return;
            }
            ImageFormat imageFormat = newResult.getImageFormat();
            TriState shouldTransform = ResizeAndRotateProducer.shouldTransform(this.mProducerContext.getImageRequest(), newResult, (ImageTranscoder) Preconditions.checkNotNull(this.mImageTranscoderFactory.createImageTranscoder(imageFormat, this.mIsResizingEnabled)));
            if (!isLast && shouldTransform == TriState.UNSET) {
                return;
            }
            if (shouldTransform != TriState.YES) {
                forwardNewResult(newResult, status, imageFormat);
            } else if (!this.mJobScheduler.updateJob(newResult, status)) {
            } else {
                if (!isLast && !this.mProducerContext.isIntermediateResultExpected()) {
                    return;
                }
                this.mJobScheduler.scheduleJob();
            }
        }
    }

    public ResizeAndRotateProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final Producer<EncodedImage> inputProducer, final boolean isResizingEnabled, final ImageTranscoderFactory imageTranscoderFactory) {
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = (PooledByteBufferFactory) Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mImageTranscoderFactory = (ImageTranscoderFactory) Preconditions.checkNotNull(imageTranscoderFactory);
        this.mIsResizingEnabled = isResizingEnabled;
    }

    private static boolean shouldRotate(RotationOptions rotationOptions, EncodedImage encodedImage) {
        return !rotationOptions.canDeferUntilRendered() && (JpegTranscoderUtils.getRotationAngle(rotationOptions, encodedImage) != 0 || shouldRotateUsingExifOrientation(rotationOptions, encodedImage));
    }

    private static boolean shouldRotateUsingExifOrientation(RotationOptions rotationOptions, EncodedImage encodedImage) {
        if (rotationOptions.rotationEnabled() && !rotationOptions.canDeferUntilRendered()) {
            return JpegTranscoderUtils.INVERTED_EXIF_ORIENTATIONS.contains(Integer.valueOf(encodedImage.getExifOrientation()));
        }
        encodedImage.setExifOrientation(0);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TriState shouldTransform(ImageRequest request, EncodedImage encodedImage, ImageTranscoder imageTranscoder) {
        if (encodedImage != null && encodedImage.getImageFormat() != ImageFormat.UNKNOWN) {
            if (!imageTranscoder.canTranscode(encodedImage.getImageFormat())) {
                return TriState.NO;
            }
            return TriState.valueOf(shouldRotate(request.getRotationOptions(), encodedImage) || imageTranscoder.canResize(encodedImage, request.getRotationOptions(), request.getResizeOptions()));
        }
        return TriState.UNSET;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext context) {
        this.mInputProducer.produceResults(new TransformingConsumer(consumer, context, this.mIsResizingEnabled, this.mImageTranscoderFactory), context);
    }
}
