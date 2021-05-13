function onStorageEvent(storageEvent) {

    alert("storage event");
}

//window.addEventListener('localStorage', onStorageEvent, false);

//$('.custom-switch-id-element').on('click', function (event) {
// localStorage.setItem('checked', $(event.target).attr('id'));
//alert($(event.target).attr('id'));
//alert(localStorage.length);

//})

function createButton() {
    localStorage.clear();
    var myButton = document.createElement("button");
    var myText = document.createTextNode("Some text");
    myButton.appendChild(myText);
    myButton.onclick = function () {
        //alert('Клик!');
    };
    document.body.appendChild(myButton);

    let navbarColor01 = document.querySelector('#navbarColor01');

    alert(localStorage.length);
    if (localStorage.length == 2) {
        navbarColor01.insertBefore(myButton, navbarColor01.firstElementChild);
    }
    var newDiv = document.createElement("div");
    newDiv.innerHTML = "<h1>Привет!</h1>";

    document.body.appendChild(newDiv);

    // Добавляем только что созданный элемент в дерево DOM

    var my_div = document.getElementById("navbarColor01");
    document.body.insertBefore(newDiv, my_div);
}

function check() {
    //alert(event.target.is);
    //var val = document.getElementById("bulba");
    //alert(val);

    let checkbox = document.getElementById(event.target.id);

    //alert(checkbox.id)
    //alert(checkbox.checked);

    var newButton = true;

    if (checkbox.checked) {
        if (localStorage.length == 2) {
            alert("Вы уже выбрали 2 покемона");
            checkbox.checked = false;
            newButton = false;
        } else {
            alert('Включен');
            localStorage.setItem(checkbox.id, 'checked');
        }

    } else {
        alert('Выключен');
        localStorage.removeItem(checkbox.id);
    }
    alert(localStorage.length);

    if (localStorage.length == 2 && newButton) {
        var myButton = document.createElement("button");
        var myText = document.createTextNode("Some text");
        myButton.appendChild(myText);
        var str = "document.location=" + "'/cmp?first=" +localStorage.key(0)+ "&second=" + localStorage.key(1) + "'";
        var str1 = "document.location=''";
        myButton.setAttribute("onClick", str);
        //myButton.onclick="document.location='/hello?page=10&count=20'";
       // myButton.al
        var navbarColor01 = document.querySelector('#navbarColor01');
        navbarColor01.insertBefore(myButton, navbarColor01.firstElementChild);
    }
}


function clearLocalStorage() {
    localStorage.clear();
}

//window.addEventListener('localStorage', function (e) {
 //   alert('Клик!');
//});

function newMain() {
    localStorage.clear();
}

