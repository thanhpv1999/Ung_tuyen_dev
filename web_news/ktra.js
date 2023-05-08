var data_product = [ 

]

var modalbvct = document.getElementById("bvctmodal");
var modalbvctwork = document.getElementById("bvctmodalwork");

function reportWindowSize() 
{
    var screen = document.getElementById("testtn");
    if(window.innerWidth < 900)
    {
        screen.className ="space-y-4 justify-center items-center w-full h-[250px] overflow-scroll pr-6";
    }else{
        screen.className ="flex flex-row justify-between items-center w-full h-[250px]";
    }
}
window.onresize = reportWindowSize;

function ReadmyCV()
{
    document.getElementById("thanhmodal").style.visibility = "visible"; 
}

function ButtonCancel()
{
    document.getElementById("inp_create_id").value = "";
    document.getElementById("inp_create_name").value ="";
    document.getElementById("inp_create_desc").value = ""; 
    document.getElementById("inp_create_price").value = "";
    document.getElementById("thanhmodal").style.visibility = "hidden"; 
}

function ButtonCreateDone()
{
    var idn = document.getElementById("inp_create_id").value;
    var namen = document.getElementById("inp_create_name").value;
    var descriptionn = document.getElementById("inp_create_desc").value;
    var pricen = document.getElementById("inp_create_price").value;

    var newProduct = { "nl":idn, "tn": namen, "cm": descriptionn, "yt": pricen };
    data_product.push(newProduct);

    document.getElementById("inp_create_id").value = "";
    document.getElementById("inp_create_name").value ="";
    document.getElementById("inp_create_desc").value = ""; 
    document.getElementById("inp_create_price").value = "";

    document.getElementById("thanhmodal").style.visibility = "hidden"; 

    alert("Cảm ơn đã đặt câu hỏi!");
}

function ReadmyCV_after()
{
    document.getElementById("thanhmodal_1").style.visibility = "visible"; 
}

function Done()
{
    document.getElementById("inp_create").value = "Write something here";
    document.getElementById("thanhmodal_1").style.visibility = "hidden"; 
    alert("Cảm ơn ý kiến của bạn!");
}


//-------------------------------------------------------------------------------------------
var blogs = [ 
    { "head":"thanh 1", "year": 2022, "type": "This is example 1", "synop": "This is example 1 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 2", "year": 2021, "type": "This is example 1", "synop": "This is example 2 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 3", "year": 2020, "type": "This is example 1", "synop": "This is example 3 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 4", "year": 2019, "type": "This is example 1", "synop": "This is example 4 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 5", "year": 2018, "type": "This is example 1", "synop": "This is example 5 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 6", "year": 2017, "type": "This is example 1", "synop": "This is example 6 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 7", "year": 2019, "type": "This is example 1", "synop": "This is example 7 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 8", "year": 2018, "type": "This is example 1", "synop": "This is example 8 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
    { "head":"thanh 9", "year": 2017, "type": "This is example 1", "synop": "This is example 9 dfghjdjcvjxc vbxcvbxcv hjxchjdfghdfghdfghdfgvbnm,dhjkfghjkg"} ,
]


function viewBlog()
{
    var cnt = 0;
    var i = 0;
    var blog = document.getElementById("blogs-main");
    for(var ele of blogs)
    {
        i++;
        if(cnt == 2) cnt = 0;
        if(cnt == 0)
        {
            var newDiv = document.createElement("div");
            newDiv.className = "flex flex-row space-x-4 justify-center items-center w-full h-auto";
        }
        cnt++;
        newDiv.append(CreateBlog(ele));
        if(blogs[i] == undefined)
        {
            var newBv = document.createElement("div");
            newBv.className = "w-full pl-4";
            newDiv.appendChild(newBv);
        }
        blog.appendChild(newDiv);
    }
}

function CreateBlog(ele)
{
    var newBv = document.createElement("div");
    newBv.className = "bg-white w-full h-[150px] pl-4 rounded-md overflow-hidden hover:bg-green-100";
    newBv.onclick = function() { modalbvct.style.visibility = "visible"; viewContent("section_bvct");} 

    var newHead = document.createElement("p");
    newHead.className = "text-1xl font-bold break-words";
    newHead.appendChild(document.createTextNode(ele.head));

    var newYear = document.createElement("span");
    newYear.className = "px-2 text-white bg-black rounded-lg font-bold break-words";
    newYear.appendChild(document.createTextNode(ele.year));

    var newType = document.createElement("span");
    newType.appendChild(document.createTextNode(ele.type));

    var newSynop = document.createElement("p");
    newSynop.className = "mb-2 break-words";
    newSynop.appendChild(document.createTextNode(ele.synop));

    var bvct = document.createElement("button");
    bvct.className ="font-bold text-red-700";
    bvct.textContent ="điều hướng"; 
    bvct.onclick=function() { modalbvct.style.visibility = "visible"; viewContent("section_bvct");}

    newBv.appendChild(newHead);
    newBv.appendChild(newYear);
    newBv.appendChild(document.createTextNode("  |  "));
    newBv.appendChild(newType);
    //newBv.appendChild(document.createTextNode("  |  "));
    //newBv.appendChild(bvct); 
    newBv.appendChild(document.createElement("br"));
    newBv.appendChild(newSynop);  

    return newBv;
}

//--------------------------------------------------------------------------------------------
var works = [ 
    { "head":"thanh van 1", "year": 2022, "type": "This is example 1", "synop": "This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg"} ,
    { "head":"thanh van 2", "year": 2021, "type": "This is example 1", "synop": "This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg"} ,
    { "head":"thanh van 3", "year": 2020, "type": "This is example 1", "synop": "This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg"} ,
    { "head":"thanh van 4", "year": 2019, "type": "This is example 1", "synop": "This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg This is example 1 dfghjdjcvjxc vbxcvbxcv hjxch jdfghdfg hdfghdf gvb nm,dhjk fghjkg"} ,
    { "head":"thanh van 5", "year": 2018, "type": "This is example 1", "synop": "This is example 5 dfghjdjcvjxc vbxcvbxcv hjxchjdfg hdfghdfg hdfgvb nm,dhjkf ghjkg"} ,
    { "head":"thanh van 6", "year": 2017, "type": "This is example 1", "synop": "This is example 6 dfghjdjcvjxc vbxcvbxcv hjxchj dfghd fghdf ghdfg v bnm,dhj kfghjkg"} ,
    { "head":"thanh van 7", "year": 2019, "type": "This is example 1", "synop": "This is example 7 dfghjdjcvjxc vbxcvbxcv hjxchj dfghdfg hdfghdfgvbnm,d hjk fghjkg"} ,
    { "head":"thanh van 8", "year": 2018, "type": "This is example 1", "synop": "This is example 8 dfghjdjcvjxc vbxcvbxcv hjxchjdf ghdfghdfg hdfgvbnm, dhjkf ghjkg"} ,
    { "head":"thanh van 9", "year": 2017, "type": "This is example 1", "synop": "This is example 9 dfghjdjcvjxc vbxcvbxcv hjxchj dfg hdfghdf ghdfgvbn m,dhjk fghjkg"} ,
    { "head":"thanh van 9", "year": 2017, "type": "This is example 1", "synop": "This is example 9 dfghjdjcvjxc vbxcvbxcv hjxchjdfg hdfghdf ghdfgvbnm ,dhjkf ghjkg"} ,
]

function viewWork()
{
    var work = document.getElementById("works-main");
    for(var ele of works)
    {
        work.append(CreateWork(ele));
        var newHr = document.createElement("hr");
        newHr.className = "w-full my-2";
        work.appendChild(newHr);
    }
}

function CreateWork(ele)
{
    var newBv = document.createElement("div");
    newBv.className = "flex flex-row items-center justify-start space-x-4 h-[170px] w-full rounded-lg hover:bg-red-100";
    newBv.onclick=function() { modalbvctwork.style.visibility = "visible"; viewContent("section_bvctwork");}

    var newImg = document.createElement("img");
    newImg.className = "rounded-md h-[150px] w-[150px]";    
    newImg.src = "./static/a1_0.jpg";

    var newDiv = document.createElement("div");
    newDiv.className = "rounded-md h-[150px] w-full overflow-hidden";

    var newHead = document.createElement("p");
    newHead.className = "text-1xl font-bold mb-2 break-words";
    newHead.appendChild(document.createTextNode(ele.head));

    var newYear = document.createElement("span");
    newYear.className = "px-2 text-white bg-black rounded-lg font-bold break-words";
    newYear.appendChild(document.createTextNode(ele.year));

    var newType = document.createElement("span");
    newType.appendChild(document.createTextNode(ele.type));

    var newSynop = document.createElement("p");
    newSynop.className = "mb-2 break-words w-full";
    newSynop.appendChild(document.createTextNode(ele.synop));

    var bvct = document.createElement("button");
    bvct.className ="font-bold text-red-700";
    bvct.textContent ="điều hướng"; 
    bvct.onclick=function() { modalbvctwork.style.visibility = "visible"; viewContent("section_bvctwork");}

    newDiv.appendChild(newHead);
    newDiv.appendChild(newYear);
    newDiv.appendChild(document.createTextNode("  |  "));
    newDiv.appendChild(newType);
    // newDiv.appendChild(document.createTextNode("  |  "));
    // newDiv.appendChild(bvct);
    newDiv.appendChild(document.createElement("br"));
    newDiv.appendChild(newSynop); 

    newBv.appendChild(newImg);
    newBv.appendChild(newDiv);

    return newBv;
}


//-----------------------------------------------------------------------------------
function viewContent(id)
{

    var vb1 = "Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng.";
    var vb2 = "Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam. Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam. Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam. Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam. Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam.Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam.Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam.Sau khi nghiên cứu Điều lệ Công đoàn Việt Nam,  tôi tán thành và tự nguyện làm đơn này gửi đến BTV Công đoàn Công ty CP Bóng đèn Phích nước Rạng Đông  để đề nghị xin được kết nạp vào tổ chức Công đoàn Việt Nam.";
    var vb3 = "Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng. Sau khi. Sau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng. Sau khiSau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng. Sau khiSau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng. Sau khiSau khi được kết nạp đoàn viên công đoàn, tôi xin hứa sẽ tích cực tham gia vào các hoạt động của tổ chức, thực hiện đầy đủ nhiệm vụ của người Đoàn viên công đoàn & đóng đoàn phí hàng tháng. Sau khi";

    var body = document.getElementById(id);

    var newh1 = document.createElement("h1");
    newh1.className = "text-3xl font-bold inline";
    newh1.appendChild(document.createTextNode("Pham Van Thannh Pham Van Thannh"));

    var bvct = document.createElement("button");
    bvct.className ="fixed top-[100px] font-bold text-white ml-[500px] h-8 w-[100px] border border-2 border-solid rounded-lg bg-black";
    bvct.textContent ="điều hướng"; 
    if(id == "section_bvctwork")
    {
        bvct.onclick=function() {modalbvctwork.style.visibility = "hidden";}
    }

    else if(id == "section_bvct")
    {
        bvct.onclick=function() {modalbvct.style.visibility = "hidden";}
    }
    
    var newh2 = document.createElement("h2");
    newh2.className = "text-2xl mt-2     "

    var newYear = document.createElement("span");
    newYear.className = "px-2 text-center text-white bg-black rounded-lg break-words";
    newYear.appendChild(document.createTextNode("2021"));
    newh2.appendChild(newYear);

    newh2.appendChild(document.createTextNode("   |    Customer Management"));

    var newp1 = document.createElement("p");
    newp1.appendChild(document.createTextNode(vb1));

    var newimg = document.createElement("img");
    newimg.className = "mx-auto w-[250px] h-[250px]";
    newimg.src = "./static/download.jpg";

    var newp2 = document.createElement("p");
    newp2.appendChild(document.createTextNode(vb2));

    var newh11 = document.createElement("h1");
    newh11.className = "text-3xl font-bold";
    newh11.appendChild(document.createTextNode("Heading 2"));

    var newp3 = document.createElement("p");
    newp3.appendChild(document.createTextNode(vb3));

    var newul = document.createElement("ul");
    newul.className = "list-disc ml-10 mb-10";
    var newli1 = document.createElement("li");
    newli1.appendChild(document.createTextNode("pham"));

    var newli2 = document.createElement("li");
    newli2.appendChild(document.createTextNode("van"));

    var newli3 = document.createElement("li");
    newli3.appendChild(document.createTextNode("thanh"));

    newul.appendChild(newli1);
    newul.appendChild(newli2);
    newul.appendChild(newli3);

    body.appendChild(newh1);
    body.appendChild(bvct);
    body.appendChild(document.createElement("br"));
    body.appendChild(newh2);
    body.appendChild(document.createElement("br"));
    body.appendChild(newp1);
    body.appendChild(document.createElement("br"));
    body.appendChild(newimg);
    body.appendChild(document.createElement("br"));
    body.appendChild(newp2);
    body.appendChild(document.createElement("br"));
    body.appendChild(newh11);
    body.appendChild(document.createElement("br"));
    body.appendChild(newp3);
    body.appendChild(document.createElement("br"));
    body.appendChild(document.createElement("hr"));
    body.appendChild(newul);
    body.appendChild(document.createElement("br"));
}