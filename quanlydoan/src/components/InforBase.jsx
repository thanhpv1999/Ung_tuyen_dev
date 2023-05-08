import React from "react"; 
import SeachBar from "./SeachBar";
import Header from "./Header";
import { Outlet, useNavigate } from "react-router-dom";
import { useEffect } from "react";

const InforBase = () => {
    const navigate = useNavigate();
    const token = localStorage.getItem("token");

    useEffect(() =>{
        if(!token){
            navigate("/Sigin")
        }
    });

    return(
        <main>
            <SeachBar />
                <Outlet />
                {/* <br />
                <input onClick={loadget} type="button" value="GET" className="font-sans not-italic text-[#FFFFFF] text[16px] w-[361px] h-[40px] bg-[#1890FF] rounded-lg mt-[40px] hover:bg-red-700" />
                <br />
                <input onClick={dangxuat} type="button" value="ĐĂNG XUẤT" className="font-sans not-italic text-[#FFFFFF] text[16px] w-[361px] h-[40px] bg-[#1890FF] rounded-lg mt-[40px] hover:bg-red-700" /> */}
            <Header />
        </main>
    );
};

export default InforBase;