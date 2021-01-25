package adou.community.mapper;

import adou.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    void incView(Question record);

    void incCommentCount(Question record);

    List<Question> selectRelated(Question question);
}
