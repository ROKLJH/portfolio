    /**********************************
     * 기본 유효성 검사 함수 (비밀번호, 이름, 생년월일, 휴대전화, 이메일)
     **********************************/
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
        if (birth < "18991231") {
            return {
                valid: false,
                message: "정말 그때 태어나셨나요?"
            };
        }
        if (birth > "20250101") {
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

    /*******************************
     * 비밀번호 확인 처리
     *******************************/
    const modPwdField = document.getElementById('modPwd');
    const modPwd2Field = document.getElementById('modPwd2');
    const modPwdMatchMessage = document.getElementById('modPwdMatchMessage');

    function checkModPwdMatch() {
        const pwdVal = modPwdField.value;
        const pwd2Val = modPwd2Field.value;
        if (pwdVal === "" || pwd2Val === "") {
            modPwdMatchMessage.textContent = "";
            return;
        }
        if (pwdVal === pwd2Val) {
            modPwdMatchMessage.textContent = "비밀번호가 일치합니다.";
            modPwdMatchMessage.className = "message blue";
        } else {
            modPwdMatchMessage.textContent = "비밀번호가 일치하지 않습니다.";
            modPwdMatchMessage.className = "message red";
        }
    }
    modPwdField.addEventListener('input', checkModPwdMatch);
    modPwd2Field.addEventListener('input', checkModPwdMatch);

    /*******************************
     * 이름, 생년월일, 휴대전화, 이메일 유효성 처리
     *******************************/
    const modNameField = document.getElementById('modName');
    const modNameValidationMessage = document.getElementById('modNameValidationMessage');
    modNameField.addEventListener('input', function() {
        const nameVal = modNameField.value;
        if (!validateName(nameVal)) {
            modNameValidationMessage.textContent = "이름을 바르게 입력해주세요. (영어, 한글만 허용)";
            modNameValidationMessage.className = "message red";
        } else {
            modNameValidationMessage.textContent = "이름 형식이 올바릅니다.";
            modNameValidationMessage.className = "message blue";
        }
    });
    const modBirthField = document.getElementById('modBirth');
    const modBirthValidationMessage = document.getElementById('modBirthValidationMessage');
    modBirthField.addEventListener('blur', function() {
        const birthVal = modBirthField.value;
        const result = validateBirth(birthVal);
        if (!result.valid) {
            modBirthValidationMessage.textContent = result.message;
            modBirthValidationMessage.className = "message red";
        } else {
            modBirthValidationMessage.textContent = result.message;
            modBirthValidationMessage.className = "message blue";
        }
    });
    const modTelField = document.getElementById('modTel');
    const modTelValidationMessage = document.getElementById('modTelValidationMessage');
    modTelField.addEventListener('blur', function() {
        const telVal = modTelField.value;
        if (!validateTel(telVal)) {
            modTelValidationMessage.textContent = "휴대폰 번호의 양식은 '01012345678' 입니다.";
            modTelValidationMessage.className = "message red";
        } else {
            modTelValidationMessage.textContent = "휴대전화 형식이 올바릅니다.";
            modTelValidationMessage.className = "message blue";
        }
    });
    const modEmailField = document.getElementById('modEmail');
    const modEmailValidationMessage = document.getElementById('modEmailValidationMessage');
    modEmailField.addEventListener('blur', function() {
        const emailVal = modEmailField.value;
        if (!validateEmail(emailVal)) {
            modEmailValidationMessage.textContent = "이메일을 다시 한번 확인해주세요";
            modEmailValidationMessage.className = "message red";
        } else {
            modEmailValidationMessage.textContent = "이메일 형식이 올바릅니다.";
            modEmailValidationMessage.className = "message blue";
        }
    });

    /*******************************
     * 폼 제출 전 전체 검증 (회원정보 수정)
     *******************************/
    document.getElementById('modifyForm').addEventListener('submit', function(e) {
        const pwdVal = modPwdField.value;
        const pwd2Val = modPwd2Field.value;
        if (!validatePwdFormat(pwdVal) || pwdVal !== pwd2Val) {
            alert("비밀번호를 다시 확인해주세요");
            e.preventDefault();
            return;
        }
        const nameVal = modNameField.value;
        if (!validateName(nameVal)) {
            alert("이름을 바르게 입력해주세요.");
            e.preventDefault();
            return;
        }
        const birthVal = modBirthField.value;
        if (!validateBirth(birthVal).valid) {
            alert("올바른 유형의 생일을 입력해주세요. 생년월일은 'YYYYMMDD'의 형식입니다");
            e.preventDefault();
            return;
        }
        const telVal = modTelField.value;
        if (!validateTel(telVal)) {
            alert("휴대폰 번호의 양식은 '01012345678' 입니다.");
            e.preventDefault();
            return;
        }
        const emailVal = modEmailField.value;
        if (!validateEmail(emailVal)) {
            alert("이메일을 다시 한번 확인해주세요");
            e.preventDefault();
            return;
        }
    });