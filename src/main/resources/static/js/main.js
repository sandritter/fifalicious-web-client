$(document).ready(function () {
    
    $(".addStroke").click(function (event) {
        fire_ajax_call(event, "/player/add-stroke", "POST");
    });

    $(".deleteStrokes").click(function (event) {
        fire_ajax_call(event, "/player/delete-all-strokes", "PUT");
    });
});

function fire_ajax_call(event, url, type) {

    $(event.target).prop("disabled", true);
    var player = getPlayer(event.target);
    var payload = {};
    payload["player-reference"] = player.id;
    
    $.ajax({
        type: type,
        contentType: "application/json",
        url: url,
        data: JSON.stringify(payload),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            updatePlayerView(player, data);
            setTimeout(function() {
                $(event.target).prop("disabled", false);
            }, 2000);
        },
        error: function (e) {
            $("#btn-search").prop("disabled", false);
        }
    });

}

function getPlayer(target) {
    return target.closest(".player");
}

function updatePlayerView(player, update) {
    updateChart(player, update);
    updateCircleText(player, update);
    updateCircleColor(player, update);
}

function updateChart(player, update) {
    var chart = $(player).find(".item");
    $(chart).removeClass();
    $(chart).addClass("item");
    $(chart).addClass(update.strikeRange);
}

function updateCircleText(player, update) {
    var circleText = $(player).find(".circle-text");
    $(circleText)[0].innerText = update.activeAmountOfStrokes + "/" + update.maxAmountOfStrokes;
}

function updateCircleColor(player, update) {
    var circle = $(player).find("#circle");
    $(circle).removeAttr("stroke");
    console.log(update.strokeColor);
    $(circle).attr("stroke", update.strokeColor);
}
