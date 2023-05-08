import React, { useEffect, useState } from "react";
import {
  CButton,
  CButtonGroup,
  CCol,
  CDropdown,
  CDropdownDivider,
  CDropdownItem,
  CDropdownMenu,
  CDropdownToggle,
  CForm,
  CFormInput,
  CInputGroup,
  CModal,
  CModalBody,
  CModalFooter,
  CModalHeader,
  CModalTitle,
  CPagination,
  CPaginationItem,
  CTable,
} from "@coreui/react";
import CIcon from "@coreui/icons-react";
import { cilInfo } from "@coreui/icons";
import * as langsService from "../../../../services/langsService";
const Collapses = () => {
  const [visible, setVisible] = useState(false);
  const columns = [
    { key: "languageId", label: "Language Id", _props: { scope: "col" } },
    { key: "decripstion", label: "Decripstion", _props: { scope: "col" } },
    { key: "heading_3", label: "Heading", _props: { scope: "col" } },
    { key: "heading_4", label: "Heading", _props: { scope: "col" } },
  ];
  const [loading, setLoading] = useState(false);
  const [state, setState] = useState({
    class: "Default",
    heading_4: (
      <button style={{ backgroundColor: "transparent", border: "none" }}>
        <CIcon icon={cilInfo} size="xl" />
      </button>
    ),

    heading_3: (
      <div>
        <CButton
          style={{
            marginRight: "10px",
          }}
          color="primary"
        >
          Primary
        </CButton>
        <CButton
          style={{
            marginRight: "10px",
          }}
          color="secondary"
        >
          Secondary
        </CButton>
        <CButton color="success">Success</CButton>
      </div>
    ),

    _cellProps: { class: { scope: "row" } },
  });
  const [items, setItems] = useState([]);
  const [details, setDetails] = useState([]);
  const [status, setStatus] = useState([]);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await langsService.listBook();
      let cloneChat = result.map((i) => {
        return {
          ...state,
          ...i,
        };
      });
      // sua doi
      setItems(cloneChat);
      setLoading(false);
    };

    fetchApi();
  }, []);
  return (
    <>
      <CInputGroup className="mb-3">
        <CFormInput
          placeholder="Recipient's username"
          aria-label="Recipient's username"
          aria-describedby="button-addon2"
        />
        <CButton
          type="button"
          color="secondary"
          variant="outline"
          id="button-addon2"
        >
          Button
        </CButton>
      </CInputGroup>
      <CButtonGroup
        style={{ float: "right" }}
        role="group"
        aria-label="Button group with nested dropdown"
      >
        <CDropdown variant="btn-group">
          <CDropdownToggle color="primary">Dropdown</CDropdownToggle>
          <CDropdownMenu>
            <CDropdownItem href="#">Action</CDropdownItem>
            <CDropdownItem href="#">Another action</CDropdownItem>
            <CDropdownItem href="#">Something else here</CDropdownItem>
            <CDropdownDivider />
            <CDropdownItem href="#">Separated link</CDropdownItem>
          </CDropdownMenu>
        </CDropdown>
      </CButtonGroup>
      <CButton
        style={{ marginRight: "10px" }}
        color="primary"
        onClick={() => setVisible(!visible)}
      >
        Create Language
      </CButton>
      <CButton style={{ marginRight: "10px" }} color="primary">
        Primary
      </CButton>
      <CButton color="primary">Primary</CButton>
      <CTable hover columns={columns} items={items} />
      <CPagination
        style={{ float: "right" }}
        aria-label="Page navigation example"
      >
        <CPaginationItem>Previous</CPaginationItem>
        <CPaginationItem>1</CPaginationItem>
        <CPaginationItem>2</CPaginationItem>
        <CPaginationItem>3</CPaginationItem>
        <CPaginationItem>Next</CPaginationItem>
      </CPagination>
      <CModal scrollable visible={visible} onClose={() => setVisible(false)}>
        <CModalHeader>
          <CModalTitle>Modal title</CModalTitle>
        </CModalHeader>
        <CModalBody>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput type="email" id="inputEmail4" label="Title" />
            </CCol>
          </CForm>
        </CModalBody>
        <CModalFooter>
          <CButton color="secondary" onClick={() => setVisible(false)}>
            Close
          </CButton>
          <CButton color="primary">Save changes</CButton>
        </CModalFooter>
      </CModal>
    </>
  );
};

export default Collapses;
