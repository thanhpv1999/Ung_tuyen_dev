import * as httpRequest from "../utils/httpRequest";
export const listBook = async (id) => {
  try {
    const res = await httpRequest.get(`list-room-by-cinema?name=${id}`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
