import './App.css';
import { Route, Routes, BrowserRouter } from "react-router-dom";
import Sigin from './components/Sigin';
import InforBase from './components/InforBase';
import Work1 from './components/Work1';
import Testfield from './components/Testfield';
import ChangeInforS from './components/ChangeInforS';
import Work2 from './components/Work2';
import Qttv from './components/Qttv';
import Quanlydoan from './components/Quanlydoan';
import MqttConnect from './utils/mqtt';
import ChatRoom from './utils/socketio';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path='/Sigin' element={<Sigin />}></Route>
          <Route path='/' element={<InforBase/>}>
            <Route path='/' element={<Work1 />}></Route>
            <Route path='/ChangeInforS' element={<ChangeInforS />}></Route>
            <Route path='/Testfield' element={<Testfield />}></Route>
            <Route path='/Quanlydoan' element={<Quanlydoan />}></Route>
            <Route path='/Qttv' element={<Qttv />}></Route>
            <Route path='/Nganhnghe' element={<Work2 />}></Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
