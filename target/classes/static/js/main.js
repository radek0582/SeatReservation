const square = document.createElement('div');
document.body.appendChild(square);

size = 200;
grow = true;
square.style.width = size + "px";
square.style.height = size + "px";

// number = '[[${msg}]]';
// alert(number);

console.log(clientslist.forEach(element => console.log(element)));

window.addEventListener("scroll", function () {
    console.log(number);

    if (size >= window.innerWidth / 2) {
        grow = !grow;
        square.style.backgroundColor("green");
    }
    else if (size <= 0) {
        grow = !grow;
        square.style.backgroundColor("red");
    }

    if (grow) {
        size += 5;
    }
    else {
        size -= 5;
    }

    if (size > 250 && size < 300) {
        searchViaAjax(size);
    }

    square.style.width = size + "px";
    square.style.height = size + "px";
})

function searchViaAjax(id) {
    $.ajax({
        type : "GET",
        url : "/users/search/api/getSearchResult/"+id,
        timeout : 100000
    });
}

const table = document.getElementById("mytab1");

// const r = t.getElementsByTagName("tr");
// const value = table.rows.length;
// console.log(value);

