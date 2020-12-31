package adou.community.service;

import adou.community.dto.PageDTO;
import adou.community.dto.QuestionDTO;
import adou.community.mapper.QuestionMapper;
import adou.community.mapper.UserMapper;
import adou.community.model.Question;
import adou.community.model.User;
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

    public PageDTO list(Integer currentPage, Integer size) {

        //将Question和User封装到QuestionDTOList中，用于展示
        Integer totalCount = questionMapper.totalCount(); //总条数
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
        List<Question> questionList = questionMapper.list(offset, size); //分页查询
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findUserById(question.getCreator());
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
}
