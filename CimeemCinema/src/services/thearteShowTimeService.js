import * as httpRequest from "../utils/httpRequest";
export const listBook = async () => {
  try {
    const res = await httpRequest.get("list-threate-show-time");
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
export const create = async (
  idThe,
  idThe2,
  idThe3,
  idThe4,
  idThe5,
  idThe6,
  idThe7,
  idThe8
) => {
  try {
    const res = await httpRequest.post(
      `saveTheToTime?idThe=${idThe}&idThe2=${idThe2}&idThe3=${idThe3}&idThe5=${idThe4}&idThe4=${idThe5}&idThe6=${idThe6}&idThe7=${idThe7}&idThe8=${idThe8}`
    );
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};
