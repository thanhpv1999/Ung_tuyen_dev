import axios from "axios";

const httpRequest = axios.create({
  baseURL: process.env.REACT_APP_BASE_URL,
});

const httpRequest_cus = axios.create({
  baseURL: process.env.REACT_APP_BASE_URL_CUS,
});

export const post = async (path, options = {}) => {
  const response = await httpRequest.post(path, options);
  return response;
};
export const get = async (path, options = {}) => {
  const response = await httpRequest.get(path, options);
  return response;
};
export const post_cus = async (path, options = {}) => {
  const response = await httpRequest_cus.post(path, options);
  return response;
};
export const get_cus = async (path, options = {}) => {
  const response = await httpRequest_cus.get(path, options);
  return response;
};

export default httpRequest;
