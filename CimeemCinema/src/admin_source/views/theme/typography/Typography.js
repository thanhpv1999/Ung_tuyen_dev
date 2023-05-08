import React, { useEffect, useState } from "react";
import {
  CTable,
  CButton,
  CFormInput,
  CInputGroup,
  CButtonGroup,
  CDropdown,
  CDropdownToggle,
  CDropdownItem,
  CDropdownMenu,
  CDropdownDivider,
  CPagination,
  CPaginationItem,
  CModalFooter,
  CCol,
  CModal,
  CModalHeader,
  CForm,
  CModalBody,
  CModalTitle,
} from "@coreui/react";
import CIcon from "@coreui/icons-react";
import { cilInfo } from "@coreui/icons";
import * as ordersService from "../../../../services/ordersService";
const Typography = () => {
  const [visible, setVisible] = useState(false);
  const columns = [
    // { key: "class", _props: { scope: "col" } },
    { key: "orderId", label: "Order Id", _props: { scope: "col" } },
    { key: "orderDate", label: "Order Date", _props: { scope: "col" } },
    { key: "subtotal", label: "Subtotal", _props: { scope: "col" } },
    { key: "name", label: "Status", _props: { scope: "col" } },
    { key: "heading_3", label: "Active", _props: { scope: "col" } },
    { key: "heading_4", label: "Detail", _props: { scope: "col" } },
  ];
  const [loading, setLoading] = useState(false);

  const [items, setItems] = useState([]);
  const [order, setOrder] = useState([]);
  useEffect(() => {
    const fetchApi = async () => {
      const result = await ordersService.listOrder();
      let cloneChat = result.map((i) => {
        if (i.orderDetail != null) {
          return {
            ...i,
            ...i.orderDetail,
            ...i.orderDetail.status,
            ...{
              heading_3: (
                <div>
                  <CButton
                    style={{
                      marginRight: "10px",
                    }}
                    color="primary"
                    onClick={() => {
                      ordersService.confirm(i.orderDetail.status.statusId);
                      setLoading(true);
                    }}
                  >
                    Confirm
                  </CButton>
                  <CButton
                    style={{
                      marginRight: "10px",
                    }}
                    color="secondary"
                    onClick={() => {
                      ordersService.cancel(i.orderDetail.status.statusId);
                      setLoading(true);
                    }}
                  >
                    Cancel
                  </CButton>
                  <CButton
                    color="success"
                    onClick={() => {
                      ordersService.print(i.orderId);
                    }}
                  >
                    Print
                  </CButton>
                </div>
              ),
              heading_4: (
                <button
                  style={{ backgroundColor: "transparent", border: "none" }}
                >
                  <CIcon
                    icon={cilInfo}
                    size="xl"
                    onClick={() => {
                      setVisible(!visible);
                      setOrder(i);
                    }}
                  />
                </button>
              ),
            },
          };
        } else {
          return {
            ...i,
            ...{
              heading_3: (
                <div>
                  <CButton
                    style={{
                      marginRight: "10px",
                    }}
                    color="primary"
                  >
                    Confirm
                  </CButton>
                  <CButton
                    style={{
                      marginRight: "10px",
                    }}
                    color="secondary"
                  >
                    Cancel
                  </CButton>
                  <CButton color="success">Print</CButton>
                </div>
              ),
              heading_4: (
                <button
                  style={{ backgroundColor: "transparent", border: "none" }}
                >
                  <CIcon
                    icon={cilInfo}
                    size="xl"
                    onClick={() => {
                      setVisible(!visible);
                      setOrder(i);
                    }}
                  />
                </button>
              ),
            },
          };
        }
      });
      // sua doi
      setItems(cloneChat);
      setLoading(false);
    };

    fetchApi();
  }, [loading, visible]);
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
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Order Id"
                value={order.orderId}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Order Date"
                value={order.orderDate}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Price"
                value={order.price}
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Product Name"
                value={
                  order.orderDetail != null
                    ? order.orderDetail.product.name
                    : ""
                }
              />
            </CCol>
          </CForm>
          <CForm validated={true} className="row g-3">
            <CCol md={12}>
              <CFormInput
                type="email"
                id="inputEmail4"
                label="Product Amount"
                value={
                  order.orderDetail != null
                    ? order.orderDetail.product.amount
                    : ""
                }
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
    </>
  );
};

export default Typography;
