var express = require('express')
  , chat = require('./routes/chat')
  , http = require('http')
  , path = require('path')
  , socketIO = require('socket.io')
  , commons = require('./commons');

var app = express();
var server = http.createServer(app);
var io = socketIO.listen(server);

app.configure(function(){
  app.set('port', commons.port);
  app.set('views', __dirname + '/views');
  app.set('view engine', 'ejs');
  app.use(express.logger('dev'));
  app.use(express.bodyParser());
  app.use(express.methodOverride());
  app.use(app.router);
  app.use(express.static(path.join(__dirname, 'public')));
});

app.configure('development', function(){
  app.use(express.errorHandler());
});

app.get('/', chat.index);

server.listen(app.get('port'), function(){
  console.log("Express server listening on port " + app.get('port'));
});

if (typeof commons.protocol != 'undefined') {
  io.configure(function(){
    io.set("transports", [commons.protocol]);
  });
}

io.sockets.on("connection", function(socket){
  console.log("client connected");
  socket.on("message", function(data){
    console.log("receive messsage -> %j", data);
    io.sockets.emit("broadcast", data);
  });
  socket.on("disconnect", function(){
    console.log("client disconnected");
  });
});
