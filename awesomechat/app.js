// const app = require('express')();
// const http = require('http').createServer(app);
// const io = require('socket.io')(http);

const express = require('express');
const app = express();
const http = require('http').createServer(app);
const io = require('socket.io')(http,{
  cors: {
    origin: "*",
  }
});

// Tạo một object để chứa các phòng chat
const rooms = {};

// API để tạo phòng chat mới
app.get('/create-room', (req, res) => {
  console.log(req.query.roomId);
  const roomId = req.query.roomId;
  const roomName = req.query.roomName;
  
  // Kiểm tra xem phòng chat đã tồn tại hay chưa
  if (rooms[roomId]) {
    return res.status(409).send('Room already exists');
  }

  // Tạo mới phòng chat
  rooms[roomId] = {
    id: roomId,
    name: roomName,
    sockets: []
  };

  res.status(200).send('Tao phong thanh cong: '+roomId);
});

// API để tham gia vào một phòng chat
app.get('/join-room', (req, res) => {
  const roomId = req.query.roomId;
  const socketId = req.query.socketId;

  // Kiểm tra xem phòng chat có tồn tại hay không
  if (!rooms[roomId]) {
    return res.status(404).send('Room not found');
  }

  // Lưu lại thông tin về socket vào danh sách của phòng chat
  rooms[roomId].sockets.push(socketId);

  // Emit một sự kiện để thông báo cho các client khác trong phòng chat
  io.to(roomId).emit('user-joined', socketId);

  res.status(200).send('Joined room successfully');
});


// Thiết lập kết nối với client
io.on('connection', (socket) => {
  console.log('A user connected');

  // Sự kiện khi client tham gia vào một phòng chat
  socket.on('join-room', (roomId) => {
    // Kiểm tra xem phòng chat có tồn tại hay không
    if (!rooms[roomId]) {
      return socket.emit('join-error', 'Room not found');
    }

    // Tham gia vào phòng chat
    socket.join(roomId);

    // Emit một sự kiện để thông báo cho các client khác trong phòng chat
    socket.to(roomId).emit('user-joined', socket.id);
  });

  // Sự kiện khi client ngắt kết nối
  socket.on('disconnect', () => {
    console.log('A user disconnected');

    // Lặp qua danh sách các phòng chat để tìm và xóa socket hiện tại
    for (let roomId in rooms) {
      if (rooms.hasOwnProperty(roomId)) {
        const sockets = rooms[roomId].sockets;
        const index = sockets.indexOf(socket.id);
        if (index !== -1) {
          sockets.splice(index, 1);
          io.to(roomId).emit('user-left', socket.id);
          break;
        }
      }
    }
  });
});

// Khởi động server
http.listen(3001, () => {
  console.log('Server listening on port 3001');
});

module.exports = app;
