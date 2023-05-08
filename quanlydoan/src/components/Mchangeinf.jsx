import React from "react";
import { useState } from "react";
import { useEffect} from "react";
import request from "../utils/request";

const Maddmember = (props) =>{
    const [inputs, setInput] = useState({});
    const [img1, setImg1] = useState("");
    const [postimg, setPostimg] = useState('');
    const [create, setCreate] = useState(false);


    useEffect(() =>{
        console.log(inputs)
        addpress();
    },[create])

    useEffect(() =>{
        request({
            url: "/api/admin/user/" + props.id,
            method: "GET"
        }).then((response)=>{
            let rsp = response.data;
            viewimg(rsp.avatar);
        })
    },[])

    const viewimg = (path) =>{
        request({
            url:"/api/file/view/" + path,
            method: "GET",
            responseType: "blob"
        }).then((response)=>{
            const url = URL.createObjectURL(response.data)
            setImg1(url)
        })
    }

    const handleChang = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInput(values => ({...values, [name]: value}))   //Spread Operator
    }

    const handleChangeFile = (file) => {
        let fileData = new FileReader();
        fileData.readAsDataURL(file);
        return  fileData.onloadend = ()=>{
            const url = fileData.result
            setImg1(url);
        }
    }

    const apiuploadimg = () =>{
        let from = new FormData();
        from.append("upload", postimg);
        request({
            headers: { "Content-Type": "multipart/form-data" },
            url: "/api/file/upload",
            method: "POST",
            data: from
        }).then((response) => {
            setInput(()=>({...inputs, "avatar": response.data.pathOnServer}))   //Spread Operator
            setCreate(!create);
        })   
    }

    const addpress = () =>{
        request({
            headers: {"Content-Type": "application/json"},
            url: "/api/admin/user/" + props.id,
            method: "PUT",
            data: JSON.stringify(inputs),
        }).then((response) => {
            console.log(response);  
        })   
    }

    return(
        <div>
            <div className="z-20 absolute left-[0px] top-[0px] w-full h-screen bg-[#42526E] opacity-[40%]"></div>
            <div className="z-30 absolute flex flex-col space-y-2 drop-shadow-md shadow-inner top-[6%] h-[90%] left-[18%] right-[18%] space-y-2 justify-start items-between bg-white rounded-md shadow-2xl px-[3%] py-[1%]">
                <div>{props.type==="0"?"Thêm quản lý":props.type==="1"?"Thêm giảng viên":"Thêm sinh viên"}</div>
                <hr className="w-full"></hr>
                <div className="flex flex-row items-between justify-center h-[20%] w-full">
                    <div className="w-[50%]">
                        <input onChange={(e)=>{setPostimg(e.target.files[0]); handleChangeFile(e.target.files[0])}} type="file" id="img" required/>
                        <img src={img1} alt="chưa có ảnh" className="h-[80px] drop-shadow-md"/>
                    </div>
                    <div className="flex flex-col items-between justify-center space-y-2 w-[50%]">
                        <input onChange={handleChang} value={inputs.username || ""} className="p-2 border-2 rounded-md" name="username" type="text" placeholder = {"Nhập tài khoản"}></input>
                        <input onChange={handleChang} value={inputs.password || ""} className="p-2 border-2 rounded-md" name="password" type="text" placeholder = {"Nhập mật khẩu"}></input>
                    </div>
                </div>
                <div className="flex flex-row items-start space-x-20 justify-start h-[30%] w-full">
                    <div className="flex flex-col items-between justify-center space-y-2 w-[40%]">
                        <input onChange={handleChang} value={inputs.fullName || ""} className="p-2 border-2 rounded-md" name="fullName" type="text" placeholder = {"Họ và tên"}></input>
                        <input onChange={handleChang} value={inputs.birthday || ""} className="p-2 border-2 rounded-md" name="birthday" type="date" placeholder = {"Ngày sinh"}></input>
                        <input onChange={handleChang} value={inputs.email || ""} className="p-2 border-2 rounded-md" name="email" type="text" placeholder = {"Số email"}></input>
                    </div>
                    <div className="flex flex-col items-between justify-center space-y-2 w-[40%]">
                        <input onChange={handleChang} value={inputs.phone || ""} className="p-2 border-2 rounded-md" name="phone" type="text" placeholder = {"Nhập số điện thoại"}></input>
                        {/* <input onChange={handleChang} value={inputs.gender || ""} className="p-2 border-2 rounded-md" name="gender" type="text" placeholder = {"Giới tính"}></input> */}
                        <div className="flex flex-row justify-between items-center w-full">
                            <div>Giới tính:</div>
                            <div> <input onChange={handleChang} type="radio" name="gender" value={1} /> Nam </div>
                            <div> <input onChange={handleChang} type="radio" name="gender" value={0} /> Nữ </div>
                            <div> <input onChange={handleChang} type="radio" name="gender" value={2} /> Khác </div>
                        </div>
                    </div>
                </div>
                <input onChange={handleChang} value={inputs.address || ""} className="p-2 border-2 rounded-md" name="address" type="text" placeholder = {"Địa chỉ"}></input>
                <input onChange={handleChang} value={inputs.type || ""} className="p-2 border-2 rounded-md" name="type" type="text" placeholder = {"Học và làm việc tại"}></input>
                <input onChange={handleChang} value={inputs.note || ""} className="p-2 border-2 rounded-md" name="note" type="text" placeholder = {"Ghi chú"}></input>
                <div className="flex flex-row space-x-2">
                    <input type="button" onClick={props.hidden} value="Hủy" className="p-2 border-2 rounded-md"></input>
                    <input type="button" onClick={apiuploadimg} value="Thêm" className="p-2 border-2 bg-blue-700 rounded-md"></input>
                </div>
            </div>
        </div>
    )
}

export default Maddmember;