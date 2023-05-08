import * as httpRequest_cus from "../utils/httpRequest";
export const listBook = async () => {
  try {
    const res = await httpRequest_cus.get_cus("about");
    // console.log(res.data[0].description);
    return res.data[0].description;
  } catch (error) {
    console.log(error);
  }
};
