import React, { useState } from "react";
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
  CFormCheck,
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

const ButtonGroups = () => {
  const [visible, setVisible] = useState(false);
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
              Create Cinema
            </CButton>
          </CCardHeader>

          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
          <CCardBody>
            <CPopover
              title="Popover title"
              content={
                <CPopover
                  title="Popover title"
                  content={
                    <CDropdown variant="btn-group" direction="dropend">
                      <CButton color="danger" size="lg">
                        Click to toggle popover
                      </CButton>
                      <CDropdownToggle color="secondary" split />
                      <CDropdownMenu>
                        <CDropdownItem href="#">Action</CDropdownItem>
                        <CDropdownItem href="#">Another action</CDropdownItem>
                        <CDropdownItem href="#">
                          Something else here
                        </CDropdownItem>
                        <CDropdownDivider />
                        <CDropdownItem href="#">Separated link</CDropdownItem>
                      </CDropdownMenu>
                    </CDropdown>
                  }
                  placement="right"
                >
                  <CButton color="danger" size="lg">
                    Click to toggle popover
                  </CButton>
                </CPopover>
              }
              placement="right"
            >
              <CButton color="danger" size="lg">
                Click to toggle popover
              </CButton>
            </CPopover>
          </CCardBody>
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
                label="Theatres"
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
              <CFormInput type="text" id="inputEmail4" label="Cinema Name" />
            </CCol>
            <CCol md={12}>
              <CFormInput type="number" id="inputEmail4" label="Medium" />
            </CCol>
            <CCol md={12}>
              <CFormCheck
                className="mb-3"
                id="validationFormCheck1"
                label="Open is live"
                feedbackInvalid="If you don't check, it won't sell by default, movie showtimes are already arranged"
                required
              />
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

export default ButtonGroups;
