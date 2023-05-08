import React, { useEffect, useState } from "react";
import request from "../utils/request";
import Imgnhanbutoon from "./Img/danhmuc/Nhanbutton.png"

const Quanlydoan = () => {
  const [data, setData] = useState([]);
  const [dataPaginate, setDataPaginate] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [pageSize, setPageSize] = useState(3);
  const [totalPage, setTotalPage] = useState(0);
  const [filter, setFilter] = useState({
    name: ""
  })

  const [madd, setMadd] = useState(false);
  const [mchangeoradd, setMchangeoradd] = useState(false)
  const [mchange, setChange] = useState(false)
  const [inputsadd, setInputadd] = useState("");
  const [dataEdit, setDataEdit] = useState();

  const AddInfor = () =>{
    // request({
    //   url: "/api/field" ,
    //   method: "POST",
    //   params: {
    //     name: inputsadd.name,
    //     code: inputsadd.code,
    //   }
    // }).then(Response=>{
    //   console.log(Response);
    //   if(Response.request.status === 200){
    //     setMadd(false)
    //   }
    // }).catch(e=>{
    //   console.log("loi post api: " + e)
    // })
  }

  const DeletInfor = (id) =>{
    // request({
    //     url: "/api/field/" + id,
    //     method: "DELETE",
    //   }).then(Response=>{
    //   console.log(Response);
    //   if(Response.request.status === 200){
    //     setChange(false)
    //   }
    // }).catch(e=>{
    //   console.log("loi post api: " + e)
    // })
  }

  const ChangeInfor = (id) =>{
    // request({
    //     url: "/api/field/" + id,
    //     method: "PUT",
    //     params: {
    //       name: inputsadd.name,
    //       code: inputsadd.code,
    //     }
    //   }).then(Response=>{
    //   console.log(Response);
    //   if(Response.request.status === 200){
    //     setMadd(false)
    //   }
    // }).catch(e=>{
    //   console.log("loi post api: " + e)
    // })
  }

  const handleAdd = (event) => {
    const name = event.target.name;
    const value = event.target.value;
    setInputadd(values => ({...values, [name]: value}))   //Spread Operator
  } 


  useEffect(() => {
    request({
      url: "/api/project-group",
      method: "GET",
    }).then((response) => {
      console.log(response);
      let responseData = response.data;
      setData(responseData);
      
      const ttPage = Math.ceil(response.data.length / pageSize);    //chia dữ liệu theo trang với kích cỡ trang được chọn trước đó
      setTotalPage(ttPage);                                         //Lưu lại tổng số page sau khi phân trang
    });
  },[mchange, pageSize]);                                      //Các biến thay đổi sẽ gọi lại api

  useEffect(() => {
    // page 1: ( 0 - 10 )
    // page 2: ( 10 - 20 )
    const start = (currentPage - 1) * pageSize;
    const end = pageSize * currentPage;
    // Do Filter
    var cloneData = [...data];
    console.log("js9uidhfúidhfúid"+cloneData)
    console.log("asduojioasjdiaws: ---"+filter.name)
    if(filter.name !== ""){
      cloneData = cloneData.filter(item => item.id.includes(filter.name));
    }
    const ttPage = Math.ceil(cloneData.length / pageSize);
    setTotalPage(ttPage);
    // End Do filter
    const result = cloneData.slice(start, end);
    // setDataPaginate(result);
  }, [data, currentPage, pageSize, filter, madd, mchange]);

  const Hienbutton =(id)=>{
    if((id >= (currentPage-1))&&(id <= (currentPage+1))){
      return(
        <button className="p-2 border" onClick={() => {setCurrentPage(id + 1);}} style={{backgroundColor: currentPage === id + 1 && "red",}}>{id + 1}</button>
      )
    }
  }

  return (
    <>
      <div style={{boxShadow: '0px 0px 10px #E5E5E5'}} className="z-0 absolute flex flex-row top-[10%] left-[15%] right-[0] h-[6%] pl-[2%] items-center justify-between pr-[3%]">
        <div className="text-xl">Ngành nghề</div>
        <input onClick={()=>{setMadd(true); setMchangeoradd(false)}} type="button" value="+ Thêm ngành nghề" className="text-center bg-[#1890FF] h-[25px] w-[15%] text-[#ffffff] hover:bg-red-700"></input>
      </div>

      {madd && <div>
        <div className="z-20 absolute left-[0px] top-[0px] w-full h-screen bg-[#42526E] opacity-[40%]" onClick={()=>setMadd(false)}></div>
        <div className="z-20 absolute flex flex-col space-y-4 left-[15%] top-[20%] w-[70%] h-[300px] bg-[#FFFFFF]">
          {!mchangeoradd && <div>Thêm ngành nghề</div>}
          {mchangeoradd && <div>Sửa ngành nghề</div>}
          <hr className="w-full"></hr>
          <div className="flex flex-row justify-between items-center w-[50%]">
            <div className="flex flex-col space-y-2 w-[200px] text-lg">
                <label>Mã ngành nghề</label>
                <input onChange={handleAdd} name="code" value={inputsadd.code || ""} type="text" placeholder = {"Nhập Mã..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
            </div>
            <div className="flex flex-col space-y-2 w-[200px] text-lg">
                <label >* Ngành nghề</label>
                <input onChange={handleAdd} name="name" value={inputsadd.name || ""} type="text" placeholder = {"Nhập ngành nghề..."} className="border-2 w-[350px] h-[30px] pl-[5px]"></input>
            </div>
          </div>
          <hr className="w-full"></hr>
          <div className="flex flex-row space-x-2">
            <input onClick={()=>setMadd(false)} type="button" value="Hủy" className="rounded-md text-center bg-[#1890FF] h-[25px] w-[60px] text-[#ffffff] hover:bg-red-700"></input>
            {!mchangeoradd && <input onClick={AddInfor} type="button" value="Thêm" className="rounded-md text-center bg-[#1890FF] h-[25px] w-[60px] text-[#ffffff] hover:bg-red-700"></input>}
            {mchangeoradd && <input onClick={()=>ChangeInfor(dataEdit.id)} type="button" value="Sửa" className="rounded-md text-center bg-[#1890FF] h-[25px] w-[60px] text-[#ffffff] hover:bg-red-700"></input>}
          </div>
        </div>
      </div>}

      {mchange && <div className="z-20 absolute drop-shadow-md shadow-inner top-[45%] left-[50%] flex flex-col space-y-2 justify-center items-center h-[84px] w-[104px] bg-white rounded-md shadow-2xl">
            <input onClick={()=>{setMadd(true); setChange(false); setMchangeoradd(true)}} type="button" value="Sửa" className="rounded-lg flex flex-row hover:bg-[#F4F5F7] w-[80%] h-[38%] justify-center items-center hover:text-[#1890FF]"></input>
            <input type="button" value="Xóa" onClick={()=>{DeletInfor(dataEdit.id)}} className="rounded-lg flex flex-row hover:bg-[#F4F5F7] w-[80%] h-[38%] justify-center items-center hover:text-[#1890FF]"></input>
      </div>}

{/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/}
{/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/}      
      
      <div className="z-10 absolute left-[18%] right-[3%] top-[18%] h-[80%]">
        <div className="h-[70%]">
        <table className="w-full border-2">
          <tr className="border bg-[#F0F0F0] h-[40px]">
            <th className="border w-[10%]">Ide</th>
            <th className="flex justify-center items-center" >
              <img src={Imgnhanbutoon} alt="anhhome" className="mt-[8%]" />
            </th>
            <th className="border w-[36%]">MÃ ngành nghề</th>
            <th className="border w-[40%]">Tên ngành nghề</th>
          </tr>
          <input placeholder="Nhâp tên" onChange={(e)=>{const input = e.target.value;setFilter({...filter, name: input})}}/>
          {dataPaginate.map((item, index) => (
            <tr className="border h-[40px]">
              <td className="border text-center">{(currentPage-1)*pageSize+(index+1)}</td>
              <td className="border text-center"> <button onClick={()=>{setChange(!mchange); setDataEdit(item)}}>Sửa</button> </td>
              <td className="border pl-[5%]">{item.id}</td>
              <td className="border pl-[5%]">{item.createdBy}</td>
            </tr>
          ))}
        </table>
        </div>

        <button className="p-2 border" onClick={() => {setCurrentPage(()=>{var temp=currentPage>1?(currentPage-1):currentPage; return temp});}}>Trươc</button>
        {[...Array(totalPage)].map((item, index) => (     // [...Array(totalPage)]: Tạo 1 mảng với độ dài là totalPage và các phần tử không có dữ liệu hoặc các phần tử có giá trị null hoặc undefine
          // <button className="p-2 border" onClick={() => {setCurrentPage(index + 1);}} style={{backgroundColor: currentPage === index + 1 && "red",}}>{index + 1}</button>
          Hienbutton(index)
        ))}
        <button className="p-2 border" onClick={() => {setCurrentPage(()=>{var temp=currentPage<totalPage?(currentPage+1):currentPage; return temp});}}>Sau</button>

        <div>Trang hiện tại: {currentPage}</div>
        <div>Số bản ghi 1 trang : {pageSize}</div>
        <select
          onChange={(e) => {
            setPageSize(e.target.value);
          }}
        >
          <option value={3}>3</option>
          <option value={4}>4</option>
          <option value={5}>5</option>
          <option value={6}>6</option>
        </select>
    </div>
  </>
  );
};

export default Quanlydoan;
