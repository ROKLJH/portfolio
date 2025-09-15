  // 파일 업로드 처리
  $("input[type='file']").change(function(e) {
    let inputFile = $("input[name='uploadFile']");
    let files = inputFile[0].files;
    let formData = new FormData();
    for (let i = 0; i < files.length; i++) {
        if (checkFile(files[i].name, files[i].size) === false) {
            alert("파일 업로드 실패");
            return;
        }
        formData.append("uploadFile", files[i]);
    }
    $.ajax({
        url: "/upload/uploadAjaxAction",
        processData: false,
        contentType: false,
        data: formData,
        type: "POST",
        success: function(result) {
            console.log("파일 업로드 완료");
            inputFile.val("");
            showUploadFile(result);
        },
        error: function(error) {
            console.error("파일 업로드 오류:", error);
        }
    });
});

function checkFile(fileName, fileSize) {
    const MAXSIZE = 5000000; // 5MB
    if (fileSize > MAXSIZE) {
        alert("파일 사이즈가 너무 큽니다.");
        return false;
    }
    const RULE = new RegExp("(.*?)\\.(exe|sh|zip|alz)$");
    if (RULE.test(fileName)) {
        alert("해당 파일은 업로드할 수 없습니다.");
        return false;
    }
    return true;
}

function showUploadFile(fileArray) {
    let uploadResult = $(".uploadResult ul");
    let str = "";
    $(fileArray).each(function(i, obj) {
        str += "<li data-path='" + obj.uploadPath + "' ";
        str += "data-uuid='" + obj.uuid + "' ";
        str += "data-filename='" + obj.fileName + "' ";
        str += "data-type='" + obj.image + "'>";
        if (obj.image) str += "<img src='/img/image.png' width='16px'> ";
        else str += "<img src='/img/attach.png' width='16px'> ";
        str += obj.fileName.substring(obj.fileName.indexOf("_") + 1);
        str += " <img src='/img/cancle.png' width='16px' class='cancel-icon' onclick='removeFile(this)' />";
        str += "</li>";
    });
    uploadResult.append(str);
}

function removeFile(cancelImg) {
    $(cancelImg).closest("li").remove();
}

$("input[type='submit']").on("click", function(e) {
    e.preventDefault();
    let form = $("#BoardRegisterForm");
    let str = "";
    $(".uploadResult ul li").each(function(i, obj) {
        let fileName = $(obj).data("filename");
        let uuid = $(obj).data("uuid");
        let uploadPath = $(obj).data("path");
        let image = $(obj).data("type");
        str += "<input type='hidden' name='attachFile[" + i + "].fileName' value='" + fileName + "'> ";
        str += "<input type='hidden' name='attachFile[" + i + "].uuid' value='" + uuid + "'> ";
        str += "<input type='hidden' name='attachFile[" + i + "].uploadPath' value='" + uploadPath + "'> ";
        str += "<input type='hidden' name='attachFile[" + i + "].image' value='" + image + "'> ";
    });
    form.append(str);

    let title = $("#boardTitle").val().trim();
    let content = $("#boardContent").val().trim();
    if (title === "") {
        alert("제목을 입력하세요");
        return;
    }
    if (content === "") {
        alert("내용을 입력해주세요");
        return;
    }
    form.submit();
});