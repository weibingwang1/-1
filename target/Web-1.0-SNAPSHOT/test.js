/*编写代码后注意在主文件的HTML的head中引入*/
/*
const arr=[0,2,4,2,5,3];
for (let i = 0; i <arr.length ; i++) {
    for (let j = 0; j <arr.length-1-i ; j++) {
        if(arr[j]>arr[j+1]){
            const temp=arr[j];
            arr[j]=arr[j+1];
            arr[j+1]=temp;
        }
    }
}
window.alert(arr);*/
/*
function f(){
    window.alert("卖瓜！")
}
*/
function  f(e){/*判断密码框中的密码是否少于六位*/
    if(e.value.length>6){
        e.removeAttribute("class")/*密码大于六位，不操作*/
    }else{
        e.setAttribute("class","illegal-psw")/*密码少于六位，调用css中的样式*/
    }
}

function http(){
    let xhr=new XMLHttpRequest();
    xhr.open('GET','https://www.baidu.com');
    xhr.send();
}