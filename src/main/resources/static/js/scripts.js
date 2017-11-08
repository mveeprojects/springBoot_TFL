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


//    Requires the below plugin to work - see index.html
//    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
function showIndividualLineStatus(lineName, lineStatus) {
    if(lineStatus == "Good Service"){
        swal(lineName, lineStatus, "success");
    } else {
        swal(lineName, lineStatus, "error");
    }
}