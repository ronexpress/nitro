const url = 'http://localhost:8080';

function startGame() {
    let player1 = document.getElementById("player1").value;
    let player1type = document.getElementById("player1type").value;
    let player2 = document.getElementById("player2").value;
    let player2type = document.getElementById("player2type").value;

    $("#message").text("");

    if (player1 == null || player1 === '' || player2 == null || player2 === '') {
        alert("Please enter name");
    } else {
        $.ajax({
            url: url + "/game/start",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(
            [
                {
                    "name": player1,
                    "type": player1type
                },
                {
                    "name": player2,
                    "type": player2type
                }
            ]
            ),
            success: function (data) {
                playerType = 'X';
                document.getElementById("pl0").style.backgroundColor = "lightblue";
                displayResponse(data);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}


function ready() {
    $.ajax({
        url: url + "/game/status",
        type: 'GET',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify(),
        success: function (data) {
            if(data.ready && !data.fullBoard){
                return true;
            }
        },
        error: function (error) {
            console.log(error);
            return false;
        }
    })
}


function move(place) {
    if(ready){
        let x = place / 10;
        let y = place % 10;
        $.ajax({
            url: url + "/game/move",
            type: 'POST',
            dataType: "json",
            contentType: "application/json",
            data: JSON.stringify(
                [x,y]
            ),
            success: function (data) {
                displayResponse(data);
            },
            error: function (error) {
                console.log(error);
            }
        })
    }
}



