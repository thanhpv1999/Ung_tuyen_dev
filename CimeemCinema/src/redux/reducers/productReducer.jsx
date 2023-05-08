import { createSlice } from "@reduxjs/toolkit";
import axios from "axios";

const initialState = {
    arrProduct: [],
    productDetail: {},
  };


  const productReducer = createSlice({
    name: "productReducer",
    initialState,
    reducers: {
      setArrProductAction: (state, action) => {
        state.arrProduct = action.payload;
      },
      setProductDetailAction: (state, action) => {
        state.productDetail = action.payload;
      },
      changeProductQntAction: (state, action) => {
        const { prodId, increOrDecre } = action.payload;
        if (prodId === state.productDetail.id) {
          if (increOrDecre) {
            state.productDetail.quantity += 1;
          } else {
            state.productDetail.quantity -= 1;
          }
        }
      },
      getProductByKwdAction: (state, action) => {
        state.arrProduct = action.payload;
      },
    },
  });

  export const {
    setArrProductAction,
    setProductDetailAction,
    getProductByKwdAction,
    changeProductQntAction,
  } = productReducer.actions;
  
  export default productReducer.reducer;


  export const getProductApi = () => {
    return async (dispatch) => {
      try {
        //call api
        const result = await axios({
          url: "http://34.68.175.32/listTheatreShowTimesNow",
          method: "GET",
        });
        //Lấy dữ liệu về đưa lên redux
        const action = setArrProductAction(result.data.content);
        dispatch(action);
      } catch (err) {
        console.log(err);
      }
    };
  };