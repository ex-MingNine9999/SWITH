var main = {
    init : function () {
        var _this = this;
        $('#insert').on('click', function () {
            _this.save();
        });

        $('#update').on('click', function () {
            _this.update();
        });

        $('#delete').on('click', function () {
            _this.delete();
        });
        $('#board_file_down').on('click', function () {
            _this.fileDown();
        });
    },

    save : function () {
        var formData = new FormData();
        formData.append("title", $("input[name=board_title]").val());
        formData.append("subTitle", $("input[name=board_sub_title]").val());
        formData.append("content", $("textarea[name=board_content]").val());
        formData.append("boardType", $("select[name=board_type]").val());

        if(!$('#board_file').val() == null) {
            alert("asdf");
            formData.append("file", $("input[name=board_file]")[0].files[0]);
        }

        if($('#board_type option:selected').val() == "--게시판--"){
            alert("게시판을 선택해주세요");
            return;
        }
        else if($('#board_title').val() == ""){
            alert("제목을 입력해주세요");
            return;
        }
        else if($('#board_content').val() == ""){
            alert("내용을 입력해주세요");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            enctype: "multipart/form-data",
            data: formData,
            processData: false,
            contentType: false
        }).done(function() {
            alert("글이 등록되었습니다.")
            window.location.href = '/board/'+ $('#board_type option:selected').val();
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            boardType: $('#board_type option:selected').val(),
            title: $('#board_title').val(),
            subTitle: $('#board_sub_title').val(),
            content: $('#board_content').val()
        };

        var id = $('#board_id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/board/'+ data.boardType;
        }).fail(function (error) {
            alert("권한이 없습니다.");
        });
    },

    delete : function () {
        var id = $('#board_id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/board/' + $('#board_type option:selected').val();
        }).fail(function (error) {
            alert("권한이 없습니다.");
        });
    },

    fileDown : function () {
        var id = $('#board_id').val();

        $.ajax({
            type: 'GET',
            url: '/api/v1/download/'+id,
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            window.location = '/api/v1/download/'+id;
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

main.init();