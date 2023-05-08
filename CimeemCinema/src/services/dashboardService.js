import * as httpRequest from "../utils/httpRequest";
const salesman_id = "eb7bdf9e-da0c-4cec-b471-1f74aeadcab3";
export const search = async () => {
  try {
    const res = await httpRequest.post(
      `count-order?salesman_id=${salesman_id}`
    );
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const sales = async () => {
  try {
    const res = await httpRequest.post(
      `sales-order?salesman_id=${salesman_id}`
    );
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const countProduct = async () => {
  try {
    const res = await httpRequest.get("count-product");
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
