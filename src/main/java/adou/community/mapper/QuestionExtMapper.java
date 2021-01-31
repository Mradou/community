package adou.community.mapper;

import adou.community.dto.QuestionQueryDTO;
import adou.community.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    void incView(Question record);

    void incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}
