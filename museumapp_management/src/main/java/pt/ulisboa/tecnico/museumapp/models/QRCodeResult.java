package pt.ulisboa.tecnico.museumapp.models;

public class QRCodeResult {
    private String image;
    private String encodedText;
    private String successMessage;
    private String errorMessage;

    public boolean isSuccessfull() {
        return successMessage != null;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getImage() {
        return image;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    @Override
    public String toString() {
        return "QRCodeResult{" +
                "image='" + image + '\'' +
                ", encodedText='" + encodedText + '\'' +
                ", successMessage='" + successMessage + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
