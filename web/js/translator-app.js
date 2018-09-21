/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {
    // 初期処理(選択済みの値を初期値として設定)
    $(function () {
        // 翻訳言語
        var toLangVal = $('.toLangHdn').val();
        if (toLangVal === "") {
            toLangVal = 'en';   // 初期値設定
        }
        $('#toLang').val(toLangVal); // 選択値の保持
    });
    // 初期カーソル位置設定
    $('#inWords').focus();

    // コピー機能
    $('.copyBtn').on('click', function () {
        var clipboard = new ClipboardJS('.copyBtn');
        clipboard.on('success',
                function () {
                    console.log("copy success");
                });
    });
    // 文字カウント機能
    $('#inWords').keyup(function () {
        var txtcount = $(this).val().length;
        $('#txtlmt').text(txtcount);
        if (txtcount === 0) {
            $('#txtlmt').text("0");
        } else if (txtcount > 200) {
            $('#txtlmt').css('color: red;');
        }
    });
});
// 言語入替機能
function changeLang(url) {
    $('form').attr('action', url);
    $('form').submit();
}
