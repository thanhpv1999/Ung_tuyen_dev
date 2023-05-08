import React, { useEffect, useState } from "react";
import {
  CAlert,
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
  CSpinner,
  CTable,
} from "@coreui/react";
import CIcon from "@coreui/icons-react";
import { cilInfo } from "@coreui/icons";
import * as moviesService from "../../../../services/moviesService";
const Placeholders = () => {
  const columns = [
    { key: "title", label: "Title", _props: { scope: "col" } },
    { key: "heading_5", label: "Image", _props: { scope: "col" } },
    { key: "views", label: "Views", _props: { scope: "col" } },
    { key: "votesAvg", label: "Votes Avg", _props: { scope: "col" } },
    { key: "votesCount", label: "Votes Count", _props: { scope: "col" } },
    { key: "dateAired", label: "Date Aired", _props: { scope: "col" } },
    { key: "status", label: "Rate", _props: { scope: "col" } },
    { key: "heading_3", label: "Active", _props: { scope: "col" } },
    { key: "heading_4", label: "Detail", _props: { scope: "col" } },
  ];
  const [loading, setLoading] = useState(false);
  const [visible, setVisible] = useState(false);
  const [visibleModal, setVisibleModal] = useState(false);

  const [items, setItems] = useState([]);
  const [order, setOrder] = useState([]);
  const deleteMovie = async (id) => {
    const deleteMovie = await moviesService.deleteMovie(id);
    console.log(deleteMovie);
    if (deleteMovie === undefined) {
      console.log("oooooooooo");
      setVisible(true);
    }
    setLoading(true);
  };
  useEffect(() => {
    const fetchApi = async () => {
      const result = await moviesService.listBook();
      let cloneChat = result.map((i) => {
        if (i.votesCount == 0) {
          return {
            ...i,
            ...{ votesCount: "0" },
            ...i.rate,
            ...{
              heading_5: (
                <button
                  style={{
                    backgroundColor: "transparent",
                    border: "none",
                  }}
                >
                  <img
                    src={i.pathImg}
                    style={{
                      width: "100px",
                      height: "150px",
                      borderRadius: "2px",
                    }}
                  />
                </button>
              ),
            },
            heading_4: (
              <button
                style={{ backgroundColor: "transparent", border: "none" }}
              >
                <CIcon
                  icon={cilInfo}
                  size="xl"
                  onClick={() => {
                    setVisibleModal(!visibleModal);
                    setOrder(i);
                  }}
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
                  onClick={() => deleteMovie(i.movieId)}
                >
                  Delete
                </CButton>
                <CButton color="success">Success</CButton>
              </div>
            ),
          };
        } else if (i.views == 0) {
          return {
            ...i,
            ...{ views: "50" },
            ...i.rate,
            ...{
              heading_5: (
                <button
                  style={{
                    backgroundColor: "transparent",
                    border: "none",
                  }}
                >
                  <img
                    src={i.pathImg}
                    style={{
                      width: "100px",
                      height: "150px",
                      borderRadius: "2px",
                    }}
                  />
                </button>
              ),
            },
            heading_4: (
              <button
                style={{ backgroundColor: "transparent", border: "none" }}
              >
                <CIcon
                  icon={cilInfo}
                  size="xl"
                  onClick={() => {
                    setVisibleModal(!visibleModal);
                    setOrder(i);
                  }}
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
                  onClick={() => deleteMovie(i.movieId)}
                >
                  Delete
                </CButton>
                <CButton color="success">Success</CButton>
              </div>
            ),
          };
        } else {
          return {
            ...i,
            ...i.rate,
            ...{
              heading_5: (
                <button
                  style={{
                    backgroundColor: "transparent",
                    border: "none",
                  }}
                >
                  <img
                    src={i.pathImg}
                    style={{
                      width: "100px",
                      height: "150px",
                      borderRadius: "2px",
                    }}
                  />
                </button>
              ),
            },
            heading_4: (
              <button
                style={{ backgroundColor: "transparent", border: "none" }}
              >
                <CIcon
                  icon={cilInfo}
                  size="xl"
                  onClick={() => {
                    setVisibleModal(!visibleModal);
                    setOrder(i);
                  }}
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
                  onClick={() => deleteMovie(i.movieId)}
                >
                  Delete
                </CButton>
                <CButton color="success">Success</CButton>
              </div>
            ),
          };
        }
      });
      // sua doi
      setItems(cloneChat);
      setLoading(true);
    };

    fetchApi();
  }, [loading]);
  return loading ? (
    <>
      <CAlert
        color="danger"
        dismissible
        visible={visible}
        onClose={() => setVisible(false)}
      >
        A simple primary alert—check it out!
      </CAlert>
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
        href="/admin/base/accordion"
        style={{ marginRight: "10px" }}
        color="primary"
      >
        Create Movie
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
      <CModal
        scrollable
        visible={visibleModal}
        onClose={() => setVisibleModal(false)}
      >
        <CModalHeader>
          <CModalTitle>Modal title</CModalTitle>
        </CModalHeader>
        <CModalBody>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="text"
                id="inputEmail4"
                label="Order Id"
                value={order.movieId}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="text"
                id="inputEmail4"
                label="Order Date"
                value={order.title}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Price"
                value={order.description}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Product Name"
                value={order.durationMin}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Product Amount"
                value={order.urlTrailer}
              />
            </CCol>
          </CForm>
        </CModalBody>
        <CModalFooter>
          <CButton color="secondary" onClick={() => setVisibleModal(false)}>
            Close
          </CButton>
          <CButton color="primary">Save changes</CButton>
        </CModalFooter>
      </CModal>
    </>
  ) : (
    <CSpinner />
  );
};

export default Placeholders;
