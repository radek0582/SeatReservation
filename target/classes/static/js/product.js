const div = document.querySelector('#square1');
let divX = 750;
let divY = 100;
div.style.left = divX + "px";
div.style.top = `${divY}px`;

let drawActive = false;
let mouseOut = false;

let insertDivX;
let insertDivY;

div.addEventListener('mouseout', (e) =>{
    console.log('mouseout');
})

div.addEventListener('mousedown', (e) => {
    div.style.backgroundColor = "gray";
// drawActive = !drawActive;

if (mouseOut == false)
    drawActive = true;

insertDivX = e.offsetX;
insertDivY = e.offsetY;
console.log(insertDivX, insertDivY);

})


div.addEventListener('mousemove', (e) => {

    if (drawActive) {
        divX = e.clientX - insertDivX;
        divY = e.clientY - insertDivY;
        div.style.left = `${divX}px`;
        div.style.top = `${divY}px`;
        // drawActive = false;

    }

})

div.addEventListener('mouseup', () => {
    div.style.backgroundColor = "green";
// drawActive = !drawActive;
    drawActive = false;
})