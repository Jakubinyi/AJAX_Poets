/**
 * Created by jakubinyi on 2017.04.25..
 */
$(document).ready(function(){
    $.ajax({
        method: 'GET',
        url: '/work-list',
        contentType: 'application/json; charset=utf-8',
        success: listArrived,
        error: xhrErrorOccurred
    });

    function listArrived(data, message, xhr) {
        data.forEach(function (val, ind, arr) {
            $("#works").append(createLi(val));
            //document.getElementById('works').appendChild(createLi(val));
            //document.querySelector('#works').appendChild(createLi(val));
        });
    }

    function createLi(work) {
        var listItem = $('<li>');
        listItem.prop('title', work.title);
        listItem.prop('publication', work.publication);
        listItem.prop('toggled', false);
        //listItem.text(work.title + ' (' + work.publication.toString() + ')');
        //listItem.html('<h4>' + work.title + ' (' + work.publication.toString() + ')</h4>');
        titleItem = $('<h4>');
        titleItem.text(work.title + ' (' + work.publication.toString() + ')');
        listItem.append(titleItem);
        listItem.click(liClicked);
        //var listItem = document.createElement('li');
        //listItem.innerText = work.title + ' (' + work.publication.toString() + ')'
        return listItem;
    }

    function liClicked(event) {
        var listItem = $(this);
        if (listItem.prop('toggled') === true) {
            listItem.prop('toggled', false);
            window.bla = listItem;
            listItem.find('p').remove();
        }
        else {
            listItem.prop('toggled', true);
            $.ajax({
                method: 'GET',
                url: '/work?title=' + encodeURI(listItem.prop('title')),
                contentType: 'text; charset=utf-8',
                success: function (data, message, xhr) {
                    displayWork(data, listItem);
                },
                error: xhrErrorOccurred
            });
        }

    }

    function displayWork(data, listItem) {
        var div = $('<div>');
        data.split('\n\n').forEach(function (elem, ind, arr) {
           div.append($('<p>').text(elem));
        });
        listItem.append(div);
    }

    function xhrErrorOccurred(xhr, message, error) {
        alert(message);
        console.log('Error occurred:');
        console.log(error);
        console.log('Related AJAX-request:');
        console.log(xhr);
    }
});

function counter() {
    var str = $("#search").val();
    var regexp = new RegExp(str, "gi");
    var result = $("*").html().match(regexp).length;
    $("#result")[0].innerHTML = "Occured: " + result;
}

