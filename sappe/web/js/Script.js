/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


function Checkbox(chkBox, argumento) {
    checkboxes = document.getElementsByName(argumento);
    var isChecked = chkBox.checked;
    for (var i = 0; i < checkboxes.length; i++) {
        checkboxes[i].checked = false;
    }
    if (isChecked) {
        chkBox.checked = true;
    }
}

function textCounter(field, countfield, maxlimit) {
    if (field.value.length > maxlimit)
        field.value = field.value.substring(0, maxlimit);
    else
        countfield.value = maxlimit - field.value.length;
}
