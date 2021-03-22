const div1000 = document.querySelector('#square1000');
const divImg = document.getElementById('i1');
const div1100 = document.querySelector('#square1100');

if (userIsAdmin){
    div1100.innerHTML = "Dodaj miejsce";
}

const divs = new Array(seatsAmount);

var seatFree = new Array(seatsAmount);
var seatNumber = new Array(seatsAmount);
var seatPosX = new Array(seatsAmount);
var seatPosY = new Array(seatsAmount);
var seatComment = new Array(seatsAmount);

//    var seatFreeX = seats[0].status;
//    console.log(seatFreeX);
for (let i =0; i< seatsAmount; i++) {
    seatFree[i] = seats[i].status;
    seatNumber[i] = seats[i].number;
    seatPosX[i] = seats[i].posX;
    seatPosY[i] = seats[i].posY;
    seatComment[i] = seats[i].comment;
}

for (let i = 0; i < seatsAmount; i++) {
    divs[i] = document.createElement('div');
    divs[i].style.width = 50 + "px";
    divs[i].style.height = 50 + "px";
    divs[i].style.cursor = "pointer";
    divs[i].style.position = "fixed";
    divs[i].style.backgroundColor = "gray";
    document.body.appendChild(divs[i]);
}

divImg.style.left = 100 + "px";
divImg.style.top = 100 + "px";

div1000.innerHTML = "Potwierdz";


let seatPermission = new Array(seatsAmount);
let mouseupPerm = new Array(seatsAmount);
let drawActive = new Array(seatsAmount);
let insertDivX = new Array(seatsAmount);
let insertDivY = new Array(seatsAmount);
let seatOwner = new Array(seatsAmount);

for (let i = 0; i < seatsAmount; i++){
    divs[i].style.left = `${seatPosX[i]}px`;
    divs[i].style.top = `${seatPosY[i]}px`;
    divs[i].innerHTML = seatNumber[i];
}

for (let i = 0; i < seatsAmount; i++) {
    seatPermission[i] = true;
    mouseupPerm[i] = false;
    drawActive[i] = false;
    insertDivX[i] = 0;
    insertDivY[i] = 0;
    seatOwner[i] = seatComment[i];
}

let mouseupPerm1000 = false;
let mouseupPerm1100 = false;

if (userIsAdmin != true) {
    for (let i = 0; i < seatsAmount; i++){
        if (userIsLogged != true || seatFree[i] != "f" && seatComment[i] != userName) {
            seatPermission [i] = false;
        }
    }
}

for (let i = 0; i < seatsAmount; i++){
    if (seatFree[i] == "f")
        divs[i].style.backgroundColor = "gray";
    else if (seatFree[i] == "h") {
        divs[i].style.color = "black";
        divs[i].style.backgroundColor = "yellow";
        divs[i].textContent = seatNumber[i] + " " + seatComment[i];
    }
    else if (seatFree[i] == "b") {
        divs[i].style.backgroundColor = "red";
        divs[i].textContent = seatNumber[i] + " " + seatComment[i];
    }
}

for (let i = 0; i < seatsAmount; i++) {
    divListeners(i);
}

function divListeners(i){
    divs[i].addEventListener('mousedown', (e) => {
        insertDivX[i] = e.offsetX;
    insertDivY[i] = e.offsetY;
    drawActive[i] = !drawActive[i];
    mouseupPerm[i] = true;
})

    divs[i].addEventListener('mouseout', (e) => {
        mouseupPerm[i] = false;
    drawActive[i] = false;
})

    divs[i].addEventListener('mousemove', (e) => {
        mouseupPerm[i] = false;

    if (userIsAdmin && drawActive[i]) {
        divX = e.clientX - insertDivX[i];
        divY = e.clientY - insertDivY[i];
        divs[i].style.left = `${divX}px`;
        divs[i].style.top = `${divY}px`;
        seatPosX[i] = divX;
        seatPosY[i] = divY;
    }
})

    divs[i].addEventListener('mouseup', (e) => {
        if (mouseupPerm[i] == true && seatPermission[i] == true)
    {
        if (seatFree[i] == "b") {
            divs[i].style.backgroundColor = "gray";
            seatFree[i] = "f";
            seatComment[i] = " ";
        }
        else if (seatFree[i] == "f") {
            divs[i].style.backgroundColor = "yellow";
            seatFree[i] = "h";

            if (userIsAdmin == false)
                seatOwner[i] = userName;

            seatComment[i] = seatOwner[i];
        }
        else if (seatFree[i] == "h") {
            if (userIsAdmin == true) {
                divs[i].style.backgroundColor = "red";
                seatFree[i] = "b";
                seatComment[i] = seatOwner[i];
            }
            else {
                divs[i].style.backgroundColor = "gray";
                seatFree[i] = "f";
                seatComment[i] = " ";
                seatOwner[i] = userName;
            }
        }
        divs[i].style.color = "black";
        divs[i].textContent = seatNumber[i] + " " + seatComment[i];
        mouseupPerm[i] = false;
    }
    drawActive[i] = !drawActive[i];
})
}

div1000.addEventListener('mouseout', (e) =>{
    mouseupPerm1000 = false;
})

div1000.addEventListener('mousedown', (e) => {
    mouseupPerm1000 = true;
})

if (userIsAdmin) {
    div1100.addEventListener('mouseout', (e) => {
        mouseupPerm1100 = false;
    })

    div1100.addEventListener('mousedown', (e) => {
        mouseupPerm1100 = true;
    })
}

div1000.addEventListener('mouseup', (e) => {
    if (mouseupPerm1000 == true)
    {
        let info = eventId + "/";
        info += seatsAmount + "/";

        for (let i = 0; i < seatsAmount; i++) {
            info += seatNumber[i] + "_" +  seatFree[i] + "_" + seatComment[i] + "_" + seatPosX[i] + "_" + seatPosY[i] + "_";
        }
        info += "_";

        searchViaAjax(info);
        mouseupPerm1000 = false;
        alert('Zapisano zmiany.');
    }
})

if (userIsAdmin){
    div1100.addEventListener('mouseup', (e) => {
        if (mouseupPerm1100 == true)
        {
            divs[seatsAmount] = document.createElement('div');
            divs[seatsAmount].style.width = 50 + "px";
            divs[seatsAmount].style.height = 50 + "px";
            divs[seatsAmount].style.cursor = "pointer";
            divs[seatsAmount].style.position = "fixed";
            divs[seatsAmount].style.backgroundColor = "gray";
            document.body.appendChild(divs[seatsAmount]);

            seatFree[seatsAmount] = "f";
            seatNumber[seatsAmount] = seatsAmount + 1;
            seatPosX[seatsAmount] = 200;
            seatPosY[seatsAmount] = 100;
            seatComment[seatsAmount] = " ";

            divs[seatsAmount].style.left = `${seatPosX[seatsAmount]}px`;
            divs[seatsAmount].style.top = `${seatPosY[seatsAmount]}px`;
            divs[seatsAmount].innerHTML = seatNumber[seatsAmount];

            seatPermission[seatsAmount] = true;
            mouseupPerm[seatsAmount] = false;
            drawActive[seatsAmount] = false;
            insertDivX[seatsAmount] = 0;
            insertDivY[seatsAmount] = 0;
            seatOwner[seatsAmount] = seatComment[seatsAmount];
            divListeners(seatsAmount);

            seatsAmount ++;
            mouseupPerm1100 = false;
        }
    })
}

function searchViaAjax(info) {
    console.log(info);

    $.ajax({
        type : "GET",
        url : "/events/event/edit/reserve/"+info,
        timeout : 100000
    });
}