document.addEventListener("DOMContentLoaded", function () {
    console.log("JavaScript 로드 완료 ✅");

    // 💰 총 지출 계산 함수
    function calculateTotalSpent() {
        let total = 0;

        // 모든 amount 입력 필드 가져오기
        let amountInputs = document.querySelectorAll("input[name='amount']");
        console.log("총 입력 필드 개수:", amountInputs.length);

        amountInputs.forEach(function (input) {
            let value = parseInt(input.value) || 0; // 숫자로 변환 (빈 값은 0 처리)
            console.log("입력 값:", value);
            total += value;
        });

        console.log("총 지출 계산 완료:", total);

        // 합산된 금액 업데이트
        document.getElementById("total-spent").textContent = `₩${total.toLocaleString()}`;
    }

    // 🛠 페이지 로드 시 총 지출 계산
    calculateTotalSpent();

    // 🖊 입력값 변경될 때마다 실시간 업데이트
    document.querySelectorAll("input[name='amount']").forEach(function (input) {
        input.addEventListener("input", calculateTotalSpent);
    });

    document.addEventListener("click", function (event) {
        if (event.target.classList.contains("delete-btn")) {
            const row = event.target.closest("tr"); // 해당 버튼이 속한 <tr> 찾기
            if (row) {
                row.remove(); // <tr> 삭제
            }
        }
    });

    function deleteRow(button) {
        const row = button.closest("tr"); // 버튼이 속한 <tr> 찾기
        if (row) {
            row.remove(); // <tr> 삭제
        }
    }

});
