import React, { useEffect, useState } from "react";
import {
  CButton,
  CCard,
  CCardBody,
  CCardHeader,
  CCol,
  CDropdown,
  CDropdownDivider,
  CDropdownItem,
  CDropdownMenu,
  CDropdownToggle,
  CForm,
  CFormInput,
  CFormSelect,
  CModal,
  CModalBody,
  CModalFooter,
  CModalHeader,
  CModalTitle,
  CPopover,
  CRow,
} from "@coreui/react";
import * as thearteService from "../../../../services/thearteService";
import * as cinemasService from "../../../../services/cinemasService";
const Buttons = () => {
  const [visible, setVisible] = useState(false);

  const [loading, setLoading] = useState(false);

  const [items, setItems] = useState([]);
  const [itemsCinema, setItemsCinema] = useState([]);

  useEffect(() => {
    const fetchApi = async () => {
      const result = await cinemasService.listCinme();
      let cloneChat = result.map((i) => {
        return {
          ...i,
        };
      });
      console.log(cloneChat);
      // sua doi
      setItems(cloneChat);
      setLoading(true);
    };

    fetchApi();
  }, []);

  return (
    <CRow>
      <CCol xs={12}>
        <CCard className="mb-4">
          <CCardHeader>
            <CButton
              style={{ marginRight: "10px" }}
              color="primary"
              onClick={() => setVisible(!visible)}
            >
              Create Theatre
            </CButton>
          </CCardHeader>

          {items.map((i) => {
            return (
              <CCardBody>
                <CPopover
                  title="Popover title"
                  content={
                    i.cinemas != null ? (
                      <CPopover
                        title="Popover title"
                        content={
                          <CDropdown variant="btn-group" direction="dropend">
                            <CButton color="danger" size="lg">
                              {i.room != null
                                ? i.room.map((i) => {
                                    return i.roomName + " " + `\n`;
                                  })
                                : ""}
                            </CButton>
                            <CDropdownToggle color="secondary" split />
                            <CDropdownMenu>
                              <CDropdownItem href="#">Action</CDropdownItem>
                              <CDropdownItem href="#">
                                Another action
                              </CDropdownItem>
                              <CDropdownItem href="#">
                                Something else here
                              </CDropdownItem>
                              <CDropdownDivider />
                              <CDropdownItem href="#">
                                Separated link
                              </CDropdownItem>
                            </CDropdownMenu>
                          </CDropdown>
                        }
                        placement="right"
                      >
                        <CButton color="danger" size="lg">
                          {i.cinemas.map((i) => {
                            return i.cinemaName + " " + `\n`;
                          })}
                        </CButton>
                      </CPopover>
                    ) : (
                      ""
                    )
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    {i.theatreName}
                  </CButton>
                </CPopover>
              </CCardBody>
            );
          })}
        </CCard>
      </CCol>
      <CModal scrollable visible={visible} onClose={() => setVisible(false)}>
        <CModalHeader>
          <CModalTitle>Modal title</CModalTitle>
        </CModalHeader>
        <CModalBody>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormSelect
                feedbackInvalid="Example invalid select feedback"
                aria-label="select example"
                label="City"
                required
              >
                <option selected="" value="">
                  Open this select menu
                </option>
                <option value="1">One</option>
                <option value="2">Two</option>
                <option value="3">Three</option>
              </CFormSelect>
            </CCol>
            <CCol md={12}>
              <CFormInput type="text" id="inputEmail4" label="Theatre Name" />
            </CCol>
            <CCol md={12}>
              <CFormInput type="number" id="inputEmail4" label="Latitude" />
            </CCol>
            <CCol md={12}>
              <CFormInput type="number" id="inputEmail4" label="Longtitude" />
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
    </CRow>
  );
};

export default Buttons;
