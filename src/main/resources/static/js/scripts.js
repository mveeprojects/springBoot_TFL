$(document).ready(function(){
    $('#jsonTable td.status').each(function(){
        if ($(this).text() != 'Good Service') {
            $(this).css('color','red');
        }
        if ($(this).text() == 'Minor Delays') {
            $(this).css('color','orange');
        }
        if ($(this).text() == 'Good Service') {
            $(this).css('color','green');
        }
    });
});

function showIndividualLineStatus(lineName, lineStatus) {
    alert("Line: " + lineName + "\n" + "Status: " + lineStatus);
}