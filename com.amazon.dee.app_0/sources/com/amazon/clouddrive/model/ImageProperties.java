package com.amazon.clouddrive.model;
/* loaded from: classes11.dex */
public class ImageProperties implements Comparable<ImageProperties> {
    private String apertureValue;
    private String captureMode;
    private String colorSpace;
    private String dateTime;
    private String dateTimeDigitized;
    private String dateTimeOriginal;
    private String exposureMode;
    private String exposureProgram;
    private String exposureTime;
    private String flash;
    private String focalLength;
    private String gpsTimeStamp;
    private Integer height;
    private String isoSpeedRatings;
    private String location;
    private String make;
    private String meteringMode;
    private String model;
    private String orientation;
    private String resolutionUnit;
    private String sensingMethod;
    private String sharpness;
    private String software;
    private String whiteBalance;
    private Integer width;
    private String xResolution;
    private String yResolution;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof ImageProperties) && compareTo((ImageProperties) obj) == 0;
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

    public String getModel() {
        return this.model;
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
        int i = 0;
        int hashCode = (getYResolution() == null ? 0 : getYResolution().hashCode()) + 1 + (getDateTimeDigitized() == null ? 0 : getDateTimeDigitized().hashCode()) + (getWhiteBalance() == null ? 0 : getWhiteBalance().hashCode()) + (getWidth() == null ? 0 : getWidth().hashCode()) + (getFocalLength() == null ? 0 : getFocalLength().hashCode()) + (getMeteringMode() == null ? 0 : getMeteringMode().hashCode()) + (getSoftware() == null ? 0 : getSoftware().hashCode()) + (getIsoSpeedRatings() == null ? 0 : getIsoSpeedRatings().hashCode()) + (getOrientation() == null ? 0 : getOrientation().hashCode()) + (getColorSpace() == null ? 0 : getColorSpace().hashCode()) + (getDateTime() == null ? 0 : getDateTime().hashCode()) + (getSharpness() == null ? 0 : getSharpness().hashCode()) + (getFlash() == null ? 0 : getFlash().hashCode()) + (getLocation() == null ? 0 : getLocation().hashCode()) + (getExposureMode() == null ? 0 : getExposureMode().hashCode()) + (getDateTimeOriginal() == null ? 0 : getDateTimeOriginal().hashCode()) + (getModel() == null ? 0 : getModel().hashCode()) + (getMake() == null ? 0 : getMake().hashCode()) + (getExposureProgram() == null ? 0 : getExposureProgram().hashCode()) + (getSensingMethod() == null ? 0 : getSensingMethod().hashCode()) + (getXResolution() == null ? 0 : getXResolution().hashCode()) + (getGpsTimeStamp() == null ? 0 : getGpsTimeStamp().hashCode()) + (getApertureValue() == null ? 0 : getApertureValue().hashCode()) + (getExposureTime() == null ? 0 : getExposureTime().hashCode()) + (getResolutionUnit() == null ? 0 : getResolutionUnit().hashCode()) + (getHeight() == null ? 0 : getHeight().hashCode());
        if (getCaptureMode() != null) {
            i = getCaptureMode().hashCode();
        }
        return hashCode + i;
    }

    public void setApertureValue(String str) {
        this.apertureValue = str;
    }

    public void setCaptureMode(String str) {
        this.captureMode = str;
    }

    public void setColorSpace(String str) {
        this.colorSpace = str;
    }

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public void setDateTimeDigitized(String str) {
        this.dateTimeDigitized = str;
    }

    public void setDateTimeOriginal(String str) {
        this.dateTimeOriginal = str;
    }

    public void setExposureMode(String str) {
        this.exposureMode = str;
    }

    public void setExposureProgram(String str) {
        this.exposureProgram = str;
    }

    public void setExposureTime(String str) {
        this.exposureTime = str;
    }

    public void setFlash(String str) {
        this.flash = str;
    }

    public void setFocalLength(String str) {
        this.focalLength = str;
    }

    public void setGpsTimeStamp(String str) {
        this.gpsTimeStamp = str;
    }

    public void setHeight(Integer num) {
        this.height = num;
    }

    public void setIsoSpeedRatings(String str) {
        this.isoSpeedRatings = str;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public void setMake(String str) {
        this.make = str;
    }

    public void setMeteringMode(String str) {
        this.meteringMode = str;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public void setOrientation(String str) {
        this.orientation = str;
    }

    public void setResolutionUnit(String str) {
        this.resolutionUnit = str;
    }

    public void setSensingMethod(String str) {
        this.sensingMethod = str;
    }

    public void setSharpness(String str) {
        this.sharpness = str;
    }

    public void setSoftware(String str) {
        this.software = str;
    }

    public void setWhiteBalance(String str) {
        this.whiteBalance = str;
    }

    public void setWidth(Integer num) {
        this.width = num;
    }

    public void setXResolution(String str) {
        this.xResolution = str;
    }

    public void setYResolution(String str) {
        this.yResolution = str;
    }

    @Override // java.lang.Comparable
    public int compareTo(ImageProperties imageProperties) {
        if (imageProperties == null) {
            return -1;
        }
        if (imageProperties == this) {
            return 0;
        }
        String yResolution = getYResolution();
        String yResolution2 = imageProperties.getYResolution();
        if (yResolution != yResolution2) {
            if (yResolution == null) {
                return -1;
            }
            if (yResolution2 == null) {
                return 1;
            }
            int compareTo = yResolution.compareTo(yResolution2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        String dateTimeDigitized = getDateTimeDigitized();
        String dateTimeDigitized2 = imageProperties.getDateTimeDigitized();
        if (dateTimeDigitized != dateTimeDigitized2) {
            if (dateTimeDigitized == null) {
                return -1;
            }
            if (dateTimeDigitized2 == null) {
                return 1;
            }
            int compareTo2 = dateTimeDigitized.compareTo(dateTimeDigitized2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        String whiteBalance = getWhiteBalance();
        String whiteBalance2 = imageProperties.getWhiteBalance();
        if (whiteBalance != whiteBalance2) {
            if (whiteBalance == null) {
                return -1;
            }
            if (whiteBalance2 == null) {
                return 1;
            }
            int compareTo3 = whiteBalance.compareTo(whiteBalance2);
            if (compareTo3 != 0) {
                return compareTo3;
            }
        }
        Integer width = getWidth();
        Integer width2 = imageProperties.getWidth();
        if (width != width2) {
            if (width == null) {
                return -1;
            }
            if (width2 == null) {
                return 1;
            }
            int compareTo4 = width.compareTo(width2);
            if (compareTo4 != 0) {
                return compareTo4;
            }
        }
        String focalLength = getFocalLength();
        String focalLength2 = imageProperties.getFocalLength();
        if (focalLength != focalLength2) {
            if (focalLength == null) {
                return -1;
            }
            if (focalLength2 == null) {
                return 1;
            }
            int compareTo5 = focalLength.compareTo(focalLength2);
            if (compareTo5 != 0) {
                return compareTo5;
            }
        }
        String meteringMode = getMeteringMode();
        String meteringMode2 = imageProperties.getMeteringMode();
        if (meteringMode != meteringMode2) {
            if (meteringMode == null) {
                return -1;
            }
            if (meteringMode2 == null) {
                return 1;
            }
            int compareTo6 = meteringMode.compareTo(meteringMode2);
            if (compareTo6 != 0) {
                return compareTo6;
            }
        }
        String software = getSoftware();
        String software2 = imageProperties.getSoftware();
        if (software != software2) {
            if (software == null) {
                return -1;
            }
            if (software2 == null) {
                return 1;
            }
            int compareTo7 = software.compareTo(software2);
            if (compareTo7 != 0) {
                return compareTo7;
            }
        }
        String isoSpeedRatings = getIsoSpeedRatings();
        String isoSpeedRatings2 = imageProperties.getIsoSpeedRatings();
        if (isoSpeedRatings != isoSpeedRatings2) {
            if (isoSpeedRatings == null) {
                return -1;
            }
            if (isoSpeedRatings2 == null) {
                return 1;
            }
            int compareTo8 = isoSpeedRatings.compareTo(isoSpeedRatings2);
            if (compareTo8 != 0) {
                return compareTo8;
            }
        }
        String orientation = getOrientation();
        String orientation2 = imageProperties.getOrientation();
        if (orientation != orientation2) {
            if (orientation == null) {
                return -1;
            }
            if (orientation2 == null) {
                return 1;
            }
            int compareTo9 = orientation.compareTo(orientation2);
            if (compareTo9 != 0) {
                return compareTo9;
            }
        }
        String colorSpace = getColorSpace();
        String colorSpace2 = imageProperties.getColorSpace();
        if (colorSpace != colorSpace2) {
            if (colorSpace == null) {
                return -1;
            }
            if (colorSpace2 == null) {
                return 1;
            }
            int compareTo10 = colorSpace.compareTo(colorSpace2);
            if (compareTo10 != 0) {
                return compareTo10;
            }
        }
        String dateTime = getDateTime();
        String dateTime2 = imageProperties.getDateTime();
        if (dateTime != dateTime2) {
            if (dateTime == null) {
                return -1;
            }
            if (dateTime2 == null) {
                return 1;
            }
            int compareTo11 = dateTime.compareTo(dateTime2);
            if (compareTo11 != 0) {
                return compareTo11;
            }
        }
        String sharpness = getSharpness();
        String sharpness2 = imageProperties.getSharpness();
        if (sharpness != sharpness2) {
            if (sharpness == null) {
                return -1;
            }
            if (sharpness2 == null) {
                return 1;
            }
            int compareTo12 = sharpness.compareTo(sharpness2);
            if (compareTo12 != 0) {
                return compareTo12;
            }
        }
        String flash = getFlash();
        String flash2 = imageProperties.getFlash();
        if (flash != flash2) {
            if (flash == null) {
                return -1;
            }
            if (flash2 == null) {
                return 1;
            }
            int compareTo13 = flash.compareTo(flash2);
            if (compareTo13 != 0) {
                return compareTo13;
            }
        }
        String location = getLocation();
        String location2 = imageProperties.getLocation();
        if (location != location2) {
            if (location == null) {
                return -1;
            }
            if (location2 == null) {
                return 1;
            }
            int compareTo14 = location.compareTo(location2);
            if (compareTo14 != 0) {
                return compareTo14;
            }
        }
        String exposureMode = getExposureMode();
        String exposureMode2 = imageProperties.getExposureMode();
        if (exposureMode != exposureMode2) {
            if (exposureMode == null) {
                return -1;
            }
            if (exposureMode2 == null) {
                return 1;
            }
            int compareTo15 = exposureMode.compareTo(exposureMode2);
            if (compareTo15 != 0) {
                return compareTo15;
            }
        }
        String dateTimeOriginal = getDateTimeOriginal();
        String dateTimeOriginal2 = imageProperties.getDateTimeOriginal();
        if (dateTimeOriginal != dateTimeOriginal2) {
            if (dateTimeOriginal == null) {
                return -1;
            }
            if (dateTimeOriginal2 == null) {
                return 1;
            }
            int compareTo16 = dateTimeOriginal.compareTo(dateTimeOriginal2);
            if (compareTo16 != 0) {
                return compareTo16;
            }
        }
        String model = getModel();
        String model2 = imageProperties.getModel();
        if (model != model2) {
            if (model == null) {
                return -1;
            }
            if (model2 == null) {
                return 1;
            }
            int compareTo17 = model.compareTo(model2);
            if (compareTo17 != 0) {
                return compareTo17;
            }
        }
        String make = getMake();
        String make2 = imageProperties.getMake();
        if (make != make2) {
            if (make == null) {
                return -1;
            }
            if (make2 == null) {
                return 1;
            }
            int compareTo18 = make.compareTo(make2);
            if (compareTo18 != 0) {
                return compareTo18;
            }
        }
        String exposureProgram = getExposureProgram();
        String exposureProgram2 = imageProperties.getExposureProgram();
        if (exposureProgram != exposureProgram2) {
            if (exposureProgram == null) {
                return -1;
            }
            if (exposureProgram2 == null) {
                return 1;
            }
            int compareTo19 = exposureProgram.compareTo(exposureProgram2);
            if (compareTo19 != 0) {
                return compareTo19;
            }
        }
        String sensingMethod = getSensingMethod();
        String sensingMethod2 = imageProperties.getSensingMethod();
        if (sensingMethod != sensingMethod2) {
            if (sensingMethod == null) {
                return -1;
            }
            if (sensingMethod2 == null) {
                return 1;
            }
            int compareTo20 = sensingMethod.compareTo(sensingMethod2);
            if (compareTo20 != 0) {
                return compareTo20;
            }
        }
        String xResolution = getXResolution();
        String xResolution2 = imageProperties.getXResolution();
        if (xResolution != xResolution2) {
            if (xResolution == null) {
                return -1;
            }
            if (xResolution2 == null) {
                return 1;
            }
            int compareTo21 = xResolution.compareTo(xResolution2);
            if (compareTo21 != 0) {
                return compareTo21;
            }
        }
        String gpsTimeStamp = getGpsTimeStamp();
        String gpsTimeStamp2 = imageProperties.getGpsTimeStamp();
        if (gpsTimeStamp != gpsTimeStamp2) {
            if (gpsTimeStamp == null) {
                return -1;
            }
            if (gpsTimeStamp2 == null) {
                return 1;
            }
            int compareTo22 = gpsTimeStamp.compareTo(gpsTimeStamp2);
            if (compareTo22 != 0) {
                return compareTo22;
            }
        }
        String apertureValue = getApertureValue();
        String apertureValue2 = imageProperties.getApertureValue();
        if (apertureValue != apertureValue2) {
            if (apertureValue == null) {
                return -1;
            }
            if (apertureValue2 == null) {
                return 1;
            }
            int compareTo23 = apertureValue.compareTo(apertureValue2);
            if (compareTo23 != 0) {
                return compareTo23;
            }
        }
        String exposureTime = getExposureTime();
        String exposureTime2 = imageProperties.getExposureTime();
        if (exposureTime != exposureTime2) {
            if (exposureTime == null) {
                return -1;
            }
            if (exposureTime2 == null) {
                return 1;
            }
            int compareTo24 = exposureTime.compareTo(exposureTime2);
            if (compareTo24 != 0) {
                return compareTo24;
            }
        }
        String resolutionUnit = getResolutionUnit();
        String resolutionUnit2 = imageProperties.getResolutionUnit();
        if (resolutionUnit != resolutionUnit2) {
            if (resolutionUnit == null) {
                return -1;
            }
            if (resolutionUnit2 == null) {
                return 1;
            }
            int compareTo25 = resolutionUnit.compareTo(resolutionUnit2);
            if (compareTo25 != 0) {
                return compareTo25;
            }
        }
        Integer height = getHeight();
        Integer height2 = imageProperties.getHeight();
        if (height != height2) {
            if (height == null) {
                return -1;
            }
            if (height2 == null) {
                return 1;
            }
            int compareTo26 = height.compareTo(height2);
            if (compareTo26 != 0) {
                return compareTo26;
            }
        }
        String captureMode = getCaptureMode();
        String captureMode2 = imageProperties.getCaptureMode();
        if (captureMode != captureMode2) {
            if (captureMode == null) {
                return -1;
            }
            if (captureMode2 == null) {
                return 1;
            }
            int compareTo27 = captureMode.compareTo(captureMode2);
            if (compareTo27 != 0) {
                return compareTo27;
            }
        }
        return 0;
    }
}
