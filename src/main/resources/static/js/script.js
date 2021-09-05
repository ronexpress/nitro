
function makeAMove(type, xCoordinate, yCoordinate) {
    $.ajax({
        url: url + "/game/gameplay",
        type: 'POST',
        dataType: "json",
        contentType: "application/json",
        data: JSON.stringify({
            "type": type,
            "coordinateX": xCoordinate,
            "coordinateY": yCoordinate,
            "gameId": gameId
        }),
        success: function (data) {
            gameOn = false;
            displayResponse(data);
        },
        error: function (error) {
            console.log(error);
        }
    })
}

function displayResponse(data) {
    let board = data.board.board;
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            let id = "D"+i+j;
            $("#" + id).text(board[i][j]);
        }
    }
    if (data.status.hasWinner) {
        $("#message").text("Winner is " + data.status.winner);
    }else{
        if (data.status.boardFull) {
            $("#message").text("It is TIE!");
        }
    }
    $("#win1").text(data.score.pl1Win);
    $("#win2").text(data.score.pl2Win);
    $("#tie").text(data.score.tie);
    document.getElementById("pl"+data.actualPlayer).style.backgroundColor = "lightblue";
    document.getElementById("pl"+(1-data.actualPlayer)).style.backgroundColor = "#207020";
    gameOn = true;
}
