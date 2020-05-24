var content = {
    init : function () {
        var _this = this;
        $('#content').on('click', function () {
            _this.content();
        });
    },
    content : function () {

        $.ajax({
            type: 'GET',
            url: '/api/v1/content/1',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8'
        }).done(function() {
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

content.init();