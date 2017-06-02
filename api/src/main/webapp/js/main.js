
function main(){

    $('.events').hide();
    $('.events').fadeIn(1000);
    $('.result').hide();
    $('.submit-button').on('click',function(){
    $(this).next().slideToggle(400);
    $(this).toggleClass('active');
    var id = document.getElementById("idInput").value;
    var title = document.getElementById("titleInput").value;
    var host = document.getElementById("hostInput").value;
    gapi.client.myApi.createEvent({'id': id,'title':title,'host':host}).execute(
    function(response) {
                var outputAlertDiv = document.getElementById('outputAlert');
                outputAlertDiv.style.visibility = 'visible';
                if (!response.error) {
                  outputAlertDiv.className = 'alert alert-success';
                  outputAlertDiv.innerHTML = '<h2>' + response.result.data + '</h2>';
                }
                else if (response.error) {
                  outputAlertDiv.className = 'alert alert-danger';
                  outputAlertDiv.innerHTML = '<b>Error Code: </b>' + response.error.code + ' [' + response.error.message + ']';
                }
             }
    );
    return false;
    //document.getElementById("id").innerHTML = response.id;
    //document.getElementById("title").innerHTML = response.title;
    //document.getElementById("host").innerHTML = response.host;
});
}

$(document).ready(main);

/*
    // This is called initially
    function init() {
      var apiName = 'myApi';
      var apiVersion = 'v1';
      var apiRoot = 'http://' + window.location.host + '/_ah/api';
      if (window.location.hostname == 'localhost'
          || window.location.hostname == '127.0.0.1'
          || ((window.location.port != "") && (window.location.port > 1023))) {
            // We're probably running against the DevAppServer
            apiRoot = 'http://' + window.location.host + '/_ah/api';
      }
      //apiRoot = 'http://192.168.0.13:8080/_ah/api';
      var callback = function() {
        enableClick();
      }
      //alert(apiRoot);
      gapi.client.load(apiName, apiVersion, callback, apiRoot);
    }

*/

