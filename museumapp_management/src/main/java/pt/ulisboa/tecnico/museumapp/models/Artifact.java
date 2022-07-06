package pt.ulisboa.tecnico.museumapp.models;

import java.util.List;

public class Artifact {
    private String name;
    private String designation;
    private String description;
    private String techCharacteristics;
    protected String moreInfo;
    protected String model;
    protected String country;
    protected String brand;
    protected String madeIn;
    protected Integer creationYear;
    protected String serialNumber;
    protected String donatedBy;
    protected String exhibitor;
    protected Integer itemNo;
    protected String typeCat;
    protected String category;
    protected String category2;
    protected String category3;
    protected String category4;
    protected List<TimeMachine> timeMachines;

    public Artifact(String name, String designation, String description, String techCharacteristics, String moreInfo, String model, String country, String brand, String madeIn, int creationYear, String serialNumber, String donatedBy,String exhibitor, int itemNo, String typeCat, String category, String category2, String category3, String category4){
        this.name = name;
        this.designation=designation;
        this.description=description;
        this.techCharacteristics=techCharacteristics;
        this.moreInfo=moreInfo;
        this.model=model;
        this.country=country;
        this.brand=brand;
        this.madeIn=madeIn;
        this.creationYear=creationYear;
        this.serialNumber=serialNumber;
        this.donatedBy=donatedBy;
        this.exhibitor=exhibitor;
        this.itemNo=itemNo;
        this.typeCat=typeCat;
        this.category=category;
        this.category2=category2;
        this.category3=category3;
        this.category4=category4;
    }
    public Artifact(String name, String designation, String description, String techCharacteristics, String moreInfo, String model, String country, String brand, String madeIn, int creationYear, String serialNumber, String donatedBy,String exhibitor, int itemNo, String typeCat, String category, String category2, String category3, String category4,List<TimeMachine> timeMachines){
        this.name = name;
        this.designation=designation;
        this.description=description;
        this.techCharacteristics=techCharacteristics;
        this.moreInfo=moreInfo;
        this.model=model;
        this.country=country;
        this.brand=brand;
        this.madeIn=madeIn;
        this.creationYear=creationYear;
        this.serialNumber=serialNumber;
        this.donatedBy=donatedBy;
        this.exhibitor=exhibitor;
        this.itemNo=itemNo;
        this.typeCat=typeCat;
        this.category=category;
        this.category2=category2;
        this.category3=category3;
        this.category4=category4;
        this.timeMachines=timeMachines;
    }

    public Artifact(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTechCharacteristics() {
        return techCharacteristics;
    }

    public void setTechCharacteristics(String techCharacteristics) {
        this.techCharacteristics = techCharacteristics;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getModel() {return model;}

    public void setModel(String model) { this.model = model; }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMadeIn() {
        return madeIn;
    }

    public void setMadeIn(String madeIn) {
        this.madeIn = madeIn;
    }

    public Integer getCreationYear() {
        return creationYear;
    }

    public void setCreationYear(Integer creationYear) {
        this.creationYear = creationYear;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDonatedBy() {
        return donatedBy;
    }

    public void setDonatedBy(String donatedBy) {
        this.donatedBy = donatedBy;
    }

    public String getExhibitor() {
        return exhibitor;
    }

    public void setExhibitor(String exhibitor) {
        this.exhibitor = exhibitor;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getTypeCat() {
        return typeCat;
    }

    public void setTypeCat(String typeCat) {
        this.typeCat = typeCat;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public String getCategory4() {
        return category4;
    }

    public void setCategory4(String category4) {
        this.category4 = category4;
    }

    public List<TimeMachine> getTimeMachines() {
        return timeMachines;
    }

    public void setTimeMachines(List<TimeMachine> timeMachines) {
        this.timeMachines = timeMachines;
    }

    public void addTimeMachine(TimeMachine t){
        this.timeMachines.add(t);
    }
    public Artifact getNullValues(Artifact artifact){
        if(artifact.getDesignation() == "")
            artifact.setDesignation("NULL");
        if(artifact.getTechCharacteristics() == "")
            artifact.setTechCharacteristics("NULL");
        if(artifact.getMoreInfo() == "")
            artifact.setMoreInfo("NULL");
        if(artifact.getModel() == "")
            artifact.setModel("NULL");
        if(artifact.getCountry() == "")
            artifact.setCountry("NULL");
        if(artifact.getBrand() == "")
            artifact.setBrand("NULL");
        if(artifact.getMadeIn() == "")
            artifact.setMadeIn("NULL");
        if(artifact.getCreationYear()==null)
            artifact.setCreationYear(0);
        if(artifact.getSerialNumber()== "")
            artifact.setSerialNumber("NULL");
        if(artifact.getDonatedBy()== "")
            artifact.setDonatedBy("NULL");
        if(artifact.getTypeCat()== "")
            artifact.setTypeCat("NULL");
        if(artifact.getCategory2()== "")
            artifact.setCategory2("NULL");
        if(artifact.getCategory3()== "")
            artifact.setCategory3("NULL");
        if(artifact.getCategory4()== "")
            artifact.setCategory4("NULL");
        return artifact;
    }


    @Override
    public String toString() {
        return "Artifact{" +
                "name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", techCharacteristics='" + techCharacteristics + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", country='" + country + '\'' +
                ", brand='" + brand + '\'' +
                ", madeIn='" + madeIn + '\'' +
                ", creationYear=" + creationYear +
                ", serialNumber='" + serialNumber + '\'' +
                ", donatedBy='" + donatedBy + '\'' +
                ", exhibitor='" + exhibitor + '\'' +
                ", itemNo=" + itemNo +
                ", typeCat='" + typeCat + '\'' +
                ", category='" + category + '\'' +
                ", category2='" + category2 + '\'' +
                ", category3='" + category3 + '\'' +
                ", category4='" + category4 + '\'' +
                '}';
    }
}
