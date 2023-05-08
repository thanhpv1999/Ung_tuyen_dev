import React, { useEffect, useState } from "react";
import {
  CButton,
  CButtonGroup,
  CDropdown,
  CDropdownDivider,
  CDropdownItem,
  CDropdownMenu,
  CDropdownToggle,
  CFormInput,
  CInputGroup,
  CPagination,
  CPaginationItem,
  CTable,
} from "@coreui/react";
import { cilInfo } from "@coreui/icons";
import CIcon from "@coreui/icons-react";
import * as thearteShowTimeService from "../../../../services/thearteShowTimeService";
const FormControl = () => {
  const columns = [
    { key: "theatreName", label: "Theatre Name", _props: { scope: "col" } },
    { key: "time", label: "Time", _props: { scope: "col" } },
    { key: "roomName", label: "Room Name", _props: { scope: "col" } },
    { key: "title", label: "Movie Name", _props: { scope: "col" } },
    { key: "heading_3", label: "Heading", _props: { scope: "col" } },
    { key: "heading_4", label: "Heading", _props: { scope: "col" } },
  ];

  const [loading, setLoading] = useState(false);
  const [visibleModal, setVisibleModal] = useState(false);

  const [items, setItems] = useState([]);
  const [order, setOrder] = useState([]);

  useEffect(() => {
    const fetchApi = async () => {
      const result = await thearteShowTimeService.listBook();
      let cloneChat = result.map((i) => {
        return {
          ...i,
          ...i.theatres,
          ...i.showTimings,
          ...i.room,
          ...i.movie,
          ...{
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
                  // onClick={() => deleteMovie(i.movieId)}
                >
                  Delete
                </CButton>
                <CButton color="success">Success</CButton>
              </div>
            ),
          },
        };
      });
      // sua doi
      setItems(cloneChat);
      setLoading(false);
    };

    fetchApi();
  }, [loading]);
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
        href="/admin/base/accordion"
        style={{ marginRight: "10px" }}
        color="primary"
        // href="/admin/base/tables"
      >
        Create Theatre Show Time
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
    </>
  );
};

export default FormControl;
