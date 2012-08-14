/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validaNumeros(event)
{
    var Tecla = event.which;
    if(Tecla == null)
        Tecla = event.keyCode;
    if ( (Tecla < 48 || Tecla > 57) && (Tecla < 0 || Tecla > 27)){
        event.returnValue = false;
        alert("Só devem ser digitados números!")
        return false;
    }
    event.returnValue = true;
    return true;
}

function validaNumerosSilencioso(event)
{
    var Tecla = event.which;
    if(Tecla == null)
        Tecla = event.keyCode;
    if ( (Tecla < 48 || Tecla > 57) && (Tecla < 0 || Tecla > 27)){
        event.returnValue = false;
        return false;
    }
    event.returnValue = true;
    return true;
}

function confirmarExclucao(){

    if(confirm("Deseja Realmente Excluir?")){
        return true;
    }
    return false;
}

function confirmarLogin(){

    if(confirm("Deseja Realmente Efetuar Operação?")){
        return true;
    }
    return false;

}
function confirmarOperacao(){

    if(confirm("Deseja Realmente Efetuar Operação?")){
        return true;
    }
    return false;

}

function confirmarCadastrado(){

    if(confirm("Deseja Realmente Efetuar Operação?")){
        return true;
    }
    return false;

}
function confirmarSimulado(){

    if(confirm("Tem questões em branco?")){
        return true;
    }
    return false;

}

function formatar(objeto, sMask, evtKeyPress) {
    var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;
    //funcao para formatar campo CPF, DATA, TEL, CEP, COD
    if(document.all) { // Internet Explorer
        nTecla = evtKeyPress.keyCode;
    } else if(document.layers) { // Nestcape
        nTecla = evtKeyPress.which;
    } else {
        nTecla = evtKeyPress.which;
        if (nTecla == 8) {
            return true;
        }
    }
    sValue = objeto.value;
    // Limpa todos os caracteres de formata‡ão que
    // j  estiverem no campo.
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( ":", "" );
    sValue = sValue.toString().replace( ":", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( " ", "" );
    fldLen = sValue.length;
    mskLen = sMask.length;
    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;
    while (i <= mskLen) {
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/") || (sMask.charAt(i) == ":"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))
        if (bolMask) {
            sCod += sMask.charAt(i);
            mskLen++;
        }
        else {
            sCod += sValue.charAt(nCount);
            nCount++;
        }
        i++;
    }
    objeto.value = sCod;
    if (nTecla != 8) { // backspace
        if (sMask.charAt(i-1) == "9") { // apenas n£meros...
            return ((nTecla > 47) && (nTecla < 58));
        }
        else { // qualquer caracter...
            return true;
        }
    }
    else {
        return true;
    }
}

function formataHorario(Campo, teclapres)
{
    if(!validaNumerosSilencioso(teclapres)){
        return false;
    }
    return formatar(Campo, '##:##:##', teclapres);
}

function formataData(Campo, teclapres)
{
    if(!validaNumerosSilencioso(teclapres)){
        return false;
    }
    return formatar(Campo, '##/##/####', teclapres);
}

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

function textCounter(campo, countcampo, maxlimit) {
    if (campo.value.length > maxlimit)
        campo.value = campo.value.substring(0, maxlimit);
    else
        countcampo.value = maxlimit - campo.value.length;
}