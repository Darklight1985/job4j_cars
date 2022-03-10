function check() {
   $.ajax({
        type: "GET",
        url: "http://localhost:8080/salecars/calc",
        dataType: "json",
        success: function (data) {
            $p = document.createElement('input')
            $p.textContent = data;
            $('#namek').appendChild($p);
        }
    })
}

