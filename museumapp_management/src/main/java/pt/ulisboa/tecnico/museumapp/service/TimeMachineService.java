package pt.ulisboa.tecnico.museumapp.service;

import org.springframework.web.multipart.MultipartFile;
import pt.ulisboa.tecnico.museumapp.entities.ArtifactEntity;
import pt.ulisboa.tecnico.museumapp.entities.TimeMachineEntity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface TimeMachineService {
    String encodeToString(BufferedImage image, String type);

    void saveRoomPlan(String uploadDir, String fileName,
                      MultipartFile multipartFile) throws IOException;

    Iterable <TimeMachineEntity> getAllTimeMachines();
    TimeMachineEntity createTimeMachine(TimeMachineEntity timeMachine);

    Optional<TimeMachineEntity> findTimeMachine(Integer timeMachineId);

    Optional<TimeMachineEntity> findById(Integer timeMachineId);

    Optional<TimeMachineEntity> findTimeMachineByName(String name);

    void updateArtifacts();

    List<ArtifactEntity> listArtifacts(Integer timeMachineId);

    void deleteTimeMachine(Integer timeMachineId);

    void updateVisitTime();

    void updateArtifactsOnTimeMachine(TimeMachineEntity t);

    boolean artifactOnTimeMachineByCategory(ArtifactEntity artifact, TimeMachineEntity t);

    boolean artifactOnTimeMachineByYear(ArtifactEntity artifact, TimeMachineEntity t);

    boolean artifactOnTimeMachineByTopic(ArtifactEntity artifact, TimeMachineEntity t);

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
    String getRoomPlanName(String timeMachineName);

    void deleteRoomPlan(Integer tm_id);
}
