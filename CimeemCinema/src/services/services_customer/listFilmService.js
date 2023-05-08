import * as httpRequest_cus from "../../utils/httpRequest";
import request from "../../utils/request";

export const listFilmNow = async () => {
  try {
    const res = await httpRequest_cus.get_cus("listTheatreShowTimesNow");
    console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

export const getAbout = async () => {
  try {
    const res = await httpRequest_cus.get_cus("about");
    console.log(res.data);
    return res.data[0];
  } catch (error) {
    console.log(error);
  }
};

export const chosseFilm = (func) => {
  request({
    url: "/listTheatreShowTimesNow",
    method: "GET",
  })
  .then((res) => {
    console.log(res.data[0].movieId);
    func(res)
  })
  .catch(()=>{
      console.log("loi phim");
  });
};

export const filmTomo = (func) => {
  request({
    url: "/listTheatreShowTimesTomorrow",
    method: "GET",
  })
  .then((res) => {
    func(res)
  })
  .catch(()=>{
      console.log("loi phim sc");
  });
};

export const qc = (func) => {
  request({
    url: "/admin/listheader",
    method: "GET",
  })
  .then((res) => {
    func(res)
  })
  .catch(()=>{
      console.log("loi qc");
  });
};




export const chosseTheatre = (Id, func) => {
  console.log("loi rap: " + Id);
  request({
    url: "/listTheatre/" + Id,
    method: "GET",
  })
  .then((res) => {
    console.log(res.data[0][0]);
    func(res);
  })
  .catch(()=>{
      console.log("loi rap");
  });
};  

export const chosseTime = (movieId, Id, func) => {
  console.log("loi lich: " + Id +"&" + movieId);
  request({
    url: "/listTime/" + Id + "&" + movieId,
    method: "GET",
  })
  .then((res) => {
    console.log(res.data);
    func(res)
  })
  .catch(()=>{
      console.log("loi rap");
  });
}; 
