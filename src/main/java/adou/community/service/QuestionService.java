package adou.community.service;

import adou.community.dto.PageDTO;
import adou.community.dto.QuestionDTO;
import adou.community.exception.CustomizeErrorCode;
import adou.community.exception.CustomizeException;
import adou.community.mapper.QuestionExtMapper;
import adou.community.mapper.QuestionMapper;
import adou.community.mapper.UserMapper;
import adou.community.model.Question;
import adou.community.model.QuestionExample;
import adou.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Resource
    private QuestionMapper questionMapper;
    @Resource
    private QuestionExtMapper questionExtMapper;

    public PageDTO list(Integer currentPage, Integer size) {

        //将Question和User封装到QuestionDTOList中，用于展示
        Integer totalCount = (int) questionMapper.countByExample(new QuestionExample()); //总条数
        Integer totalPage;//总页数
        //计算总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //不让页码越界
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        Integer offset = size * (currentPage - 1); //查询的起始数据序号 0开始
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(new QuestionExample(), new RowBounds(offset, size));//分页查询
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        //将QuestionDTOList和其他需要的东西封装到PageDTO中,并返回
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOList);
        pageDTO.set(totalPage, currentPage, size);
        return pageDTO;
    }


    public PageDTO listByUid(Long uid, Integer currentPage, Integer size) {
        //将Question和User封装到QuestionDTOList中，用于展示
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(uid);
        Integer totalCount = (int) questionMapper.countByExample(questionExample); //总条数
        Integer totalPage;//总页数
        //计算总页数
        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        //不让页码越界
        if (currentPage < 1) {
            currentPage = 1;
        }
        if (currentPage > totalPage) {
            currentPage = totalPage;
        }
        Integer offset = size * (currentPage - 1); //查询的起始数据序号 0开始
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(uid);
        List<Question> questionList = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));//分页查询
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        //将QuestionDTOList和其他需要的东西封装到PageDTO中,并返回
        PageDTO pageDTO = new PageDTO();
        pageDTO.setQuestionDTOList(questionDTOList);
        pageDTO.set(totalPage, currentPage, size);
        return pageDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.insert(question);
        } else {
            //更新
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());
            int update = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if (update == 0) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }
}
