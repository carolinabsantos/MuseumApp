package pt.ulisboa.tecnico.museumapp;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
public class GenerateQRCode {
    public static void generateQRCodeImage(Integer visit_id)
            throws WriterException, IOException {
        String mainPath = "./src/main/resources/static/qrcodes/";
        String fileName = "QRCode" + visit_id + ".png";
        String filePath = mainPath + fileName;
        // generates the QRCode and saves it in the defined path
        //data that we want to store in the QR code
        String data= String.format("localhost:8181/visit/%d", visit_id);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }


    public static byte[] getQRCodeImage(Integer visit_id) throws WriterException, IOException {

        String data= String.format("localhost:8181/visit/%d", visit_id);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(data, BarcodeFormat.QR_CODE, 200, 200);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageConfig con = new MatrixToImageConfig( 0xFF000002 , 0xFFFFC041 ) ;

        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream,con);
        byte[] pngData = pngOutputStream.toByteArray();
        return pngData;
    }


}