import React from "react"; 
import imghome from "./Img/home/home.png"
import imgquantri from "./Img/main layout/1.png"
import imgdanhmuc from "./Img/main layout/2.png"
import imgdoan from "./Img/main layout/3.png"
import imgtochuc from "./Img/main layout/Vector.png"
import imgdieuhuong from "./Img/main layout/drop down.png"
import { useState } from "react";
import { useNavigate } from "react-router-dom";


const SeachBar = () =>{
    const [hidden, setHidden] = useState(true);

    const navigate = useNavigate();
    const Nganhnghe = () =>{
        navigate("/Testfield")
    }

    const Qttv = () =>{
        navigate("/Qttv")
    }

    const Quanlydoan = () =>{
        navigate("/Quanlydoan")
    }

    return(
        <div className="fixed flex flex-col justify-start items-center top-[0px] left-[0px] h-screen w-[15%] border-r-2 pt-[1%] z-10 bg-white">
            <img src={imghome} alt="anhhome" className="h-[80px]" />
            <div className="absolute top-[20%] w-full">
                <div className="flex flex-row space-x-4 justify-start items-center mb-[12%] pl-[10%] w-full h-[40px] hover:bg-[#F9F9F9] hover:text-[#1890FF]">
                    <img src={imgquantri} alt="anhhome" className="h-[22px]" />
                    <input type="button" onClick={Qttv} value="Quản trị thành viên"></input>
                </div>
                <div className="flex flex-col space-y-4 mb-[12%]">
                    <div className="flex flex-row space-x-4 justify-between items-center w-full h-[40px] pl-[10%] pr-[15%] hover:bg-[#F9F9F9] hover:text-[#1890FF]">
                        <div className="flex flex-row space-x-4 justify-start items-center"> 
                            <img src={imgdanhmuc} alt="anhhome" className="h-[22px]" />
                            <div>Danh mục</div>
                        </div>
                        <div className="flex flex-row items-center h-[30px]">
                            <img onClick={()=>setHidden(!hidden)} src={imgdieuhuong} alt="anhhome" className="hover:h-[30px] hover:bg-red-200 hover:rounded-full" />
                        </div>
                    </div>
                    {!hidden && <div className="flex flex-col justify-center items-start space-y-2 ml-[20%]">
                        <input onClick={Nganhnghe} value="Ngành nghề" type="button" className="hover:bg-[#F0F7FF] hover:w-[80%] hover:h-[30%] hover:text-[#1890FF] rounded-md"></input>
                        <input value="Khoá" type="button" className="hover:bg-[#F0F7FF] hover:w-[80%] hover:h-[30%] hover:text-[#1890FF] rounded-md"></input>
                        <input value="Lớp" type="button" className="hover:bg-[#F0F7FF] hover:w-[80%] hover:h-[30%] hover:text-[#1890FF] rounded-md"></input>
                    </div>}
                </div>
                <div className="flex flex-row space-x-4 justify-between items-center w-full h-[40px] mb-[12%] pl-[10%] pr-[15%] hover:bg-[#F9F9F9] hover:text-[#1890FF]">
                    <div className="flex flex-row space-x-4 justify-start items-center"> 
                        <img src={imgdoan} alt="anhhome" className="h-[22px]" />
                        <div>Đồ án</div>
                    </div>
                    <div className="flex flex-row items-center h-[30px]">
                        <img onClick={()=>{Quanlydoan()}} src={imgdieuhuong} alt="anhhome" className="hover:h-[30px] hover:bg-red-200 hover:rounded-full" />
                    </div>
                </div>
                <div className="flex flex-row space-x-4 justify-start items-center mb-[12%] pl-[10%] w-full h-[40px] hover:bg-[#F9F9F9] hover:text-[#1890FF]">
                    <img src={imgtochuc} alt="anhhome" className="h-[22px]" />
                    <div>Tổ chức</div>
                </div>
            </div>
        </div>
    )
}

export default SeachBar