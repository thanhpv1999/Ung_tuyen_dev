import * as httpRequest from "../utils/httpRequest";
export const listBook = async () => {
  try {
    const res = await httpRequest.get("list-location");
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
