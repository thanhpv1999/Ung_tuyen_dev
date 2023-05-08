import './App.css';
import { Route, Routes, BrowserRouter } from "react-router-dom";
import MqttConnect from './utils/mqtt';
import ChatRoom from './utils/socketio';

function App() {
  return (
    <div>
      <MqttConnect />
      {/* <ChatRoom /> */}
    </div>
  );
}

export default App;
