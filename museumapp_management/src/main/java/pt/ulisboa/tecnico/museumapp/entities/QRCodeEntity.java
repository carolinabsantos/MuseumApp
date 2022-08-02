package pt.ulisboa.tecnico.museumapp.entities;

import com.google.zxing.common.BitMatrix;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

@Entity
public class QRCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="qrcode_id")
    private Integer id;
    @Column(name="visit_id")
    private Integer visitId;

    @Column(name="qrcode_text")
    private String text;

    @Column(name="qrcode")
    private String qrCode;

    public QRCodeEntity(Integer visitId) {
        this.visitId = visitId;
        this.text = "localhost:8081/" + visitId;
    }

    public QRCodeEntity() {

    }

    public Integer getId() {
        return id;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = "localhost:8081/" + visitId;
    }

    @Override
    public String toString() {
        return "QRCodeEntity{" +
                "id=" + id +
                ", visitId=" + visitId +
                ", text=" + text +
                ", qrcode=" + qrCode +
                '}';
    }
}
