package adou.community.mapper;

import adou.community.model.Question;

import java.util.List;

public interface QuestionMapper {

    void save(Question question);

    List<Question> list();
}
