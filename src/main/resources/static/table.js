$(document).ready(function () {

    $('#selecctall').click(function (event) {
        if (this.checked) {
            $('.checkbox').each(function () {
                $(this).prop('checked', true);
            });
        } else {
            $('.checkbox').each(function () {
                $(this).prop('checked', false); 
            });
        }

        var checkedRows = $('.checkbox:checked').map( function() {
            return this.value;
        }).get().join(",");

        $('#ids').val(checkedRows);

    });

    $('.checkbox').change(function(){
        var checkedRows = $('.checkbox:checked').map( function() {
            return this.value;
        }).get().join(",");

        $('#ids').val(checkedRows);
    });

});