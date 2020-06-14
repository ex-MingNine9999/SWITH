var login = {
    init : function () {
        var _this = this;
        $('#signin').on('click', function () {
            //_this.signin();
        });

        $('#signup').on('click', function () {
            _this.signup();
        });

        $('#forget').on('click', function () {
            _this.forget();
        });
    },

    signin : function () {
        var data = {
            email: $('#email').val(),
            password: $('#password').val()
        };

        if(data.email == ""){
            alert("아이디를 입력해주세요.");
            return;
        }

        else if(data.password == ""){
            alert("패스워드를 입력해주세요.");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v2/signin',
            dataType: 'json',
            contentType:'application/x-www-form-urlencoded; charset=utf-8;',
            data: data
        }).done(function(data, status, headers) {
            window.location.href = '/main';
        }).fail(function (data, status, headers) {
            $('#lg_error').show();
            $('#password').val("");
        });
    },

    signup : function () {
        var getMail = RegExp(/^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/);
        var getName = RegExp(/^[가-힣]+$/);

        var data = {
            email: $('#reg_email').val(),
            password: $('#reg_password').val(),
            name: $('#reg_name').val()
        };

        var confirmPW = $('#reg_password_confirm').val();

        if(data.email == "" || data.password == "" || data.name == "" || confirmPW == "") {
            alert("빈칸을 채워주세요")
            return;
        }

        else if(!getMail.test(data.email)){
            alert("올바르지 않은 이메일 형식입니다.");
            return;
        }

        else if(data.password.length < 8) {
            alert("비밀번호는 8자리 이상이어야 합니다.");
            return;
        }

        else if(confirmPW != data.password){
            alert("확인 비밀번호가 일치하지 않습니다.");
            return;
        }

        else if(data.name.length < 2) {
            alert("이름은 2글자 이상이어야 합니다.");
            return;
        }

        else if(!getName.test(data.name)){
            alert("올바르지 않은 이름입니다.");
            return;
        }

        $.ajax({
            type: 'POST',
            url: '/api/v2/signup',
            dataType: 'json',
            contentType:'application/json; charset=utf-8;',
            data: JSON.stringify(data)
        }).done(function() {
            alert("회원가입이 완료되었습니다.")
            window.location.href = '/login';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }
};

login.init();