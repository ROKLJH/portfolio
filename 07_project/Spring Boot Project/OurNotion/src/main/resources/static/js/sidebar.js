// sidebar.js - 수정된 버전 (null 체크 포함)
var leftVisibleBtn = document.querySelector("input[name='leftVisible']");
if (leftVisibleBtn) {
    leftVisibleBtn.style.display = "none";
}
var rightVisibleBtn = document.querySelector("input[name='rightVisible']");
if (rightVisibleBtn) {
    rightVisibleBtn.style.display = "none";
}
var rightSidebarCloseBtn = document.querySelector("input[name='rightSidebarClose']");
if (rightSidebarCloseBtn) {
    rightSidebarCloseBtn.style.display = "inline-block";
}
var leftSidebarCloseBtn = document.querySelector("input[name='leftSidebarClose']");
if (leftSidebarCloseBtn) {
    leftSidebarCloseBtn.style.display = "inline-block";
}

// 왼쪽 사이드바 닫기 함수
function leftSideBarClose() {
    var leftSidebar = document.querySelector('.leftSideBarOpen');
    var mainContents = document.querySelector('.mainContents');
    if (leftSidebar) leftSidebar.style.width = '0';
    if (mainContents) {
        mainContents.style.marginLeft = '0';
        mainContents.style.width = '100%';
        mainContents.style.paddingLeft = '200px';
    }
    if (leftVisibleBtn) leftVisibleBtn.style.display = "inline-block";
    if (leftSidebarCloseBtn) leftSidebarCloseBtn.style.display = "none";
    // 기타 요소 숨김
    var userProfile = document.querySelector('.userProfile');
    var mainMenu = document.querySelector('.mainMenu');
    var userMenu = document.querySelector('.userMenu');
    var userLogOut = document.querySelector('.userLogOut');
    if (userProfile) userProfile.style.display = "none";
    if (mainMenu) mainMenu.style.display = "none";
    if (userMenu) userMenu.style.display = "none";
    if (userLogOut) userLogOut.style.display = "none";
}

// 왼쪽 사이드바 열기 함수
function leftSideBarOpen() {
    var leftSidebar = document.querySelector('.leftSideBarOpen');
    var mainContents = document.querySelector('.mainContents');
    if (leftSidebar) leftSidebar.style.width = '200px';
    if (mainContents) {
        mainContents.style.marginLeft = '0px';
        mainContents.style.paddingLeft = '200px';
    }
    if (leftVisibleBtn) leftVisibleBtn.style.display = "none";
    if (leftSidebarCloseBtn) leftSidebarCloseBtn.style.display = "inline-block";
    // 기타 요소 보이기
    var userProfile = document.querySelector('.userProfile');
    var mainMenu = document.querySelector('.mainMenu');
    var userMenu = document.querySelector('.userMenu');
    var userLogOut = document.querySelector('.userLogOut');
    if (userProfile) userProfile.style.display = "block";
    if (mainMenu) mainMenu.style.display = "block";
    if (userMenu) userMenu.style.display = "block";
    if (userLogOut) userLogOut.style.display = "block";
}

// 오른쪽 사이드바 닫기 함수
function rightSideBarClose() {
    var rightSidebar = document.querySelector('.rightSideBarOpen');
    var mainContents = document.querySelector('.mainContents');
    if (rightSidebar) rightSidebar.style.width = '0';
    if (mainContents) {
        mainContents.style.marginRight = '0';
        mainContents.style.width = '100%';
        mainContents.style.paddingRight = '200px';
    }
    if (rightVisibleBtn) rightVisibleBtn.style.display = "inline-block";
    if (document.querySelector("input[name='rightSidebarClose']"))
        document.querySelector("input[name='rightSidebarClose']").style.display = "none";
}

// 오른쪽 사이드바 열기 함수
function rightSideBarOpen() {
    var rightSidebar = document.querySelector('.rightSideBarOpen');
    var mainContents = document.querySelector('.mainContents');
    if (rightSidebar) rightSidebar.style.width = '200px';
    if (mainContents) {
        mainContents.style.marginRight = '100px';
        mainContents.style.paddingRight = '200px';
    }
    if (rightVisibleBtn) rightVisibleBtn.style.display = "none";
    if (document.querySelector("input[name='rightSidebarClose']"))
        document.querySelector("input[name='rightSidebarClose']").style.display = "inline-block";
}