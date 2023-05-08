import axios from "axios";

const token = localStorage.getItem('token');
const headers = {
    'Content-Type': 'application/json',
    "Access-Control-Allow-Origin": "*",
    'Accept':  'application/json',
    'Access-Control-Allow-Methods': 'DELETE,GET,PATCH,POST,PUT',

}
if(token){
    headers['Authorization'] = `Bearer ${token}`;
}
const request =  axios.create({
    baseURL : "http://localhost:8085",
    timeout: 5000,
    headers: headers
})

export default request;