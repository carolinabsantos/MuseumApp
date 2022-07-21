package pt.ulisboa.tecnico.museumapp.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//@EnableJpaAuditing
@Entity
public class ArtifactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    protected String name;

    @Column(name = "designation")
    protected String designation;

    @Column(name = "description", nullable = false)
    protected String description;

    @Column(name = "techCharacteristics")
    protected String techCharacteristics;

    @Column(name = "moreInfo")
    protected String moreInfo;

    @Column(name = "model")
    protected String model;

    @Column(name = "country")
    protected String country;

    @Column(name = "brand")
    protected String brand;

    @Column(name = "madeIn")
    protected String madeIn;

    @Column(name = "creationYear")
    protected Integer creationYear;

    @Column(name = "serialNumber")
    protected String serialNumber;

    @Column(name = "donatedBy")
    protected String donatedBy;

    @Column(name = "exhibitor", nullable = false)
    protected String exhibitor;

    @Column(name = "itemNo", nullable = false)
    protected Integer itemNo;

    @Column(name = "typeCat")
    protected String typeCat;

    @Column(name = "category", nullable = false)
    protected String category;

    @Column(name = "category2")
    protected String category2;

    @Column(name = "category3")
    protected String category3;

    @Column(name = "category4")
    protected String category4;

    @Column(name = "timeToVisit")
    protected Integer timeToVisit;

    @ManyToMany(mappedBy = "artifacts")
    List<TimeMachineEntity> timeMachines;

    public ArtifactEntity(String name, String designation, String description, String techCharacteristics, String moreInfo, String model, String country, String brand, String madeIn, int creationYear, String serialNumber, String donatedBy,String exhibitor, int itemNo, String typeCat, String category, String category2, String category3, String category4, Integer timeToVisit){
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
        this.timeToVisit=timeToVisit;
    }

    public ArtifactEntity(String name, String designation, String description, String techCharacteristics, String moreInfo, String model, String country, String brand, String madeIn, int creationYear, String serialNumber, String donatedBy,String exhibitor, int itemNo, String typeCat, String category, String category2, String category3, String category4, List<TimeMachineEntity> timeMachines, Integer timeToVisit){
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
        this.timeToVisit=timeToVisit;
    }
    public ArtifactEntity(){}

    public Integer getId() {
        return id;
    }

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

    public List<TimeMachineEntity> getTimeMachines() {
        return timeMachines;
    }

    public void setTimeMachines(List<TimeMachineEntity> timeMachines) {
        this.timeMachines = timeMachines;
    }

    public void addTimeMachine(TimeMachineEntity timeMachine) {
        this.timeMachines.add(timeMachine);
    }

    public Integer getTimeToVisit() {
        return timeToVisit;
    }

    public void setTimeToVisit(Integer timeToVisit) {
        this.timeToVisit = timeToVisit;
    }

    @Override
    public String toString() {
        return "ArtifactEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", description='" + description + '\'' +
                ", techCharacteristics='" + techCharacteristics + '\'' +
                ", moreInfo='" + moreInfo + '\'' +
                ", model='" + model + '\'' +
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
                ", timeToVisit='" + timeToVisit +
                '}';
    }
}