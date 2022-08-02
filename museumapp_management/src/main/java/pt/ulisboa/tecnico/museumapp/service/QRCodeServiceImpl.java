package pt.ulisboa.tecnico.museumapp.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;
import pt.ulisboa.tecnico.museumapp.models.QRCodeParser;
import pt.ulisboa.tecnico.museumapp.models.QRCodeResult;
import pt.ulisboa.tecnico.museumapp.repositories.QRCodeRepository;
import pt.ulisboa.tecnico.museumapp.repositories.VisitRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class QRCodeServiceImpl implements QRCodeService{
    @Autowired
    QRCodeRepository qrCodeRepository;
    @Autowired
    VisitRepository visitRepository;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/qrcodes/QRCode";

    @Override
    public Iterable<QRCodeEntity> getAllQRCodes() {
        return qrCodeRepository.findAll();
    }

    @Override
    public QRCodeEntity getQRCodeByVisit(Integer visitId) {
        for(QRCodeEntity q: qrCodeRepository.findAll()){
            if(q.getVisitId().equals(visitId)){
                return q;
            }
        }
        return null;
    }

    @Override
    public QRCodeEntity createQRCode(QRCodeEntity qrcode) {
        return qrCodeRepository.save(qrcode);
    }

    @Override
    public void deleteQRCode(Integer id) {
        qrCodeRepository.deleteById(id);
    }

    @Override
    public String decodeQrCodeFile(File qrCodeFile) throws Exception {
        log.info("start decoding file {}", qrCodeFile.getName());
        BufferedImage bufferedImage = ImageIO.read(qrCodeFile);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
        Map<DecodeHintType, Object> hintMap = new EnumMap<>(DecodeHintType.class);
        hintMap.put(DecodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(DecodeHintType.PURE_BARCODE, Void.class);
        Result result = new QRCodeReader().decode(binaryBitmap, hintMap);
        log.info("file {} successfully decoded", qrCodeFile.getName());
        return result.getText();
    }

    @Override
    public QRCodeResult generateQrCodeUrl(QRCodeEntity QRCodeEntity) {
        String extracted = new QRCodeParser(QRCodeEntity).parse();
        return this.generateImageAsBase64(extracted);
    }

    @Override
    public QRCodeResult generateImageAsBase64(String textToBeEncoded) {
        QRCodeResult result = new QRCodeResult();
        result.setEncodedText(textToBeEncoded);
        String imageText = "";
        int size = 250;
        String fileType = "png";
        try {
            Map<EncodeHintType, Object> hintMap = createHintMap();
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(textToBeEncoded, BarcodeFormat.QR_CODE, size, size, hintMap);
            int width = 200;
            int height = 200;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (bitMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            String fileName = UUID.randomUUID().toString();
            File myFile = File.createTempFile(fileName, "." + fileType);
            ImageIO.write(image, fileType, myFile);
            byte[] bytes = FileUtils.readFileToByteArray(myFile);
            imageText = "data:image/png;base64," +
                    Base64Utils.encodeToString(bytes);
            result.setImage(imageText);
        } catch (WriterException | IOException e) {
            String msg = "Processing QR code failed.";
            log.error(msg, e);
            result.setErrorMessage(msg);
        }
        log.info("QR Code for text {} was successfully created.", textToBeEncoded);
        result.setSuccessMessage("QR Code was successfully created.");
        return result;
    }

    @Override
    public boolean downloadQRCode(QRCodeEntity qrCode) {
        String textToBeEncoded = qrCode.getText();
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(textToBeEncoded, BarcodeFormat.QR_CODE, 200, 200);
            String filePath = QR_CODE_IMAGE_PATH + qrCode.getVisitId() + ".png";
            Path path = Paths.get(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WriterException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void deleteQRCodeFile (Integer visit_id){
        File myObj = new File(QR_CODE_IMAGE_PATH + visit_id + ".png");
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }
    @NotNull
    private Map<EncodeHintType, Object> createHintMap() {
        Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        hintMap.put(EncodeHintType.MARGIN, 1);
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        return hintMap;
    }
}
