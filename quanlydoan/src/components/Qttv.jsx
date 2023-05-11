import React, { useEffect, useState } from "react";
import request from "../utils/request";
import { useNavigate } from "react-router-dom";
import Imgnhanbutoon from "./Img/danhmuc/Nhanbutton.png"
import Maddmember from "./Maddmember";
import Mchitiet from "./Mchitiet.jsx";
import Mchangeinf from "./Mchangeinf";

const Qttv = () => {
  const [data, setData] = useState([]);
  const [idct, setIdct] = useState();
  const [currentPage, setCurrentPage] = useState(0);
  const [pageSize, setPageSize] = useState(5);
  const [filter, setFilter] = useState({
    name: "",
    phone: "",
    email: "",
    address: "",
    sex: "",
    birthday: "",
  })

  const [addchoose, setAddchoose] = useState(null);
  const [tieptuc, setTieptuc] = useState(false);
  const [maddchoose, setMaddchoose] = useState(false);
  const [madd, setMadd] = useState(false);
  const [mchange, setChange] = useState(false)
  const [totalmem, setTotalmem] = useState();
  const [mchitiet, setMchitiet] = useState(false);
  const [mchangeinf, setMchangeinf] = useState(false);

  const navigate = useNavigate();
//   useEffect(() =>{
//       if(!(localStorage.getItem("token"))){
//           navigate("/Sigin")
//       }
//   });

  const handleSeach = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setFilter(values => ({...values, [name]: value}))   //Spread Operator
}


  useEffect(() => {
    request({
      url: "/api/admin/user",
      method: "GET",
      params: {
        address: filter.address,
        birthday_end: filter.birthday,
        email: filter.email,
        full_name: filter.name,
        gender: filter.sex,
        phone: filter.phone,
        page_index: currentPage,
        page_size: pageSize,    
    }
    }).then((response) => {
      console.log(response);
      let responseData = response.data;
      setData(responseData);    
      setTotalmem((response.headers.totalelement/pageSize));
    });
  }, [filter, currentPage, pageSize, totalmem]);   

  const Hienbutton =(id)=>{
      return(
        <button className="p-2 border">{id + 1}</button>
      )
  }

  return (
    <>
      <div style={{boxShadow: '0px 0px 10px #E5E5E5'}} className="z-0 absolute flex flex-row top-[10%] left-[15%] right-[0] h-[6%] pl-[2%] items-center justify-between pr-[3%]">
        <div className="text-xl">Thành viên</div>
        <input onClick={()=>{setMadd(true)}} type="button" value="+ Thêm thành viên" className="text-center bg-[#1890FF] h-[25px] w-[15%] text-[#ffffff] hover:bg-red-700"></input>
      </div>

      {madd && 
      <div>
        <div className="z-20 absolute left-[0px] top-[0px] w-full h-screen bg-[#42526E] opacity-[40%]" onClick={()=>setMadd(false)}></div>
        <div className="z-20 absolute flex flex-col space-y-4 left-[15%] top-[20%] w-[70%] h-[300px] bg-[#FFFFFF] p-6">
          <div>Thêm thành viên</div>
          <hr className="w-full"></hr>
          <div className="flex flex-row justify-between items-center w-[50%]">
            <div> <input onChange={(e) => {setAddchoose(() => (e.target.value)); setTieptuc(true)}} type="radio" name="addmember" value="0" /> Quản lý </div>
            <div> <input onChange={(e) => {setAddchoose(() => (e.target.value)); setTieptuc(true)}} type="radio" name="addmember" value="1" /> Giảng viên </div>
            <div> <input onChange={(e) => {setAddchoose(() => (e.target.value)); setTieptuc(true)}} type="radio" name="addmember" value="2" /> Sinh viên </div>
          </div>
          <hr className="w-full"></hr>
          <div className="flex flex-row space-x-2">
            <input onClick={()=>{setMadd(false); setTieptuc(false)}} type="button" value="Hủy" className="rounded-md text-center bg-[#1890FF] h-[25px] w-[60px] text-[#ffffff] hover:bg-red-700"></input>
            {tieptuc && <input onClick={()=>{setMaddchoose(true); setMadd(false); setTieptuc(false)}} type="button" value="Tiếp tục" className="rounded-md text-center bg-[#1890FF] h-[25px] w-[80px] text-[#ffffff] hover:bg-red-700"></input>}
          </div>
        </div>
      </div>}

      {mchange && <div className="z-20 absolute drop-shadow-md shadow-inner top-[45%] left-[50%] flex flex-col space-y-2 justify-center items-center h-[84px] w-[104px] bg-white rounded-md shadow-2xl">
            <input onClick={()=>{setMchangeinf(true); setChange(false)}} type="button" value="Sửa" className="rounded-lg flex flex-row hover:bg-[#F4F5F7] w-[80%] h-[38%] justify-center items-center hover:text-[#1890FF]"></input>
            <input onClick={()=>{setMchitiet(true); setChange(false)}} type="button" value="chi tiet" className="rounded-lg flex flex-row hover:bg-[#F4F5F7] w-[80%] h-[38%] justify-center items-center hover:text-[#1890FF]"></input>
      </div>}

      {maddchoose && <Maddmember hidden={()=>setMaddchoose(false)} type={addchoose} />}
      {mchitiet && <Mchitiet hidden={()=>setMchitiet(false)} id={idct}/>}
      {mchangeinf && <Mchangeinf hidden={()=>setMchangeinf(false)} id={idct}/>}


{/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/}
{/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/}      
      
      <div className="z-10 absolute left-[18%] right-[3%] top-[18%] h-[80%]">
        <div className="flex flex-col h-[70%] mb-4">
          <div className="border-2 overflow-x-auto">
            <table className="w-full border-2">
              <thead>
                <tr className="flex flex-row border bg-[#F0F0F0] h-[40px] items-center justify-start">
                  <th className="border-x w-[50px]">Ide</th>
                  <th className="border-x flex justify-center items-center w-[50px]" >
                    <img src={Imgnhanbutoon} alt="anhhome"/>
                  </th>
                  <th className="border-x w-[300px]">Họ và tên</th>
                  <th className="border-x w-[300px]">Số điện thoại</th>
                  <th className="border-x w-[300px]">Email</th>
                  <th className="border-x w-[300px]">Địa chỉ</th>
                  <th className="border-x w-[300px]">Giới tính</th>
                  <th className="border-x w-[300px]">Ngày sinh</th>
                </tr>
              </thead>
              <tbody className="w-full">
                <div className="border-2 h-[60px] overflow-y-auto w-full">
                  <tr className="flex flex-row border items-center justify-start">
                    <td className="border-x flex justify-center items-center w-[50px]"></td>
                    <td className="border-x flex justify-center w-[50px]"></td>
                    <td className="border-x flex justify-start w-[300px]">
                      <input type="text" className="h-[35px] border-2 my-2" name="name" onChange={handleSeach} value={filter.name || ""} placeholder="Nhâp tên"/>
                    </td>
                    <td className="border-x flex justify-start w-[300px]">
                      <input type="text" className="h-[35px] border-2 my-2" name="phone" onChange={handleSeach} value={filter.phone || ""} placeholder="Nhâp sđt"/>
                    </td>
                    <td className="border-x flex justify-start w-[300px]">
                      <input type="text" className="h-[35px] border-2 my-2" name="email" onChange={handleSeach} value={filter.email || ""} placeholder="Nhâp email"/>
                    </td>
                    <td className="border-x flex justify-start w-[300px]">
                      <input type="text" className="h-[35px] border-2 my-2" name="address" onChange={handleSeach} value={filter.address || ""} placeholder="Nhâp address"/>
                    </td>
                    <td className="border-x flex justify-start w-[300px]">
                      <select name="sex" onChange={(e) => {setFilter(values => ({...values, [e.target.name]: e.target.value}))}}>
                        <option value="">All</option>
                        <option value={0}>Nữ</option>
                        <option value={1}>Nam</option>
                      </select>
                    </td>
                    <td className="border-x flex justify-start w-[300px]">
                      <input type="text" className="h-[35px] border-2 my-2" name="birthday" onChange={handleSeach} value={filter.birthday || ""} placeholder="birthday"/>
                    </td>
                  </tr>
                </div>
                <div className="border-2 h-[450px] overflow-y-auto w-full">
                  {data.map((item, index) => {
                    var sex=item.gender===0?"Nữ":"Nam";
                    return ( 
                      <tr className="flex flex-row border items-center justify-start">
                        <td className="border-x flex justify-center items-center w-[50px]">{(currentPage)*pageSize+(index+1)}</td>
                        <td className="border-x flex justify-center w-[50px]"> <button id={item.id} onClick={(event)=>{setChange(!mchange); setIdct(event.target.id)}}>Sửa</button> </td>
                        <td className="border-x flex justify-start w-[300px]">{item.fullName}</td>
                        <td className="border-x flex justify-start w-[300px]">{item.phone}</td>
                        <td className="border-x flex justify-start w-[300px]">{item.email}</td>
                        <td className="border-x flex justify-start w-[300px]">{item.address}</td>
                        <td onClick={() => {}} className="border-x flex justify-start w-[300px]">{sex}</td>
                        <td className="border-x flex justify-start w-[300px]">{item.birthday}</td>
                      </tr>
                    )
                  })}
                </div>
              </tbody>
            </table>
          </div>
        </div>

        <button className="p-2 border" onClick={() => {setCurrentPage(()=>(currentPage>=1?(currentPage-1):currentPage));}}>Trươc</button>
          {Hienbutton(currentPage)}
        <button className="p-2 border" onClick={() => {setCurrentPage(()=>(currentPage<(totalmem-1)?(currentPage+1):currentPage));}}>Sau</button>

        <div>Số bản ghi 1 trang : {pageSize}</div>
        <select
          onChange={(e) => {
            setPageSize(e.target.value);
          }}
        >
          <option value={5}>5</option>
          <option value={10}>10</option>
          <option value={15}>15</option>
          <option value={20}>20</option>
        </select>
      </div>
  </>
  );
};

export default Qttv;
