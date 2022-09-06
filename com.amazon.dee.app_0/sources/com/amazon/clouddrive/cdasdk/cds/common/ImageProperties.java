package com.amazon.clouddrive.cdasdk.cds.common;

import com.android.tools.r8.GeneratedOutlineSupport1;
import com.fasterxml.jackson.annotation.JsonProperty;
/* loaded from: classes11.dex */
public class ImageProperties {
    @JsonProperty("apertureValue")
    private String apertureValue;
    @JsonProperty("captureMode")
    private String captureMode;
    @JsonProperty("colorSpace")
    private String colorSpace;
    @JsonProperty("dateTime")
    private String dateTime;
    @JsonProperty("dateTimeDigitized")
    private String dateTimeDigitized;
    @JsonProperty("dateTimeOriginal")
    private String dateTimeOriginal;
    @JsonProperty("exposureMode")
    private String exposureMode;
    @JsonProperty("exposureProgram")
    private String exposureProgram;
    @JsonProperty("exposureTime")
    private String exposureTime;
    @JsonProperty("flash")
    private String flash;
    @JsonProperty("focalLength")
    private String focalLength;
    @JsonProperty("gpsDateStamp")
    private String gpsDateStamp;
    @JsonProperty("gpsTimeStamp")
    private String gpsTimeStamp;
    @JsonProperty("height")
    private Integer height;
    @JsonProperty("isoSpeedRatings")
    private String isoSpeedRatings;
    @JsonProperty("location")
    private String location;
    @JsonProperty("make")
    private String make;
    @JsonProperty("meteringMode")
    private String meteringMode;
    @JsonProperty("orientation")
    private String orientation;
    @JsonProperty("resolutionUnit")
    private String resolutionUnit;
    @JsonProperty("sensingMethod")
    private String sensingMethod;
    @JsonProperty("sharpness")
    private String sharpness;
    @JsonProperty("software")
    private String software;
    @JsonProperty("subSecTime")
    private String subSecTime;
    @JsonProperty("subSecTimeDigitized")
    private String subSecTimeDigitized;
    @JsonProperty("subSecTimeOriginal")
    private String subSecTimeOriginal;
    @JsonProperty("whiteBalance")
    private String whiteBalance;
    @JsonProperty("width")
    private Integer width;
    @JsonProperty("xResolution")
    private String xResolution;
    @JsonProperty("yResolution")
    private String yResolution;

    protected boolean canEqual(Object obj) {
        return obj instanceof ImageProperties;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ImageProperties)) {
            return false;
        }
        ImageProperties imageProperties = (ImageProperties) obj;
        if (!imageProperties.canEqual(this)) {
            return false;
        }
        String make = getMake();
        String make2 = imageProperties.getMake();
        if (make != null ? !make.equals(make2) : make2 != null) {
            return false;
        }
        String isoSpeedRatings = getIsoSpeedRatings();
        String isoSpeedRatings2 = imageProperties.getIsoSpeedRatings();
        if (isoSpeedRatings != null ? !isoSpeedRatings.equals(isoSpeedRatings2) : isoSpeedRatings2 != null) {
            return false;
        }
        String exposureTime = getExposureTime();
        String exposureTime2 = imageProperties.getExposureTime();
        if (exposureTime != null ? !exposureTime.equals(exposureTime2) : exposureTime2 != null) {
            return false;
        }
        String apertureValue = getApertureValue();
        String apertureValue2 = imageProperties.getApertureValue();
        if (apertureValue != null ? !apertureValue.equals(apertureValue2) : apertureValue2 != null) {
            return false;
        }
        String dateTimeOriginal = getDateTimeOriginal();
        String dateTimeOriginal2 = imageProperties.getDateTimeOriginal();
        if (dateTimeOriginal != null ? !dateTimeOriginal.equals(dateTimeOriginal2) : dateTimeOriginal2 != null) {
            return false;
        }
        String flash = getFlash();
        String flash2 = imageProperties.getFlash();
        if (flash != null ? !flash.equals(flash2) : flash2 != null) {
            return false;
        }
        String focalLength = getFocalLength();
        String focalLength2 = imageProperties.getFocalLength();
        if (focalLength != null ? !focalLength.equals(focalLength2) : focalLength2 != null) {
            return false;
        }
        String dateTime = getDateTime();
        String dateTime2 = imageProperties.getDateTime();
        if (dateTime != null ? !dateTime.equals(dateTime2) : dateTime2 != null) {
            return false;
        }
        String dateTimeDigitized = getDateTimeDigitized();
        String dateTimeDigitized2 = imageProperties.getDateTimeDigitized();
        if (dateTimeDigitized != null ? !dateTimeDigitized.equals(dateTimeDigitized2) : dateTimeDigitized2 != null) {
            return false;
        }
        String software = getSoftware();
        String software2 = imageProperties.getSoftware();
        if (software != null ? !software.equals(software2) : software2 != null) {
            return false;
        }
        String orientation = getOrientation();
        String orientation2 = imageProperties.getOrientation();
        if (orientation != null ? !orientation.equals(orientation2) : orientation2 != null) {
            return false;
        }
        String colorSpace = getColorSpace();
        String colorSpace2 = imageProperties.getColorSpace();
        if (colorSpace != null ? !colorSpace.equals(colorSpace2) : colorSpace2 != null) {
            return false;
        }
        String meteringMode = getMeteringMode();
        String meteringMode2 = imageProperties.getMeteringMode();
        if (meteringMode != null ? !meteringMode.equals(meteringMode2) : meteringMode2 != null) {
            return false;
        }
        String exposureProgram = getExposureProgram();
        String exposureProgram2 = imageProperties.getExposureProgram();
        if (exposureProgram != null ? !exposureProgram.equals(exposureProgram2) : exposureProgram2 != null) {
            return false;
        }
        String exposureMode = getExposureMode();
        String exposureMode2 = imageProperties.getExposureMode();
        if (exposureMode != null ? !exposureMode.equals(exposureMode2) : exposureMode2 != null) {
            return false;
        }
        String sharpness = getSharpness();
        String sharpness2 = imageProperties.getSharpness();
        if (sharpness != null ? !sharpness.equals(sharpness2) : sharpness2 != null) {
            return false;
        }
        String whiteBalance = getWhiteBalance();
        String whiteBalance2 = imageProperties.getWhiteBalance();
        if (whiteBalance != null ? !whiteBalance.equals(whiteBalance2) : whiteBalance2 != null) {
            return false;
        }
        String sensingMethod = getSensingMethod();
        String sensingMethod2 = imageProperties.getSensingMethod();
        if (sensingMethod != null ? !sensingMethod.equals(sensingMethod2) : sensingMethod2 != null) {
            return false;
        }
        String xResolution = getXResolution();
        String xResolution2 = imageProperties.getXResolution();
        if (xResolution != null ? !xResolution.equals(xResolution2) : xResolution2 != null) {
            return false;
        }
        String yResolution = getYResolution();
        String yResolution2 = imageProperties.getYResolution();
        if (yResolution != null ? !yResolution.equals(yResolution2) : yResolution2 != null) {
            return false;
        }
        String resolutionUnit = getResolutionUnit();
        String resolutionUnit2 = imageProperties.getResolutionUnit();
        if (resolutionUnit != null ? !resolutionUnit.equals(resolutionUnit2) : resolutionUnit2 != null) {
            return false;
        }
        String gpsTimeStamp = getGpsTimeStamp();
        String gpsTimeStamp2 = imageProperties.getGpsTimeStamp();
        if (gpsTimeStamp != null ? !gpsTimeStamp.equals(gpsTimeStamp2) : gpsTimeStamp2 != null) {
            return false;
        }
        Integer height = getHeight();
        Integer height2 = imageProperties.getHeight();
        if (height != null ? !height.equals(height2) : height2 != null) {
            return false;
        }
        Integer width = getWidth();
        Integer width2 = imageProperties.getWidth();
        if (width != null ? !width.equals(width2) : width2 != null) {
            return false;
        }
        String location = getLocation();
        String location2 = imageProperties.getLocation();
        if (location != null ? !location.equals(location2) : location2 != null) {
            return false;
        }
        String captureMode = getCaptureMode();
        String captureMode2 = imageProperties.getCaptureMode();
        if (captureMode != null ? !captureMode.equals(captureMode2) : captureMode2 != null) {
            return false;
        }
        String subSecTimeOriginal = getSubSecTimeOriginal();
        String subSecTimeOriginal2 = imageProperties.getSubSecTimeOriginal();
        if (subSecTimeOriginal != null ? !subSecTimeOriginal.equals(subSecTimeOriginal2) : subSecTimeOriginal2 != null) {
            return false;
        }
        String subSecTimeDigitized = getSubSecTimeDigitized();
        String subSecTimeDigitized2 = imageProperties.getSubSecTimeDigitized();
        if (subSecTimeDigitized != null ? !subSecTimeDigitized.equals(subSecTimeDigitized2) : subSecTimeDigitized2 != null) {
            return false;
        }
        String subSecTime = getSubSecTime();
        String subSecTime2 = imageProperties.getSubSecTime();
        if (subSecTime != null ? !subSecTime.equals(subSecTime2) : subSecTime2 != null) {
            return false;
        }
        String gpsDateStamp = getGpsDateStamp();
        String gpsDateStamp2 = imageProperties.getGpsDateStamp();
        return gpsDateStamp != null ? gpsDateStamp.equals(gpsDateStamp2) : gpsDateStamp2 == null;
    }

    public String getApertureValue() {
        return this.apertureValue;
    }

    public String getCaptureMode() {
        return this.captureMode;
    }

    public String getColorSpace() {
        return this.colorSpace;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public String getDateTimeDigitized() {
        return this.dateTimeDigitized;
    }

    public String getDateTimeOriginal() {
        return this.dateTimeOriginal;
    }

    public String getExposureMode() {
        return this.exposureMode;
    }

    public String getExposureProgram() {
        return this.exposureProgram;
    }

    public String getExposureTime() {
        return this.exposureTime;
    }

    public String getFlash() {
        return this.flash;
    }

    public String getFocalLength() {
        return this.focalLength;
    }

    public String getGpsDateStamp() {
        return this.gpsDateStamp;
    }

    public String getGpsTimeStamp() {
        return this.gpsTimeStamp;
    }

    public Integer getHeight() {
        return this.height;
    }

    public String getIsoSpeedRatings() {
        return this.isoSpeedRatings;
    }

    public String getLocation() {
        return this.location;
    }

    public String getMake() {
        return this.make;
    }

    public String getMeteringMode() {
        return this.meteringMode;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public String getResolutionUnit() {
        return this.resolutionUnit;
    }

    public String getSensingMethod() {
        return this.sensingMethod;
    }

    public String getSharpness() {
        return this.sharpness;
    }

    public String getSoftware() {
        return this.software;
    }

    public String getSubSecTime() {
        return this.subSecTime;
    }

    public String getSubSecTimeDigitized() {
        return this.subSecTimeDigitized;
    }

    public String getSubSecTimeOriginal() {
        return this.subSecTimeOriginal;
    }

    public String getWhiteBalance() {
        return this.whiteBalance;
    }

    public Integer getWidth() {
        return this.width;
    }

    public String getXResolution() {
        return this.xResolution;
    }

    public String getYResolution() {
        return this.yResolution;
    }

    public int hashCode() {
        String make = getMake();
        int i = 43;
        int hashCode = make == null ? 43 : make.hashCode();
        String isoSpeedRatings = getIsoSpeedRatings();
        int hashCode2 = ((hashCode + 59) * 59) + (isoSpeedRatings == null ? 43 : isoSpeedRatings.hashCode());
        String exposureTime = getExposureTime();
        int hashCode3 = (hashCode2 * 59) + (exposureTime == null ? 43 : exposureTime.hashCode());
        String apertureValue = getApertureValue();
        int hashCode4 = (hashCode3 * 59) + (apertureValue == null ? 43 : apertureValue.hashCode());
        String dateTimeOriginal = getDateTimeOriginal();
        int hashCode5 = (hashCode4 * 59) + (dateTimeOriginal == null ? 43 : dateTimeOriginal.hashCode());
        String flash = getFlash();
        int hashCode6 = (hashCode5 * 59) + (flash == null ? 43 : flash.hashCode());
        String focalLength = getFocalLength();
        int hashCode7 = (hashCode6 * 59) + (focalLength == null ? 43 : focalLength.hashCode());
        String dateTime = getDateTime();
        int hashCode8 = (hashCode7 * 59) + (dateTime == null ? 43 : dateTime.hashCode());
        String dateTimeDigitized = getDateTimeDigitized();
        int hashCode9 = (hashCode8 * 59) + (dateTimeDigitized == null ? 43 : dateTimeDigitized.hashCode());
        String software = getSoftware();
        int hashCode10 = (hashCode9 * 59) + (software == null ? 43 : software.hashCode());
        String orientation = getOrientation();
        int hashCode11 = (hashCode10 * 59) + (orientation == null ? 43 : orientation.hashCode());
        String colorSpace = getColorSpace();
        int hashCode12 = (hashCode11 * 59) + (colorSpace == null ? 43 : colorSpace.hashCode());
        String meteringMode = getMeteringMode();
        int hashCode13 = (hashCode12 * 59) + (meteringMode == null ? 43 : meteringMode.hashCode());
        String exposureProgram = getExposureProgram();
        int hashCode14 = (hashCode13 * 59) + (exposureProgram == null ? 43 : exposureProgram.hashCode());
        String exposureMode = getExposureMode();
        int hashCode15 = (hashCode14 * 59) + (exposureMode == null ? 43 : exposureMode.hashCode());
        String sharpness = getSharpness();
        int hashCode16 = (hashCode15 * 59) + (sharpness == null ? 43 : sharpness.hashCode());
        String whiteBalance = getWhiteBalance();
        int hashCode17 = (hashCode16 * 59) + (whiteBalance == null ? 43 : whiteBalance.hashCode());
        String sensingMethod = getSensingMethod();
        int hashCode18 = (hashCode17 * 59) + (sensingMethod == null ? 43 : sensingMethod.hashCode());
        String xResolution = getXResolution();
        int hashCode19 = (hashCode18 * 59) + (xResolution == null ? 43 : xResolution.hashCode());
        String yResolution = getYResolution();
        int hashCode20 = (hashCode19 * 59) + (yResolution == null ? 43 : yResolution.hashCode());
        String resolutionUnit = getResolutionUnit();
        int hashCode21 = (hashCode20 * 59) + (resolutionUnit == null ? 43 : resolutionUnit.hashCode());
        String gpsTimeStamp = getGpsTimeStamp();
        int hashCode22 = (hashCode21 * 59) + (gpsTimeStamp == null ? 43 : gpsTimeStamp.hashCode());
        Integer height = getHeight();
        int hashCode23 = (hashCode22 * 59) + (height == null ? 43 : height.hashCode());
        Integer width = getWidth();
        int hashCode24 = (hashCode23 * 59) + (width == null ? 43 : width.hashCode());
        String location = getLocation();
        int hashCode25 = (hashCode24 * 59) + (location == null ? 43 : location.hashCode());
        String captureMode = getCaptureMode();
        int hashCode26 = (hashCode25 * 59) + (captureMode == null ? 43 : captureMode.hashCode());
        String subSecTimeOriginal = getSubSecTimeOriginal();
        int hashCode27 = (hashCode26 * 59) + (subSecTimeOriginal == null ? 43 : subSecTimeOriginal.hashCode());
        String subSecTimeDigitized = getSubSecTimeDigitized();
        int hashCode28 = (hashCode27 * 59) + (subSecTimeDigitized == null ? 43 : subSecTimeDigitized.hashCode());
        String subSecTime = getSubSecTime();
        int hashCode29 = (hashCode28 * 59) + (subSecTime == null ? 43 : subSecTime.hashCode());
        String gpsDateStamp = getGpsDateStamp();
        int i2 = hashCode29 * 59;
        if (gpsDateStamp != null) {
            i = gpsDateStamp.hashCode();
        }
        return i2 + i;
    }

    @JsonProperty("apertureValue")
    public void setApertureValue(String str) {
        this.apertureValue = str;
    }

    @JsonProperty("captureMode")
    public void setCaptureMode(String str) {
        this.captureMode = str;
    }

    @JsonProperty("colorSpace")
    public void setColorSpace(String str) {
        this.colorSpace = str;
    }

    @JsonProperty("dateTime")
    public void setDateTime(String str) {
        this.dateTime = str;
    }

    @JsonProperty("dateTimeDigitized")
    public void setDateTimeDigitized(String str) {
        this.dateTimeDigitized = str;
    }

    @JsonProperty("dateTimeOriginal")
    public void setDateTimeOriginal(String str) {
        this.dateTimeOriginal = str;
    }

    @JsonProperty("exposureMode")
    public void setExposureMode(String str) {
        this.exposureMode = str;
    }

    @JsonProperty("exposureProgram")
    public void setExposureProgram(String str) {
        this.exposureProgram = str;
    }

    @JsonProperty("exposureTime")
    public void setExposureTime(String str) {
        this.exposureTime = str;
    }

    @JsonProperty("flash")
    public void setFlash(String str) {
        this.flash = str;
    }

    @JsonProperty("focalLength")
    public void setFocalLength(String str) {
        this.focalLength = str;
    }

    @JsonProperty("gpsDateStamp")
    public void setGpsDateStamp(String str) {
        this.gpsDateStamp = str;
    }

    @JsonProperty("gpsTimeStamp")
    public void setGpsTimeStamp(String str) {
        this.gpsTimeStamp = str;
    }

    @JsonProperty("height")
    public void setHeight(Integer num) {
        this.height = num;
    }

    @JsonProperty("isoSpeedRatings")
    public void setIsoSpeedRatings(String str) {
        this.isoSpeedRatings = str;
    }

    @JsonProperty("location")
    public void setLocation(String str) {
        this.location = str;
    }

    @JsonProperty("make")
    public void setMake(String str) {
        this.make = str;
    }

    @JsonProperty("meteringMode")
    public void setMeteringMode(String str) {
        this.meteringMode = str;
    }

    @JsonProperty("orientation")
    public void setOrientation(String str) {
        this.orientation = str;
    }

    @JsonProperty("resolutionUnit")
    public void setResolutionUnit(String str) {
        this.resolutionUnit = str;
    }

    @JsonProperty("sensingMethod")
    public void setSensingMethod(String str) {
        this.sensingMethod = str;
    }

    @JsonProperty("sharpness")
    public void setSharpness(String str) {
        this.sharpness = str;
    }

    @JsonProperty("software")
    public void setSoftware(String str) {
        this.software = str;
    }

    @JsonProperty("subSecTime")
    public void setSubSecTime(String str) {
        this.subSecTime = str;
    }

    @JsonProperty("subSecTimeDigitized")
    public void setSubSecTimeDigitized(String str) {
        this.subSecTimeDigitized = str;
    }

    @JsonProperty("subSecTimeOriginal")
    public void setSubSecTimeOriginal(String str) {
        this.subSecTimeOriginal = str;
    }

    @JsonProperty("whiteBalance")
    public void setWhiteBalance(String str) {
        this.whiteBalance = str;
    }

    @JsonProperty("width")
    public void setWidth(Integer num) {
        this.width = num;
    }

    @JsonProperty("xResolution")
    public void setXResolution(String str) {
        this.xResolution = str;
    }

    @JsonProperty("yResolution")
    public void setYResolution(String str) {
        this.yResolution = str;
    }

    public String toString() {
        StringBuilder outline107 = GeneratedOutlineSupport1.outline107("ImageProperties(make=");
        outline107.append(getMake());
        outline107.append(", isoSpeedRatings=");
        outline107.append(getIsoSpeedRatings());
        outline107.append(", exposureTime=");
        outline107.append(getExposureTime());
        outline107.append(", apertureValue=");
        outline107.append(getApertureValue());
        outline107.append(", dateTimeOriginal=");
        outline107.append(getDateTimeOriginal());
        outline107.append(", flash=");
        outline107.append(getFlash());
        outline107.append(", focalLength=");
        outline107.append(getFocalLength());
        outline107.append(", dateTime=");
        outline107.append(getDateTime());
        outline107.append(", dateTimeDigitized=");
        outline107.append(getDateTimeDigitized());
        outline107.append(", software=");
        outline107.append(getSoftware());
        outline107.append(", orientation=");
        outline107.append(getOrientation());
        outline107.append(", colorSpace=");
        outline107.append(getColorSpace());
        outline107.append(", meteringMode=");
        outline107.append(getMeteringMode());
        outline107.append(", exposureProgram=");
        outline107.append(getExposureProgram());
        outline107.append(", exposureMode=");
        outline107.append(getExposureMode());
        outline107.append(", sharpness=");
        outline107.append(getSharpness());
        outline107.append(", whiteBalance=");
        outline107.append(getWhiteBalance());
        outline107.append(", sensingMethod=");
        outline107.append(getSensingMethod());
        outline107.append(", xResolution=");
        outline107.append(getXResolution());
        outline107.append(", yResolution=");
        outline107.append(getYResolution());
        outline107.append(", resolutionUnit=");
        outline107.append(getResolutionUnit());
        outline107.append(", gpsTimeStamp=");
        outline107.append(getGpsTimeStamp());
        outline107.append(", height=");
        outline107.append(getHeight());
        outline107.append(", width=");
        outline107.append(getWidth());
        outline107.append(", location=");
        outline107.append(getLocation());
        outline107.append(", captureMode=");
        outline107.append(getCaptureMode());
        outline107.append(", subSecTimeOriginal=");
        outline107.append(getSubSecTimeOriginal());
        outline107.append(", subSecTimeDigitized=");
        outline107.append(getSubSecTimeDigitized());
        outline107.append(", subSecTime=");
        outline107.append(getSubSecTime());
        outline107.append(", gpsDateStamp=");
        outline107.append(getGpsDateStamp());
        outline107.append(")");
        return outline107.toString();
    }
}
