package pt.ulisboa.tecnico.museumapp.models;

import org.apache.commons.lang3.StringUtils;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;

public class QRCodeParser extends AbstractQrCodeParser{
    private final QRCodeEntity qrCodeEntity;

    public QRCodeParser(QRCodeEntity qrCodeEntity) {
        this.qrCodeEntity = qrCodeEntity;
    }

    @Override
    public String parse() {
        return StringUtils.replace(this.qrCodeEntity.getText(), " ", "%20");
    }
}
