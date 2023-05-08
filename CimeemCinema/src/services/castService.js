import * as httpRequest from "../utils/httpRequest";
export const listBook = async () => {
  try {
    const res = await httpRequest.get("cast");
    // console.log(res.data);
    return res.data.content;
  } catch (error) {
    console.log(error);
  }
};
