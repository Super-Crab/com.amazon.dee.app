package com.amazon.bluefront.api.v2;
/* loaded from: classes11.dex */
public class SpeechToTextInputParameters implements Comparable<SpeechToTextInputParameters> {
    private RecognitionParameters mRecognitionParams;
    private ServiceParameters mServiceParams;

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof SpeechToTextInputParameters) && compareTo((SpeechToTextInputParameters) obj) == 0;
    }

    public RecognitionParameters getRecognitionParams() {
        return this.mRecognitionParams;
    }

    public ServiceParameters getServiceParams() {
        return this.mServiceParams;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (getRecognitionParams() == null ? 0 : getRecognitionParams().hashCode()) + 1;
        if (getServiceParams() != null) {
            i = getServiceParams().hashCode();
        }
        return hashCode + i;
    }

    public void setRecognitionParams(RecognitionParameters recognitionParameters) {
        this.mRecognitionParams = recognitionParameters;
    }

    public void setServiceParams(ServiceParameters serviceParameters) {
        this.mServiceParams = serviceParameters;
    }

    @Override // java.lang.Comparable
    public int compareTo(SpeechToTextInputParameters speechToTextInputParameters) {
        if (speechToTextInputParameters == null) {
            return -1;
        }
        if (speechToTextInputParameters == this) {
            return 0;
        }
        RecognitionParameters recognitionParams = getRecognitionParams();
        RecognitionParameters recognitionParams2 = speechToTextInputParameters.getRecognitionParams();
        if (recognitionParams != recognitionParams2) {
            if (recognitionParams == null) {
                return -1;
            }
            if (recognitionParams2 == null) {
                return 1;
            }
            int compareTo = recognitionParams.compareTo(recognitionParams2);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        ServiceParameters serviceParams = getServiceParams();
        ServiceParameters serviceParams2 = speechToTextInputParameters.getServiceParams();
        if (serviceParams != serviceParams2) {
            if (serviceParams == null) {
                return -1;
            }
            if (serviceParams2 == null) {
                return 1;
            }
            int compareTo2 = serviceParams.compareTo(serviceParams2);
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        return 0;
    }
}
