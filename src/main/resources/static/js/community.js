function post() {
    const questionId = $("#question_id").val();
    const comment = $("#comment_content").val();
    $.ajax({
        url: "/comment",
        data: JSON.stringify({
            parentId: questionId,
            content: comment,
            type: 1
        }),
        type: "POST",
        contentType: "application/json",
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                $("#comment_section").hide();
            } else {
                if(data.code = 2003){
                    const isAccepted = confirm(data.message);
                    if(isAccepted){
                        window.open("https://github.com/login/oauth/authorize?client_id=a7a22c93e0849e04d393&redirect_uri=http://localhost/callback&scope=user&state=1")
                        window.localStorage.setItem("closable",true);

                    }
                }else {
                    alert(data.message)
                }
            }
        }
    });
}