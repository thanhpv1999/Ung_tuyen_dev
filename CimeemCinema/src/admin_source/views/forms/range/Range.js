import React, { useState } from "react";
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
  CFormTextarea,
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

const Range = () => {
  const [visible, setVisible] = useState(false);
  const columns = [
    { key: "class", _props: { scope: "col" } },
    { key: "heading_1", label: "Heading", _props: { scope: "col" } },
    { key: "heading_2", label: "Heading", _props: { scope: "col" } },
    { key: "heading_3", label: "Heading", _props: { scope: "col" } },
    { key: "heading_5", label: "Heading", _props: { scope: "col" } },
    { key: "heading_6", label: "Heading", _props: { scope: "col" } },
    { key: "heading_4", label: "Heading", _props: { scope: "col" } },
  ];
  const items = [
    {
      class: "Default",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
    },
    {
      class: "Primary",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
    },
    {
      class: "Secondary",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "secondary" },
    },
    {
      class: "Success",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "success" },
    },
    {
      class: "Danger",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "danger" },
    },
    {
      class: "Warning",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "warning" },
    },
    {
      class: "Info",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "info" },
    },
    {
      class: "Light",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "light" },
    },
    {
      class: "Dark",
      heading_1: "Cell",
      heading_2: "Cell",
      heading_3: (
        <button style={{ backgroundColor: "transparent", border: "none" }}>
          <CIcon icon={cilInfo} size="xl" />
        </button>
      ),
      heading_5: "Cell",
      heading_6: "Cell",
      heading_4: (
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
      _props: { color: "dark" },
    },
  ];
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
        Create Type Seat
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
              <CFormInput type="email" id="inputEmail4" label="Status" />
            </CCol>
            <CCol md={12}>
              <CFormInput type="number" id="inputEmail4" label="Age" />
            </CCol>
            <CCol md={12}>
              <CFormTextarea
                feedbackInvalid="Please enter a message in the textarea."
                id="validationTextarea"
                label="Description"
                placeholder="Required example textarea"
                required
              ></CFormTextarea>
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

export default Range;
