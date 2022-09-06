package com.amazon.clouddrive.model.serializer;

import com.amazon.clouddrive.model.ImageProperties;
import java.io.IOException;
import org.codehaus.jackson.JsonGenerator;
/* loaded from: classes11.dex */
public class ImagePropertiesSerializer implements JsonSerializer<ImageProperties> {
    public static final JsonSerializer<ImageProperties> INSTANCE = new ImagePropertiesSerializer();
    private final ImagePropertiesFieldSerializer mFieldSerializer = new ImagePropertiesFieldSerializer();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes11.dex */
    public static class ImagePropertiesFieldSerializer implements JsonFieldSerializer<ImageProperties> {
        ImagePropertiesFieldSerializer() {
        }

        @Override // com.amazon.clouddrive.model.serializer.JsonFieldSerializer
        public /* bridge */ /* synthetic */ void serializeFields(Object obj, JsonGenerator jsonGenerator) throws IOException {
            serializeFields((ImagePropertiesFieldSerializer) ((ImageProperties) obj), jsonGenerator);
        }

        public <U extends ImageProperties> void serializeFields(U u, JsonGenerator jsonGenerator) throws IOException {
            jsonGenerator.writeFieldName("yResolution");
            SimpleSerializers.serializeString(u.getYResolution(), jsonGenerator);
            jsonGenerator.writeFieldName("dateTimeDigitized");
            SimpleSerializers.serializeString(u.getDateTimeDigitized(), jsonGenerator);
            jsonGenerator.writeFieldName("whiteBalance");
            SimpleSerializers.serializeString(u.getWhiteBalance(), jsonGenerator);
            jsonGenerator.writeFieldName("width");
            SimpleSerializers.serializeInteger(u.getWidth(), jsonGenerator);
            jsonGenerator.writeFieldName("focalLength");
            SimpleSerializers.serializeString(u.getFocalLength(), jsonGenerator);
            jsonGenerator.writeFieldName("meteringMode");
            SimpleSerializers.serializeString(u.getMeteringMode(), jsonGenerator);
            jsonGenerator.writeFieldName("software");
            SimpleSerializers.serializeString(u.getSoftware(), jsonGenerator);
            jsonGenerator.writeFieldName("isoSpeedRatings");
            SimpleSerializers.serializeString(u.getIsoSpeedRatings(), jsonGenerator);
            jsonGenerator.writeFieldName("orientation");
            SimpleSerializers.serializeString(u.getOrientation(), jsonGenerator);
            jsonGenerator.writeFieldName("colorSpace");
            SimpleSerializers.serializeString(u.getColorSpace(), jsonGenerator);
            jsonGenerator.writeFieldName("dateTime");
            SimpleSerializers.serializeString(u.getDateTime(), jsonGenerator);
            jsonGenerator.writeFieldName("sharpness");
            SimpleSerializers.serializeString(u.getSharpness(), jsonGenerator);
            jsonGenerator.writeFieldName("flash");
            SimpleSerializers.serializeString(u.getFlash(), jsonGenerator);
            jsonGenerator.writeFieldName("location");
            SimpleSerializers.serializeString(u.getLocation(), jsonGenerator);
            jsonGenerator.writeFieldName("exposureMode");
            SimpleSerializers.serializeString(u.getExposureMode(), jsonGenerator);
            jsonGenerator.writeFieldName("dateTimeOriginal");
            SimpleSerializers.serializeString(u.getDateTimeOriginal(), jsonGenerator);
            jsonGenerator.writeFieldName("model");
            SimpleSerializers.serializeString(u.getModel(), jsonGenerator);
            jsonGenerator.writeFieldName("make");
            SimpleSerializers.serializeString(u.getMake(), jsonGenerator);
            jsonGenerator.writeFieldName("exposureProgram");
            SimpleSerializers.serializeString(u.getExposureProgram(), jsonGenerator);
            jsonGenerator.writeFieldName("sensingMethod");
            SimpleSerializers.serializeString(u.getSensingMethod(), jsonGenerator);
            jsonGenerator.writeFieldName("xResolution");
            SimpleSerializers.serializeString(u.getXResolution(), jsonGenerator);
            jsonGenerator.writeFieldName("gpsTimeStamp");
            SimpleSerializers.serializeString(u.getGpsTimeStamp(), jsonGenerator);
            jsonGenerator.writeFieldName("apertureValue");
            SimpleSerializers.serializeString(u.getApertureValue(), jsonGenerator);
            jsonGenerator.writeFieldName("exposureTime");
            SimpleSerializers.serializeString(u.getExposureTime(), jsonGenerator);
            jsonGenerator.writeFieldName("resolutionUnit");
            SimpleSerializers.serializeString(u.getResolutionUnit(), jsonGenerator);
            jsonGenerator.writeFieldName("height");
            SimpleSerializers.serializeInteger(u.getHeight(), jsonGenerator);
            jsonGenerator.writeFieldName("captureMode");
            SimpleSerializers.serializeString(u.getCaptureMode(), jsonGenerator);
        }
    }

    private ImagePropertiesSerializer() {
    }

    @Override // com.amazon.clouddrive.model.serializer.JsonSerializer
    public final void serialize(ImageProperties imageProperties, JsonGenerator jsonGenerator) throws IOException {
        if (imageProperties == null) {
            jsonGenerator.writeNull();
            return;
        }
        jsonGenerator.writeStartObject();
        this.mFieldSerializer.serializeFields((ImagePropertiesFieldSerializer) imageProperties, jsonGenerator);
        jsonGenerator.writeEndObject();
    }
}
