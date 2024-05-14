$(function() {
    var min = $("#slider").data("min");
    var max = $("#slider").data("max");
    var currentMin = $("#slider").data("current-min");
    var currentMax = $("#slider").data("current-max");

    $("#slider").slider({
        range: true,
        min: min,
        max: max,
        values: [currentMin, currentMax],
        slide: function(event, ui) {
            $("#slider-value").text(ui.values[0] + " - " + ui.values[1]);
            $("#currentMin").val(ui.values[0]);
            $("#currentMax").val(ui.values[1]);
        }
    });

    $("#slider-value").text(currentMin + " - " + currentMax);
});
