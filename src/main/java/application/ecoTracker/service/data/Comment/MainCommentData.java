package application.ecoTracker.service.data.Comment;

import java.util.ArrayList;
import java.util.List;

import application.ecoTracker.domain.comment.MainComment;

public class MainCommentData extends CommentData {

    private long id;
    private List<CommentData> replies = new ArrayList<>();
   

    protected MainCommentData(){
        super();
    }

    public MainCommentData(MainComment comment, long id, List<CommentData> replies) {
        super(comment);
        this.id = id;

        for(CommentData reply : replies) {
            this.replies.add(reply);
        }

    }

    public List<CommentData> getReplies() {
        return replies;
    }

    public void setReplies(List<CommentData> replies) {
        this.replies = replies;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    
}
