package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.UPCEReader;
/* loaded from: classes3.dex */
public final class ProductResultParser extends ResultParser {
    @Override // com.google.zxing.client.result.ResultParser
    /* renamed from: parse  reason: collision with other method in class */
    public ProductParsedResult mo10122parse(Result result) {
        BarcodeFormat barcodeFormat = result.getBarcodeFormat();
        if (barcodeFormat == BarcodeFormat.UPC_A || barcodeFormat == BarcodeFormat.UPC_E || barcodeFormat == BarcodeFormat.EAN_8 || barcodeFormat == BarcodeFormat.EAN_13) {
            String massagedText = ResultParser.getMassagedText(result);
            if (!ResultParser.isStringOfDigits(massagedText, massagedText.length())) {
                return null;
            }
            return new ProductParsedResult(massagedText, (barcodeFormat == BarcodeFormat.UPC_E && massagedText.length() == 8) ? UPCEReader.convertUPCEtoUPCA(massagedText) : massagedText);
        }
        return null;
    }
}
