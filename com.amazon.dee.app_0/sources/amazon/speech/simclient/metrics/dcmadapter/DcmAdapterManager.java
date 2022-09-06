package amazon.speech.simclient.metrics.dcmadapter;
/* loaded from: classes.dex */
public class DcmAdapterManager {
    private static DcmAdapter sDcmAdapter = new Dcm1pAdapter();

    public static DcmAdapter getDcmAdapter() {
        return sDcmAdapter;
    }

    public static void setAndUseDcmAdapter(DcmAdapter dcmAdapter) {
        sDcmAdapter = dcmAdapter;
    }
}
