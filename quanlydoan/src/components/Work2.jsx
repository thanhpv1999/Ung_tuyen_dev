import React, { useEffect, useState } from 'react'
import request from "../utils/request";
import { useNavigate } from "react-router-dom";

const Work2 = () =>{

    const [data, setData] = useState([]);

    const navigate = useNavigate();
    useEffect(() =>{
        if(!(localStorage.getItem("token"))){
            navigate("/Sigin")
        }
    });

    useEffect(()=>{
        request({
            url: "/api/field",
            method: "GET",
        }).then((response)=>{
            console.log(response)
            setData(response.data)
        })
    },[])

    return(
        <div>
            <div style={{boxShadow: '0px 0px 10px #E5E5E5'}} className="z-0 absolute flex flex-row top-[10%] left-[15%] right-[0] h-[6%] pl-[2%] items-center justify-between pr-[3%]">
                <div className="text-xl">Ngành nghề</div>
                <input type="button" value="+ Thêm ngành nghề" className="text-center bg-[#1890FF] h-[25px] w-[15%] text-[#ffffff] hover:bg-red-700"></input>
            </div>
            <div className="absolute flex flex-col top-[20%] left-[18%] right-[3%] bottom-[12%] border-2 space-y-4">
                <div className="flex flex-row w-full h-[180px] bg-[#F0F0F0]">

                </div>
                <div className='flex overflow-y-scroll w-[100%] justify-center items-start'>
                    <table className='w-[95%]'>
                        <tr className='border'>
                            <th className='border'>Id</th>
                            <th className='border'></th>
                            <th className='border'>MÃ ngành nghề</th>
                            <th className='border'>Tên ngành nghề</th>
                        </tr>
                        {data.map((item,index)=> (
                            <tr className='border'>
                                <td className='border'>{index}</td>
                                <td className='border'></td>
                                <td className='border'>{item.code}</td>
                                <td className='border'>{item.name}</td>
                            </tr>
                        ))}
                    </table>
                </div>
            </div>
        </div>
    )
}

export default Work2;