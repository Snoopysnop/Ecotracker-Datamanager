package application.ecoTracker.service.DTO.comment;

public class MainCommentDTO extends CommentDTO {
    
    private long observation_id;

    protected MainCommentDTO() {
        super();
    }

    public MainCommentDTO(String content, String author, long observation_id) {
        super(content, author);
        this.observation_id = observation_id;
    }

    public long getObservation_id() {
        return observation_id;
    }

    public void setObservation_id(long observation_id) {
        this.observation_id = observation_id;
    }

}
