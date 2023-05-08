import React from "react";
import Imghomedetail from "./Img/home/home.png";
import Imgcamera from "./Img/home/camera.png";
import { useState } from "react";
import Madd from "./Madd";
import { useNavigate } from "react-router-dom";

const ChangeInforS = () =>{

    const [madd, setMadd] = useState(false);

    const navigate = useNavigate();
    const field = () =>{
        navigate(-1)
    }

    return(
        <div>
            <div style={{boxShadow: '0px 0px 10px #E5E5E5'}} className="text-xl z-0 absolute flex top-[10%] left-[15%] w-full h-[6%] pl-[2%] items-center justify-start ">Tổ chức</div>
            <div className="absolute top-[18%] left-[16%] right-[1%] h-auto bg-[#F9F9F9]">
                <div className="relative top-[1%] left-[12%] h-[200px] w-[200px]">
                    <img src={Imghomedetail} alt="anhhome" className="absolute h-[80%] w-[80%] z-0 top-[0%] left-[0%]" />
                    <img onClick={()=>setMadd(!madd)} src={Imgcamera} alt="anhhome" className="absolute h-[30px] z-10 top-[55%] left-[65%] hover:h-[40px] hover:left-[62%] hover:top-[52%]" />
                    <div className="absolute flex flex-col space-y-2 top-[30%] left-[100%] w-[100px] text-lg">
                        <label >Tên trường</label>
                        <input placeholder = {"Nhập tên trường..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                    </div>
                    {madd && <Madd />}
                </div>
                <div className="relative top-[3%] left-[12%] w-auto flex flex-col space-y-6">
                    <label className="font-semibold text-2xl">Thông tin trường</label>
                    <div className="flex flex-row justify-between items-center w-[50%]">
                        <div className="flex flex-col space-y-2 w-[100px] text-lg">
                            <label >Email</label>
                            <input placeholder = {"Nhập Email..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                        </div>
                        <div className="flex flex-col space-y-2 w-[100px] text-lg">
                            <label >Mã số thuế</label>
                            <input placeholder = {"Nhập Mã số thuế..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                        </div>
                    </div>
                    <div className="flex flex-row justify-between items-center w-[50%]">
                        <div className="flex flex-col space-y-2 w-[100px] text-lg">
                            <label >ố điện thoại</label>
                            <input placeholder = {"Nhập Số điện thoại..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                        </div>
                        <div className="flex flex-col space-y-2 w-[100px] text-lg">
                            <label >Website</label>
                            <input placeholder = {"Nhập Website..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                        </div>
                    </div>
                    <div className="flex flex-row justify-between items-center w-[50%]">
                        <div className="flex flex-col space-y-2 w-auto text-lg">
                                <label >Địa chỉ</label>
                                <input placeholder = {"Nhập Địa chỉ..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
                        </div>
                        <div className="flex flex-row justify-end items-end w-full h-[65px] text-lg">
                            {/* <label >Website</label>
                            <input placeholder = {"Nhập Website..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input> */}
                            <input value="Cập nhật" type="button" onClick={field} className="text-center border-2 rounded-md border-sky-500 text-sky-500 h-[30px] w-[100px] hover:bg-sky-500 hover:text-white"></input>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default ChangeInforS;