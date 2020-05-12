var ManageUploadDialog = {};

var check_dataType = "";

var myVar;

function _getChangedText() {
  var words = ["Uploading", "Uploading.", "Uploading..", "Uploading..."];

  var i = 0;

  i = (i + 1) % words.length;
  return words[i];
}

function _changeText() {
  var txt = _getChangedText();
  document.getElementById("changer").innerHTML = txt;
}
function displayProgressBar() {
  
  myVar = setInterval("_changeText()", 1000); 
  // find way to terminate this...maybe put in new function (using a start/stop)
  $(".upload-progress-bar")[0].style.visibility = "visible";
}

ManageUploadDialog.firstLogin = true;

ManageUploadDialog.launch = function(apikey, callback) {
   $.get(
      "command/snac/apikey",
       function(data) {
        ManageUploadDialog.display(apikey, data.apikey, callback);
   });
};

ManageUploadDialog.display = function(apikey, saved_apikey, callback) {
  var self = this;
  var frame = $(DOM.loadHTML("snac", "scripts/dialogs/manage-upload-dialog.html"));
  var elmts = this._elmts = DOM.bind(frame);

  if (document.getElementById('resourcebutton').checked) {   
    check_dataType = "GET_Resource";
 }
 else {
    check_dataType = "GET_Constellation";
 }

  ManageUploadDialog.firstLaunch = false;

  this._elmts.dialogHeader.text($.i18n('snac-upload/dialog-header'));
  this._elmts.explainUpload.html($.i18n('snac-upload/explain-key'));
  this._elmts.keyLabel.text($.i18n('snac-upload/key-label'));
  this._elmts.cancelButton.text($.i18n('snac-upload/close'));
  this._elmts.uploadButton.text($.i18n('snac-upload/upload'));

  if (apikey != null) {
    this._elmts.keyInput.text(apikey);

    } else if (saved_apikey != null) {
      this._elmts.keyInput.text(saved_apikey);
    }
  this._level = DialogSystem.showDialog(frame);

  var dismiss = function() {
    DialogSystem.dismissUntil(self._level - 1);
  };

  frame.find('.cancel-btn').click(function() {
     dismiss();
     callback(null);
  });

  var rad = document.getElementsByName('uploadOption')
  var prev = null;
  var prod_or_dev = "dev";
  for (var i = 0; i < rad.length; i++) {
      rad[i].addEventListener('change', function() {
          (prev) ? prev.value: null;
          if (this !== prev) {
              prev = this;
          }
          prod_or_dev = this.value;
      });
  }

    elmts.uploadButton.click(function() {
    
    console.log(prod_or_dev);
    console.log(elmts.apiKeyForm.serialize());

    $.post(
      "command/snac/upload",
      {
        "state": JSON.stringify(prod_or_dev),
        "dataType": JSON.stringify(check_dataType)
      },
      function(data) {
          dismiss();
          callback(null); 
          clearInterval(myVar);//maybe here you need to terminate the setInterval call
      });
  });
};
