<!DOCTYPE html>
<html lang="en">
<head>
    <title>Searching</title>
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <style>
        .div1 {
            display: inline-block;
        }
    </style>
    <script>
        $(document).on("click", "#searchButton", function () {
            let params = {
                bus: document.getElementById('search').value
            };

            $.post("search", $.param(params), function (responseJson) {
                let $select = $("#BusInfo");
                $select.find("option").remove();
                $.each(responseJson, function (key, value) {
                    $("<option>").text(key).appendTo($select)
                    $("<option>").val(key).text(value).appendTo($select);
                });
            });
        });
    </script>
</head>
<body>
<div class="div1">
    <form id="searchForm" action="search" method="post">
        <input type="text" name="search" id="search" placeholder="Bus"/>
    </form>
    <button id="searchButton">search</button>
</div>
<div id="BusInfo">
    <h1>Info</h1>
</div>
</body>
</html>