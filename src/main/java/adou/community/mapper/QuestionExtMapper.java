package adou.community.mapper;

import adou.community.model.Question;

public interface QuestionExtMapper {
    void incView(Question record);

    void incCommentCount(Question record);
}
