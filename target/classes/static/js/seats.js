const div = document.querySelector('#square1');
const div2 = document.querySelector('#square2');
const div3 = document.querySelector('#square3');
const div4 = document.querySelector('#square4');
const div100 = document.querySelector('#square100');
const divImg = document.getElementById('i1');

let divs = [div, div2, div3, div4];

divImg.style.left = 100 + "px";
divImg.style.top = 100 + "px";

div100.innerHTML = "Potwierdz";

let seat1free = seatStatus1;
let seat2free = seatStatus2;
let seat3free = seatStatus3;
let seat4free = seatStatus4;

// div.style.left = `${seatPosX1}px`;
// div2.style.left = `${seatPosX2}px`;
// div3.style.left = `${seatPosX3}px`;
// div4.style.left = `${seatPosX4}px`;
//
// div.style.top = `${seatPosY1}px`;
// div2.style.top = `${seatPosY2}px`;
// div3.style.top = `${seatPosY3}px`;
// div4.style.top = `${seatPosY4}px`;
//
// div.innerHTML = seatNumber1;
// div2.innerHTML = seatNumber2;
// div3.innerHTML = seatNumber3;
// div4.innerHTML = seatNumber4;

for (let i = 0; i < divs.length; i++){
    divs[i].style.left = `${seatPosX[i]}px`;
    divs[i].style.top = `${seatPosY[i]}px`;
    divs[i].innerHTML = seatNumber[i];
}

let seat1permission = true;
let seat2permission = true;
let seat3permission = true;
let seat4permission = true;

let mouseupPerm1 = false;
let mouseupPerm2 = false;
let mouseupPerm3 = false;
let mouseupPerm4 = false;
let mouseupPerm100 = false;

let drawActive1 = false;
let drawActive2 = false;
let drawActive3 = false;
let drawActive4 = false;

let insertDivX1;
let insertDivY1;
let insertDivX2;
let insertDivY2;
let insertDivX3;
let insertDivY3;
let insertDivX4;
let insertDivY4;

let seatOwner1 = seatComment1;
let seatOwner2 = seatComment2;
let seatOwner3 = seatComment3;
let seatOwner4 = seatComment4;

let resAmount = 2;


if (userIsAdmin != true){
    if (userIsLogged != true || seat1free != "f" && seatComment1 != userName)
        seat1permission = false;
    if (userIsLogged != true || seat2free != "f" && seatComment2 != userName)
        seat2permission = false;
    if (userIsLogged != true || seat3free != "f" && seatComment3 != userName)
        seat3permission = false;
    if (userIsLogged != true || seat4free != "f" && seatComment4 != userName)
        seat4permission = false;
}

if (seat1free == "f")
    div.style.backgroundColor = "gray";
else if (seat1free == "h") {
    div.style.color = "black";
    div.style.backgroundColor = "yellow";
    div.textContent = seatNumber1 + " " + seatComment1;
}
else if (seat1free == "b") {
    div.style.backgroundColor = "red";
    div.textContent = seatNumber1 + " " + seatComment1;
}

if (seat2free == "f")
    div2.style.backgroundColor = "gray";
else if (seat2free == "h") {
    div2.style.color = "black";
    div2.style.backgroundColor = "yellow";
    div2.textContent = seatNumber1 + " " + seatComment2;
}
else if (seat2free == "b") {
    div2.style.backgroundColor = "red";
    div2.textContent = seatNumber2 + " " + seatComment2;
}

if (seat3free == "f")
    div3.style.backgroundColor = "gray";
else if (seat3free == "h") {
    div3.style.color = "black";
    div3.style.backgroundColor = "yellow";
    div3.textContent = seatNumber3 + " " + seatComment3;
}
else if (seat3free == "b") {
    div3.style.backgroundColor = "red";
    div3.textContent = seatNumber3 + " " + seatComment3;
}

if (seat4free == "f")
    div4.style.backgroundColor = "gray";
else if (seat4free == "h") {
    div4.style.color = "black";
    div4.style.backgroundColor = "yellow";
    div4.textContent = seatNumber4 + " " + seatComment4;
}
else if (seat4free == "b") {
    div4.style.backgroundColor = "red";
    div4.textContent = seatNumber4 + " " + seatComment4;
}

div.addEventListener('mousedown', (e) => {
    insertDivX1 = e.offsetX;
    insertDivY1 = e.offsetY;
    drawActive1 = !drawActive1;
    mouseupPerm1 = true;
})

div.addEventListener('mouseout', (e) =>{
    mouseupPerm1 = false;
    drawActive1 = false;
})

div.addEventListener('mousemove', (e) => {
    mouseupPerm1 = false;

    if (userIsAdmin && drawActive1) {
        divX = e.clientX - insertDivX1;
        divY = e.clientY - insertDivY1;
        div.style.left = `${divX}px`;
        div.style.top = `${divY}px`;
        seatPosX1 = divX;
        seatPosY1 = divY;
    }
})

div2.addEventListener('mousedown', (e) => {
    insertDivX2 = e.offsetX;
    insertDivY2 = e.offsetY;
    drawActive2 = !drawActive2;
    mouseupPerm2 = true;
})

div2.addEventListener('mouseout', (e) =>{
    mouseupPerm2 = false;
    drawActive2 = false;
})

div2.addEventListener('mousemove', (e) => {
    mouseupPerm2 = false;

    if (userIsAdmin && drawActive2) {
        divX = e.clientX - insertDivX2;
        divY = e.clientY - insertDivY2;
        div2.style.left = `${divX}px`;
        div2.style.top = `${divY}px`;
        seatPosX2 = divX;
        seatPosY2 = divY;
    }
})

div3.addEventListener('mousedown', (e) => {
    insertDivX3 = e.offsetX;
    insertDivY3 = e.offsetY;
    drawActive3 = !drawActive3;
    mouseupPerm3 = true;
})

div3.addEventListener('mouseout', (e) =>{
    mouseupPerm3 = false;
    drawActive3 = false;
})

div3.addEventListener('mousemove', (e) => {
    mouseupPerm3 = false;

    if (userIsAdmin && drawActive3) {
        divX = e.clientX - insertDivX3;
        divY = e.clientY - insertDivY3;
        div3.style.left = `${divX}px`;
        div3.style.top = `${divY}px`;
        seatPosX3 = divX;
        seatPosY3 = divY;
    }
})

div4.addEventListener('mousedown', (e) => {
    insertDivX4 = e.offsetX;
    insertDivY4 = e.offsetY;
    drawActive4 = !drawActive4;
    mouseupPerm4 = true;
})

div4.addEventListener('mouseout', (e) =>{
    mouseupPerm4 = false;
    drawActive4 = false;
})

div4.addEventListener('mousemove', (e) => {
    mouseupPerm4 = false;

    if (userIsAdmin && drawActive4) {
        divX = e.clientX - insertDivX4;
        divY = e.clientY - insertDivY4;
        div4.style.left = `${divX}px`;
        div4.style.top = `${divY}px`;
        seatPosX4 = divX;
        seatPosY4 = divY;
    }
})

div.addEventListener('mouseup', (e) => {
    if (mouseupPerm1 == true && seat1permission == true)
    {
        if (seat1free == "b") {
            div.style.backgroundColor = "gray";
            seat1free = "f";
            seatComment1 = " ";
        }
        else if (seat1free == "f") {
            div.style.backgroundColor = "yellow";
            seat1free = "h";

            if (userIsAdmin == false)
                seatOwner1 = userName;

            seatComment1 = seatOwner1;
        }
        else if (seat1free == "h") {
            if (userIsAdmin == true) {
                div.style.backgroundColor = "red";
                seat1free = "b";
                seatComment1 = seatOwner1;
            }
            else {
                div.style.backgroundColor = "gray";
                seat1free = "f";
                seatComment1 = " ";
                seatOwner1 = userName;
            }
        }
        div.style.color = "black";
        div.textContent = seatNumber1 + " " + seatComment1;
        mouseupPerm1 = false;
    }
    drawActive1 = !drawActive1;
})

div2.addEventListener('mouseup', (e) => {
    if (mouseupPerm2 == true && seat2permission == true)
    {
        if (seat2free == "b") {
            div2.style.backgroundColor = "gray";
            seat2free = "f";
            seatComment2 = " ";
        }
        else if (seat2free == "f") {
            div2.style.backgroundColor = "yellow";
            seat2free = "h";

            if (userIsAdmin == false)
                seatOwner2 = userName;

            seatComment2 = seatOwner2;
        }
        else if (seat2free == "h") {
            if (userIsAdmin == true) {
                div2.style.backgroundColor = "red";
                seat2free = "b";
                seatComment2 = seatOwner2;
            }
            else {
                div2.style.backgroundColor = "gray";
                seat2free = "f";
                seatComment2 = " ";
                seatOwner2 = userName;
            }
        }
        div2.style.color = "black";
        div2.textContent = seatNumber2 + " " + seatComment2;
        mouseupPerm2 = false;
    }
    drawActive2 = !drawActive2;
})

div3.addEventListener('mouseup', (e) => {
    if (mouseupPerm3 == true && seat3permission == true)
    {
        if (seat3free == "b") {
            div3.style.backgroundColor = "gray";
            seat3free = "f";
            seatComment3 = " ";
        }
        else if (seat3free == "f") {
            div3.style.backgroundColor = "yellow";
            seat3free = "h";

            if (userIsAdmin == false)
                seatOwner3 = userName;

            seatComment3 = seatOwner3;
        }
        else if (seat3free == "h") {
            if (userIsAdmin == true) {
                div3.style.backgroundColor = "red";
                seat3free = "b";
                seatComment3 = seatOwner3;
            }
            else {
                div3.style.backgroundColor = "gray";
                seat3free = "f";
                seatComment3 = " ";
                seatOwner3 = userName;
            }
        }
        div3.style.color = "black";
        div3.textContent = seatNumber3 + " " + seatComment3;
        mouseupPerm3 = false;
    }
    drawActive3 = !drawActive3;
})

div4.addEventListener('mouseup', (e) => {
    if (mouseupPerm4 == true && seat4permission == true)
    {
        if (seat4free == "b") {
            div4.style.backgroundColor = "gray";
            seat4free = "f";
            seatComment4 = " ";
        }
        else if (seat4free == "f") {
            div4.style.backgroundColor = "yellow";
            seat4free = "h";

            if (userIsAdmin == false)
                seatOwner4 = userName;

            seatComment4 = seatOwner4;
        }
        else if (seat4free == "h") {
            if (userIsAdmin == true) {
                div4.style.backgroundColor = "red";
                seat4free = "b";
                seatComment4 = seatOwner4;
            }
            else {
                div4.style.backgroundColor = "gray";
                seat4free = "f";
                seatComment4 = " ";
                seatOwner4 = userName;
            }
        }
        div4.style.color = "black";
        div4.textContent = seatNumber4 + " " + seatComment4;
        mouseupPerm4 = false;
    }
    drawActive4 = !drawActive4;
})




div100.addEventListener('mouseout', (e) =>{
    mouseupPerm100 = false;
})

div100.addEventListener('mousedown', (e) => {
    mouseupPerm100 = true;
})

div100.addEventListener('mouseup', (e) => {
    if (mouseupPerm100 == true)
    {
        let info =
            eventId + "/" +
            seat1free + "/" + seatComment1 + "/" + seatPosX1 + "/" + seatPosY1 + "/" +
            seat2free + "/" + seatComment2 + "/" + seatPosX2 + "/" + seatPosY2 + "/" +
            seat3free + "/" + seatComment3 + "/" + seatPosX3 + "/" + seatPosY3 + "/" +
            seat4free + "/" + seatComment4 + "/" + seatPosX4 + "/" + seatPosY4;

        searchViaAjax(info);
        mouseupPerm100 = false;
        alert('Zapisano zmiany.');
    }
})

function searchViaAjax(info) {
    console.log(info);

    $.ajax({
        type : "GET",
        url : "/events/event/edit/reserve/"+info,
        timeout : 100000
    });
}