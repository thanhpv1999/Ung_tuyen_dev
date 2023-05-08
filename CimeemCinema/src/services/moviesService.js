import * as httpRequest from "../utils/httpRequest";
export const listBook = async () => {
  try {
    const res = await httpRequest.get("movie");
    // console.log(res.data);
    return res.data.content;
  } catch (error) {
    console.log(error);
  }
};

export const uploadImg = async (img) => {
  try {
    const res = await httpRequest.post("uploadimg", img, {
      headers: {
        "Content-Type": "multipart/form-data",
      },
      mode: "no-cors",
    });
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

// {
//   "title": "The Real Ghost 02",
//   "description": "ok",
//   "durationMin": 50,
//   "rate": {
//      "rateId": "9a3d3a86-1873-4129-acfc-ab4ef6ceca90"
//   },
//   "pathImg": "https://static.bunnycdn.ru/i/cache/images/7/7c/7c3a3ab44d0d69a09d4f5d5a2158603a.jpg-w380",
//   "urlTrailer": "https://youtu.be/Ru4Jbmh7bcQ",
//   "startUpDate": "2023-02-28",
//   "languagess": {
//       "languageId":"f108f7c8-62ee-45ae-b1a6-0cc372d96359"
//   },
//   "dateAired": "2023-02-28"
// }
export const createMovie = async (
  title,
  description,
  durationMin,
  rateId,
  pathImg,
  urlTrailer,
  startUpDate,
  languageId,
  dateAired,
  values,
  actor,
  director,
  location
) => {
  try {
    const res = await httpRequest.post(
      `saveProduct?values=${values}&actor=${actor}&director=${director}&location=${location}`,
      {
        title: title,
        description: description,
        durationMin: durationMin,
        rate: {
          rateId: rateId,
        },
        pathImg: pathImg,
        urlTrailer: urlTrailer,
        startUpDate: startUpDate,
        languagess: {
          languageId: languageId,
        },
        dateAired: dateAired,
      }
    );
    // console.log(res.data);
    return res.data;
  } catch (error) {
    console.log(error);
  }
};

export const deleteMovie = async (i) => {
  try {
    const res = await httpRequest.post(`deleteProduct?id=${i}`);
    console.log(res.data);
    return res;
  } catch (error) {
    console.log(error);
  }
};
