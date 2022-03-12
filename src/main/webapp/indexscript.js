function addAds() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/adcar",
        dataType: "json",
        success: function (data) {
            $('#table tbody div').remove();
            addRow(data);
        }
    })
}

function pass(elem) {
    var status = elem.target.id
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/salecars/putitem',
        data: {
            'id': status
        },
        success: function () {
            addAds()
        }
    })
}

function getUser() {
    var value;
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/auth.do",
        async: false,
        dataType: "json",
        success: function (data) {
            $('#userrow li:first a').remove();
            $p = document.createElement('a')
            $p.className = "nav-link";
            $p.text = "Пользователь: " + data['name']
            $('#userrow li:first').append($p);
            value = data['name'];
        }
    })
    return value;
}

function addRow(data) {
    var cifra = getUser()
    for (let i = 0; i < data.length; i++) {
        <!-- <Блок с изображением> -->
        $divcap = document.createElement('div');
        $newimg = document.createElement('img');
        $newimg.width = "450";
        $newimg.src = data[i]['photo'];
        $divcap.className = "col-md-5";
        $divcheck = document.createElement('div');
        $divcheck.className = "form-check";
        $inputad = document.createElement('input');
        $inputad.className = "form-check-input";
        $inputad.type = "checkbox";
        $inputad.id = data[i]['id'];
        $labelcheck = document.createElement('label');
        $labelcheck.className = "form-check-label";
        $labelcheck.htmlFor = data[i]['id'];
        $labelcheck.innerText = "Закрыть заявление о продаже"
        if (data[i]['user'].name != cifra) {
            $inputad.disabled = true;
        }
        if (data[i]['done'] == true) {
            $inputad.checked = true;
        }

        $inputad.addEventListener("change", pass, true)

        $divcheck.appendChild($inputad);
        $divcheck.appendChild($labelcheck);

        $divcap.appendChild($newimg);
        $divcap.appendChild($divcheck);

        <!-- <Блок содержащий карточки данных> -->
        $divcard = document.createElement('div');
        $divcard.className = "card-deck";

        $newdiv = document.createElement('div');
        $newdiv.className = "card-body";
        $labelUser = document.createElement('label');
        $labelUser.innerHTML = "Владелец";
        $inputUser = document.createElement('input');
        $inputUser.className = "form-control";
        $inputUser.style = "width: 100%";
        $inputUser.value = data[i]['user'].name;
        $inputUser.readOnly = true;

        $labelModel = document.createElement('label');
        $labelModel.innerHTML = "Модель"
        $inputModel = document.createElement('input');
        $inputModel.className = "form-control";
        $inputModel.style = "width: 100%";
        $inputModel.value = data[i]['car'].model.name;
        $inputModel.readOnly = true;
        $labelDate = document.createElement('label');
        $labelDate.innerHTML = "Дата размещенея объявления"
        $inputDate = document.createElement('input');
        $inputDate.className = "form-control";
        $inputDate.style = "width: 100%";
        $inputDate.value = data[i]['created'];
        $inputDate.readOnly = true;
        $labelStatus = document.createElement('label');
        $labelStatus.innerHTML = "Статус объявления"
        $inputStatus = document.createElement('input');
        $inputStatus.className = "form-control";
        $inputStatus.style = "width: 100%";
        if (data[i]['done'] == true) {
            $inputStatus.value = "Продано";
        } else {
            $inputStatus.value = "В продаже";
        }
        $inputStatus.readOnly = true;
        $labelDescr = document.createElement('label');
        $labelDescr.innerHTML = data[i]['description'];
        $newdiv.appendChild($labelUser);
        $newdiv.appendChild($inputUser);
        $newdiv.appendChild($labelModel);
        $newdiv.appendChild($inputModel);
        $newdiv.appendChild($labelDate);
        $newdiv.appendChild($inputDate);
        $newdiv.appendChild($labelStatus);
        $newdiv.appendChild($inputStatus)

        $newdiv.appendChild(document.createElement('br'));
        $newdiv.appendChild($labelDescr);


        $newdiv2 = document.createElement('div');
        $newdiv2.className = "card-body";
        $labelBody = document.createElement('label');
        $labelBody.innerHTML = "Тип кузова"
        $inputBody = document.createElement('input');
        $inputBody.className = "form-control";
        $inputBody.style = "width: 100%";
        $inputBody.value = data[i]['car'].bodyType.name;
        $inputBody.readOnly = true;
        $labelEngine = document.createElement('label');
        $labelEngine.innerHTML = "Двигатель"
        $inputEngine = document.createElement('input');
        $inputEngine.className = "form-control";
        $inputEngine.style = "width: 100%";
        $inputEngine.value = data[i]['car'].engine.name;
        $inputEngine.readOnly = true;

        $newdiv2.appendChild($labelBody);
        $newdiv2.appendChild($inputBody);
        $newdiv2.appendChild($labelEngine);
        $newdiv2.appendChild($inputEngine);

        $newdiv3 = document.createElement('div');
        $newdiv3.className = "card-body";
        $labelDrive = document.createElement('label');
        $labelDrive.innerHTML = "Тип привода"
        $inputDrive = document.createElement('input');
        $inputDrive.className = "form-control";
        $inputDrive.style = "width: 100%";
        $inputDrive.value = data[i]['car'].driveType.name;
        $inputDrive.readOnly = true;
        $labelPower = document.createElement('label');
        $labelPower.innerHTML = "Мощность, л.с."
        $inputPower = document.createElement('input');
        $inputPower.className = "form-control";
        $inputPower.style = "width: 100%";
        $inputPower.value = data[i]['car'].power;
        $inputPower.readOnly = true;

        $newdiv3.appendChild($labelDrive);
        $newdiv3.appendChild($inputDrive);
        $newdiv3.appendChild($labelPower);
        $newdiv3.appendChild($inputPower);

        $divcard.appendChild($newdiv);
        $divcard.appendChild($newdiv2);
        $divcard.appendChild($newdiv3);

        <!-- <Создаем основной несущий блок> -->

        $divgen = document.createElement('div');
        $divgen.className = "card text-white bg-dark mb-3";

        $divsub = document.createElement('div');
        $divsub.className = "row no-gutters";

        $divsub.appendChild($divcap)
        $divsub.appendChild($divcard)

        $divgen.appendChild($divsub);

        $('#table tbody').append($divgen);

    }
}


function getModel() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/getmodel",
        async: false,
        dataType: "json",
        success: function (datamodel) {
            $('#ModelGroup option').remove();
            for (let i = 0; i < datamodel.length; i++) {
                $option = document.createElement('option')
                $option.value = datamodel[i]['id']
                $option.innerHTML = datamodel[i]['name']
                $('#ModelGroup').append($option);
            }
        }
    })
}

function getBody() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/getbody",
        async: false,
        dataType: "json",
        success: function (databody) {
            $('#BodyGroup option').remove();
            for (let i = 0; i < databody.length; i++) {
                $option = document.createElement('option')
                $option.value = databody[i]['id']
                $option.innerHTML = databody[i]['name']
                $('#BodyGroup').append($option);
            }
        }
    })
}

function getDrive() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/getdrive",
        async: false,
        dataType: "json",
        success: function (datadrive) {
            $('#DriveGroup option').remove();
            for (let i = 0; i < datadrive.length; i++) {
                $option = document.createElement('option')
                $option.value = datadrive[i]['id']
                $option.innerHTML = datadrive[i]['name']
                $('#DriveGroup').append($option);
            }
        }
    })
}

function getEngine() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/getengine",
        async: false,
        dataType: "json",
        success: function (dataengine) {
            $('#EngineGroup option').remove();
            for (let i = 0; i < dataengine.length; i++) {
                $option = document.createElement('option')
                $option.value = dataengine[i]['id']
                $option.innerHTML = dataengine[i]['name']
                $('#EngineGroup').append($option);
            }
        }
    })
}
