import React, { useEffect, useState } from "react";
import {
  CButton,
  CCol,
  CForm,
  CFormInput,
  CFormSelect,
  CPagination,
  CPaginationItem,
  CTable,
} from "@coreui/react";
import { TimePicker } from "react-ios-time-picker";

import * as showTimingService from "../../../../services/showTimingService";
const Select = () => {
  var curr = new Date();
  curr.setDate(curr.getDate());
  var date = curr.toISOString().substring(0, 10);
  const columns = [
    { key: "time", label: "Time", _props: { scope: "col" } },
    { key: "heading_3", label: "Heading", _props: { scope: "col" } },
  ];

  const [loading, setLoading] = useState(false);

  const [items, setItems] = useState([]);

  useEffect(() => {
    const fetchApi = async () => {
      const result = await showTimingService.listTime();
      let cloneChat = result.map((i) => {
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

  const [value, setValue] = useState("10:00");

  const onChange = (timeValue) => {
    setValue(timeValue + ":00");
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const response = await showTimingService.saveTime(value);
    window.location.replace("/admin/forms/select");
  };

  return (
    <>
      <CForm validated={true} className="row g-3" onSubmit={handleSubmit}>
        <TimePicker onChange={onChange} value={value} />
        <div className="mb-3">
          <CButton
            // href="/admin/base/tables"
            type="submit"
            color="primary"
            style={{ marginRight: "10px" }}
          >
            Create Time
          </CButton>
          <CButton
            href="/admin/base/placeholders"
            type="submit"
            color="primary"
          >
            Cancel
          </CButton>
        </div>
      </CForm>
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

export default Select;
