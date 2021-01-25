package adou.community.mapper;

import adou.community.model.Comment;

public interface CommentExtMapper {
    void incCommentCount(Comment record);
}
