var commons = require('../commons');

exports.index = function(req, res){
  var params = {
    title: 'Socket.IO-Client (web)',
    host: commons.host
  };
  if (typeof commons.protocol != 'undefined') {
    params.protocol = commons.protocol;
  }
  
  res.render('chat', params);
};
