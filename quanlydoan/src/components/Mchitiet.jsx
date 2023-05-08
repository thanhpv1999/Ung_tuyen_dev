import React from "react";
import { useState } from "react";
import { useEffect} from "react";
import request from "../utils/request";

const Mchitiet = (props) =>{

    const [data, setData] = useState([])
    const [dataimg, setDataimg] = useState("")

    useEffect(() =>{
        request({
            url: "/api/admin/user/" + props.id,
            method: "GET"
        }).then((response)=>{
            let rsp = response.data;
            viewimg(rsp.avatar);
            setData(rsp);
        })
    },[])

    const viewimg = (path) =>{
        request({
            url:"/api/file/view/" + path,
            method: "GET",
            responseType: "blob"
        }).then((response)=>{
            const url = URL.createObjectURL(response.data)
            setDataimg(url)
        })
    }


    return(
        <div onClick={props.hidden}>
            <div className="z-20 absolute left-[0px] top-[0px] w-full h-screen bg-[#42526E] opacity-[40%]"></div>
            <div className="z-20 absolute flex flex-col space-y-4 left-[15%] top-[20%] w-[70%] h-[300px] bg-[#FFFFFF] p-6">
                <div>infor of {data.type}</div>
                <div className="flex flex-row items-between justify-between w-[30%]">
                    <img src={dataimg} alt="chưa có ảnh" className="h-[100px] w-[100px]"/>
                    <div>{data.username}</div>   
                </div>
                <div className="flex flex-row items-between justify-between w-[30%]">
                    <div>{data.fullName}</div>
                    <div>{data.email}</div>
                </div>
                <div className="flex flex-row items-between justify-between w-[30%]">
                    <div>{data.phone}</div>
                    <div>{data.birthday}</div>
                </div>
            </div>
        </div>
    )
}

export default Mchitiet;