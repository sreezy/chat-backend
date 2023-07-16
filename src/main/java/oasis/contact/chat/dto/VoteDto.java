package oasis.contact.chat.dto;

public class VoteDto {
    private int voteValue; // 1 for upvote, -1 for downvote

    public int getVoteValue() {
        return voteValue;
    }

    public void setVoteValue(int voteValue) {
        this.voteValue = voteValue;
    }
}

