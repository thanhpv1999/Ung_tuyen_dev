import * as httpRequest from "../utils/httpRequest";
export const listOrder = async () => {
  const id = "3a045b5f-413e-417f-bdac-f9111e45e995";
  try {
    const res = await httpRequest.post(`adorder?salesman_id=${id}`);
    // console.log(res.data);
    return res.data.content;
  } catch (error) {
    console.log(error);
  }
};
export const cancel = async (id) => {
  try {
    const res = await httpRequest.get(`cancel?userId=${id}`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const confirm = async (id) => {
  try {
    const res = await httpRequest.get(`confirm?userId=${id}`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const print = async (id) => {
  try {
    const res = await httpRequest.get(`print?userId=${id}`);
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
