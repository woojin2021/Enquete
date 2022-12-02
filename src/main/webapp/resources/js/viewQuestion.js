/**
 * 앙케이트 표시 스크립트
 * enquete.jsp
 */

function convertToHTMLCode(json) {
	const objJson = eval("(" + json + ")");
	const enquetes = objJson.enquetes;
	let htmlCode = '';
	for (let i = 0; i < enquetes.length; i++) {
		const subject = enquetes[i].subject;
		htmlCode += '<div class="form-group row"><label class="col-sm-12">Q' + (i+1) + '&nbsp;&nbsp;&nbsp;' + subject + '</label><div class="offset-sm-1 col-sm-8">';
		let defaultCheck = 'checked';
		for (let j = 0; j < enquetes[i].options.length; j++) {
			const option = enquetes[i].options[j].option;
			htmlCode += '<div class="form-check"><input class="form-check-input" type="radio" name="ANS_' + (i+1)+ '" value="' + (j+1) + '" ' + defaultCheck + '>' + option + '</div>';
			defaultCheck = '';
		}
		htmlCode += '</div></div>';
	}
	
	return htmlCode;
}
