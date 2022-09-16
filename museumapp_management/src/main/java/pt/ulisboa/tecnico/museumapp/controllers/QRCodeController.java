package pt.ulisboa.tecnico.museumapp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pt.ulisboa.tecnico.museumapp.entities.QRCodeEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeSlotEntity;
import pt.ulisboa.tecnico.museumapp.entities.VisitEntity;
import pt.ulisboa.tecnico.museumapp.models.QRCodeResult;
import pt.ulisboa.tecnico.museumapp.service.QRCodeService;

import java.io.File;
import java.io.IOException;

@Controller
public class QRCodeController {
    @Autowired
    QRCodeService qrCodeService;

    private Logger logger = LoggerFactory.getLogger(QRCodeController.class);

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

    @PostMapping("/uploadQrCode")
    public String uploadQrCode(@RequestParam("qrCodeFile") MultipartFile qrCodeFile, RedirectAttributes redirectAttributes) {
        if(qrCodeFile.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Please choose file to upload.");
            return "redirect:/museum";
        }

        try {
            String qrContent = qrCodeService.decodeQR(qrCodeFile.getBytes());
            System.out.println("QRCode Content: "+ qrContent);
            Integer visit_id = qrCodeService.getVisitId(qrContent);
            System.out.println("Visit id: "+ visit_id);
            redirectAttributes.addFlashAttribute("successMessage", "File upload successfully.");
            redirectAttributes.addFlashAttribute("qrContent", "Your QR Code Content:" + qrContent);
            return "redirect:/visit-info/"+visit_id;
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/museum";
    }

}
