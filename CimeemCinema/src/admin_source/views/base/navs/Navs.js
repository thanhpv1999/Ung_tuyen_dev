import React from "react";
import { CButton, CCol, CForm, CFormInput } from "@coreui/react";

const Navs = () => {
  return (
    <CForm validated={true} className="row g-3">
      <CCol md={6}>
        <CFormInput type="email" id="inputEmail4" label="Title" />
      </CCol>

      <div className="mb-3">
        <CButton
          href="/admin/base/tables"
          type="submit"
          color="primary"
          style={{ marginRight: "10px" }}
        >
          Create Movie
        </CButton>
        <CButton href="/admin/base/placeholders" type="submit" color="primary">
          Cancel
        </CButton>
      </div>
    </CForm>
  );
};

export default Navs;
