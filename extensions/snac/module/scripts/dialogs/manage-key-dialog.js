var SNACManageKeysDialog = {};

SNACManageKeysDialog.launch = function(apikey, callback) {
  $.get(
    "command/snac/apikey",
    function(data) {
      SNACManageKeysDialog.display(apikey, data.apikey, callback);
    }
  );
};

SNACManageKeysDialog.display = function(apikey, saved_apikey, callback) {
  var self = this;
  var frame = $(DOM.loadHTML("snac", "scripts/dialogs/manage-key-dialog.html"));
  var elmts = this._elmts = DOM.bind(frame);
  
  SNACManageKeysDialog.firstLaunch = false;

  this._elmts.dialogHeader.text($.i18n('snac-account/dialog-header'));
  this._elmts.explainKey.html($.i18n('snac-account/explain-key'));
  this._elmts.explainKey2.html($.i18n('snac-account/explain-key2'));
  this._elmts.keyLabel.text($.i18n('snac-account/key-label'));
  this._elmts.keyInput.attr("placeholder", $.i18n('snac-account/key-placeholder'));
  this._elmts.cancelButton.text($.i18n('snac-account/close'));
  this._elmts.loginButton.text($.i18n('snac-account/log-in'));
  let checked = 1;
  var checkedOrNah = document.getElementById("myCheck");

  $(document).mouseup(function(e) {
    //console.log(e.target);
    if (e.target.id=="snackey") {
      //console.log("IN");
      $(document).keypress(function(e) { 
        if (e.keyCode == 13) {
          $('.submit-btn').click();
          //console.log("SUBMITTED");
        }
      });
    } else {
      //console.log("OUT");
      $(document).keypress(function(e) { 
        if (e.keyCode == 13) {
          e.preventDefault();
        }
      });
    }
  });

  $(document).keyup(function(e) {
    if (e.keyCode === 27) {
      $('.cancel-btn').click();   // esc
      //console.log("ESCAPED");
    }
  });

  valueChange = function(item){
      checkedOrNah = document.getElementById("myCheck");
      if (checkedOrNah.checked == true) {
        checked = 1;
      } else if (checkedOrNah.checked == false) {
          if (checked == null) {
              localStorage.removeItem('mycheckedvalue');
          } 
        checked = 0;
      }
  }
  if (apikey != null) {
    elmts.keyInput.val(apikey);
    } else if (saved_apikey != null) {
      //console.log(localStorage.getItem("mycheckedvalue"));
      if (localStorage.getItem("mycheckedvalue") == 1) {
        elmts.keyInput.val(saved_apikey);
      } 
      else if (localStorage.getItem("mycheckedvalue") == 0){
        elmts.keyInput.val(null);
      }
    }
  this._level = DialogSystem.showDialog(frame);

  var dismiss = function() {
    DialogSystem.dismissUntil(self._level - 1);
  };

  frame.find('.cancel-btn').click(function() {
     dismiss();
     callback(null);
  });

  elmts.loginButton.click(function() {
      frame.hide();
      $.post(
          "command/snac/apikey",
          elmts.apiKeyForm.serialize(),
          function(data) {
              if (data.apikey) {
                dismiss();
                callback(data.apikey);
              } else {
                dismiss();
                callback(null);
              }
          });
          localStorage.setItem('mycheckedvalue', checked);
          //console.log(checked);
  });
};
