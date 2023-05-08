import * as httpRequest from "../utils/httpRequest";
export const listBook = async (idThe, idRoom) => {
  try {
    const res = await httpRequest.get(
      `list-time?idThe=${idThe}&idRoom=${idRoom}`
    );
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const saveTime = async (time) => {
  try {
    const res = await httpRequest.post("saveTime", {
      time: time,
    });
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const listTime = async () => {
  try {
    const res = await httpRequest.get("listTime");
    // console.log(res.data);
    return res.data.content;
  } catch (error) {
    console.log(error);
  }
};
