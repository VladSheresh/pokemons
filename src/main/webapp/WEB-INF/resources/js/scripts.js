function onStorageEvent(storageEvent) {

    alert("storage event");
}

function check() {
    var checkbox = document.getElementById(event.target.id);

    if (checkbox.checked) {
        if (localStorage.length == 2) {
            alert("Вы уже выбрали 2 покемона");
            checkbox.checked = false;
        } else {
            localStorage.setItem(checkbox.id, 'checked');
        }

    } else {
        localStorage.removeItem(checkbox.id);
        document.getElementById("cmp").style.backgroundColor = 'rgba(0, 0, 0, 0)';
        document.getElementById("cmp").removeAttribute("href");
        document.getElementById("cmp").onclick= test;
    }
    alert(localStorage.length);

    if (localStorage.length == 2) {
        document.getElementById("cmp").href = "/cmp?first=" +localStorage.key(0)+ "&second=" + localStorage.key(1);
        document.getElementById("cmp").onclick = clearLocalStorage;
        document.getElementById("cmp").style.backgroundColor = "#17a2b8";
       // var navbarColor01 = document.querySelector('#navbarColor01');
       // navbarColor01.insertBefore(myLink, navbarColor01.firstElementChild);
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

$(function() {
    $('#idPokemon').on('input',function() {
        element = document.getElementById("searchPokemon");
        var str = "document.location=" + "'/hello?idPokemon=" + $('#idPokemon').val() + "'";
        element.setAttribute("onClick", str);

    });
});

$('input.custom-switch-id-element').each(function( index ) {
    if(localStorage.key(0) == this.id || localStorage.key(1) == this.id)
    {
        this.checked=true;
    }
    if(localStorage.length == 2){
        document.getElementById("cmp").style.backgroundColor = "#17a2b8";
    }
})

function test() {
    //document.getElementById(localStorage.key(0)).checked = false;
    //document.getElementById(localStorage.key(1)).checked = false;
    //localStorage.clear();
    alert('select 2 pokemon');
}
