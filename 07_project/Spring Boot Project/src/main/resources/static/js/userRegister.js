    // ------------------------------
    // 기존 회원가입 관련 유효성 검사 (아이디, 비밀번호, 생년월일, 휴대전화, 이메일, 이름 등)
    // ------------------------------
    let idCheckPassed = false;

    function validateIdFormat(id) {
        const regex = /^[A-Za-z0-9]{4,8}$/;
        return regex.test(id);
    }

    function validatePwdFormat(pwd) {
        const regex = /^[A-Za-z0-9]{4,12}$/;
        return regex.test(pwd);
    }

    function validateName(name) {
        const regex = /^[A-Za-z가-힣]+$/;
        return regex.test(name);
    }

    function validateBirth(birth) {
        const regex = /^[0-9]{8}$/;
        if (!regex.test(birth)) {
            return {
                valid: false,
                message: "생년월일은 'YYYYMMDD'의 형식입니다."
            };
        }
        if (birth < "19000101") {
            return {
                valid: false,
                message: "정말 그때 태어나셨나요?"
            };
        }
        if (birth > "20241231") {
            return {
                valid: false,
                message: "가입 가능한 생년월일은 24년 12월 31일 이전입니다."
            };
        }
        return {
            valid: true,
            message: "생년월일 형식이 올바릅니다."
        };
    }

    function validateTel(tel) {
        const regex = /^[0-9]{11}$/;
        return regex.test(tel);
    }

    function validateEmail(email) {
        const regex = /^[a-z0-9]+@[a-z]+\.[a-z]+$/;
        return regex.test(email);
    }

    // ------------------------------
    // 아이디 관련 이벤트
    // ------------------------------
    const userIdField = document.getElementById('userId');
    const idValidationMessage = document.getElementById('idValidationMessage');
    const idDuplicateMessage = document.getElementById('idDuplicateMessage');
    userIdField.addEventListener('input', function() {
        const idVal = userIdField.value;
        idCheckPassed = false;
        idDuplicateMessage.textContent = "";
        if (!validateIdFormat(idVal)) {
            idValidationMessage.textContent = "아이디는 영어 소문자, 대문자, 숫자만 입력 가능하며, 4글자 이상 8글자 이하로 입력해주세요.";
            idValidationMessage.className = "message red";
        } else {
            idValidationMessage.textContent = "아이디 형식이 올바릅니다.";
            idValidationMessage.className = "message blue";
        }
    });
    document.getElementById('idCheckBtn').addEventListener('click', function() {
        const idVal = userIdField.value;
        if (!validateIdFormat(idVal)) {
            idDuplicateMessage.textContent = "유효하지 않은 아이디 형식입니다.";
            idDuplicateMessage.className = "message red";
            idCheckPassed = false;
            return;
        }
        fetch("/user/idCheck?id=" + encodeURIComponent(idVal))
            .then(response => response.json())
            .then(data => {
                if (data.available) {
                    idDuplicateMessage.textContent = "사용 가능합니다.";
                    idDuplicateMessage.className = "message blue";
                    idCheckPassed = true;
                } else {
                    idDuplicateMessage.textContent = "중복된 ID가 있습니다. 다시 입력해주세요.";
                    idDuplicateMessage.className = "message red";
                    idCheckPassed = false;
                }
            })
            .catch(error => {
                console.error("ID 중복 검사 오류:", error);
                idDuplicateMessage.textContent = "아이디 중복 검사에 실패했습니다.";
                idDuplicateMessage.className = "message red";
                idCheckPassed = false;
            });
    });

    // ------------------------------
    // 비밀번호 및 비밀번호 확인 이벤트
    // ------------------------------
    const pwdField = document.getElementById('pwd');
    const pwd2Field = document.getElementById('pwd2');
    const pwdValidationMessage = document.getElementById('pwdValidationMessage');
    const pwdMatchMessage = document.getElementById('pwdMatchMessage');
    pwdField.addEventListener('input', function() {
        const pwdVal = pwdField.value;
        if (!validatePwdFormat(pwdVal)) {
            pwdValidationMessage.textContent = "비밀번호는 영어 소,대문자 및 숫자를 합쳐서 최소 4, 최대 12자까지 입력 가능합니다.";
            pwdValidationMessage.className = "message red";
        } else {
            pwdValidationMessage.textContent = "사용 가능한 비밀번호 입니다.";
            pwdValidationMessage.className = "message blue";
        }
        checkPwdMatch();
    });
    pwd2Field.addEventListener('input', checkPwdMatch);

    function checkPwdMatch() {
        const pwdVal = pwdField.value;
        const pwd2Val = pwd2Field.value;
        if (pwdVal === "" || pwd2Val === "") {
            pwdMatchMessage.textContent = "";
            return;
        }
        if (pwdVal === pwd2Val) {
            pwdMatchMessage.textContent = "비밀번호가 일치합니다.";
            pwdMatchMessage.className = "message blue";
        } else {
            pwdMatchMessage.textContent = "비밀번호가 일치하지 않습니다.";
            pwdMatchMessage.className = "message red";
        }
    }

    // ------------------------------
    // 생년월일, 휴대전화, 이메일, 이름 유효성 이벤트 (기존과 동일)
    // ------------------------------
    const birthField = document.getElementById('birth');
    const birthValidationMessage = document.getElementById('birthValidationMessage');
    birthField.addEventListener('blur', function() {
        const birthVal = birthField.value;
        const result = validateBirth(birthVal);
        if (!result.valid) {
            birthValidationMessage.textContent = result.message;
            birthValidationMessage.className = "message red";
        } else {
            birthValidationMessage.textContent = result.message;
            birthValidationMessage.className = "message blue";
        }
    });
    const telField = document.getElementById('telNum');
    const telValidationMessage = document.getElementById('telValidationMessage');
    telField.addEventListener('blur', function() {
        const telVal = telField.value;
        if (!validateTel(telVal)) {
            telValidationMessage.textContent = "휴대폰 번호의 양식은 '01012345678' 입니다.";
            telValidationMessage.className = "message red";
        } else {
            telValidationMessage.textContent = "휴대전화 형식이 올바릅니다.";
            telValidationMessage.className = "message blue";
        }
    });
    const emailField = document.getElementById('email');
    const emailValidationMessage = document.getElementById('emailValidationMessage');
    emailField.addEventListener('blur', function() {
        const emailVal = emailField.value;
        if (!validateEmail(emailVal)) {
            emailValidationMessage.textContent = "이메일을 다시 한번 확인해주세요";
            emailValidationMessage.className = "message red";
        } else {
            emailValidationMessage.textContent = "이메일 형식이 올바릅니다.";
            emailValidationMessage.className = "message blue";
        }
    });
    const nameField = document.getElementById('name');
    const nameValidationMessage = document.getElementById('nameValidationMessage');
    nameField.addEventListener('input', function() {
        const nameVal = nameField.value;
        if (!validateName(nameVal)) {
            nameValidationMessage.textContent = "이름을 바르게 입력해주세요. (영어, 한글만 허용)";
            nameValidationMessage.className = "message red";
        } else {
            nameValidationMessage.textContent = "이름 형식이 올바릅니다.";
            nameValidationMessage.className = "message blue";
        }
    });

    // ------------------------------
    // 프로필 사진 업로드 처리
    // ------------------------------
    const MAX_PROFILE_SIZE = 2097152; // 2MB 제한 (2*1024*1024)
    const profileExtRegex = /\.(png|jpe?g|webp|bmp)$/i; // 허용 확장자 (대소문자 무시)
    const profilePicInput = document.getElementById('profilePic');
    const profilePreviewUl = $("#profilePreview");

    // 파일 선택 시 이벤트
    $("#profilePic").on("change", function(e) {
        const file = this.files[0]; // 한 장만 선택
        if (!file) return;
        // 파일 크기 검사
        if (file.size > MAX_PROFILE_SIZE) {
            alert("최대 용량은 2mb까지 입니다");
            $(this).val(""); // 파일 입력 초기화
            profilePreviewUl.empty();
            return;
        }
        // 확장자 검사
        if (!profileExtRegex.test(file.name)) {
            alert("사진파일(.png, .jpg, .jpeg, .webp, .bmp)만 등록 가능합니다.");
            $(this).val(""); // 파일 입력 초기화
            profilePreviewUl.empty();
            return;
        }
        // 미리보기 생성: 파일명과 cancel 아이콘 표시
        let previewHtml = "<li data-filename='" + file.name + "'>";
        previewHtml += "<span>" + file.name + "</span>";
        previewHtml += " <img src='/img/cancle.png' width='16px' class='cancel-icon' onclick='removeProfilePic()' alt='삭제'>";
        previewHtml += "</li>";
        profilePreviewUl.empty().append(previewHtml);
    });

    // 프로필 사진 취소 함수: cancel 아이콘 클릭 시 미리보기 및 파일 입력 초기화
    function removeProfilePic() {
        $("#profilePreview").empty();
        $("#profilePic").val("");
    }

    // ------------------------------
    // 폼 제출 시 전체 검증
    // ------------------------------
    $("#registerForm").on("submit", function(e) {
        if (!idCheckPassed) {
            alert("아이디 검사를 다시 시도해주세요");
            e.preventDefault();
            return;
        }
        const pwdVal = pwdField.value;
        const pwd2Val = pwd2Field.value;
        if (!validatePwdFormat(pwdVal) || pwdVal !== pwd2Val) {
            alert("비밀번호를 다시 확인해주세요");
            e.preventDefault();
            return;
        }
        const nameVal = nameField.value;
        if (!validateName(nameVal)) {
            alert("이름을 바르게 입력해주세요.");
            e.preventDefault();
            return;
        }
        const genderRadios = document.getElementsByName("gender");
        let genderChecked = false;
        for (let radio of genderRadios) {
            if (radio.checked) {
                genderChecked = true;
                break;
            }
        }
        if (!genderChecked) {
            alert("성별을 체크해주세요");
            e.preventDefault();
            return;
        }
        const birthVal = birthField.value;
        if (!validateBirth(birthVal).valid) {
            alert("올바른 유형의 생일을 입력해주세요. 생년월일은 'YYYYMMDD'의 형식입니다");
            e.preventDefault();
            return;
        }
        const telVal = telField.value;
        if (!validateTel(telVal)) {
            alert("휴대폰 번호의 양식은 '01012345678' 입니다.");
            e.preventDefault();
            return;
        }
        const emailVal = emailField.value;
        if (!validateEmail(emailVal)) {
            alert("이메일을 다시 한번 확인해주세요");
            e.preventDefault();
            return;
        }
    });