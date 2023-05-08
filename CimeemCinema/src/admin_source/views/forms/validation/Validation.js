import React, { useState } from "react";
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CForm,
  CFormCheck,
  CFormInput,
  CFormFeedback,
  CFormLabel,
  CFormSelect,
  CFormTextarea,
  CInputGroup,
  CInputGroupText,
  CRow,
} from "@coreui/react";
import { DocsExample } from "../../../components";
import Seatbooking from "./Seatbooking";

const Validation = () => {
  return (
    <CRow>
      <CCol>
        <CCard className="mb-4">
          <CCardHeader>
            <strong>Validation</strong> <small>Browser defaults</small>
          </CCardHeader>
          <Seatbooking />
        </CCard>
      </CCol>
    </CRow>
  );
};

export default Validation;
