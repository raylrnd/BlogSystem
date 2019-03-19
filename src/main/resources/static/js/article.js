
function subArticle() {
    var title = $('#articleForm input[name=title]').val();<!-- 寻找id=="articleForm"input 中name=="title"的标签-->
    var content = $('#text').val();

    if (title == '') {
        tale.alertWarn('请输入文章标题');
        return;
    }
    if (content == '') {
        tale.alertWarn('请输入文章内容');
        return;
    }


    var params = $("#articleForm").serialize();
    //console.log(params);
    alert(params);
//    var url = $('#articleForm #articleid').val() != '' ? '/modifyArticle' : '/publishArticle';
    $.ajax({
        url: "/publishArticle"
    })
    $.post({
        url:'/publishArticle',
        data:params,
        success: function (result) {
            if (result && result.code == 'success') {
                tale.alertOk({
                    text:'文章保存成功',
                    then: function () {
                        setTimeout(function () {
                            window.location.href = '/admin/article';
                        }, 500);
                    }
                });
            } else {
                tale.alertError(result.msg || '保存文章失败');
            }
        }
    });
}