console.log("scriptFile loaded");

function over(arg){
    arg.src = "./images/color.jpeg";
    arg.width = arg.width * 1.1;
    console.log("이미지에 커서 올려놓음");
}

function out(arg){
    arg.src = "./images/grayscale.jpeg";
    arg.width = arg.width * 10 / 11;
    console.log("현재 이미지 크기 : " + arg.width);
}

function clck(arg){
    arg.width = "1000";
}

function dblclck(arg){
    arg.width = "600";
}