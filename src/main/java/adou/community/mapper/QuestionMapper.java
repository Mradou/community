package adou.community.mapper;

import adou.community.dto.QuestionDTO;
import adou.community.model.Question;

import java.util.List;

public interface QuestionMapper {

    void save(Question question);

    List<Question> list(Integer offset, Integer size);

    Integer totalCount();

    List<Question> listByUid(Integer uid, Integer offset, Integer size);

    Integer totalCountByUid(Integer uid);

    Question getById(Integer id);
}

