package application.ecoTracker.service.DTO.comment;

public class ReplyDTO extends CommentDTO {

    private long reference_id;

    protected ReplyDTO() {
        super();
    }

    public ReplyDTO(String content, String author, long reference_id) {
        super(content, author);
        this.reference_id = reference_id;
    }

    public long getReference_id() {
        return reference_id;
    }

    public void setReference_id(long reference_id) {
        this.reference_id = reference_id;
    }
     
}
