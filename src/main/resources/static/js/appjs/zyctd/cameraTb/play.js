var turnedStreamUrl = $("#turnedStreamUrl").val();
var remoteUrl = $("#remoteUrl").val();
var frameSrc = remoteUrl + "?cameraUrl=" + encodeURI(turnedStreamUrl);
window.frames['iframe1'].location.href = frameSrc;
