const express = require('express');
const app = express();
const http = require('http');
const server = http.createServer(app);
const { Server } = require("socket.io");
const io = new Server(server, {
  cors: {
    origin: "*",
  }
});

// app.get('/', (req, res) => {
//   res.sendFile(__dirname + '/index.html');
// });

// io.on('connection', (socket) => {
//   console.log('a user connected');
//   socket.on('disconnect', () => {
//     console.log('user disconnected');
//   });
//   socket.on('chat message', (msg) => {
//     console.log('message: ' + msg);
//   });
  
//   socket.on('chat message', (msg) => {
//     // io.emit('chat message', msg);
//     socket.broadcast.emit('chat message', msg);
//   });
  
// });

io.on('connection', socket => {
  console.log('Client connected');

  socket.on('createRoom', room => {
    // Tạo phòng và gửi thông báo tới client
    console.log("tao phong");
    socket.join(room);
    socket.emit('roomCreated', room);
  });

  socket.on('joinRoom', room => {
    // Tham gia vào phòng và gửi thông báo tới các client khác trong phòng
    console.log("vao phong");
    socket.join(room);
    socket.to(room).emit('message', 'A new user has joined the room');
  });

  socket.on('sendMessage', (room, message) => {
    // Gửi tin nhắn tới các client khác trong phòng
    socket.to(room).emit('message', message);
  });

  socket.on('disconnect', () => {
    console.log('Client disconnected');
  });
});


server.listen(3000, () => {
  console.log('listening on *:3000');
});