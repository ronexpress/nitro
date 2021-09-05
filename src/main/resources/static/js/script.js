
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
            $("#message").text("It's TIE!");
        }
    }
    $("#win1").text(data.score.pl1Win);
    $("#win2").text(data.score.pl2Win);
    $("#tie").text(data.score.tie);
    document.getElementById("pl"+data.actualPlayer).style.backgroundColor = "lightblue";
    document.getElementById("pl"+(1-data.actualPlayer)).style.backgroundColor = "#207020";
    gameOn = true;
}
