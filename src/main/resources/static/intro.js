var arrayOfElements=document.getElementsByClassName('nav-item');
var lengthOfArray=arrayOfElements.length;

for (var i=0; i<lengthOfArray;i++){
    arrayOfElements[i].style.display='none';
}

document.getElementById('howto').style.display='block';
document.getElementById('logout').style.display='block';
