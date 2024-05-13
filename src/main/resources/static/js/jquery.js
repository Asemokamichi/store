$(function() {
    $("#slider").slider({
        range: true,
        min: $("#slider").data("min-value"),
        max: $("#slider").data("max-value"),
        values: [$("#slider").data("min-value"), $("#slider").data("max-value")],
        slide: function(event, ui) {
            $("#slider-value").text(ui.values[0] + " - " + ui.values[1]);
            $("#minValueInput").val(ui.values[0]);
            $("#maxValueInput").val(ui.values[1]);
        }
    });

    $("#slider-value").text($("#slider").slider("values", 0) +
        " - " + $("#slider").slider("values", 1));
});

