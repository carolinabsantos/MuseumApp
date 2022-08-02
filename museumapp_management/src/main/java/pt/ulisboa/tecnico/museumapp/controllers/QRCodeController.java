package pt.ulisboa.tecnico.museumapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;
import pt.ulisboa.tecnico.museumapp.models.QRCodeResult;
import pt.ulisboa.tecnico.museumapp.service.QRCodeService;

@Controller
public class QRCodeController {
    @Autowired
    QRCodeService qrCodeService;

    @GetMapping("/show-qrcode")
    public String processUrl(Model model, @RequestParam Integer visit_id) {
        QRCodeEntity qrCodeEntity = qrCodeService.getQRCodeByVisit(visit_id);
        QRCodeResult result = qrCodeService.generateQrCodeUrl(qrCodeEntity);
        model.addAttribute("qrCode", result.getImage());
        return "show-qrcode";
    }

    @GetMapping("/download-qrcode")
    public String sendQRCode(@RequestParam Integer visit_id) {
        QRCodeEntity qrCodeEntity = qrCodeService.getQRCodeByVisit(visit_id);
        qrCodeService.downloadQRCode(qrCodeEntity);
        return "redirect:/list-visits";
    }


}
