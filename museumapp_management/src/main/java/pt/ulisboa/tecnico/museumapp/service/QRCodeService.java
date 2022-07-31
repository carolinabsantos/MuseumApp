package pt.ulisboa.tecnico.museumapp.service;

import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;

import javax.persistence.PreRemove;
import java.util.List;
import java.util.Optional;

public interface QRCodeService {
    byte[] generateQRCode(String qrContent, int width, int height);
}
