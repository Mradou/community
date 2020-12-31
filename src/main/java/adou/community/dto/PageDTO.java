package adou.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questionDTOList; //当前页面包含的数据
    private Integer currentPage; //当前所在页面
    private List<Integer> pages = new ArrayList<>(); //当前可以选择前往的页面
    private Integer totalCount; //总记录数
    private Integer totalPage; //总页数
    private boolean showPre;
    private boolean showFirst;
    private boolean showNext;
    private boolean showEnd;

    public void set(Integer totalPage, Integer currentPage, Integer size) {
        //int totalPage = (int) Math.ceil(totalCount / size); //总页数
        this.currentPage = currentPage;
        this.totalPage = totalPage;

        //让展示当前页码以及其的前三页和后三页
        pages.add(currentPage);
        for (int i = 1; i <= 3; i++) {
            if (currentPage - i > 0) {
                pages.add(0, currentPage - i);
            }
            if (currentPage + i <= totalPage) {
                pages.add(currentPage + i);
            }
        }

        //是否展示上一页
        if (currentPage == 1) {
            showPre = false;
        } else {
            showPre = true;
        }

        //是否展示下一页
        if (currentPage == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        //是否展示第一页
        if (pages.contains(1)) {
            showFirst = false;
        } else {
            showFirst = true;
        }

        //是否展示最后一页
        if (pages.contains(totalPage)) {
            showEnd = false;
        } else {
            showEnd = true;
        }
    }
}
