import React from 'react';
import mqtt from 'mqtt/dist/mqtt';
import { useState } from 'react';
import { useEffect } from 'react';

const MqttConnect = () =>{

    const [connectStatus, setConnectStatus] = useState('Connect');
    const [subtStatus, setSubStatus] = useState('sub');
    const [client, setClient] = useState(null);
    const [payload, setPayload] = useState({});
    const [inputot, setInputot] = useState({});
    const [issub, setIssub] = useState(false);
    const [inputsub, setInputsub] = useState({
        topic: '',
        qos: 0,
    });
    const [inputPayload, setInputPayload] = useState({
        payload: '',
    });
    const [messages, setMessages] = useState([])

    const handleChang = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputot(values => ({...values, [name]: value}))   //Spread Operator
    }

    const handleChang_1 = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputsub(values => ({...values, [name]: value}))   //Spread Operator
    }

    useEffect(()=>{
        console.log(inputsub)
    },[inputsub])

    const mqttConnect = () => {
        const { host, clientId, port, username, password } = inputot;
        const url = `wss://${host}:${port}/mqtt`;
        const options = {
          keepalive: 30,
          protocolId: 'MQTT',
          protocolVersion: 4,
          clean: true,
          reconnectPeriod: 1000,
          connectTimeout: 30 * 1000,
          will: {
            topic: 'ban_phim',
            payload: 'Connection Closed abnormally..!',
            qos: 0,
            retain: false
          },
          rejectUnauthorized: false
        };
        options.clientId = clientId;
        options.username = username;
        options.password = password;

        setConnectStatus('Connecting...');
        setClient(mqtt.connect(url, options));
    }

    const mqttDisconnect = () => {
        if (client) {
          client.end(() => {
            setConnectStatus('Connect');
          });
        }
    }

    const mqttSub = () => {
        if (client) {
          const { topic, qos } = inputsub;
          client.subscribe(topic, { qos }, (error) => {
            if (error) {
              console.log('Subscribe to topics error', error)
              setIssub(false);
              return
            }
            setIssub(true);
          });
        }
    }

    const mqttUnSub = () => {
        if (client) {
          const { topic } = inputsub;
          client.unsubscribe(topic, error => {
            if (error) {
              setIssub(true)
              return
            }
            setIssub(false)
          });
        }
    }

    const mqttPublish = () => {
        if (client) {
          const { topic, qos } = inputsub;
          const payload = inputPayload.payload;
          client.publish(topic, payload, { qos }, error => {
            if (error) {
              console.log('Publish error: ', error);
            }
          });
        }
    }

    useEffect(() => {
        if (client) {
          client.on('connect', () => {
            setConnectStatus('Connected');
          });
          client.on('error', (err) => {
            console.error('Connection error: ', err);
            client.end();
          });
          client.on('reconnect', () => {
            setConnectStatus('Reconnecting');
          });
          client.on('message', (topic, message) => {
            const payload = { topic, message: message.toString() };
            setPayload(payload);
          });
        }
    }, [client]);

    useEffect(() => {
        if (payload.topic) {
          setMessages(messages => [...messages, payload])
        }
    }, [payload])

    useEffect(()=>{
        if(issub){
            setSubStatus('sub_cr');
        }else{
            setSubStatus('sub_uncr');
        }
    },[issub])

    return(
        <div>
            <form className='flex flex-col space-y-2'>
                <input onChange={handleChang} value={inputot.host || ""} className="p-2 border-2 rounded-md w-[60%]" name="host" type="text" placeholder = {"host..."}></input>
                <input onChange={handleChang} value={inputot.clientId || ""} className="p-2 border-2 rounded-md w-[60%]" name="clientId" type="text" placeholder = {"clientId..."}></input>
                <input onChange={handleChang} value={inputot.port || ""} className="p-2 border-2 rounded-md w-[60%]" name="port" type="text" placeholder = {"port..."}></input>
                <input onChange={handleChang} value={inputot.username || ""} className="p-2 border-2 rounded-md w-[60%]" name="username" type="text" placeholder = {"username..."}></input>
                <input onChange={handleChang} value={inputot.password || ""} className="p-2 border-2 rounded-md w-[60%]" name="password" type="text" placeholder = {"password..."}></input>
            </form>
            <input type="button" value={connectStatus} onClick={mqttConnect} className="m-6 border-2 rounded-lg hover:bg-blue-700 w-[150px] h-[50px] hover:text-[#FFFFFF]"></input>
            <input type="button" value="Unconnect" onClick={mqttDisconnect} className="m-6 border-2 rounded-lg hover:bg-blue-700 w-[150px] h-[50px] hover:text-[#FFFFFF]"></input>
            
            <form className='flex flex-col space-y-2'>
                <input onChange={handleChang_1} value={inputsub.topic || ""} className="p-2 border-2 rounded-md w-[60%]" name="topic" type="text" placeholder = {"topic..."}></input>
                <select onChange={handleChang_1} className='w-[5%] border-2 rounded-md'>
                    <option value={0} name="qos">0</option>
                    <option value={1} name="qos">1</option>
                    <option value={2} name="qos">2</option>
                </select>
            </form>
            <input type="button" value={subtStatus} onClick={mqttSub} className="m-6 border-2 rounded-lg hover:bg-blue-700 w-[150px] h-[50px] hover:text-[#FFFFFF]"></input>
            <input type="button" value="Unsub" onClick={mqttUnSub} className="m-6 border-2 rounded-lg hover:bg-blue-700 w-[150px] h-[50px] hover:text-[#FFFFFF]"></input>
            <br></br>
            {issub && <input onChange={(event)=>(setInputPayload(values => ({...values, payload: event.target.value})))} value={inputPayload.payload || ""} className="p-2 border-2 rounded-md w-[60%]" name="payload" type="text" placeholder = {"payload..."}></input>}
            {issub && <input type="button" value="punlisher" onClick={mqttPublish} className="m-6 border-2 rounded-lg hover:bg-blue-700 w-[150px] h-[50px] hover:text-[#FFFFFF]"></input>}

            <div className='text-2xl mb-2'><b>Recive...</b></div>
            <div className="border-2 h-[250px] overflow-y-auto w-[60%]">
                <tr className="flex flex-row border items-center justify-start">
                    <td className="border-x flex justify-start w-[300px]"><b>Topic</b></td>
                    <td className="border-x flex justify-start w-full"><b>Message</b></td>
                </tr>
                {messages.map((item, index) => {
                return ( 
                    <tr className="flex flex-row border items-center justify-start">
                        <td className="border-x flex justify-start w-[300px]">{item.topic}</td>
                        <td className="border-x flex justify-start w-full">{item.message}</td>
                    </tr>
                )
                })}
            </div>

        </div>
    )
}

export default MqttConnect;