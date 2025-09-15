  // 게시글 수정 폼 제출 시 제목/내용 검증
  $("#boardModifyForm").on("submit", function(e) {
    let title = $("#modBoardTitle").val().trim();
    let content = $("#modBoardContent").val().trim();
    if (title === "") {
        alert("제목을 입력하세요");
        e.preventDefault();
        return;
    }
    if (content === "") {
        alert("내용을 입력해주세요");
        e.preventDefault();
        return;
    }
});