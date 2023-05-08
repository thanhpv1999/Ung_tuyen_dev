import React from "react"; 
import flagred from "./Img/main layout/avatar1.png"
import bell from "./Img/main layout/bell.png"
import avatar from "./Img/main layout/avatar.png"
import { useState } from "react";
import Mqltk from "./Mqltk";

const Header = () =>{
    
    const [mqltk, setMqltk] = useState(false);
    
    return(
        <div className="z-20 fixed flex flex-row left-[15%] top-0 w-[85%] justify-between items-start px-[2%] pt-[1%]">
            <div className="flex flex-row text-2xl">Trường Đại học A</div>
            <div className="flex flex-row space-x-6 items-center mr-[5%]">
                <img src={flagred} alt="anhhome" className="h-[26px]" />
                <img src={bell} alt="anhhome" className="h-[26px]" />
                <img onClick={()=>setMqltk(!mqltk)} src={avatar} alt="anhhome" className="h-[26px] hover:" />
            </div>
            {mqltk && <Mqltk />}
        </div>
    )

}

export default Header