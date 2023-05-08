import React from "react";
import { useNavigate } from "react-router-dom/dist"
import avatar from "./Img/main layout/avatar.png"
import Imglogout from "./Img/main layout/logout.png"

const Mqltk = () =>{
    const navigate = useNavigate();

    const dangxuat = () =>{
        localStorage.removeItem("token");
        navigate("/Sigin");
    }

    return(
        <div className="fixed flex flex-col space-y-4 justify-center items-center top-[60px] right-[3%] h-[260px] w-[255px] bg-white drop-shadow-xl shadow-inner">
            <div className="flex flex-col space-y-4 justify-center items-center h-[80%] w-full">
                <img src={avatar} alt="anhhome" className="h-[60px] hover:" />
                <label >Nguyễn Văn A</label>
                <input type="button" value="Quản lý tài khoản" className="font-sans not-italic text-[#FFFFFF] text[16px] w-[60%] h-[35px] bg-[#1890FF] rounded-lg hover:bg-red-700" />
                </div>
            <hr className="w-full"></hr>
            <div className="flex flex-row w-full items-start justify-start pl-[5%] h-[20%]">
                <div className="hover:flex hover:border-2 hover:shadow-lg w-[50%] h-[80%] items-center justify-center">
                    <img onClick={dangxuat}  src={Imglogout} alt="anhhome"/>
                </div>
            </div>
        </div>
    )
}

export default Mqltk;