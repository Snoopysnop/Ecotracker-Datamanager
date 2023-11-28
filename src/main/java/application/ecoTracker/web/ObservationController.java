package application.ecoTracker.web;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import application.ecoTracker.DAO.UserDAO;
import application.ecoTracker.DAO.CampaignDAO;
import application.ecoTracker.DAO.CommentDAO;
import application.ecoTracker.DAO.ObservationDAO;
import application.ecoTracker.domain.Campaign;
import application.ecoTracker.domain.Observation;
import application.ecoTracker.domain.User;
import application.ecoTracker.domain.comment.Comment;
import application.ecoTracker.domain.comment.MainComment;
import application.ecoTracker.domain.comment.Reply;
import application.ecoTracker.service.DTO.ObservationDTO;
import application.ecoTracker.service.DTO.comment.CommentDTO;
import application.ecoTracker.service.data.ObservationData;
import application.ecoTracker.service.data.Comment.CommentData;
import application.ecoTracker.service.data.Comment.MainCommentData;
import io.swagger.v3.oas.annotations.Operation;

@RestController
public class ObservationController {

    private static final Logger LOGGER = Logger.getLogger(ObservationController.class.getName());

    @Value("${observationsImageFolder}")
    private String observationsImageFolder;

    @Autowired
    private ObservationDAO observationDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private CampaignDAO campaignDAO;

    @Autowired
    private CommentDAO commentDAO;

    @RequestMapping(value = "/observation/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns the campaign {id}"
    )
    public ObservationData findById(@PathVariable long id){

        Observation observation;
        try {
            observation = observationDAO.findById(id).get();
        }

        catch (Exception exception) {
            LOGGER.info("Observation " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ObservationData(observation, observationsImageFolder);
    }

    @RequestMapping(value = "/observations", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns all observations"
    )
    public List<ObservationData> findAll() {
        List<Observation> observationList = observationDAO.findAll();

        List<ObservationData> observationDataList = new ArrayList<>();
        for(Observation observation : observationList){
            try {
                observationDataList.add(new ObservationData(observation, observationsImageFolder));
            }
            catch(Exception e){
                LOGGER.warning("error getting observation " + observation.getId());
                LOGGER.warning(e.toString());
            }
        }        

        return observationDataList;

    }

    @RequestMapping(value = "/observation/{id}/upload", method = RequestMethod.PUT)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Upload an image for the observation {id}"
    )
    public void uploadImage(@PathVariable long id, @RequestParam("image") MultipartFile image){

        // TODO : is user owner of the observation

        try {
            File path = new File(observationsImageFolder + id + "/");
            image.transferTo(new File(observationsImageFolder + id + "/" + path.list().length + ".png")); 
        } catch (Exception e) {
            LOGGER.info("Can't upload for observation " + id);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/observation/create", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Create an observation"
    )
    public ObservationData create(@RequestPart("observationDTO") ObservationDTO observationDTO, @RequestPart("image") MultipartFile image){

        // TODO : check if user logged in
        User author = userDAO.findByPseudo(observationDTO.getAuthor_pseudo());
        if(author == null) {
            LOGGER.info("User " + observationDTO.getAuthor_pseudo() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Campaign campaign;

        try{
            campaign = campaignDAO.findById(observationDTO.getCampaign_id()).get();
        }
        catch (Exception e) {
            LOGGER.info("Campaign " + observationDTO.getCampaign_id() + " not found");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
        Observation observation = new Observation(author, campaign, observationDTO.getTaxonomyGroup(), observationDTO.getTitle(), observationDTO.getLocation(), observationDTO.getDescription());
        observationDAO.save(observation);

        File pathFile = new File(observationsImageFolder + observation.getId());
        pathFile.mkdir();

        try {
            image.transferTo(new File(observationsImageFolder + observation.getId() + "/0" + ".png"));
        } catch (Exception e) {
            LOGGER.warning("Error creating Observation " + observation);
            e.printStackTrace();
            observationDAO.delete(observation);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        } 



        return new ObservationData(observation, observationsImageFolder);
    }

    @RequestMapping(value = "/observation/{id}/comment", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Add comment to the observation {id}"
    )
    public void comment(@PathVariable long id, @RequestBody CommentDTO commentDTO){
        // TODO : check if user logged in
        // TODO : handle user in body / user logged in

        User author = userDAO.findByPseudo(commentDTO.getAuthor());
        if(author == null){
            LOGGER.info("User " + commentDTO.getAuthor() + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Observation observation;
        try {
            observation = observationDAO.findById(id).get();
        } catch (Exception e) {
            LOGGER.info("Observation " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        MainComment comment = new MainComment(commentDTO.getContent(), author, observation);
        commentDAO.save(comment);
    }

    @RequestMapping(value = "/observation/comment/{id}/reply", method = RequestMethod.POST)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Reply to the comment {id}"
    )
    public void reply(@PathVariable long id, @RequestBody CommentDTO commentDTO){

        // TODO : check if user logged in
        // TODO : handle user in body / user logged in
        
        User author = userDAO.findByPseudo(commentDTO.getAuthor());
        if(author == null){
            LOGGER.info("User " + commentDTO.getAuthor() + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Comment reference  = commentDAO.findMainCommentById(id);
        if(reference == null) {
            LOGGER.info("Comment " + id + " not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Reply comment = new Reply(commentDTO.getContent(), author, reference);
        commentDAO.save(comment);
    }

    @RequestMapping(value = "/observation/{id}/comments", method = RequestMethod.GET)
    @ResponseBody
    @Operation(
        tags = {"Observation"},
        description = "Returns all comments for the observation {id}"
    )
    public List<MainCommentData> getComments(@PathVariable long id){
        List<MainComment> commentList = commentDAO.findMainCommentByObservationId(id);

        List<MainCommentData> commentDataList = new ArrayList<>();
        for(MainComment comment : commentList){
            List<CommentData> replies = new ArrayList<>();
            for(Reply reply : comment.getReplyList()) {
                replies.add(new CommentData(reply));
            }

            commentDataList.add(new MainCommentData(comment, comment.getId(), replies));
        }

        return commentDataList;

    }

}