var COUNT_RECTANGLES = 20;
var count_removed = 0;
var hasStart = false;
var rectangles = [];
var removed = {};
var fin = false;

function init() {
    var height = window.innerHeight - 50;
    var width = window.innerWidth - 300;

    var timer_div = document.getElementById("timer");
    timer_div.style.top = "8px";
    timer_div.style.left = (width + 13) + "px";

    var help = document.getElementById("help");
    help.style.top = 8 + 170 + "px";
    help.style.left = (width + 13) + "px";

    var box = document.getElementById("box");
    box.style.height = height + "px";
    box.style.width = width + "px";
    box.style.border = "3px solid #00FF00";

    for (var num = 0; num < COUNT_RECTANGLES; num++) {
        var newRectangleDiv = document.createElement("div");

        var color = '#' + Math.round(0xffffff * Math.random()).toString(16);
        console.log(color);
        var widthRectangle = Math.round(300 * Math.random()) + 21;
        var heightRectangle = Math.round(150 * Math.random()) + 21;
        var topIndent = Math.round((height - heightRectangle - 13) * Math.random()) + 13;
        var leftIndent = Math.round((width - widthRectangle - 13) * Math.random()) + 13;

        var rectangle = {};
        rectangle['x1'] = leftIndent;
        rectangle['y1'] = topIndent;
        rectangle['x2'] = leftIndent + widthRectangle;
        rectangle['y2'] = topIndent + heightRectangle;
        rectangles.push(rectangle);
        removed[num] = false;

        newRectangleDiv.id = num;
        newRectangleDiv.style.background = color;
        newRectangleDiv.style.width = widthRectangle + "px";
        newRectangleDiv.style.height = heightRectangle + "px";
        newRectangleDiv.style.top = topIndent + "px";
        newRectangleDiv.style.left = leftIndent + "px";
        newRectangleDiv.style.position = "absolute";

        box.appendChild(newRectangleDiv);

        $("#" + num).on("click", function() {
            if (!hasStart) {
                timer_start();
                hasStart = true;
            }
            var id = $(this).attr('id');
            $('#' + id).remove();
            removed[id] = true;
            count_removed += 1;
            if (!checkIntersection()) {
                fin = true;
                alert("Time: " + seconds + "." + milisec + " sec\n" + count_removed + " rectangles removed\n");
            }
        });
    }
}

function checkIntersection() {
    // BUG IS HERE
    // and may be no
    for (var i = 0; i < COUNT_RECTANGLES; i++)
        if (!removed[i])
            for (var j = 0; j < COUNT_RECTANGLES; j++)
                if (!removed[j] && i != j) {
                    if (Math.min(rectangles[i]['x2'], rectangles[j]['x2']) > Math.max(rectangles[i]['x1'], rectangles[j]['x1']) &&
                        Math.min(rectangles[i]['y2'], rectangles[j]['y2']) > Math.max(rectangles[i]['y1'], rectangles[j]['y1'])) {
                        return true;
                    }
                }
    return false;
}

var milisec = 0;
var seconds = 0;

function display() {
    if (fin)
        return;
    if (milisec >= 9){
        milisec = 0;
        seconds += 1;
    } else
        milisec += 1;
    document.getElementById('timer').innerHTML = seconds + "." + milisec;
    setTimeout("display()", 100);
}

function timer_start() {
    milisec = 0;
    seconds = 0;
    display();
}

$(document).ready(function() {
    $("#restart").on("click", function() {
        location.reload();
    });
});
