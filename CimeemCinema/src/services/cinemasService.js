import * as httpRequest from "../utils/httpRequest";
export const listBook = async (id) => {
  try {
    const res = await httpRequest.get(`list-cinemas-id?id=${id}`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const listCinme = async () => {
  try {
    const res = await httpRequest.get(`list-cinemas-id`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
