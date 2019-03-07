$(function(){
    // ソート可能ヘッダをクリックした時
    $(".sortable-th").on("click", function(e){
        // ページをリセット
        $("#page").val("0");

        // ソートのキーを設定
        $("#sortBy").val($(this).data("sortby"));

        // 現在のソート順を取得
        var currentSort = $(this).attr("aria-sort");
        if(currentSort === "descending"){
            // 昇順
            $("#order").val("asc");
        } else {
            // 降順
            $("#order").val("desc");
        }

        // 検索フォームを送信
        $("#searchForm").submit();
    });

    // ページャー
    // 前のページヘボタンをクリックした時
    $("#prevButton").on("click", function(e){
        $("#page").val(parseInt($("#page").val())-1);
        if(isNaN($("#page").val())){
            $("#page").val(0);
        }
        $("#searchForm").submit();
    });
    // 次のページヘボタンをクリックした時
    $("#nextButton").on("click", function(e){
        $("#page").val(parseInt($("#page").val())+1);
        if(isNaN($("#page").val())){
            $("#page").val(1);
        }
        $("#searchForm").submit();
    });

    // フォーム送信時
    $("form").on("submit", function(e) {
        // ボタン無効化
        $("button").prop("disabled", true);
        setTimeout(function() {
            // ボタン有効化
            $("button").prop("disabled", false);
        }, 5000);
    });

    // daterangepicker
    $(".form-daterangepicker").daterangepicker({
        "singleDatePicker": true,
        "showDropdowns": true,
        "timePicker": true,
        "timePicker24Hour": true,
        "timePickerSeconds": true,
        "autoApply": true,
        "autoUpdateInput": false,
        locale: {
            "format": "YYYY/MM/DD HH:mm:ss",
            "applyLabel": "決定",
            "cancelLabel": "クリア",
        }
    }).on("apply.daterangepicker", function(ev, picker) {
        $(this).val(picker.startDate.format("YYYY/MM/DD HH:mm:ss"));
    }).on("cancel.daterangepicker", function(ev, picker) {
        $(this).val("");
    });
});

// Ajaxエラーを処理
function handleAjaxError(XMLHttpRequest, textStatus, errorThrown){
    alert("エラー");
}