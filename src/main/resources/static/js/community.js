/**
 * 回复问题或评论的抽取
 * @param targetId
 * @param type
 * @param content
 */
function comment2target(targetId, type, content) {
    if (!content) {
        alert("不能回复空内容~");
        return;
    }
    $.ajax({
        url: "/comment",
        data: JSON.stringify({
            parentId: targetId,
            content: content,
            type: type
        }),
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                $("#comment_section").hide();
                window.location.reload();
            } else {
                if (data.code == 2003) {
                    const isAccepted = confirm(data.message);
                    if (isAccepted) {
                        window.open("https://github.com/login/oauth/authorize?client_id=a7a22c93e0849e04d393&redirect_uri=http://localhost/callback&scope=user&state=1")
                        window.localStorage.setItem("closable", true);
                    }
                } else {
                    alert(data.message)
                }
            }
        }
    });
}

/**
 * 对问题提交回复
 */
function post() {
    const questionId = $("#question_id").val();
    const comment = $("#comment_content").val();
    comment2target(questionId, 1, comment)
}

/**
 * 展开二级评论
 */
function collapseComments(e) {
    const id = e.getAttribute("data-id");
    const comments = $("#comment-" + id);

    // 获取一下二级评论的展开状态
    const collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("in");
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
    } else {
        const subCommentContainer = $("#comment-" + id);
        if (subCommentContainer.children().length != 1) {
            //展开二级评论
            comments.addClass("in");
            // 标记二级评论展开状态
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");
        } else {
            $.getJSON("/comment/" + id, function (data) {
                $.each(data.data.reverse(), function (index, comment) {
                    const mediaLeftElement = $("<div/>", {
                        "class": "media-left"
                    }).append($("<img/>", {
                        "class": "media-object img-rounded",
                        "src": comment.user.avatarUrl
                    }));

                    const mediaBodyElement = $("<div/>", {
                        "class": "media-body"
                    }).append($("<h5/>", {
                        "class": "media-heading",
                        "html": comment.user.name
                    })).append($("<div/>", {
                        "html": comment.content
                    })).append($("<div/>", {
                        "class": "menu"
                    }).append($("<span/>", {
                        "class": "pull-right",
                        //"html": moment(comment.gmtCreate).format('YYYY-MM-DD')
                    })));

                    const mediaElement = $("<div/>", {
                        "class": "media"
                    }).append(mediaLeftElement).append(mediaBodyElement);

                    const commentElement = $("<div/>", {
                        "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
                    }).append(mediaElement);

                    subCommentContainer.prepend(commentElement);
                });
                //展开二级评论
                comments.addClass("in");
                // 标记二级评论展开状态
                e.setAttribute("data-collapse", "in");
                e.classList.add("active");
            });
        }
    }
}

/**
 * 对评论提交回复
 */
function comment(e) {
    const commentID = e.getAttribute("data-id");
    const content = $("#input-" + commentID).val();
    comment2target(commentID, 2, content);
}

/**
 * 展示标签库
 */
function showSelectTag() {
    $("#selectTag").show();
}

/**
 * 将标签库内的标签添加到标签输入栏中
 * @param value
 */
function selectTag(e) {
    const value = e.getAttribute("data-tag");
    const previous = $("#tag").val();
    if (previous.indexOf(value) == -1) {
        if (previous) {
            $("#tag").val(previous + "," + value);
        } else {
            $("#tag").val(value);
        }
    }
}