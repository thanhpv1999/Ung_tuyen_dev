import React from "react";
import Imghomedetail from "./Img/home/home.png";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";

const Work1 = () =>{

    const navigate = useNavigate();

    useEffect(() =>{
        if(!(localStorage.getItem("token"))){
            navigate("/Sigin")
        }
    });
    const field = () =>{
        navigate("/ChangeInforS")
    }

    return(
        <div>
            <div style={{boxShadow: '0px 0px 10px #E5E5E5'}} className="text-xl z-0 absolute flex top-[10%] left-[15%] w-full h-[6%] pl-[2%] items-center justify-start ">Tổ chức</div>
            <div className="absolute top-[18%] left-[16%] right-[1%] bottom-[3%] bg-[#F9F9F9]">
                <input value="Sửa" type="button" onClick={field} className="relative top-[6%] left-[90%] text-center border-2 rounded-md border-sky-500 text-sky-500 h-[30px] w-[50px] hover:bg-sky-500 hover:text-white"></input>
                <div className="relative top-[1%] left-[12%] h-[200px] w-[200px]">
                    <img src={Imghomedetail} alt="anhhome" className="absolute h-[80%] w-[80%] z-0 top-[0%] left-[0%]" />
                    <label className="absolute top-[50%] left-[100%] w-[100px] text-lg text-gray-400">Tên trường</label>
                </div>
                <div className="relative top-[8%] left-[12%] flex flex-col space-y-6">
                    <label className="font-semibold text-2xl">Thông tin trường</label>
                    <div className="flex flex-row justify-between items-center w-[50%]">
                        <label className="text-lg text-gray-400 w-[50%]">Email</label>
                        <div className="w-[20%] flex flex-row justify-start items-center">
                            <label className="text-lg text-gray-400">Mã số thuế</label>
                        </div>
                    </div>
                    <div className="flex flex-row justify-between items-center w-[50%]">
                        <label className="text-lg text-gray-400 w-[50%]">Số điện thoại</label>
                        <div className="w-[20%] flex flex-row justify-start items-center">
                            <label className="text-lg text-gray-400">Website</label>
                        </div>
                    </div>
                    <label className="text-lg text-gray-400">Địa chỉ</label>
                </div>
            </div>
        </div>
    )
}

export default Work1;