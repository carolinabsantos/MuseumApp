package pt.ulisboa.tecnico.museumapp.service;

import com.google.zxing.WriterException;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;
import pt.ulisboa.tecnico.museumapp.models.QRCodeResult;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface QRCodeService {

    Iterable <QRCodeEntity> getAllQRCodes();

    QRCodeEntity getQRCodeByVisit(Integer visitId);

    QRCodeEntity createQRCode(QRCodeEntity qrcode);

    void deleteQRCode(Integer id);

    String decodeQR(byte[] qrCodeBytes);

    QRCodeResult generateQrCodeUrl(QRCodeEntity QRCodeEntity);

    QRCodeResult generateImageAsBase64(String textToBeEncoded);

    boolean downloadQRCode(QRCodeEntity qrCode);

    void deleteQRCodeFile(Integer visit_id);

    Integer getVisitId(String qrCodeContent);
}
