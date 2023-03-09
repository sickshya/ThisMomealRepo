/**
 *
 */
$(function () {
    var idchk = false; // 아이디 검사
    var pwdchk = false; // 패스워드
    var pwdconfirmchk = false; // 패스워드 확인
    var namechk = false; // 이름
    var emchk = false; // 이메일
    var emconfirmchk = false; // 이메일 인증

    var $id = $("#id")
    var $userId = $("#userId");
    var $nickname = $("#nickName");
    var $username = $("#userName");
    var $password = $("#password");
    var $pwdconfirm = $("#pwdconfirm");

    var $memail = $("#memail");
    var $checkEmail = $("#checkEmail"); // 인증번호 발송 버튼
    var $memailconfirm = $("#memailconfirm"); // 인증번호 확인input
    var $memailconfirmTxt = $("#memailconfirmTxt"); // 인증번호 확인 txt

    // 아이디 정규식 검사
    $userId.on("keyup", function () { // 키보드 눌렀을 때 실행
        var regExp = /^[a-zA-Z0-9]{5,15}$/g;

        if (!regExp.test($userId.val())) { // id 가 공백인 경우 체크
            idchk = false;
            $id.html("<span id='check'>사용할 수 없는 아이디입니다.</span>");
            $("#check").css(
                {"color": "#FA3E3E", "font-weight": "bold", "font-size": "10px"}
            )
        } else { // 공백아니면 중복체크
            $.ajax({
                type: "POST",
                url: "/signup/checkId.do",
                data: {
                    "userId": $userId.val()
                },
                success: function (data) {
                    if (data == 1) { // 1이면 중복
                        idchk = false;
                        $id.html("<span id='check'>이미 존재하는 아이디입니다</span>")
                        $("#check").css(
                            {"color": "#FA3E3E", "font-weight": "bold", "font-size": "10px"}
                        )
                        //console.log("중복아이디");
                    } else { // 아니면 중복아님
                        idchk = true;
                        $id.html("<span id='check'>사용가능한 아이디입니다</span>")

                        $("#check").css(
                            {"color": "#0D6EFD", "font-weight": "bold", "font-size": "10px"}
                        )
                        //console.log("중복아닌 아이디");
                    }
                }
            })
        }
    });

    // 패스워드 2중 검사
    $pwdconfirm.on("keyup", function () {
        if ($pwdconfirm.val() != $password.val()) {
            pwdconfirmchk = false;
            //console.log("불일치");
            $("#pwdconfirmchk").html("<span id='checkpwd'>비밀번호가 일치하지 않습니다</span>")
            $("#checkpwd").css(
                {"color": "#FA3E3E", "font-weight": "bold", "font-size": "10px"}
            )
        } else {
            pwdconfirmchk = true;
            //console.log("동일한 비밀번호");
            $("#pwdconfirmchk").html("<span id='checkpwd'>비밀번호 일치 확인</span>")
            $("#checkpwd").css(
                {"color": "#0D6EFD", "font-weight": "bold", "font-size": "10px"}
            )
        }
    })

    // 이메일 정규식 검사
    $memail.on("keyup", function () {
        var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        //console.log("email : "+$memail.val());
        if (!regExp.test($memail.val())) {
            //console.log("형식 미확인");
            emchk = false;

            $("#mailTxt").html("<span id='chkmail'>이메일 형식이 맞지 않습니다</span>")
            $("#chkmail").css(
                {"color": "#FA3E3E", "font-weight": "bold", "font-size": "10px"}
            )
        } else {
            emchk = true;

            //console.log("형식 확인");
            $("#mailTxt").html("<span id='chkmail'>이메일을 형식을 확인했습니다</span>")
            $("#chkmail").css(
                {"color": "#0D6EFD", "font-weight": "bold", "font-size": "10px"}
            )
        }
    })

    // 이메일 인증번호
    $checkEmail.click(function () {
        $.ajax({
            type: "POST",
            url: "/signup/mailConfirm.do",
            data: {
                "email": $("#memail").val()
            },
            success: function (data) {
                alert("인증번호가 발송 되었습니다. \n 확인부탁드립니다.")
                console.log("data : " + data)
                chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt)
            }
        })
    })

    // 이메일 인증번호 체크 함수
    function chkEmailConfirm(data, $memailconfirm, $memailconfirmTxt) {
        $memailconfirm.on("keyup", function () {
            if (data != $memailconfirm.val()) {
                emconfirmchk = false;
                $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호가 잘못되었습니다</span>")
                $memconfirmchk.css(
                    {"color": "#FA3E3E", "font-weight": "bold", "font-size": "10px"}
                )
                //console.log("중복아이디");
            } else {
                emconfirmchk = true;
                $memailconfirmTxt.html("<span id='emconfirmchk'>인증번호 확인 완료</span>")

                $memconfirmchk.css(
                    {"color": "#0D6EFD", "font-weight": "bold", "font-size": "10px"}
                )
            }
        })
    }

    // formSubmit 함수
    function formSubmit() {

        // console.log("idchk : "+idchk) console.log("pwdchk : "+pwdchk)
        // console.log("pwdconfrimchk : "+pwdconfrimchk) console.log("namechk :
        // "+namechk) console.log("birchk : "+birchk) console.log("emchk : "+emchk)
        // console.log("emconfirmchk : "+emconfirmchk) console.log("phchk : "+phchk)
        // console.log("select : "+$("#select").val())

        /*
        if (!idchk) {
            frm
                .userId
                .focus();
            return false;
        } else if (!pwdchk) {
            frm
                .password
                .focus();
            return false;
        } else if (!pwdconfrimchk) {
            frm
                .pwdconfirm
                .focus();
            return false;
        } else if (!namechk) {
            frm
                .mname
                .focus();
            return false;
        } else if (!emchk) {
            frm
                .memail
                .focus();
            return false;
        } else if (!emconfirmchk) {
            frm
                .memailconfirm
                .focus();
            return false;
        } else if ($("#maddr").val() == "") {
            frm
                .maddr
                .focus();
            return false;
        } else {
            return true;
        }
        */
    }
})