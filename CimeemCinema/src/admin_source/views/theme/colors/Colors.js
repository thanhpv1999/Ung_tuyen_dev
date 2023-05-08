import PropTypes from "prop-types";
import React, { useEffect, useState, createRef } from "react";
import classNames from "classnames";
import {
  CCol,
  CButton,
  CTable,
  CInputGroup,
  CFormInput,
  CButtonGroup,
  CDropdown,
  CDropdownToggle,
  CDropdownItem,
  CDropdownMenu,
  CDropdownDivider,
  CPagination,
  CPaginationItem,
  CModal,
  CModalHeader,
  CModalTitle,
  CModalBody,
  CForm,
  CModalFooter,
} from "@coreui/react";
import { rgbToHex } from "@coreui/utils";
import CIcon from "@coreui/icons-react";
import { cilInfo } from "@coreui/icons";
import * as bookingServices from "../../../../services/bookingService";
const ThemeView = () => {
  const [color, setColor] = useState("rgb(255, 255, 255)");

  const ref = createRef();

  useEffect(() => {
    const el = ref.current.parentNode.firstChild;
    const varColor = window
      .getComputedStyle(el)
      .getPropertyValue("background-color");
    setColor(varColor);
  }, [ref]);

  return (
    <table className="table w-100" ref={ref}>
      <tbody>
        <tr>
          <td className="text-medium-emphasis">HEX:</td>
          <td className="font-weight-bold">{rgbToHex(color)}</td>
        </tr>
        <tr>
          <td className="text-medium-emphasis">RGB:</td>
          <td className="font-weight-bold">{color}</td>
        </tr>
      </tbody>
    </table>
  );
};

const ThemeColor = ({ className, children }) => {
  const classes = classNames(className, "theme-color w-75 rounded mb-3");
  return (
    <CCol xs={12} sm={6} md={4} xl={2} className="mb-4">
      <div className={classes} style={{ paddingTop: "75%" }}></div>
      {children}
      <ThemeView />
    </CCol>
  );
};

ThemeColor.propTypes = {
  children: PropTypes.node,
  className: PropTypes.string,
};
const columns = [
  { key: "bookingId", label: "Booking Id", _props: { scope: "col" } },
  { key: "createdAt", label: "Created At", _props: { scope: "col" } },
  { key: "status", label: "Status", _props: { scope: "col" } },
  { key: "heading_3", label: "Active", _props: { scope: "col" } },
  { key: "heading_4", label: "Detail", _props: { scope: "col" } },
];

const Colors = () => {
  const [loading, setLoading] = useState(false);
  const [visible, setVisible] = useState(false);
  const [items, setItems] = useState([]);
  useEffect(() => {
    const fetchApi = async () => {
      setLoading(true);

      const result = await bookingServices.listBook();

      let cloneChat = result.map((i) => {
        if (i.status === true) {
          return {
            ...i,
            ...{ status: "true" },
            ...{
              heading_4: (
                <button
                  style={{ backgroundColor: "transparent", border: "none" }}
                >
                  <CIcon
                    icon={cilInfo}
                    size="xl"
                    onClick={() => setVisible(!visible)}
                  />
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
            },
          };
        } else {
          return {
            ...i,
            ...{ status: "false" },
            ...{
              heading_4: (
                <button
                  style={{ backgroundColor: "transparent", border: "none" }}
                >
                  <CIcon
                    icon={cilInfo}
                    size="xl"
                    onClick={() => setVisible(!visible)}
                  />
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
            },
          };
        }
      });
      // sua doi
      setItems(cloneChat);
      console.log(items);
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
      <CButton style={{ marginRight: "10px" }} color="primary">
        OK
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

export default Colors;
