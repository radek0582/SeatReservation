const square = document.createElement('div');
document.body.appendChild(square);

let size = 100;
let grow = true;
square.style.width = size + "px";
square.style.height = size + "px";



window.addEventListener("scroll", function () {
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

    square.style.width = size + "px";
    square.style.height = size + "px";
})

const tableContinent = document.body.querySelector('tr1');
tableContinent.style.backgroundColor("blue");