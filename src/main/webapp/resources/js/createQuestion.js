/**
 * 앙케이트 작성용 스크립트
 * enqueteAdd.jsp, enqueteEdit.jsp
 */

//let chkhtml = document.getElementById("chkhtml");
function linkTest() {
	alert("on linked");
}

/*
{"enquetes":[
  {"subject":"sbj", "options":[{"option":"opt1"},{"option":"opt2"},{"option":"opt3"},{"option":"opt4"}]},
  {"subject":"sbj", "options":[{"option":"opt1"},{"option":"opt2"},{"option":"opt3"},{"option":"opt4"}]}
]}
 */
function convertToJSON(id) {
	let enquetes = '{"enquetes":[';
	const container = document.getElementById(id);
	const contents = container.children;
	for(let i = 0; i < contents.length; i++) {
		let enquete = "{";
		const datas = contents[i].getElementsByTagName("input");
		enquete += '"subject":' + '"' + datas[0].value + '", "options":[';
		delimiter = "";
		for(let j = 1; j < datas.length; j++) {
			if (j > 1) {
				enquete += ",";	
			}
			enquete += delimiter + '{"option":"' + datas[j].value + '"}';
		}		
		enquete += "]}";
		if (i > 0) {
			enquetes += ",";	
		}
		enquetes += enquete;
	}
	enquetes += "]}";
	
	return enquetes;
}

function convertToHTML(json) {
	const objJson = eval("(" + json + ")");
	const enquetes = objJson.enquetes;
	const contents = [];
	for (let i = 0; i < enquetes.length; i++) {
		const subject = enquetes[i].subject;
		const options = [];
		for (let j = 0; j < enquetes[i].options.length; j++) {
			options[j] = enquetes[i].options[j].option;
		}
		const content = createQuestion(i + 1, subject, options);
		contents[i] = content;
	}
	enquetes[0].options[2].summary = 3;

	return contents;
}

function testJSON(json) {
	const implJson = eval("(" + json + ")");
	alert(implJson.enquetes[0].options[0].option);
}

/* 설문추가 */
function addQuestion(id) {
	const container = document.getElementById(id);
	const index = container.childElementCount;
	container.appendChild(createQuestion(index + 1));
}

/* 설문삭제 */
function delQuestion(id) {
	const container = document.getElementById(id);
	const index = container.childElementCount;
	if (index == 1) {
		alert("설문은 1개이상 등록해야 됩니다.");
		return;
	}
	container.removeChild(container.lastElementChild);
}

/*
<elementType class="className" />
 */
function createElement(elementType, className) {
	const elm = document.createElement(elementType);
	const att = document.createAttribute("class");
	att.value = className;
	elm.setAttributeNode(att);
	return elm;
}

/*
<div class="className" />
 */
function createDiv(className) {
	return createElement("div", className);
}

/*
<div class="wrapClass">
	<input type="text" name="name" value="value">
</div>
 */
function createInputDiv(wrapClass, name, value) {
	const wrap = createDiv(wrapClass);
	const inputText = createElement("input", "form-control");
	inputText.setAttributeNode(createAttribute("type", "text"));
	inputText.setAttributeNode(createAttribute("name", name));
	inputText.setAttributeNode(createAttribute("value", value));
	wrap.appendChild(inputText);
	return wrap;
}

/*
name="value"
 */
function createAttribute(name, value) {
	const att = document.createAttribute(name);
	att.value = value;
	return att;
}

/* 설문생성 */
function createQuestion(index, subject, options) {
	if (subject === undefined) {
		subject = "설문내용";
	}
	if (options === undefined) {
		options = ["선택1","선택2","선택3","선택4"];
	}

	const container = createDiv("form-group row");
	
	const num = createElement("label", "offset-sm-1 col-sm-1")
	num.innerText = "#" + index;
	container.appendChild(num);
	
	const contents = createDiv("col-sm-8");
	contents.setAttributeNode(createAttribute("style", "background-color: lightblue;"));
	container.appendChild(contents);

	const divSubject = createInputDiv("form-group row", "q" + index, subject)
	contents.appendChild(divSubject);
	
	const divOptions = createDiv("form-group row");
	for(let i = 0; i < 4; i++) {
		const opt = createInputDiv("col-sm-3", "q" + index + "_" + (i + 1), options[i]);
		divOptions.appendChild(opt);
	}
	contents.appendChild(divOptions);

	return container;
}