package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;
import pt.ulisboa.tecnico.museumapp.entities.*;
import pt.ulisboa.tecnico.museumapp.repositories.ArtifactRepository;
import pt.ulisboa.tecnico.museumapp.repositories.TimeMachineRepository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeMachineServiceImpl implements TimeMachineService{
    @Autowired
    TimeMachineRepository timeMachineRepository;

    @Autowired
    ArtifactRepository artifactRepository;

    private static final String ROOM_PLAN_IMAGE_PATH = "./src/main/resources/static/room-plans/TM-";

    @Override
    public Iterable<TimeMachineEntity> getAllTimeMachines() {
        return timeMachineRepository.findAll();
    }

    @Override
    public TimeMachineEntity createTimeMachine(TimeMachineEntity timeMachine) {
        return timeMachineRepository.save(timeMachine);
    }

    @Override
    public Optional<TimeMachineEntity> findTimeMachine(Integer timeMachineId) {
        return timeMachineRepository.findById(timeMachineId);
    }

    @Override
    public Optional<TimeMachineEntity> findById(Integer timeMachineId){
        return timeMachineRepository.findById(timeMachineId);
    }

    @Override
    public Optional<TimeMachineEntity> findTimeMachineByName(String name) {
        return timeMachineRepository.findByNameEquals(name);
    }

    @Override
    public void updateArtifacts() {
        for (TimeMachineEntity timeMachine : timeMachineRepository.findAll()) {
            updateArtifactsOnTimeMachine(timeMachine);
            timeMachine.setArtifactsCount(timeMachine.getArtifacts().size());
        }
    }

    @Override
    public List<ArtifactEntity> listArtifacts(Integer timeMachineId) {
        TimeMachineEntity timeMachine = timeMachineRepository.findById(timeMachineId).get();
        return timeMachine.getArtifacts();
    }

    @Override
    public void deleteTimeMachine(Integer id){
        timeMachineRepository.deleteById(id);
    }

    @Override
    public void updateVisitTime() {
        for (TimeMachineEntity timeMachine : timeMachineRepository.findAll()) {
            Integer visitTime = 0;
            for (ArtifactEntity a : timeMachine.getArtifacts()){
                visitTime += a.getTimeToVisit();
            }
            timeMachine.setVisitTime(visitTime);
        }
    }

    @Override
    public void updateArtifactsOnTimeMachine(TimeMachineEntity timeMachine){
        for (ArtifactEntity artifact : artifactRepository.findAll()){
            if (artifactOnTimeMachineByCategory(artifact,timeMachine) | artifactOnTimeMachineByYear(artifact, timeMachine) | artifactOnTimeMachineByTopic(artifact, timeMachine)) {
                if(timeMachine.getArtifactsCount()==null){
                    timeMachine.addArtifact(artifact);
                }
                else if(!(timeMachine.getArtifacts().contains(artifact))){
                    timeMachine.addArtifact(artifact);
                }
            }
        }
    }
    @Override
    public boolean artifactOnTimeMachineByCategory(ArtifactEntity artifact, TimeMachineEntity t){
        if (artifact.getCategory().equals(t.getName()) | artifact.getCategory2().equals(t.getName()) | artifact.getCategory3().equals(t.getName()) | artifact.getCategory4().equals(t.getName()))
            return true;
        return false;
    }
    @Override
    public boolean artifactOnTimeMachineByYear(ArtifactEntity artifact, TimeMachineEntity t){
        if (artifact.getCreationYear().toString().equals(t.getName()))
            return true;
        return false;
    }
    @Override
    public boolean artifactOnTimeMachineByTopic(ArtifactEntity artifact, TimeMachineEntity t){
        if(artifact.getCountry().equals(t.getName()) | artifact.getBrand().equals(t.getName()) | artifact.getDonatedBy().equals(t.getName()) | artifact.getExhibitor().equals(t.getName()))
            return true;
        return false;
    }

    @Override
    public void saveRoomPlan(String uploadDir, String fileName,
                             MultipartFile multipartFile) throws IOException {
        String dir = "./src/main/resources/static/room-plans";
        Path uploadPath = Paths.get(dir);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    /*@Override
    public String dowloadRoomPlan(String timeMachineName, File roomPlanFile) {
        String tMName;

        List<FileItem> formItems = upload.parseRequest(request);

        if(timeMachineName.contains(" ")){
            tMName=timeMachineName.replace(" ", "-");
        } else if (timeMachineName.contains(".")) {
            tMName=timeMachineName.replace(".", "-");
        } else tMName = timeMachineName;
        System.out.println("File name to save: " + tMName);

        String roomPlanName = tMName + ".png";
        String filePath = ROOM_PLAN_IMAGE_PATH + roomPlanName;
        File path = new File(filePath);

        try {
            if (formItems != null && formItems.size() > 0) {
// iterates over form's fields
                for (FileItem item : formItems) {
                    if (!item.isFormField()) {
                        System.out.println("File path to save: " + filePath);
                        item.write(path);
                        request.setAttribute("message",
                                "Upload has been done successfully!");

                    }
                }
                }
// processes only fields that are not form fields


            BufferedImage bfImage = ImageIO.read(new FileInputStream(roomPlanFile));
                   *//* new BufferedImage(963,640,BufferedImage.TYPE_INT_ARGB);*//*
            ImageIO.write(bfImage, "png", path);

            return roomPlanName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void doPost(String timeMachineName, HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
// checks if the request actually contains upload file
        String tMName;
        if(timeMachineName.contains(" ")){
            tMName=timeMachineName.replace(" ", "-");
        } else if (timeMachineName.contains(".")) {
            tMName=timeMachineName.replace(".", "-");
        } else tMName = timeMachineName;
        System.out.println("File name to save: " + tMName);

        String roomPlanName = tMName + ".png";
        String filePath = ROOM_PLAN_IMAGE_PATH + roomPlanName;

        if (!ServletFileUpload.isMultipartContent(request)) {
// if not, we stop here
            PrintWriter writer = response.getWriter();
            writer.println("Error: Form must has enctype=multipart/form-data.");
            writer.flush();
            return;
        }

// configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
// sets memory threshold - beyond which files are stored in disk
        factory.setSizeThreshold(MEMORY_THRESHOLD);
// sets temporary location to store files
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);

// sets maximum size of upload file
        upload.setFileSizeMax(MAX_FILE_SIZE);

// sets maximum size of request (include file + form data)
        upload.setSizeMax(MAX_REQUEST_SIZE);

// creates the directory if it does not exist
        File uploadDir = new File(filePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
// parses the request's content to extract file data
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest((RequestContext) request);

            if (formItems != null && formItems.size() > 0) {
// iterates over form's fields
                for (FileItem item : formItems) {
// processes only fields that are not form fields
                    if (!item.isFormField()) {
                        String fileName = new File(item.getName()).getName();
                        String filesPath = filePath + File.separator + fileName;
                        File storeFile = new File(filePath);


                        item.write(storeFile);
                        request.setAttribute("message",
                                "Upload has been done successfully!");
                    }
                }
            }
        } catch (Exception ex) {
            request.setAttribute("message",
                    "There was an error: " + ex.getMessage());
        }

        request.getServletContext().getRequestDispatcher("/message.jsp").forward(request, response);
    }*/
    @Override
    public String getRoomPlanName (String timeMachineName){
        String tMName;

        if(timeMachineName.contains(" ")){
            tMName=timeMachineName.replace(" ", "-");
        } else if (timeMachineName.contains(".")) {
            tMName=timeMachineName.replace(".", "-");
        } else tMName = timeMachineName;
        String roomPlanName = tMName + ".png";
        return roomPlanName;
    }

    @Override
    public void deleteRoomPlan (Integer tm_id){
        String tmName = timeMachineRepository.getById(tm_id).getName();
        String roomPlanName = getRoomPlanName(tmName);
        File myObj = new File(ROOM_PLAN_IMAGE_PATH + roomPlanName);
        if (myObj.delete()) {
            System.out.println("Deleted the file: " + myObj.getName());
        } else {
            System.out.println("Failed to delete the file.");
        }
    }


    @Override
    public String encodeToString(BufferedImage image, String type) {

        String imageString=null;
        String encodedImage=null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {

            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();


            encodedImage= Base64Utils.encodeToString(imageBytes);

            imageString = "data:image/"+type+";base64,"+encodedImage;

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

    @Override
    public void updateTimeMachine(Integer id, Integer capacity, TypeOfTimeMachine type, String image){
        timeMachineRepository.updateTimeMachineType(id, type);
        timeMachineRepository.updateTimeMachineCapacity(id, capacity);
        timeMachineRepository.updateTimeMachineImage(id, image);
    }
}
