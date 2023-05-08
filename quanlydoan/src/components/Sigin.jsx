import React from "react";
import axios from 'axios';
import { useState } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import Img from "./Img/unsplash_7JX0-bfiuxQ.png";
import Img2 from "./Img/unsplash_ruJm3dBXCqw.png";

function Sigin(){
    const [inputs, setInput] = useState("");
    // const [status, setStatus] = useState(401);
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    useEffect(() =>{
        if(token){
            navigate("/")
        }
    });

    const Login = () => {
        axios({
            url: "https://training.bks.center/api/auth/login",
            method: "POST",
            params: {
                username: inputs.username,
                password: inputs.password,
            }
        }).then(Response=>{
            console.log(Response);
            localStorage.setItem ("token", Response.data.token);
            if(Response.data.token !== null){
                navigate("/");
            }
        }).catch(e=>{
            console.log("loi post api: " + e)
        })
    }

    const handleChang = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInput(values => ({...values, [name]: value}))   //Spread Operator
    }

    return(
        <div>
            <h1 className="z-10 absolute left-[40px] top-[24px] w-[200px] h-[23px] font-sans not-italic font-medium text-xl text-[#172B4D] ">Quản lý đồ án</h1>
            <h1 className="z-10 absolute left-[20%] top-[140px] w-[200px] h-[38px] font-sans not-italic font-bold text-[32px] leading-9 text-[#1890FF] ">Đăng nhập</h1>
            <form className="absolute left-[15%] top-[229px] z-10">
                <label>
                    <div className="mb-[10px] text-[#172B4D]">Tài khoản</div>
                    <input onChange={handleChang} name="username" value={inputs.username || ""} type="text" placeholder = {"Tài khoản"} className = "border-2 border-sloid rounded-md w-[361px] h-[40px] pl-[24px]" />
                </label>
                <label>
                    <div className="mb-[10px] mt-[24px] text-[#172B4D]">Mật khẩu</div>
                    <input onChange={handleChang} name="password" value={inputs.password || ""} type="password" placeholder = {"Mật khẩu"} className = "border-2 border-sloid rounded-md w-[361px] h-[40px] pl-[24px]" />
                </label>
                <br/>
                <input onClick={Login} type="button" value="Đăng nhập" className="font-sans not-italic text-[#FFFFFF] text[16px] w-[361px] h-[40px] bg-[#1890FF] rounded-lg mt-[40px] hover:bg-red-700" />
            </form>
            <img src={Img} alt="anh1" className="absolute top-[0px] right-[0px] z-0 w-[45%] h-[65%]" />
            <img src={Img2} alt="anh2" className="absolute bottom-[0px] right-[67.2px] z-0 w-[55%] h-[50%]" />
        </div>
    );
}

export default Sigin;