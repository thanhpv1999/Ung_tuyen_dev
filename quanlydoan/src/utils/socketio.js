import React, { useState, useEffect } from 'react';
import io from 'socket.io-client';

const ChatRoom = () => {
  const [socket, setSocket] = useState(null);
  const [room, setRoom] = useState('');
  const [cRoom, setCRoom] = useState('');
  const [message, setMessage] = useState('');
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    const newSocket = io('http://localhost:3000');
    setSocket(newSocket);

    // Clean up function
    return () => {
      newSocket.disconnect();
    };
  }, []);

  const handleJoinRoom = () => {
    console.log("tao phong")
    socket.emit('joinRoom', room);
  };

  const handleCreateRoom = () => {
    console.log("tao phong")
    socket.emit('createRoom', cRoom);
  };

  const handleSendMessage = () => {
    socket.emit('sendMessage', room, message);
    setMessage('');
  };

  useEffect(() => {
    if (socket) {
      socket.on('message', message => {
        setMessages(prevMessages => [...prevMessages, message]);
      });
    }
  }, [socket]);

  return (
    <div>
      <h1>Chat Room</h1>
      <div>
        <label>Room name create:</label>
        <input className='border-2 rounded-md' type="text" value={cRoom} onChange={e => setCRoom(e.target.value)} />
        <button className='hover:bg-[#0efefe]' onClick={handleCreateRoom}>Create Room</button>
      </div>
      <div>
        <label>Room name:</label>
        <input className='border-2 rounded-md' type="text" value={room} onChange={e => setRoom(e.target.value)} />
        <button className='hover:bg-[#0efefe]' onClick={handleJoinRoom}>Join Room</button>
      </div>
      <div>
        <ul>
          {messages.map((message, index) => (
            <li key={index}>{message}</li>
          ))}
        </ul>
        <div>
          <input className='border-2 rounded-md' type="text" value={message} onChange={e => setMessage(e.target.value)} />
          <button className='hover:bg-[#0efefe]' onClick={handleSendMessage}>Send</button>
        </div>
      </div>
    </div>
  );
};

export default ChatRoom;